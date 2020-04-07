package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.Contract;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.mapper.ContractMapper;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.service.impl.ContractServiceImpl;
import com.costmgn.costmgnsrv.utils.Department;
import com.costmgn.costmgnsrv.utils.EntityType;
import com.costmgn.costmgnsrv.utils.Post;
import com.costmgn.costmgnsrv.utils.Status;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ContractServiceTest {

    private ContractService contractService;
    private ContractMapper contractMapper;
    private WorkMapper workMapper;
    private EntitySelector selector;

    private User salesman;

    @Before
    public void setUp() {
        contractMapper = mock(ContractMapper.class);
        workMapper = mock(WorkMapper.class);
        selector = mock(EntitySelector.class);
        contractService = new ContractServiceImpl(contractMapper, workMapper, selector);

        salesman = new User();
        salesman.setId(1);
        salesman.setDepartment(Department.SALES.ordinal());
        salesman.setPost(Post.SalesMan.ordinal());
    }

    @Test
    public void shouldCallContractInsertWhenAddContract() {
        Contract contract = new Contract();
        contract.setId(1);
        contractService.addContract(contract, new User());
        verify(contractMapper).insert(any(Contract.class));
        ArgumentCaptor<Work> workArgumentCaptor = ArgumentCaptor.forClass(Work.class);
        verify(workMapper).updateByPrimaryKey(workArgumentCaptor.capture());
        assertEquals(workArgumentCaptor.getValue().getEntityId(), 1);
        assertEquals(workArgumentCaptor.getValue().getType(), EntityType.CONTRACT.ordinal());
    }

    @Test
    public void shouldCallWorkInsertWhenAddContract() {
        ArgumentCaptor<Work> workArgumentCaptor = ArgumentCaptor.forClass(Work.class);
        contractService.addContract(new Contract(), salesman);
        verify(workMapper).insert(workArgumentCaptor.capture());
        Work work = workArgumentCaptor.getValue();
        assertEquals(work.getStatus(), Status.NOT_SUBMITTED.ordinal());
        assertEquals(work.getDepartment(), Department.SALES.ordinal());
    }

    @Test
    public void shouldCallSelectWhenGetContract() {
        contractService.getContract(1);
        verify(contractMapper).selectByPrimaryKey(1);
    }

    @Test
    public void shouldProcedureRightWhenUpdate() {
        Contract contract = new Contract();
        contract.setId(1);
        contractService.updateContract(contract);
        verify(contractMapper).updateByPrimaryKey(any(Contract.class));
    }

    @Test
    public void shouldCallSelectorWhenGetContracts() {
        contractService.getContracts(salesman, 1);
        verify(selector).getIds(salesman, 1, EntityType.CONTRACT.ordinal());
    }
}