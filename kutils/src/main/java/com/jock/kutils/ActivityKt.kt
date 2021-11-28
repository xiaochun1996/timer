package com.jock.kutils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

/**
 * Description: Activity 扩展函数
 * Author: lxc
 * Date: 2021/11/28 14:12
 */


inline fun <reified T> AppCompatActivity.startActivity(params:HashMap<String,Any?>?=null){
    val intent = createIntent(this,T::class.java,params)
    this.startActivity(intent)
}

//@JvmStatic
fun <T> createIntent(ctx: Context, clazz: Class<out T>, params: HashMap<String,Any?>?): Intent {
    val intent = Intent(ctx, clazz)
    if (!params.isNullOrEmpty()) fillIntentArguments(intent, params)
    return intent
}

private fun fillIntentArguments(intent: Intent, params: HashMap<String,Any?>) {
    params.forEach {
        val value = it.value
        when (value) {
            null -> intent.putExtra(it.key, null as Serializable?)
            is Int -> intent.putExtra(it.key, value)
            is Long -> intent.putExtra(it.key, value)
            is CharSequence -> intent.putExtra(it.key, value)
            is String -> intent.putExtra(it.key, value)
            is Float -> intent.putExtra(it.key, value)
            is Double -> intent.putExtra(it.key, value)
            is Char -> intent.putExtra(it.key, value)
            is Short -> intent.putExtra(it.key, value)
            is Boolean -> intent.putExtra(it.key, value)
            is Serializable -> intent.putExtra(it.key, value)
            is Bundle -> intent.putExtra(it.key, value)
            is Parcelable -> intent.putExtra(it.key, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> intent.putExtra(it.key, value)
                value.isArrayOf<String>() -> intent.putExtra(it.key, value)
                value.isArrayOf<Parcelable>() -> intent.putExtra(it.key, value)
                else -> throw IntentException("Intent extra ${it.key} has wrong type ${value.javaClass.name}")
            }
            is IntArray -> intent.putExtra(it.key, value)
            is LongArray -> intent.putExtra(it.key, value)
            is FloatArray -> intent.putExtra(it.key, value)
            is DoubleArray -> intent.putExtra(it.key, value)
            is CharArray -> intent.putExtra(it.key, value)
            is ShortArray -> intent.putExtra(it.key, value)
            is BooleanArray -> intent.putExtra(it.key, value)
            else -> throw IntentException("Intent extra ${it.key} has wrong type ${value.javaClass.name}")
        }
        return@forEach
    }
}

open class IntentException(message: String = "") : RuntimeException(message)

inline fun <reified T:Serializable> AppCompatActivity.getSerializableExtra(name:String): T {
    return intent.getSerializableExtra(name) as T
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T:Serializable> AppCompatActivity.getSerializableListExtra(name:String): List<T> {
    return intent.getSerializableExtra(name) as List<T>
}


