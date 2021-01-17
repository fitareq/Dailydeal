package com.dailydealbd.roomdata.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.dailydealbd.utils.ConstantsResources;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = ConstantsResources.TABLE_WISHLIST)
public class WishList {

    @ColumnInfo(name = "uid")
    @SerializedName("uid")
    private Integer userId;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "pid")
    @SerializedName("pid")
    private Integer productId;

    @ColumnInfo(name = "title")
    private String productTitle;

    @ColumnInfo(name = "image")
    private String productImage;

    @ColumnInfo(name = "slug")
    private String productSlug;



    @Ignore
    public WishList(int userId, int productId) {

        this.userId = userId;
        this.productId = productId;
    }



    public WishList(int userId, int productId, String productTitle, String productImage, String productSlug) {

        this.userId = userId;
        this.productId = productId;
        this.productTitle = productTitle;
        this.productImage = productImage;
        this.productSlug = productSlug;
    }



    public Integer getUserId() {

        return userId;
    }



    public Integer getProductId() {

        return productId;
    }



    public String getProductTitle() {

        return productTitle;
    }



    public String getProductImage() {

        return productImage;
    }



    public String getProductSlug() {

        return productSlug;
    }




}
