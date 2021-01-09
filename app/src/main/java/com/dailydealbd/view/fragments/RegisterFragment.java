package com.dailydealbd.view.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aamarpay.library.DialogBuilder;
import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Registration;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.RegistrationViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


public class RegisterFragment extends Fragment {

    private TextInputEditText registerName, registerPhoneNumber, registerEmail, registerPassword, registerConfirmPassword;
    private Button registerButton;
    private TextView registerLoginTView;
    private RegistrationViewModel viewModel;
    private OnClickRoutes.registrationClickListener registrationClickListener;





    public RegisterFragment(OnClickRoutes.registrationClickListener registrationClickListener) {
        // Required empty public constructor
        this.registrationClickListener = registrationClickListener;
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
        registerLoginTView = v.findViewById(R.id.register_login_textview);

        return v;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        viewModel.setRegistrationClickListener(registrationClickListener);

        registerLoginTView.setOnClickListener(v->{
            viewModel.gotoLogin();
        });
        registerButton.setOnClickListener(v -> {

            ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setTitle("Registration");
            dialog.setMessage("Processing...");
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            String name, phone, email, password, confirmPassword;
            name = Objects.requireNonNull(registerName.getText()).toString();
            phone = Objects.requireNonNull(registerPhoneNumber.getText()).toString();
            email = Objects.requireNonNull(registerEmail.getText()).toString();
            password = Objects.requireNonNull(registerPassword.getText()).toString();
            confirmPassword = Objects.requireNonNull(registerConfirmPassword.getText()).toString();
            viewModel.userInputValidation(name,phone,email,password,confirmPassword);
            dialog.dismiss();
            //viewModel.registerNewUser(user);

        });
    }




}