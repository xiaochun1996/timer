package com.jock.jframe.jetpack.viewbinding

import com.jock.base.activity.BaseActivity
import com.jock.base.jetpack.ActivityBinding
import com.jock.jframe.databinding.JetpackActivityViewbindingCustomViewDemoBinding
import com.jock.jframe.databinding.LayoutViewbindingCustomViewBinding

/**
 * Description: 类自定义控件 Demo
 * Author: lxc
 * Date: 2021/11/28 15:58
 */
class ViewBindingCustomViewActivity :
    BaseActivity<JetpackActivityViewbindingCustomViewDemoBinding>() {

    override fun initView(mBinding: JetpackActivityViewbindingCustomViewDemoBinding) {
        mBinding.itemOne.bindView(ChapterBean("我是标题一","我是副标题一","我是内容一"))
        mBinding.itemTwo.bindView(ChapterBean("我是标题二","我是副标题二","我是内容二"))
        mBinding.itemThree.bindView(ChapterBean("我是标题三","我是副标题三","我是内容三"))
    }

    override fun viewBinding(): ActivityBinding {
        return JetpackActivityViewbindingCustomViewDemoBinding::inflate
    }

    data class ChapterBean(val title: String, val subtitle: String, val content: String)

    private fun LayoutViewbindingCustomViewBinding.bindView(chapterBean: ChapterBean) {
        this.tvTitle.text = chapterBean.title
        this.tvSubTitle.text = chapterBean.subtitle
        this.tvContent.text = chapterBean.content
    }
}