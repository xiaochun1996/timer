package com.jock.timer.table

import androidx.room.*

/**
 * Description: 任务
 * Author: lxc
 * Date: 2021/11/30 10:30
 */
@Entity(indices = [Index(value = ["task_name"], unique = true)])
data class TaskEntity(
    @ColumnInfo(name = "task_name") var name: String?,
    @ColumnInfo(name = "tag_id") var tagId: Long? = 1,
    @ColumnInfo(name = "parent_id") var parentId: Long? = 0,
    @ColumnInfo(name = "level") var level: Int? = 0,
    @ColumnInfo(name = "task_category") val category: Int? = 0,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)

/**
 * Description: 记录
 * Author: lxc
 * Date: 2021/11/30 10:30
 */
@Entity
data class RecorderEntity(
    @ColumnInfo(name = "start_time") var startTime: Long,
    @ColumnInfo(name = "end_time") var endTime: Long,
    @ColumnInfo(name = "task_id") var taskId: Long,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)

/**
 * Description: 标签
 * Author: lxc
 * Date: 2021/11/30 10:30
 */
@Entity(indices = [Index(value = ["tag_name"], unique = true)])
data class TagEntity(
    @ColumnInfo(name = "tag_name") var tagName: String?,
    @ColumnInfo(name = "type_id") var typeId: Long? = 1,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)

/**
 * Description: 种类
 * Author: lxc
 * Date: 2021/11/30 10:30
 */
@Entity(indices = [Index(value = ["type_name"], unique = true)])
data class TypeEntity(
    @ColumnInfo(name = "type_name") var typeName: String?,
    @ColumnInfo(name = "background_color") var color: String? = "-16777216",
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)

/**
 * 任务视图
 */
@DatabaseView(
    "SELECT RecorderEntity.id AS recorderId,RecorderEntity.start_time AS startTime,RecorderEntity.end_time AS endTime," +
            "TaskEntity.id AS taskId,TaskEntity.task_name AS taskName ," +
            "TagEntity.id AS tagId,TagEntity.tag_name AS tagName, " +
            "TypeEntity.id AS typeId,TypeEntity.type_name AS typeName,TypeEntity.background_color AS backgroundColor " +
            "FROM RecorderEntity ,TaskEntity ,TagEntity,TypeEntity " +
            "WHERE RecorderEntity.task_id = TaskEntity.id and TaskEntity.tag_id = TagEntity.id and TypeEntity.id = TagEntity.type_id"
)
data class RecorderTaskEntity(
    val recorderId: Long,
    val startTime: Long,
    val endTime: Long,
    val taskId: Long,
    val taskName: String,
    val tagId: Long,
    val tagName: String,
    val typeId: Long,
    val typeName: String,
    val backgroundColor: String
) {
    @Ignore
    var isSelect: Boolean = false
}