package entity;

public class Food_Item {

	private int item_id;
	private String item_name;
	private double price;
	private String category;

	public Food_Item(int item_id, String item_name, double price, String category) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.price = price;
		this.category = category;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Food_Item [item_id=" + item_id + ", item_name=" + item_name
				+ ", price=" + price + ", category=" + category + "]";
	}
}