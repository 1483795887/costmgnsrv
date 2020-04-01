package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.bean.BudgetBean;
import com.costmgn.costmgnsrv.service.BudgetService;
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
@RequestMapping(value = "/budget", method = RequestMethod.POST)
public class BudgetController {
    private BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @RequestMapping("/addBudget")
    public WebApiResponse<Boolean> addBudget(@RequestBody BudgetBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/updateBudget")
    public WebApiResponse<Boolean> updateBudget(@RequestBody BudgetBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getBudgets")
    public WebApiResponse<List<BudgetBean>> getBudgets(HttpServletRequest request) {
        List<BudgetBean> budgetList = new ArrayList<>();
        return WebApiResponse.success(budgetList);
    }

    @RequestMapping("/submitBudget")
    public WebApiResponse<Boolean> submitBudget(@RequestBody BudgetBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/approveBudget")
    public WebApiResponse<Boolean> approvePlan(@RequestBody BudgetBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/refuseBudget")
    public WebApiResponse<Boolean> refusePlan(@RequestBody BudgetBean bean) {
        return WebApiResponse.success(true);
    }
}
