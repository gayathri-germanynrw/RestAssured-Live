package com.cydeo.liveLabs.week28.evening;

import com.cydeo.pojo.MRDataConstructor;
import com.cydeo.pojo.MRDataDriver;
import com.cydeo.utility.FormulaTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Homework4 extends FormulaTestBase {
     /*
    TASK 1

    - Given accept type is json
    - And path param driverId is alonso
    - When user send GET request /drivers/{driverId}.json
    - Then verify status code is 200
    - And content type is application/json; charset=utf-8
    - And total is 1
    - And givenName is Fernando
    - And familyName is Alonso
    - And nationality is Spanish
     */

    @Test
    public void task1() {
        //    - Given accept type is json
        //    - And path param driverId is alonso
        //    - When user send GET request /drivers/{driverId}.json
        //    - Then verify status code is 200
        //    - And content type is application/json; charset=utf-8
        JsonPath jsonPath = given().pathParam("driverId", "alonso").
                when().get("/drivers/{driverId}.json").prettyPeek().
                then().statusCode(200).contentType("application/json; charset=utf-8")
                .extract().jsonPath();
        MRDataDriver mrData = jsonPath.getObject("MRData", MRDataDriver.class);
        System.out.println("mrData = " + mrData);

        //    - And total is 1
        String total = jsonPath.getString("MRData.total");
        System.out.println("total = " + total);
        assertEquals("1",total);

        //    - And givenName is Fernando
        String givenName = jsonPath.getString("MRData.DriverTable.Drivers[0].givenName");
        System.out.println("givenName = " + givenName);
        assertEquals("Fernando",givenName);

        //    - And familyName is Alonso
        String familyName = jsonPath.getString("MRData.DriverTable.Drivers[0].familyName");
        System.out.println("familyName = " + familyName);
        assertEquals("Alonso",familyName);

        //    - And nationality is Spanish
        String nationality = jsonPath.getString("MRData.DriverTable.Drivers[0].nationality");
        System.out.println("nationality = " + nationality);
        assertEquals("Spanish",nationality);


    }

     /*
    TASK 2

    - Given accept type is json
    - When user send request /constructorStandings/1/constructors.json
    - Then verify status code is 200
    - And content type is application/json; charset=utf-8
    - And total is 17
    - And limit is 30
    - And each constructor has constructorId
    - And each constructor has name
    - And size of constructor is 17
    - And constructor IDs has “benetton”, “mercedes”,“team_lotus”
     */
    @Test
    public void test2(){
        JsonPath jsonPath = given().pathParam("driverId", "alonso").
                when().get("constructorStandings/1/constructors.json").prettyPeek().
                then().statusCode(200).contentType("application/json; charset=utf-8")
                .extract().jsonPath();
        //Get json data in MRData POJO

        MRDataConstructor mrDataConstructor = jsonPath.getObject("MRData", MRDataConstructor.class);

        // -And total is 17

        assertEquals("17",mrDataConstructor.getTotal());

        // - And limit is 30
        assertEquals("30",mrDataConstructor.getLimit());

        // -- And each constructor has constructorId
       // assertEquals(mrDataConstructor.getConstructorTable().get);
    }

}
