package com.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="course_many_many_bi")
public class Course{ // non-owning side
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cid")
	int cid;
	
	@Column(name="coursename")
	String coursename;
	
	@ManyToMany(mappedBy = "course")
	List<Aspirant> aspirant = new ArrayList<Aspirant>(); 
	
	public List<Aspirant> getAspitant() {
		return aspirant;
	}

	public void setAspirant(List<Aspirant> aspirant) {
		this.aspirant = aspirant;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	
}