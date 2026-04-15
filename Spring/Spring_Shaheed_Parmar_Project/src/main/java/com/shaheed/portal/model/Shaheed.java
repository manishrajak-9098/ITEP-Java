package com.shaheed.portal.model;

import java.time.LocalDate;

public class Shaheed {

    private Long id;
    private String name;
    private String rank;
    private String unit;
    private String serviceNumber;
    private LocalDate dateOfMartyrdom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public LocalDate getDateOfMartyrdom() {
        return dateOfMartyrdom;
    }

    public void setDateOfMartyrdom(LocalDate dateOfMartyrdom) {
        this.dateOfMartyrdom = dateOfMartyrdom;
    }
}

