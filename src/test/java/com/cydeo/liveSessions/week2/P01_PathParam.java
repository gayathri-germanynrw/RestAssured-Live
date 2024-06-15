package com.cydeo.liveSessions.week2;

import com.cydeo.utility.FakeStoreTestBase;
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
     * - Name is "My Products"
     * - Category name is "Shoes"
     */
    @Test
    public void responsePath() {

    }
}
