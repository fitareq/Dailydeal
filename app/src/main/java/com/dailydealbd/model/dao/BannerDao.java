package com.dailydealbd.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dailydealbd.model.Banner;
import com.dailydealbd.utils.ConstantsResources;

import java.util.List;

@Dao
public interface BannerDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
void insertBannerList(List<Banner> banners);
@Query("DELETE FROM "+ ConstantsResources.TABLE_HOMEPAGE_BANNER)
void deleteAllBanner();
@Query("SELECT * FROM "+ConstantsResources.TABLE_HOMEPAGE_BANNER)
List<Banner> getAllBanner();

}
