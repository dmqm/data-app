package com.qiny.ycwl.data.lora.dao;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AppDataProcessor {
    String DATA_FROM_APP = "data_from_app";

    @Output(DATA_FROM_APP)
    MessageChannel dataFromApp();
}

