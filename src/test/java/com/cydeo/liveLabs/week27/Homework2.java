package com.cydeo.liveLabs.week27;

import com.cydeo.utility.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Homework2 extends HrTestBase {
    /**
     * TASK 1 :
     * - Given accept type is Json
     * - Path param value- US
     * - When users sends request to /countries
     * - Then status code is 200
     * - And Content - Type is Json
     * - And country_id is US
     * - And Country_name is United States of America
     * - And Region_id is 2
     */

    @Test
    public void task1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("country_id", "US").
                when().get("/countries/{country_id}");

        //     * - Then status code is 200
        Assertions.assertEquals(200,response.statusCode());

        //     * - And Content - Type is Json
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        //     * - And country_id is US
        Assertions.assertEquals("US",response.path("country_id"));

        //     * - And Country_name is United States of America
        Assertions.assertEquals("United States of America",response.path("country_name"));

        //     * - And Region_id is 2
        Assertions.assertEquals(2,(int)response.path("region_id"));



    }


    /**
     * TASK 2 :
     * - Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users sends request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25
     */
    @Test
    public void task2() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");

        //     * - Then status code is 200
        Assertions.assertEquals(200,response.statusCode());

        //     * - And Content - Type is Json
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        //     * - And all job_ids start with 'SA'
        JsonPath jp = response.jsonPath();
        List<String> allJobIDs = jp.getList("items.job_id");
        System.out.println("allJobIDs = " + allJobIDs);

        // STREAM --> CHALLENGE

        for (String eachJobID : allJobIDs) {
            Assertions.assertTrue(eachJobID.startsWith("SA"));
        }

        //     * - And all department_ids are 80
        List<Integer> allDepIDs = jp.getList("items.department_id");
        System.out.println("allDepIDs = " + allDepIDs);

        // STREAM --> CHALLENGE
        for (Integer eachDepID : allDepIDs) {
            Assertions.assertEquals(80,eachDepID);
        }

        //     * - Count is 25
        int count = jp.getInt("count");
        Assertions.assertEquals(25,count);


    }

    /**
     * TASK 3 :
     * - Given accept type is Json
     * - Query param value q={“region_id":3}
     * - When users sends request to /countries
     * - Then status code is 200
     * - And all regions_id is 3
     * - And count is 6
     * - And hasMore is false
     * - And Country_name are;
     * Australia,China,India,Japan,Malaysia,Singapore
     */
    @Test
    public void task3() {

        // assume you are getting data from another source (Excel file , UI , DB etc )
        String [] countries={"Australia","China","India","Japan","Malaysia","Singapore"};

        given().accept(ContentType.JSON)
                .queryParam("q","{\"region_id\":3}").
                when().get("/countries")
                .then() // will get Response for verification
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("items.region_id", everyItem(is(3)))
                .body("count",is(6))
                .body("hasMore",is(false))
                .body("items.country_name",containsInRelativeOrder(countries));

    }

}