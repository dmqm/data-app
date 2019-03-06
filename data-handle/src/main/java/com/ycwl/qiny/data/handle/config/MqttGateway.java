package com.ycwl.qiny.data.handle.config;

import org.springframework.integration.annotation.MessagingGateway;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 10:13 2019/2/27
 * @ Description：
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
    void sendToMqtt(String data);
}
