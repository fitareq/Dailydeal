package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.dailydealbd.R;
import com.google.android.material.textfield.TextInputEditText;


public class RegisterFragment extends Fragment {

    private TextInputEditText registerName, registerPhoneNumber, registerEmail, registerPassword, registerConfirmPassword;
    private Button registerButton;



    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_register, container, false);

        registerName = v.findViewById(R.id.register_name);
        registerPhoneNumber = v.findViewById(R.id.register_phone_number);
        registerEmail = v.findViewById(R.id.register_email);
        registerPassword = v.findViewById(R.id.register_password);
        registerConfirmPassword = v.findViewById(R.id.register_confirm_password);
        registerButton = v.findViewById(R.id.register_button);


        return v;
    }
}