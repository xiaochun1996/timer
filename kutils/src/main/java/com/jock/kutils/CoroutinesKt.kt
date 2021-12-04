package com.jock.kutils

import android.view.View
import kotlinx.coroutines.*

/**
 * Description: View 扩展
 * Author: lxc
 * Date: 2021/12/1 11:37
 */

/**
 * 设置异步点击
 */
fun View.setSyncClickListener(block:(View)->Unit){
    this.setOnClickListener {
        async2.launch {
            block.invoke(it)
        }
    }
}

/**
 * 运行在子线程
 */
fun runOnSyncThread(block: (CoroutineScope) -> Unit): Job {
    return CoroutineScope(Dispatchers.IO).launch {
        block.invoke(this)
    }
}

val async2 = CoroutineScope(Dispatchers.IO)

val main = CoroutineScope(Dispatchers.Main)


/**
 * 运行在主线程
 */
fun runOnUiThread(block: (CoroutineScope) -> Unit){
    CoroutineScope(Dispatchers.IO).launch {
        block.invoke(this)
    }
}