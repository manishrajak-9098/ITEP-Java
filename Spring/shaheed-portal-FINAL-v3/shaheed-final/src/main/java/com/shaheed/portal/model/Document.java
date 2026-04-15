package com.shaheed.portal.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private int docId;

    @Column(name = "soldier_id", nullable = false)
    private int soldierId;

    @Column(name = "uploaded_by_family_id")
    private Integer uploadedByFamilyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "doc_type", nullable = false)
    private DocType docType;

    @Column(name = "doc_name", nullable = false, length = 200)
    private String docName;

    @Column(name = "file_path", nullable = false, length = 500)
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_type", length = 50)
    private String fileType;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status")
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    @Column(name = "verified_by_officer_id")
    private Integer verifiedByOfficerId;

    @Column(name = "verification_remarks", length = 500)
    private String verificationRemarks;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    public enum DocType {
        DEATH_CERTIFICATE, IDENTITY_PROOF, BANK_PASSBOOK,
        SERVICE_RECORD, FAMILY_PHOTO, MARRIAGE_CERTIFICATE,
        BIRTH_CERTIFICATE, PENSION_FORM, AWARD_CERTIFICATE, OTHER
    }

    public enum VerificationStatus {
        PENDING, VERIFIED, REJECTED, RESUBMIT_REQUIRED
    }

    public Document() {}

    @PrePersist
    protected void onCreate() { this.uploadedAt = LocalDateTime.now(); }

    public int getDocId() { return docId; }
    public void setDocId(int docId) { this.docId = docId; }

    public int getSoldierId() { return soldierId; }
    public void setSoldierId(int soldierId) { this.soldierId = soldierId; }

    public Integer getUploadedByFamilyId() { return uploadedByFamilyId; }
    public void setUploadedByFamilyId(Integer uploadedByFamilyId) { this.uploadedByFamilyId = uploadedByFamilyId; }

    public DocType getDocType() { return docType; }
    public void setDocType(DocType docType) { this.docType = docType; }

    public String getDocName() { return docName; }
    public void setDocName(String docName) { this.docName = docName; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public VerificationStatus getVerificationStatus() { return verificationStatus; }
    public void setVerificationStatus(VerificationStatus verificationStatus) { this.verificationStatus = verificationStatus; }

    public Integer getVerifiedByOfficerId() { return verifiedByOfficerId; }
    public void setVerifiedByOfficerId(Integer verifiedByOfficerId) { this.verifiedByOfficerId = verifiedByOfficerId; }

    public String getVerificationRemarks() { return verificationRemarks; }
    public void setVerificationRemarks(String verificationRemarks) { this.verificationRemarks = verificationRemarks; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public LocalDateTime getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }
}
