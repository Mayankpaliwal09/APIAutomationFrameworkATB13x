package com.mayankPaliwal.modules.VWO;

import com.google.gson.Gson;
import com.mayankPaliwal.Pojos.requestPOJO.appVWO.VWOLoginRequest;
import com.mayankPaliwal.Pojos.responsePOJO.appVWO.reponsePOJO.InvalidLoginResponse;
import com.mayankPaliwal.Pojos.responsePOJO.appVWO.LoginResponse;

public class VWOPayloadManager {

    Gson gson;

    // SERILIZATION -- JAVA OBJECT to JSON
    public String setLoginDataValid(){

        VWOLoginRequest loginRequest = new VWOLoginRequest();
        loginRequest.setUsername("20010041034@gateway.edu.in");
        loginRequest.setPassword("Paliwal@2609");
        loginRequest.setRemember(false);
        loginRequest.setRecaptchaResponseField("");

        gson = new Gson();
        String jsonPayloadString = gson.toJson(loginRequest);
        System.out.println("Payload Login to the -> " + jsonPayloadString);
        return jsonPayloadString;


    }


    public String setLoginDataInValid(){
        VWOLoginRequest loginRequest = new VWOLoginRequest();
        loginRequest.setUsername("abc.com");
        loginRequest.setPassword("123");
        loginRequest.setRemember(false);
        loginRequest.setRecaptchaResponseField("");

        gson = new Gson();
        String jsonPayloadString = gson.toJson(loginRequest);
        System.out.println("Payload Login to the -> " + jsonPayloadString);
        return jsonPayloadString;

    }


    // DESERILIZATION --JSON STRING TO JAVA OBJECT

    public LoginResponse getLoginDataValid(String loginResponse) {
        gson = new Gson();

        LoginResponse loginResponse1 = gson.fromJson(loginResponse,LoginResponse.class);
        return loginResponse1;
    }


    public String getLoginDataInvalid(String loginResponseEx){
        gson = new Gson();
        InvalidLoginResponse loginResponse = gson.fromJson(loginResponseEx, InvalidLoginResponse.class);
        return  loginResponse.getMessage();

    }
}
