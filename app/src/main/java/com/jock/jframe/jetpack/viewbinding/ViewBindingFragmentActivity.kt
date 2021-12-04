package com.jock.jframe.jetpack.viewbinding

import com.jock.jframe.jetpack.viewbinding.base.BaseActivity
import com.jock.base.jetpack.ActivityBinding
import com.jock.jframe.databinding.JetpackActivityViewbindingFragmentDemoBinding

/**
 * Description: ViewBinding Fragment Demo
 * Author: lxc
 * Date: 2021/11/28 15:58
 */
class ViewBindingFragmentActivity: BaseActivity<JetpackActivityViewbindingFragmentDemoBinding>() {

    override fun initView(mBinding: JetpackActivityViewbindingFragmentDemoBinding) {

    }

    override fun viewBinding(): ActivityBinding {
        return JetpackActivityViewbindingFragmentDemoBinding::inflate
    }
}