package com.yonyou.cloudapprove;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yonyou.cloudapprove.service.BillService;
import com.yonyou.cloudapprove.service.FlowService;
import com.yonyou.cloudapprove.service.RegisterListenService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.request.form.IFormDataQueryCondition;
import yonyou.bpm.rest.request.identity.BasicDataResourceParam;
import yonyou.bpm.rest.request.identity.BasicdataQueryParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApproveListenTest {

    @Value("${rest.teant}")
    private String teant;
    //协同表单的source
    private String source = "ESN";
    //访问本系统的token
    private final static String token="dsfdfgagfdgfdaewvdg";

    //流程结束监听
    private final static String type = "process_listener";

    @Autowired
    RegisterListenService registerListenService;
    @Autowired
    BillService billService;

    /**
     * 查询云审监听
     */
    @Test
    public void getListen() throws RestException {
        BasicdataQueryParam queryParam = new BasicdataQueryParam();
        queryParam.setTenantId(teant);
        ObjectNode result = (ObjectNode) registerListenService.select(queryParam);
        System.out.println("这是查询云审监听返回的json数据:"+ result.toString());
    }

    /**
     * 测试云审监听
     */
    @Test
    public void testListen() throws RestException {
        BasicDataResourceParam params = new BasicDataResourceParam();
        params.setCode("haikang");
        params.setName("测试云审批6192");
        params.setUrl("http://10.6.255.27/approve/listen");
        //流程id为：租户Id+source
        params.setProcDefId("iform_5aca65946e");   //---获取方式请看images/3.png
        params.setTenantId(teant);
        params.setSource(source);
        params.setToken(token);
        params.setType(type);
        ObjectNode object = (ObjectNode)registerListenService.register(params);
        System.out.println("这是注册监听返回的json数据:"+ object.toString());
    }


    @Test
    public void deleteListen() throws RestException {
        String id="56632d39-b1d2-11ea-b96f-1a8e19373b3f";
        boolean result = registerListenService.delete(id);
        System.out.println("这是删除监听返回的数据:"+ result);
    }

    /**
     * 根据businesskey获取表单内容
     * @throws RestException
     */
    @Test
    public void getBillContent() throws RestException {
        String businessKey = "7f9d082cb627468faec1dd2069f67481:2cb072174d3b435ab81225f3be2ca2c6";
        List<IFormDataQueryCondition> list = new ArrayList<IFormDataQueryCondition>();
        IFormDataQueryCondition iFormDataQueryCondition = new IFormDataQueryCondition();
        iFormDataQueryCondition.setColumnCode("ts");
        iFormDataQueryCondition.setColumnValue("2020-06-04 20:25:38");
        list.add(iFormDataQueryCondition);
        ObjectNode obj = (ObjectNode) billService.getBillContent(businessKey,list);
        System.out.println("这是获取到表单json数据:"+ obj.toString());
    }

    @Test
    public void update() throws RestException {
        BasicDataResourceParam basicData = new BasicDataResourceParam();
        basicData.setId("d569cc3a-9f28-11ea-9458-9aa2e21290bb");
        basicData.setProcDefId("iform_3e41d17055:1:9267b1fe-9e46-11ea-9458-9aa2e21290bb");
        ObjectNode object = (ObjectNode)registerListenService.update(basicData);
        System.out.println("这是修改监听返回的json数据:"+ object.toString());
    }

}
