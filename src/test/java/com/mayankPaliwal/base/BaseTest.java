package com.mayankPaliwal.base;

import com.mayankPaliwal.APIendpoints.APIConstants;
import com.mayankPaliwal.asserts.AssertActions;
import com.mayankPaliwal.modules.restfulBooker.PayloadManager;
import com.mayankPaliwal.modules.VWO.VWOPayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    // this file contains common to all test cases
    // CommontoALL Testcases
    //  BASE URL , CONTENT/TYPE - JSON - COMMON TO ALL

    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;

    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public VWOPayloadManager vwoPayloadManager;
    public JsonPath jsonPath;


    @BeforeTest
    public void setup(){
        System.out.println("Starting of the test");
        payloadManager = new PayloadManager();
        vwoPayloadManager = new VWOPayloadManager();
        assertActions = new AssertActions();

//        requestSpecification = RestAssured.given();
//        requestSpecification.baseUri(APIConstants.BASE_URL);
//        requestSpecification.contentType(ContentType.JSON).log().all();


        // alternate way to do above thing
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type","application/json")
                .build().log().all();

    }

    @AfterTest
    public void tearDown(){
        System.out.println("Finished the test");
    }


    public String getToken(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);

        //setting payload

        String payload = payloadManager.setAuthPayload();

        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();
        String token = payloadManager.getTokenFromJson(response.asString());
        return token;
    }



}
