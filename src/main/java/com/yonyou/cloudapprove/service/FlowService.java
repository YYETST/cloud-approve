package com.yonyou.cloudapprove.service;

import org.springframework.stereotype.Service;
import yonyou.bpm.rest.*;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.request.category.CategoryQueryParam;
import yonyou.bpm.rest.request.historic.HistoricActivityQueryParam;
import yonyou.bpm.rest.request.historic.HistoricProcessInstancesQueryParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nishch
 * @version 1.0
 * @date 2020/5/22
 * @des  流程
 */
@Service
public class FlowService extends BaseService{

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
     * 获取审批人
     */
    public Object getApprovePerson(String businessKey) throws RestException {
        HistoryService historyService = getHistoryService();
        HistoricProcessInstancesQueryParam queryParam = new HistoricProcessInstancesQueryParam();
        queryParam.setBusinessKey(businessKey);
        queryParam.setReturnTasks(true);
        queryParam.setReturnTaskParticipants(true);
        queryParam.setFinished(false);
        Object result = historyService.getHistoricProcessInstances(queryParam);
        return result;
    }



    public HistoryService getHistoryService(){
        BpmRest bpmRest = BpmRests.getBpmRest(getBaseParam());
        return bpmRest.getHistoryService();
    }
}
