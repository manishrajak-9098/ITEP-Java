package entity;

public class Order {

	private int order_id;
	private int list_Items;
	private double total_Price;
	private String order_Status;

	public Order(int order_id, int list_Items, double total_Price, String order_Status) {
		super();
		this.order_id = order_id;
		this.list_Items = list_Items;
		this.total_Price = total_Price;
		this.order_Status = order_Status;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getList_Items() {
		return list_Items;
	}

	public void setList_Items(int list_Items) {
		this.list_Items = list_Items;
	}

	public double getTotal_Price() {
		return total_Price;
	}

	public void setTotal_Price(double total_Price) {
		this.total_Price = total_Price;
	}

	public String getOrder_Status() {
		return order_Status;
	}

	public void setOrder_Status(String order_Status) {
		this.order_Status = order_Status;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", list_Items=" + list_Items
				+ ", total_Price=" + total_Price + ", order_Status=" + order_Status + "]";
	}
}