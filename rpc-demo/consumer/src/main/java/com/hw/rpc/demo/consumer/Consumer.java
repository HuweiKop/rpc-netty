package com.hw.rpc.demo.consumer;

import com.hw.rpc.demo.api.IUserService;
import com.hw.rpc.demo.model.User;
import com.hw.rpc.netty.client.RemoteProxyFactory;

/**
 * Created by huwei on 2017/4/5.
 */
public class Consumer {
    public static void main(String[] args){
        RemoteProxyFactory factory = new RemoteProxyFactory(8020,"com.hw.rpc.demo.api");
        IUserService service = factory.getBean(IUserService.class);
        User user = service.getUserById("111");
        System.out.println(user.getUsername());
        System.out.println(user.getAge());
    }
}
