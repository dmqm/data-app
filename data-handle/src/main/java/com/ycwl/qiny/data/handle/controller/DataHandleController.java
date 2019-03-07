package com.ycwl.qiny.data.handle.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ycwl.qiny.data.handle.service.DataHandleService;
import com.ycwl.qiny.data.handle.util.ConstEnum;
import com.ycwl.qiny.data.handle.util.DataHandleError;
import com.ycwl.qiny.data.handle.util.JsonParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 16:15 2019/2/26
 * @ Description：
 */
@RestController
@RequestMapping("energy/electric")
public class DataHandleController {
    private static Logger log = LoggerFactory.getLogger(DataHandleController.class);
    @Autowired
    private DataHandleService dataHandleService;

    @Value("${app.id}")
    private String app_id;

    @RequestMapping("/uplink")
 //   @HystrixCommand(fallbackMethod = "getUplinkDataFailedInController")
    public int getUplinkData(@RequestBody String message) {
        Map map = JsonParseUtil.parseNestJson(message);
        if (map.containsKey(ConstEnum.APP_ID.getName()) && map.get(ConstEnum.APP_ID.getName()).equals("123")) {
            dataHandleService.handleUplinkData(map);
        }
        return DataHandleError.RECEIVE_SUCCESS.getCode();
    }

    protected int getUplinkDataFailedInController(@RequestBody String message) {
        return DataHandleError.SERVICE_IS_NOT_AVAILABLE.getCode();
    }
}
