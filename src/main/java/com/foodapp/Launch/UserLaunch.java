// UserLaunch.java in com.foodapp.launch
package com.foodapp.Launch;

import java.util.Scanner;
import com.foodapp.dao.UserDAO;
import com.foodapp.daoImpl.UserDAOImpl;
import com.foodapp.model.User;

public class UserLaunch {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to User Management \nEnter your choice: "
                    + "\n 1. Insert User"
                    + "\n 2. View User List"
                    + "\n 3. View Specific User"
                    + "\n 4. Update User Address"
                    + "\n 5. Delete User"
                    + "\n 0. Exit");

            try {
                int ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter user ID:");
                        int userId = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter username:");
                        String username = sc.nextLine();
                        System.out.println("Enter password:");
                        String password = sc.nextLine();
                        System.out.println("Enter email:");
                        String email = sc.nextLine();
                        System.out.println("Enter address:");
                        String address = sc.nextLine();
                        System.out.println(userDAO.insert(new User( username, password, email, address)) == 1
                                ? "Insert Success" : "Insert Failure");
                        break;

                    case 2:
                        userDAO.fetchAll().forEach(System.out::println);
                        break;

                    case 3:
                        userId = getUserId();
                        User user = userDAO.fetchOne(userId);
                        System.out.println(user != null ? user : "User not found!");
                        break;

                    case 4:
                        userId = getUserId();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter the new address:");
                        address = sc.nextLine();
                        System.out.println(userDAO.update(userId, address) == 1
                                ? "Update Success" : "Update Failure");
                        break;

                    case 5:
                        userId = getUserId();
                        System.out.println(userDAO.delete(userId) == 1
                                ? "Delete Success" : "Delete Failure");
                        break;

                    case 0:
                        System.out.println("Exiting... Goodbye!");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu options.");
                sc.nextLine(); // Clear invalid input
            }
        }
    }

    private static int getUserId() {
        System.out.println("Enter user ID:");
        return sc.nextInt();
    }
}
