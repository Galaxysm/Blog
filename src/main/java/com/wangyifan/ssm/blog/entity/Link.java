package com.wangyifan.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 链接
 *
 * @author 王一凡
 */
@Data
public class Link implements Serializable {


    private static final long serialVersionUID = -259829372268790508L;
    /**
     * 链接ID
     */
    private Integer linkId;
    /**
     * 链接URL
     */
    private String linkUrl;
    /**
     * 链接名称
     */
    private String linkName;
    /**
     *
     */
    private String linkImage;
    /**
     * 链接描述
     */
    private String linkDescription;
    /**
     *
     */
    private String linkOwnerNickname;
    /**
     * 链接联系方式
     */
    private String linkOwnerContact;
    /**
     * 链接申请更新时间
     */
    private Date linkUpdateTime;
    /**
     * 链接申请创建时间
     */
    private Date linkCreateTime;
    /**
     * 链接优先度
     */
    private Integer linkOrder;
    /**
     * 链接神申请状态 隐藏or显示
     */
    private Integer linkStatus;

}