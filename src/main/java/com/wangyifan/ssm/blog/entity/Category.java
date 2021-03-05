package com.wangyifan.ssm.blog.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * 类目
 *
 * @author 王一凡
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 6687286913317513141L;
    /**
     * 类目Id
     */
    private Integer categoryId;
    /**
     * 父类目Id
     */
    private Integer categoryPid;
    /**
     * 类目名称
     */
    private String categoryName;
    /**
     * 类目描述
     */
    private String categoryDescription;
    /**
     * 类目优先度
     */
    private Integer categoryOrder;
    /**
     * 类目图标样式
     */
    private String categoryIcon;

    /**
     * 文章数量(非数据库字段)
     */
    private Integer articleCount;

    /**
     * 父类目构造方法
     */
    public Category(Integer categoryId, Integer categoryPid, String categoryName, String categoryDescription, Integer categoryOrder, String categoryIcon, Integer articleCount) {
        this.categoryId = categoryId;
        this.categoryPid = categoryPid;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryOrder = categoryOrder;
        this.categoryIcon = categoryIcon;
        this.articleCount = articleCount;
    }

    /**
     * 子类目构造方法
     */
    public Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    /**
     * 这个方法一般用于
     * 父类ID赋值给子类PID，即子类的PID=父类ID
     */
    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 无参构造方法
     */
    public Category() {
    }

    /**
     * 未分类
     *
     * @return 分类
     */
    public static Category Default() {
        return new Category(100000000, "未分类");
    }


}
