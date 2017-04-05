package com.hw.rpc.netty.server;

import com.hw.rpc.netty.common.MyObjectEncoder;
import com.hw.rpc.netty.common.ServiceContainer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by huwei on 2017/4/1.
 */
public class RemoteServer {

//    public volatile boolean start = false;

    private int port;
    private static ServiceContainer serviceContainer = new ServiceContainer();

    public RemoteServer(int port, String packagePath) {
        this.port = port;
        serviceContainer.init(packagePath);
    }

    /**
     * rpc server 逻辑
     */
    public synchronized void start() throws Exception {
//        if(start)
//            throw new Exception("服务器已经启动。。。");
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
        .childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(
                        new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())))
                .addLast(new ObjectEncoder()).addLast(new ObjectServerHandler());
            }
        }).option(ChannelOption.SO_BACKLOG, 1024)
        .option(ChannelOption.SO_KEEPALIVE, false);
        ChannelFuture future;
        try {
            future = bootstrap.bind(this.port).sync();
            future.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(channelFuture.isSuccess()){
//                        start = true;
                        System.out.println("服务器启动成功。。。");
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Object getService(String serviceName){
        return serviceContainer.getService(serviceName);
    }

    public static void main(String[] args){
        RemoteServer server = new RemoteServer(8010,"com.hw.rpc.netty");
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
