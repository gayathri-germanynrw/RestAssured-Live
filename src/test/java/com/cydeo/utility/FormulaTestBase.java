package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class FormulaTestBase {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://ergast.com/api/f1";

    }


}