package com.jpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "army")
public class Army {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;

    @Column(name = "army_rank")
    private String rank;

    private String unit;
    private String posting;
    private int experience;
    private double salary;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getPosting() { return posting; }
    public void setPosting(String posting) { this.posting = posting; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
}
