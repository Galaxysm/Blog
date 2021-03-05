package com.wangyifan.ssm.blog.controller.home;

import cn.hutool.http.HtmlUtil;
import com.wangyifan.ssm.blog.entity.User;
import com.wangyifan.ssm.blog.enums.ArticleStatus;
import com.wangyifan.ssm.blog.dto.JsonResult;
import com.wangyifan.ssm.blog.entity.Article;
import com.wangyifan.ssm.blog.entity.Comment;
import com.wangyifan.ssm.blog.enums.Role;
import com.wangyifan.ssm.blog.service.ArticleService;
import com.wangyifan.ssm.blog.service.CommentService;
import com.wangyifan.ssm.blog.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

/**
 * 文章评论的controller
 *
 * @author 王一凡
 *
 */

@Controller
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    /**
     * 添加评论
     *
     * @param request;
     * @param comment;
     */
    @RequestMapping(value = "/comment", method = {RequestMethod.POST})
    public JsonResult insertComment(HttpServletRequest request, Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new JsonResult().fail("请先登录");
        }
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
        if (article == null) {
            return new JsonResult().fail("文章不存在");
        }


        //添加评论
        comment.setCommentAuthorAvatar(user.getUserAvatar());
        comment.setCommentUserId(user.getUserId());
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        if (Objects.equals(user.getUserId(), article.getArticleUserId())) {
            comment.setCommentRole(Role.OWNER.getValue());
        } else {
            comment.setCommentRole(Role.VISITOR.getValue());
        }


        //过滤字符，防止XSS攻击
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));

        comment.setCommentAuthorName(user.getUserNickname());
        comment.setCommentAuthorEmail(user.getUserEmail());
        comment.setCommentAuthorUrl(user.getUserUrl());
        try {
            commentService.insertComment(comment);
            //更新文章的评论数
            articleService.updateCommentCount(article.getArticleId());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult().fail();
        }
        return new JsonResult().ok();
    }


}
