package com.jourwon.spring.boot.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class SysMenuDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型，0-目录，1-菜单，2-按钮
     */
    private Integer menuType;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 权限标识(多个用逗号隔开，如：user:list,user:update)
     */
    private String permission;

    /**
     * 排序
     */
    private Integer orderNum;


}
