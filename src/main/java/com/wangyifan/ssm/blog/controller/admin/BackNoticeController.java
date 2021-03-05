package com.wangyifan.ssm.blog.controller.admin;


import com.wangyifan.ssm.blog.entity.Notice;
import com.wangyifan.ssm.blog.enums.NoticeStatus;
import com.wangyifan.ssm.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * @author 王一凡
 */

@Controller
@RequestMapping("/admin/notice")
public class BackNoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 后台公告列表显示
     *
     * @return Admin/Notice/index
     */
    @RequestMapping(value = "")
    public String index(Model model) {
        List<Notice> noticeList = noticeService.listNotice(null);
        model.addAttribute("noticeList", noticeList);
        return "Admin/Notice/index";

    }

    /**
     * 添加公告显示
     *
     * @return Admin/Notice/insert
     */
    @RequestMapping(value = "/insert")
    public String insertNoticeView() {
        return "Admin/Notice/insert";
    }

    /**
     * 添加公告提交
     *
     * @param notice 公告
     * @return redirect:/admin/notice
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertNoticeSubmit(Notice notice) {
        notice.setNoticeCreateTime(new Date());
        notice.setNoticeUpdateTime(new Date());
        notice.setNoticeStatus(NoticeStatus.NORMAL.getValue());
        notice.setNoticeOrder(1);
        noticeService.insertNotice(notice);
        return "redirect:/admin/notice";
    }

    /**
     * 删除公告
     *
     * @param id 公告ID
     * @return redirect:/admin/notice
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteNotice(@PathVariable("id") Integer id) {
        noticeService.deleteNotice(id);

        return "redirect:/admin/notice";
    }

    /**
     * 编辑公告页面显示
     *
     * @param id 公告ID
     * @return Admin/Notice/edit
     */
    @RequestMapping(value = "/edit/{id}")
    public String editNoticeView(@PathVariable("id") Integer id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "Admin/Notice/edit";
    }


    /**
     * 编辑公告页面显示
     *
     * @param notice 公告
     * @return redirect:/admin/notice
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editNoticeSubmit(Notice notice) {
        notice.setNoticeUpdateTime(new Date());
        noticeService.updateNotice(notice);
        return "redirect:/admin/notice";
    }


}
