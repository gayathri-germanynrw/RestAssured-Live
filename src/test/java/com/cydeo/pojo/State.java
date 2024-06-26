package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class State {

    @JsonProperty("country abbreviation")
    private String countryAbbreviation;

    private String country;

    @JsonProperty("place name")
    private String placeName;

    private String state;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;

    private List<Place> places;



}