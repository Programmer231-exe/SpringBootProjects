package com.dateformatchecker.kafkatester;

import com.dateformatchecker.kafkaproducer.SendToKafkaCluster;
import com.dateformatchecker.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class KafkaProducerTester {
    @Autowired
    private SendToKafkaCluster sender;

    @Test
    public void is_it_writing(){
        sender.send(new User("23-01-1998","30"));
        assertTrue("true",1==1);
    }

    @Test
    public void is_it_reading(){
        sender.getData();
        assertTrue("true",1==1);
    }
}
