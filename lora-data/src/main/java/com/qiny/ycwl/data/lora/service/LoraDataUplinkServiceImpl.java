package com.qiny.ycwl.data.lora.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qiny.ycwl.data.lora.dao.AppDataProcessor;
import com.qiny.ycwl.data.lora.dao.DemoProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding({AppDataProcessor.class, DemoProcessor.class})
public class LoraDataUplinkServiceImpl implements LoraDataUplinkService {
    private static Logger log = LoggerFactory.getLogger(LoraDataUplinkServiceImpl.class);
    @Autowired
    @Output(AppDataProcessor.DATA_FROM_APP)
    private MessageChannel dataFromAppChannel;

    @Override
    @HystrixCommand(fallbackMethod = "sendFailed")
    public void sendAppData(String msg) {
        dataFromAppChannel.send(MessageBuilder.withPayload(msg).build());
        log.info("send message to kafka client ： " + msg);
    }

    public void sendFailed(String msg) {
        log.error("send: " + msg + " failed");
    }
}
