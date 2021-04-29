package com.example.base.mvp.base


interface IBasePresenter {
    fun attachView(view: IBaseView)

    fun detachView()
}
