package com.yonyou.cloudapprove.service;

import org.springframework.stereotype.Service;
import yonyou.bpm.rest.BpmRest;
import yonyou.bpm.rest.BpmRests;
import yonyou.bpm.rest.FormService;
import yonyou.bpm.rest.RepositoryService;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.request.form.IFormDataQueryCondition;
import yonyou.bpm.rest.request.form.IFormDataQueryParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author nishch
 * @version 1.0
 * @date 2020/5/25
 * @des
 */
@Service
public class BillService extends BaseService{

    /**
     * 获取表单数据
     * @param businessKey
     * @return
     * @throws RestException
     */
    public Object getBillContent(String businessKey) throws RestException {
        IFormDataQueryParam param =new IFormDataQueryParam();
        String[] pks = businessKey.split(":");
        param.setPkBo(pks[1]);
        param.setPkBoins(pks[0]);
        BpmRest bpmRest = BpmRests.getBpmRest(getBaseParam());
        FormService formService = bpmRest.getFormService();
        Object obj=formService.queryIFormData(param);
        return obj;
    }


}
