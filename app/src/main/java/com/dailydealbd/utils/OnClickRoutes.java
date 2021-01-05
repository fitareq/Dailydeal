package com.dailydealbd.utils;

public interface OnClickRoutes {
    interface categoryOnClickFromCategoryFragment
    {
        void categoryClickFCAdapterTCFragment(int cId,String cTitle);
    }
    interface loadCategoryFromHome{
        void homeToCategory(int cId, String cTitle);
    }
    interface loadSingleProduct
    {
        void loadSingleProductData(String slug);
    }
}
