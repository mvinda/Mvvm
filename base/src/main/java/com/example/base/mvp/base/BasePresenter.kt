package com.example.opensdktesttool.component.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BasePresenter<T : IBaseView> : IBasePresenter {

    var mView: T? = null
    var mDisposables: CompositeDisposable? = null


    override fun attachView(view: IBaseView) {
        if (view != null)
            mView = view as T
    }

    protected fun addSubscribe(disposable: Disposable?) {
        if (mDisposables == null)
            mDisposables = CompositeDisposable()
        if (disposable != null) {
            mDisposables?.add(disposable)
        }
    }

    protected fun cancelSubscribe(disposable: Disposable?) {
        if (disposable == null) return
        if (mDisposables != null) {
            mDisposables?.remove(disposable)
        }
        disposable.dispose()
    }


    override fun detachView() {
        if (mDisposables != null) {
            mDisposables?.clear()
        }
        mView = null
    }


}