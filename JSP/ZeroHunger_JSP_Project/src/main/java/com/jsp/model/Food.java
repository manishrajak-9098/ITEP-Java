package com.jsp.model;
import java.sql.Timestamp;

public class Food {
    private int foodId, donorId, ngoId;
    private String foodName, quantity, placeName, fullAddress, contactNo, pickupTime, note, status;
    private Timestamp postedTime;
    private String donorName, ngoName; // Helper fields for Admin view

    // Getters and Setters
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
    public int getNgoId() { 
    	return ngoId;
    	}
    public void setNgoId(int ngoId) {
    	this.ngoId = ngoId;
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
    public String getPlaceName() {
    	return placeName;
    	}
    public void setPlaceName(String placeName) { 
    	this.placeName = placeName; 
    	}
    public String getFullAddress() {
    	return fullAddress;
    	}
    public void setFullAddress(String fullAddress) {
    	this.fullAddress = fullAddress;
    	}
    public String getContactNo() {
    	return contactNo;
    	}
    public void setContactNo(String contactNo) {
    	this.contactNo = contactNo;
    	}
    public String getPickupTime() { 
    	return pickupTime; 
    	}
    public void setPickupTime(String pickupTime) {
    	this.pickupTime = pickupTime;
    	}
    public String getNote() { 
    	return note;
    	}
    public void setNote(String note) {
    	this.note = note;
    	}
    public String getStatus() {
    	return status;
    	}
    public void setStatus(String status) {
    	this.status = status;
    	}
    public Timestamp getPostedTime() {
    	return postedTime;
    	}
    public void setPostedTime(Timestamp postedTime) {
    	this.postedTime = postedTime; 
    	}
    public String getDonorName() { 
    	return donorName;
    	}
    public void setDonorName(String donorName) {
    	this.donorName = donorName;
    	}
    public String getNgoName() {
    	return ngoName;
    	}
    public void setNgoName(String ngoName) {
    	this.ngoName = ngoName;
    	}
}