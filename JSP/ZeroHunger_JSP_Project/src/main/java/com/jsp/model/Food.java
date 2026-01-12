package com.jsp.model;



import java.sql.Timestamp;

public class Food {
    private int foodId;
    private int donorId;
    private String foodName;
    private String quantity;
    private String location;
    private Timestamp postedTime;
    private String status;
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public int getDonorId() {
		return donorId;
	}
	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Timestamp getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(Timestamp postedTime) {
		this.postedTime = postedTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
