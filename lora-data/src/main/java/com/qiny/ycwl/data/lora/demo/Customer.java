package com.qiny.ycwl.data.lora.demo;

import com.qiny.ycwl.data.lora.dao.DemoProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(DemoProcessor.class)
public class Customer {
    private Logger log = LoggerFactory.getLogger(Customer.class);

    @StreamListener(DemoProcessor.TEST_DEMO_INPUT)
    public void receive(String msg) {
        log.info("receive :  " + msg);
    }
}
