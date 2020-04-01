package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.Plan;
import com.costmgn.costmgnsrv.service.PlanService;
import com.costmgn.costmgnsrv.service.WorkService;
import com.costmgn.costmgnsrv.utils.IdListBean;
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
    private WorkService workService;

    @Autowired
    public PlanController(PlanService planService, WorkService workService) {
        this.planService = planService;
        this.workService = workService;
    }

    @RequestMapping("/addPlan")
    public WebApiResponse<Boolean> addPlan(@RequestBody Plan plan) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/updatePlan")
    public WebApiResponse<Boolean> updatePlan(@RequestBody Plan bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getPlans")
    public WebApiResponse<List<Plan>> getPlans(HttpServletRequest request, @RequestBody int type) {
        List<Plan> planList = new ArrayList<>();
        return WebApiResponse.success(planList);
    }

    @RequestMapping("/getPlan")
    public WebApiResponse<Plan> getPlan(int id) {
        return WebApiResponse.success(null);
    }

    @RequestMapping("/submitPlan")
    public WebApiResponse<Boolean> submitPlan(@RequestBody IdListBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/approvePlan")
    public WebApiResponse<Boolean> approvePlan(@RequestBody IdListBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/refusePlan")
    public WebApiResponse<Boolean> refusePlan(@RequestBody IdListBean bean) {
        return WebApiResponse.success(true);
    }
}
