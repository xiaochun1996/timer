package com.jock.jframe.jetpack.viewbinding.fragment

import com.jock.base.activity.BaseFragment
import com.jock.base.jetpack.FragmentBinding
import com.jock.jframe.databinding.JetpackActivityViewbindingActivityDemoBinding

/**
 * Description: ViewBinding Fragment Demo
 * Author: lxc
 * Date: 2021/11/28 16:06
 */
class ViewBindingFragment: BaseFragment<JetpackActivityViewbindingActivityDemoBinding>() {
    override fun initView(mBinding: JetpackActivityViewbindingActivityDemoBinding) {
        mBinding.tvContent.text = "Hello ViewBinding Fragment!"
    }

    override fun viewBinding(): FragmentBinding {
        return JetpackActivityViewbindingActivityDemoBinding::inflate
    }
}