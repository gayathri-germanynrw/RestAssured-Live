package com.cydeo.liveSessions.week1;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SimpleGETRequest extends HrTestBase {

    /**
     * 1. Send request to HR ORDS url and save the response
     * 2. GET /regions
     * 3. Store the response in Response Object that comes from GET Request
     * 4. Print out followings
     *     - Headers
     *     - Content-Type
     *     - Status Code
     *     - Response
     *     - Date
     *     - Verify response body has "Europe"
     *     - Verify response has Date
     */



    @Test
    public void simpleGET() {

        Response response = given().log().uri()
                .accept(ContentType.JSON) // API SEND ME RESPONSE IN JSON FORMAT
                .when().get("/regions");

        // Print all data from RESPONSE
        // response.prettyPrint();

        //     *     - Headers
        System.out.println("------- HEADERS -------");
        System.out.println("response.getHeaders() = " + response.getHeaders());
        System.out.println("response.headers() = " + response.headers());

        //     *     - Content-Type
        System.out.println("------- CONTENT TYPE  -------");
        System.out.println("response.getContentType() = " + response.getContentType());
        System.out.println("response.contentType() = " + response.contentType());

        //     *     - Status Code
        System.out.println("------- STATUS CODE  -------");
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        assertEquals(200,response.statusCode());
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        //     *     - Date
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        //     *     - Verify response body has "Europe"
        boolean isEurope = response.asString().contains("Europe");
        System.out.println("isEurope = " + isEurope);
        assertTrue(isEurope);

        //     *     - Verify response has Date
        boolean isDateExist = response.headers().hasHeaderWithName("Date");
        System.out.println("isDateExist = " + isDateExist);
        assertTrue(isDateExist);


    }
}
