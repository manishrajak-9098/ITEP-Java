package com.shaheed.portal.service;

import com.shaheed.portal.model.Soldier;
import java.util.List;

public interface SoldierService {

    // Admin: New shaheed record banana
    void registerSoldier(Soldier soldier, int adminId);

    // Update karna
    void updateSoldier(Soldier soldier);

    // Status change karna
    void updateCaseStatus(int soldierId, Soldier.CaseStatus status);

    // Officer assign karna
    void assignOfficer(int soldierId, int officerId);

    // Getters
    Soldier getSoldierById(int soldierId);
    Soldier getSoldierByApplicationId(String applicationId);
    List<Soldier> getAllSoldiers();
    List<Soldier> getSoldiersByStatus(Soldier.CaseStatus status);
    List<Soldier> getSoldiersForOfficer(int officerId);

    // Dashboard stats
    long getTotalCount();
    long getPendingCount();
    long getVerifiedCount();
    long getCompletedCount();
}
