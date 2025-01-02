// OrderItemLaunch.java in com.foodapp.launch
package com.foodapp.Launch;

import java.util.Scanner;
import com.foodapp.dao.OrderItemDAO;
import com.foodapp.daoImpl.OrderItemDAOImpl;
import com.foodapp.model.OrderItem;

public class OrderItemLaunch {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to OrderItem Management \nEnter your choice: "
                    + "\n 1. Insert OrderItem"
                    + "\n 2. View OrderItem List"
                    + "\n 3. View Specific OrderItem"
                    + "\n 4. View OrderItems by Order"
                    + "\n 5. View OrderItems by Menu"
                    + "\n 6. Update OrderItem"
                    + "\n 7. Delete OrderItem"
                    + "\n 0. Exit");

            try {
                int ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter order item ID:");
                        int orderItemId = sc.nextInt();
                        System.out.println("Enter order ID:");
                        int orderId = sc.nextInt();
                        System.out.println("Enter menu ID:");
                        int menuId = sc.nextInt();
                        System.out.println("Enter quantity:");
                        int quantity = sc.nextInt();
                        System.out.println("Enter item total:");
                        int itemTotal = sc.nextInt();
                        System.out.println(orderItemDAO.insert(new OrderItem(orderItemId, orderId, menuId, quantity, itemTotal)) == 1
                                ? "Insert Success" : "Insert Failure");
                        break;

                    case 2:
                        orderItemDAO.fetchAll().forEach(System.out::println);
                        break;

                    case 3:
                        orderItemId = getOrderItemId();
                        OrderItem orderItem = orderItemDAO.fetchOne(orderItemId);
                        System.out.println(orderItem != null ? orderItem : "OrderItem not found!");
                        break;

                    case 4:
                        orderId = getOrderId();
                        orderItemDAO.fetchByOrderId(orderId).forEach(System.out::println);
                        break;

                    case 5:
                        menuId = getMenuId();
                        orderItemDAO.fetchByMenuId(menuId).forEach(System.out::println);
                        break;

                    case 6:
                        orderItemId = getOrderItemId();
                        sc.nextLine(); // Consume newline
                        System.out.println("Enter new quantity:");
                        quantity = sc.nextInt();
                        System.out.println("Enter new item total:");
                        itemTotal = sc.nextInt();
                        System.out.println(orderItemDAO.update(orderItemId, quantity, itemTotal) == 1
                                ? "Update Success" : "Update Failure");
                        break;

                    case 7:
                        orderItemId = getOrderItemId();
                        System.out.println(orderItemDAO.delete(orderItemId) == 1
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

    private static int getOrderItemId() {
        System.out.println("Enter order item ID:");
        return sc.nextInt();
    }

    private static int getOrderId() {
        System.out.println("Enter order ID:");
        return sc.nextInt();
    }

    private static int getMenuId() {
        System.out.println("Enter menu ID:");
        return sc.nextInt();
    }
}
