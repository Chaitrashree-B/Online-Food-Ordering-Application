// RestaurantLaunch.java in com.foodapp.launch
package com.foodapp.Launch;

import java.util.Scanner;
import com.foodapp.dao.RestaurantDAO;
import com.foodapp.daoImpl.RestaurantDAOImpl;
import com.foodapp.model.Restaurant;

public class RestaurantLaunch {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to Restaurant Management \nEnter your choice: "
                    + "\n 1. Insert Restaurant"
                    + "\n 2. View Restaurant List"
                    + "\n 3. View Specific Restaurant"
                    + "\n 4. Update Restaurant Address"
                    + "\n 5. Delete Restaurant"
                    + "\n 0. Exit");

            try {
                int ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter restaurant ID:");
                        int restaurantId = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter restaurant name:");
                        String restaurantName = sc.nextLine();
                        System.out.println("Enter cuisine type:");
                        String cuisineType = sc.nextLine();
                        System.out.println("Enter delivery time:");
                        int deliveryTime = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter address:");
                        String address = sc.nextLine();
                        System.out.println("Enter ratings:");
                        float ratings = sc.nextFloat();
                        System.out.println("Is the restaurant active? (true/false):");
                        boolean isActive = sc.nextBoolean();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter image path:");
                        String imagePath = sc.nextLine();
                        System.out.println(restaurantDAO.insert(new Restaurant(restaurantId, restaurantName, cuisineType, 
                                deliveryTime, address, ratings, isActive, imagePath)) == 1
                                ? "Insert Success" : "Insert Failure");
                        break;

                    case 2:
                        restaurantDAO.fetchAll().forEach(System.out::println);
                        break;

                    case 3:
                        restaurantId = getRestaurantId();
                        Restaurant restaurant = restaurantDAO.fetchOne(restaurantId);
                        System.out.println(restaurant != null ? restaurant : "Restaurant not found!");
                        break;

                    case 4:
                        restaurantId = getRestaurantId();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter the new address:");
                        address = sc.nextLine();
                        System.out.println("Is the restaurant active? (true/false):");
                        isActive = sc.nextBoolean();
                        System.out.println(restaurantDAO.update(restaurantId, address, isActive) == 1
                                ? "Update Success" : "Update Failure");
                        break;

                    case 5:
                        restaurantId = getRestaurantId();
                        System.out.println(restaurantDAO.delete(restaurantId) == 1
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

    private static int getRestaurantId() {
        System.out.println("Enter restaurant ID:");
        return sc.nextInt();
    }
}
