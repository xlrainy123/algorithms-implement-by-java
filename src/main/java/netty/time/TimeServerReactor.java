package netty.time;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeServerReactor implements Runnable{

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    TimeServerReactor(){
        try {
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1",9090), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器启动在9090端口");
        } catch (IOException e) {
            //todo
        }
    }
    public void run (){
        while (true){
            try{
                int num = selector.select();
                if (num <= 0){
                    continue;
                } else {
                    System.out.println("num:"+num);
                    Thread.sleep(1000);
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey current = null;
                while (it.hasNext()){
                    current = it.next();
                    it.remove();
                    handlerRequest(current);
                }
            }catch (Exception e){

            }
        }
    }

    public void handlerRequest(SelectionKey current) throws IOException{
        if (!current.isValid()){
            return;
        }
        if (current.isAcceptable()){
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel)current.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.err.println("欢迎" + socketChannel.socket().getPort() + "的到来");
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("333333");
        }
        if (current.isReadable()){
            System.out.println("444444");
            SocketChannel socketChannel = (SocketChannel)current.channel();
            ByteBuffer dist = ByteBuffer.allocate(256);
            int readBytes = socketChannel.read(dist);
            if (readBytes > 0){
                dist.flip();
                byte[] toBytes = new byte[dist.remaining()];
                dist.get(toBytes);
                String contents = new String(toBytes, "utf-8");
                System.out.println("接收到的内容为："+contents);
                long currentTime = System.currentTimeMillis();
                String sendContents = "Now time is:"+currentTime;
                dist.clear();
                dist.put(sendContents.getBytes());
                dist.flip();
                System.out.println("dist.position()="+dist.position()+", dist.limit()="+dist.limit());
                System.err.println("数据回复完毕，字数为:"+socketChannel.write(dist));
            } else if (readBytes < 0){
                //todo
                System.out.println("readBytes:"+readBytes);
            } else {
                System.out.println("2222");
            }
        }
    }
}
