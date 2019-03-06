package com.qiny.ycwl.data.lora.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qiny.ycwl.data.lora.service.LoraDataUplinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/lora")
public class LoraDataUplinkController {
    private static Logger log = LoggerFactory.getLogger(LoraDataUplinkController.class);
    @Autowired
    private LoraDataUplinkService loraDataUplinkService;

    @RequestMapping("/uplink")
    @HystrixCommand(fallbackMethod = "receiveFailed")
    public boolean receiveUplink(@RequestBody Map<String, String> map) {
        if (map.containsKey("data")) {
            String payload = map.get("data");
            log.info("receive from http: " + payload);
            loraDataUplinkService.sendAppData(payload);
            return true;
        }
        return false;
    }

    protected boolean receiveFailed(@RequestBody Map<String, String> map) {
        log.info("receive from http failed");
        return true;
    }
}
