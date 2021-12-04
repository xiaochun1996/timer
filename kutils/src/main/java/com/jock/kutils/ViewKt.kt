package com.jock.kutils

import android.text.SpannableStringBuilder
import android.view.View

/**
 * Description: 点击事件
 * Author: lxc
 * Date: 2021/12/1 11:48
 */

fun String.toEditable() = SpannableStringBuilder(this)

/**
 * 防快速点击
 */
fun View.clickListener(doubleTime:Int = 500, block:(View)->Unit){
    var lastClickTime = 0L
    this.setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if(currentTime - lastClickTime > doubleTime){
            block.invoke(it)
            lastClickTime = currentTime
        }
    }
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}