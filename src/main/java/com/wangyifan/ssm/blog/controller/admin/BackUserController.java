package com.wangyifan.ssm.blog.controller.admin;


import com.wangyifan.ssm.blog.entity.User;
import com.wangyifan.ssm.blog.enums.UserRole;
import com.wangyifan.ssm.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 王一凡
 */
@Controller
@RequestMapping("/admin/user")
public class BackUserController {

    @Autowired
    private UserService userService;

    /**
     * 后台用户列表显示
     *
     * @return modelandview
     */
    @RequestMapping(value = "")
    public ModelAndView userList()  {
        ModelAndView modelandview = new ModelAndView();

        List<User> userList = userService.listUser();
        modelandview.addObject("userList",userList);

        modelandview.setViewName("Admin/User/index");
        return modelandview;

    }

    /**
     * 后台添加用户页面显示
     *
     * @return modelandview
     */
    @RequestMapping(value = "/insert")
    public ModelAndView insertUserView()  {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Admin/User/insert");
        return modelAndView;
    }

    /**
     * 检查用户名是否存在
     *
     * @param request;
     */
    @RequestMapping(value = "/checkUserName",method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String checkUserName(HttpServletRequest request)  {
        Map<String, Object> map = new HashMap<>();
        String username = request.getParameter("username");
        User user = userService.getUserByName(username);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if(user!=null) {
            if(user.getUserId()!=id) {
                map.put("code", 1);
                map.put("msg", "用户名已存在！");
            }
        } else {
            map.put("code",0);
            map.put("msg","");
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 检查Email是否存在
     *
     * @param request;
     */
    @RequestMapping(value = "/checkUserEmail",method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String checkUserEmail(HttpServletRequest request)  {
        Map<String, Object> map = new HashMap<String, Object>();
        String email = request.getParameter("email");
        User user = userService.getUserByEmail(email);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if(user!=null) {
            if(user.getUserId()!=id) {
                map.put("code", 1);
                map.put("msg", "电子邮箱已存在！");
            }
        } else {
            map.put("code",0);
            map.put("msg","");
        }
        String result = new JSONObject(map).toString();
        return result;
    }


    /**
     * 后台添加用户页面提交
     *
     * @param user 用户
     * @return redirect:/admin/user
     */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertUserSubmit(User user)  {
        User username = userService.getUserByName(user.getUserName());
        User useremail = userService.getUserByEmail(user.getUserEmail());
        if(username==null&&useremail==null) {
            user.setUserRegisterTime(new Date());               //设置注册时间
            user.setUserStatus(1);                              //设置用户状态-正常Or禁用
            user.setUserRole(UserRole.USER.getValue());         //设置用户角色-admin Or user
            userService.insertUser(user);
        }
        return "redirect:/admin/user";
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return redirect:/admin/user
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id)  {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }

    /**
     * 编辑用户页面显示
     *
     * @param id 用户ID
     * @return modelAndView
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editUserView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();

        User user =  userService.getUserById(id);
        modelAndView.addObject("user",user);

        modelAndView.setViewName("Admin/User/edit");
        return modelAndView;
    }


    /**
     * 编辑用户提交
     *
     * @param user 用户
     * @return redirect:/admin/user
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editUserSubmit(User user)  {
        userService.updateUser(user);
        return "redirect:/admin/user";
    }

}
