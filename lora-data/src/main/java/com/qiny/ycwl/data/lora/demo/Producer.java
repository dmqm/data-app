package com.qiny.ycwl.data.lora.demo;


import com.qiny.ycwl.data.lora.dao.DemoProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(DemoProcessor.class)
public class Producer {
    Logger log = LoggerFactory.getLogger(Producer.class);
    @Autowired
    @Output(DemoProcessor.TEST_DEMO_OUTPUT)
    private MessageChannel channel;

    public void send(String msg) {
        channel.send(MessageBuilder.withPayload(msg).build());
        log.info("send : " + msg);
    }
}
