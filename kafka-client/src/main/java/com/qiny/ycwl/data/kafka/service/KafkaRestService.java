package com.qiny.ycwl.data.kafka.service;

import java.net.URI;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 11:03 2018/10/12
 * @ Description：
 */
public interface KafkaRestService {
    public void sendMessage(String msg, URI uri);

    public String receiveMessage(URI uri);
}
