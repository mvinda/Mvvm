package com.example.base.mvp.frame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder

abstract class MVPBaseFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var mUnbinder: Unbinder
    protected var mIsInitData: Boolean = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getLayoutId() > 0) {
            mView = inflater.inflate(getLayoutId(), container, false)
            mUnbinder = ButterKnife.bind(this, mView)
            return mView
        } else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    abstract fun initData()


    abstract fun getLayoutId(): Int

    override fun onDestroyView() {
        super.onDestroyView()
        mUnbinder?.unbind()
    }

}
