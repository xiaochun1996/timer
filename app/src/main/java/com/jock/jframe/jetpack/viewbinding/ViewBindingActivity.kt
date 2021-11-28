package com.jock.jframe.jetpack.viewbinding

import com.jock.base.activity.BaseActivity
import com.jock.base.jetpack.ActivityBinding
import com.jock.jframe.databinding.JetpackActivityViewbindingActivityDemoBinding

/**
 * Description: ViewBinding Demo
 * Author: lxc
 * Date: 2021/11/28 15:58
 */
class ViewBindingActivity: BaseActivity<JetpackActivityViewbindingActivityDemoBinding>() {

    override fun initView(mBinding: JetpackActivityViewbindingActivityDemoBinding) {
        mBinding.tvContent.text = "Hello ViewBinding!"
    }

    override fun viewBinding(): ActivityBinding {
        return JetpackActivityViewbindingActivityDemoBinding::inflate
    }
}