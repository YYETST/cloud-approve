package com.yonyou.cloudapprove;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yonyou.cloudapprove.entity.FileEntity;
import com.yonyou.cloudapprove.service.BillService;
import com.yonyou.cloudapprove.utils.Base64File;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.request.form.IFormDataQueryCondition;
import yonyou.bpm.rest.request.form.IFormDataQueryParam;
import yonyou.bpm.rest.request.form.IFormSubmitData;
import yonyou.bpm.rest.request.form.IFormSubmitParam;
import yonyou.bpm.rest.request.identity.BasicdataQueryParam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class BillTest {

    @Value("${rest.teant}")
    private String teant;
    //协同表单的source
    private String source = "ESN";
    //访问本系统的token
    private final static String token="dsfdfgagfdgfdaewvdg";


    @Autowired
    BillService billService;

    /**
     * 获取审批流附件
     */
    @Test
    public void getFiles() throws RestException {
        ObjectNode result = (ObjectNode) billService.getBillFiles("71c1ace9d4a6422f85244d44ee4e673a:bea0303d91b94f91bb71169ec431b5b5");
        System.out.println("这是查询审批流附件返回的json数据:"+ result.toString());
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

    /**
     * 修改表单数据
     */
    @Test
    public void updateParam() throws RestException, IOException {
        IFormSubmitParam param = new IFormSubmitParam();
        String businessKey = "9377535fec78427baf035441eab7a0da:bea0303d91b94f91bb71169ec431b5b5";
        IFormDataQueryParam queryParam = billService.getPkBoAndBoins(businessKey);
        param.setPkBo(queryParam.getPkBo());
        param.setPkBoins(queryParam.getPkBoins());
        List<IFormSubmitData> list = new ArrayList<IFormSubmitData>();
        IFormSubmitData updateData = new IFormSubmitData();
        //修改一个附件 -------------------
        //附件字段名   ----获取审批流附件会返回key
        updateData.setColumnCode("fj_1590733790029381");
        List<FileEntity>  fileEntities = new ArrayList<FileEntity>();
        FileEntity fileEntity= new FileEntity();
        fileEntity.setType("image/png");
        fileEntity.setFilesize(19696);
        fileEntity.setSize(19696);
        fileEntity.setName("2.png");
        fileEntity.setFilename("2.png");
        fileEntity.setUrl("http://cs-doc-manager.test.app.yyuap.com/mybook/selfbuild/images/1.png");
        fileEntities.add(fileEntity);
        updateData.setValue(fileEntities);
        list.add(updateData);
        param.setFormData(list);
        String result = billService.updateBill(param);
        System.out.println("这是查询审批流附件返回的json数据:"+ result.toString());
    }



    /**
     * 获取表单字段
     */
    @Test
    public void getBillParam() throws RestException {
        ObjectNode result = (ObjectNode) billService.getBillParams("1aaa2ff042d34e3ea9a1983ec1222fdc:2cb072174d3b435ab81225f3be2ca2c6");
        System.out.println("这是查询表单字段返回的json数据:"+ result.toString());
    }

    /**
     * 修改表单数据 --非文件
     *
     */
    @Test
    public void updateBillData() throws RestException, IOException {
        IFormSubmitParam param = new IFormSubmitParam();
        String businessKey = "9377535fec78427baf035441eab7a0da:bea0303d91b94f91bb71169ec431b5b5";
        IFormDataQueryParam queryParam = billService.getPkBoAndBoins(businessKey);
        param.setPkBo(queryParam.getPkBo());
        param.setPkBoins(queryParam.getPkBoins());
        List<IFormSubmitData> list = new ArrayList<IFormSubmitData>();
        IFormSubmitData updateData = new IFormSubmitData();
        //修改一个附件 -------------------
        //附件字段名   ----获取审批流附件会返回key
        updateData.setFieldId("3dabe73c9f894676932dfe45978ea234");
        updateData.setValue("nscer");
        list.add(updateData);
        param.setFormData(list);
        String result = billService.updateBill(param);
        System.out.println("这是查询审批流附件返回的json数据:"+ result.toString());
    }
}
