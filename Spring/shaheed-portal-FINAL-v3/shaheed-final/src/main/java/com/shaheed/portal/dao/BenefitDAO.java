package com.shaheed.portal.dao;

import com.shaheed.portal.model.Benefit;
import java.util.List;

public interface BenefitDAO {

    void saveBenefit(Benefit benefit);
    Benefit getBenefitById(int benefitId);
    List<Benefit> getBenefitsBySoldier(int soldierId);
    List<Benefit> getBenefitsByStatus(Benefit.BenefitStatus status);
    List<Benefit> getBenefitsBySoldierAndStatus(int soldierId, Benefit.BenefitStatus status);
    void updateBenefit(Benefit benefit);
    void updateBenefitStatus(int benefitId, Benefit.BenefitStatus status, String remarks);
    long getCountByStatus(Benefit.BenefitStatus status);
    long getCountBySoldier(int soldierId);
}
