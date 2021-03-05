package com.wangyifan.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章和标签关联
 *
 * @author 王一凡
 */
@Data
public class ArticleTagRef implements Serializable {
    private static final long serialVersionUID = -5816783232020910492L;
    /**
     * 文章Id
     */
    private Integer articleId;
    /**
     * 标签Id
     */
    private Integer tagId;

    /**
     * 文章Id与标签Id关联无参构造方法
     */
    public ArticleTagRef() {
    }

    /**
     * 文章Id与标签Id关联构造方法
     */
    public ArticleTagRef(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
