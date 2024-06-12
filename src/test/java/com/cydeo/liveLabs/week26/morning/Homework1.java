package com.cydeo.liveLabs.week26.morning;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


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
        assertEquals(200,statusCode);

        //opt2
        assertEquals(200,response.getStatusCode());

        //opt3
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

//* - And Content - Type is application/json

        //opt1
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);
        assertEquals("application/json",contentType);

        //opt2
        assertEquals(ContentType.JSON.toString(), response.contentType());

//* - And response contains United States of America

        //opt1
        String bodyAsString = response.body().asPrettyString();
        //System.out.println("bodyAsString = " + bodyAsString);
        Assertions.assertTrue(bodyAsString.contains("United States of America"));

        //opt2
        String countryName = response.jsonPath().getString("country_name");
        System.out.println("countryName = " + countryName);


    }

    @Test
    public void task2() {
//     * - Given accept type is Json
//     * - When users sends request to /employees/1
        Response response = given().accept(ContentType.JSON)
                .when().get(url+"employees/1");

//* - Then status code is 404
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        assertEquals(404,response.getStatusCode());
        assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
    }

    /*
     * - And "Transfer-Encoding" should be "chunked"
     */

    @Test
    public void task3() {
        int region_id=1;
//* - Given Accept type  is Json
//* - When users sends request to /regions/1
        //opt1
        //Response response = given().accept(ContentType.JSON).when().get(url + "regions/1");
        //opt2
        Response response = given().accept(ContentType.JSON).when().get(url + "regions/"+region_id);


//* - Then status code is 200
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(200,response.statusCode());

//* - And Content - Type is application/json
        System.out.println("response.contentType() = " + response.contentType());
        assertEquals(ContentType.JSON.toString(), response.contentType());

// * - And response contains Europe
        JsonPath jsonPath =response.jsonPath();
        System.out.println("jsonPath.getString(\"region_name\") = " + jsonPath.getString("region_name"));
        assertEquals("Europe", jsonPath.getString("region_name"));

//* - And header should contains Date
        System.out.println("response.headers().hasHeaderWithName(\"Date\") = " + response.headers().hasHeaderWithName("Date"));
        assertTrue(response.headers().hasHeaderWithName("Date"));

//* - And "Transfer-Encoding" should be "chunked"
        System.out.println("response.header(\"Transfer-Encoding\") = " + response.header("Transfer-Encoding"));
        assertEquals("chunked", response.header("Transfer-Encoding"));

        System.out.println("======================================================");

        response.prettyPrint();

    }
}
