package com.wangyifan.ssm.blog.controller.admin;

import com.wangyifan.ssm.blog.util.MyUtils;
import com.wangyifan.ssm.blog.entity.Article;
import com.wangyifan.ssm.blog.entity.Comment;
import com.wangyifan.ssm.blog.entity.User;
import com.wangyifan.ssm.blog.service.ArticleService;
import com.wangyifan.ssm.blog.service.CommentService;
import com.wangyifan.ssm.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王一凡
 */
@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    /**
     * 后台首页
     *
     * @return index
     */
    @RequestMapping("/admin")
    public String index(Model model) {
        //文章列表
        List<Article> articleList = articleService.listRecentArticle(5);
        model.addAttribute("articleList", articleList);
        //评论列表
        List<Comment> commentList = commentService.listRecentComment(5);
        model.addAttribute("commentList", commentList);
        return "Admin/index";
    }

    /**普通用户注册*/
    @RequestMapping("/register")
    public String register(){
        return "/Admin/register";
    }
    /**
     * 执行普通用户注册
     *
     */
    @RequestMapping(value = "/registerVerify", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String  registerVerify(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String usernickname = request.getParameter("usernickname");
        String useremail = request.getParameter("useremail");
        User user=new User();

        //检查用户名是否重复
        User name=userService.getUserByName(username);
        //检查邮箱是否重复
        User email=userService.getUserByEmail(useremail);
        if (name==null&&email==null){
            //注册成功
            map.put("code", 1);
            map.put("msg", "注册成功");
            user.setUserName(username);
            user.setUserEmail(useremail);
            user.setUserNickname(usernickname);
            user.setUserPass(password);
            user.setUserRegisterTime(new Date());
            user.setUserStatus(1);
            userService.insertUser(user);
        }else if(name !=null){
            map.put("code", 0);
            map.put("msg", "用户名重复！");
        }else if(email !=null){
            map.put("code", 0);
            map.put("msg", "邮箱重复！");
        }
        String result = new JSONObject(map).toString();
        return result;
    }



    /**
     * 登录页面显示
     *
     * @return login
     */
    @RequestMapping("/login")
    public String loginPage() {
        return "Admin/login";
    }

    /**
     * 登录验证
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        User user = userService.getUserByNameOrEmail(username);
        if (user == null) {
            map.put("code", 0);
            map.put("msg", "用户名无效！");
        } else if (!user.getUserPass().equals(password)) {
            map.put("code", 0);
            map.put("msg", "密码错误！");
        } else {
            //登录成功
            map.put("code", 1);
            map.put("msg", "");
            //添加session
            request.getSession().setAttribute("user", user);
            //添加cookie
            if (rememberme != null) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(MyUtils.getIpAddr(request));
            userService.updateUser(user);

        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/admin/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }


}
