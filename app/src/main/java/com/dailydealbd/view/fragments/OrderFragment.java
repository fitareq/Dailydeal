package com.dailydealbd.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dailydealbd.R;



public class OrderFragment extends Fragment {


    private int productId, quantity;
    private String title, image, totalPrice, attributeOption;

    public OrderFragment(int productId, String title, String image, String totalPrice, int quantity, String attributeOption) {
        // Required empty public constructor
        this.productId = productId;
        this.quantity = quantity;
        this.title = title;
        this.image = image;
        this.totalPrice = totalPrice;
        this.attributeOption = attributeOption;
        }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order, container, false);
        return v;
    }




}