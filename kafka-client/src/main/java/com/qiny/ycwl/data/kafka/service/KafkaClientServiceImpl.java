package com.qiny.ycwl.data.kafka.service;

import com.qiny.ycwl.data.kafka.dao.KafkaProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(KafkaProcessor.class)
public class KafkaClientServiceImpl implements KafkaClientService {
    private static Logger log = LoggerFactory.getLogger(KafkaClientServiceImpl.class);
    @Autowired
    @Output(KafkaProcessor.DATA_TO_KAFKA)
    private MessageChannel dataToKafka;

    @Override
    public void sendMessage(String msg) {
        log.info("receive from rabbit : " + msg);
        dataToKafka.send(MessageBuilder.withPayload(msg).build());
        log.info("send to kafka : " + msg);
    }

    @Override
    @StreamListener(KafkaProcessor.DATA_FROM_KAFKA)
    public void receiveMessage(String msg) {
        log.info("receive from kafka : " + msg);
    }
}
