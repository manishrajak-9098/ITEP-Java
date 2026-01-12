package com.servlet.dto;

import java.io.Serializable;

public class Task implements Serializable{
	
	private int uid;
	private String category, task , priority;
	
	
	public Task() {
		super();
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
}