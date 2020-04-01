package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.bean.UserBean;
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
    public WebApiResponse<Boolean> login(@RequestBody UserBean user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", userService.getUser(user.getUserid()));
        return WebApiResponse.success(true);
    }

    @RequestMapping("/updatePassword")
    public WebApiResponse<Boolean> updatePassword(@RequestBody ChangePasswordBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getUserList")
    public WebApiResponse<List<UserBean>> getUserList(HttpServletRequest request) {
        List<UserBean> userList = new ArrayList<>();
        return WebApiResponse.success(userList);
    }

    @RequestMapping("/addUser")
    public WebApiResponse<Boolean> addUser() {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/removeUser")
    public WebApiResponse<Boolean> deleteUser(@RequestBody IdListBean bean) {
        return WebApiResponse.success(true);
    }
}
