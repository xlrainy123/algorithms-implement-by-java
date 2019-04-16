package netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NIOMessageClient {

    class SendThread implements Runnable{
        @Override
        public void run() {
            Scanner in = new Scanner(System.in);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            try {
                while (in.hasNext()){
                    String content = in.nextLine();
                    System.out.println(content);
                    buffer.put(content.getBytes());
                    buffer.flip();
                    System.out.println(buffer.position()+","+buffer.limit());
                    channel.write(buffer);   //channel.write(buffer);被阻塞了
                    if (buffer.hasRemaining()){
                        System.out.println("还有数据没发送完");
                        handlerTCPError();      //暂时先不处理
                    } else {
                        System.out.println("发送完成");
                    }
                    buffer.clear();//                    buffer.compact();
                }
            } catch (IOException e){
                //todo
            }
        }

        private void handlerTCPError(){}

        private SocketChannel channel;
        SendThread(SocketChannel channel){
            this.channel = channel;
        }
    }

    class ReactorThread implements Runnable{
        @Override
        public void run() {
            doConnect();
            while (true) {
                try {
                    while (selector.select() > 0){
                        System.err.println("有事件发生");
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> keyIt = selectionKeys.iterator();
                        while (keyIt.hasNext()){
                            SelectionKey currentKey = keyIt.next();
                            keyIt.remove();
                            try {
                                channelHandler(currentKey);
                            } finally {
                                shutDownGracefully(currentKey);
                            }
                        }
                    }
                }catch (IOException e){
                    //todo
                }
            }
        }


        public void channelHandler(SelectionKey currentKey) throws IOException{
            if (!currentKey.isValid()){
                System.exit(-1);
            }
            SocketChannel socketChannel = (SocketChannel)currentKey.channel();
            if (currentKey.isConnectable()){
                System.err.println("isConnectable");
                if (socketChannel.finishConnect()){
                    socketChannel.register(selector, SelectionKey.OP_READ);
//                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                    System.err.println(socketChannel.validOps());
                    doWrite(socketChannel);
                } else {
                    System.out.println("链接失败");
                    System.exit(-1);
                }
            }

            if (currentKey.isReadable()){
                System.err.println("isReadable");
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = socketChannel.read(buffer);
                if (readBytes > 0){
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String inputContent = new String(bytes, "utf-8");
                    System.out.println(inputContent);
                } else if(readBytes < 0) {
                    shutDownGracefully(currentKey);
                } else{
                    //todo
                }
            }

            if (currentKey.isWritable()){
                System.err.println("isWritable");
                System.err.println("11111111111111111");
            }
        }


        private void shutDownGracefully(SelectionKey currentKey) throws IOException{
            if (currentKey != null){
                currentKey.cancel();
                if (currentKey.channel() != null){
                    currentKey.channel().close();
                }
            }
        }

        private void doConnect(){
            try {
                if (socketChannel.connect(new InetSocketAddress(host,port))){
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    doWrite(socketChannel);
                } else {
                    System.out.println("1111");
                    socketChannel.register(selector, SelectionKey.OP_CONNECT);
                }
            }catch (IOException e){
                //todo
            }
        }

        private void doWrite(SocketChannel channel){
            System.out.println("开始准备发送数据");
            new Thread(new SendThread(channel)).start();
        }
    }

    private String host;
    private int port;
    private SocketChannel socketChannel;
    private Selector selector;

    private SendThread sendThread ;
    private ReactorThread acceptThread;

    public NIOMessageClient(String host, int port){
        this(port);
        this.host = host;
    }
    public NIOMessageClient(int port){
        this.host = "127.0.0.1";
        this.port = port;
        try {
            socketChannel = SocketChannel.open();
            selector = Selector.open();
        } catch (IOException e) {
            //todo
        }
    }

    public void startUp(){
        acceptThread = new ReactorThread();
        Thread thread = new Thread(acceptThread);
        thread.start();
    }
    public static void main (String[] args) throws IOException{
        NIOMessageClient client = new NIOMessageClient(9090);
        client.socketChannel.configureBlocking(false);
        client.startUp();
    }


}
