package com.qiny.ycwl.data.kafka.controller;

import com.qiny.ycwl.data.kafka.KafkaClientApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KafkaClientApplication.class)
public class LoraDataControllerTest {

    @Test
    public void receiveMessage() {
    }

    @Test
    public void receiveDLQMessage() {
    }
}