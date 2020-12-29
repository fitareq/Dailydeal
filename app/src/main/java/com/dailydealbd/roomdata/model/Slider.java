package com.dailydealbd.roomdata.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.dailydealbd.utils.ConstantsResources;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = ConstantsResources.TABLE_HOMEPAGE_SLIDER)
public class Slider {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private final Integer sliderId;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private final String sliderTitle;

    @ColumnInfo(name = "title_bd")
    @SerializedName("title_bd")
    private final String sliderTitleBd;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    private final String sliderDescription;

    @ColumnInfo(name = "description_bd")
    @SerializedName("description_bd")
    private final String sliderDescriptionBd;

    @ColumnInfo(name = "button_text")
    @SerializedName("button_text")
    private final String sliderButtonText;

    @ColumnInfo(name = "button_text_bd")
    @SerializedName("button_text_bd")
    private final String sliderButtonTextBd;

    @ColumnInfo(name = "button_link")
    @SerializedName("button_link")
    private final String sliderButtonLink;

    @ColumnInfo(name = "background_image")
    @SerializedName("background_image")
    private final String sliderBackgroundImage;

    @ColumnInfo(name = "slider_image")
    @SerializedName("slider_image")
    private final String sliderImage;

    @ColumnInfo(name = "title_color")
    @SerializedName("title_color")
    private final String sliderTitleColor;

    @ColumnInfo(name = "description_color")
    @SerializedName("description_color")
    private final String sliderDescriptionColor;

    @ColumnInfo(name = "button_color")
    @SerializedName("button_color")
    private final String sliderButtonColor;

    @ColumnInfo(name = "image_reverse")
    @SerializedName("image_reverse")
    private final Integer sliderImageReverse;



    public Slider(@NonNull Integer sliderId, String sliderTitle,
                  String sliderTitleBd, String sliderDescription,
                  String sliderDescriptionBd, String sliderButtonText,
                  String sliderButtonTextBd, String sliderButtonLink,
                  String sliderBackgroundImage, String sliderImage,
                  String sliderTitleColor, String sliderDescriptionColor,
                  String sliderButtonColor, Integer sliderImageReverse) {

        this.sliderId = sliderId;
        this.sliderTitle = sliderTitle;
        this.sliderTitleBd = sliderTitleBd;
        this.sliderDescription = sliderDescription;
        this.sliderDescriptionBd = sliderDescriptionBd;
        this.sliderButtonText = sliderButtonText;
        this.sliderButtonTextBd = sliderButtonTextBd;
        this.sliderButtonLink = sliderButtonLink;
        this.sliderBackgroundImage = sliderBackgroundImage;
        this.sliderImage = sliderImage;
        this.sliderTitleColor = sliderTitleColor;
        this.sliderDescriptionColor = sliderDescriptionColor;
        this.sliderButtonColor = sliderButtonColor;
        this.sliderImageReverse = sliderImageReverse;

    }



    @NonNull
    public Integer getSliderId() {

        return sliderId;
    }


    public String getSliderTitle() {

        return sliderTitle;
    }


    public String getSliderTitleBd() {

        return sliderTitleBd;
    }


    public String getSliderDescription() {

        return sliderDescription;
    }


    public String getSliderDescriptionBd() {

        return sliderDescriptionBd;
    }


    public String getSliderButtonText() {

        return sliderButtonText;
    }


    public String getSliderButtonTextBd() {

        return sliderButtonTextBd;
    }


    public String getSliderButtonLink() {

        return sliderButtonLink;
    }


    public String getSliderBackgroundImage() {

        return sliderBackgroundImage;
    }


    public String getSliderImage() {

        return sliderImage;
    }


    public String getSliderTitleColor() {

        return sliderTitleColor;
    }


    public String getSliderDescriptionColor() {

        return sliderDescriptionColor;
    }


    public String getSliderButtonColor() {

        return sliderButtonColor;
    }


    public Integer getSliderImageReverse() {

        return sliderImageReverse;
    }



}

/*public class Slider
{
    private String Abc;
    private int Url;

    public Slider(String abc, int url) {
        Abc = abc;
        Url = url;
    }

    public String getAbc() {
        return Abc;
    }

    public void setAbc(String abc) {
        Abc = abc;
    }

    public int getUrl() {
        return Url;
    }

    public void setUrl(int url) {
        Url = url;
    }
}*/
