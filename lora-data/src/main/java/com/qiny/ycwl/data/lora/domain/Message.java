package com.qiny.ycwl.data.lora.domain;

public class Message<T> {
    int id;
    T t;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
