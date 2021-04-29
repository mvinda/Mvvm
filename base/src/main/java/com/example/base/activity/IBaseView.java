package com.example.base.activity;

/**
 * Created by Allen on 2017/7/20.
 */
public interface IBaseView {
    void showContent();

    void showLoading();

    void onRefreshEmpty();

    void onRefreshFailure(String message);
}
