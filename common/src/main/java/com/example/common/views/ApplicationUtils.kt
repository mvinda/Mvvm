package com.example.common.views

import android.app.Application

/**
 *  create by WHD
 *   on 2022/3/8
 */
object ApplicationUtils {
    private lateinit var application: Application
    fun getApplication():Application {
        return application
    }

    fun setApplication(application:Application){
        this.application =application
    }
}
