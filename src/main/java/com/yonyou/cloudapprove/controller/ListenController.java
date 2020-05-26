package com.yonyou.cloudapprove.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nishch
 * @version 1.0
 * @date 2020/5/22
 * @des 云审批监听事件注册
 */
@RestController
public class ListenController {

    @PostMapping("/approve/listen")
    public JSONObject approveContent(@RequestBody JSONObject request) {
        System.out.println(request.toString());  //request 会返回：businessKey  processDefinitionKey
        System.out.println(222+"--------------------");
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("msgSuccess",true);
        result.put("desc","ok");
        return (JSONObject) JSONObject.toJSON(result);
    }

}
