package com.example.base.mvp.frame


interface IMVPBasePresenter {
    fun attachView(view: IMVPBaseView)

    fun detachView()
}
