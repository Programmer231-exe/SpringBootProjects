package com.dateformatchecker.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@ConstructorBinding
@ConfigurationProperties(prefix = "kafka.constants")
public class KafkaConsumerConfig {
    private final Logger LOGGER = LogManager.getLogger();
    private final String BOOTSTRAP_SERVERS;
    private final String GROUP_ID;
    private final String ENABLE_AUTO_COMMIT;
    private final String AUTO_COMMIT_INTERVAL_MS;
    private final String KEY_DESERIALIZER;
    private final String VALUE_DESERIALIZER;
    private final String AUTO_OFFSET_RESET;

    public String getBOOTSTRAP_SERVERS() {
        return BOOTSTRAP_SERVERS;
    }

    public String getGROUP_ID() {
        return GROUP_ID;
    }

    public String getENABLE_AUTO_COMMIT() {
        return ENABLE_AUTO_COMMIT;
    }

    public String getAUTO_COMMIT_INTERVAL_MS() {
        return AUTO_COMMIT_INTERVAL_MS;
    }

    public String getKEY_DESERIALIZER() {
        return KEY_DESERIALIZER;
    }

    public String getVALUE_DESERIALIZER() {
        return VALUE_DESERIALIZER;
    }

    public String getAUTO_OFFSET_RESET() {
        return AUTO_OFFSET_RESET;
    }

    public KafkaConsumerConfig(String BOOTSTRAP_SERVERS, String GROUP_ID, String ENABLE_AUTO_COMMIT, String AUTO_COMMIT_INTERVAL_MS, String KEY_DESERIALIZER, String VALUE_DESERIALIZER, String AUTO_OFFSET_RESET) {
        this.BOOTSTRAP_SERVERS = BOOTSTRAP_SERVERS;
        this.GROUP_ID = GROUP_ID;
        this.ENABLE_AUTO_COMMIT = ENABLE_AUTO_COMMIT;
        this.AUTO_COMMIT_INTERVAL_MS = AUTO_COMMIT_INTERVAL_MS;
        this.KEY_DESERIALIZER = KEY_DESERIALIZER;
        this.VALUE_DESERIALIZER = VALUE_DESERIALIZER;
        this.AUTO_OFFSET_RESET = AUTO_OFFSET_RESET;
    }

    public Map<String,Object> convertObjToMap(){
        Map<String,Object> kafkaProducerMap = new HashMap<>();
        Field[] allFields = this.getClass().getDeclaredFields();
        Object value = null;
        String fieldName = "";

        for(Field field : allFields){
            field.setAccessible(true);
            try{
                value = field.get(this);
                fieldName = field.getName();
                if(fieldName.equalsIgnoreCase("LOGGER")){
                    continue;
                }
                if(fieldName.contains("_")){
                    fieldName = fieldName.replaceAll( "_",".");
                }
                System.out.println(fieldName + " "+value.toString());
                kafkaProducerMap.put(fieldName.toLowerCase(),value);
            }catch(IllegalAccessException iae){
                LOGGER.error("Illegal Access exception occurs ");
            }

        }

        return kafkaProducerMap;

    }
}
