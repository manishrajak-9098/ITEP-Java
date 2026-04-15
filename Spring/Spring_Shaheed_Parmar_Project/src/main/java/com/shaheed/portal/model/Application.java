package com.shaheed.portal.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private Long id;
    private Long familyMemberId;
    private Shaheed shaheed;

    private String armyIdCardPath;
    private String shaheedCertificatePath;
    private String aadhaarPath;
    private String familyProofPath;
    private String shaheedPhotoPath;

    private ApplicationStatus status = ApplicationStatus.PENDING;
    private String rejectionReason;
    private LocalDateTime submittedAt = LocalDateTime.now();
    private LocalDateTime verifiedAt;

    private List<FacilityAllocation> allocations = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Long familyMemberId) {
        this.familyMemberId = familyMemberId;
    }

    public Shaheed getShaheed() {
        return shaheed;
    }

    public void setShaheed(Shaheed shaheed) {
        this.shaheed = shaheed;
    }

    public String getArmyIdCardPath() {
        return armyIdCardPath;
    }

    public void setArmyIdCardPath(String armyIdCardPath) {
        this.armyIdCardPath = armyIdCardPath;
    }

    public String getShaheedCertificatePath() {
        return shaheedCertificatePath;
    }

    public void setShaheedCertificatePath(String shaheedCertificatePath) {
        this.shaheedCertificatePath = shaheedCertificatePath;
    }

    public String getAadhaarPath() {
        return aadhaarPath;
    }

    public void setAadhaarPath(String aadhaarPath) {
        this.aadhaarPath = aadhaarPath;
    }

    public String getFamilyProofPath() {
        return familyProofPath;
    }

    public void setFamilyProofPath(String familyProofPath) {
        this.familyProofPath = familyProofPath;
    }

    public String getShaheedPhotoPath() {
        return shaheedPhotoPath;
    }

    public void setShaheedPhotoPath(String shaheedPhotoPath) {
        this.shaheedPhotoPath = shaheedPhotoPath;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public List<FacilityAllocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<FacilityAllocation> allocations) {
        this.allocations = allocations;
    }
}

