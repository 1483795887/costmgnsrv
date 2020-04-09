package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.Contract;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.service.ContractService;
import com.costmgn.costmgnsrv.service.WorkService;
import com.costmgn.costmgnsrv.utils.IdListBean;
import com.costmgn.costmgnsrv.utils.PostHelper;
import com.costmgn.costmgnsrv.utils.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractControllerTest {
    private PostHelper postHelper;

    private User user;
    private Contract contract;
    private ArgumentCaptor<User> userArgumentCaptor;
    private ArgumentCaptor<Contract> contractArgumentCaptor;

    private static final String URL_ADD_CONTRACT = "/contract/addContract";
    private static final String URL_UPDATE_CONTRACT = "/contract/updateContract";
    private static final String URL_GET_CONTRACT = "/contract/getContract";
    private static final String URL_GET_CONTRACTS = "/contract/getContracts";
    private static final String URL_SUBMIT_CONTRACT = "/contract/submitContract";
    private static final String URL_APPROVE_CONTRACT = "/contract/approveContract";
    private static final String URL_REFUSE_CONTRACT = "/contract/refuseContract";

    @MockBean
    private ContractService service;
    @MockBean
    private WorkService workService;
    @Autowired
    private WebApplicationContext context;


    @Before
    public void setUp() {
        postHelper = new PostHelper(context);
        user = new User();
        user.setName("张三");

        contract = new Contract();
        contract.setTitle("testContract");

        userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        contractArgumentCaptor = ArgumentCaptor.forClass(Contract.class);
    }

    @Test
    public void shouldCallRightWhenAddContract() throws Exception {
        postHelper.setSessionItem("user", user);
        postHelper.getBoolean(URL_ADD_CONTRACT, contract);

        verify(service).addContract(contractArgumentCaptor.capture(), userArgumentCaptor.capture());
        assertEquals(contract.getTitle(), contractArgumentCaptor.getValue().getTitle());
        assertEquals(user.getName(), userArgumentCaptor.getValue().getName());
    }

    @Test
    public void shouldCallRightWhenUpdateContract() throws Exception {
        contract.setTitle("test321");

        postHelper.getBoolean(URL_UPDATE_CONTRACT, contract);
        verify(service).updateContract(contractArgumentCaptor.capture());
        assertEquals(contractArgumentCaptor.getValue().getTitle(), "test321");
    }

    @Test
    public void shouldCallRightWhenGetContract() throws Exception {
        postHelper.getData(URL_GET_CONTRACT, 1, Contract.class);
        verify(service).getContract(1);
    }

    @Test
    public void shouldCallRightWhenSubmitContract() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_SUBMIT_CONTRACT, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(workService, times(10))
                .updateStatus(anyInt(), eq(Status.NOT_AUDITED.ordinal()));
    }

    @Test
    public void shouldCallRightWhenRefuseContract() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_REFUSE_CONTRACT, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(workService, times(10))
                .updateStatus(anyInt(), eq(Status.REFUSED.ordinal()));
    }

    @Test
    public void shouldCallRightWhenApproveContract() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_APPROVE_CONTRACT, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(workService, times(10))
                .updateStatus(anyInt(), eq(Status.FINISHED.ordinal()));
    }

    @Test
    public void shouldCallRightWhenGetContracts() throws Exception {
        postHelper.setSessionItem("user", user);
        postHelper.getList(URL_GET_CONTRACTS, 1, Contract.class);
        verify(service).getContracts(userArgumentCaptor.capture(), eq(1));
        assertEquals(userArgumentCaptor.getValue().getName(), user.getName());
    }
}
