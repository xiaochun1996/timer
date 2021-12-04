package com.jock.timer.activity

import android.graphics.Color
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.jock.base.activity.BaseActivity
import com.jock.base.jetpack.ActivityBinding
import com.jock.kutils.clickListener
import com.jock.kutils.setSyncClickListener
import com.jock.kutils.startActivity
import com.jock.kutils.toast
import com.jock.timer.databinding.RoomActivityTestOperationBinding
import com.jock.timer.table.recorderDao


/**
 * Description: 主页面
 * Author: lxc
 * Date: 2021/11/30 09:21
 */
class MainActivity : BaseActivity<RoomActivityTestOperationBinding>() {

    val dao = recorderDao

    override fun onResume() {
        super.onResume()
        startActivity<HomeActivity>()
    }

    override fun RoomActivityTestOperationBinding.initView() {
        btAdd.clickListener {
            ColorPickerDialogBuilder
                .with(mContext)
                .setTitle("Choose color")
//                .initialColor(currentBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener { selectedColor ->

                }
                .setPositiveButton("确定") { dialog, selectedColor, allColors ->
                    btUpdate.setTextColor(selectedColor)
                }
                .setNegativeButton(
                    "取消"
                ) { dialog, which -> }
                .build()
                .show()
        }
        btDelete.clickListener {

        }
        btUpdate.clickListener {

        }
        btQuery.clickListener {

        }
    }


    override fun viewBinding(): ActivityBinding {
        return RoomActivityTestOperationBinding::inflate
    }
}