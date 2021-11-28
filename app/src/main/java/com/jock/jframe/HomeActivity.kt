package com.jock.jframe

import com.jock.base.activity.BaseActivity
import com.jock.base.jetpack.ActivityBinding
import com.jock.jframe.bean.MenuEntity
import com.jock.jframe.databinding.ActivityMainBinding
import com.jock.jframe.jetpack.viewbinding.ViewBindingActivity
import com.jock.jframe.jetpack.viewbinding.ViewBindingCustomViewActivity
import com.jock.jframe.jetpack.viewbinding.ViewBindingFragmentActivity
import com.jock.jframe.menu.MenuActivity
import com.jock.kutils.startActivity

class HomeActivity : BaseActivity<ActivityMainBinding>() {
    override fun viewBinding(): ActivityBinding {
        return ActivityMainBinding::inflate
    }

    override fun initView() {
        mBinding.tvContent.text = "进入 demo"
        mBinding.tvContent.setOnClickListener {
//            val map = HashMap<String, Any?>()
//            map["menu"] = initMenu().child
//            startActivity<MenuActivity>(map)
            startActivity<ViewBindingCustomViewActivity>()
        }
    }

    private fun initMenu(): MenuEntity {

        val jetpackMenu = MenuEntity("Jetpack", jetpackMenu())

        return MenuEntity("Android 学习", mutableListOf(jetpackMenu))
    }

    /**
     * Jetpack 相关页面
     */
    private fun jetpackMenu(): MutableList<MenuEntity> {
        val jetpackMenu = mutableListOf<MenuEntity>()
        jetpackMenu.add(MenuEntity("ViewBinding 使用",viewBindingMenu()))
        return jetpackMenu
    }
    private fun viewBindingMenu(): MutableList<MenuEntity> {
        val viewBindingMenu = mutableListOf<MenuEntity>()
        viewBindingMenu.add(MenuEntity("ViewBinding Activity 中使用",null,true, ViewBindingActivity::class.java))
        viewBindingMenu.add(MenuEntity("ViewBinding Fragment 中使用",null,true, ViewBindingFragmentActivity::class.java))
        viewBindingMenu.add(MenuEntity("ViewBinding + 扩展函数 代码复用",null,true, ViewBindingCustomViewActivity::class.java))
        return viewBindingMenu
    }
}