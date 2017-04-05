package com.hw.rpc.netty.client;

import com.hw.rpc.netty.common.MyObjectEncoder;
import com.hw.rpc.netty.common.RequestMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.UUID;

/**
 * Created by huwei on 2017/4/4.
 */
public class RemoteClient {

    private int port;

    public RemoteClient(int port) {
        this.port = port;
    }

    public <T> T send(RequestMessage message) throws Exception {
        EventLoopGroup work = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(work).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())))
                                .addLast(new ObjectEncoder()).addLast(new ObjectClientHandler());
                    }
                })
                .option(ChannelOption.SO_KEEPALIVE, false)
                .option(ChannelOption.SO_BACKLOG, 1024);
        ChannelFuture future = bootstrap.connect("127.0.0.1", this.port).sync();
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("客户端连接成功。。。。。");
                }
            }
        });
        ChannelFuture future1 = future.channel().writeAndFlush(message).sync();
        future.channel().closeFuture().sync();
        System.out.println("result.......");
        return (T) ResultMapper.getResult(message.getRequestId());
    }

    public static void main(String[] args) {
        RemoteClient client = new RemoteClient(8010);
        for (int i = 0; i < 1; i++) {
            RequestMessage message = new RequestMessage();
            message.setServerName("testService");
            message.setMethodName("sayHello");
            message.setRequestId(UUID.randomUUID().toString());
            try {
                RequestMessage msg = client.send(message);
                System.out.println(msg);
                System.out.println(msg.getServerName());
                System.out.println(msg.getMethodName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
