package com.mayankPaliwal.Pojos.requestPOJO.appVWO;

public class VWOLoginRequest{
	private String remember;
	private String password;
	private String recaptchaResponseField;
	private String username;

	public void setRemember(String remember){
		this.remember = remember;
	}

	public String getRemember(){
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
