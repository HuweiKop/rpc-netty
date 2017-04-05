package com.hw.rpc.demo.provider.service;

import com.hw.rpc.demo.api.IUserService;
import com.hw.rpc.demo.model.User;
import com.hw.rpc.netty.common.RpcService;

/**
 * Created by huwei on 2017/4/5.
 */
@RpcService("userService")
public class UserServiceImpl implements IUserService {
    public User getUserById(String id) {
            System.out.println("user id ======"+id);
            User user = new User();
            user.setUsername("user1");
            user.setAge(23);
            return user;
    }
}
