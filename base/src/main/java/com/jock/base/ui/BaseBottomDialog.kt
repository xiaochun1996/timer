package com.jock.base.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jock.base.R
import com.jock.base.jetpack.DialogBinding

/**
 * Created by lxc on 5/13/21 7:36 PM.
 * Desc: 底部 Dialog 抽取
 */
abstract class BaseBottomDialog<VB: ViewBinding> : BottomSheetDialogFragment() {

    /**
     * Dialog 布局
     */
    abstract fun inflateView():DialogBinding

    /**
     * 初始化视图
     */
    abstract fun VB.initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val viewBinding = inflateView().invoke(layoutInflater) as VB
        dialog.setContentView(viewBinding.root)
        viewBinding.initView()
        return dialog
    }

    protected fun isFixedHeight(): Boolean {
        return false
    }
}