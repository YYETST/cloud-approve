package com.yonyou.cloudapprove;

import com.yonyou.cloudapprove.service.MyTaskService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yonyou.bpm.rest.exception.RestException;

@RunWith(SpringRunner.class)
@SpringBootTest
class MyTaskTest {

    @Autowired
    MyTaskService myTaskService;

    /**
     * 获取我的代办统计
     */
    @Test
    public void getMyTask() throws RestException {
        Object obj = myTaskService.queryTasksCount("0f059088-9c92-4769-a3e7-8f1a341cc3df");
        System.out.println("这是获取到表单json数据:"+ obj.toString());
    }

}
