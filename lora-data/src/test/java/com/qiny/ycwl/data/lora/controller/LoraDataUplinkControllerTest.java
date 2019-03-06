package com.qiny.ycwl.data.lora.controller;

import com.qiny.ycwl.data.lora.LoraDataApplication;
import com.qiny.ycwl.data.lora.service.LoraDataUplinkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LoraDataApplication.class)
public class LoraDataUplinkControllerTest {

    @Autowired
    private LoraDataUplinkService loraDataUplinkService;

    @Test
    public void receiveUplink() {
    }
}