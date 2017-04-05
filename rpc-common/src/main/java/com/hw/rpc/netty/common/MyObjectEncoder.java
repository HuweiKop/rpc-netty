package com.hw.rpc.netty.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by huwei on 2017/4/5.
 */
public class MyObjectEncoder extends ObjectEncoder {
    @Override
    protected void encode(ChannelHandlerContext ctx, Serializable msg, ByteBuf out) throws Exception {
        int startIdx = out.writerIndex();
        ByteBufOutputStream byteOut = new ByteBufOutputStream(out);
        ObjectOutputStream oout = new ObjectOutputStream(byteOut);
        oout.writeObject(msg);
        oout.flush();
        oout.close();
        int endIdx = out.writerIndex();
        out.setInt(startIdx, endIdx - startIdx - 4);
    }
}
