package com.example.news.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.mvvm.fragment.MvvmFragment;
import com.example.news.R;
import com.example.news.databinding.FragmentHomeBinding;


@Route(path = "/home/home_fragment")
public class HomeFragment extends MvvmFragment<FragmentHomeBinding, HomeFragmentViewModel> implements HomeFragmentViewModel.IMainView {

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.refresh();
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeFragmentViewModel getViewModel() {
        return new HomeFragmentViewModel();
    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected String getFragmentTag() {
        return "HeadlineNewsFragment";
    }




}
