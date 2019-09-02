package com.tap2up.utils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zxb
 * Date: 2019/08/19
 * Description:
 * Version: V1.0
 */
public class AlfModel {
    private String code;
    private String description;
    private List result;

    public AlfModel() {
    }

    public AlfModel(String code, String description, List result) {
        this.code = code;
        this.description = description;
        this.result = result;
    }

    public AlfModel(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getReturncode() {
        return code;
    }

    public void setReturncode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return description;
    }

    public void setMsg(String description) {
        this.description = description;
    }

    public List getData() {
        return result;
    }

    public void setData(List result) {
        this.result = result;
    }
}
