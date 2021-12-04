package com.jock.timer.activity

import android.text.SpannableString
import android.util.Log
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.github.gzuliyujiang.wheelpicker.TimePicker
import com.github.gzuliyujiang.wheelpicker.annotation.TimeMode
import com.jock.base.activity.BaseActivity
import com.jock.base.jetpack.ActivityBinding
import com.jock.kutils.*
import com.jock.timer.databinding.ActivityMainBinding
import com.jock.timer.table.*
import kotlinx.coroutines.*

/**
 * Description: 首页
 * Author: lxc
 * Date: 2021/12/1 11:32
 */
class HomeActivity : BaseActivity<ActivityMainBinding>() {

    var startTime: Long = 0L
    var endTime: Long = 0L

    override fun ActivityMainBinding.initView() {
        tvProStartTime.text = ""
        tvProStartTime.text = ""
        tvProEvent.setText(SpannableString(""))

        if (!TimerShare.init) {
            async2.launch {
                val typeList = arrayOf("未知", "投资", "固定", "娱乐", "浪费").map {
                    TypeEntity(it)
                }
                val typeIds = typeDao.insertAll(typeList)
                TimerShare.typeId = typeIds[0]
                val allTags = mutableListOf<TagEntity>()
                val defaultTag = arrayOf("默认").map {
                    TagEntity(it, typeIds[0])
                }
                val investmentTag = arrayOf("技术", "工具", "心理").map {
                    TagEntity(it, typeIds[1])
                }
                val fixedTag = arrayOf("工作", "洗漱", "吃饭").map {
                    TagEntity(it, typeIds[2])
                }
                val entertainmentTag = arrayOf("听歌", "视频", "游玩").map {
                    TagEntity(it, typeIds[3])
                }
                val wasteTag = arrayOf("百度", "新闻").map {
                    TagEntity(it, typeIds[4])
                }
                allTags.addAll(defaultTag)
                allTags.addAll(investmentTag)
                allTags.addAll(fixedTag)
                allTags.addAll(entertainmentTag)
                allTags.addAll(wasteTag)
                val tagIds = tagDao.insertAll(allTags)
                TimerShare.tagId = tagIds[0]
            }
            TimerShare.init = true
        }
        tvInsert.clickListener {

        }

        tvAddTask.clickListener {
            startActivity<TaskActivity>()
        }

        tvTimeDiagram.clickListener {
            startActivity<TodayDiagramActivity>()
        }

        // Adapter
        rvTaskLabel

        // 当前条
        val lastTime = TimerShare.lastTime
        if(lastTime.isInit()){
            tvStartTime.text = lastTime.toHourMinute()
            startTime = DateUtil.getHourMinuteLong("$lastTime")
        }
        tvEndTime.text = ""
        tvEvent.setText(SpannableString(""))
        val tvEvent = tvEvent
        tvEvent.setOnEnterListener {
            if (tvEvent.text.isNotEmpty()) {
                addTask()
            }
        }

        // 单击最近，长按修改
        tvStartTime.setOnLongClickListener {
            timePicker(tvStartTime) {
                startTime = it
            }
            true
        }
        tvStartTime.setOnClickListener {
            startTime = if (TimerShare.lastTime.isInit()) {
                lastTime
            } else {
                DateUtil.getHourMinuteLong()
            }
            tvStartTime.text = DateUtil.getHourMinute(startTime)
        }
        tvStartTime.watchTime()

        // 单击当前时间，长按修改
        tvEndTime.setOnLongClickListener {
            timePicker(tvEndTime) {
                endTime = DateUtil.getHourMinuteLong()
            }
            true
        }
        tvEndTime.setOnClickListener {
            endTime = DateUtil.getHourMinuteLong()
            tvEndTime.text = DateUtil.getHourMinute(endTime)
        }
        tvEndTime.watchTime()


        // 底部按钮
        tvHabit.setSyncClickListener {

        }
        tvToday.setSyncClickListener {

        }
        tvCommit.clickListener {
            addTask()
        }
    }


    private fun TextView.watchTime() {
        val textView = this
        textView.doAfterTextChanged {
            if (it.toString().isBlank()) {
                return@doAfterTextChanged
            }
            async2.launch {
                val todayRecorder =
                    recorderTaskDao.doQueryByOrder("endTime") as MutableList<RecorderTaskEntity>
                val time = DateUtil.getHourMinuteLong(textView.text.toString())
                Log.e("time", "$time $startTime $endTime")
                todayRecorder.forEach {
                    if (time in (it.startTime + 1) until it.endTime) {
                        runOnUiThread {
                            toast("此时间段已被添加")
                            textView.text = ""
                        }
                    }
                }
            }
        }
    }

    /**
     * 添加任务
     */
    private fun ActivityMainBinding.addTask() {
        val taskContent = tvEvent.text.toString()
        val task = TaskRecorderEntity(
            tvStartTime.text.toString().toHourMinute(),
            tvEndTime.text.toString().toHourMinute(),
            taskContent
        )
        addTask(task) {
            toast("插入完成")
            tvEvent.text = "".toEditable()
            TimerShare.lastTime = tvEndTime.text.toString().toHourMinute()
            val startText = TimerShare.lastTime.toHourMinute()
            tvStartTime.text = startText
            startTime = DateUtil.getHourMinuteLong()
            tvEndTime.text = ""
        }
    }

    /**
     * 设置时间选择
     */
    private fun timePicker(callBack: TextView, block: (Long) -> Unit) {
        val datePicker = TimePicker(this)
        val layout = datePicker.wheelLayout
        layout.setTimeMode(TimeMode.HOUR_24_NO_SECOND)
        layout.setTimeLabel("时", "分", "秒")
        datePicker.setOnTimePickedListener { hour, minute, _ ->
            callBack.text = "$hour:$minute"
            val date = DateUtil.getHourMinuteLong("${hour}:${minute}")
            block.invoke(date)
            println(date)
        }
        datePicker.show()
    }

    override fun viewBinding(): ActivityBinding {
        return ActivityMainBinding::inflate
    }
}

