package entity;

public class Restaurant {

	private int rest_id;
	private String name;
	private String menu_items;

	public Restaurant(int rest_id, String name, String menu_items) {
		super();
		this.rest_id = rest_id;
		this.name = name;
		this.menu_items = menu_items;
	}

	public int getRest_id() {
		return rest_id;
	}

	public void setRest_id(int rest_id) {
		this.rest_id = rest_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenu_items() {
		return menu_items;
	}

	public void setMenu_items(String menu_items) {
		this.menu_items = menu_items;
	}

	@Override
	public String toString() {
		return "Restaurant [rest_id=" + rest_id + ", name=" + name
				+ ", menu_items=" + menu_items + "]";
	}
}