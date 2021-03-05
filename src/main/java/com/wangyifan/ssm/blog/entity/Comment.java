package com.wangyifan.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章评论
 *
 * @author 王一凡
 */
@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = -1038897351672911219L;
    /**
     * 父评论Id
     */
    private Integer commentId;
    /**
     * 子评论ID
     */
    private Integer commentPid;
    /**
     * @ 对象名称
     */
    private String commentPname;
    /**
     * 评论文章ID
     */
    private Integer commentArticleId;
    /**
     * 评论者名称
     */
    private String commentAuthorName;
    /**
     * 评论者Email
     */
    private String commentAuthorEmail;
    /**
     * 评论者URL
     */
    private String commentAuthorUrl;
    /**
     * 评论者头像
     */
    private String commentAuthorAvatar;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     *
     */
    private String commentAgent;
    /**
     * 评论者Ip地址
     */
    private String commentIp;
    /**
     * 评论创建时间
     */
    private Date commentCreateTime;

    /**
     * 角色(管理员1，访客0)
     */
    private Integer commentRole;

    /**
     * 评论用户ID
     */
    private Integer commentUserId;

    /**
     * 非数据库字段
     */
    private Article article;
}