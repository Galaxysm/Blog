package com.wangyifan.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 主要选项 （基本信息，小工具）
 *
 * @author 王一凡
 */
@Data
public class Options implements Serializable {
    private static final long serialVersionUID = -776792869602511933L;
    /**
     * 站点Id
     */
    private Integer optionId;
    /**
     * 站点标题
     */
    private String optionSiteTitle;
    /**
     * 站点描述
     */
    private String optionSiteDescrption;
    /**
     * 首页描述
     */
    private String optionMetaDescrption;
    /**
     * 站点关键字
     */
    private String optionMetaKeyword;
    /**
     * 站点博主头像
     */
    private String optionAboutsiteAvatar;
    /**
     * 站点博主标题
     */
    private String optionAboutsiteTitle;
    /**
     * 站点博主内容
     */
    private String optionAboutsiteContent;
    /**
     * 站点博主微信
     */
    private String optionAboutsiteWechat;
    /**
     * 站点博主QQ
     */
    private String optionAboutsiteQq;
    /**
     * 站点博主GitHub
     */
    private String optionAboutsiteGithub;
    /**
     * 站点博主微博
     */
    private String optionAboutsiteWeibo;
    /**
     * 未定义内容
     */
    private String optionTongji;
    /**
     * 站点状态
     */
    private Integer optionStatus;

}