package com.wangyifan.ssm.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * Java 中的DTO全称是 Data Transfer Object, 概括来说，就是把需要传递参数封装为一个class的形式，也符合java面向对象编程的概念.
 * 比较常用的方式：Web参数的传递，使用 Javascript POST一个DTO类型的对象，在Java 服务端可用DTO类型对象直接接收
 * 
 * @author 王一凡
 *
 */
@Data
public class ArticleParam {
    /**
     * 文章Id
     */
    private Integer articleId;
    /**
     *文章标题
     */
    private String articleTitle;
    /**
     *文章内容
     */
    private String articleContent;
    /**
     *文章父类目Id
     */
    private Integer articleParentCategoryId;
    /**
     *文章子类目Id
     */
    private Integer articleChildCategoryId;
    /**
     *文章优先级
     */
    private Integer articleOrder;
    /**
     *文章状态 草稿or已发布
     */
    private Integer articleStatus;
    /**
     *文章标签Id
     */
    private List<Integer> articleTagIds;

}
