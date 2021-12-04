package com.jock.timer.activity

import com.jock.base.activity.BaseActivity
import com.jock.base.jetpack.ActivityBinding
import com.jock.kutils.clickListener
import com.jock.kutils.main
import com.jock.timer.databinding.ActivityTaskAddBinding
import com.jock.timer.dialog.LabelEntity
import com.jock.timer.dialog.LabelDialog
import com.jock.timer.table.typeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Description: 任务添加 Activity
 * Author: lxc
 * Date: 2021/12/3 22:23
 */
class TaskActivity:BaseActivity<ActivityTaskAddBinding>() {

    /**
     * 选中的类型
     */
    var tagEntity:LabelEntity?=null

    override fun ActivityTaskAddBinding.initView() {
        tvType.clickListener {
            var list = mutableListOf<LabelEntity>()
            main.launch {
                withContext(Dispatchers.IO){
                    list = typeDao.findAll()?.map {
                        LabelEntity(it.id,it.typeName?:"")
                    }!!.toMutableList()
                }
                val labelDialog = LabelDialog("类别", list)
                labelDialog.show(supportFragmentManager,"")
                labelDialog.itemClickListener = {
                    tagEntity = it
                    tvType.text = it.name
                }
            }
        }
        tvCategory.clickListener {

        }
    }

    override fun viewBinding(): ActivityBinding {
        return ActivityTaskAddBinding::inflate
    }
}


