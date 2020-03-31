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
    public WebApiResponse<Boolean> login(@RequestBody User user) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/updatePassword")
    public WebApiResponse<Boolean> updatePassword(@RequestBody ChangePasswordBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getUserList")
    public WebApiResponse<List<User>> getUserList() {
        List<User> userList = new ArrayList<>();
        return WebApiResponse.success(userList);
    }

    @RequestMapping("/addUser")
    public WebApiResponse<Boolean> addUser() {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/deleteUser")
    public WebApiResponse<Boolean> deleteUser(@RequestBody IdListBean bean) {
        return WebApiResponse.success(true);
    }
}
