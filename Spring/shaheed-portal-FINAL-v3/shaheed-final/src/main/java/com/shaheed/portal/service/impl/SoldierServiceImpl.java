package com.shaheed.portal.service.impl;

import com.shaheed.portal.dao.SoldierDAO;
import com.shaheed.portal.model.Soldier;
import com.shaheed.portal.service.SoldierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class SoldierServiceImpl implements SoldierService {

    @Autowired
    private SoldierDAO soldierDAO;

    /**
     * REGISTER SOLDIER — Unique Application ID auto-generate hoga
     * Format: SP-2024-00042  (SP = Shaheed Portal)
     */
    @Override
    public void registerSoldier(Soldier soldier, int adminId) {
        // Application ID generate karo
        String appId = generateApplicationId();
        soldier.setApplicationId(appId);
        soldier.setCreatedByAdminId(adminId);
        soldier.setCaseStatus(Soldier.CaseStatus.PENDING);

        soldierDAO.saveSoldier(soldier);
    }

    /**
     * Unique Application ID: SP-YYYY-XXXXX
     * e.g., SP-2024-00001
     */
    private String generateApplicationId() {
        String year = String.valueOf(LocalDate.now().getYear());
        long count = soldierDAO.getTotalSoldierCount() + 1;
        String seq  = String.format("%05d", count);
        String candidate = "SP-" + year + "-" + seq;

        // Agar already exist kare (edge case) to increment
        while (soldierDAO.applicationIdExists(candidate)) {
            count++;
            seq = String.format("%05d", count);
            candidate = "SP-" + year + "-" + seq;
        }
        return candidate;
    }

    @Override
    public void updateSoldier(Soldier soldier) {
        soldierDAO.updateSoldier(soldier);
    }

    @Override
    public void updateCaseStatus(int soldierId, Soldier.CaseStatus status) {
        soldierDAO.updateCaseStatus(soldierId, status);
    }

    @Override
    public void assignOfficer(int soldierId, int officerId) {
        Soldier soldier = soldierDAO.getSoldierById(soldierId);
        if (soldier == null) throw new RuntimeException("Soldier not found: " + soldierId);
        soldier.setAssignedOfficerId(officerId);
        soldierDAO.updateSoldier(soldier);
    }

    @Override
    public Soldier getSoldierById(int soldierId) {
        return soldierDAO.getSoldierById(soldierId);
    }

    @Override
    public Soldier getSoldierByApplicationId(String applicationId) {
        return soldierDAO.getSoldierByApplicationId(applicationId);
    }

    @Override
    public List<Soldier> getAllSoldiers() {
        return soldierDAO.getAllSoldiers();
    }

    @Override
    public List<Soldier> getSoldiersByStatus(Soldier.CaseStatus status) {
        return soldierDAO.getSoldiersByStatus(status);
    }

    @Override
    public List<Soldier> getSoldiersForOfficer(int officerId) {
        return soldierDAO.getSoldiersAssignedToOfficer(officerId);
    }

    @Override
    public long getTotalCount() { return soldierDAO.getTotalSoldierCount(); }

    @Override
    public long getPendingCount() { return soldierDAO.getCountByStatus(Soldier.CaseStatus.PENDING); }

    @Override
    public long getVerifiedCount() { return soldierDAO.getCountByStatus(Soldier.CaseStatus.VERIFIED); }

    @Override
    public long getCompletedCount() { return soldierDAO.getCountByStatus(Soldier.CaseStatus.COMPLETED); }
}
