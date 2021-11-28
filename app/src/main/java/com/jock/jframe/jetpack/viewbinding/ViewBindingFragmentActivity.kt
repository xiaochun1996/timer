package com.jock.jframe.jetpack.viewbinding

import androidx.databinding.DataBindingUtil.getBinding
import com.jock.base.activity.BaseActivity
import com.jock.base.jetpack.ActivityBinding
import com.jock.jframe.databinding.JetpackActivityViewbindingFragmentDemoBinding

/**
 * Description: ViewBinding Fragment Demo
 * Author: lxc
 * Date: 2021/11/28 15:58
 */
class ViewBindingFragmentActivity: BaseActivity<JetpackActivityViewbindingFragmentDemoBinding>() {

    override fun initView() {

    }

    override fun viewBinding(): ActivityBinding {
        return JetpackActivityViewbindingFragmentDemoBinding::inflate
    }
}