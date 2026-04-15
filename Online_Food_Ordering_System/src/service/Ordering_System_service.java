package service;

import java.util.List;

import entity.Food_Item;
import entity.Order;
import entity.Restaurant;
import repository.Ordering_System_Repo;

public class Ordering_System_service {

	private Ordering_System_Repo repo = new Ordering_System_Repo();

	// Add food
	public void addFood(Food_Item foodItem) {
		repo.addFood(foodItem);
	}

	// View menu (all food items)
	public List<Food_Item> viewMenu() {
		return repo.findAllFood();
	}

	// View restaurants
	public List<Restaurant> view_Restaurant() {
		return repo.findAllRestaurant();
	}

	// Add order
	public void addOrder(Order order) {
		repo.add_Order(order);
	}

	// View order history
	public List<Order> viewOrderHistory() {
		return repo.findAllOrderHistory();
	}

	// Cancel order by ID
	public void delete(int order_id) {
		Order order = repo.findOrderById(order_id);
		if (order != null) {
			repo.cancel(order);
		} else {
			System.out.println("Order with ID " + order_id + " not found.");
		}
	}
}