package com.ycwl.qiny.data.handle.util;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 10:32 2019/2/27
 * @ Description：
 */
public enum DataHandleError {
    RECEIVE_SUCCESS("数据接收成功", 20100),
    RECEIVE_FAILED_IN_CONTROLLER("数据接收失败", 50101),
    SERVICE_IS_NOT_AVAILABLE("服务不可用", 50102);
    private String msg;
    private int code;

    private DataHandleError(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
