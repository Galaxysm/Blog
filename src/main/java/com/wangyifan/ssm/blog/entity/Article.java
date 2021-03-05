package com.wangyifan.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章
 *
 * @author 王一凡
 */
@Data
public class Article implements Serializable {
    /**
     * 实现Serializable接口的目的是为类可持久化，比如在网络传输或本地存储，为系统的分布和异构部署提供先决条件。若没有序列化，现在我们所熟悉的远程调用，对象数据库都不可能存在，
     */
    private static final long serialVersionUID = 5207865247400761539L;
    /**
     * 文章Id
     */
    private Integer articleId;
    /**
     * 文章作者Id
     */
    private Integer articleUserId;
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 文章浏览次数
     */
    private Integer articleViewCount;
    /**
     * 文章评论数
     */
    private Integer articleCommentCount;
    /**
     * 文章点赞量
     */
    private Integer articleLikeCount;
    /**
     * 文章发布时间
     */
    private Date articleCreateTime;
    /**
     * 文章更新时间
     */
    private Date articleUpdateTime;
    /**
     * 文章评论
     */
    private Integer articleIsComment;
    /**
     * 文章状态 草稿or已发布
     */
    private Integer articleStatus;
    /**
     * 文章优先度
     */
    private Integer articleOrder;
    /**
     * 文章内容
     */
    private String articleContent;
    /**
     * 文章摘要
     */
    private String articleSummary;
    /**
     * 作者信息
     */
    private User user;
    /**
     * 文章标签
     */
    private List<Tag> tagList;
    /**
     * 文章类目
     */
    private List<Category> categoryList;

}