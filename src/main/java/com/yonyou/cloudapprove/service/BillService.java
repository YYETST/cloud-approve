package com.yonyou.cloudapprove.service;

import org.springframework.stereotype.Service;
import yonyou.bpm.rest.BpmRest;
import yonyou.bpm.rest.BpmRests;
import yonyou.bpm.rest.FormService;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.param.BaseParam;
import yonyou.bpm.rest.request.form.*;

import java.util.ArrayList;
import java.util.List;

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
    public Object getBillContent(String businessKey,List<IFormDataQueryCondition> list) throws RestException {
        IFormDataQueryParam param =new IFormDataQueryParam();
        String[] pks = businessKey.split(":");
        param.setPkBo(pks[1]);
        param.setPkBoins(pks[0]);
        param.setSqlConditions(list);
        return  getFormService().queryIFormData(param);
    }


    /**
     * 获取表单所有数据
     * @param pkbo
     * @return
     * @throws RestException
     */
    public Object getBillAllContent(String pkbo,List<IFormDataQueryCondition> list) throws RestException {
        IFormDataQueryParam param =new IFormDataQueryParam();
        param.setPkBo(pkbo);
        param.setSqlConditions(list);
        return  getFormService().queryIFormData(param);
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
        formFieldQueryParam.setFormId(getPkBo(businessKey).getPkBo());
        return getFormService().queryFormFields(formFieldQueryParam);
    }


    /**
     * 获取表单字段pkbo
     * @return
     */
    public Object getBillParamsByPkbo(String pkbo) throws RestException {
        FormFieldQueryParam formFieldQueryParam=new FormFieldQueryParam();
        formFieldQueryParam.setFormId(pkbo);
        return getFormService().queryFormFields(formFieldQueryParam);
    }

    /**
     * 获取表单字段pkbo
     * @return
     */
    public Object getBillCode(String ifrom) throws RestException {
        BillCodeQueryParam params = new BillCodeQueryParam();
        params.setRulecode(ifrom);
        return getFormService().queryBillCodes(params);
    }

    /**
     * 保存数据
     * @return
     */
    public Object submitIForm(String yhtuserid,IFormSubmitParam parma) throws RestException {
        return getFormService(yhtuserid).submitIForm(parma);
    }

    public FormService getFormService(){
        BpmRest bpmRest = BpmRests.getBpmRest(getBaseParam());
        return bpmRest.getFormService();
    }

    public FormService getFormService(String yhtuserid){
        BaseParam param = getBaseParam();
        param.setOperatorID(yhtuserid);
        BpmRest bpmRest = BpmRests.getBpmRest(param);
        return bpmRest.getFormService();
    }


}
