package com.shaheed.portal.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "benefits")
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benefit_id")
    private int benefitId;

    @Column(name = "soldier_id", nullable = false)
    private int soldierId;

    @Column(name = "family_id")
    private Integer familyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "benefit_type", nullable = false)
    private BenefitType benefitType;

    @Column(name = "benefit_name", nullable = false, length = 200)
    private String benefitName;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "amount_unit", length = 50)
    private String amountUnit;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BenefitStatus status = BenefitStatus.PENDING;

    @Column(name = "approval_date")
    private LocalDate approvalDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "sanctioned_by", length = 150)
    private String sanctionedBy;

    @Column(name = "remarks", length = 500)
    private String remarks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by_admin_id")
    private int createdByAdminId;

    public enum BenefitType {
        EX_GRATIA, FAMILY_PENSION, EDUCATION_HELP,
        MEDICAL_HELP, HOUSING_HELP, JOB_ASSISTANCE, SCHOLARSHIP, OTHER
    }

    public enum BenefitStatus {
        PENDING, APPROVED, UNDER_PROCESS, PAID, REJECTED, ON_HOLD
    }

    public Benefit() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() { this.updatedAt = LocalDateTime.now(); }

    public int getBenefitId() { return benefitId; }
    public void setBenefitId(int benefitId) { this.benefitId = benefitId; }

    public int getSoldierId() { return soldierId; }
    public void setSoldierId(int soldierId) { this.soldierId = soldierId; }

    public Integer getFamilyId() { return familyId; }
    public void setFamilyId(Integer familyId) { this.familyId = familyId; }

    public BenefitType getBenefitType() { return benefitType; }
    public void setBenefitType(BenefitType benefitType) { this.benefitType = benefitType; }

    public String getBenefitName() { return benefitName; }
    public void setBenefitName(String benefitName) { this.benefitName = benefitName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getAmountUnit() { return amountUnit; }
    public void setAmountUnit(String amountUnit) { this.amountUnit = amountUnit; }

    public BenefitStatus getStatus() { return status; }
    public void setStatus(BenefitStatus status) { this.status = status; }

    public LocalDate getApprovalDate() { return approvalDate; }
    public void setApprovalDate(LocalDate approvalDate) { this.approvalDate = approvalDate; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public String getSanctionedBy() { return sanctionedBy; }
    public void setSanctionedBy(String sanctionedBy) { this.sanctionedBy = sanctionedBy; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public int getCreatedByAdminId() { return createdByAdminId; }
    public void setCreatedByAdminId(int createdByAdminId) { this.createdByAdminId = createdByAdminId; }
}
