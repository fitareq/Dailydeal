package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Registration;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.RegistrationViewModel;


public class RegisterFragment extends Fragment {


    private EditText registerName, registerPhoneNumber, registerEmail, registerPassword, registerConfirmPassword;
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

        registerLoginTView.setOnClickListener(v -> {
            viewModel.gotoLogin();
        });
        registerButton.setOnClickListener(v -> {

            registerButton.setText("Processing...");
            /*ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setTitle("Registration");
            dialog.setMessage("Processing...");
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();*/
            String name, phone, email, password, confirmPassword;
            name = registerName.getText().toString();
            phone = registerPhoneNumber.getText().toString();
            email = registerEmail.getText().toString();
            password = registerPassword.getText().toString();
            confirmPassword = registerConfirmPassword.getText().toString();


            if (TextUtils.isEmpty(name)) {
                registerName.setError("Enter a name");
                registerButton.setText("Register");
            } else if (TextUtils.isEmpty(phone)) {
                registerPhoneNumber.setError("Enter a phone number");
                registerButton.setText("Register");
            } else if (TextUtils.isEmpty(email)) {
                registerEmail.setError("Enter an email");
                registerButton.setText("Register");
            } else if (TextUtils.isEmpty(password)) {
                registerPassword.setError("Enter a password");
                registerButton.setText("Register");
            } else if (!TextUtils.equals(password, confirmPassword)) {
                registerPassword.setError("Password didn't match");
                registerConfirmPassword.setError("Password didn't match");
                registerButton.setText("Register");
            } else {
                Registration user = new Registration(name, email, password, phone);
                viewModel.registerNewUser(user);
            }
            //viewModel.userInputValidation(registerButton,name,phone,email,password,confirmPassword);
            //dialog.dismiss();
            //viewModel.registerNewUser(user);

        });
    }




}