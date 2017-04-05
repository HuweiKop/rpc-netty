package com.hw.rpc.netty.server;

import com.hw.rpc.netty.common.RequestMessage;
import com.hw.rpc.netty.common.ResponseMessage;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

/**
 * Created by huwei on 2017/4/4.
 */
public class ObjectServerHandler extends SimpleChannelInboundHandler<RequestMessage> {

    protected void channelRead0(io.netty.channel.ChannelHandlerContext channelHandlerContext, RequestMessage message) throws Exception {
        System.out.println(message.getRequestId());
        System.out.println(message.getServerName());
        System.out.println(message.getMethodName());
        Object service = RemoteServer.getService(message.getServerName());
        System.out.println(service);
        Method method = service.getClass().getMethod(message.getMethodName(),message.getParameterTypes());
        Object result = method.invoke(service,message.getParameters());
        System.out.println(result);
        ResponseMessage response = new ResponseMessage();
        response.setRequestId(message.getRequestId());
        response.setResult(result);
        channelHandlerContext.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    System.out.println("服务器响应完毕。。。");
                }
            }
        });
    }
}
