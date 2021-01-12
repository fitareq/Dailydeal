package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dailydealbd.R;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.AccountViewModel;


public class SettingsFragment extends Fragment {

    private Button signOutBtn;
    private AccountViewModel viewModel;


    public SettingsFragment(AccountViewModel viewModel)
    {
        this.viewModel = viewModel;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.settings, container,false);
        signOutBtn = v.findViewById(R.id.settings_sign_out_btn);
        return v;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.signOutUser();
                signOutBtn.setVisibility(View.GONE);
            }
        });
    }




}
