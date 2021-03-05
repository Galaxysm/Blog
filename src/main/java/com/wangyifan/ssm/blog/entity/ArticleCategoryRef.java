package com.wangyifan.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类关联表
 *
 * @author 王一凡
 */
@Data
public class ArticleCategoryRef implements Serializable {

    private static final long serialVersionUID = -6809206515467725995L;
    /**
     * 文章Id
     */
    private Integer articleId;
    /**
     * 类目Id
     */
    private Integer categoryId;

    /**
     * 文章Id与类目Id关联无参构造方法
     */
    public ArticleCategoryRef() {
    }

    /**
     * 文章Id与类目Id关联构造方法
     */
    public ArticleCategoryRef(Integer articleId, Integer categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }
}