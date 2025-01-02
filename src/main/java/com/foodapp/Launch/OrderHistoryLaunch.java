/*package com.foodapp.Launch;

import java.util.Scanner;
import com.foodapp.dao.OrderHistoryDAO;
import com.foodapp.daoImpl.OrderHistoryDAOImpl;
import com.foodapp.model.OrderHistory;

public class OrderHistoryLaunch {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to Order History Management\nEnter your choice: "
                    + "\n 1. Insert Order History"
                    + "\n 2. View All Order Histories"
                    + "\n 3. View Specific Order History"
                    + "\n 4. View Order Histories by User ID"
                    + "\n 5. View Order Histories by Order ID"
                    + "\n 6. Update Order History Status"
                    + "\n 7. Delete Order History"
                    + "\n 0. Exit");

            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter Order History ID:");
                        int orderHistoryId = sc.nextInt();
                        System.out.println("Enter Order ID:");
                        int ordersId = sc.nextInt();
                        System.out.println("Enter User ID:");
                        int usersId = sc.nextInt();
                        System.out.println("Enter Total Amount:");
                        float totalAmount = sc.nextFloat();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter Status:");
                        String status = sc.nextLine();
                        System.out.println(orderHistoryDAO.insert(new OrderHistory(orderHistoryId, ordersId, usersId, totalAmount, status)) == 1
                                ? "Insert Success" : "Insert Failure");
                        break;

                    case 2:
                        orderHistoryDAO.fetchAll().forEach(System.out::println);
                        break;

                    case 3:
                        System.out.println("Enter Order History ID:");
                        orderHistoryId = sc.nextInt();
                        OrderHistory orderHistory = orderHistoryDAO.fetchOne(orderHistoryId);
                        System.out.println(orderHistory != null ? orderHistory : "Order History not found!");
                        break;

                    case 4:
                        System.out.println("Enter User ID:");
                        usersId = sc.nextInt();
                        orderHistoryDAO.fetchByUserId(usersId).forEach(System.out::println);
                        break;

                    case 5:
                        System.out.println("Enter Order ID:");
                        ordersId = sc.nextInt();
                        orderHistoryDAO.fetchByOrderId(ordersId).forEach(System.out::println);
                        break;

                    case 6:
                        System.out.println("Enter Order History ID:");
                        orderHistoryId = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter New Status:");
                        status = sc.nextLine();
                        System.out.println(orderHistoryDAO.update(orderHistoryId, status) == 1
                                ? "Update Success" : "Update Failure");
                        break;

                    case 7:
                        System.out.println("Enter Order History ID:");
                        orderHistoryId = sc.nextInt();
                        System.out.println(orderHistoryDAO.delete(orderHistoryId) == 1
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
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }
    }
}*/
