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
import yonyou.bpm.rest.request.identity.BasicDataResourceParam;
import yonyou.bpm.rest.request.identity.BasicdataQueryParam;

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
        ArrayNode node = (ArrayNode) result.get("data");
        System.out.println("这是查询云审监听返回的json数据:"+ node.get(0).toString());
    }

    /**
     * 测试云审监听
     */
    @Test
    public void testListen() throws RestException {
        BasicDataResourceParam params = new BasicDataResourceParam();
        params.setCode("haikang");
        params.setName("海康门卫监听");
        params.setUrl("http://310l2268a8.wicp.vip/approve/listen");
        //流程id为：租户Id+source
        params.setProcDefId("iform_3e41d17055");   //---获取方式请看images/3.png
        params.setTenantId(teant);
        params.setSource(source);
        params.setToken(token);
        ObjectNode object = (ObjectNode)registerListenService.register(params);
        System.out.println("这是注册监听返回的json数据:"+ object.toString());
    }


    @Test
    public void deleteListen() throws RestException {
        String id="ec0a040d-9f2f-11ea-9458-9aa2e21290bb";
        boolean result = registerListenService.delete(id);
        System.out.println("这是删除监听返回的数据:"+ result);
    }

    /**
     * 根据businesskey获取表单内容
     * @throws RestException
     */
    @Test
    public void getBillContent() throws RestException {
        String businessKey = "754ba83feac74e0cb6eaadda5451f654:598df2358ebf4a6ea5394e939378e4be";
        ObjectNode obj = (ObjectNode) billService.getBillContent(businessKey);
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
