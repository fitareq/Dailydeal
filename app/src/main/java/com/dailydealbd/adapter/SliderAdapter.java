package com.dailydealbd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Slider;
import com.dailydealbd.utils.ConstantsResources;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.sliderHolder> {

    private List<Slider> mSliderModel;
    private Context context;

    public SliderAdapter( List<Slider> mSliderModel) {
        this.mSliderModel = mSliderModel;
    }


    @Override
    public sliderHolder onCreateViewHolder(ViewGroup parent) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, parent, false);
        return new sliderHolder(view);
    }

    @Override
    public void onBindViewHolder(sliderHolder viewHolder, final int position) {

        Slider current = mSliderModel.get(position);
        String image = current.getSliderImage();
        String title = current.getSliderTitle();

        if (!title.isEmpty())
            viewHolder.textViewDescription.setText(current.getSliderTitle());
        if (!image.isEmpty())
        {
            image = ConstantsResources.SLIDER_IMAGE_BASE_URL+image;
            Picasso.get().load(image).into(viewHolder.imageViewBackground);
        }

        viewHolder.itemView.setOnClickListener(v -> Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getCount() {
            return mSliderModel.size();
    }

    public class sliderHolder extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public sliderHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;

        }
    }
}
