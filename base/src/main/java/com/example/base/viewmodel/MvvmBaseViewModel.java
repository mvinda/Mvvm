package com.example.base.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.base.model.SuperBaseModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class MvvmBaseViewModel <V,M extends SuperBaseModel> extends ViewModel implements IMvvmBaseViewModel<V> {
    private Reference<V> mUIRef;
    protected M model;

    public void attachUI(V ui) {
        mUIRef = new WeakReference<>(ui);
    }

    @Override
    public V getPageView() {
        return null;
    }

    @Override
    public boolean isUIAttached() {
        return false;
    }

    @Override
    public void detachUI() {

    }


}
