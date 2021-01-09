package com.dailydealbd.view.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aamarpay.library.AamarPay;
import com.aamarpay.library.DialogBuilder;
import com.dailydealbd.R;
import com.dailydealbd.adapter.CartAdapter;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.CartViewModel;

import org.json.JSONObject;


public class CartFragment extends Fragment implements OnClickRoutes.cartAdapterClickListener {





    public CartFragment(OnClickRoutes.cartClickListener cartClickListener) {

        // Required empty public constructor
        this.cartClickListener = cartClickListener;
    }


    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private CartViewModel viewModel;
    private OnClickRoutes.cartClickListener cartClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_cart, container, false);
       recyclerView = v.findViewById(R.id.cart_recycler_view);

       manager = new LinearLayoutManager(getContext());
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(manager);
       viewModel = new ViewModelProvider(this).get(CartViewModel.class);
       viewModel.getAllCart().observe(getViewLifecycleOwner(), carts -> {
           adapter = new CartAdapter(carts, CartFragment.this);
           recyclerView.setAdapter(adapter);
       });
        return v;
    }



    @Override
    public void deleteCart(Cart cart) {
        viewModel.deleteCart(cart);
    }



    @Override
    public void updateCart(Cart cart)
    {
        viewModel.updateCart(cart);
    }



    @Override
    public void checkoutCart(int productId, String title, String image, String totalPrice, int quantity, String attributeOption) {
        cartClickListener.cartToOrder(productId, title, image, totalPrice, quantity,attributeOption);
    }




}