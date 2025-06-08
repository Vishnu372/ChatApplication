package practice.ChatApplication.Model;

import java.time.LocalDateTime;

public class Message {
    private String message;
    private User receiver;
    private User sender;
    private String localTime;
    private boolean isSeen;
//    LocalDateTime now = LocalDateTime.now();

    public Message(String message,User sender,String localTime)
    {
        this.message=message;
        this.sender=sender;
        this.localTime=localTime;
    }

    public Message(String message,User sender,User receiver,String localTime)
    {
        this.message=message;
        this.sender=sender;
        this.receiver=receiver;
        this.localTime=localTime;
    }
//
//    public User getReceiver() {return receiver;}
//
//    public String getMessage() {return message;}

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {return sender;}

    public String getLocalTime() {return localTime;}

    public boolean isSeen() {return isSeen;}

    public void setSeen(boolean seen) {isSeen = seen;}

    public String toString(String value) {
        if(value.equals("normal"))
        return "[ "+localTime+" ] "+sender.getName()+" -> "+receiver.getName()+"  Message : "+ message;
        else
            return "[ "+localTime+" ] "+sender.getName()+" Message : "+ message;
    }
}



