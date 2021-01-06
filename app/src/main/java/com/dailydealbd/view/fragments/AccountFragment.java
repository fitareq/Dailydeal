package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.dailydealbd.R;

public class AccountFragment extends Fragment {

    private TextView AccountUserName, AccountWishList, AccountOder;
    private ImageView AccountSettingsBtn;
    private Button AccountWishListBtn, AccountOrderBtn, AccountRegisterLogin;




    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment AccountFragment.
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        AccountUserName = v.findViewById(R.id.account_user_name);
        AccountWishList = v.findViewById(R.id.account_my_wishList);
        AccountOder = v.findViewById(R.id.account_my_order);
        AccountSettingsBtn = v.findViewById(R.id.account_settings_btn);
        AccountWishListBtn = v.findViewById(R.id.account_wishlist_btn);
        AccountOrderBtn = v.findViewById(R.id.account_order_btn);
        AccountRegisterLogin = v.findViewById(R.id.account_register_login);


        return v;
    }
}