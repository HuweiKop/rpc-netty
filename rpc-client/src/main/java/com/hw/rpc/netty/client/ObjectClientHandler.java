package com.hw.rpc.netty.client;

import com.hw.rpc.netty.common.ResponseMessage;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by huwei on 2017/4/4.
 */
public class ObjectClientHandler extends SimpleChannelInboundHandler<ResponseMessage> {

    protected void channelRead0(io.netty.channel.ChannelHandlerContext channelHandlerContext, ResponseMessage message) throws Exception {
        ResultMapper.addResult(message.getRequestId(),message.getResult());
        System.out.println("handler..........");
    }
}
