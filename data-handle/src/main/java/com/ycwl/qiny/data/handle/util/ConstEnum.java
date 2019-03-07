package com.ycwl.qiny.data.handle.util;

public enum ConstEnum {
    APP_ID("applicationID"),
    APP_NAME("applicationName"),
    POWER("instantaneousPower"),
    DATA("data");

    private String name;

    private ConstEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
