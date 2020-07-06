package com.yonyou.cloudapprove;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.scrt.DigestUtils;
import yonyou.bpm.rest.scrt.RestHMacParam;

@RunWith(SpringRunner.class)
@SpringBootTest
class Sign {

    @Value("${rest.token}")
    private  String approveToken;   //用友云审批的验证token
    @Value("${rest.teant}")
    private  String teant;
    /**
     * 获取sign
     */
    @Test
    public void getMySendTotal() throws RestException {
        //TODO:请填写自己要使用的url
        String url = "https://ys.yonyoucloud.com/ubpm-web-rest/service/query/ext/historic-process-instances/count";
        RestHMacParam param = new RestHMacParam();
        param.setRequestPath(url);
        param.setTenant(teant);
        String sign = DigestUtils.hmac(param, approveToken.trim(), DigestUtils.Algorithm_HMAC.HmacSHA1);
        System.out.println("这是获取到到的sign:"+ sign);
    }

}
