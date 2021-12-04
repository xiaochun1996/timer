package com.jock.timer.dialog

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jock.base.jetpack.DialogBinding
import com.jock.base.ui.BaseBottomDialog
import com.jock.kutils.clickListener
import com.jock.kutils.isNull
import com.jock.kutils.main
import com.jock.kutils.toast
import com.jock.timer.adapter.LabelAdapter
import com.jock.timer.databinding.DialogLabelLayoutBinding
import com.jock.timer.table.tagDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Description: 标签/类型
 * Author: lxc
 * Date: 2021/12/2 19:53
 */
class LabelDialog(private val title: String, private val listLabel: List<LabelEntity>) : BaseBottomDialog<DialogLabelLayoutBinding>() {

    private var mSelectEntity: LabelEntity?=null
    var itemClickListener:((LabelEntity)->Unit)?=null

    override fun inflateView(): DialogBinding {
        return DialogLabelLayoutBinding::inflate
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun DialogLabelLayoutBinding.initView() {
        tvTitle.text = title
        val typeAdapter = LabelAdapter()
        rvType.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)
        rvType.adapter = typeAdapter
        typeAdapter.setNewInstance(listLabel.toMutableList())

        val tagAdapter = LabelAdapter()
        rvTag.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)
        rvTag.adapter = tagAdapter

        typeAdapter.setOnItemClickListener { _, _, position ->
            // 重置
            typeAdapter.data.forEach { it.isSelect = false }
            typeAdapter.data[position].isSelect = true
            typeAdapter.notifyDataSetChanged()

            main.launch {
                var list = mutableListOf<LabelEntity>()
                withContext(Dispatchers.IO){
                    list = tagDao.doQueryByLimit("type_id",listLabel[position].id.toString())?.map {
                        LabelEntity(it.id,it.tagName?:"")
                    }!!.toMutableList()
                }
                if(list.isNotEmpty()) {
                    mSelectEntity = list[0]
                    list[0].isSelect = true
                }
                tagAdapter.setNewInstance(list)
            }
        }
        tagAdapter.setOnItemClickListener { _, _, position ->
            tagAdapter.data.forEach { it.isSelect = false }
            tagAdapter.data[position].isSelect = true
            mSelectEntity = tagAdapter.data[position]
            tagAdapter.notifyDataSetChanged()
        }

        tvConfirm.clickListener {
            if(mSelectEntity.isNull){
                context?.toast("请先选择标签")
            } else {
                itemClickListener?.invoke(mSelectEntity!!)
                dismiss()
            }
        }
    }
}

data class LabelEntity(val id:Long, val name:String, var isSelect:Boolean=false)