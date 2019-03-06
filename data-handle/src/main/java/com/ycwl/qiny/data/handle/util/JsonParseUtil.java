package com.ycwl.qiny.data.handle.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 16:11 2019/2/27
 * @ Description：
 */
public class JsonParseUtil {
    public static Map<String, Object> parseNestJson(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject json = JSONObject.parseObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Iterator it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    Object json2 = it.next();
                    list.add(parseNestJson(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }
}
