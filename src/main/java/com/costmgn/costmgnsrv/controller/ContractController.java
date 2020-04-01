package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.Contract;
import com.costmgn.costmgnsrv.service.ContractService;
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
@RequestMapping(value = "/contract", method = RequestMethod.POST)
public class ContractController {
    private ContractService contractService;
    private WorkService workService;

    @Autowired
    public ContractController(ContractService contractService, WorkService workService) {
        this.contractService = contractService;
        this.workService = workService;
    }

    @RequestMapping("/addContract")
    public WebApiResponse<Boolean> addContract(@RequestBody Contract contract) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/updateContract")
    public WebApiResponse<Boolean> updateContract(@RequestBody Contract contract) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getContracts")
    public WebApiResponse<List<Contract>> getContracts(HttpServletRequest request, @RequestBody int type) {
        List<Contract> contractList = new ArrayList<>();
        return WebApiResponse.success(contractList);
    }

    @RequestMapping("/getContract")
    public WebApiResponse<Contract> getContract(int id) {
        return WebApiResponse.success(null);
    }

    @RequestMapping("/submitContract")
    public WebApiResponse<Boolean> submitContract(@RequestBody IdListBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/approveContract")
    public WebApiResponse<Boolean> approveContract(@RequestBody IdListBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/refuseContract")
    public WebApiResponse<Boolean> refuseContract(@RequestBody IdListBean bean) {
        return WebApiResponse.success(true);
    }
}
