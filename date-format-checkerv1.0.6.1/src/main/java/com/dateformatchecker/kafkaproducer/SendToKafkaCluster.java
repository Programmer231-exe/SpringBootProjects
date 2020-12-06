package com.dateformatchecker.kafkaproducer;

import com.dateformatchecker.configuration.KafkaProducerConfig;
import com.dateformatchecker.model.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class SendToKafkaCluster {
    @Autowired
    private KafkaProducerConfig producerConfig;
    private Producer<String,User> producer;
    private static final Logger LOGGER = LogManager.getLogger(SendToKafkaCluster.class);


    public boolean send(User user){
        String id = String.valueOf(user.hashCode());
        LOGGER.warn(id);
        producer = new KafkaProducer<String,User>(producerConfig.convertObjToMap());
        Future<RecordMetadata> recordMetadataFuture = producer.send(new ProducerRecord<String,User>("date-checker2",id,user));
        try{
            LOGGER.info("Record Meta Data: " + recordMetadataFuture.get().toString());
            LOGGER.info("Sending done: " + recordMetadataFuture.isDone());
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        producer.close();
        if(recordMetadataFuture.isDone()){
            return true;
        }else{
            return false;
        }

    }

    public SendToKafkaCluster() {
    }


    }

    /*
     private KafkaConsumer<String,User> consumer;

    public void getData(){
        boolean isValidCall = true;
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("group.id","Checker");
        properties.setProperty("enable.auto.commit","true");
        properties.setProperty("auto.commit.interval.ms","1000");
        properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer","com.dateformatchecker.serializers.UserDeserializer");
        properties.setProperty("auto.offset.reset","latest");
        consumer = new KafkaConsumer<String,User>(properties);
        consumer.subscribe(Arrays.asList("date-checker2"));

        while(isValidCall){
           // consumer.seekToBeginning(consumer.assignment());
            ConsumerRecords<String,User> records = consumer.poll(Duration.ofMillis(1000));
           LOGGER.info(records);
            int count = records.count();
            LOGGER.warn(count);


            for(ConsumerRecord<String,User> record : records){
                    LOGGER.warn(">>>>>>" + record.key() + " " + "Record Value : " + record.value().getDate() + " " + record.value().getSlo());
                    isValidCall = false;
                    consumer.commitSync();
                }
            }
        }
     */



