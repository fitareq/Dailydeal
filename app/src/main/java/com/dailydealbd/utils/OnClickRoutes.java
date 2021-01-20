package com.dailydealbd.utils;

import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.WishList;
import com.dailydealbd.viewmodel.AccountViewModel;


public interface OnClickRoutes {



    interface categoryAdapterListener
    {
        void categoryClick(int cId, String title);
    }
    interface homeFragmentListener {
        void homeToCategory(int cId, String cTitle);
    }
    interface singleProductClickListener
    {
        void loadSingleProductData(String slug);
    }
    interface cartAdapterClickListener
    {
        void deleteCart(Cart cart);
        void updateCart(Cart cart, int position);
        void checkoutCart(Cart cart);
    }
    interface singleProductBackPressed
    {
        void singleProductBackPressedListener();
    }
    interface fullImageClickListener
    {
        void fullImageOnBackClick(String slug);
    }
    interface singleProductImageClick
    {
        void showFullImage(String image, String slug);
    }
    interface singleProductMyCartClick
    {
        void goToMyCart();
    }
    interface registrationClickListener
    {
        void goToLoginFromRegistration();
    }
    interface loginFragmentListener
    {
        void loginToRegistration();
        void loginToHome();
    }
    interface cartFragmentListener
    {
        void cartToLogin();
        void cartToHome();
        void cartToOrder(int productId, String title, String image, String totalPrice, int quantity, String attributeOption);
    }
    interface accountFragmentListener
    {
        void accountToLogin();
        void accountToSettings(AccountViewModel viewModel);
        void accountBackBtnPressed();
        void accountToCart();
        void accountToOrder();
        void accountToWishList();

    }
    interface orderFragmentListener
    {
        void orderToCart();
    }
    interface wishlistFragmentListener
    {
        void wishlishtBackBtnPressed();
        void wishlistToSingleProduct(String slug);
    }
    interface wishlistAdapterListener
    {
        void wishlistDeleteBtnClickListener(WishList wishList);
        void wishlistItemClickListener(String slug);
    }

    interface contactUsFragmentListener
    {
        void conctactUsMapClickListener();
        void conctactUsPhoneClickListener();
        void contactUsBackPressed();
    }
}
