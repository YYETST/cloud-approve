package com.yonyou.cloudapprove.service;

import org.springframework.stereotype.Service;
import yonyou.bpm.rest.BpmRest;
import yonyou.bpm.rest.BpmRests;
import yonyou.bpm.rest.FormService;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.request.form.FormFieldQueryParam;
import yonyou.bpm.rest.request.form.IFormDataQueryParam;
import yonyou.bpm.rest.request.form.IFormSubmitParam;

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
        return  getFormService().queryIFormData(getPkBoAndBoins(businessKey));
    }

    /**
     * 获取审批流程附件
     * @return
     */
    public Object getBillFiles(String businessKey) throws RestException{
        IFormDataQueryParam params = getPkBoAndBoins(businessKey);
        return getFormService().getIFormFileData(params.getPkBo(),params.getPkBoins());
    }

    /**
     * 更新表单数据
     * @param param
     * @return
     * @throws RestException
     */
    public String updateBill(IFormSubmitParam param) throws RestException {
        return getFormService().updateIFormData(param);
    }

    /**
     * 获取表单字段
     * @return
     */
    public Object getBillParams(String businessKey) throws RestException {
        FormFieldQueryParam formFieldQueryParam=new FormFieldQueryParam();
        formFieldQueryParam.setFieldId(getPkBo(businessKey).getPkBo());
        return getFormService().queryFormFields(formFieldQueryParam);
    }

    public FormService getFormService(){
        BpmRest bpmRest = BpmRests.getBpmRest(getBaseParam());
        return bpmRest.getFormService();
    }


}
