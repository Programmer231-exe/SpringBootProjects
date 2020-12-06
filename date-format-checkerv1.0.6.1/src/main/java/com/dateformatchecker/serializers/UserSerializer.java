package com.dateformatchecker.serializers;

import com.dateformatchecker.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class UserSerializer implements Serializer<User> {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, User data) {
        byte[] serializedBytes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            serializedBytes = objectMapper.writeValueAsString(data).getBytes();
        }catch(JsonProcessingException jpe){
            LOGGER.error("Error occurs while converting java objects into json");
        }
        return serializedBytes;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, User data) {
        byte[] serializedBytes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            serializedBytes = objectMapper.writeValueAsString(data).getBytes();
        }catch(JsonProcessingException jpe){
            LOGGER.error("Error occurs while converting java objects into json");
        }
        return serializedBytes;
    }

    @Override
    public void close() {

    }
}
