package com.iot;

import com.iot.security.MicromonitorProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MicromonitorProperties.class})
public class IotApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IotApplication.class, args);
    }

/*    @Autowired
    public SchedulerService schedulerService;*/

    @Override
    public void run(String... strings) throws Exception {
        //schedulerService.createSchedule("*/1 * * * * ?");

    }
}
