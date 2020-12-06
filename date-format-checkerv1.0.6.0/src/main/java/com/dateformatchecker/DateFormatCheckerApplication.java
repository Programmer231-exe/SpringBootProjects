package com.dateformatchecker;

import com.dateformatchecker.configuration.KafkaConsumerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(KafkaConsumerConfig.class)
public class DateFormatCheckerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DateFormatCheckerApplication.class, args);
    }

}
