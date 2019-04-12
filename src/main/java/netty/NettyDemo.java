package netty;

import io.netty.channel.nio.NioEventLoopGroup;

public class NettyDemo {

    public void demo(){
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        boosGroup.next();
    }
    public static void main(String[] args){

    }
}
