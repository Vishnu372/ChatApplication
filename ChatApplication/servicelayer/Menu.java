package practice.ChatApplication.servicelayer;

import practice.ChatApplication.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import practice.ChatApplication.repositorylayer.*;

public class Menu {
    Scanner sc=new Scanner(System.in);
    UserStorage userStorage=new UserStorage();
    FriendsMenu friendsMenu=new FriendsMenu();
    MessageStorage messageStorage=new MessageStorage();
    ///  this function is used to show  the user option after login
    public void showUserPreference(User user){
        Login:while(true){
        try{
            System.out.println("Welcome "+user.getName());
            System.out.println("1. View Friends Message ");
            System.out.println("2. View Groups and Send Messages ");
            System.out.println("3. View Friends and groups list ");
            System.out.println("4. Add Friends ");
            System.out.println("5. Remove Friends ");
            System.out.println("6. Create a group ");
            System.out.println("7. Exit from the group ");
            System.out.println("8. LogOut Account ");
            System.out.println("Enter your Choice : ");
            int choice=sc.nextInt();
            sc.nextLine();
            switch (choice)
            {
                /// View Friend message
                case 1->{
                        while(true) {
                        int personChoice;
                            System.out.println("---Here Is your friends List --");
                        friendsMenu.showFriendList(user);
                            System.out.println("If no Friend List is appeared means press -1 : ");
                            int safe=sc.nextInt();
                            if(safe==-1){
                                System.out.println("Please add freinds or group to view messages !!! ");
                                break;
                            }

                        System.out.println("Enter Your Choice number according to the person : ");
                        personChoice = sc.nextInt();
                        sc.nextLine();
                        if(personChoice>=user.getUserFriends().size()){
                            System.out.println("Out of Range please try Again!!! ");
                            continue ;
                        }
                        User Friend = user.getUserFriends().get(personChoice); // message user object value get
                        messageStorage.showMessage(user,Friend);
                        System.out.println("If you want to send message to the corresponding person press '1' : ");
                        while (true) {
                            int c = sc.nextInt();
                            sc.nextLine();
                            if (c == 1) {
                                System.out.println("Enter the message :");
                                String message = sc.nextLine().trim();
                                messageStorage.addMessage(user, Friend, message);
                                System.out.println("Want send another message press '1' again : ");
                            } else {
                                    break;
                            }
                        }

                    }

                }
                /// view group message
                case 2->{
                    while(true) {
                    int personChoice,addmess;
                        friendsMenu.showGroupList(user);
                        System.out.println("Enter Your Choice number according to the group listed : ");
                        personChoice = sc.nextInt();
                        sc.nextLine();
                        ChatRoom chatRoom = user.getUserGroup().get(personChoice);
                        System.out.println("Chat Room " + chatRoom.getRoomName() + " Messages");
                        chatRoom.showMessages();
                        System.out.println("\n if you want add some messages means enter 1 : ");
                        addmess = sc.nextInt();
                        sc.nextLine();
                        if (addmess == 1) {
                            while (true) {
                                System.out.println("Enter the message to group Here : ");
                                String message = sc.nextLine().trim();
                                chatRoom.addMessage(user, message);
                                System.out.println("Would you like to enter another message means press 1 :");
                                int val=sc.nextInt();
                                sc.nextLine();
                                if ( val != 1) {
                                    break;
                                }
                            }
                        }

                        System.out.println("If you are a group admin\n 1. want to delete any of the messages in the group \n2. want to remove the member by name \n Enter the choice : ");
                        int ni=sc.nextInt();sc.nextLine();
                        boolean groupadmin=chatRoom.groupadmin(user);
                        if(groupadmin && ni==1){
                            chatRoom.showMessages();
                            System.out.println("Enter the message number to remove message ");
                            int removeindex= sc.nextInt();sc.nextLine();
                            chatRoom.removeByAdmin(user,removeindex);
                        }
                        else if(groupadmin&&ni==2){
                            chatRoom.showMembers();
                        }
                        System.out.println("If you want exit from the group menu means press '1' : ");
                        int n = sc.nextInt();sc.nextLine();
                        if(n==1)break;
                    }
                }
                ///  used to verify the group and friends on their chat
                case 3->{
                    System.out.println("Friends List : ");
                    friendsMenu.showFriendList(user);
                    System.out.println("ChatRoom Or Group List :");
                    friendsMenu.showGroupList(user);
                }
                /// used to add friends
                case 4->{
                    while (true) {
                        System.out.println("Enter your friend contact number : ");
                        User friend=null;
                        String num=sc.nextLine().trim().replaceAll("^[a-z|A-Z]", "");
                        if(num.length()!=10){
                          System.out.println("Enter the valid number please try Again... ");
                        }
                        else{
                            for(User u:UserStorage.users){
                                if(num.equals(u.getPhoneNo())){
                                    friend = u;
                                    break;
                                }
                            }
                            if(friend!=null){
                                user.addFriends(friend);
                                friend.addFriends(user);
                                System.out.println(friend.getName()+" is added to your friend List successfully...");
                                System.out.println("If you want to add another friend press '1' : ");
                                int another=sc.nextInt();sc.nextLine();
                                if(another!=1){
                                    break;
                                }
                            }
                            else{
                                System.out.println("User not found please enter the number again!!! ");
                            }
                        }
                    }
                }
                /// used to remove the friends
                case 5->{
                    System.out.println("----Friend List----");
                    friendsMenu.showFriendList(user);
                    System.out.println("Enter the number according to your friend show in the list which you want to remove : ");
                    int pref= sc.nextInt();sc.nextLine();
                    User friend=user.getFriend(pref);
                    user.removeFriend(friend);
                }
                /// used to create a group
                case 6->{
                    System.out.println("Create the Room Name Here : ");
                    String name=sc.nextLine();
                    ChatRoom chatRoom=new ChatRoom(name);
                    System.out.println("ChatRoom created successfully... ");
                    System.out.println("You need to add your friends...");
                    System.out.println("Here is your Friends List..");
                    friendsMenu.showFriendList(user);
                    System.out.println("Enter you the number straight to your friend seperated by gap \n example : 1 3 4 7 .. ");
                    String select=sc.nextLine().trim();
                    List<User> selectedUser=new ArrayList<>();
                    selectedUser.add(user);
                    for(String index:select.split(" "))
                    {
                        selectedUser.add(user.getFriend(Integer.parseInt(index)));
                    }
                    chatRoom.addMembers(selectedUser);
                    for(User u:selectedUser){
                        u.addChat(chatRoom);
                    }
                    System.out.println("ChatRoom created Successfully Here below is the list of members");
                    chatRoom.showMembers();
                    chatRoom.addleader(user);
                    System.out.println("Group Leaders :");
                    chatRoom.showLeaders();
                }
                /// used to remove group
                case 7->{
                    System.out.println("----Group List----");
                    friendsMenu.showGroupList(user);
                    System.out.println("Enter the number according to Group show in the list which you want to remove : ");
                    int pref= sc.nextInt();sc.nextLine();
                    ChatRoom room=user.getGroup(pref);
                    user.removeGroup(room);
                }
                /// used to log out the current account
                case 8->{
                    System.out.println("Your Account successfully Logout...");
                    break Login;
                }
                default -> {
                    System.out.println("Enter the correct input format ");
                }
            }
        }
        catch (Exception e){
            System.out.println("type mismatch error "+ e);

        }
    }
    }

/// this function is used to check login to get user class
    public void showLoginOrCreate(){
        String number,PIN;
        System.out.println("Enter your Contact ");
        number=sc.nextLine().trim().replaceAll("^[a-z|A-Z]","");
        if(number.length()!=10)
        {
            System.out.println(" Enter the valid Contact number with in 10 digit and non alphabetic ");
        }
        System.out.println("Enter the PIN Here : ");
        PIN=sc.nextLine().trim().replaceAll("^[a-z|A-Z]","");
        if(PIN.length()!=4){
            System.out.println("Enter the 4 digit pin and non alphabetic character ");
        }
        User user=userStorage.checkAccount(number,PIN);
        if(user!=null)
        {
            showUserPreference(user);
        }
        else{
            System.out.println("User Not exist!!! if you go to create account press '1' : ");
            int one=sc.nextInt();
            sc.nextLine();
            if(one==1)
            {
                System.out.println("You are redirect to create account... ");
                createAccount();
            }
            else{
                System.out.println("Thanks for using our application... bye...");
                System.exit(0);
            }
        }
    }


/// Create the User account
    public void createAccount(){
        String name,contact,pin;
        System.out.println("Please fill the below query to create a Account!!! ");
        while(true) {
            System.out.println("Enter your name : ");
            name = sc.nextLine().trim().replaceAll("^[0-9]", "");
            System.out.println("Enter your Contact : ");
            while (true) {
                contact = sc.nextLine().trim().replaceAll("^[a-zA-Z]", "");
                if (contact.length() != 10) {
                    System.out.println(" Enter the valid Contact number with in 10 digit and non alphabetic ");
                } else {
                    break;
                }
            }
            while(true) {
                System.out.println("Create your four digit pin : ");
                pin = sc.nextLine().trim().replaceAll("^[a-z|A-Z]", "");
                if(pin.length()!=4){
                    System.out.println("Enter the 4 digit pin and non alphabetic character ");
                }
                else{
                    break;
                }
            }
            int press=0;
            System.out.println("Name : "+name+" Contact : "+contact+" pin :"+pin);
            System.out.println("Here above is the details you enter check once if any changes press 1 otherwise 0 : ");
            press= sc.nextInt();
            sc.nextLine();
            if(press != 1) {
                userStorage.addUser(name, contact, pin);
                break;
            }
        }
        System.out.println("User Added successfully you will redirect to login!!!");
        showLoginOrCreate();
    }
}


///  this class is used for the freinds list Menu
class FriendsMenu{
    public void showFriendList(User user)
    {
        int id=0;
        for(User u:user.getUserFriends()){
            System.out.println(id++ +" "+u.getName());
        }
    }

    public void showGroupList(User user)
    {
        int id=0;
        for(ChatRoom u:user.getUserGroup()){
            System.out.println(id++ +" "+u.getRoomName());
        }
    }


}
