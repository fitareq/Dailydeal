package com.dailydealbd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Categories;
import com.dailydealbd.utils.ConstantsResources;
import com.dailydealbd.utils.OnClickRoutes;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    private List<Categories> categories;
    private OnClickRoutes.categoryClickListener onClickFromCategoryFragment;
    private View itemV;

    public CategoriesAdapter(List<Categories> categories, OnClickRoutes.categoryClickListener onClickFromCategoryFragment)
    {
        this.categories = categories;
        this.onClickFromCategoryFragment = onClickFromCategoryFragment;

    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card_view, parent,false);
        return new CategoriesViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Categories current = categories.get(position);
        String image = current.getCategoryImage();
        String title = current.getCategoryName();
        int id = current.getCategoryId();

        if (image!=null)
        {
            image = ConstantsResources.CATEGORY_IMAGE_BASE_URL+image;
            Picasso.get().load(image).placeholder(R.drawable.place_holder).noFade().into(holder.categoryImage);
        }
        if (title!=null)
        {
            holder.categoryTitle.setText(title);
        }else holder.categoryTitle.setVisibility(View.GONE);

        itemV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFromCategoryFragment.categoryClickFCAdapterTCFragment(id,title);
            }
        });

    }


    @Override
    public int getItemCount() {

        return categories.size();
    }



    public class CategoriesViewHolder extends RecyclerView.ViewHolder
{

    ImageView categoryImage;
    TextView categoryTitle;


    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryImage = itemView.findViewById(R.id.category_image);
        categoryTitle = itemView.findViewById(R.id.category_title);
        itemV = itemView;
    }




}
}
