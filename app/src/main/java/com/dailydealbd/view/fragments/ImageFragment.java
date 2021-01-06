package com.dailydealbd.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.dailydealbd.R;
import com.dailydealbd.utils.ConstantsResources;
import com.dailydealbd.utils.OnClickRoutes;
import com.squareup.picasso.Picasso;


public class ImageFragment extends Fragment {



    private ImageButton backBtn;
    private ImageView fullImage;
    private String image, slug;
    private OnClickRoutes.fullImageClickListener clickListener;
    public ImageFragment(String image,OnClickRoutes.fullImageClickListener clickListener, String slug) {
        // Required empty public constructor
        this.image = image;
        this.clickListener = clickListener;
        this.slug = slug;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_image, container, false);
        backBtn = v.findViewById(R.id.image_back_btn);
        fullImage = v.findViewById(R.id.product_full_image);
        if (image!=null)
        {
            image = ConstantsResources.PRODUCT_IMAGE_BASE_URL+image;
            Picasso.get().load(image).into(fullImage);
        }
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.fullImageOnBackClick(slug);
            }
        });
        return v;
    }




}