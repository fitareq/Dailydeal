package com.dailydealbd.view.fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.viewmodel.HomeViewModel;
import com.dailydealbd.viewmodel.SingleProductViewModel;
import com.smarteist.autoimageslider.SliderView;

public class SingleProductFragment extends Fragment implements View.OnClickListener {

    private SliderView sliderView;
    private TextView singleProductTitle,singleProductStock,
                     singleProductDescription, singleProductSku,
                     singleProductQuantityTview, singleProductQuantity, singleProductPrice, singleProductOfferPrice;
    private ImageView ivWishlist;
    private Button myCart, addToCart;
    private final String slug;
    private SingleProductViewModel viewModel;
    private Cart cart;


    public SingleProductFragment(String slug) {
        this.slug = slug;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_single_product, container, false);

        sliderView = v.findViewById(R.id.single_product_slider);
        singleProductTitle = v.findViewById(R.id.single_product_title);
        singleProductStock = v.findViewById(R.id.single_product_stock);
        singleProductDescription = v.findViewById(R.id.single_product_description);
        singleProductSku = v.findViewById(R.id.single_product_sku);
        singleProductQuantityTview = v.findViewById(R.id.single_product_quantity_tview);
        singleProductQuantity = v.findViewById(R.id.single_product_quantity);
        singleProductPrice = v.findViewById(R.id.tvPrice);
        singleProductOfferPrice = v.findViewById(R.id.tvPrice1);
        myCart = v.findViewById(R.id.my_cart);
        addToCart = v.findViewById(R.id.add_to_cart);
        ivWishlist = v.findViewById(R.id.ivWishlist);


        addToCart.setOnClickListener(this);

        viewModel= new ViewModelProvider(this).get(SingleProductViewModel.class);

        return v;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.setProduct(slug);

        viewModel.getProduct().observe(getViewLifecycleOwner(), products -> {
            String title = products.getProductTitle();
            String description = products.getProductDescription();
            String sku = products.getProductSku();
            String price = products.getProductPrice();
            String offerPrice = products.getProductOfferPrice();
            String image = products.getProductImage();
            String qt = "Available: ";
            String attribute = products.getProductAttributeOptions();
            int productId = products.getProductId();
            int quantity = products.getProductQuantity();
            if (title!=null)
                singleProductTitle.setText(title);
            else singleProductTitle.setVisibility(View.GONE);

            if (description!=null)
                singleProductDescription.setText(description);
            else singleProductDescription.setVisibility(View.GONE);

            if (sku!=null) {
                sku = "SKU: " + sku;
                singleProductSku.setText(sku);
            }
            else singleProductSku.setText("SKU: null");

            if (price!=null)
                singleProductPrice.setText(price);
            else singleProductPrice.setText("null");

            if (offerPrice!=null){
                singleProductOfferPrice.setText(offerPrice);
                singleProductPrice.setText(price);
                singleProductPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            }else {
                singleProductPrice.setText(price);
                singleProductOfferPrice.setVisibility(View.GONE);
            }


            if (quantity>0)
                qt = qt + quantity+ " piece(s).";
            else qt = "Out of Stock";
            singleProductStock.setText(qt);


            cart = new Cart(0,productId,1,price,attribute,image,title);
        });






    }



    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.add_to_cart) {
            viewModel.addToCart(cart);
            Toast.makeText(requireContext(), "Added to Cart", Toast.LENGTH_LONG).show();
        }
    }




}