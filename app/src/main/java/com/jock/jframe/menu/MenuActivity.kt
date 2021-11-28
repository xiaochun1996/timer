package com.jock.jframe.menu

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jock.base.activity.BaseActivity
import com.jock.base.jetpack.ActivityBinding
import com.jock.jframe.bean.MenuEntity
import com.jock.jframe.databinding.ActivityCommonListBinding
import com.jock.jframe.databinding.ActivityMainBinding
import com.jock.kutils.getSerializableListExtra
import com.jock.kutils.startActivity

/**
 * Description: Jetpack Demo 类
 * Author: lxc
 * Date: 2021/11/28 13:06
 */
class MenuActivity: BaseActivity<ActivityCommonListBinding>() {

    override fun initView(mBinding: ActivityCommonListBinding) {
        val menuList = getSerializableListExtra<MenuEntity>("menu")
        val container = mBinding.rvContainer
        val adapter = MenuAdapter()
        adapter.setOnItemClickListener { adapter, view, position ->
            val menu = adapter.data[position] as MenuEntity
            if(menu.page){
                val intent =  Intent(this, menu.path)
                startActivity(intent)
            } else {
                val map = HashMap<String, Any?>()
                map["menu"] = menu.child
                startActivity<MenuActivity>(map)
            }
        }
        container.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        container.adapter = adapter
        adapter.setNewInstance(menuList.toMutableList())
    }

    override fun viewBinding(): ActivityBinding {
        return ActivityCommonListBinding::inflate
    }
}

