package com.jock.jframe.menu

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jock.jframe.R
import com.jock.jframe.bean.MenuEntity

/**
 * Description: 菜单 Adapter
 * Author: lxc
 * Date: 2021/11/28 13:27
 */
class MenuAdapter : BaseQuickAdapter<MenuEntity, BaseViewHolder>(R.layout.adapter_item_menu) {

    override fun convert(holder: BaseViewHolder, item: MenuEntity) {
        holder.setText(R.id.tv_title,item.title)
    }
}