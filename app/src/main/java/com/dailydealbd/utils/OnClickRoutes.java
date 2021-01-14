package com.dailydealbd.utils;

import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.viewmodel.AccountViewModel;


public interface OnClickRoutes {
    interface categoryListener
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
    interface loginClickListener
    {
        void loginToRegistration();
        void loginToHome();
    }
    interface cartClickListener
    {
        void cartToLogin();
        void cartToOrder(int productId, String title, String image, String totalPrice, int quantity, String attributeOption);
    }
    interface accountFragmentListener
    {
        void accountToLogin();
        void accountToSettings(AccountViewModel viewModel);

    }
    interface orderFragmentListener
    {
        void orderToCart();
    }

}
