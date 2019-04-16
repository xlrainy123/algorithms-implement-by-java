package netty.time;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandler implements Runnable{

    private SocketChannel socketChannel;
    private Selector selector;

    TimeClientHandler(){
        try{
            socketChannel = SocketChannel.open();
            selector = Selector.open();
            socketChannel.configureBlocking(false);
        }catch (IOException e){
            //todo
        }
    }


    public void connect(){
        try{
            if (socketChannel.connect(new InetSocketAddress("127.0.0.1",9090))){
                socketChannel.register(selector, SelectionKey.OP_READ);
                work(socketChannel);
            } else {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        }catch (IOException e){
            //todo
        }
    }

    public void work(SocketChannel socketChannel) throws IOException{
        System.out.println("开始发送数据");
        ByteBuffer dist = ByteBuffer.allocate(512);
        byte[] orders = "hello".getBytes();
        dist.put(orders);
        dist.flip();
        socketChannel.write(dist);
        if (!dist.hasRemaining()){
            System.out.println("数据发送完毕");
        } else {
            //todo
        }
    }
    public void run (){
        connect();
        try {
            while (true){
                int num = selector.select();
                if (num <= 0){
                    continue;
                } else {
                    System.out.println(num);
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()){
                    SelectionKey current = it.next();
                    it.remove();
                    handler(current);
                }
            }
        } catch (IOException e){
            //todo
        }
    }

    public void handler(SelectionKey selectionKey) throws IOException{
        if (!selectionKey.isValid()){
            return ;
        }
        if (selectionKey.isConnectable()){
            System.out.println("isConnectable");
            SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
            if (socketChannel.finishConnect()){
                socketChannel.register(selector, SelectionKey.OP_READ);
                work(socketChannel);
            } else {
                System.out.println("连接失败");
                System.exit(-1);
            }
        }
        if (selectionKey.isReadable()){
            SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
            System.out.println("isReadable");
            ByteBuffer dist = ByteBuffer.allocate(512);
            int readBytes = socketChannel.read(dist);
            if (readBytes > 0){
                /**
                 * 接受到服务端这边发送过来的数据，但是解析不对，不知道怎么回事
                 */
                System.out.println("readBytes="+readBytes);
                dist.flip();
                System.out.println("dist.remaining()="+dist.remaining());
                byte[] bytes = new byte[dist.remaining()];
                dist.get(bytes);
                String contents = new String(bytes, "utf-8");
                System.out.println(contents);
            } else if (readBytes < 0){
                //todo
                System.out.println("111111111111");
            } else {
                System.out.println("222222222222");
                //todo
            }
        }
    }
}
