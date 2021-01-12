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

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.AccountViewModel;


public class AccountFragment extends Fragment {


    private TextView userName, userPhone;
    private Button AccountWishListBtn, AccountOrderBtn, AccountRegisterLogin;
    private AccountViewModel viewModel;
    private ImageButton accountSettingsButton;

    private String user_name, user_phone;

    private OnClickRoutes.accountFragmentListener accountFragmentListener;



    public AccountFragment(OnClickRoutes.accountFragmentListener accountFragmentListener) {
        // Required empty public constructor
        this.accountFragmentListener = accountFragmentListener;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        userName = v.findViewById(R.id.account_username);
        userPhone = v.findViewById(R.id.account_user_phone);
        accountSettingsButton = v.findViewById(R.id.account_settings_btn);



        return v;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        viewModel.setAccountFragmentListener(accountFragmentListener);
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