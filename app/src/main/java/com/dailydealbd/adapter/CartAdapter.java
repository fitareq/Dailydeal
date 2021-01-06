package com.dailydealbd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.utils.ConstantsResources;
import com.dailydealbd.utils.OnClickRoutes;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Cart> cartList;
    private View item;
    private OnClickRoutes.cartClickListener cartClickListener;

    public CartAdapter(List<Cart> cartList, OnClickRoutes.cartClickListener cartClickListener)
    {
        this.cartList = cartList;
        this.cartClickListener = cartClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_card_view, parent,false);
        return new CartViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position)
    {
        Cart current = cartList.get(position);
        String image = current.getProductImage();
        String title = current.getProductTitle();
        String price = current.getProductPrice();


        if (image!=null)
        {
            image = ConstantsResources.PRODUCT_IMAGE_BASE_URL+image;
            Picasso.get().load(image).into(holder.cartProductImage);
        }
        holder.cartProductTitle.setText(title);
        holder.cartProductPrice.setText(price);

        holder.cartProductDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartClickListener.deleteCart(current);
            }
        });

    }



    @Override
    public int getItemCount() {

        return cartList.size();
    }




    public class CartViewHolder extends RecyclerView.ViewHolder
{

    ImageView cartProductImage;
    TextView cartProductTitle;
    TextView cartProductPrice;
    TextView cartProductTotalPrice;
    TextView cartProductQuantity;
    ImageView cartProductQuantitySub;
    ImageView cartProductQuantityAdd;
    Button cartProductCheckout;
    ImageView cartProductDelete;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        cartProductImage = itemView.findViewById(R.id.cart_product_image);
        cartProductTitle = itemView.findViewById(R.id.cart_product_title);
        cartProductPrice = itemView.findViewById(R.id.cart_product_price);
        cartProductTotalPrice = itemView.findViewById(R.id.cart_product_total_price);
        cartProductQuantity = itemView.findViewById(R.id.cart_product_quantity);
        cartProductQuantitySub = itemView.findViewById(R.id.cart_product_quantity_sub);
        cartProductQuantityAdd = itemView.findViewById(R.id.cart_product_quantity_add);
        cartProductCheckout = itemView.findViewById(R.id.cart_product_checkout);
        cartProductDelete = itemView.findViewById(R.id.cart_product_delete);
        item = itemView;
    }




}
}
