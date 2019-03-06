package com.ycwl.qiny.data.handle.domain;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 15:22 2019/2/27
 * @ Description：
 */
public class Message {
    private String application_name;
    private Map data;

    public Message(String application_name, Map data) {
        this.application_name = application_name;
        this.data = data;
    }

    public String getApplication_name() {
        return application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String json = JSONObject.toJSONString(data);
        StringBuilder ret = new StringBuilder();
        ret.append("{ \"application_name\": \"").append(application_name).append("\",").append("\"data\": ").append(json).append("}}");
        return ret.toString();
    }
}
