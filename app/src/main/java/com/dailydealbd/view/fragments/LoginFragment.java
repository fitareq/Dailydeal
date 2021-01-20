package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Login;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;


public class LoginFragment extends Fragment {


    public LoginFragment(OnClickRoutes.loginFragmentListener loginFragmentListener) {
        this.loginFragmentListener = loginFragmentListener;
        // Required empty public constructor
    }

    private ImageButton backButton;
    private TextInputEditText userPhoneNumber;
    private TextInputEditText userPassword;
    private Button loginButton;
    private TextView forgotPassword;
    private TextView registerTview;
    private LoginViewModel viewModel;
    private OnClickRoutes.loginFragmentListener loginFragmentListener;

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


        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        viewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user!=null)
                    if (!user.getUserName().isEmpty())
                    {

                    }
            }
        });
        viewModel.setLoginFragmentListener(loginFragmentListener);


        registerTview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.gotoRegistration();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Processing...");
                String phone = userPhoneNumber.getText().toString();
                String password = userPassword.getText().toString();
                if (TextUtils.isEmpty(phone))
                {
                    userPhoneNumber.setError("Enter a valid number.");
                    loginButton.setText("Login");

                }else if (TextUtils.isEmpty(phone))
                {
                    userPassword.setError("Enter a valid password.");
                    loginButton.setText("Login");

                }else {
                    Login login = new Login(phone, password);
                    viewModel.authenticateUserFromRemote(login);
                    //loginButton.setText("Login");

                }
            }
        });



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFragmentListener.loginToHome();
            }
        });
    }




}