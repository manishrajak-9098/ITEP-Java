package com.shaheed.portal.dao;

import com.shaheed.portal.model.Soldier;
import java.util.List;

public interface SoldierDAO {

    void saveSoldier(Soldier soldier);
    Soldier getSoldierById(int soldierId);
    Soldier getSoldierByApplicationId(String applicationId);
    Soldier getSoldierByServiceNumber(String serviceNumber);
    List<Soldier> getAllSoldiers();
    List<Soldier> getSoldiersByStatus(Soldier.CaseStatus status);
    List<Soldier> getSoldiersAssignedToOfficer(int officerId);
    void updateSoldier(Soldier soldier);
    void updateCaseStatus(int soldierId, Soldier.CaseStatus status);
    long getTotalSoldierCount();
    long getCountByStatus(Soldier.CaseStatus status);
    boolean applicationIdExists(String applicationId);
    boolean serviceNumberExists(String serviceNumber);
}
