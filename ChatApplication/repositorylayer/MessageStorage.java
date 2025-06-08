package practice.ChatApplication.repositorylayer;
import practice.ChatApplication.Model.*;
import practice.ChatApplication.servicelayer.Menu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

class usePair{ User u1, u2;

    public usePair(User u1, User u2) {
        this.u1 = u1;
        this.u2 = u2;
    }

    @Override
    public String toString() {
        return u1.getName() + " send message -> " + u2.getName() + " : ";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        usePair other = (usePair) obj;

        return (Objects.equals(u1, other.u1) && Objects.equals(u2, other.u2)) ||
                (Objects.equals(u1, other.u2) && Objects.equals(u2, other.u1));
    }

    @Override
    public int hashCode() {
        // Order-independent hashcode
        return u1.hashCode() + u2.hashCode();
    }
}

public class MessageStorage {
    HashMap<usePair, List<Message>> chatHistory=new HashMap<>();

    public void addMessage(User sender,User receiver,String Content){
        usePair pair = new usePair(sender, receiver);
        LocalDateTime now = LocalDateTime.now();

        if (!chatHistory.containsKey(pair)) {
            List<Message> messages = new ArrayList<>();
            messages.add(new Message(Content, sender, receiver, now.toString()));
            chatHistory.put(pair, messages);
        } else {
            chatHistory.get(pair).add(new Message(Content, sender, receiver, now.toString()));
        }

        System.out.println("Message sent to user: " + receiver.getName() + " successfully");
    }

    public void showMessage(User sender,User receiver) {
        usePair pair = new usePair(sender, receiver);

        if (!chatHistory.containsKey(pair)) {
            System.out.println("No messages found between users.");
            return;
        }

        List<Message> messages = chatHistory.get(pair);
        for (Message message : messages) {
            System.out.println(message.toString("normal")); // Assumes Message class has a valid toString("normal")
        }

        System.out.println("All messages listed.");
    }
}
