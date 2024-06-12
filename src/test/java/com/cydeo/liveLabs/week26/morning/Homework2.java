package com.cydeo.liveLabs.week26.morning;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Homework2 extends HrTestBase {
    @Test
    public void task1() {
// * - Given accept type is Json
// * - Path param value- US
// * - When users sends request to /countries

        Response response = given().accept(ContentType.JSON)
                                    .pathParam("country_id", "US")
                                    .when().get("/countries/{country_id}");
        //response.prettyPrint();

//* - Then status code is 200

        assertEquals(HttpStatus.SC_OK,response.statusCode());

//* - And Content - Type is Json

        assertEquals(ContentType.JSON.toString(), response.contentType());

//* - And country_id is US
        JsonPath jsonPath= response.jsonPath();
        assertEquals("US",jsonPath.getString("country_id"));

//* - And Country_name is United States of America
        assertEquals("United States of America",jsonPath.getString("country_name"));

//* - And Region_id is 2
        assertEquals(2,jsonPath.getInt("region_id"));
    }
}
