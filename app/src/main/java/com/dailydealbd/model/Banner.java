package com.dailydealbd.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.dailydealbd.utils.ConstantsResources;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = ConstantsResources.TABLE_HOMEPAGE_BANNER)
public class Banner {

@PrimaryKey
@NonNull
@ColumnInfo(name = "id")
@SerializedName("id")
private final Integer bannerId;

@ColumnInfo(name = "title")
@SerializedName("title")
private final String bannerTitle;

@ColumnInfo(name = "title_bd")
@SerializedName("title_bd")
private final String bannerTitleBd;

@ColumnInfo(name = "url")
@SerializedName("url")
private final String bannerUrl;

@ColumnInfo(name = "image")
@SerializedName("image")
private final String bannerImage;

@ColumnInfo(name = "short_code")
@SerializedName("short_code")
private final String bannerShortCode;



public Banner(@NonNull Integer bannerId, String bannerTitle, String bannerTitleBd, String bannerUrl, String bannerImage, String bannerShortCode) {

    this.bannerId = bannerId;
    this.bannerTitle = bannerTitle;
    this.bannerTitleBd = bannerTitleBd;
    this.bannerUrl = bannerUrl;
    this.bannerImage = bannerImage;
    this.bannerShortCode = bannerShortCode;
}



@NonNull
public Integer getBannerId() {

    return bannerId;
}



public String getBannerTitle() {

    return bannerTitle;
}



public String getBannerTitleBd() {

    return bannerTitleBd;
}



public String getBannerUrl() {

    return bannerUrl;
}



public String getBannerImage() {

    return bannerImage;
}



public String getBannerShortCode() {

    return bannerShortCode;
}




}
