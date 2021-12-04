package com.jock.timer.adapter

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jock.timer.R
import com.jock.timer.dialog.LabelEntity

/**
 * Description: 标签 Adapter
 * Author: lxc
 * Date: 2021/11/28 13:27
 */
class LabelAdapter : BaseQuickAdapter<LabelEntity, BaseViewHolder>(R.layout.room_adapter_item) {

    override fun convert(holder: BaseViewHolder, item: LabelEntity) {
        val tvContent = holder.getView<TextView>(R.id.tv_content)
        if(item.isSelect){
            tvContent.setBackgroundColor(ContextCompat.getColor(context,R.color.purple_200))
        } else {
            tvContent.setBackgroundColor(ContextCompat.getColor(context,R.color.white))
        }
        tvContent.text = item.name
    }
}