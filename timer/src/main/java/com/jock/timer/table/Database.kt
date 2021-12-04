package com.jock.timer.table

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jock.timer.application.TimerApplication

/**
 * Description: 数据库
 * Author: lxc
 * Date: 2021/11/30 11:33
 */
@Database(
    entities = [TaskEntity::class, RecorderEntity::class, TagEntity::class, TypeEntity::class],
    views = [RecorderTaskEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun recorderDao(): RecorderDao

    abstract fun tagDao(): TagDao

    abstract fun typeDao(): TypeDao

    abstract fun recorderTaskDao(): RecorderTaskDao
}

val dateBase: AppDatabase
    get() = Room.databaseBuilder(
        TimerApplication.getContext(),
        AppDatabase::class.java,
        "database-name"
    ).build()

val taskDao: TaskDao
    get() = dateBase.taskDao()

val recorderDao: RecorderDao
    get() = dateBase.recorderDao()

val tagDao: TagDao
    get() = dateBase.tagDao()

val typeDao: TypeDao
    get() = dateBase.typeDao()

val recorderTaskDao: RecorderTaskDao
    get() = dateBase.recorderTaskDao()
