package com.dateformatchecker.kafkaconsumer;

import com.dateformatchecker.configuration.KafkaConsumerConfig;
import com.dateformatchecker.model.ResultDate;
import com.dateformatchecker.model.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;


@Component
public class KafkaConsumerDateFetcher {
    @Autowired
    private KafkaConsumerConfig consumerConfig;
    private KafkaConsumer<String, User> consumer;
    private static final Logger LOGGER = LogManager.getLogger(KafkaConsumerDateFetcher.class);

    public User getUser(){
        User user = null;
        boolean isValidCall = true;
        consumer = new KafkaConsumer<String,User>(consumerConfig.convertObjToMap());
        consumer.subscribe(Arrays.asList("date-checker2"));

        while(isValidCall){
            // consumer.seekToBeginning(consumer.assignment());
            ConsumerRecords<String,User> records = consumer.poll(Duration.ofMillis(1000));
            LOGGER.info(records.toString());
            int count = records.count();
            LOGGER.warn(count);


            for(ConsumerRecord<String,User> record : records){
                LOGGER.warn(">>>>>>" + record.key() + " " + "Record Value : " + record.value().getDate() + " " + record.value().getSlo());
                user = record.value();
                if(user!= null){
                    isValidCall = false;
                }

                consumer.commitAsync();
            }
        }
        consumer.close();
        return user;
    }
    }


