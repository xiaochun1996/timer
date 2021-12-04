package com.jock.kutils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by lxc on 2021/11/28 12:14.
 * Desc: Context 扩展函数
 */
fun Context.toast(content: CharSequence,duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,content,duration).show()
}

fun Context.toast(content:String,duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,content,duration).show()
}