package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.service.UserService;
import com.costmgn.costmgnsrv.utils.ChangePasswordBean;
import com.costmgn.costmgnsrv.utils.IdListBean;
import com.costmgn.costmgnsrv.utils.WebApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public WebApiResponse<User> login(@RequestBody User user,
                                      HttpServletRequest request) {
        boolean result = userService.login(user.getUserid(), user.getPassword());
        if (result) {
            HttpSession session = request.getSession();
            User userBean = userService.getUser(user.getUserid());
            session.setAttribute("user", userBean);
            return WebApiResponse.success(userBean);
        } else {
            return WebApiResponse.error("账号或密码错误");
        }
    }

    @RequestMapping("/updatePassword")
    public WebApiResponse<Boolean> updatePassword(@RequestBody ChangePasswordBean bean) {
        User user = userService.getUser(bean.getId());
        if (user == null) {
            return WebApiResponse.error("账号不存在");//既然登录了那账号就一定存在
        } else {
            if (!user.getPassword().equals(bean.getPassword())) {
                return WebApiResponse.error("密码错误");
            } else {
                userService.updatePassword(bean.getId(), bean.getNewPassword());
            }
        }

        return WebApiResponse.success(true);
    }

    @RequestMapping("/getUserList")
    public WebApiResponse<List<User>> getUserList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<User> userList = userService.getUsers(user);
        return WebApiResponse.success(userList);
    }

    @RequestMapping("/addUser")
    public WebApiResponse<Boolean> addUser(@RequestBody User bean) {
        userService.addUser(bean);
        return WebApiResponse.success(true);
    }

    @RequestMapping("/removeUser")
    public WebApiResponse<Boolean> removeUser(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            userService.removeUser(id);
        }
        return WebApiResponse.success(true);
    }
}
