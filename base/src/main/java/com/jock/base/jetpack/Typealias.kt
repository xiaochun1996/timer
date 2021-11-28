package com.jock.base.jetpack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * Description: 别名
 * Author: lxc
 * Date: 2021/11/28 12:42
 */

typealias ActivityBinding = (LayoutInflater) -> ViewBinding

typealias FragmentBinding = (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding
