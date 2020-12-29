package com.dailydealbd.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.dailydealbd.R;
import com.dailydealbd.adapter.SliderAdapter;
import com.dailydealbd.roomdata.model.Slider;
import com.dailydealbd.viewmodel.HomeViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;


public class HomeFragment extends Fragment {


SliderView sliderView;
private HomeViewModel mViewModel;
private SliderAdapter adapter;
    //private LiveData<List<Slider>> sliderList;



public HomeFragment(HomeViewModel homeViewModel) {
    mViewModel = homeViewModel;

}



@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


    //mViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
    if (savedInstanceState==null)
        mViewModel.fetchDataFromRemote();
    View rootView = inflater.inflate(R.layout.fragment_home, container, false);
    sliderView = rootView.findViewById(R.id.slider);
    sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
    sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
    sliderView.startAutoCycle();
        return rootView;
}

@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);




    mViewModel.getSliderList().observe(getViewLifecycleOwner(), new Observer<List<Slider>>() {
        @Override
        public void onChanged(List<Slider> sliders) {
            adapter = new SliderAdapter(sliders);
            sliderView.setSliderAdapter(adapter);
        }
    });





    //sliderList = viewModel.getSliderList();



}





}