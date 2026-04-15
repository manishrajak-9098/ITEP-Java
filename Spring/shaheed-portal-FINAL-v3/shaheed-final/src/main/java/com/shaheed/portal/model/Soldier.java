package com.shaheed.portal.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "soldiers")
public class Soldier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soldier_id")
    private int soldierId;

    @Column(name = "service_number", unique = true, nullable = false, length = 20)
    private String serviceNumber;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "rank", nullable = false, length = 50)
    private String rank;

    @Column(name = "unit", nullable = false, length = 100)
    private String unit;

    @Column(name = "regiment", length = 100)
    private String regiment;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;

    @Column(name = "date_of_martyrdom", nullable = false)
    private LocalDate dateOfMartydom;

    @Column(name = "place_of_martyrdom", length = 200)
    private String placeOfMartydom;

    @Enumerated(EnumType.STRING)
    @Column(name = "martyrdom_category")
    private MartydomCategory martyrdomCategory;

    @Column(name = "awards", length = 500)
    private String awards;

    @Enumerated(EnumType.STRING)
    @Column(name = "case_status")
    private CaseStatus caseStatus = CaseStatus.PENDING;

    @Column(name = "application_id", unique = true, length = 20)
    private String applicationId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by_admin_id")
    private int createdByAdminId;

    @Column(name = "assigned_officer_id")
    private Integer assignedOfficerId;

    @Column(name = "officer_remarks", length = 500)
    private String officerRemarks;

    public enum MartydomCategory {
        BATTLE_CASUALTY, ACCIDENTAL, DISEASE, OTHER
    }

    public enum CaseStatus {
        PENDING, DOCUMENTS_SUBMITTED, UNDER_VERIFICATION,
        VERIFIED, BENEFITS_PROCESSING, COMPLETED, REJECTED
    }

    public Soldier() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public int getSoldierId() { return soldierId; }
    public void setSoldierId(int soldierId) { this.soldierId = soldierId; }

    public String getServiceNumber() { return serviceNumber; }
    public void setServiceNumber(String serviceNumber) { this.serviceNumber = serviceNumber; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getRegiment() { return regiment; }
    public void setRegiment(String regiment) { this.regiment = regiment; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }

    public LocalDate getDateOfMartydom() { return dateOfMartydom; }
    public void setDateOfMartydom(LocalDate dateOfMartydom) { this.dateOfMartydom = dateOfMartydom; }

    public String getPlaceOfMartydom() { return placeOfMartydom; }
    public void setPlaceOfMartydom(String placeOfMartydom) { this.placeOfMartydom = placeOfMartydom; }

    public MartydomCategory getMartydomCategory() { return martyrdomCategory; }
    public void setMartydomCategory(MartydomCategory martyrdomCategory) { this.martyrdomCategory = martyrdomCategory; }

    public String getAwards() { return awards; }
    public void setAwards(String awards) { this.awards = awards; }

    public CaseStatus getCaseStatus() { return caseStatus; }
    public void setCaseStatus(CaseStatus caseStatus) { this.caseStatus = caseStatus; }

    public String getApplicationId() { return applicationId; }
    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public int getCreatedByAdminId() { return createdByAdminId; }
    public void setCreatedByAdminId(int createdByAdminId) { this.createdByAdminId = createdByAdminId; }

    public Integer getAssignedOfficerId() { return assignedOfficerId; }
    public void setAssignedOfficerId(Integer assignedOfficerId) { this.assignedOfficerId = assignedOfficerId; }

    public String getOfficerRemarks() { return officerRemarks; }
    public void setOfficerRemarks(String officerRemarks) { this.officerRemarks = officerRemarks; }
}
