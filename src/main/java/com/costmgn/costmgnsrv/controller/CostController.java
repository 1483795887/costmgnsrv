package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.Receipt;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.service.CostService;
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
import java.util.List;

@RestController
@RequestMapping(value = "/cost", method = RequestMethod.POST)
public class CostController {
    private CostService costService;
    private WorkService workService;

    @Autowired
    public CostController(CostService costService, WorkService workService) {
        this.costService = costService;
        this.workService = workService;
    }

    @RequestMapping("/addCost")
    public WebApiResponse<Boolean> addCost(@RequestBody Receipt cost,
                                           HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        costService.addCost(cost, user);
        return WebApiResponse.success(true);
    }

    @RequestMapping("/updateCost")
    public WebApiResponse<Boolean> updateCost(@RequestBody Receipt bean) {
        costService.updateCost(bean);
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getCosts")
    public WebApiResponse<List<Receipt>> getCosts(@RequestBody int type,
                                                  HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Receipt> costList = costService.getCosts(user, type);
        return WebApiResponse.success(costList);
    }

    @RequestMapping("/getCostsForBudget")
    public WebApiResponse<List<Receipt>> getCostsForBudget(@RequestBody int costId) {
        List<Receipt> receiptList = costService.getCosts(costId);
        return WebApiResponse.success(receiptList);
    }

    @RequestMapping("/getCost")
    public WebApiResponse<Receipt> getCost(@RequestBody int id) {
        return WebApiResponse.success(costService.getCost(id));
    }

    @RequestMapping("/submitCost")
    public WebApiResponse<Boolean> submitCost(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            workService.updateStatus(id, Status.NOT_AUDITED.ordinal());
        }
        return WebApiResponse.success(true);
    }

    @RequestMapping("/approveCost")
    public WebApiResponse<Boolean> approveCost(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            workService.updateStatus(id, Status.FINISHED.ordinal());
        }
        return WebApiResponse.success(true);
    }

    @RequestMapping("/refuseCost")
    public WebApiResponse<Boolean> refuseCost(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            workService.updateStatus(id, Status.REFUSED.ordinal());
        }
        return WebApiResponse.success(true);
    }
}