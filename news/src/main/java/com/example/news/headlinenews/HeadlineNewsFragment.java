package com.example.news.headlinenews;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.base.fragment.MvvmFragment;
import com.example.news.R;
import com.example.news.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HeadlineNewsFragment extends MvvmFragment<FragmentHomeBinding, HeadlLineNewsViewModel> implements HeadlLineNewsViewModel.IMainView {
    HeadlineNewsFragmentAdapter mAdapter;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.refresh();
        viewDataBinding.tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        initChannels();
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
    public HeadlLineNewsViewModel getViewModel() {
        return new HeadlLineNewsViewModel();
    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected String getFragmentTag() {
        return "HeadlineNewsFragment";
    }

    public void initChannels() {
        mAdapter = new HeadlineNewsFragmentAdapter(getChildFragmentManager());
        viewDataBinding.viewpager.setAdapter(mAdapter);
        viewDataBinding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(viewDataBinding.tablayout));
        viewDataBinding.viewpager.setOffscreenPageLimit(1);
        //绑定tab点击事件
        viewDataBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewDataBinding.viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onChannelsLoaded(ArrayList<ChannelsModel.Channel> channels) {
        mAdapter.setChannels(channels);
        viewDataBinding.tablayout.removeAllTabs();
        for (ChannelsModel.Channel channel : channels) {
            viewDataBinding.tablayout.addTab(viewDataBinding.tablayout.newTab().setText(channel.channelName));
        }
        viewDataBinding.tablayout.scrollTo(0,0);
    }


}
