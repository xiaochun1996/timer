package com.jock.kutils

import android.view.KeyEvent
import android.view.View
import android.widget.EditText

/**
 * Description: 扩展方法
 * Author: lxc
 * Date: 2021/12/4 22:12
 */
fun EditText.setOnEnterListener(block: (View) -> Unit) {
    this.setOnEditorActionListener { _, _, event ->
        // 会触发两次
        if (event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
            println("${event.keyCode} ${DateUtil.getHourMinute()}")
            block.invoke(this)
            true
        } else {
            false
        }
    }
}
