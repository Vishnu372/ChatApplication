package practice.ChatApplication.repositorylayer;

import java.util.HashSet;
import java.util.Set;
import practice.ChatApplication.Model.*;

public class UserStorage {
    public static Set<User> users=new HashSet<>();

/// Add the user into repository
    public void addUser(String Name,String Contact,String pin)
    {
        for(User u:users){
            if(u.getPhoneNo().equals(Contact)){
                System.out.println("the Contact number is already exist !!!");
                return ;
            }
        }
        users.add(new User(Name,Contact,pin));
        System.out.println("Account Created Successfully!!! -> "+Name+" ( "+Contact+" )");
    }

    ///  it is used to check the account is present or not
    public User checkAccount(String number ,String pin)
    {
        for(User u:users){
            if(u.getPhoneNo().equals(number)&&u.getPin().equals(pin))
            {
                return u;
            }
        }
        return null;
    }

}
