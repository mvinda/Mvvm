package com.example.news.home;

import android.util.Log;

import com.example.base.mvvm.activity.IBaseView;
import com.example.base.mvvm.model.BaseModel;
import com.example.base.mvvm.viewmodel.MvvmBaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentViewModel extends MvvmBaseViewModel<HomeFragmentViewModel.IMainView, HomeModel> implements BaseModel.IModelListener<ArrayList<HomeModel.Channel>> {
    public ArrayList<HomeModel.Channel> channels = new ArrayList<>();


    public HomeFragmentViewModel(){
        model = new HomeModel();
        model.register(this);
    }

    public void refresh(){
        model.getCachedDataAndLoad();
    }

    @Override
    public void onLoadFinish(BaseModel model, ArrayList<HomeModel.Channel> data) {

    }

    @Override
    public void onLoadFail(BaseModel model, String prompt) {
        Log.i("whd", "onLoadFail: onLoadFail"+prompt);
    }


    public interface IMainView extends IBaseView {
    }
}
