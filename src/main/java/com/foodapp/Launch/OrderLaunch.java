// OrderLaunch.java in com.foodapp.launch
package com.foodapp.Launch;

import java.util.Scanner;
import com.foodapp.dao.OrderDAO;
import com.foodapp.daoImpl.OrderDAOImpl;
import com.foodapp.model.Order;

public class OrderLaunch {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAOImpl();
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to Order Management \nEnter your choice: "
                    + "\n 1. Insert Order"
                    + "\n 2. View Order List"
                    + "\n 3. View Specific Order"
                    + "\n 4. View Orders by User"
                    + "\n 5. View Orders by Restaurant"
                    + "\n 6. Update Order"
                    + "\n 7. Delete Order"
                    + "\n 0. Exit");

            try {
                int ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter order ID:");
                        int orderId = sc.nextInt();
                        System.out.println("Enter user ID:");
                        int userId = sc.nextInt();
                        System.out.println("Enter restaurant ID:");
                        int restaurantId = sc.nextInt();
                        System.out.println("Enter total amount:");
                        float totalAmount = sc.nextFloat();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter order status:");
                        String status = sc.nextLine();
                        System.out.println("Enter payment mode:");
                        String paymentMode = sc.nextLine();
                        System.out.println(orderDAO.insert(new Order(orderId, userId, restaurantId, totalAmount, status, paymentMode)) == 1
                                ? "Insert Success" : "Insert Failure");
                        break;

                    case 2:
                        orderDAO.fetchAll().forEach(System.out::println);
                        break;

                    case 3:
                        orderId = getOrderId();
                        Order order = orderDAO.fetchOne(orderId);
                        System.out.println(order != null ? order : "Order not found!");
                        break;

                    case 4:
                        userId = getUserId();
                        orderDAO.fetchByUserId(userId).forEach(System.out::println);
                        break;

                    case 5:
                        restaurantId = getRestaurantId();
                        orderDAO.fetchByRestaurantId(restaurantId).forEach(System.out::println);
                        break;

                    case 6:
                        orderId = getOrderId();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter new order status:");
                        status = sc.nextLine();
                        System.out.println("Enter new payment mode:");
                        paymentMode = sc.nextLine();
                        System.out.println(orderDAO.update(orderId, status, paymentMode) == 1
                                ? "Update Success" : "Update Failure");
                        break;

                    case 7:
                        orderId = getOrderId();
                        System.out.println(orderDAO.delete(orderId) == 1
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

    private static int getOrderId() {
        System.out.println("Enter order ID:");
        return sc.nextInt();
    }

    private static int getUserId() {
        System.out.println("Enter user ID:");
        return sc.nextInt();
    }

    private static int getRestaurantId() {
        System.out.println("Enter restaurant ID:");
        return sc.nextInt();
    }
}
