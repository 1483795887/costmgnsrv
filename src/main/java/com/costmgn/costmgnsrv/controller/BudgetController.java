package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.Budget;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.service.BudgetService;
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
@RequestMapping(value = "/budget", method = RequestMethod.POST)
public class BudgetController {
    private BudgetService budgetService;
    private WorkService workService;

    @Autowired
    public BudgetController(BudgetService budgetService, WorkService workService) {
        this.budgetService = budgetService;
        this.workService = workService;
    }

    @RequestMapping("/addBudget")
    public WebApiResponse<Boolean> addBudget(@RequestBody Budget budget,
                                             HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        budgetService.addBudget(budget, user);
        return WebApiResponse.success(true);
    }

    @RequestMapping("/updateBudget")
    public WebApiResponse<Boolean> updateBudget(@RequestBody Budget bean) {
        budgetService.updateBudget(bean);
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getBudgets")
    public WebApiResponse<List<Budget>> getBudgets(@RequestBody int type,
                                                   HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Budget> budgetList = budgetService.getBudgets(user, type);
        return WebApiResponse.success(budgetList);
    }

    @RequestMapping("/getBudget")
    public WebApiResponse<Budget> getBudget(@RequestBody int id) {
        return WebApiResponse.success(budgetService.getBudget(id));
    }

    @RequestMapping("/submitBudget")
    public WebApiResponse<Boolean> submitBudget(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            workService.updateStatus(id, Status.NOT_PASSED.ordinal());
        }
        return WebApiResponse.success(true);
    }

    @RequestMapping("/approveBudget")
    public WebApiResponse<Boolean> approveBudget(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            workService.updateStatus(id, Status.FINISHED.ordinal());
        }
        return WebApiResponse.success(true);
    }

    @RequestMapping("/refuseBudget")
    public WebApiResponse<Boolean> refuseBudget(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            workService.updateStatus(id, Status.REFUSED.ordinal());
        }
        return WebApiResponse.success(true);
    }
}