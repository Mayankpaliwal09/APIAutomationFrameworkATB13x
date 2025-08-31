package com.mayankPaliwal.tests.restfulBooker.e2e;

import com.mayankPaliwal.APIendpoints.APIConstants;
import com.mayankPaliwal.Pojos.requestPOJO.restfulBooker.Booking;
import com.mayankPaliwal.Pojos.responsePOJO.restfulBooker.BookingResponse;
import com.mayankPaliwal.base.BaseTest;
import com.mayankPaliwal.modules.restfulBooker.PayloadManager;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestIntegrationFlow1 extends BaseTest {
    // TestE2EFlow_01

    //  Test E2E Scenario 1

    //  1. Create a Booking -> bookingID

    // 2. Create Token -> token

    // 3. Verify that the Create Booking is working - GET Request to bookingID

    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

    // 5. Delete the Booking - Need to get the token, bookingID from above request

    @Test(groups = "qa", priority = 1)
    @Owner("mayank")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification).body(payloadManager.createPayloadBookingAsString())
                .with().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"mayank");
//        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        Integer bookingid = bookingResponse.getBookingid();
        iTestContext.setAttribute("bookingid",bookingid);

    }

    @Test(groups = "qa", priority = 2 , dependsOnMethods = {"testCreateBooking"})
    @Owner("mayank")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext){

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        System.out.println(bookingid );
        String basePathGet = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;

        requestSpecification.basePath(basePathGet);
        response=RestAssured.given(requestSpecification).when().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertActions.verifyStringKeyNotNull(booking.getFirstname());

    }

    @Test(groups = "qa", priority = 3 , dependsOnMethods = {"testVerifyBookingId"})
    @Owner("mayank")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token",token);

        String basePathPutPatch = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        System.out.println(basePathPutPatch);

         requestSpecification.basePath(basePathPutPatch);
         response=RestAssured.given(requestSpecification)
                 .cookie("token",token)
                 .when().body(payloadManager.createPayloadBookingAsString()).patch();

         validatableResponse = response.then().log().all();
         validatableResponse.statusCode(200);

         Booking booking = payloadManager.getResponseFromJSON(response.asString());

         assertActions.verifyStringKeyNotNull(booking.getFirstname());
         assertActions.verifyStringKey(booking.getFirstname(),"mayank");

    }

    @Test(groups = "qa", priority = 4)
    @Owner("mayank")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token",token);

        String basePathDelete = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        System.out.println(basePathDelete);

        requestSpecification.basePath(basePathDelete).cookie("token",token);
        validatableResponse = RestAssured.given().spec(requestSpecification).when().delete().then().log().all();
        validatableResponse.statusCode(201);




    }


}
