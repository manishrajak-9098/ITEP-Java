package com.shaheed.portal.service;

import com.shaheed.portal.model.Grievance;
import java.util.List;

public interface GrievanceService {
    void fileGrievance(Grievance grievance);
    void respondToGrievance(int grievanceId, String response, int respondedById);
    void closeGrievance(int grievanceId);
    Grievance getGrievanceById(int grievanceId);
    List<Grievance> getGrievancesByFamily(int familyId);
    List<Grievance> getAllOpenGrievances();
    List<Grievance> getAllGrievances();
    long getOpenCount();
}
