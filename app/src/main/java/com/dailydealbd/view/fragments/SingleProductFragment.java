package com.dailydealbd.view.fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.utils.ConstantsResources;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.SingleProductViewModel;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;


public class SingleProductFragment extends Fragment implements View.OnClickListener {

    private SliderView sliderView;
    private ImageButton productQuantityAdd, productQuantitySub, backBtn;
    private TextView singleProductTitle,singleProductStock,
                     singleProductDescription, singleProductSku,
                     singleProductQuantityTview, singleProductQuantity, singleProductPrice, singleProductOfferPrice;
    private ImageView ivWishlist, productImage;
    private Button myCart, addToCart;
    private final String slug;
    private SingleProductViewModel viewModel;
    private Cart cart;
    String title;
    String description;
    String sku;
    String price;
    String offerPrice;
    String image;
    String attribute;
    int productId;
    int stock;
    int quantity = 1;
    private OnClickRoutes.singleProductBackPressed backPressed;
    private OnClickRoutes.singleProductImageClick imageClick;
    private OnClickRoutes.singleProductMyCartClick cartClick;


    public SingleProductFragment(String slug, OnClickRoutes.singleProductMyCartClick cartClick, OnClickRoutes.singleProductBackPressed backPressed,OnClickRoutes.singleProductImageClick imageClick) {
        this.slug = slug;
        this.backPressed = backPressed;
        this.imageClick = imageClick;
        this.cartClick = cartClick;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_single_product, container, false);


        backBtn = v.findViewById(R.id.single_product_back_btn);
        productQuantityAdd = v.findViewById(R.id.quantity_add);
        productQuantitySub = v.findViewById(R.id.quantity_sub);
        sliderView = v.findViewById(R.id.single_product_slider);
        productImage = v.findViewById(R.id.single_product_image);
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
        productQuantitySub.setOnClickListener(this);
        productQuantityAdd.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        productImage.setOnClickListener(this);
        myCart.setOnClickListener(this);

        viewModel= new ViewModelProvider(this).get(SingleProductViewModel.class);

        return v;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.setProduct(slug);

        viewModel.getProduct().observe(getViewLifecycleOwner(), products -> {
            title        = products.getProductTitle();
            description  = products.getProductDescription();
            sku          = products.getProductSku();
            price        = products.getProductPrice();
            offerPrice   = products.getProductOfferPrice();
            image        = products.getProductImage();
            attribute    = products.getProductAttributeOptions();
            productId    = products.getProductId();
            stock = products.getProductQuantity();
            String qt = "Available: ";
            if (image!=null)
            {
                String img = ConstantsResources.PRODUCT_IMAGE_BASE_URL+image;
                Picasso.get().load(img).into(productImage);
            }
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


            if (stock >0)
                qt = qt + stock + " piece(s).";
            else qt = "Out of Stock";
            singleProductStock.setText(qt);



        });






    }



    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.add_to_cart) {
            int q = Integer.parseInt(singleProductQuantity.getText().toString());
            cart = new Cart(0,productId,q,price,attribute,image,title);
            viewModel.addToCart(cart);
            Toast.makeText(requireContext(), "Added to Cart", Toast.LENGTH_LONG).show();
        }
        else if (v.getId()==R.id.quantity_add)
        {
            ++quantity;
            singleProductQuantity.setText(String.valueOf(quantity));

        }else if (v.getId()==R.id.quantity_add)
        {
            if (quantity>1)
            {
                --quantity;
                singleProductQuantity.setText(String.valueOf(quantity));
            }
        }else if (v.getId()==R.id.single_product_back_btn)
        {
            backPressed.singleProductBackPressedListener();
        }else if (v.getId()==R.id.single_product_image)
        {
            imageClick.showFullImage(image, slug);
        }else if (v.getId()==R.id.my_cart)
        {
            cartClick.goToMyCart();
        }
    }




}