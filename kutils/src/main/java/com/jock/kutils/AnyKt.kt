package com.jock.kutils

/**
 * Description:
 * Author: lxc
 * Date: 2021/12/4 22:09
 */
val Any?.isNull:Boolean
    get() = this==null

val String?.isNotNullOrBlack:Boolean
    get() = !(this.isNull || this!!.isBlank())