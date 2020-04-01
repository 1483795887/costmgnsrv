package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.bean.PlanBean;
import com.costmgn.costmgnsrv.service.PlanService;
import com.costmgn.costmgnsrv.utils.WebApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/plan", method = RequestMethod.POST)
public class PlanController {
    private PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @RequestMapping("/addPlan")
    public WebApiResponse<Boolean> addPlan(@RequestBody PlanBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/updatePlan")
    public WebApiResponse<Boolean> updatePlan(@RequestBody PlanBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getPlans")
    public WebApiResponse<List<PlanBean>> getPlans(HttpServletRequest request) {
        List<PlanBean> planList = new ArrayList<>();
        return WebApiResponse.success(planList);
    }

    @RequestMapping("/submitPlan")
    public WebApiResponse<Boolean> submitPlan(@RequestBody PlanBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/approvePlan")
    public WebApiResponse<Boolean> approvePlan(@RequestBody PlanBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/refusePlan")
    public WebApiResponse<Boolean> refusePlan(@RequestBody PlanBean bean) {
        return WebApiResponse.success(true);
    }
}
