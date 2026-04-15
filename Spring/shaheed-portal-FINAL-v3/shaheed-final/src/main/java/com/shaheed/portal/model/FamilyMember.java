package com.shaheed.portal.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "family_members")
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_id")
    private int familyId;

    @Column(name = "soldier_id", nullable = false)
    private int soldierId;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation", nullable = false)
    private Relation relation;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "aadhar_number", length = 20)
    private String aadharNumber;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "pincode", length = 10)
    private String pincode;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "bank_account_number", length = 30)
    private String bankAccountNumber;

    @Column(name = "bank_name", length = 100)
    private String bankName;

    @Column(name = "ifsc_code", length = 20)
    private String ifscCode;

    @Column(name = "bank_branch", length = 100)
    private String bankBranch;

    @Column(name = "is_primary_beneficiary")
    private boolean primaryBeneficiary = false;

    @Column(name = "has_login_access")
    private boolean hasLoginAccess = false;

    public enum Relation {
        WIFE, MOTHER, FATHER, SON, DAUGHTER, BROTHER, SISTER, OTHER
    }

    public FamilyMember() {}

    public int getFamilyId() { return familyId; }
    public void setFamilyId(int familyId) { this.familyId = familyId; }

    public int getSoldierId() { return soldierId; }
    public void setSoldierId(int soldierId) { this.soldierId = soldierId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Relation getRelation() { return relation; }
    public void setRelation(Relation relation) { this.relation = relation; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getAadharNumber() { return aadharNumber; }
    public void setAadharNumber(String aadharNumber) { this.aadharNumber = aadharNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBankAccountNumber() { return bankAccountNumber; }
    public void setBankAccountNumber(String bankAccountNumber) { this.bankAccountNumber = bankAccountNumber; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getIfscCode() { return ifscCode; }
    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }

    public String getBankBranch() { return bankBranch; }
    public void setBankBranch(String bankBranch) { this.bankBranch = bankBranch; }

    public boolean isPrimaryBeneficiary() { return primaryBeneficiary; }
    public void setPrimaryBeneficiary(boolean primaryBeneficiary) { this.primaryBeneficiary = primaryBeneficiary; }

    public boolean isHasLoginAccess() { return hasLoginAccess; }
    public void setHasLoginAccess(boolean hasLoginAccess) { this.hasLoginAccess = hasLoginAccess; }
}
