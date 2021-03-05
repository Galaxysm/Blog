package com.wangyifan.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面
 *
 * @author 王一凡
 */
@Data
public class Page implements Serializable {

    private static final long serialVersionUID = 3927496662110298634L;
    /**
     * 页面Id
     */
    private Integer pageId;
    /**
     * 页面Key
     */
    private String pageKey;
    /**
     * 页面标题
     */
    private String pageTitle;
    /**
     * 页面内容
     */
    private String pageContent;
    /**
     * 页面创建时间
     */
    private Date pageCreateTime;
    /**
     * 页面更新时间
     */
    private Date pageUpdateTime;
    /**
     * 页面浏览量
     */
    private Integer pageViewCount;
    /**
     * 页面浏览量
     */
    private Integer pageCommentCount;
    /**
     * 页面状态
     */
    private Integer pageStatus;

}