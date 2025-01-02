// MenuLaunch.java in com.foodapp.launch
package com.foodapp.Launch;

import java.util.Scanner;
import com.foodapp.dao.MenuDAO;
import com.foodapp.daoImpl.MenuDAOImpl;
import com.foodapp.model.Menu;

public class MenuLaunch {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        MenuDAO menuDAO = new MenuDAOImpl();
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to Menu Management \nEnter your choice: "
                    + "\n 1. Insert Menu"
                    + "\n 2. View Menu List"
                    + "\n 3. View Specific Menu"
                    + "\n 4. View Menu by Restaurant"
                    + "\n 5. Update Menu"
                    + "\n 6. Delete Menu"
                    + "\n 0. Exit");

            try {
                int ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter menu ID:");
                        int menuId = sc.nextInt();
                        System.out.println("Enter restaurant ID:");
                        int restaurantId = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter menu name:");
                        String menuName = sc.nextLine();
                        System.out.println("Enter description:");
                        String description = sc.nextLine();
                        System.out.println("Enter price:");
                        int price = sc.nextInt();
                        System.out.println("Is the menu available? (true/false):");
                        boolean isAvailable = sc.nextBoolean();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter image path:");
                        String imagePath = sc.nextLine();
                        System.out.println(menuDAO.insert(new Menu(menuId, restaurantId, menuName, description, price, isAvailable, imagePath)) == 1
                                ? "Insert Success" : "Insert Failure");
                        break;

                    case 2:
                        menuDAO.fetchAll().forEach(System.out::println);
                        break;

                    case 3:
                        menuId = getMenuId();
                        Menu menu = menuDAO.fetchOne(menuId);
                        System.out.println(menu != null ? menu : "Menu not found!");
                        break;

                    case 4:
                        System.out.println("Enter restaurant ID:");
                        restaurantId = sc.nextInt();
                        menuDAO.fetchByRestaurantId(restaurantId).forEach(System.out::println);
                        break;

                    case 5:
                        menuId = getMenuId();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter the new description:");
                        description = sc.nextLine();
                        System.out.println("Enter the new price:");
                        price = sc.nextInt();
                        System.out.println("Is the menu available? (true/false):");
                        isAvailable = sc.nextBoolean();
                        System.out.println(menuDAO.update(menuId, description, price, isAvailable) == 1
                                ? "Update Success" : "Update Failure");
                        break;

                    case 6:
                        menuId = getMenuId();
                        System.out.println(menuDAO.delete(menuId) == 1
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

    private static int getMenuId() {
        System.out.println("Enter menu ID:");
        return sc.nextInt();
    }
}
