package com.dailydealbd.roomdata.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dailydealbd.roomdata.model.Slider;
import com.dailydealbd.utils.ConstantsResources;

import java.util.List;

@Dao
public interface SliderDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
void insertSliderList(List<Slider> sliders);
@Query("DELETE FROM "+ ConstantsResources.TABLE_HOMEPAGE_SLIDER)
void deleteAllSlider();
@Query("SELECT * FROM "+ConstantsResources.TABLE_HOMEPAGE_SLIDER)
LiveData<List<Slider>> getAllSlider();
}
