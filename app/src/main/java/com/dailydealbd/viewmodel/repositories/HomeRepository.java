package com.dailydealbd.viewmodel.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Banner;
import com.dailydealbd.roomdata.model.Categories;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.Slider;
import com.dailydealbd.roomdata.model.dao.BannerDao;
import com.dailydealbd.roomdata.model.dao.CategoriesDao;
import com.dailydealbd.roomdata.model.dao.ProductsDao;
import com.dailydealbd.roomdata.model.dao.SliderDao;
import com.dailydealbd.network.APIInstance;
import com.dailydealbd.network.DailyDealApi;
import com.dailydealbd.roomdata.LocalDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeRepository {


    private final DailyDealApi api;
    private LiveData<List<Slider>> sliderList;
    private LiveData<List<Products>> productsList;
    private LiveData<List<Categories>> categoriesList;
    private LiveData<List<Banner>> bannerList;

    private LocalDatabase db;
    private SliderDao sliderDao;
    private ProductsDao productsDao;
    private CategoriesDao categoriesDao;
    private BannerDao bannerDao;



    public HomeRepository(Application application) {

        api = APIInstance.retroInstance().create(DailyDealApi.class);
        db = LocalDatabase.getINSTANCE(application);
        sliderDao = db.sliderDao();
        productsDao = db.productsDao();
        categoriesDao = db.categoriesDao();
        bannerDao = db.bannerDao();
        bannerList = bannerDao.getAllBanner();
        productsList = productsDao.getAllProducts();
        sliderList = sliderDao.getAllSlider();
        categoriesList = categoriesDao.getAllCategory();
    }



    public void fetchSliderDataFromRemote() {

        Call<List<Slider>> call = api.getAllSlider();
        call.enqueue(new Callback<List<Slider>>() {
            @Override
            public void onResponse(Call<List<Slider>> call, Response<List<Slider>> response) {

                if (response.isSuccessful()) {
                    insertSliderList(response.body());
                }
            }



            @Override
            public void onFailure(Call<List<Slider>> call, Throwable t) {

            }
        });
    }


    public void fetchBannerDataFromRemote()
    {
        Call<List<Banner>> call = api.getAllBanner();
        call.enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                if (response.isSuccessful())
                {
                    insertBannerList(response.body());
                }
            }



            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {

            }
        });
    }
    public void fetchProductsDataFromRemote() {

        Call<List<Products>> call = api.getAllProducts();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {

                if (response.isSuccessful()) {
                    insertProductsList(response.body());
                }
            }



            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });
    }



    public void fetchCategoriesDataFromRemote() {

        Call<List<Categories>> call = api.getAllCategories();
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {

                if (response.isSuccessful()) {
                    insertCategoriesList(response.body());
                }
            }



            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {

            }
        });
    }



    public LiveData<List<Slider>> getSliderList() {
        return sliderList;
    }



    public LiveData<List<Products>> getProductsList() {
        return productsList;
    }



    public LiveData<List<Categories>> getCategoriesList() {
        return categoriesList;
    }

    public LiveData<List<Banner>> getBannerList(){return bannerList;}


    private void insertBannerList(List<Banner> banners)
    {
        LocalDatabase.databaseWriteExecutors.execute(new Runnable() {
            @Override
            public void run() {
                bannerDao.insertBannerList(banners);
            }
        });
    }
    private void insertCategoriesList(List<Categories> categories)
    {
        LocalDatabase.databaseWriteExecutors.execute(new Runnable() {
            @Override
            public void run() {
                categoriesDao.insertCategoryList(categories);
            }
        });
    }

    private void insertProductsList(List<Products> products) {

        LocalDatabase.databaseWriteExecutors.execute(() -> productsDao.insertProductList(products));
    }



    private void insertSliderList(List<Slider> slider) {

        LocalDatabase.databaseWriteExecutors.execute(() -> sliderDao.insertSliderList(slider));
    }






}
