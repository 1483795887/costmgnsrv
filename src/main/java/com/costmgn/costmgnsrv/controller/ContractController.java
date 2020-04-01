package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.bean.ContractBean;
import com.costmgn.costmgnsrv.service.ContractService;
import com.costmgn.costmgnsrv.utils.WebApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/contract", method = RequestMethod.POST)
public class ContractController {
    private ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @RequestMapping("/addContract")
    public WebApiResponse<Boolean> addContract(@RequestBody ContractBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/updateContract")
    public WebApiResponse<Boolean> updateContract(@RequestBody ContractBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getContracts")
    public WebApiResponse<List<ContractBean>> getContracts() {
        List<ContractBean> contractList = new ArrayList<>();
        return WebApiResponse.success(contractList);
    }

    @RequestMapping("/submitContract")
    public WebApiResponse<Boolean> submitContract(@RequestBody ContractBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/approveContract")
    public WebApiResponse<Boolean> approveContract(@RequestBody ContractBean bean) {
        return WebApiResponse.success(true);
    }

    @RequestMapping("/refuseContract")
    public WebApiResponse<Boolean> refuseContract(@RequestBody ContractBean bean) {
        return WebApiResponse.success(true);
    }
}
