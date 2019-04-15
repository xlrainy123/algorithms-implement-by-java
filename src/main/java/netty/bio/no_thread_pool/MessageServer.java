package netty.bio.no_thread_pool;

import netty.message.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author xlrainy
 */
public class MessageServer {

    private List<Socket> clients;
    private ServerSocket server;
    public static int defaultPort = 8888;

    public MessageServer(ServerSocket server){
        this.server = server;
    }

    public MessageServer(ServerSocket server, int port){
        this(server);
        this.defaultPort = port;
    }

    public void run() throws IOException {
        System.out.println("服务器开始启动！");
        while (true){
            Socket client = server.accept();
            System.out.println("连接建立完成，"+client.getPort());
            synchronized (clients){
                clients.add(client);
            }
            Thread eventThread = new Thread(new EventThread(client));
            eventThread.start();

        }
    }

    public void init(){
        clients = new ArrayList<>();
    }

    public static void main (String[] args) throws IOException{
        ServerSocket serverSocket =  new ServerSocket(defaultPort);
        MessageServer server = new MessageServer(serverSocket);
        server.init();
        server.run();
    }

    class EventThread implements Runnable{

        Socket client;
        BufferedReader reader;
        PrintWriter writer;

        EventThread(Socket client){
            this.client = client;
        }

        public void broadcast(String body) {
            Iterator<Socket> it = clients.iterator();
            while (it.hasNext()){
                Socket client = it.next();
                System.out.println(client);
                try{
                    writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                    writer.println("广播给"+client.getPort()+":"+body);
                    writer.flush();
                } catch (IOException e){
                    System.out.println("端口"+client.getPort()+"发送失败!");
                    it.remove();
                }
            }
        }

        public void run(){
            System.out.println("开始处理的客户端是："+client);
            String body = "";
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                while (true){
                    body = reader.readLine();
                    if (body == null || "".equals(body)){
                        continue;
                    }
                    broadcast(body);
                }
            }catch (IOException e) {
                //todo
            }
        }
    }
}
