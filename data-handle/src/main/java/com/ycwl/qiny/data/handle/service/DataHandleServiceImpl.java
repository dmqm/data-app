package com.ycwl.qiny.data.handle.service;

import com.ycwl.qiny.data.handle.config.MqttGateway;
import com.ycwl.qiny.data.handle.domain.GeneralMessage;
import com.ycwl.qiny.data.handle.domain.Message;
import com.ycwl.qiny.data.handle.domain.NestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.Default;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 16:15 2019/2/26
 * @ Description：
 */
@Service
public class DataHandleServiceImpl implements DataHandleService {
    private static Logger log = LoggerFactory.getLogger(DataHandleServiceImpl.class);
    private static String PAYLOAD_NAME = "data";
    private static String POWER_NAME = "instantaneous_power";
    private static String APPLICATION_NAME = "application_name";
    private static String APPLICATION_ID = "application_id";
    @Autowired
    private MqttGateway mqttGateway;

    @Value("${power-bound.max}")
    private String powerMaxBound = "10000";
    @Value("${power-bound.min}")
    private String powerMinBound = "0";
    @Value("${power-bound.regular}")
    private String pattern = "(\\S|\\s)*";

    @Override
    public void handleUplinkData(Map map) {
        if (map.containsKey(PAYLOAD_NAME)&&map.containsKey(APPLICATION_NAME)) {
            String application_name=(String) map.get(APPLICATION_NAME);
            int application_id=(Integer)map.get(APPLICATION_ID);
            Object obj = map.get(PAYLOAD_NAME);
            if (obj instanceof Map) {
                Map payload = (Map) obj;
                if (boundDetect(map)) {
                    Message message=new NestMessage(application_id,application_name,payload);
                    mqttGateway.sendToMqtt(message.toString());
                }
            } else if (obj instanceof String) {
                if (regularDetect((String) obj)) {
                    Message message=new GeneralMessage(application_id,application_name,(String)obj);
                    mqttGateway.sendToMqtt(message.toString());
                }
            } else {
                Message message=new GeneralMessage(application_id,application_name,(String)obj);
                mqttGateway.sendToMqtt(message.toString());
            }
        }
    }

    private boolean boundDetect(Map data) {
        if (data.containsKey(POWER_NAME)) {
            String power = (String) data.get(POWER_NAME);
            if (power.compareTo(powerMaxBound) > 0 || power.compareTo(powerMinBound) < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean regularDetect(String data) {
        if (Pattern.matches(pattern, data)) {
            return true;
        }
        return false;
    }
}
