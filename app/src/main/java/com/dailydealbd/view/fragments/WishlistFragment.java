package com.dailydealbd.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.dailydealbd.R;
import com.dailydealbd.adapter.WishlistAdapter;
import com.dailydealbd.viewmodel.WishlistViewModel;


public class WishlistFragment extends Fragment {


    private RecyclerView wishlistRView;
    private WishlistAdapter adapter;
    private ImageButton wishlistBackBtn;
    private RecyclerView.LayoutManager manager;
    private WishlistViewModel viewModel;



    public WishlistFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_wishlist, container, false);
        wishlistBackBtn = v.findViewById(R.id.wishlist_back_btn);
        wishlistRView = v.findViewById(R.id.wishlist_recyclerview);

        viewModel = new ViewModelProvider(this).get(WishlistViewModel.class);
        wishlistRView.setHasFixedSize(true);
        manager = new GridLayoutManager(getContext(),1);
        wishlistRView.setLayoutManager(manager);


        viewModel.getWishList().observe(getViewLifecycleOwner(),wishLists -> {
            adapter = new WishlistAdapter(wishLists);
            wishlistRView.setAdapter(adapter);
        });
        return v;
    }




}