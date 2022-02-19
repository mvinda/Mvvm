package com.example.mall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mall.R;
import com.example.mall.databinding.FragmentMallBinding;


@Route(path = "/mall/mall_fragment")
public class MallFragment extends Fragment {
    FragmentMallBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mall, container, false);
        mBinding.homeTxtTitle.setText("商场");
        return mBinding.getRoot();
    }
}
