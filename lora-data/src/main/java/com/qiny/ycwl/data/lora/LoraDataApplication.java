package com.qiny.ycwl.data.lora;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringCloudApplication
@EnableHystrix
@EnableHystrixDashboard
public class LoraDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoraDataApplication.class, args);
    }
}
