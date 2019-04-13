package netty.bio.no_thread_pool;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class MessageClient {
    private Socket client;
    private SendThread sendThread;
    private AcceptThread acceptThread;

    MessageClient(Socket client){
        this.client = client;
    }

    class SendThread implements Runnable{

        Socket client;
        Scanner in = new Scanner(System.in);
        PrintWriter writer;

        SendThread(Socket client){
            this.client = client;
        }

        public void run (){
            String line = "";
            try {
                writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                while (in.hasNext()) {
                    line = in.nextLine();
                    writer.println(line);
                    writer.flush();
                }
            } catch (IOException e) {
                //todo
            }
        }
    }

    class AcceptThread implements Runnable{
        Socket socket;
        BufferedReader reader;
        AcceptThread(Socket socket){
            this.socket = socket;
        }
        public void run (){
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true){
                    String body = reader.readLine();
                    if (body == null || "".equals(body)){
                        continue;
                    }
                    System.out.println(body);
                }
            }catch (IOException e) {

            }
        }
    }

    public static void main(String[] args){
        Socket socket = new Socket();
        MessageClient client = new MessageClient(socket);
        client.connect();
        client.config();
        client.startUp();
    }

    public void connect(){
        try {
            client.connect(new InetSocketAddress("127.0.0.1", 8888));
        }catch (IOException e) {

        }
    }

    public void config(){
        sendThread = new SendThread(client);
        acceptThread = new AcceptThread(client);
    }

    public void startUp(){
        Thread send = new Thread(sendThread);
        Thread accept = new Thread(acceptThread);
        send.start();;
        accept.start();
    }
}
