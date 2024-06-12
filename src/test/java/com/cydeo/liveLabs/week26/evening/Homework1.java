package com.cydeo.liveLabs.week26.evening;

import com.cydeo.utility.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Homework1  extends HrTestBase {

    /**

     * Task 1 :
     * - Given accept type is Json
     * - When users sends request to /countries/US
     * - Then status code is 200
     * - And Content - Type is application/json
     * - And response contains United States of America
     *
     *   Given --> Always condition that you have
     *   When  --> Action
     *   Then  --> Verification

     */

    @Test
    public void task1() {

        Response response = RestAssured.given().log().uri()
                .accept(ContentType.JSON)
                .when().get("/countries/US");

        response.prettyPrint();

        //     * - Then status code is 200
        Assertions.assertEquals(200,response.statusCode());


        //     * - And Content - Type is application/json
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());


        //     * - And response contains United States of America
        Assertions.assertTrue(response.body().asString().contains("United States of America"));


        // Response Path
        String countryName = response.path("country_name");
        Assertions.assertEquals("United States of America",countryName);


        // JsonPath
        JsonPath jp = response.jsonPath();
        String cName = jp.getString("country_name");
        Assertions.assertEquals("United States of America",cName);


    }
    /**
     *
     * Task 2 : NEGATIVE TESTS
     * - Given accept type is Json
     * - When users sends request to /employees/1
     * - Then status code is 404
     *
     */
    @Test
    public void task2() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/employees/1");

        Assertions.assertEquals(404,response.statusCode());


    }


    /**
     * Task 3 :
     * - Given Accept type  is Json
     * - When users sends GET request to /regions/1
     * - Then status code is 200
     * - And Content - Type is application/json
     * - And response contains Europe
     * - And header should contains Date
     * - And "Transfer-Encoding" should be "chunked"
     */

    @Test
    public void task3() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 1)
                .when().get("regions/{id}");


        //     * - Then status code is 200
        Assertions.assertEquals(200,response.statusCode());

        //     * - And Content - Type is application/json
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());


        //     * - And response contains Europe
        //     Init JSONPATH Object
        JsonPath jp = response.jsonPath();
        String regionName = jp.getString("region_name");
        Assertions.assertEquals("Europe",regionName);


        //     * - And header should contains Date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //     * - And "Transfer-Encoding" should be "chunked"
        String header = response.header("Transfer-Encoding");
        Assertions.assertEquals("chunked",header);


    }
}
