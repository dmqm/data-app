package com.qiny.ycwl.data.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 11:04 2018/10/12
 * @ Description：
 */
@Service
public class KafkaRestServiceImpl implements KafkaRestService {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public void sendMessage(String msg, URI uri) {
        HttpEntity<String> request = new HttpEntity(msg);
        restTemplate.postForObject(uri, request, String.class);
    }

    @Override
    public String receiveMessage(URI uri) {
        return restTemplate.getForObject(uri, String.class);
    }
}
