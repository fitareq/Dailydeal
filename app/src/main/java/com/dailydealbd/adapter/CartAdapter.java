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
    private OnClickRoutes.cartAdapterClickListener cartAdapterClickListener;

    public CartAdapter(List<Cart> cartList, OnClickRoutes.cartAdapterClickListener cartAdapterClickListener)
    {
        this.cartList = cartList;
        this.cartAdapterClickListener = cartAdapterClickListener;
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
        String attributeOption = current.getAttributesOption();
        int quantity = current.getProductQuantity();
        String q = String.valueOf(quantity);
        int userId = 1;
        int pr;
        int productId = current.getProductId();
        String p;
        int totalPrice;


        if (image!=null)
        {
            String img = ConstantsResources.PRODUCT_IMAGE_BASE_URL+image;
            Picasso.get().load(img).into(holder.cartProductImage);
        }
        holder.cartProductTitle.setText(title);
        if (attributeOption!=null)
        {

            if (attributeOption.contains("-"))
            {
                int b = attributeOption.indexOf('-');
                int l = attributeOption.length();
                p = attributeOption.substring(b+1,l-1);

                //s = attributeOption.substring(attributeOption.indexOf('-')-1, attributeOption.indexOf('৳')-1);

            }else {
                p = attributeOption;
            }
            p = p.replaceAll(" ", "");
            pr = Integer.parseInt(p);
            totalPrice = pr*quantity;
            price = pr+"*"+quantity;
            holder.cartProductPrice.setText(price);
            holder.cartProductTotalPrice.setText(String.valueOf(totalPrice));

        }

        //holder.cartProductPrice.setText(p);
        holder.cartProductQuantity.setText(q);
       // holder.cartProductTotalPrice.setText(String.valueOf(totalPrice));

        holder.cartProductDelete.setOnClickListener(v -> cartAdapterClickListener.deleteCart(current));

        holder.cartProductQuantityAdd.setOnClickListener(v -> {
            int n = quantity+1;
            Cart cart = new Cart(userId, productId,n,"price",attributeOption,image,title );
            //cartAdapterClickListener.deleteCart(current);
            cartAdapterClickListener.updateCart(cart);
        });
        holder.cartProductQuantitySub.setOnClickListener(v -> {
            if (quantity>1) {
                int n = quantity-1;
                Cart cart = new Cart(userId, productId, n, "price", attributeOption, image, title);
                //cartAdapterClickListener.deleteCart(current);
                cartAdapterClickListener.updateCart(cart);
            }
        });
        holder.cartProductCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartAdapterClickListener.checkoutCart(productId, title, image,holder.cartProductTotalPrice.getText().toString(),quantity,attributeOption);
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
