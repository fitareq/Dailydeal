package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dailydealbd.R;
import com.google.android.material.textfield.TextInputEditText;


public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    private ImageButton backButton;
    private TextInputEditText userPhoneNumber;
    private TextInputEditText userPassword;
    private Button loginButton;
    private TextView forgotPassword;
    private TextView registerTview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        backButton = v.findViewById(R.id.back_btn);
        userPhoneNumber = v.findViewById(R.id.login_phone_number);
        userPassword = v.findViewById(R.id.login_password);
        loginButton = v.findViewById(R.id.login_button);
        forgotPassword = v.findViewById(R.id.login_forgot_password);
        registerTview = v.findViewById(R.id.login_register_textview);


        return v;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);



    }




}