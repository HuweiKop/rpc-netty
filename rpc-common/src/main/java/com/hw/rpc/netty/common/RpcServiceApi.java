package com.hw.rpc.netty.common;

import java.lang.annotation.*;

/**
 * Created by huwei on 2017/4/5.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcServiceApi {
    String value() default "";
}
