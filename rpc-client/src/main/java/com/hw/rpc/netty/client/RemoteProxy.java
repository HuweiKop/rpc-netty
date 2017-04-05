package com.hw.rpc.netty.client;

import com.hw.rpc.netty.common.RequestMessage;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by huwei on 2017/4/5.
 */
public class RemoteProxy implements InvocationHandler {

    private int port;
    private String serverrName;

    public RemoteProxy(String serverName, int port){
        this.serverrName = serverName;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(this.serverrName);
        System.out.println(method.getName());
        RequestMessage message = new RequestMessage();
        message.setServerName(this.serverrName);
        message.setMethodName(method.getName());
        message.setParameters(args);
        message.setParameterTypes(method.getParameterTypes());
        message.setRequestId(UUID.randomUUID().toString());

        RemoteClient client = new RemoteClient(this.port);
        return client.send(message);
    }
}
