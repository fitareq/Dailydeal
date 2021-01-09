package com.dailydealbd.utils;

import com.dailydealbd.roomdata.model.Cart;


public interface OnClickRoutes {
    interface categoryClickListener
    {
        void categoryClickFCAdapterTCFragment(int cId,String cTitle);
    }
    interface homeClickListener {
        void homeToCategory(int cId, String cTitle);
    }
    interface singleProductClickListener
    {
        void loadSingleProductData(String slug);
    }
    interface cartAdapterClickListener
    {
        void deleteCart(Cart cart);
        void updateCart(Cart cart);
        void checkoutCart(int productId, String title, String image, String totalPrice, int quantity, String attributeOption);
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
    interface loginClickListener
    {
        void loginToRegistration();
        void loginToHome();
    }
    interface cartClickListener
    {
        void cartToOrder(int productId, String title, String image, String totalPrice, int quantity, String attributeOption);
    }

}
