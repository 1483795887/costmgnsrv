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
import java.util.ArrayList;
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
    public WebApiResponse<User> login(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User userBean = userService.getUser(user.getUserid());
        session.setAttribute("user", userBean);
        return WebApiResponse.success(userBean);
    }

    @RequestMapping("/updatePassword")
    public WebApiResponse<Boolean> updatePassword(@RequestBody ChangePasswordBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getUserList")
    public WebApiResponse<List<User>> getUserList(HttpServletRequest request) {
        List<User> userList = new ArrayList<>();
        return WebApiResponse.success(userList);
    }

    @RequestMapping("/addUser")
    public WebApiResponse<Boolean> addUser(User bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/removeUser")
    public WebApiResponse<Boolean> removeUser(@RequestBody IdListBean bean) {
        return WebApiResponse.success(true);
    }
}
