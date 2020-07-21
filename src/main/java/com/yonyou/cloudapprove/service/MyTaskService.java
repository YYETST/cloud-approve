package com.yonyou.cloudapprove.service;

import org.springframework.stereotype.Service;
import yonyou.bpm.rest.BpmRest;
import yonyou.bpm.rest.BpmRests;
import yonyou.bpm.rest.CategoryService;
import yonyou.bpm.rest.TaskService;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.request.category.CategoryQueryParam;
import yonyou.bpm.rest.request.task.TaskQueryParam;

/**
 * @author nishch
 * @version 1.0
 * @date 2020/7/21
 * @des  任务相关
 */
@Service
public class MyTaskService extends BaseService{

    /**
     * 获取代办统计
     * @return
     * @throws RestException
     */
    public Object queryTasksCount(String yhtuserid) throws RestException {
        TaskQueryParam param = new TaskQueryParam();
        param.setAssignee(yhtuserid);
        Object result  = getTaskService().queryTasksCount(param);
        return result;
    }

    public TaskService getTaskService(){
        BpmRest bpmRest = BpmRests.getBpmRest(getBaseParam());
        return bpmRest.getTaskService();
    }
}
