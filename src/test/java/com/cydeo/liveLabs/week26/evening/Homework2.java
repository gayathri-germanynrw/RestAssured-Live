package com.cydeo.liveLabs.week26.evening;

import com.cydeo.utility.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Homework2 extends HrTestBase {
    /*
     * TASK 1 :
     * - Given accept type is Json
     * - Path param value- US
     * - When users sends request to /countries
     * - Then status code is 200
     * - And Content - Type is Json
     * - And country_id is US
     * - And Country_name is United States of America
     * - And Region_id is 2

     * TASK 2 :
     * - Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users sends request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25

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
    public void task1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", "US")
                .when().get("/countries/{id}");


        //     * - Then status code is 200
        Assertions.assertEquals(200,response.statusCode());

        //     * - And Content - Type is Json
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        //     * - And country_id is US
        JsonPath jp = response.jsonPath();
        String countryId = jp.getString("country_id");
        Assertions.assertEquals("US",countryId);

        //     * - And Country_name is United States of America
        Assertions.assertEquals("United States of America",jp.getString("country_name"));


        //     * - And Region_id is 2
        int id = jp.getInt("region_id");
        Assertions.assertEquals(2,id);

        // Bonus
        // Verify FIRST href information ends with US
        String firstHref = jp.getString("links[0].href");
        System.out.println("firstHref = " + firstHref);
        Assertions.assertTrue(firstHref.endsWith(countryId));


    }
}
