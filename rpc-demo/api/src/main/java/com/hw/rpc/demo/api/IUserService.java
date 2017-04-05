package com.hw.rpc.demo.api;

import com.hw.rpc.demo.model.User;
import com.hw.rpc.netty.common.RpcServiceApi;

/**
 * Created by huwei on 2017/4/5.
 */
@RpcServiceApi("userService")
public interface IUserService {
    User getUserById(String id);
}
