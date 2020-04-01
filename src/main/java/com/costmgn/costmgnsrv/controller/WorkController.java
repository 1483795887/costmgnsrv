package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.bean.WorkBean;
import com.costmgn.costmgnsrv.service.WorkService;
import com.costmgn.costmgnsrv.utils.WebApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public WebApiResponse<List<WorkBean>> getCurWorks() {
        List<WorkBean> workList = new ArrayList<>();
        return WebApiResponse.success(workList);
    }

    @RequestMapping("/getToDoWorks")
    public WebApiResponse<List<WorkBean>> getToDoWorks() {
        List<WorkBean> workList = new ArrayList<>();
        return WebApiResponse.success(workList);
    }
}
