package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailydealbd.R;
import com.dailydealbd.adapter.CartAdapter;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.CartViewModel;

import java.util.List;


public class CartFragment extends Fragment implements OnClickRoutes.cartAdapterClickListener {





    public CartFragment(OnClickRoutes.cartClickListener cartClickListener) {

        // Required empty public constructor
        this.cartClickListener = cartClickListener;
    }


    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private CartViewModel viewModel;
    private Button cartAllCheckout;
    private TextView cartAllPrice;
    private String  price;
    private OnClickRoutes.cartClickListener cartClickListener;

    private boolean isUserLoggedIn = false;
    private int position = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_cart, container, false);
       recyclerView = v.findViewById(R.id.cart_recycler_view);
       cartAllCheckout = v.findViewById(R.id.cart_all_product_checkout);
       cartAllPrice = v.findViewById(R.id.cart_all_product_total_price);

       manager = new LinearLayoutManager(getContext());
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(manager);
       viewModel = new ViewModelProvider(this).get(CartViewModel.class);
       viewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
           @Override
           public void onChanged(User user) {
               if (user!=null)
               {
                   isUserLoggedIn = true;
                   //cartClickListener.cartToLogin();
               }
           }
       });
       viewModel.getAllCart().observe(getViewLifecycleOwner(), carts -> {
           adapter = new CartAdapter(carts, CartFragment.this);
           recyclerView.setAdapter(adapter);
           if (position!=0)
               recyclerView.scrollToPosition(position);
       });

        return v;
    }




    @Override
    public void deleteCart(Cart cart) {
        viewModel.deleteCart(cart);
    }



    @Override
    public void updateCart(Cart cart, int position)
    {
        this.position = position;
        viewModel.updateCart(cart);
    }



    @Override
    public void checkoutCart(int productId, String title, String image, String totalPrice, int quantity, String attributeOption) {

        if (isUserLoggedIn)
            cartClickListener.cartToOrder(productId, title, image, totalPrice, quantity,attributeOption);
        else cartClickListener.cartToLogin();
    }



    @Override
    public void cartAllPrice(int totPrice) {
        this.price = String.valueOf(totPrice);
        cartAllPrice.setText(price);
    }




}