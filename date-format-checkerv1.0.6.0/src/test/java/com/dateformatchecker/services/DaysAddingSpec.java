package com.dateformatchecker.services;

import com.dateformatchecker.exceptionhandling.DateFormatMismatchException;
import com.dateformatchecker.kafkaconsumer.KafkaConsumerDateFetcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class DaysAddingSpec {

    @Autowired
    private KafkaConsumerDateFetcher kafkaConsumerDateFetcher;
    @Test
    public void kk(){
        kafkaConsumerDateFetcher.getUser();
        assertTrue(true);
    }
}
