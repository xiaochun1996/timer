package com.jock.timer.table

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.jock.base.jetpack.room.BaseDao
import com.jock.base.jetpack.room.BaseViewDao

/**
 * Description: 任务相关操作
 * Author: lxc
 * Date: 2021/11/30 11:16
 */
@Dao
abstract class  TaskDao: BaseDao<TaskEntity>()
@Dao
abstract class  RecorderDao:BaseDao<RecorderEntity>()
@Dao
abstract class TagDao:BaseDao<TagEntity>()
@Dao
abstract class  TypeDao:BaseDao<TypeEntity>()
@Dao
abstract class  RecorderTaskDao: BaseViewDao<RecorderTaskEntity>(){

    fun findRangeRecorder(begin:Long, end:Long): List<RecorderTaskEntity>? {
        val query = SimpleSQLiteQuery("select * from $tableName WHERE startTime <= $end AND $begin <= endTime")
        return doFindAll(query)
    }
}