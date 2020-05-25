package com.yonyou.cloudapprove.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.impl.DefaultIdentityService;
import yonyou.bpm.rest.param.BaseParam;
import yonyou.bpm.rest.request.identity.BasicDataResourceParam;
import yonyou.bpm.rest.request.identity.BasicdataQueryParam;

/**
 * @author nishch
 * @version 1.0
 * @date 2020/5/22
 * @des  注册审批监听
 */
@Service
public class RegisterListenService extends BaseService{


    //流程结束监听
    private final static String type = "process_listener";

    /**
     * 注册监听流程
     * @param params
     * @return
     * @throws RestException
     */
    public Object register(BasicDataResourceParam params) throws RestException {
        DefaultIdentityService identityService = new DefaultIdentityService();
        identityService.setBaseParam(getBaseParam());
        params.setType(type);
        Object result = identityService.insertBasicData(params);
        return result;
    }

    /**
     * 查询监听流程
     * @param params
     * @return
     * @throws RestException
     */
    public Object select(BasicdataQueryParam params) throws RestException {
        DefaultIdentityService identityService = new DefaultIdentityService();
        identityService.setBaseParam(getBaseParam());
        Object result = identityService.queryBasicdatas(params);
        return result;
    }

    /**
     * 修改监听
     */
    public Object update(BasicDataResourceParam params) throws RestException {
        DefaultIdentityService identityService = new DefaultIdentityService();
        identityService.setBaseParam(getBaseParam());
        Object result = identityService.updateBasicData(params);
        return result;
    }


}
