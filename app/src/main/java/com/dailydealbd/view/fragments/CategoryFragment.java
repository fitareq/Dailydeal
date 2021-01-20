package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailydealbd.R;
import com.dailydealbd.adapter.CategoriesAdapter;
import com.dailydealbd.adapter.ProductsAdapter;
import com.dailydealbd.roomdata.model.Categories;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.CategoryViewModel;
import com.dailydealbd.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment implements OnClickRoutes.categoryAdapterListener {


    private RecyclerView categoryListRView;
    private CategoriesAdapter categoryListAdapter;
    private RecyclerView.LayoutManager categoryListManager;

    private RecyclerView categoryProductsRView;
    private ProductsAdapter categoryProductsAdapter;
    private RecyclerView.LayoutManager categoryProductsManager;

    private TextView categoryTitle;
    private CategoryViewModel viewModel;

    private int cid;
    private String title;



    public CategoryFragment(int cid, String title) {
        this.cid = cid;
        this.title = title;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_category, container, false);
        categoryTitle = v.findViewById(R.id.category_title);
        categoryListRView = v.findViewById(R.id.all_category_list);
        categoryListRView.setHasFixedSize(true);
        categoryListManager = new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false);
        categoryListRView.setLayoutManager(categoryListManager);

        categoryProductsRView = v.findViewById(R.id.category_products);
        categoryProductsRView.setHasFixedSize(true);
        categoryProductsManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        categoryProductsRView.setLayoutManager(categoryProductsManager);
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        return v;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


       viewModel.getCategories().observe(getViewLifecycleOwner(),categories -> {
           categoryListAdapter = new CategoriesAdapter(categories, this);
           categoryListRView.setAdapter(categoryListAdapter);
           if (cid==0||title==null) {
               if (categories != null)
                   if (categories.size() > 0) {
                       LoadCategoryProducts(categories.get(0).getCategoryId(), categories.get(0).getCategoryName());
                   }
           }else LoadCategoryProducts(cid,title);
       });



    }







    private void LoadCategoryProducts(int cId, String title) {

        categoryTitle.setText(title);
        viewModel.getProducts().observe(getViewLifecycleOwner(), product -> {
            List<Products> categoryProducts = new ArrayList<>();
            if (product != null)
                if (product.size() > 0) {
                    for (Products p : product) {
                        if (p.getProductCategoryId() == cId) {
                            categoryProducts.add(p);
                        }
                    }

                    categoryProductsAdapter = new ProductsAdapter(categoryProducts, (OnClickRoutes.singleProductClickListener) getActivity());
                    categoryProductsRView.setAdapter(categoryProductsAdapter);
                }
        });
    }



    @Override
    public void categoryClick(int cId, String title) {
        LoadCategoryProducts(cId,title);
    }




}