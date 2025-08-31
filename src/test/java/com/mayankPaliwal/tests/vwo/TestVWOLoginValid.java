package com.mayankPaliwal.tests.vwo;

import com.mayankPaliwal.APIendpoints.APIConstants;
import com.mayankPaliwal.Pojos.responsePOJO.appVWO.LoginResponse;
import com.mayankPaliwal.base.BaseTest;
import com.mayankPaliwal.modules.restfulBooker.PayloadManager;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestVWOLoginValid extends BaseTest {

    @Test
    public void test_VWO_Login_Positive(){

        requestSpecification.baseUri(APIConstants.APP_VWO_URL);
        response = RestAssured.given(requestSpecification).body(vwoPayloadManager.setLoginDataValid())
                .when().log().all().post();

        LoginResponse loginResponse = vwoPayloadManager.getLoginDataValid(response.asString());

        assertActions.verifyStatusCode(response,200);

        System.out.println(loginResponse.getAccountId());
        assertActions.verifyStringKeyNotNull(loginResponse.getAccountId());
        assertActions.verifyStringKey(loginResponse.getName(),"mayank p");
    }
}
