package com.dateformatchecker.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class User {

    @ApiModelProperty(position = 1, required = true,value = "23-01-1999",allowableValues = "dd-MM-yyyy",example = "23-01-1999")
    private String date;
    private String slo;

    public User(String date, String slo) {
        this.date = date;
        this.slo = slo;
    }

    public User() {
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @ApiModelProperty(position = 2,required = true,value = "40",example = "40")
    public String getSlo() {
        return slo;
    }

    public void setSlo(String slo) {
        this.slo = slo;
    }
}
