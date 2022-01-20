package com.example.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.account.R
import com.example.account.databinding.FragmentAccountBinding
//一级路由不同module 不能一样
@Route(path = "/account/account_fragment")
class AccountFragment : Fragment() {
    var mBinding: FragmentAccountBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ARouter.getInstance().inject(this)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        mBinding?.homeTxtTitle?.text = getString(R.string.menu_account)
        return mBinding?.root
    }
}