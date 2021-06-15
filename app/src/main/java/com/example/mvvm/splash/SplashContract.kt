package com.example.mvvm.splash

import com.example.base.mvp.frame.IMVPBaseView

interface SplashContract {
    interface View : IMVPBaseView {
        fun setSplashImage(image: String?)
        fun setSplashImageVisibility(visibility: Int)
        fun setJumpText(text: String)
        fun setJumpVisibility(visibility: Int)
        fun startHome()
    }

    interface Presenter {
        fun init()
        fun onSplashImageClick()
        fun onJumpClick()
    }
}

