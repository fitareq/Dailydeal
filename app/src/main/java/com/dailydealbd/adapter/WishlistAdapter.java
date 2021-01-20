package com.dailydealbd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.WishList;
import com.dailydealbd.utils.ConstantsResources;
import com.dailydealbd.utils.OnClickRoutes;
import com.squareup.picasso.Picasso;

import java.util.List;


public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistHolder> {


    private List<WishList> wishLists;
    private OnClickRoutes.wishlistAdapterListener wishlistAdapterListener;



    public WishlistAdapter(List<WishList> wishLists, OnClickRoutes.wishlistAdapterListener wishlistAdapterListener) {

        this.wishLists = wishLists;
        this.wishlistAdapterListener = wishlistAdapterListener;
    }



    @NonNull
    @Override
    public WishlistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_card_view, parent, false);
        return new WishlistHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull WishlistHolder holder, int position) {

        WishList current = wishLists.get(position);

        String image = current.getProductImage();
        String title = current.getProductTitle();
        String slug = current.getProductSlug();

        if (image != null) {
            String img = ConstantsResources.PRODUCT_IMAGE_BASE_URL + image;
            Picasso.get().load(img).into(holder.productIView);
        }
        if (title != null) {
            holder.productTitleTView.setText(title);
        }
        holder.deleteIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wishlistAdapterListener.wishlistDeleteBtnClickListener(current);
            }
        });
        holder.productTitleTView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wishlistAdapterListener.wishlistItemClickListener(slug);
            }
        });
        holder.productIView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wishlistAdapterListener.wishlistItemClickListener(slug);
            }
        });
    }



    @Override
    public int getItemCount() {

        return wishLists.size();
    }


    public class WishlistHolder extends RecyclerView.ViewHolder {


        ImageView productIView;
        TextView productTitleTView;
        ImageButton deleteIBtn;



        public WishlistHolder(@NonNull View itemView) {

            super(itemView);
            productIView = itemView.findViewById(R.id.wish_list_image_view);
            productTitleTView = itemView.findViewById(R.id.wish_list_title_text_view);
            deleteIBtn = itemView.findViewById(R.id.wish_list_delete_button);

        }




    }




}
