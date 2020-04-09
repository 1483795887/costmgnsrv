package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.Plan;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.service.PlanService;
import com.costmgn.costmgnsrv.service.WorkService;
import com.costmgn.costmgnsrv.utils.IdListBean;
import com.costmgn.costmgnsrv.utils.Status;
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
    public WebApiResponse<Boolean> addPlan(@RequestBody Plan plan, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        planService.addPlan(plan, user);
        return WebApiResponse.success(true);
    }

    @RequestMapping("/updatePlan")
    public WebApiResponse<Boolean> updatePlan(@RequestBody Plan bean) {
        planService.updatePlan(bean);
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getPlans")
    public WebApiResponse<List<Plan>> getPlans(HttpServletRequest request, @RequestBody int type) {
        List<Plan> planList = new ArrayList<>();
        return WebApiResponse.success(planList);
    }

    @RequestMapping("/getPlan")
    public WebApiResponse<Plan> getPlan(@RequestBody int id) {
        return WebApiResponse.success(planService.getPlan(id));
    }

    @RequestMapping("/submitPlan")
    public WebApiResponse<Boolean> submitPlan(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            workService.updateStatus(id, Status.NOT_AUDITED.ordinal());
        }
        return WebApiResponse.success(true);
    }

    @RequestMapping("/approvePlan")
    public WebApiResponse<Boolean> approvePlan(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            workService.updateStatus(id, Status.FINISHED.ordinal());
        }
        return WebApiResponse.success(true);
    }

    @RequestMapping("/refusePlan")
    public WebApiResponse<Boolean> refusePlan(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            workService.updateStatus(id, Status.REFUSED.ordinal());
        }
        return WebApiResponse.success(true);
    }
}
