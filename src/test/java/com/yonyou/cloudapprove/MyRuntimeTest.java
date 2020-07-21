package com.yonyou.cloudapprove;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yonyou.cloudapprove.entity.BillParamsEntity;
import com.yonyou.cloudapprove.service.BillService;
import com.yonyou.cloudapprove.service.FlowService;
import com.yonyou.cloudapprove.service.MyRuntimeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.request.RestVariable;
import yonyou.bpm.rest.request.form.IFormSubmitData;
import yonyou.bpm.rest.request.form.IFormSubmitParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class MyRuntimeTest {

    @Autowired
    FlowService flowService;
    @Autowired
    MyRuntimeService myRuntimeService;
    @Autowired
    BillService billService;

    /**
     * 获取流程目录
     */
    @Test
    public void getFlowDir() throws RestException {
        Object obj = flowService.getFlowDir();
        System.out.println("这是获取到表单json数据:"+ obj.toString());
    }


    /**
     * 获取表单字段
     */
    @Test
    public void getBillParam() throws RestException {
        ObjectNode result = (ObjectNode) billService.getBillParamsByPkbo("ab37e4343de142fe9c386fc7fad32ce4");
        JsonNode jsonNode = result.get("data");
        List<BillParamsEntity> list = new ArrayList<BillParamsEntity>();
        if (jsonNode.isArray())
        {
            for (JsonNode objNode : jsonNode)
            {
                String tableFieldName = objNode.get("tableFieldName").toString();
                //保存表单用到的字段fieldId
                String fieldId = tableFieldName.replace("\"", "").substring(1);
                byte[] variableContent = JSONObject.parse(objNode.get("variableContent").toString()).toString().getBytes();
                Map<String,Object> obj =JSONObject.parseObject(variableContent,Map.class);
                //字段描述
                String name = obj.get("name").toString();
                list.add(new BillParamsEntity(fieldId,name));
            }
        }
        System.out.println("这是查询表单字段返回的json数据:"+ list.toString());
    }

    /**
     * 保存表单-并发起流程
     */
    @Test
    public void saveBill() throws RestException {
        IFormSubmitParam parmas = new IFormSubmitParam();
        parmas.setPkBo("ab37e4343de142fe9c386fc7fad32ce4");
        List<IFormSubmitData> list = new ArrayList<IFormSubmitData>();
        IFormSubmitData data = new IFormSubmitData();
        //获取表单字段返回的tableFieldName去除前缀f
        data.setFieldId("20200619102500w9gu9KrDrK");
        //字段值
        data.setValue("ccccvbb");
        list.add(data);
        parmas.setFormData(list);
        parmas.setStartProcess(true);
        ObjectNode result = (ObjectNode) billService.submitIForm(parmas);
        System.out.println("这是查询表单字段返回的json数据:"+ result.toString());
    }

}
