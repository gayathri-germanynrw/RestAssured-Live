package com.cydeo.liveSessions.week2;

import com.cydeo.utility.FakeStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class P03_Deserialization extends FakeStoreTestBase {


    /**
     * Send request to FakeStoreTestBase url and save the response
     * Accept application/json
     * Path Param id is 2
     * Query Param limit 10
     * Query Param offset 0
     * GET /categories/{id}/products
     * Store the response in Response Object that comes from get Request
     * Print out followings
     * - Print response
     * - Content-Type is application/json; charset=utf-8
     * - Status Code is 200
     * - Retrieve data as JAVA Collections and print out following information
     * <p>
     * System.out.println("====== GET ALL PRODUCTS ======");
     * System.out.println("====== GET SECOND PRODUCTS ======");
     * System.out.println("====== GET SECOND PRODUCTS PRICE ======");
     * System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
     * System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
     * System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
     * System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
     */

    @Test
    public void task1() {

        JsonPath jp = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 2)
                .queryParam("limit", 10)
                .queryParam("offset", 0)
                .when().get("/categories/{id}/products")
                .then().statusCode(200)
                .contentType(ContentType.JSON.withCharset("utf-8"))
                .extract().jsonPath();

        System.out.println("====== GET FIRST PRODUCTS ======");
        Map<String, Object> firstProduct = jp.getMap("[0]");
        System.out.println("firstProduct = " + firstProduct);

        System.out.println("====== GET FIRST IMAGES ======");
        List<String> firstProductImages = jp.getList("[0].images");
        System.out.println("firstProductImages = " + firstProductImages);

        System.out.println("====== GET FIRST PRODUCT CATEGORY ======");
        Map<String, Object> firstProductCategory = jp.getMap("[0].category");
        System.out.println("firstProductCategory = " + firstProductCategory);


        System.out.println("====== GET ALL PRODUCTS ======");
        List<Map<String, Object>> allProducts = jp.getList("");
         /*
         for (Map<String, Object> eachProduct : allProducts) {
                System.out.println("eachProduct = " + eachProduct);
         }
          */




        System.out.println("====== GET SECOND PRODUCTS ======");
        Map<String, Object> secondProduct = allProducts.get(1);
        System.out.println("secondProduct = " + secondProduct);

        System.out.println("====== GET SECOND PRODUCTS PRICE ======");
        int secondProductPrice = (int)secondProduct.get("price");
        System.out.println("secondProductPrice = " + secondProductPrice);

        System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
        List<String> secondProductImages = (List<String> )secondProduct.get("images");
        System.out.println("secondProductImages = " + secondProductImages);

        System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
        String secondProductFirstImage = secondProductImages.get(0);
        System.out.println(secondProductFirstImage);

        System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
        Map<String,Object> secondCategory = (Map<String, Object>) secondProduct.get("category");
        System.out.println("secondCategory = " + secondCategory);

        System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
        String secondProductCategory = (String) secondCategory.get("name");
        System.out.println("secondProductCategory = " + secondProductCategory);
    }
}