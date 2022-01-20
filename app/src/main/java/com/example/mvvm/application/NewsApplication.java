package com.example.mvvm.application;


import com.alibaba.android.arouter.launcher.ARouter;
import com.billy.cc.core.component.CC;
import com.example.base.BaseApplication;
import com.example.base.loadsir.CustomCallback;
import com.example.base.loadsir.EmptyCallback;
import com.example.base.loadsir.ErrorCallback;
import com.example.base.loadsir.LoadingCallback;
import com.example.base.loadsir.TimeoutCallback;
import com.example.base.preference.PreferencesUtil;
import com.example.mvvm.BuildConfig;
import com.example.network.ApiBase;
import com.kingja.loadsir.core.LoadSir;

public class NewsApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setDebug(BuildConfig.DEBUG);

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

        ApiBase.setNetworkRequestInfo(new NetworkRequestInfo());
        PreferencesUtil.init(this);
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();

        CC.enableDebug(BuildConfig.DEBUG);
        CC.enableVerboseLog(BuildConfig.DEBUG);
        CC.enableRemoteCC(BuildConfig.DEBUG);
    }
}
