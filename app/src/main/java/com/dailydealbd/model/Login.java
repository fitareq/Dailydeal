package com.dailydealbd.model;

import com.google.gson.annotations.SerializedName;


public class Login {

@SerializedName("phone_number")
private final String userPhone;
@SerializedName("password")
private final String loginToken;



public Login(String userPhone, String loginToken) {

    this.userPhone = userPhone;
    this.loginToken = loginToken;
}




}
