package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.service.UserService;
import com.costmgn.costmgnsrv.utils.WebApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
