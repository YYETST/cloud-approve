package com.yonyou.cloudapprove.service;

import org.springframework.beans.factory.annotation.Value;
import yonyou.bpm.rest.param.BaseParam;
import yonyou.bpm.rest.request.form.IFormDataQueryParam;

/**
 * @author nishch
 * @version 1.0
 * @date 2020/5/22
 * @des
 */
public class BaseService {

    @Value("${rest.server}")
    private  String base_url;
    @Value("${rest.teant}")
    private  String teant;
    //协同表单的source
    @Value("${rest.source}")
    private String source;
    @Value("${rest.token}")
    private  String approveToken;   //用友云审批的验证token

    public BaseParam getBaseParam(){
        BaseParam baseParam = new BaseParam();
        baseParam.setServer(base_url);
        baseParam.setTenant(teant);
        baseParam.setClientToken(approveToken);
        baseParam.setSource(source);
        return baseParam;
    }

    public IFormDataQueryParam getPkBoAndBoins(String businessKey){
        IFormDataQueryParam param =new IFormDataQueryParam();
        String[] pks = businessKey.split(":");
        param.setPkBo(pks[1]);
        param.setPkBoins(pks[0]);
        return param;
    }

    public IFormDataQueryParam getPkBo(String businessKey){
        IFormDataQueryParam param =new IFormDataQueryParam();
        String[] pks = businessKey.split(":");
        param.setPkBo(pks[1]);
        return param;
    }
}
