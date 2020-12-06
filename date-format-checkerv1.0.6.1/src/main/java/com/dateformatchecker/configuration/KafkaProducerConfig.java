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
public class KafkaProducerConfig {

    private static final Logger LOGGER = LogManager.getLogger(KafkaProducerConfig.class);

    private final String BOOTSTRAP_SERVERS;
    private final String ACKS;
    private final String KEY_SERIALIZER;
    private final String VALUE_SERIALIZER;
    private final String ENABLE_IDEMPOTENCE;

    public KafkaProducerConfig(String BOOTSTRAP_SERVERS, String ACKS, String KEY_SERIALIZER, String VALUE_SERIALIZER, String ENABLE_IDEMPOTENCE) {
        this.BOOTSTRAP_SERVERS = BOOTSTRAP_SERVERS;
        this.ACKS = ACKS;
        this.KEY_SERIALIZER = KEY_SERIALIZER;
        this.VALUE_SERIALIZER = VALUE_SERIALIZER;
        this.ENABLE_IDEMPOTENCE = ENABLE_IDEMPOTENCE;
    }

    public String getENABLE_IDEMPOTENCE() {
        return ENABLE_IDEMPOTENCE;
    }

    public String getBOOTSTRAP_SERVERS() {
        return BOOTSTRAP_SERVERS;
    }

    public String getACKS() {
        return ACKS;
    }

    public String getKEY_SERIALIZER() {
        return KEY_SERIALIZER;
    }

    public String getVALUE_SERIALIZER() {
        return VALUE_SERIALIZER;
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
                   fieldName = fieldName.replace("_",".");
               }
               kafkaProducerMap.put(fieldName.toLowerCase(),value);
           }catch(IllegalAccessException iae){
               LOGGER.error("Illegal Access exception occurs ");
           }

        }

        return kafkaProducerMap;

    }
}
