package netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class NIOMessageServer {

    private ServerSocketChannel serverChannnel;
    private Selector selector;
    private List<SocketChannel> clients;
    public NIOMessageServer(){
        try {
            serverChannnel = ServerSocketChannel.open();
            selector = Selector.open();
            clients = new ArrayList<>();
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void setBlocking(boolean blocking) {
        try {
            serverChannnel.configureBlocking(blocking);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bind(int port){
        try {
            serverChannnel.socket().bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bindAndSetBacklog(int port, int backlog){
        try {
            serverChannnel.socket().bind(new InetSocketAddress(port), backlog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SelectionKey register(int interest) {
        try {
            return serverChannnel.register(selector, interest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void work(){
        System.out.println("开始work");
        try {
            while (selector.select() > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIt = selectionKeys.iterator();
                while (keyIt.hasNext()){
                    SelectionKey currentKey = keyIt.next();
                    keyIt.remove();
                    try {
                        handleRequest(currentKey);
                    } finally {
                        shutDownGracefully(currentKey);
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void handleRequest(SelectionKey currentKey) throws IOException{
        if (!currentKey.isValid()){
            return;
        }
        if (currentKey.isAcceptable()){
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel)currentKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("欢迎"+socketChannel.socket().getPort()+"的到来");
            synchronized (clients) {
                clients.add(socketChannel);
            }
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
        if (currentKey.isReadable()){
            SocketChannel socketChannel = (SocketChannel)currentKey.channel();
            ByteBuffer distBuffer = ByteBuffer.allocate(1024);
            int readBytes = socketChannel.read(distBuffer);
            if (readBytes > 0){
                distBuffer.flip();      //limit = position; position = 0;
                byte[] bytes = new byte[distBuffer.remaining()];
                distBuffer.get(bytes);  //把distBuffer的数据读到bytes数组中
                String inputContent = new String(bytes, "utf-8");
                System.out.println("来自"+socketChannel.socket().getPort()+"的消息:"+inputContent);
                broadcast(inputContent);
            }
        }
    }

    public void broadcast(String content) throws IOException{
        ByteBuffer distBuffer = ByteBuffer.allocate(1024);
        for (SocketChannel socketChannel : clients){
            String sendMessage = "广播给"+socketChannel.socket().getPort()+"的信息是:"+content;
            distBuffer.put(sendMessage.getBytes());
            distBuffer.flip();
            socketChannel.write(distBuffer);
        }
    }

    public void shutDownGracefully(SelectionKey currentKey) throws IOException{
        if (currentKey != null){
            currentKey.cancel();
            if (currentKey.channel() != null){
                currentKey.channel().close();
            }
        }
    }
    public static void main(String[] args){
        NIOMessageServer server = new NIOMessageServer();
        server.setBlocking(false);
        server.bindAndSetBacklog(9090, 1024);
        SelectionKey key = server.register(SelectionKey.OP_ACCEPT);
        System.out.println("SelectionKey:"+key);
        server.work();
    }
}
