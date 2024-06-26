package com.cydeo.liveLabs.week27;

import com.cydeo.pojo.Place;
import com.cydeo.pojo.State;
import com.cydeo.utility.ZipCodeTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class Homework3 extends ZipCodeTestBase {

    /**
     * TASK 1
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us/{zipcode} endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     * post code is 22031
     * country is United States
     * country abbreviation is US
     * place name is Fairfax
     * state is Virginia
     */
    @Test
    public void task1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("zipCode", 22031).
                when().get("/us/{zipCode}");


        //     *     Then status code must be 200
        Assertions.assertEquals(200, response.statusCode());

        //     *     And content type must be application/json
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());

        //     *     And Server header is cloudflare
        Assertions.assertEquals("cloudflare", response.header("Server"));

        //     *     And Report-To header exists
        assertTrue(response.getHeaders().hasHeaderWithName("Report-To"));

        //     *     And body should contains following information
        JsonPath jp = response.jsonPath();
        //     *     post code is 22031
        Assertions.assertEquals("22031", jp.getString("'post code'"));

        //     *     country is United States
        Assertions.assertEquals("United States", jp.getString("country"));

        //     *     country abbreviation is US
        Assertions.assertEquals("US", jp.getString("'country abbreviation'"));

        //     *     place name is Fairfax
        Assertions.assertEquals("Fairfax", jp.getString("places[0].'place name'"));

        //     *     state is Virginia
        Assertions.assertEquals("Virginia", jp.getString("places[0].state"));


    }

    @Test
    public void task1HamCrest() {


        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("postalCode", 22031).
                when().get("/us/{postalCode}")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server", is("cloudflare"))
                .header("Report-To", notNullValue())
                .body("'post code'", is("22031"))
                .body("country", is("United States"))
                .body("'country abbreviation'", is("US"))
                .body("places[0].'place name'", is("Fairfax"))
                .body("places[0].state", is("Virginia"))
                .extract().jsonPath();
    }

    /**
     * TASK 2
     * Given Accept application/json
     * And path zipcode is 50000
     * When I send a GET request to /us/{postCode} endpoint
     * Then status code must be 404
     * <p>
     * 404 --> Not Found
     * NEGATIVE --> Intentional wrong data to get expected values
     * --> 404
     * --> {
     * "message":"Data is not exist",
     * "status" :404
     * }
     */

    @Test
    public void task2() {
        RestAssured.given().accept(ContentType.JSON)
                .pathParam("zipCode", 50000).
                when().get("/us/{zipCode}")
                .then().statusCode(404);
    }
    /**
     * TASK 3
     *     Given Accept application/json
     *     And path state is va
     *     And path city is Fairfax
     *     When I send a GET request to /us/{state}/{city} endpoint
     *     Then status code must be 200
     *     And content type must be application/json
     *     And payload should contains following information
     *     country abbreviation is US
     *     country United States
     *     place name Fairfax
     *     each places must contains fairfax as a value
     *     each post code must start with 22
     */

    @Test
    public void task3() {


        Response response = given().accept(ContentType.JSON).
                pathParams(getStateAndCityMap("va","Fairfax")).
                when().get("/us/{state}/{city}");

        //     *     Then status code must be 200
        Assertions.assertEquals(200,response.statusCode());

        //     *     And content type must be application/json
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());


        //     *     And payload should contains following information
        JsonPath jp = response.jsonPath();

        State stateInfo = jp.getObject("", State.class);
        System.out.println("stateInfo = " + stateInfo);

        //     *     country abbreviation is US
        String countryAbbreviation = stateInfo.getCountryAbbreviation();
        System.out.println("countryAbbreviation = " + countryAbbreviation);
        Assertions.assertEquals("US",countryAbbreviation);

        //     *     country United States
        String country = stateInfo.getCountry();
        System.out.println("country = " + country);
        Assertions.assertEquals("United States",country);

        //     *     place name Fairfax
        String placeName = stateInfo.getPlaceName();
        System.out.println("placeName = " + placeName);
        Assertions.assertEquals("Fairfax",placeName);

        assertAll("Learning Soft Assert with Junit5",
                ()-> assertEquals("US",countryAbbreviation), // 1
                ()-> assertEquals("United States",country),
                ()-> assertEquals("Fairfax",placeName) // 2
        );


        List<Place> allPlaces = stateInfo.getPlaces();
        System.out.println("allPlaces = " + allPlaces);


        //     *     each places must contains Fairfax as a value
        //     *     each post code must start with 22

        for (Place eachPlace : allPlaces) {
            assertTrue(eachPlace.getPlaceName().contains("Fairfax"));
            assertTrue(eachPlace.getPostCode().startsWith("22"));
        }

    }

    private static Map<String, String> getStateAndCityMap(String state,String city) {
        Map<String,String> pathParams=new HashMap<>();
        pathParams.put("state",state);
        pathParams.put("city",city);
        return pathParams;
    }
}