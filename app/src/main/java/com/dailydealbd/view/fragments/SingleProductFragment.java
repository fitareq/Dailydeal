package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.dailydealbd.R;
import com.smarteist.autoimageslider.SliderView;

public class SingleProductFragment extends Fragment {

    private SliderView sliderView;
    private TextView singleProductTitle,singleProductStock,
                     singleProductDescription, singleProductSku,
                     singleProductQuantityTview, singleProductQuantity,myCart, addToCart ;
    private ImageView ivWishlist;


    public SingleProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_single_product, container, false);

        sliderView = v.findViewById(R.id.single_product_slider);
        singleProductTitle = v.findViewById(R.id.single_product_title);
        singleProductStock = v.findViewById(R.id.single_product_stock);
        singleProductDescription = v.findViewById(R.id.single_product_description);
        singleProductSku = v.findViewById(R.id.single_product_sku);
        singleProductQuantityTview = v.findViewById(R.id.single_product_quantity_tview);
        singleProductQuantity = v.findViewById(R.id.single_product_quantity);
        myCart = v.findViewById(R.id.my_cart);
        addToCart = v.findViewById(R.id.add_to_cart);
        ivWishlist = v.findViewById(R.id.ivWishlist);




        return v;


    }
}