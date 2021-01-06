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
    interface cartClickListener
    {
        void deleteCart(Cart cart);
        void updateCart(Cart cart);
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
}
