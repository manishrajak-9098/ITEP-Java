package com.jsp.model;

import java.sql.Timestamp;

public class Food {
    private int foodId;
    private int donorId;
    private String foodName;
    private String quantity;
    private String placeName;
    private String fullAddress;
    private String pickupTime;
    private String note;
    private Timestamp postedTime;
    private String status;

    public int getFoodId() { return foodId; }
    public void setFoodId(int foodId) { this.foodId = foodId; }

    public int getDonorId() { return donorId; }
    public void setDonorId(int donorId) { this.donorId = donorId; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String getPlaceName() { return placeName; }
    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public String getFullAddress() { return fullAddress; }
    public void setFullAddress(String fullAddress) { this.fullAddress = fullAddress; }

    public String getPickupTime() { return pickupTime; }
    public void setPickupTime(String pickupTime) { this.pickupTime = pickupTime; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public Timestamp getPostedTime() { return postedTime; }
    public void setPostedTime(Timestamp postedTime) { this.postedTime = postedTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
