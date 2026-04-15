package com.shaheed.portal.dao;

import com.shaheed.portal.model.Grievance;
import java.util.List;

public interface GrievanceDAO {
    void saveGrievance(Grievance grievance);
    Grievance getGrievanceById(int grievanceId);
    Grievance getGrievanceByNumber(String grievanceNumber);
    List<Grievance> getAllGrievances();
    List<Grievance> getGrievancesByFamily(int familyId);
    List<Grievance> getGrievancesBySoldier(int soldierId);
    List<Grievance> getOpenGrievances();
    void updateGrievance(Grievance grievance);
    long getOpenGrievanceCount();
}
