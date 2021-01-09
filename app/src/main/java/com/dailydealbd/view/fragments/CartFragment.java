package com.dailydealbd.view.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.dailydealbd.adapter.ProductsAdapter;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.utils.ConstantsResources;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.CartViewModel;
import com.dailydealbd.viewmodel.HomeViewModel;

import org.json.JSONObject;

import java.util.List;
import java.util.Objects;


public class CartFragment extends Fragment implements OnClickRoutes.cartClickListener{





    public CartFragment() {
        // Required empty public constructor
    }


    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private CartViewModel viewModel;

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
    public void checkoutCart() {

        AlertDialog alertDialog = null;
        DialogBuilder dialogBuilder = new DialogBuilder(getContext(),alertDialog);

        dialogBuilder.showLoading();
        AamarPay aamarPay = new AamarPay(getContext(), "aamarpay","28c78bb1f45112f5d40b956fe104645a");
        aamarPay.testMode(true);
        aamarPay.autoGenerateTransactionID(true);
        String trxId = aamarPay.generate_trx_id();
        //aamarPay.setTransactionID(trxId);
        String trAmount = "100";
        aamarPay.setTransactionParameter("100","BDT","description");
        aamarPay.setCustomerDetails("tareq","fitareq@gmail.com", "01647167559", "mirpur", "dhaka", "Bangladesh");

        aamarPay.initPGW(new AamarPay.onInitListener() {
            @Override
            public void onInitFailure(Boolean error, String message) {

                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                dialogBuilder.errorPopUp(message);
                //dialogBuilder.dismissDialog();
            }



            @Override
            public void onPaymentSuccess(JSONObject jsonObject) {

                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                dialogBuilder.dismissDialog();
            }



            @Override
            public void onPaymentFailure(JSONObject jsonObject) {

                dialogBuilder.errorPopUp(jsonObject.toString());
            }



            @Override
            public void onPaymentProcessingFailed(JSONObject jsonObject) {

            }



            @Override
            public void onPaymentCancel(JSONObject jsonObject) {

                dialogBuilder.errorPopUp(jsonObject.toString());
            }
        });
    }




}