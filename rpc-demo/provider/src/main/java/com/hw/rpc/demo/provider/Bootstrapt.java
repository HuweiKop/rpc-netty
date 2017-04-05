package com.hw.rpc.demo.provider;

import com.hw.rpc.netty.server.RemoteServer;

/**
 * Created by huwei on 2017/4/5.
 */
public class Bootstrapt {
    public static void main(String[] args){
        RemoteServer server = new RemoteServer(8020,"com.hw.rpc.demo.provider");
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
