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
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.HomeViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OnClickRoutes.categoryOnClickFromCategoryFragment{


    SliderView sliderView;
    private HomeViewModel mViewModel;
    private SliderAdapter adapter;

    private RecyclerView ProductsRView;
    private ProductsAdapter productsAdapter;
    private RecyclerView.LayoutManager productsManager;

    private RecyclerView weekDealsRView;
    private ProductsAdapter weekDealsAdapter;
    private RecyclerView.LayoutManager weekDealsManager;

    private RecyclerView topRatedRView;
    private ProductsAdapter topRatedAdapter;
    private RecyclerView.LayoutManager topRatedManager;

    private RecyclerView CategoryRView;
    private CategoriesAdapter categoriesAdapter;
    private RecyclerView.LayoutManager categoriesManager;

    private List<Products> allProducts;
    private List<Products> topRatedProducts = new ArrayList<>();
    private List<Products> weekDealsProducts = new ArrayList<>();

    private OnClickRoutes.loadCategoryFromHome loadcategory;
    //private LiveData<List<Slider>> sliderList;



    public HomeFragment(OnClickRoutes.loadCategoryFromHome loadcategory) {
        this.loadcategory = loadcategory;

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        sliderView = rootView.findViewById(R.id.slider);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.startAutoCycle();

        ProductsRView = rootView.findViewById(R.id.just_for_you_rview);
        ProductsRView.setHasFixedSize(true);
        ProductsRView.setNestedScrollingEnabled(false);

        weekDealsRView = rootView.findViewById(R.id.week_deals_rview);
        weekDealsRView.setHasFixedSize(true);
        weekDealsRView.setNestedScrollingEnabled(false);

        topRatedRView = rootView.findViewById(R.id.top_rated_rview);
        topRatedRView.setHasFixedSize(true);
        topRatedRView.setNestedScrollingEnabled(false);

        CategoryRView = rootView.findViewById(R.id.categories_recyclerview);
        CategoryRView.setHasFixedSize(true);
        CategoryRView.setNestedScrollingEnabled(false);


        categoriesManager = new GridLayoutManager(getContext(),1,RecyclerView.HORIZONTAL,false);
        CategoryRView.setLayoutManager(categoriesManager);
        productsManager = new GridLayoutManager(getContext(), 2);
        ProductsRView.setLayoutManager(productsManager);
        weekDealsManager = new GridLayoutManager(getContext(),2);
        weekDealsRView.setLayoutManager(weekDealsManager);
        topRatedManager = new GridLayoutManager(getContext(),2);
        topRatedRView.setLayoutManager(topRatedManager);


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
                if (!products.isEmpty())
                    if (products.size()>0)
                        initializeProducts(products);
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

        mViewModel.getCategoriesList().observe(getViewLifecycleOwner(),categories-> {
                categoriesAdapter = new CategoriesAdapter(categories, (OnClickRoutes.categoryOnClickFromCategoryFragment) this);
                CategoryRView.setAdapter(categoriesAdapter);

        });





        //sliderList = viewModel.getSliderList();


    }



    private void initializeProducts(List<Products> products)
    {
        for (Products p: products)
        {
            if (p.getProductToprated()==1)
                topRatedProducts.add(p);
            if (p.getProductWeekDeals()==1)
                weekDealsProducts.add(p);
        }
        weekDealsAdapter = new ProductsAdapter(weekDealsProducts);
        topRatedAdapter = new ProductsAdapter(topRatedProducts);
        if (weekDealsProducts!=null)
        weekDealsRView.setAdapter(weekDealsAdapter);
        if (topRatedProducts!=null)
        topRatedRView.setAdapter(topRatedAdapter);
    }



    @Override
    public void onDestroy() {
        mViewModel.getSliderList().removeObservers(this);
        super.onDestroy();
    }



    @Override
    public void categoryClickFCAdapterTCFragment(int cId, String cTitle) {
        loadcategory.homeToCategory(cId,cTitle);
    }




}