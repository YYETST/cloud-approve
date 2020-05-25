package com.yonyou.cloudapprove.service;

import org.springframework.stereotype.Service;
import yonyou.bpm.rest.BpmRest;
import yonyou.bpm.rest.BpmRests;
import yonyou.bpm.rest.CategoryService;
import yonyou.bpm.rest.RepositoryService;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.request.category.CategoryQueryParam;

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
     * @param categoryName
     * @return
     * @throws RestException
     */
    public Object getFlowDir(String categoryName) throws RestException {
        BpmRest bpmRest = BpmRests.getBpmRest(getBaseParam());
        CategoryService categoryService = bpmRest.getCategoryService();
        CategoryQueryParam categoryQueryParam = new CategoryQueryParam();
        categoryQueryParam.setName(categoryName);
        Object result  = categoryService.getCategories(categoryQueryParam);
        return result;
    }

    /**
     * 获取流
     */
    public Object getFlowDefine(String name) throws RestException {
        BpmRest bpmRest = BpmRests.getBpmRest(getBaseParam());
        RepositoryService repositoryService = bpmRest.getRepositoryService();
        Object result  = repositoryService.getProcessDefinition(name);
        return result;
    }
}
