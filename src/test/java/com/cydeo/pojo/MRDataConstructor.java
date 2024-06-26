package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MRDataConstructor {

    private String limit;
    private String total;
    private String offset;
    @JsonProperty("ConstructorTable")
    private ConstructorTable constructorTable;

}