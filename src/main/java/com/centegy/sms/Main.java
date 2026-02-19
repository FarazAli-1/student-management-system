package com.centegy.sms;

import java.util.Scanner;



public class Main {

    static MenuHandler menuHandler = new MenuHandler();
    static Scanner sc = new Scanner(System.in);

    static void main(String[] args) {

        System.out.print("Enter Admin Password: ");
        String pass = sc.next();
        if (!pass.equals("admin123")) {
            System.out.println("Access Denied");
            System.exit(0);
        }

        MenuHandler.initializeData();

        boolean cond = true;
        while (cond) {

        menuHandler.mainMenu();

        System.out.print("Enter your choice : ");
        int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    MenuHandler.studentMenu();
                    break;

                case 2:
                    MenuHandler.courseMenu();
                    break;

                case 3:
                    MenuHandler.instructorMenu();
                    break;

                case 4:
                    MenuHandler.enrolmentMenu();
                    break;

                case 5:
                    MenuHandler.examinationMenu();
                    break;
                case 0:
                    System.out.println("Thanks");
                    cond = false;
                    break;
            }
        }
    }
}
