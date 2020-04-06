package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.Receipt;
import com.costmgn.costmgnsrv.service.CostService;
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
    public WebApiResponse<Boolean> addCost(@RequestBody Receipt receipt) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getCosts")
    public WebApiResponse<List<Receipt>> getCosts(HttpServletRequest request, @RequestBody int type) {
        List<Receipt> costList = new ArrayList<>();
        return WebApiResponse.success(costList);
    }

    @RequestMapping("/getCost")
    public WebApiResponse<Receipt> getCost(int id) {
        return WebApiResponse.success(null);
    }

    @RequestMapping("/submitCost")
    public WebApiResponse<Boolean> submitCost(@RequestBody IdListBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/approveCost")
    public WebApiResponse<Boolean> approveCost(@RequestBody IdListBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/refuseCost")
    public WebApiResponse<Boolean> refuseCost(@RequestBody IdListBean bean) {
        return WebApiResponse.success(true);
    }
}
