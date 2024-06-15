package com.cydeo.liveSessions.week2;

import com.cydeo.utility.FakeStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_PathParam extends FakeStoreTestBase {

    /**
     * 1- Given accept type is Json
     * 2- Path Parameters value is
     * - id —> 60
     * 3- When user sends GET request to api/v1/products/{id}
     * 4- Verify followings
     * - Status code should be 200
     * - Content Type is application/json; charset=utf-8
     * - Print response
     * - id is 60
     * - Name is "My Product"
     * - Category name is "Organic"
     */
    @Test
    public void responsePath() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 60)
                .when().get("/products/{id}");

        // Print response
        response.prettyPrint();

        //     * - Status code should be 200
        Assertions.assertEquals(200,response.statusCode());

        //     * - Content Type is application/json; charset=utf-8
        Assertions.assertEquals("application/json; charset=utf-8",response.contentType());

        //     * - id is 60
        Assertions.assertEquals(60,(int)response.path("id"));

        //     * - Title is "My Product"
        Assertions.assertEquals("My Product",response.path("title"));

        //     * - Category name is "Organic"
        Assertions.assertEquals("Organic",response.path("category.name"));



        // HOW CAN I MAKE SURE LINK IS WORKING ? CATEGORY.IMAGE
        String imageURL = response.path("category.image");
        System.out.println("imageURL = " + imageURL);

        /*
          Response res= RestAssured.get(imageURL);
          int statusCode=res.statusCode();

         */

        int statusCode = RestAssured.get(imageURL).statusCode();
        Assertions.assertEquals(200,statusCode);


        // HOW TO CHECK BROKEN LINKS IN UI ? --> Link is not working
        // Locate all
        // a --> getAttribute("href")
        // img --> getAttribute("src")
        //
        // Create a loop --> Send
        // int statusCode = RestAssured.get(imageURL).statusCode();
        //    if(statusCode!=200){
        //    }


    }


    @Test
    public void jsonPath() {


        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 60)
                .when().get("/products/{id}");

        // Print response
        response.prettyPrint();

        //     * - Status code should be 200
        Assertions.assertEquals(200,response.statusCode());

        //     * - Content Type is application/json; charset=utf-8
        Assertions.assertEquals("application/json; charset=utf-8",response.contentType());


        // JSON PATH OBJECT
        JsonPath jp = response.jsonPath();

        //     * - id is 60
        Assertions.assertEquals(60,jp.getInt("id"));

        //     * - Title is "My Product"
        Assertions.assertEquals("My Product",jp.getString("title"));

        //     * - Category name is "Organic"
        Assertions.assertEquals("Organic",jp.getString("category.name"));


    }
}
