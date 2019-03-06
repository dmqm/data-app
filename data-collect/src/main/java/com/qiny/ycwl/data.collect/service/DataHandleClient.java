package com.qiny.ycwl.data.collect.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("data-handle")
public interface DataHandleClient {

    @RequestMapping(method = RequestMethod.GET, value = "/energy/electric/uplink")
    public int getUplinkData(@RequestBody String message);
}
