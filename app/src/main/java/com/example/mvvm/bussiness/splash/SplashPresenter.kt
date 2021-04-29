package com.example.fastwork.business.splash

import android.os.CountDownTimer
import com.example.base.mvp.base.BasePresenter
import com.example.mvvm.bussiness.splash.SplashContract

class SplashPresenter : BasePresenter<SplashContract.View>(), SplashContract.Presenter {
    private lateinit var mCountDown: SplashCountDown


    override fun init() {
        if (mView == null)
            return
        mCountDown = SplashCountDown(4000, 1000)
        mCountDown.start()

    }

    override fun onSplashImageClick() {
    }

    override fun onJumpClick() {
        mCountDown.cancel()
        mCountDown.onFinish()
    }

    inner class SplashCountDown(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {

        override fun onFinish() {
            mView?.startHome()

        }

        override fun onTick(millisUntilFinished: Long) {
            mView?.setJumpText("跳过 ${millisUntilFinished / 1000}")
        }
    }
}