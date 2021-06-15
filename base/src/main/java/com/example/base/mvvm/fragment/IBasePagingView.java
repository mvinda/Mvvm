package com.example.base.mvvm.fragment;


import  com.example.base.mvvm.activity.IBaseView;



public interface IBasePagingView extends IBaseView {

    void onLoadMoreFailure(String message);

    void onLoadMoreEmpty();
}
