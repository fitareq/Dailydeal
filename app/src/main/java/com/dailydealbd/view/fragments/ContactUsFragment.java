package com.dailydealbd.view.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dailydealbd.R;
import com.dailydealbd.utils.OnClickRoutes;


public class ContactUsFragment extends Fragment {

    private TextView callbtn;
    private ImageView mapbtn;

    private OnClickRoutes.contactUsFragmentListener contactUsFragmentListener;

    public ContactUsFragment(OnClickRoutes.contactUsFragmentListener contactUsFragmentListener)
    {
        this.contactUsFragmentListener = contactUsFragmentListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.contact_us,container,false);
        callbtn = v.findViewById(R.id.map);
        mapbtn = v.findViewById(R.id.phone_number);

        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactUsFragmentListener.conctactUsMapClickListener();
            }
        });

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactUsFragmentListener.conctactUsPhoneClickListener();
            }
        });

        return v;
    }




}
