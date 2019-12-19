package com.iot.scheduling;

import com.iot.messaging.service.RelayEmitterService;
import com.iot.messaging.transformer.RelayMessageTransformer;
import com.iot.model.relay.RelayScheduleJob;
import com.iot.model.rpicomponent.AbstractRPiComponent;
import com.iot.model.rpicomponent.component.Relay;
import com.iot.model.rpicomponent.data.RelayDTO;
import com.iot.model.rpicomponent.data.RelayState;
import com.iot.repository.rpicomponent.RelayRepository;
import com.iot.web.service.RelayService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RelayJob implements Job {

    @Autowired
    private RelayService relayService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RelayEmitterService humidityEmitterService;

    @Autowired
    RelayMessageTransformer relayMessageTransformer;

    @Autowired
    private RelayRepository relayRepository;
    @Autowired
    private Jackson2JsonObjectMapper jackson2JsonObjectMapper;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        RelayScheduleJob job = (RelayScheduleJob) context.getJobDetail().getJobDataMap().get("schedule");
        if (job != null) {
            RelayDTO relayDto = new RelayDTO(job.getComponent(), job.getState());
            Iterable<Relay> allRelays = relayRepository.findAll();
            if (allRelays == null) {
                return;
            }
            if (!allRelays.iterator().hasNext()) {
                return;
            }
            for (AbstractRPiComponent relay : allRelays) {
                relayDto.setComponent(relay);
                relayDto.setState(RelayState.ON);
                try {
                    relayService.toggle(relayDto);
                    Map<String, Object> headers = new HashMap<>();
                    headers.put("relay", relayDto);
                    String toJson = jackson2JsonObjectMapper.toJson(relayDto);
                    Message<String> message = MessageBuilder.createMessage(toJson, new MessageHeaders(headers));
                    logger.info("relay is {}", message.getPayload());
                    try {
                        Message<RelayDTO> transform = relayMessageTransformer.transform(message);
                        humidityEmitterService.process(transform);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

}
