package com.shaheed.portal.dao;

import com.shaheed.portal.model.FamilyMember;
import java.util.List;

public interface FamilyMemberDAO {
    void saveFamilyMember(FamilyMember member);
    FamilyMember getFamilyMemberById(int familyId);
    List<FamilyMember> getFamilyBySoldier(int soldierId);
    FamilyMember getPrimaryBeneficiary(int soldierId);
    void updateFamilyMember(FamilyMember member);
    long getCountBySoldier(int soldierId);
}
