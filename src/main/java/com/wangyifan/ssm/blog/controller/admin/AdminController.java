package com.wangyifan.ssm.blog.controller.admin;

import com.wangyifan.ssm.blog.enums.UserRole;
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
import org.springframework.web.servlet.ModelAndView;

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
     * @return Admin/index
     */
    @RequestMapping("/admin")
    public String index(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer userId = null;
        if (!UserRole.ADMIN.getValue().equals(user.getUserRole())) {
            // 用户查询自己的文章, 管理员查询所有的
            userId = user.getUserId();
        }
        //文章列表
        List<Article> articleList = articleService.listRecentArticle(userId, 5);
        model.addAttribute("articleList", articleList);
        //评论列表
        List<Comment> commentList = commentService.listRecentComment(userId, 5);
        model.addAttribute("commentList", commentList);
        return "Admin/index";
    }

    /**
     * 显示用户注册界面
     *
     * @return Admin/register
     */
    @RequestMapping("/register")
    public String register() {
        return "/Admin/register";
    }

    /**
     * 执行用户注册功能
     *
     *
     */
    @RequestMapping(value = "/registerVerify", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String registerVerify(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String usernickname = request.getParameter("usernickname");
        String useremail = request.getParameter("useremail");
        User user = new User();

        //检查用户名是否重复
        User name = userService.getUserByName(username);
        //检查邮箱是否重复
        User email = userService.getUserByEmail(useremail);
        if (name == null && email == null) {
            //注册成功
            map.put("code", 1);
            map.put("msg", "注册成功");
            //设置默认头像
            user.setUserAvatar("/img/1117676206.jpeg");
            user.setUserName(username);
            user.setUserPass(password);
            user.setUserNickname(usernickname);
            user.setUserEmail(useremail);
            user.setUserRegisterTime(new Date());
            user.setUserStatus(1);
            user.setArticleCount(0);
            user.setUserRole(UserRole.USER.getValue());
            userService.insertUser(user);

        } else if (name != null) {
            //检查用户名是否重复
            map.put("code", 0);
            map.put("msg", "用户名重复！");
        } else if (email != null) {
            //检查邮箱是否重复
            map.put("code", 0);
            map.put("msg", "邮箱重复！");
        }
        String result = new JSONObject(map).toString();
        return result;
    }


    /**
     * 显示用户登录页面
     *
     * @return Admin/login
     */
    @RequestMapping("/login")
    public String loginPage() {
        return "Admin/login";
    }

    /**
     * 登录验证
     *
     * @param request;
     * @param response;
     */
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        User user = userService.getUserByNameOrEmail(username);
        if (user == null) {
            map.put("code", 0);
            map.put("msg", "用户名未注册！");
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
            //设置用户最后登录时间
            user.setUserLastLoginTime(new Date());
            //设置用户最后登录Ip
            user.setUserLastLoginIp(MyUtils.getIpAddr(request));
            userService.updateUser(user);
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 退出登录
     *
     * @param session;
     * @return redirect:/login
     */
    @RequestMapping(value = "/admin/logout")
    public String logout(HttpSession session) {
        //  注销用户，使session失效。
        //session.removeAttribute()适用于清空指定的属性
        //session.invalidate()是清除当前session的所有相关信息
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }


    /**
     * 登录者基本信息页面显示
     *
     * @return modelAndView
     */
    @RequestMapping(value = "/admin/profile")
    public ModelAndView userProfileView(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        User sessionUser = (User) session.getAttribute("user");
        User user = userService.getUserById(sessionUser.getUserId());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("Admin/User/profile");
        return modelAndView;
    }

    /**
     * 登陆者编辑个人信息页面显示
     *
     * @return modelAndView
     */
    @RequestMapping(value = "/admin/profile/edit")
    public ModelAndView editUserView(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User loginUser = (User) session.getAttribute("user");
        User user = userService.getUserById(loginUser.getUserId());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("Admin/User/editProfile");
        return modelAndView;
    }


    /**
     * 编辑用户提交
     *
     * @return redirect:/admin/profile
     */
    @RequestMapping(value = "/admin/profile/save", method = RequestMethod.POST)
    public String saveProfile(User user, HttpSession session) {
        User dbUser = (User) session.getAttribute("user");
        user.setUserId(dbUser.getUserId());
        userService.updateUser(user);
        return "redirect:/admin/profile";
    }
}
