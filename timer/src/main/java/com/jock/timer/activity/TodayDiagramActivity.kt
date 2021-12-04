package com.jock.timer.activity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jock.base.activity.BaseActivity
import com.jock.base.jetpack.ActivityBinding
import com.jock.kutils.DateUtil
import com.jock.kutils.main
import com.jock.kutils.async2
import com.jock.timer.adapter.TaskAdapter
import com.jock.timer.databinding.ActivityTodayBinding
import com.jock.timer.table.RecorderTaskEntity
import com.jock.timer.table.recorderTaskDao
import kotlinx.coroutines.launch

/**
 * Description: 今日视图
 * Author: lxc
 * Date: 2021/12/2 15:27
 */
class TodayDiagramActivity: BaseActivity<ActivityTodayBinding>() {

    override fun ActivityTodayBinding.initView() {
        val adapter = TaskAdapter()
        rvTodayRecorder.layoutManager = LinearLayoutManager(this@TodayDiagramActivity, RecyclerView.VERTICAL,false)
        rvTodayRecorder.adapter = adapter
        adapter.refreshAdapter = {
            getTodayData(adapter)

        }
        getTodayData(adapter)
    }

    private fun getTodayData(adapter: TaskAdapter) {
        async2.launch {
            var todayRecorder = mutableListOf<RecorderTaskEntity>()
            async2.launch {
                val range = DateUtil.getTodayTime()
                todayRecorder = recorderTaskDao.findRangeRecorder(
                    range[0].time,
                    range[1].time
                ) as MutableList<RecorderTaskEntity>
            }.join()
            main.launch {
                adapter.setNewInstance(todayRecorder)
                adapter.setOnItemClickListener { _, _, position ->
                    adapter.data.forEach { it.isSelect = false }
                    adapter.data[position].isSelect = true
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun viewBinding(): ActivityBinding {
        return ActivityTodayBinding::inflate
    }
}