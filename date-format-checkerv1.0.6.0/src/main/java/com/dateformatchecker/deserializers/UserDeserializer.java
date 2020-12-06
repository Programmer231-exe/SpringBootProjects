package com.dateformatchecker.deserializers;

import com.dateformatchecker.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class UserDeserializer implements Deserializer<User> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public User deserialize(String topic, byte[] data) {
        ObjectMapper oMapper = new ObjectMapper();
        User user = null;
        try{
            user = oMapper.readValue(data,User.class);
        }catch(JsonParseException jpe){
            return null;
        }catch(JsonMappingException jme){
            return null;
        }catch(IOException ioe){
            return null;
        }
        return user;
    }

    @Override
    public User deserialize(String topic, Headers headers, byte[] data) {
        ObjectMapper oMapper = new ObjectMapper();
        User user = null;
        try{
            user = oMapper.readValue(data,User.class);
        }catch(JsonParseException jpe){
            return null;
        }catch(JsonMappingException jme){
            return null;
        }catch(IOException ioe){
            return null;
        }
        return user;
    }

    @Override
    public void close() {

    }
}
