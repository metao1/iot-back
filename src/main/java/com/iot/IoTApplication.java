package com.iot;

import com.iot.security.MicromonitorProperties;
import com.iot.simulator.MqttPublisherClientService;
import com.iot.simulator.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MicromonitorProperties.class})
public class IoTApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IoTApplication.class, args);
    }

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private MqttPublisherClientService mqttPublisherClientService;

    @Override
    public void run(String... strings) throws Exception {
        schedulerService.createSchedule("*/1 * * * * ?");
        mqttPublisherClientService.run();
    }
}
