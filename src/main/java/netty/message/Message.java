package netty.message;

import java.io.Serializable;

public class Message implements Serializable {
    private String message;
    private Client whoSendMessage;

    public Message(String message, Client client) {
        this.message = message;
        this.whoSendMessage = client;
    }

    public Client getWhoSendMessage() {
        return whoSendMessage;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString(){
        return "client["+whoSendMessage+"]"+", message:"+message;
    }
}
