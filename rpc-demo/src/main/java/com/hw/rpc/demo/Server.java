package com.hw.rpc.demo;

import com.hw.rpc.netty.server.RemoteServer;

/**
 * Created by huwei on 2017/4/5.
 */
public class Server {
    public static void main(String[] args){
        RemoteServer server = new RemoteServer(8020,"com.hw.rpc.deom");
    }
}
