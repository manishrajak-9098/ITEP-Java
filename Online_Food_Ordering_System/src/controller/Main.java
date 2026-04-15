package controller;

import java.util.List;
import java.util.Scanner;

import entity.Food_Item;
import entity.Order;
import entity.Restaurant;
import service.Ordering_System_service;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Ordering_System_service service = new Ordering_System_service();

		while (true) {
			try {
				System.out.println("\n====== Online Food Ordering System ======");
				System.out.println("1. View Restaurants");
				System.out.println("2. View Restaurant Menu");
				System.out.println("3. Add Food Item");
				System.out.println("4. Place an Order");
				System.out.println("5. View Order History");
				System.out.println("6. Cancel Order");
				System.out.println("7. Exit");
				System.out.println("=========================================");
				System.out.print("Enter Choice: ");

				int choice = Integer.parseInt(sc.nextLine());

				switch (choice) {

				case 1:
					List<Restaurant> restaurants = service.view_Restaurant();
					if (restaurants.isEmpty()) {
						System.out.println("No restaurants found.");
					} else {
						System.out.println("--- Restaurants ---");
						for (Restaurant r : restaurants) {
							System.out.println(r);
						}
					}
					break;

				case 2:
					List<Food_Item> menu = service.viewMenu();
					if (menu.isEmpty()) {
						System.out.println("No food items in menu. Please add food first.");
					} else {
						System.out.println("--- Restaurant Menu ---");
						for (Food_Item f : menu) {
							System.out.println(f);
						}
					}
					break;

				case 3:
					System.out.print("Enter Food ID: ");
					int id = Integer.parseInt(sc.nextLine());

					System.out.print("Enter Item Name: ");
					String item_name = sc.nextLine();

					System.out.print("Enter Food Price: ");
					double price = Double.parseDouble(sc.nextLine());

					System.out.print("Enter Category: ");
					String cate = sc.nextLine();

					service.addFood(new Food_Item(id, item_name, price, cate));
					System.out.println("Food Added Successfully!");
					break;

				case 4:
					System.out.print("Enter Order ID: ");
					int order_id = Integer.parseInt(sc.nextLine());

					System.out.print("Enter Number of Items: ");
					int num = Integer.parseInt(sc.nextLine());

					System.out.print("Enter Total Price: ");
					double total_price = Double.parseDouble(sc.nextLine());

					System.out.print("Enter Order Status (e.g. Pending): ");
					String status = sc.nextLine();

					service.addOrder(new Order(order_id, num, total_price, status));
					System.out.println("Order Placed Successfully!");
					break;

				case 5:
					List<Order> orders = service.viewOrderHistory();
					if (orders.isEmpty()) {
						System.out.println("No orders found.");
					} else {
						System.out.println("--- Order History ---");
						for (Order o : orders) {
							System.out.println(o);
						}
					}
					break;

				case 6:
					System.out.print("Enter Order ID to Cancel: ");
					int cancelId = Integer.parseInt(sc.nextLine());
					service.delete(cancelId);
					System.out.println("Order cancelled successfully.");
					break;

				case 7:
					System.out.println("Thank you! Exiting...");
					sc.close();
					return;

				default:
					System.out.println("Invalid choice. Please enter 1-7.");
				}

			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a valid number.");
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}