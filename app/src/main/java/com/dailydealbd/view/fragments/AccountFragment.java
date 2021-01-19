package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailydealbd.R;
import com.dailydealbd.adapter.BannerAdapter;
import com.dailydealbd.adapter.ProductsAdapter;
import com.dailydealbd.adapter.SliderAdapter;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.AccountViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


public class AccountFragment extends Fragment {


    private TextView userName, userPhone;
    private ImageButton AccountWishListBtn, AccountOrderBtn, AccountCartBtn;
    private AccountViewModel viewModel;
    private ImageButton accountSettingsButton, accountBackBtn;

    private String user_name, user_phone;

    private OnClickRoutes.accountFragmentListener accountFragmentListener;

    private SliderView sliderView;
    private BannerAdapter adapter;
    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;
    private RecyclerView.LayoutManager productsManager;


    public AccountFragment(OnClickRoutes.accountFragmentListener accountFragmentListener) {
        // Required empty public constructor
        this.accountFragmentListener = accountFragmentListener;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);


        AccountWishListBtn = v.findViewById(R.id.account_user_wish_list_image_view);
        AccountOrderBtn = v.findViewById(R.id.account_order_ib);
        AccountCartBtn = v.findViewById(R.id.account_cart_ib);
        userName = v.findViewById(R.id.account_username);
        userPhone = v.findViewById(R.id.account_user_phone);
        accountBackBtn = v.findViewById(R.id.account_back_btn);
        accountSettingsButton = v.findViewById(R.id.account_settings_btn);
        sliderView = v.findViewById(R.id.account_banner);
        recyclerView = v.findViewById(R.id.just_for_you_account_rview);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.startAutoCycle();
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);


        productsManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(productsManager);

        accountBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountFragmentListener.accountToHome();
            }
        });
        AccountCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountFragmentListener.accountToCart();
            }
        });
        AccountOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountFragmentListener.accountToOrder();
            }
        });
        AccountWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountFragmentListener.accountToWishList();
            }
        });
        return v;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        viewModel.setAccountFragmentListener(accountFragmentListener);


        viewModel.getBanners().observe(getViewLifecycleOwner(),bannerList -> {
            adapter = new BannerAdapter(bannerList);
            sliderView.setSliderAdapter(adapter);
        });
        viewModel.getProducts().observe(getViewLifecycleOwner(),products -> {
            productsAdapter = new ProductsAdapter(products, (OnClickRoutes.singleProductClickListener) getActivity());
            recyclerView.setAdapter(productsAdapter);
        });
        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {

            if (user != null) {
                //if (user.getUserName()!=null && !user.getUserName().isEmpty()){
                //user_name = ;
                //user_phone = ;
                userName.setText(user.getUserName());
                userPhone.setText(user.getPhoneNumber());
            }else viewModel.gotoLogin();
        //}else viewModel.gotoLogin();

        });



        accountSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountFragmentListener.accountToSettings(viewModel);
            }
        });
        /*if (user != null) {
            user_name = user.getUserName();
            user_phone = user.getPhoneNumber();
            if (!user_name.isEmpty())
            {
                userName.setText(user_name);
            }
            if (!user_phone.isEmpty())
            {
                userPhone.setText(user_phone);
            }
        }*/


    }




}