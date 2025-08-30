package com.mayankPaliwal.tests.restfulBooker.crud;

import com.mayankPaliwal.APIendpoints.APIConstants;
import com.mayankPaliwal.Pojos.responsePOJO.restfulBooker.BookingResponse;
import com.mayankPaliwal.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class TestCreateBooking extends BaseTest {

    @Test(groups = "reg", priority = 1)
    @Owner("Mayank Paliwal")
    @Description("TC#1 - Verify That the Booking created succesfully")
    public void test_CreateBookingPOST_Positive(){

        // setup will first and making the request - part -1

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString())
                .log().all().post();

        // step - 2 - Extraction of data
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());


        // step -3 - varification and vaidation of data via assertJ, testNG
        assertActions.verifyStatusCode(response,200);
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"mayank");

    }


    @Test(groups = "reg", priority = 2)
    @Owner("Mayank Paliwal")
    @Description("TC#2 - empty request")
    public void test_CreateBookingPOST_Negative(){

        // setup will first and making the request - part -1

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured
                .given(requestSpecification)
                .when()
                .body("{}")
                .log().all()
                .post();

      validatableResponse = response.then().log().all();
      validatableResponse.statusCode(500);

    }


    @Test(groups = "reg", priority = 3)
    @Owner("Mayank Paliwal")
    @Description("TC#3 - Verify That the Booking with chinese ")
    public void test_CreateBookingPOST_POSITIVE_CHINESE(){

        // setup will first and making the request - part -1

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured
                .given(requestSpecification)
                .when()
                .body(payloadManager.createPayloadBookingAsStringWrongBody())
                .log().all()
                .post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        // step -3 - varification and vaidation of data via assertJ, testNG
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());

    }


    @Test(groups = "reg", priority = 4)
    @Owner("Mayank Paliwal")
    @Description("TC#4 - Verify that the Booking can be Created, When Payload is RANDOM")    public void test_CreateBookingPOST_POSITIVE_FAKER_RANDOM_DATA(){

        // setup will first and making the request - part -1

        // Setup and Making a Request.
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingFakerJS()).log().all().post();
        System.out.println(response.asString());

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKeyNotNull(bookingResponse.getBooking().getFirstname());

    }


    @Test(groups = "reg", priority = 4)
    @Owner("Mayank Paliwal")
    @Description("TC#5 - no header")
    public void test_CreateBookingPOST_NEGATIVE_NO_HEADER(){

        // setup will first and making the request - part -1

        // Setup and Making a Request.
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        requestSpecification.contentType(ContentType.HTML);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingFakerJS()).log().all().post();
        System.out.println(response.asString());

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);

    }


    @Test(groups = "reg", priority = 4)
    @Owner("Mayank Paliwal")
    @Description("TC#6 - verify by using  wrong URL")
    public void test_CreateBookingPOST_NEGATIVE_NO_URL(){

        // setup will first and making the request - part -1

        // Setup and Making a Request.
        requestSpecification.basePath(APIConstants.APP_VWO_URL);
        requestSpecification.contentType(ContentType.HTML);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingFakerJS()).log().all().post();
        System.out.println(response.asString());

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);

    }



}
