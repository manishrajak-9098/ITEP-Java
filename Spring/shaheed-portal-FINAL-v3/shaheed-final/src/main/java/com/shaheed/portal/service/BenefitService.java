package com.shaheed.portal.service;

import com.shaheed.portal.model.Benefit;
import java.util.List;

public interface BenefitService {
    void addBenefit(Benefit benefit, int adminId);
    void updateBenefitStatus(int benefitId, Benefit.BenefitStatus status, String remarks);
    Benefit getBenefitById(int benefitId);
    List<Benefit> getBenefitsBySoldier(int soldierId);
    List<Benefit> getBenefitsByStatus(Benefit.BenefitStatus status);
    long getPendingCount();
    long getApprovedCount();
    long getPaidCount();
}
