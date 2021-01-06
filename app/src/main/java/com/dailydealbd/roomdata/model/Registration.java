package com.dailydealbd.roomdata.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.dailydealbd.utils.ConstantsResources;
import com.google.gson.annotations.SerializedName;


public class Registration {


        @SerializedName("name")
        private final String userName;

        @SerializedName("email")
        private final String userEmail;

        @SerializedName("password")
        private final String userPassword;

        @SerializedName("phone_number")
        private final String phoneNumber;



        public Registration(String userName, String userEmail, String userPassword, String phoneNumber) {

            this.userName = userName;
            this.userEmail = userEmail;
            this.userPassword = userPassword;
            this.phoneNumber = phoneNumber;
        }



    public String getUserName() {

        return userName;
    }



    public String getUserEmail() {

        return userEmail;
    }



    public String getUserPassword() {

        return userPassword;
    }



    public String getPhoneNumber() {

        return phoneNumber;
    }




}

