package com.jock.timer.adapter

import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jock.kutils.*
import com.jock.timer.R
import com.jock.timer.table.*
import kotlinx.coroutines.launch

/**
 * Description: 菜单 Adapter
 * Author: lxc
 * Date: 2021/11/28 13:27
 */
class TaskAdapter : BaseQuickAdapter<RecorderTaskEntity, BaseViewHolder>(R.layout.room_adapter_recorder) {

    var refreshAdapter:(()->Unit)?=null

    override fun convert(holder: BaseViewHolder, item: RecorderTaskEntity) {
        val tvContent = holder.getView<TextView>(R.id.tv_content)
        val tvStartTime = holder.getView<TextView>(R.id.tv_start_time)
        val tvEndTime = holder.getView<TextView>(R.id.tv_end_time)
        val tvCommit = holder.getView<TextView>(R.id.tv_time_sum)
        val tvTag = holder.getView<TextView>(R.id.tv_tag)
        val tvDelete = holder.getView<TextView>(R.id.tv_delete)
        val tvSubmit = holder.getView<TextView>(R.id.tv_submit)

        val etContent = holder.getView<EditText>(R.id.et_content)
        val llEdit = holder.getView<View>(R.id.ll_edit)
        tvContent.text = item.taskName
        etContent.text = item.taskName.toEditable()


        tvStartTime.text = "开始：${item.startTime.toHourMinute()}"
        tvEndTime.text = "结束：${item.endTime.toHourMinute()}"
        tvCommit.text = ((item.endTime-item.startTime)/1000/60).toString()
        tvTag.text = "标签：${item.tagName}"

        // 删除
        tvDelete.clickListener {
            async2.launch {
                taskDao.deleteById(item.taskId)
                recorderDao.deleteById(item.recorderId)
                refreshAdapter?.invoke()
            }
        }

        // 修改
        tvSubmit.clickListener {
            async2.launch {
                taskDao.deleteById(item.taskId)
                val defaultTagId = TimerShare.tagId
                val task = TaskEntity(etContent.text.toString(),defaultTagId)
                val taskId = taskDao.insert(task)
                recorderDao.update(RecorderEntity(item.startTime,item.endTime,taskId,item.recorderId))
                refreshAdapter?.invoke()
            }
        }

        if (item.isSelect){
            tvContent.gone()
            etContent.visible()
            llEdit.visible()
        } else {
            etContent.gone()
            tvContent.visible()
            llEdit.gone()
        }
    }
}
