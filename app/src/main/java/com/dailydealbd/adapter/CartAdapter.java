package com.dailydealbd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Cart;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Cart> cartList;

    public CartAdapter(List<Cart> cartList)
    {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_card_view, parent,false);
        return new CartViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {

        return cartList.size();
    }



    public class CartViewHolder extends RecyclerView.ViewHolder
{


    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
    }




}
}
