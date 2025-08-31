package com.mayankPaliwal.Pojos.requestPOJO.appVWO;

import com.mayankPaliwal.Pojos.requestPOJO.restfulBooker.Booking;

public class VWOLoginRequest{
	private boolean remember;
	private String password;
	private String recaptchaResponseField;
	private String username;

	public void setRemember(boolean remember){
		this.remember = remember;
	}

	public boolean getRemember(){
		return remember;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setRecaptchaResponseField(String recaptchaResponseField){
		this.recaptchaResponseField = recaptchaResponseField;
	}

	public String getRecaptchaResponseField(){
		return recaptchaResponseField;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}
