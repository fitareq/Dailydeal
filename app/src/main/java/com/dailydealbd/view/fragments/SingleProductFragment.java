package com.dailydealbd.view.fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.WishList;
import com.dailydealbd.utils.ConstantsResources;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.SingleProductViewModel;
import com.reginald.editspinner.EditSpinner;
import com.skyhope.showmoretextview.ShowMoreTextView;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;


public class SingleProductFragment extends Fragment implements View.OnClickListener {


    private ShowMoreTextView singleProductDescription;
    private SliderView sliderView;
    private EditSpinner editSpinner;
    private ImageButton productQuantityAdd, productQuantitySub, backBtn;
    private TextView singleProductTitle, singleProductStock, singleProductSku,
            singleProductQuantityTview, singleProductQuantity, singleProductPrice, singleProductOfferPrice, cartBadge;
    private ImageView ivWishlist, ivWishlistAdded, productImage;
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
    int userId = 0;
    int productId = 0;
    int stock;
    int quantity = 1;
    private OnClickRoutes.singleProductBackPressed backPressed;
    private OnClickRoutes.singleProductImageClick imageClick;
    private OnClickRoutes.singleProductMyCartClick cartClick;



    public SingleProductFragment(String slug, OnClickRoutes.singleProductMyCartClick cartClick, OnClickRoutes.singleProductBackPressed backPressed, OnClickRoutes.singleProductImageClick imageClick) {

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


        cartBadge = v.findViewById(R.id.cart_badge);
        backBtn = v.findViewById(R.id.single_product_back_btn);
        editSpinner = v.findViewById(R.id.price_option_spinner);
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
        ivWishlistAdded = v.findViewById(R.id.ivWishlist_added);


        addToCart.setOnClickListener(this);
        productQuantitySub.setOnClickListener(this);
        productQuantityAdd.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        productImage.setOnClickListener(this);
        myCart.setOnClickListener(this);
        ivWishlist.setOnClickListener(this);
        ivWishlistAdded.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(SingleProductViewModel.class);

        return v;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        viewModel.setProduct(slug);


        viewModel.getCarts().observe(getViewLifecycleOwner(), new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> cartList) {
                if (cartList!=null)
                {
                    int size = cartList.size();
                    if (size>0)
                    {
                        cartBadge.setVisibility(View.VISIBLE);
                        cartBadge.setText(String.valueOf(size));
                    }
                    else cartBadge.setVisibility(View.GONE);

                }
            }
        });
        viewModel.getUser().observe(getViewLifecycleOwner(), user ->
        {
            if (user != null) {
                userId = user.getUserId();
            }
        });

        viewModel.getProduct().observe(getViewLifecycleOwner(), products -> {
            title = products.getProductTitle();
            description = products.getProductDescription();
            sku = products.getProductSku();
            price = products.getProductPrice();
            offerPrice = products.getProductOfferPrice();
            image = products.getProductImage();
            attribute = products.getProductAttributeOptions();
            productId = products.getProductId();
            stock = products.getProductQuantity();
            spinnerSelect(attribute);
            String qt = "Available: ";
            if (image != null) {
                String img = ConstantsResources.PRODUCT_IMAGE_BASE_URL + image;
                Picasso.get().load(img).into(productImage);
            }
            if (title != null)
                singleProductTitle.setText(title);
            else singleProductTitle.setVisibility(View.GONE);

            if (description != null) {
                description = description.replaceAll("<.*?>", "");

                singleProductDescription.setShowingLine(3);
                singleProductDescription.addShowMoreText("Read more");
                singleProductDescription.addShowLessText("Read less");
                singleProductDescription.setShowMoreColor(R.color.purple_700);
                singleProductDescription.setShowLessTextColor(R.color.purple_700);
                singleProductDescription.setText(description);


            } else singleProductDescription.setVisibility(View.GONE);

            if (sku != null) {
                sku = "SKU: " + sku;
                singleProductSku.setText(sku);
            } else singleProductSku.setText("SKU: null");

            if (price != null)
                singleProductPrice.setText(price);
            else singleProductPrice.setText("null");

            if (offerPrice != null) {
                singleProductOfferPrice.setText(offerPrice);
                singleProductPrice.setText(price);
                singleProductPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                singleProductPrice.setText(price);
                singleProductOfferPrice.setVisibility(View.GONE);
            }


            if (stock > 0)
                qt = qt + stock + " piece(s).";
            else qt = "Out of Stock";
            singleProductStock.setText(qt);

        });

        viewModel.getWishlist().observe(getViewLifecycleOwner(),wishLists -> {
            if (wishLists!=null)
            {
                for (WishList w: wishLists)
                {
                    if (w.getProductId()==productId)
                    {
                        ivWishlist.setVisibility(View.GONE);
                        ivWishlistAdded.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


    }




    private void spinnerSelect(String att) {

        String[] arrayList = att.split(",");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, arrayList);
        editSpinner.setEditable(false);
        editSpinner.setText(arrayList[0]);
        editSpinner.setAdapter(adapter);

    }



    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.add_to_cart) {
            int q = Integer.parseInt(singleProductQuantity.getText().toString());

            attribute = editSpinner.getText().toString();
            if (attribute.contains("-")) {
                int b = attribute.indexOf("-");
                int l = attribute.length();
                price = attribute.substring(b + 1, l - 1).replaceAll(" ", "");
            } else price = attribute.replaceAll("[a-zA-Z]", "").replaceAll(" ", "");

            String totalPrice = String.valueOf(Integer.parseInt(price) * q);
            cart = new Cart(0, productId, q, price, attribute, image, title, totalPrice);
            viewModel.addToCart(cart);
            Toast.makeText(requireContext(), "Added to Cart", Toast.LENGTH_LONG).show();
        } else if (v.getId() == R.id.quantity_add) {
            ++quantity;
            singleProductQuantity.setText(String.valueOf(quantity));

        } else if (v.getId() == R.id.quantity_sub) {
            if (quantity > 1) {
                --quantity;
                singleProductQuantity.setText(String.valueOf(quantity));
            }
        } else if (v.getId() == R.id.single_product_back_btn) {
            backPressed.singleProductBackPressedListener();
        } else if (v.getId() == R.id.single_product_image) {
            imageClick.showFullImage(image, slug);
        } else if (v.getId() == R.id.my_cart) {
            cartClick.goToMyCart();
        } else if (v.getId() == R.id.ivWishlist) {
            if (userId == 0) {
                Toast.makeText(getContext(), "Please Login to add WishList", Toast.LENGTH_SHORT).show();
            } else {
                WishList wishList = new WishList(userId, productId, title, image, slug);
                viewModel.addToWishlist(wishList);
                ivWishlist.setVisibility(View.GONE);
                ivWishlistAdded.setVisibility(View.VISIBLE);
            }
        } else if (v.getId() == R.id.ivWishlist_added) {
            WishList wishList = new WishList(userId, productId, title, image, slug);
            viewModel.deleteFromWishList(wishList);
            ivWishlistAdded.setVisibility(View.GONE);
            ivWishlist.setVisibility(View.VISIBLE);
        }
    }




}