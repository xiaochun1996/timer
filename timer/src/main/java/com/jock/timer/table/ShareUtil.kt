package com.jock.timer.table

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.jock.timer.application.TimerApplication

/**
 * Description: SharePreferences 的存储
 * Author: lxc
 * Date: 2021/12/1 22:07
 */


fun Context.sharePreferences(name: String): SharedPreferences {
    return getSharedPreferences(name, AppCompatActivity.MODE_PRIVATE)
}

fun Long.isInit(): Boolean {
    return this != -1L
}

val timerShare = TimerApplication.getContext().sharePreferences("timer")

object TimerShare {
    private const val LAST_TIME = "last_time"
    private const val DEFAULT_TAG = "default_tag"
    private const val DEFAULT_TYPE = "default_type"
    private const val INIT = "init"

    var lastTime: Long
        get() = timerShare.getLong(LAST_TIME, -1)
        set(value) = timerShare.putLong(LAST_TIME, value)

    var init: Boolean
        get() = timerShare.getBoolean(INIT, false)
        set(value) = timerShare.putBoolean(INIT, value)

    var tagId: Long
        get() = timerShare.getLong(DEFAULT_TAG, -1)
        set(value) = timerShare.putLong(DEFAULT_TAG, value)

    var typeId: Long
        get() = timerShare.getLong(DEFAULT_TYPE, -1)
        set(value) = timerShare.putLong(DEFAULT_TYPE, value)
}

fun SharedPreferences.putString(key: String, value: String) {
    this.edit {
        putString(key, value)
    }
}

fun SharedPreferences.putLong(key: String, value: Long) {
    this.edit {
        putLong(key, value)
    }
}

fun SharedPreferences.putBoolean(key: String, value: Boolean) {
    this.edit {
        putBoolean(key, value)
    }
}

fun SharedPreferences.getString(key: String): String? {
    return this.getString(key, "")
}

