package com.shaheed.portal.service.impl;

import com.shaheed.portal.dao.BenefitDAO;
import com.shaheed.portal.model.Benefit;
import com.shaheed.portal.service.BenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BenefitServiceImpl implements BenefitService {

    @Autowired
    private BenefitDAO benefitDAO;

    @Override
    public void addBenefit(Benefit benefit, int adminId) {
        benefit.setCreatedByAdminId(adminId);
        benefit.setStatus(Benefit.BenefitStatus.PENDING);
        benefitDAO.saveBenefit(benefit);
    }

    @Override
    public void updateBenefitStatus(int benefitId, Benefit.BenefitStatus status, String remarks) {
        benefitDAO.updateBenefitStatus(benefitId, status, remarks);
    }

    @Override
    public Benefit getBenefitById(int benefitId) {
        return benefitDAO.getBenefitById(benefitId);
    }

    @Override
    public List<Benefit> getBenefitsBySoldier(int soldierId) {
        return benefitDAO.getBenefitsBySoldier(soldierId);
    }

    @Override
    public List<Benefit> getBenefitsByStatus(Benefit.BenefitStatus status) {
        return benefitDAO.getBenefitsByStatus(status);
    }

    @Override
    public long getPendingCount() { return benefitDAO.getCountByStatus(Benefit.BenefitStatus.PENDING); }

    @Override
    public long getApprovedCount() { return benefitDAO.getCountByStatus(Benefit.BenefitStatus.APPROVED); }

    @Override
    public long getPaidCount() { return benefitDAO.getCountByStatus(Benefit.BenefitStatus.PAID); }
}
