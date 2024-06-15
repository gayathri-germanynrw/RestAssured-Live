package com.cydeo.liveSessions.week2;

import com.cydeo.utility.FakeStoreTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P02_QueryParam extends FakeStoreTestBase {

    /**
     * 1- Given accept type is Json
     * 2- Query Parameters value is
     * - limit —> 100
     * - offset —> 0
     * 3- When user sends GET request to /products
     * 4- Verify followings
     * - Status code should be 200
     * - Content Type is application/json; charset=utf-8
     * - Each product has id
     * - Each product has category id
     * - Get all product names
     * - Get product ids
     * - Get all category names
     */

    @Test
    public void queryParam1() {

        JsonPath jp = given().accept(ContentType.JSON)
                .queryParam("limit", 10)
                .queryParam("offset", 0)
                .when().get("/products")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON.withCharset("utf-8"))
                .body("id", everyItem(notNullValue()))
                .body("category.id", everyItem(notNullValue()))
                .extract().jsonPath();
        //     * - Get all product title
        List<String> allTitles = jp.getList("title");
        System.out.println("allTitles = " + allTitles);

        //     * - Get first product title
        // String firstTitle = jp.getString("[0].title");
        String firstTitle = jp.getString("title[0]");
        System.out.println("firstTitle = " + firstTitle);

        //     * - Get last product title
        String lastTitle = jp.getString("title[-1]");
        System.out.println("lastTitle = " + lastTitle);

        // Get me last one
        int index = allTitles.size()-1;
        String manualWay = jp.getString("title[" + index + "]");
        System.out.println("manualWay = " + manualWay);


    }
}