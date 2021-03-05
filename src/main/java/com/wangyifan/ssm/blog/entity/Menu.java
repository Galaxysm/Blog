package com.wangyifan.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单
 *
 * @author 王一凡
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 489914127235951698L;
    /**
     * 菜单ID
     */
    private Integer menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单URL 可以是网址，也可以是Key
     */
    private String menuUrl;
    /**
     * 菜单级别 一级菜单（顶部菜单 level；1），二级菜单（主要菜单level；2）
     */
    private Integer menuLevel;
    /**
     * 菜单图标样式
     */
    private String menuIcon;
    /**
     * 菜单优先度
     */
    private Integer menuOrder;

}