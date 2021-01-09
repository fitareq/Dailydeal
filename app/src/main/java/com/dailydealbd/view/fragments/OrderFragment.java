package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.dailydealbd.R;
import com.google.android.material.textfield.TextInputEditText;
import com.reginald.editspinner.EditSpinner;


public class OrderFragment extends Fragment {

    private ImageButton OrderBackBtn;
    private Button PlaceOderBtn;
    private TextInputEditText CheckoutName, checkoutAddress, checkoutCity, checkoutPhone, checkoutEmail;
    private EditSpinner spinner;


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
        OrderBackBtn = v.findViewById(R.id.order_back_btn);
        PlaceOderBtn = v.findViewById(R.id.place_order_btn);
        CheckoutName = v.findViewById(R.id.checkout_name);
        checkoutAddress = v.findViewById(R.id.checkout_address);
        checkoutCity = v.findViewById(R.id.checkout_city);
        checkoutPhone = v.findViewById(R.id.checkout_contact_number);
        checkoutEmail = v.findViewById(R.id.checkout_email);
        spinner = v.findViewById(R.id.checkout_spinner);

        return v;
    }




}