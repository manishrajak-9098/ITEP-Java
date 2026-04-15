package com.jsp.model;
import java.sql.Timestamp;

public class Feedback {
    private int id;
    private String name;
    private String email;
    private String message;
    private Timestamp sentDate;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Timestamp getSentDate() { return sentDate; }
    public void setSentDate(Timestamp sentDate) { this.sentDate = sentDate; }
}