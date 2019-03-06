package com.qiny.ycwl.data.kafka.dao;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface RabbitProcessor {
    String DATA_FROM_APP = "data_from_app";

    @Input(DATA_FROM_APP)
    SubscribableChannel dataFromApp();
}
