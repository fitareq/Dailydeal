package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailydealbd.R;
import com.dailydealbd.adapter.CategoriesAdapter;
import com.dailydealbd.adapter.ProductsAdapter;
import com.dailydealbd.adapter.SliderAdapter;
import com.dailydealbd.roomdata.model.Categories;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.Slider;
import com.dailydealbd.viewmodel.HomeViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;


public class HomeFragment extends Fragment {


    SliderView sliderView;
    private HomeViewModel mViewModel;
    private SliderAdapter adapter;

    private RecyclerView ProductsRView;
    private ProductsAdapter productsAdapter;
    private RecyclerView.LayoutManager productsManager;

    private RecyclerView CategoryRView;
    private CategoriesAdapter categoriesAdapter;
    private RecyclerView.LayoutManager categoriesManager;

    private List<Products> allProducts;
    //private LiveData<List<Slider>> sliderList;



    public HomeFragment() {


    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ProductsRView = rootView.findViewById(R.id.just_for_you_rview);
        ProductsRView.setHasFixedSize(true);
        ProductsRView.setNestedScrollingEnabled(false);

        CategoryRView = rootView.findViewById(R.id.categories_recyclerview);
        CategoryRView.setHasFixedSize(true);
        CategoryRView.setNestedScrollingEnabled(false);


        categoriesManager = new GridLayoutManager(getContext(),1,RecyclerView.HORIZONTAL,false);
        CategoryRView.setLayoutManager(categoriesManager);
        productsManager = new GridLayoutManager(getContext(), 2);
        ProductsRView.setLayoutManager(productsManager);

        sliderView = rootView.findViewById(R.id.slider);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.startAutoCycle();
        mViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        if (savedInstanceState == null) {
            mViewModel.fetchProductsDataFromRemote();
            mViewModel.fetchSliderDataFromRemote();
            mViewModel.fetchCategoriesDataFromRemote();
        }
        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        mViewModel.getProductsList().observe(getViewLifecycleOwner(), new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                //allProducts = products;
                productsAdapter = new ProductsAdapter(products);
                ProductsRView.setAdapter(productsAdapter);
            }
        });
        mViewModel.getSliderList().observe(getViewLifecycleOwner(), new Observer<List<Slider>>() {
            @Override
            public void onChanged(List<Slider> sliders) {

                adapter = new SliderAdapter(sliders);
                sliderView.setSliderAdapter(adapter);
            }
        });

        mViewModel.getCategoriesList().observe(getViewLifecycleOwner(), new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> categories) {
                categoriesAdapter = new CategoriesAdapter(categories);
                CategoryRView.setAdapter(categoriesAdapter);
            }
        });


        //sliderList = viewModel.getSliderList();


    }




}