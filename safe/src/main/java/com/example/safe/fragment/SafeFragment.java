package com.example.safe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.safe.R;
import com.example.safe.databinding.FragmentSafeBinding;


@Route(path = "/safe/safe_fragment")
public class SafeFragment extends Fragment {
    FragmentSafeBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_safe, container, false);
        mBinding.homeTxtTitle.setText("商家");
        return mBinding.getRoot();
    }
}
