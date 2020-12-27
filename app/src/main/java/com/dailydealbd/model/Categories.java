package com.dailydealbd.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.dailydealbd.utils.ConstantsResources;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = ConstantsResources.TABLE_CATEGORIES)
public class Categories {

@PrimaryKey
@NonNull
@ColumnInfo(name = "id")
@SerializedName("id")
private final Integer categoryId;

@ColumnInfo(name = "name")
@SerializedName("name")
private final String categoryName;

@ColumnInfo(name = "name_bd")
@SerializedName("name_bd")
private final String categoryNameBd;

@ColumnInfo(name = "description")
@SerializedName("description")
private final String categoryDescription;

@ColumnInfo(name = "image")
@SerializedName("image")
private final String categoryImage;

@ColumnInfo(name = "thumbnail")
@SerializedName("thumbnail")
private final String categoryThumbnail;

@ColumnInfo(name = "parent_id")
@SerializedName("parent_id")
private final Integer categoryParentId;

@ColumnInfo(name = "slug")
@SerializedName("slug")
private final String categorySlug;



public Categories(@NonNull Integer categoryId, String categoryName, String categoryNameBd, String categoryDescription, String categoryImage, String categoryThumbnail, Integer categoryParentId, String categorySlug) {

    this.categoryId = categoryId;
    this.categoryName = categoryName;
    this.categoryNameBd = categoryNameBd;
    this.categoryDescription = categoryDescription;
    this.categoryImage = categoryImage;
    this.categoryThumbnail = categoryThumbnail;
    this.categoryParentId = categoryParentId;
    this.categorySlug = categorySlug;
}



@NonNull
public Integer getCategoryId() {

    return categoryId;
}



public String getCategoryName() {

    return categoryName;
}



public String getCategoryNameBd() {

    return categoryNameBd;
}



public String getCategoryDescription() {

    return categoryDescription;
}



public String getCategoryImage() {

    return categoryImage;
}



public String getCategoryThumbnail() {

    return categoryThumbnail;
}



public Integer getCategoryParentId() {

    return categoryParentId;
}



public String getCategorySlug() {

    return categorySlug;
}




}
