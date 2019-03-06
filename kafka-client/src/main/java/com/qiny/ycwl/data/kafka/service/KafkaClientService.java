package com.qiny.ycwl.data.kafka.service;

public interface KafkaClientService {

    public void sendMessage(String msg);

    public void receiveMessage(String msg);
}
