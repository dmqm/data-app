package com.ycwl.qiny.data.handle.domain;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 11:14 2019/3/7
 * @ Description：
 */
public abstract class Message {
    private int application_id;
    private String application_name;

    public int getApplication_id() {
        return application_id;
    }

    public void setApplication_id(int application_id) {
        this.application_id = application_id;
    }

    public String getApplication_name() {
        return application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    public abstract String toString();

    public Message(int application_id, String application_name) {
        this.application_id = application_id;
        this.application_name = application_name;
    }
}
