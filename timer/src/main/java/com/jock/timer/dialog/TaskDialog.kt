package com.jock.timer.dialog

import com.jock.base.jetpack.DialogBinding
import com.jock.base.ui.BaseBottomDialog
import com.jock.kutils.DateUtil
import com.jock.kutils.toEditable
import com.jock.timer.databinding.DialogTaskDetailBinding
import com.jock.timer.table.RecorderTaskEntity

/**
 * Description: 任务对话框
 * Author: lxc
 * Date: 2021/12/2 19:53
 */
class TaskDialog(private val recorderTaskEntity: RecorderTaskEntity) : BaseBottomDialog<DialogTaskDetailBinding>() {
    override fun inflateView(): DialogBinding {
        return DialogTaskDetailBinding::inflate
    }

    override fun DialogTaskDetailBinding.initView() {
        etTaskName.text = recorderTaskEntity.taskName.toEditable()
        tvStartTime.text = DateUtil.getHourMinute(recorderTaskEntity.startTime)
        tvEndTime.text = DateUtil.getHourMinute(recorderTaskEntity.endTime)
        tvTag.text = recorderTaskEntity.tagName
        tvType.text = recorderTaskEntity.typeName
        tvType.setTextColor(recorderTaskEntity.backgroundColor.toInt())
    }
}