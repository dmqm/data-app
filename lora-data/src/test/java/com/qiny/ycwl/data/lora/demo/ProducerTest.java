package com.qiny.ycwl.data.lora.demo;

import com.qiny.ycwl.data.lora.LoraDataApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 15:56 2018/10/9
 * @ Description：
 */
@SpringBootTest(classes = LoraDataApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProducerTest {
    @Autowired
    private Producer producer;

    @Test
    public void send() {
        producer.send("-------------------- test msg -----------------------------");
    }
}