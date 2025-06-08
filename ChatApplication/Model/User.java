package practice.ChatApplication.Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String Name;
    private String phoneNo;
    private String pin;
    private List<ChatRoom> userGroup=new ArrayList<>();
    private List<User> userFriends=new ArrayList<>();

    public User(String Name,String phoneNo,String pin)
    {
        this.Name=Name;
        this.phoneNo=phoneNo;
        this.pin=pin;
    }

    public String getName() {return Name;}
    public String getPhoneNo() {return phoneNo;}
    public String getPin() {return pin;}
    public void setName(String name) {this.Name = name;}
    public void setPhoneNo(String phoneNo) {this.phoneNo = phoneNo;}
    public void setPin(String pin) {this.pin = pin;}

    public List<ChatRoom> getUserGroup() {
        return userGroup;
    }

    public void addChat(ChatRoom chatRoom){
        userGroup.add(chatRoom);
    }
//    public void setUserGroup(List<ChatRoom> userGroup) {
//        this.userGroup = userGroup;
//    }
    public User getFriend(int index){
        return userFriends.get(index);
    }
    public ChatRoom getGroup(int index){
        return userGroup.get(index);
    }
    public List<User> getUserFriends() {
        return userFriends;
    }

    public void removeFriend(User user) {
        userFriends.remove(user);
        System.out.println("Remove the user successfully... ");
    }

    public void removeGroup(ChatRoom room) {
        userGroup.remove(room);
        System.out.println("Remove the Group successfully... ");
    }
    public void addFriends(User friend){
        userFriends.add(friend);
    }
//    public void joinGroup(ChatRoom room){
//        userGroup.add(room);
//    }
}
