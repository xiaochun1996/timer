package com.jock.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.jock.base.jetpack.FragmentBinding
import java.lang.Exception

/**
 * Description: 基类 Fragment
 * Author: lxc
 * Date: 2021/11/28 12:43
 */
abstract class BaseFragment<T: ViewBinding>:Fragment(),FragmentTemplate{

    private var mBinding: T?=null

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = viewBinding().invoke(inflater,container,false) as T
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding?.let{
            initView()
        }
    }

    /**
     * 获取 ViewBinding
     */
    fun getBinding(): T {
        if(mBinding==null){
            throw Exception("ViewBinding is Null.")
        }
        return mBinding!!
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}
