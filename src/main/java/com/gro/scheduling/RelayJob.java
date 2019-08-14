package com.gro.scheduling;

import com.gro.messaging.service.RelayEmitterService;
import com.gro.messaging.transformer.RelayMessageTransformer;
import com.gro.model.NotFoundException;
import com.gro.model.relay.RelayScheduleJob;
import com.gro.model.rpicomponent.AbstractRPiComponent;
import com.gro.model.rpicomponent.data.RelayDTO;
import com.gro.model.rpicomponent.data.RelayState;
import com.gro.repository.rpicomponent.RPiComponentRepository;
import com.gro.web.service.RelayService;
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
import java.util.Optional;

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
    private RPiComponentRepository relayRepository;
    @Autowired
    private Jackson2JsonObjectMapper jackson2JsonObjectMapper;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        RelayScheduleJob job = (RelayScheduleJob) context.getJobDetail().getJobDataMap().get("schedule");
        if (job != null) {

            RelayDTO relayDto = new RelayDTO(job.getComponent(), job.getState());
            Optional<AbstractRPiComponent> relay = relayRepository.findById(5);
            relayDto.setComponent(relay.orElseThrow(() -> new NotFoundException("The component not found")));
            relayDto.setState(RelayState.ON);
            try {
                relayService.toggle(relayDto);
                Map<String, Object> headers = new HashMap<>();
                headers.put("relay", relayDto);
                String toJson = jackson2JsonObjectMapper.toJson(relayDto);
                Message<String> message = MessageBuilder.createMessage(toJson, new MessageHeaders(headers));
                logger.info("relay is {}", message.getPayload());
                try {
                    Message<RelayDTO> transform = relayMessageTransformer.transform(relayDto, message);
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
