package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.Contract;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.service.ContractService;
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
    public WebApiResponse<Boolean> addContract(@RequestBody Contract contract,
                                               HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        contractService.addContract(contract, user);
        return WebApiResponse.success(true);
    }

    @RequestMapping("/updateContract")
    public WebApiResponse<Boolean> updateContract(@RequestBody Contract bean) {
        contractService.updateContract(bean);
        return WebApiResponse.success(true);
    }

    @RequestMapping("/getContracts")
    public WebApiResponse<List<Contract>> getContracts(@RequestBody int type,
                                                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Contract> contractList = contractService.getContracts(user, type);
        return WebApiResponse.success(contractList);
    }

    @RequestMapping("/getContract")
    public WebApiResponse<Contract> getContract(@RequestBody int id) {
        return WebApiResponse.success(contractService.getContract(id));
    }

    @RequestMapping("/submitContract")
    public WebApiResponse<Boolean> submitContract(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            workService.updateStatus(id, Status.NOT_AUDITED.ordinal());
        }
        return WebApiResponse.success(true);
    }

    @RequestMapping("/approveContract")
    public WebApiResponse<Boolean> approveContract(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            workService.updateStatus(id, Status.FINISHED.ordinal());
        }
        return WebApiResponse.success(true);
    }

    @RequestMapping("/refuseContract")
    public WebApiResponse<Boolean> refuseContract(@RequestBody IdListBean bean) {
        for (int id : bean.getIdList()) {
            workService.updateStatus(id, Status.REFUSED.ordinal());
        }
        return WebApiResponse.success(true);
    }
}