package com.dateformatchecker.propertiesclass;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "constantvalues")
public class ConstantDate {
    private String date;

    public ConstantDate(String date) {
        this.date = date;
    }

    public ConstantDate(){
        super();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
