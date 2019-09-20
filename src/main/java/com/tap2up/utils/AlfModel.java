package com.tap2up.utils;

import com.alibaba.fastjson.JSON;
import com.tap2up.controller.AlfController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zxb
 * Date: 2019/08/19
 * Description:
 * Version: V1.0
 */
public class AlfModel<T> implements Serializable {
    private String code;
    private String description;
    private T result;

    public AlfModel() {
    }

    public AlfModel(String code, String description, T result) {
        this.code = code;
        this.description = description;
        this.result = result;
    }

    public AlfModel(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Map<String,String> Encrypt(String code, String description) {
        Map<String,String> map = new HashMap<>();
        JSON json = (JSON) JSON.toJSON(new AlfModel(code,description));
        String data = null;
        try {
            data = EncryptUtils.Encrypt(json.toString(), AlfController.aes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("data",data);
        return map;
    }

    public Map<String,String> Encrypt2(String code, String description,T t) {
        Map<String,String> map = new HashMap<>();
        JSON json = (JSON) JSON.toJSON(new AlfModel(code,description,t));
        String data = null;
        try {
            data = EncryptUtils.Encrypt(json.toString(), AlfController.aes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("data",data);
        return map;
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

    public T getData() {
        return result;
    }

    public void setData(T result) {
        this.result = result;
    }
}
