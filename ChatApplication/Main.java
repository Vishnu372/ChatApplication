package practice.ChatApplication;

import practice.ChatApplication.servicelayer.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Menu menu=new Menu();
        System.out.println("Welcome to chat Application ");
        while(true){
            int c;
            System.out.println("1. Login ");
            System.out.println("2. Create an Account if not exist");
            System.out.println("3. Exit");
            System.out.println("Enter your choice : ");
            c= sc.nextInt();
            sc.nextLine();
            switch (c)
            {
                case 1->{
                    menu.showLoginOrCreate();
                }
                case 2->{
                    menu.createAccount();
                }
                case 3->{
                    System.out.println("Thanks for Coming...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Enter the correct input format ");
                }
            }
        }
    }
}
