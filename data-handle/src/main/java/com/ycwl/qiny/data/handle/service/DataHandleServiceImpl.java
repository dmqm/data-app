package com.ycwl.qiny.data.handle.service;

import com.ycwl.qiny.data.handle.config.MqttGateway;
import com.ycwl.qiny.data.handle.domain.GeneralMessage;
import com.ycwl.qiny.data.handle.domain.Message;
import com.ycwl.qiny.data.handle.domain.NestMessage;
import com.ycwl.qiny.data.handle.util.ConstEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private MqttGateway mqttGateway;

    //    @Value("${power-bound.max}")
    private String powerMaxBound = "10000";
    //    @Value("${power-bound.min}")
    private String powerMinBound = "0";
    //    @Value("${power-bound.regular}")
    private String pattern = "(\\S|\\s)*";

    @Override
    public void handleUplinkData(Map map) {
        if (map.containsKey(ConstEnum.DATA.getName()) && map.containsKey(ConstEnum.APP_NAME.getName())) {
            String application_name = (String) map.get(ConstEnum.APP_NAME.getName());
            int application_id = Integer.parseInt((String) map.get(ConstEnum.APP_ID.getName()));
            Object obj = map.get(ConstEnum.DATA.getName());
            if (obj instanceof Map) {
                Map payload = (Map) obj;
                if (boundDetect(map)) {
                    Message message = new NestMessage(application_id, application_name, payload);
                    mqttGateway.sendToMqtt(message.toString());
                }
            } else if (obj instanceof String) {
                if (regularDetect((String) obj)) {
                    Message message = new GeneralMessage(application_id, application_name, (String) obj);
                    mqttGateway.sendToMqtt(message.toString());
                }
            } else {
                Message message = new GeneralMessage(application_id, application_name, (String) obj);
                mqttGateway.sendToMqtt(message.toString());
            }
        }
    }

    private boolean boundDetect(Map data) {
        if (data.containsKey(ConstEnum.POWER.getName())) {
            String power = (String) data.get(ConstEnum.POWER.getName());
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
