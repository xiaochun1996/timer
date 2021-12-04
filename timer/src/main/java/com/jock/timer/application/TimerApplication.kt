package com.jock.timer.application

import android.app.Application
import android.content.Context
import com.jock.base.application.BaseApplication

/**
 * Description: Application
 * Author: lxc
 * Date: 2021/11/30 14:44
 */
class TimerApplication: BaseApplication() {

    companion object {
        var mContext: Application? = null
        fun getContext():Context{
            return mContext!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }
}