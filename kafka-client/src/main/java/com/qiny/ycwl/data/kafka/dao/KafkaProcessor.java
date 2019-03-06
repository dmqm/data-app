package com.qiny.ycwl.data.kafka.dao;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KafkaProcessor {
    String DATA_TO_KAFKA = "data_to_kafka";
    String DATA_FROM_KAFKA = "data_from_kafka";

    @Output(DATA_TO_KAFKA)
    MessageChannel dataToKafka();

    @Input(DATA_FROM_KAFKA)
    MessageChannel dataFromKafka();
}
