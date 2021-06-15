package com.example.mvvm.splash

import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.bumptech.glide.Glide
import com.example.base.mvp.frame.MVPBaseActivity
import com.example.mvvm.MainActivity
import com.example.mvvm.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : MVPBaseActivity<SplashPresenter>(), SplashContract.View {


    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        if (mPresenter == null) {
            mPresenter = SplashPresenter()
        }

        splashImage.setOnClickListener { mPresenter?.onSplashImageClick() }
        splashJump.setOnClickListener { mPresenter?.onJumpClick() }

    }

    override fun initData() {
        mPresenter?.attachView(this)
        mPresenter?.init()
    }


    override fun setSplashImage(image: String?) {
        val alphaAnimation = AlphaAnimation(0f, 1f)
        alphaAnimation.fillAfter = true
        alphaAnimation.duration = 500
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {
                Glide.with(this@SplashActivity)
                        .load(image)
                        .into(splashImage)
            }

        })


//        val httpUtils = HttpUtils()
//        val createApi = httpUtils.createApi(HttpApi::class.java).wangzhanzuo().compose(HttpTransformer<BaseDataModel<MarketWindowModel>, MarketWindowModel>());

    }

    override fun setSplashImageVisibility(visibility: Int) {
        splashImage.visibility = visibility
    }

    override fun setJumpText(text: String) {
        splashJump.text = text
    }

    override fun setJumpVisibility(visibility: Int) {
        splashJump.visibility = visibility
    }

    override fun startHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun hideDialog() {
        //nothing
    }

    override fun showDialog() {
        //nothing
    }


}