package com.yonyou.cloudapprove.entity;

import java.io.Serializable;

/**
 * @author nishch
 * @version 1.0
 * @date 2020/7/21
 * @des
 */


public class BillParamsEntity implements Serializable {


    public BillParamsEntity() {
    }

    public BillParamsEntity(String fieldId, String name) {
        this.fieldId = fieldId;
        this.name = name;
    }

    private String fieldId;
    private String name;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{fieldId:"+fieldId+",name:"+name+"}";
    }
}
