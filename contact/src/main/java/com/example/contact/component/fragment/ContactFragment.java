package com.example.contact.component.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.contact.R;
import com.example.contact.databinding.FragmentSocialContactBinding;


/**
 * Created by Vishal Patolia on 18-Feb-18.
 */
public class ContactFragment extends Fragment {
    FragmentSocialContactBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_social_contact, container, false);
        mBinding.homeTxtTitle.setText(getString(R.string.menu_social_contact));
        return mBinding.getRoot();
    }
}
