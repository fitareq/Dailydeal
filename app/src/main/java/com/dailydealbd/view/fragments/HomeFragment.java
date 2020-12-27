package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dailydealbd.R;
import com.dailydealbd.adapter.SliderAdapter;
import com.dailydealbd.model.Slider;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    View rootView;
    private SliderAdapter adapter;
    SliderView sliderView;
    private List<Slider> mSliderModel = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        sliderView = rootView.findViewById(R.id.slider);

        mSliderModel.add(new Slider("description 0",R.drawable.one));
        mSliderModel.add(new Slider("description 1" , R.drawable.two));

        adapter = new SliderAdapter(getContext(),mSliderModel);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.startAutoCycle();

        return rootView;
    }

}