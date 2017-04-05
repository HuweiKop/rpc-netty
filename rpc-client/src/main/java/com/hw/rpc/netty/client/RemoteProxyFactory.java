package com.hw.rpc.netty.client;

import com.hw.rpc.netty.common.ServiceContainer;

import java.lang.reflect.Proxy;

/**
 * Created by huwei on 2017/4/5.
 */
public class RemoteProxyFactory {

    private int port;
    private ServiceContainer serviceContainer = new ServiceContainer();

    public RemoteProxyFactory(int port, String packagePath){
        this.port = port;
        this.serviceContainer.init(packagePath);
    }

    public <T> T getBean(Class<T> serviceClass){
        String serverName= this.serviceContainer.getServiceName(serviceClass);
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),new Class[]{serviceClass},new RemoteProxy(serverName,this.port));
    }
}
