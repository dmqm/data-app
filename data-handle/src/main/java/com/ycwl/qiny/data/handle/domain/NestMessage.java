package com.ycwl.qiny.data.handle.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 15:22 2019/2/27
 * @ Description：
 */
public class NestMessage extends Message{
    private Map data;

    public NestMessage(int application_id, String application_name, Map data) {
        super(application_id,application_name);
        this.data = data;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    @Override
    public String toString() {
        Map ret = new HashMap(data);
        ret.put("application_id", this.getApplication_id());
        ret.put("application_name", this.getApplication_name());
        return ret.toString();
    }
}
