package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.bean.CostBean;
import com.costmgn.costmgnsrv.service.CostService;
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
@RequestMapping(value = "/cost", method = RequestMethod.POST)
public class CostController {
    private CostService costService;

    @Autowired
    public CostController(CostService costService) {
        this.costService = costService;
    }

    @RequestMapping("/addCost")
    public WebApiResponse<Boolean> addCost(@RequestBody CostBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/updateCost")
    public WebApiResponse<Boolean> updateCost(@RequestBody CostBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getCost")
    public WebApiResponse<List<CostBean>> getCosts(HttpServletRequest request) {
        List<CostBean> costList = new ArrayList<>();
        return WebApiResponse.success(costList);
    }

    @RequestMapping("/submitCost")
    public WebApiResponse<Boolean> submitCost(@RequestBody CostBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/approveCost")
    public WebApiResponse<Boolean> approveCost(@RequestBody CostBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/refuseCost")
    public WebApiResponse<Boolean> refuseCost(@RequestBody CostBean bean) {
        return WebApiResponse.success(true);
    }
}
