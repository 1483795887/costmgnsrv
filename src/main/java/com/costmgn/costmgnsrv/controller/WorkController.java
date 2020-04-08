package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.service.WorkService;
import com.costmgn.costmgnsrv.utils.WebApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/work", method = RequestMethod.POST)
public class WorkController {
    private WorkService workService;

    @Autowired
    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @RequestMapping("/getCurWorks")
    public WebApiResponse<List<Work>> getCurWorks(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Work> workList = workService.getCurWorks(user);
        return WebApiResponse.success(workList);
    }

    @RequestMapping("/getToDoWorks")
    public WebApiResponse<List<Work>> getToDoWorks(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Work> workList = workService.getToDoWorks(user);
        return WebApiResponse.success(workList);
    }
}
