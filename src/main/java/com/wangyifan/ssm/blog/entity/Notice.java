package com.wangyifan.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告
 *
 * @author 王一凡
 */
@Data
public class Notice implements Serializable {

    private static final long serialVersionUID = -4901560494243593100L;
    private Integer noticeId;
    /**
     * 公告标题
     */
    private String noticeTitle;
    /**
     * 公告内容
     */
    private String noticeContent;
    /**
     * 公告创建时间
     */
    private Date noticeCreateTime;
    /**
     * 公告更新时间
     */
    private Date noticeUpdateTime;
    /**
     * 公告状态
     */
    private Integer noticeStatus;
    /**
     * 公告优先级
     */
    private Integer noticeOrder;
}