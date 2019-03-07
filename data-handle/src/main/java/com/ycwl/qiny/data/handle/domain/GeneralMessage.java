package com.ycwl.qiny.data.handle.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 15:22 2019/2/27
 * @ Description：
 */
public class GeneralMessage extends Message{
    private String data;

    public GeneralMessage(int application_id, String application_name, String data) {
        super(application_id,application_name);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        Map map = new HashMap();
        map.put("application_id", this.getApplication_id());
        map.put("application_name", this.getApplication_name());
        map.put("data",data);
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map));
        return json.toJSONString();
    }
}
