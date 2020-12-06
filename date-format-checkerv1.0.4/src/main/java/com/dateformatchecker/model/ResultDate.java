package com.dateformatchecker.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ResultDate {
    private String resultDate;

    @ApiModelProperty(position = 1, required = true,value = "23-01-1999",example = "dd-MM-yyyy")
    public String getResultDate() {
        return resultDate;
    }

    public ResultDate(){
        super();
    }

    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }

    public ResultDate(String resultDate) {
        this.resultDate = resultDate;
    }
}
