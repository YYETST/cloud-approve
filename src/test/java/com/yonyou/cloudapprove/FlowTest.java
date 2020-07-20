package com.yonyou.cloudapprove;

import com.yonyou.cloudapprove.service.FlowService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.scrt.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
class FlowTest {

    @Autowired
    FlowService flowService;

    /**
     * 获取流程目录
     */
    @Test
    public void getFlowDir() throws RestException {
        Object obj = flowService.getFlowDir();
        System.out.println("这是获取到表单json数据:"+ obj.toString());
    }
    /**
     * 获取流程信息
     * @throws RestException
     */
    @Test
    public void getApproveFlow() throws RestException {
        String businessKey = "10b30b807d624580bd8153334934f56c:ab37e4343de142fe9c386fc7fad32ce4";
        Object obj = flowService.getApprovePerson(businessKey);
        System.out.println("这是获取到表单json数据:"+ obj.toString());
    }

    /**
     * 获取我发起事项统计
     */
    @Test
    public void getMySendTotal() throws RestException {
        String yhtuserid = "0f059088-9c92-4769-a3e7-8f1a341cc3df";
        Object obj = flowService.getMySendTotal(yhtuserid);
        System.out.println("这是获取到表单json数据:"+ obj.toString());
    }

}
