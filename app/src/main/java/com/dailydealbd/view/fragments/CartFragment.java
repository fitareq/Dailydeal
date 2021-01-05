package com.dailydealbd.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dailydealbd.R;
import com.dailydealbd.adapter.ProductsAdapter;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.HomeViewModel;

import java.util.List;


public class CartFragment extends Fragment {





    public CartFragment() {
        // Required empty public constructor
    }


    private RecyclerView recyclerView;
    private ProductsAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_cart, container, false);
       recyclerView = v.findViewById(R.id.review);

       manager = new GridLayoutManager(getContext(),2);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(manager);
       homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
       homeViewModel.getProductsList().observe(getViewLifecycleOwner(), new Observer<List<Products>>() {
           @Override
           public void onChanged(List<Products> products) {
               adapter = new ProductsAdapter(products, (OnClickRoutes.loadSingleProduct) getActivity());
               recyclerView.setAdapter(adapter);
           }
       });
        return v;
    }




}