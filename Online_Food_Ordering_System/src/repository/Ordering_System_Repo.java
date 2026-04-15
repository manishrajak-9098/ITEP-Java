package repository;

import java.util.ArrayList;
import java.util.List;

import entity.Food_Item;
import entity.Order;
import entity.Restaurant;

public class Ordering_System_Repo {

	ArrayList<Restaurant> rest_list = new ArrayList<Restaurant>() {{
		add(new Restaurant(1, "Domino's Pizza", "Pizza, Garlic Bread, Pasta"));
		add(new Restaurant(2, "Burger King", "Burger, Fries, Cold Drink"));
		add(new Restaurant(3, "Biryani House", "Biryani, Raita, Lassi"));
	}};

	ArrayList<Food_Item> food_list = new ArrayList<Food_Item>();

	ArrayList<Order> order_list = new ArrayList<Order>();

	// Add food item
	public void addFood(Food_Item item) {
		food_list.add(item);
	}

	public List<Food_Item> findAllFood() {
		return food_list;
	}

	// View restaurant
	public List<Restaurant> findAllRestaurant() {
		return rest_list;
	}

	// Add order
	public void add_Order(Order order) {
		order_list.add(order);
	}

	// View order history
	public List<Order> findAllOrderHistory() {
		return order_list;
	}

	// Find order by ID (cancel ke liye)
	public Order findOrderById(int order_id) {
		for (Order o : order_list) {
			if (o.getOrder_id() == order_id) {
				return o;
			}
		}
		return null;
	}

	// Cancel order
	public void cancel(Order order) {
		order_list.remove(order);
	}
}