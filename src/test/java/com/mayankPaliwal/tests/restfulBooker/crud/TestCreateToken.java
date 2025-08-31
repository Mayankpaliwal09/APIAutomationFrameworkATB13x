package com.mayankPaliwal.tests.restfulBooker.crud;

import com.mayankPaliwal.APIendpoints.APIConstants;
import com.mayankPaliwal.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateToken  extends BaseTest {


    @Link("https://bugz.atlassian.net/browse/RBT-7")
    @Description("Verify that post request to the create token basically creates a 16-digit token. ")
    @Test(groups = "reg", priority = 1)
    @Owner("Mayank Paliwal")
    public void testVerify_TOKENPOST(){

        // Automatically have the base URL set and the header set.
        // This URL as well as the header of application JSON is automatically
        // set when you start using extends from BaseTest.

        requestSpecification.basePath(APIConstants.AUTH_URL);
//        response = RestAssured.given(requestSpecification).when().body(payloadManager.setAuthPayload()).post();
        response = RestAssured.given(requestSpecification).body(payloadManager.setAuthPayload()).when().log().all().post();


        // Extraction (JSON String Response to java object)
        String token = payloadManager.getTokenFromJson(response.asString());
        System.out.println(token);
//        validation
        assertActions.verifyStringKeyNotNull(token);
    }

    @Link("https://bugz.atlassian.net/browse/RBT-7")
    @Description("Verify that post request to the create token basically creates a 16-digit token. ")
    @Test(groups = "reg", priority = 1)
    @Owner("Mayank Paliwal")
    public void testVerify_TOKENPOST_NEGATIVE(){

        // Automatically have the base URL set and the header set.
        // This URL as well as the header of application JSON is automatically
        // set when you start using extends from BaseTest.

        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification).when()
                .body("{}").post();


        // Extraction (JSON String Response to java object)
        String invalid_reason = payloadManager.getInvalidToken(response.asString());
        System.out.println(invalid_reason);
//        validation
        assertActions.verifyStringKeyNotNull(invalid_reason);
        assertActions.verifyStringKey(invalid_reason,"Bad credentials");

    }

}
