package com.qiny.ycwl.data.collect.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qiny.ycwl.data.collect.service.DataHandleClient;
import com.qiny.ycwl.data.collect.util.DataCollectError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lora")
public class DataCollectController {
    private static Logger log = LoggerFactory.getLogger(DataCollectController.class);
    @Autowired
    private DataHandleClient dataHandleClient;

    @RequestMapping("/uplink")
    @HystrixCommand(fallbackMethod = "receiveFailedInController")
    public int receiveUplink(@RequestBody String message) {
        if (null != message) {
            log.info("receive from http :" + message);
            return dataHandleClient.getUplinkData(message);
        }
        return DataCollectError.RECEIVE_FAILED_IN_CONTROLLER.getCode();
    }

    protected int receiveFailedInController(@RequestBody String message) {
        return DataCollectError.SERVICE_IS_NOT_AVAILABLE.getCode();
    }
}
