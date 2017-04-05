package com.hw.rpc.netty.client;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huwei on 2017/4/5.
 */
public class ResultMapper {
    private static Map<String, Object> resultMap = new HashMap<>();

    public static void addResult(String key, Object result) {
        resultMap.put(key, result);
    }

    public static Object getResult(String key) {
        return resultMap.get(key);
    }
}
