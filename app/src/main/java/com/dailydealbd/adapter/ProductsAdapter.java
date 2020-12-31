package com.dailydealbd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.utils.ConstantsResources;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private List<Products> products;



    public ProductsAdapter(List<Products> products) {

        this.products = products;
    }



    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_card_view,parent,false);
        return new ProductsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {

        Products current = products.get(position);

        String title = current.getProductTitle();
        String price = current.getProductPrice();
        String offerPrice = current.getProductOfferPrice();
        String image = current.getProductImage();

        if (title!=null)
            holder.productTitle.setText(title);
        if (price!=null)
            holder.productPrice.setText(price);
        else holder.productPrice.setVisibility(View.GONE);
        if (offerPrice!=null)
            holder.productOfferPrice.setText(offerPrice);
        else holder.productOfferPrice.setVisibility(View.GONE);
        if (image!=null)
        {
            image = ConstantsResources.PRODUCT_IMAGE_BASE_URL+image;
            Picasso.get().load(image).into(holder.productImage);
        }

    }

    @Override
    public int getItemCount() {

        return products.size();
    }

    public void setProducts(List<Products> products)
    {
        this.products = products;
    }


    public static class ProductsViewHolder extends RecyclerView.ViewHolder
{

   ImageView productImage;
   TextView productTitle;
   TextView productPrice;
   TextView productOfferPrice;
   RatingBar productRating;

    public ProductsViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.product_image);
        productTitle = itemView.findViewById(R.id.product_title);
        productPrice = itemView.findViewById(R.id.product_price);
        productOfferPrice = itemView.findViewById(R.id.product_previous_price);
        productRating = itemView.findViewById(R.id.product_rating);
    }
}





}
