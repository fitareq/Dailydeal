package com.dailydealbd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Banner;
import com.dailydealbd.utils.ConstantsResources;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;


public class BannerAdapter extends SliderViewAdapter<BannerAdapter.BannerViewHolder> {

    private List<Banner> bannerList;


    public BannerAdapter(List<Banner> bannerList)
    {
        this.bannerList = bannerList;
    }

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_card_view, parent,false);
        return new BannerViewHolder(v);
    }



    @Override
    public void onBindViewHolder(BannerViewHolder viewHolder, int position) {

        String image = bannerList.get(position).getBannerImage();
        if (image!=null)
        {
            image = ConstantsResources.BANNER_IMAGE_BASE_URL+image;
            Picasso.get().load(image).into(viewHolder.imageView);
        }
    }



    @Override
    public int getCount() {

        return bannerList.size();
    }



    public class BannerViewHolder extends SliderViewAdapter.ViewHolder
{


    ImageView imageView;
    public BannerViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.banner_slider_image);
    }




}
}
