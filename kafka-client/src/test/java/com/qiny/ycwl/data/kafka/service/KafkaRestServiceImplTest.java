package com.qiny.ycwl.data.kafka.service;

import com.qiny.ycwl.data.kafka.KafkaClientApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 15:50 2018/10/17
 * @ Description：
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KafkaClientApplication.class)
public class KafkaRestServiceImplTest {
    Logger log = LoggerFactory.getLogger(KafkaRestServiceImplTest.class);
    @Autowired
    private KafkaRestService kafkaRestService;

    @Test
    public void sendMessage() throws URISyntaxException {
        kafkaRestService.sendMessage("test-message", new URI("http://171.217.92.131:28082/topics/kafka-test"));
    }

    /**
     * 可以收到topic
     */
    @Test
    public void receiveMessage() throws URISyntaxException {
        log.info(kafkaRestService.receiveMessage(new URI("http://171.217.92.131:28082/topics")));
    }
}