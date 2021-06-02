package com.example.opensdktesttool.component.base


interface IBasePresenter {
    fun attachView(view: IBaseView)

    fun detachView()
}
