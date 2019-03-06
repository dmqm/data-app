package com.qiny.ycwl.data.kafka.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 11:10 2018/10/12
 * @ Description：
 */
@Configuration
public class KafkaRestClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
