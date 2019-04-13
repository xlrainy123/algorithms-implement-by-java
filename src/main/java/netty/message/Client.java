package netty.message;

import java.io.Serializable;

public class Client implements Serializable {
    private String clientIp;
    private int clientPort;

    public Client(String clientIp, int clientPort){
        this.clientIp = clientIp;
        this.clientPort = clientPort;
    }

    public int getClientPort() {
        return clientPort;
    }

    public String getClientIp() {
        return clientIp;
    }

    @Override
    public String toString(){
        return "client ip:"+clientIp+", client port:"+clientPort;
    }
}
