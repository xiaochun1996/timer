package com.jock.jframe.bean

import java.io.Serializable

/**
 * Description: JavaBean
 * Author: lxc
 * Date: 2021/11/28 13:21
 */

/**
 * @Description 菜单实体类
 * @param title 当前菜单名称
 * @param child 当前菜单的子菜单
 * @param page 是否是功能页
 * @param path 功能页 class 对象
 */
data class MenuEntity(val title:String, val child:List<MenuEntity>?=null, val page:Boolean= false, val path:Class<*>?=null):Serializable