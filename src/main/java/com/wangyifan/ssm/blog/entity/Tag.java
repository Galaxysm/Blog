package com.wangyifan.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 标签
 *
 * @author 王一凡
 */
@Data
public class Tag implements Serializable{
    private static final long serialVersionUID = 605449151900057035L;
    /**
     * 标签Id
     */
    private Integer tagId;
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 标签描述
     */
    private String tagDescription;

    /**
     * 文章数量(不是数据库字段)
     */
    private Integer articleCount;

    public Tag() {
    }

    public Tag(Integer tagId) {
        this.tagId = tagId;
    }

    public Tag(Integer tagId, String tagName, String tagDescription, Integer articleCount) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagDescription = tagDescription;
        this.articleCount = articleCount;
    }
}