package com.example.news.headlinenews;

import android.util.Log;

import com.example.base.mvvm.activity.IBaseView;
import com.example.base.mvvm.model.BaseModel;
import com.example.base.mvvm.viewmodel.MvvmBaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
public class HeadlLineNewsViewModel extends MvvmBaseViewModel<HeadlLineNewsViewModel.IMainView, ChannelsModel> implements BaseModel.IModelListener<ArrayList<ChannelsModel.Channel>> {
    public ArrayList<ChannelsModel.Channel> channels = new ArrayList<>();


    public HeadlLineNewsViewModel(){
        model = new ChannelsModel();
        model.register(this);
    }

    public void refresh(){
        model.getCachedDataAndLoad();
    }

    @Override
    public void onLoadFinish(BaseModel model, ArrayList<ChannelsModel.Channel> data) {
        if(model instanceof ChannelsModel){
            if(getPageView() != null && data instanceof List) {
                channels.clear();
                channels.addAll(data);
                getPageView().onChannelsLoaded(channels);
            }
        }
    }

    @Override
    public void onLoadFail(BaseModel model, String prompt) {
        Log.i("whd", "onLoadFail: onLoadFail"+prompt);
    }


    public interface IMainView extends IBaseView {
        void onChannelsLoaded(ArrayList<ChannelsModel.Channel> channels);
    }
}
