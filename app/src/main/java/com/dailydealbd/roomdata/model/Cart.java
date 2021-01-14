package com.dailydealbd.roomdata.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.dailydealbd.utils.ConstantsResources;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = ConstantsResources.TABLE_CART_USER)
public class Cart {


@ColumnInfo(name = "user_id")
@SerializedName("user_id")
private final Integer userId;

@NonNull
@PrimaryKey
@ColumnInfo(name = "product_id")
@SerializedName("product_id")
private final Integer productId;

@ColumnInfo(name = "qtybutton")
@SerializedName("qtybutton")
private final Integer productQuantity;

@ColumnInfo(name = "price")
@SerializedName("price")
private final String productPrice;
//attributes_option
@ColumnInfo(name = "attributes_option")
@SerializedName("attributes_option")
private final String attributesOption;
//product_image
@ColumnInfo(name = "product_image")
@SerializedName("product_image")
private final String productImage;
//product_title
@ColumnInfo(name = "product_title")
@SerializedName("product_title")
private final String productTitle;

@ColumnInfo(name = "total_price")
private final String productTotalPrice;



public Cart(Integer userId, @NonNull Integer productId, Integer productQuantity,
            String productPrice, String attributesOption, String productImage, String productTitle, String productTotalPrice) {

    this.userId = userId;
    this.productId = productId;
    this.productQuantity = productQuantity;
    this.productPrice = productPrice;
    this.attributesOption = attributesOption;
    this.productImage = productImage;
    this.productTitle = productTitle;
    this.productTotalPrice = productTotalPrice;
}



public Integer getUserId() {

    return userId;
}



@NonNull
public Integer getProductId() {

    return productId;
}



public Integer getProductQuantity() {

    return productQuantity;
}



public String getProductPrice() {

    return productPrice;
}



public String getAttributesOption() {

    return attributesOption;
}



public String getProductImage() {

    return productImage;
}



public String getProductTitle() {

    return productTitle;
}

public String getProductTotalPrice()
{
    return productTotalPrice;
}



}
