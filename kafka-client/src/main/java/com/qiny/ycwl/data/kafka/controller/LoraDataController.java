package com.qiny.ycwl.data.kafka.controller;

import com.qiny.ycwl.data.kafka.dao.RabbitProcessor;
import com.qiny.ycwl.data.kafka.service.KafkaClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(RabbitProcessor.class)
public class LoraDataController {
    @Autowired
    private KafkaClientService dataService;

    @StreamListener(RabbitProcessor.DATA_FROM_APP)
    public void receiveMessage(String msg) {
        dataService.sendMessage(msg);
    }
}
