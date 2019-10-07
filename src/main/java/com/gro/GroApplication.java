package com.gro;

import com.gro.security.MicromonitorProperties;
import com.gro.simulator.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MicromonitorProperties.class})
public class GroApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GroApplication.class, args);
    }

    @Autowired
    public SchedulerService schedulerService;

    @Override
    public void run(String... strings) throws Exception {
        schedulerService.createSchedule("*/1 * * * * ?");

    }
}
