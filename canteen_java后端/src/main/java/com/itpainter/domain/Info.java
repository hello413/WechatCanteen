package com.itpainter.domain;

import java.util.Arrays;

public class Info {
    private boolean flag;//后端返回结果正常为true，发生异常返回false
    private Object data;//后端返回结果数据对象
    private Object[] objects;
    private String errorMsg;

    @Override
    public String toString() {
        return "Info{" +
                "flag=" + flag +
                ", data=" + data +
                ", objects=" + Arrays.toString(objects) +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Info() {
    }

    public Info(boolean flag, Object data, Object[] objects, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.objects = objects;
        this.errorMsg = errorMsg;
    }
}
