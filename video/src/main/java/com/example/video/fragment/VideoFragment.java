package com.example.video.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.video.R;
import com.example.video.databinding.FragmentVideoBinding;


/**
 * Created by Vishal Patolia on 18-Feb-18.
 */
@Route(path = "/video/contact_fragment")
public class VideoFragment extends Fragment {
    FragmentVideoBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false);
        mBinding.homeTxtTitle.setText(getString(R.string.menu_video));
        return mBinding.getRoot();
    }
}
