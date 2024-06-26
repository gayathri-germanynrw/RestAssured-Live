package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public abstract class ZipCodeTestBase {


    @BeforeAll
    public static void init(){

        baseURI="http://api.zippopotam.us";
    }


    @AfterAll
    public static void destroy(){

        reset(); // Reset all variables to default values
    }
}