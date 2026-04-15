package com.shaheed.portal.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "grievances")
public class Grievance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grievance_id")
    private int grievanceId;

    @Column(name = "soldier_id", nullable = false)
    private int soldierId;

    @Column(name = "family_id", nullable = false)
    private int familyId;

    @Column(name = "grievance_number", unique = true, length = 20)
    private String grievanceNumber;

    @Column(name = "subject", nullable = false, length = 200)
    private String subject;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private GrievanceCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private GrievanceStatus status = GrievanceStatus.OPEN;

    @Column(name = "response", length = 1000)
    private String response;

    @Column(name = "responded_by_id")
    private Integer respondedById;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    public enum GrievanceCategory {
        BENEFIT_DELAY, DOCUMENT_ISSUE, PENSION_PROBLEM,
        INFORMATION_REQUIRED, INCORRECT_DATA, OTHER
    }

    public enum GrievanceStatus {
        OPEN, IN_PROGRESS, RESOLVED, CLOSED
    }

    public Grievance() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() { this.updatedAt = LocalDateTime.now(); }

    public int getGrievanceId() { return grievanceId; }
    public void setGrievanceId(int grievanceId) { this.grievanceId = grievanceId; }

    public int getSoldierId() { return soldierId; }
    public void setSoldierId(int soldierId) { this.soldierId = soldierId; }

    public int getFamilyId() { return familyId; }
    public void setFamilyId(int familyId) { this.familyId = familyId; }

    public String getGrievanceNumber() { return grievanceNumber; }
    public void setGrievanceNumber(String grievanceNumber) { this.grievanceNumber = grievanceNumber; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public GrievanceCategory getCategory() { return category; }
    public void setCategory(GrievanceCategory category) { this.category = category; }

    public GrievanceStatus getStatus() { return status; }
    public void setStatus(GrievanceStatus status) { this.status = status; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public Integer getRespondedById() { return respondedById; }
    public void setRespondedById(Integer respondedById) { this.respondedById = respondedById; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }
}
