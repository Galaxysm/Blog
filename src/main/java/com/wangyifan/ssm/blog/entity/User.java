package com.wangyifan.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author 王一凡
 */
@Data
public class User implements Serializable{
    private static final long serialVersionUID = -4415517704211731385L;
    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String userPass;
    /**
     * 用户昵称
     */
    private String userNickname;
    /**
     * 用户Email
     */
    private String userEmail;
    /**
     * 用户URL
     */
    private String userUrl;
    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 用户最后登录Ip
     */
    private String userLastLoginIp;
    /**
     * 用户注册时间
     */
    private Date userRegisterTime;
    /**
     * 用户最后登录时间
     */
    private Date userLastLoginTime;
    /**
     * 用户状态
     */
    private Integer userStatus;
    /**
     * 用户角色：admin/user
     */
    private String userRole;

    /**
     * 文章数量（不是数据库字段）
     */
    private Integer articleCount;

}