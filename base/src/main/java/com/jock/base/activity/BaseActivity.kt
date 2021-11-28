package com.jock.base.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.jock.base.jetpack.ActivityBinding
import com.jock.base.jetpack.FragmentBinding

/**
 * Description: Activity 基类
 * Author: lxc
 * Date: 2021/11/28 12:31
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), ActivityTemplate {

    protected lateinit var mBinding: VB

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewBinding 绑定
        mBinding = viewBinding().invoke(layoutInflater) as VB
        setContentView(mBinding.root)
        initView()
    }


}

/**
 * Fragment 和 Activity 共有方法接口
 */
interface Template {

    /**
     * 初始化 View
     */
    fun initView()

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
interface ActivityTemplate : Template {
    /**
     * ViewBinding 绑定
     */
    fun viewBinding(): ActivityBinding
}

/**
 * Fragment 特有抽象方法接口
 */
interface FragmentTemplate : Template {
    /**
     * ViewBinding 绑定
     */
    fun viewBinding():FragmentBinding
}