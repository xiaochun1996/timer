package com.jock.base.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.jock.base.jetpack.ActivityBinding
import com.jock.base.jetpack.FragmentBinding
import java.security.AccessController.getContext

/**
 * Description: Activity 基类
 * Author: lxc
 * Date: 2021/11/28 12:31
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), ActivityTemplate<VB> {

    protected lateinit var mBinding: VB

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewBinding 绑定
        mBinding = viewBinding().invoke(layoutInflater) as VB
        setContentView(mBinding.root)
        mBinding.initView()
    }

    val mContext: Context
        get() = this
}

/**
 * Fragment 和 Activity 共有方法接口
 */
interface Template<VB> {

    /**
     * 初始化 View
     */
    fun VB.initView()

    /**
     * 初始化数据源
     */
    fun initData(){

    }

    /**
     * 初始化 Listener
     */
    fun initListener(){

    }
}

/**
 * Activity 特有抽象方法接口
 */
interface ActivityTemplate<VB> : Template<VB> {
    /**
     * ViewBinding 绑定
     */
    fun viewBinding(): ActivityBinding
}

/**
 * Fragment 特有抽象方法接口
 */
interface FragmentTemplate<VB> : Template<VB> {
    /**
     * ViewBinding 绑定
     */
    fun viewBinding():FragmentBinding
}