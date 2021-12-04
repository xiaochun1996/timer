package com.jock.kutils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Description:
 * Author: lxc
 * Date: 2021/12/1 13:44
 */
object DateUtil {

    private const val FORMAT_HH_MM = "HH:mm"
    private const val FORMAT_HH_MM_SS = "HH:mm:ss"
    private const val FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
    private const val FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm"

    fun getHourMinute(date:Long): String {
        val dateFormat = SimpleDateFormat(FORMAT_HH_MM,Locale.getDefault())
        return dateFormat.format(Date(date))
    }

    fun getHourMinute(date:Date = Date()): String {
        val dateFormat = SimpleDateFormat(FORMAT_HH_MM,Locale.getDefault())
        return dateFormat.format(date)
    }

    fun getHourMinuteLong(string:String): Long {
        val dateFormat = SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM,Locale.getDefault())
        return dateFormat.parse("${getToday()} $string").time
    }

    fun getHourMinuteLong(date:Date = Date()): Long {
        val dateFormat = SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM,Locale.getDefault())
        val format = dateFormat.format(date)
        return dateFormat.parse("$format").time
    }

    private fun getToday(date:Date = Date()): String {
        val dateFormat = SimpleDateFormat(FORMAT_YYYY_MM_DD,Locale.getDefault())
        return dateFormat.format(date)
    }

    fun getTodayTime(): Array<Date> {
        val dateArr = Array(2){Date()}
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        dateArr[0]=calendar.time
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        dateArr[1]=calendar.time
        return dateArr
    }
}

fun Long.toHourMinute(): String {
    return DateUtil.getHourMinute(this)
}

fun String.toHourMinute(): Long {
    return DateUtil.getHourMinuteLong(this)
}