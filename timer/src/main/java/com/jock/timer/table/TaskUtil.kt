package com.jock.timer.table

import android.widget.Toast
import com.jock.kutils.async2
import com.jock.kutils.isNull
import com.jock.kutils.main
import com.jock.timer.application.TimerApplication
import kotlinx.coroutines.launch

/**
 * Description: 记录相关工具类
 * Author: lxc
 * Date: 2021/12/4 22:51
 */

/**
 * 添加记录
 */
fun addTask(recorderEntity: TaskRecorderEntity, afterAdd: (() -> Unit)? = null) {
    if (!checkParams(recorderEntity)) {
        return
    }
    async2.launch {
        async2.launch {
            val tagId = if (recorderEntity.tagId.isNull) {
                TimerShare.tagId
            } else {
                recorderEntity.tagId
            }
            val task = TaskEntity(recorderEntity.taskContent, tagId)
            val taskId = taskDao.insert(task)
            val recorder = RecorderEntity(recorderEntity.startTime, recorderEntity.endTime, taskId)
            recorderDao.insert(recorder)
        }.join()
        // 等待异步执行完成后刷新 UI
        main.launch {
            afterAdd?.invoke()
        }
    }
}

/**
 * 记录添加参数检查
 */
private fun checkParams(recorderEntity: TaskRecorderEntity): Boolean {
    if (recorderEntity.endTime > System.currentTimeMillis()) {
        toast("开始时间不能大于当前时间")
        return false
    }
    if (recorderEntity.endTime < recorderEntity.startTime) {
        toast("开始时间不能大于结束时间")
        return false
    }
    if (recorderEntity.taskContent.isBlank()) {
        toast("任务内容不能为空")
        return false
    }
    if (recorderEntity.startTime == 0L) {
        toast("开始时间不能为空")
        return false
    }
    return true
}

fun toast(content: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(TimerApplication.getContext(), content, duration).show()
}

data class TaskRecorderEntity(
    var startTime: Long,
    var endTime: Long,
    var taskContent: String,
    var tagId: Long? = null
)