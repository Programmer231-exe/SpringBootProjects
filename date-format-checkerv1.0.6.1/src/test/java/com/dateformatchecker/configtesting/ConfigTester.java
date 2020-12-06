package com.dateformatchecker.configtesting;

import com.dateformatchecker.configuration.KafkaProducerConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class ConfigTester {

    @Autowired
    private KafkaProducerConfig config;

    @Test
    void checkProperties(){
        config.convertObjToMap();
        Assertions.assertAll(
                () -> assertEquals("Assertion failed","localhost:9092",config.getBOOTSTRAP_SERVERS())
        );
    }

}
