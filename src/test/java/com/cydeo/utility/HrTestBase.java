package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HrTestBase {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://3.84.109.78:1000/ords/hr";

    }

}
