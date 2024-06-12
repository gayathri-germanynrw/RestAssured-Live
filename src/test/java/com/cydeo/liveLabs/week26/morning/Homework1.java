package com.cydeo.liveLabs.week26.morning;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Homework1  {

    String url= "http://52.90.163.139:1000/ords/hr/";
    @Test
    public void task1() {
//* - Given accept type is Json
//* - When users sends request to /countries/US
        Response response = given().accept(ContentType.JSON)
                .when().get(url+"countries/US");

        //response.prettyPrint();

//* - Then status code is 200
        //opt1
        int statusCode = response.getStatusCode();
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        Assertions.assertEquals(200,statusCode);

        //opt2
        Assertions.assertEquals(200,response.getStatusCode());

        //opt3
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());

//* - And Content - Type is application/json

        //opt1
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);
        Assertions.assertEquals("application/json",contentType);

        //opt2
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());

//* - And response contains United States of America

        //opt1
        String bodyAsString = response.body().asPrettyString();
        //System.out.println("bodyAsString = " + bodyAsString);
        Assertions.assertTrue(bodyAsString.contains("United States of America"));

        //opt2
        String countryName = response.jsonPath().getString("country_name");
        System.out.println("countryName = " + countryName);


    }

    /*
     * Task 2 : NEGATIVE TESTS
     * - Given accept type is Json
     * - When users sends request to /employees/1
     * - Then status code is 404
     */
    @Test
    public void task2() {
        Response response = given().accept(ContentType.JSON)
                .when().get(url+"employees/1");





















    }

    /*
     * Task 3 :
     * - Given Accept type  is Json
     * - When users sends request to /regions/1
     * - Then status code is 200
     * - And Content - Type is application/json
     * - And response contains Europe
     * - And header should contains Date
     * - And "Transfer-Encoding" should be "chunked"


     */

}
