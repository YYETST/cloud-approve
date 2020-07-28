package com.yonyou.cloudapprove.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;
import yonyou.bpm.rest.*;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.param.BaseParam;
import yonyou.bpm.rest.request.RestVariable;
import yonyou.bpm.rest.request.category.CategoryQueryParam;

import java.util.List;

/**
 * @author nishch
 * @version 1.0
 * @date 2020/7/20
 * @des  发起表单流程
 */
@Service
public class MyRuntimeService extends BaseService{

    /**
     * 获取流程目录
     * @return
     * @throws RestException
     */
    public Object getFlowDir() throws RestException {
        BpmRest bpmRest = BpmRests.getBpmRest(getBaseParam());
        CategoryService categoryService = bpmRest.getCategoryService();
        CategoryQueryParam categoryQueryParam = new CategoryQueryParam();
        Object result  = categoryService.getCategories(categoryQueryParam);
        return result;
    }


    /**
     * 发起流程
     * @return
     */
    public Object submit(String yhtuserid,String procDefId, String businessKey, List<RestVariable> variables) throws RestException {
        return getRuntimeService(yhtuserid).startProcessInstanceByKey(procDefId, businessKey,variables);
    }



    public RuntimeService getRuntimeService(String yhtuserid){
        BaseParam param = getBaseParam();
        param.setOperatorID(yhtuserid);
        BpmRest bpmRest = BpmRests.getBpmRest(param);
        return bpmRest.getRuntimeService();
    }
}
