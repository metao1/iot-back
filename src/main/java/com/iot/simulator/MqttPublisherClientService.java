package com.iot.simulator;

import com.metao.mqtt.pack.MqttClient;
import com.metao.mqtt.pack.MqttClientImpl;
import com.metao.mqtt.pack.MqttConnectResult;
import com.metao.mqtt.pack.MqttHandler;

import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.*;

@Configuration
public class MqttPublisherClientService {

    private final Logger LOG = LoggerFactory.getLogger(MqttPublisherClientService.class);
    private final Future<MqttConnectResult> mqttConnectResultFuture;
    @Value("${mqtt.host}")
    private String mqttHost = "localhost";

    @Value("${mqtt.port}")
    private int mqttPort = 1880;

    private  static final Queue<Message> mqttMessages = new PriorityBlockingQueue<>(10);
    private  MqttConnectResult mqttConnectResult = null;
    private final MqttHandler mqttHandler = (topic, payload) -> {

    };
    private Executor executor = Executors.newFixedThreadPool(5);
    private CompletionService<MqttResult> executorCompletionService = new ExecutorCompletionService<>(executor);

    public MqttPublisherClientService() {
        MqttClient mqttClient = new MqttClientImpl(mqttHandler);
        mqttConnectResultFuture = mqttClient.connect(mqttHost, mqttPort);
    }

    /**
     * @param message to be added to the message pool
     * @return true if Successful to add message to messageQueue
     */
    public boolean addToMqttMessagePool(Message message){
        return mqttMessages.offer(message);
    }

    public void run() throws InterruptedException, ExecutionException, TimeoutException {
        if(mqttConnectResultFuture.isSuccess()) {
            LOG.info("The publisher connected to the Mqtt Server");
            mqttConnectResult = mqttConnectResultFuture.get(5000, TimeUnit.MILLISECONDS);
            executor.execute(() -> {
                Message task;
                while ((task = mqttMessages.poll()) != null && mqttConnectResult != null && mqttConnectResult.isSuccess()) {
                    executorCompletionService.submit(new Worker(task));
                }
            });
        }else{
            LOG.warn("The publisher can't connect to the Mqtt Server");
        }
    }

    //this will be run in the executor thread
    private class Worker implements Callable<MqttResult>{
        private final Message message;

        public Worker(Message message) {
            this.message = message;
        }

        @Override
        public MqttResult call() throws Exception {
            //send the mqtt message
            message.getPayload();
            //mqttClient.publish()
            return new MqttResult();
        }
    }
}
