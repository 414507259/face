package com.tap2up.utils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zxb
 * Date: 2019/08/19
 * Description:
 * Version: V1.0
 */
public class MyModel {
    private String returncode;
    private String msg;
    private List data;

    public MyModel() {
    }

    public MyModel(String returncode, String msg, List data) {
        this.returncode = returncode;
        this.msg = msg;
        this.data = data;
    }

    public MyModel(String returncode, String msg) {
        this.returncode = returncode;
        this.msg = msg;
    }

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
