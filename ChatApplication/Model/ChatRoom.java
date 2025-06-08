package practice.ChatApplication.Model;

import java.util.*;
import java.time.LocalDateTime;



public class ChatRoom {
    private String roomName;
    private Set<User> groupLeader=new HashSet<>();
    private List<User> groupMembers=new ArrayList<>();
    private List<Message> groupMessage=new ArrayList<>();
    LocalDateTime now = LocalDateTime.now();

    public ChatRoom(String name){
        this.roomName=name;
    }
    public void showMessages()
    {
        int index=0;
        for(Message m:groupMessage)
        {
            System.out.println(index++ + ". " +m.toString("group"));
        }
    }

    public void addMessage(User user,String content){
        if(groupMembers.contains(user))
        {
            groupMessage.add(new Message(content,user,LocalDateTime.now().toString()));
        }
        else{
            System.out.println("Sorry your not one of the group member Now... only view the messages ");
        }
    }

    public void addleader(User user){
        groupLeader.add(user);
    }
    public void addMembers(List<User> li){
        this.groupMembers.addAll(li);
    }
    public void removeByAdmin(User user,int index){
        if(groupLeader.contains(user))
        {
            groupMessage.get(index).setMessage("This message was removed by admin ");
        }
    }

    public void showMembers(){
        int index=0;
        for(User u:groupMembers){
            System.out.println(index++ +". "+u.getName()+"Contact : ( "+u.getPhoneNo()+" )");
        }
    }

    public void showLeaders(){
        int index=0;
        for(User u:groupLeader){
            System.out.println(index++ + ". "+u.getName()+"Contact : ( "+u.getPhoneNo()+" )" );
        }
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean groupadmin(User user){
        return groupLeader.contains(user);
    }

//    public Set<User> getMembers(){
//        return this.groupMembers;
//    }
//    public Set<User> getGroupLeader(){
//        return this.groupLeader;
//    }
}
