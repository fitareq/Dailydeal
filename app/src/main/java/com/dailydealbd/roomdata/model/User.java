package com.dailydealbd.roomdata.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.dailydealbd.utils.ConstantsResources;


@Entity(tableName = ConstantsResources.TABLE_LOGGED_IN_USER)
public class User {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final Integer userId;

    @ColumnInfo(name = "name")
    private final String userName;

    @ColumnInfo(name = "email")
    private final String userEmail;

    @ColumnInfo(name = "phone_number")
    private final String phoneNumber;

    @ColumnInfo(name = "otp")
    private final String otp;

    @ColumnInfo(name = "address")
    private final String address;

    @ColumnInfo(name = "delivery_phone")
    private final String delivery_phone;

    @ColumnInfo(name = "delivery_address")
    private final String delivery_address;

    @ColumnInfo(name = "city_id")
    private final Integer city_id;

    @ColumnInfo(name = "gender")
    private final Integer gender;

    @ColumnInfo(name = "status")
    private final Integer status;

    @ColumnInfo(name = "email_verified_at")
    private final String email_verified_at;



    public User(@NonNull Integer userId, String userName, String userEmail, String phoneNumber, String otp, String address, String delivery_phone, String delivery_address, Integer city_id, Integer gender, Integer status, String email_verified_at) {

        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.phoneNumber = phoneNumber;
        this.otp = otp;
        this.address = address;
        this.delivery_phone = delivery_phone;
        this.delivery_address = delivery_address;
        this.city_id = city_id;
        this.gender = gender;
        this.status = status;
        this.email_verified_at = email_verified_at;
    }



    @NonNull
    public Integer getUserId() {

        return userId;
    }



    public String getUserName() {

        return userName;
    }



    public String getUserEmail() {

        return userEmail;
    }



    public String getPhoneNumber() {

        return phoneNumber;
    }



    public String getOtp() {

        return otp;
    }



    public String getAddress() {

        return address;
    }



    public String getDelivery_phone() {

        return delivery_phone;
    }



    public String getDelivery_address() {

        return delivery_address;
    }



    public Integer getCity_id() {

        return city_id;
    }



    public Integer getGender() {

        return gender;
    }



    public Integer getStatus() {

        return status;
    }



    public String getEmail_verified_at() {

        return email_verified_at;
    }




}
