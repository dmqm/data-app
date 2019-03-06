package com.qiny.ycwl.data.lora.dao;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 15:43 2018/10/9
 * @ Description：
 */
public interface DemoProcessor {
    String TEST_DEMO_INPUT = "test_demo_input";
    String TEST_DEMO_OUTPUT = "test_demo_output";

    @Input(TEST_DEMO_INPUT)
    MessageChannel testDemoInput();

    @Output(TEST_DEMO_OUTPUT)
    MessageChannel testDemoOutput();
}
