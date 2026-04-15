package com.shaheed.portal.service.impl;

import com.shaheed.portal.dao.GrievanceDAO;
import com.shaheed.portal.model.Grievance;
import com.shaheed.portal.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class GrievanceServiceImpl implements GrievanceService {

    @Autowired
    private GrievanceDAO grievanceDAO;

    @Override
    public void fileGrievance(Grievance grievance) {
        // Auto grievance number: GRV-2024-0001
        String year = String.valueOf(LocalDateTime.now().getYear());
        long count = grievanceDAO.getAllGrievances().size() + 1;
        grievance.setGrievanceNumber("GRV-" + year + "-" + String.format("%04d", count));
        grievance.setStatus(Grievance.GrievanceStatus.OPEN);
        grievanceDAO.saveGrievance(grievance);
    }

    @Override
    public void respondToGrievance(int grievanceId, String response, int respondedById) {
        Grievance g = grievanceDAO.getGrievanceById(grievanceId);
        if (g == null) throw new RuntimeException("Grievance not found");
        g.setResponse(response);
        g.setRespondedById(respondedById);
        g.setStatus(Grievance.GrievanceStatus.RESOLVED);
        g.setResolvedAt(LocalDateTime.now());
        grievanceDAO.updateGrievance(g);
    }

    @Override
    public void closeGrievance(int grievanceId) {
        Grievance g = grievanceDAO.getGrievanceById(grievanceId);
        if (g == null) throw new RuntimeException("Grievance not found");
        g.setStatus(Grievance.GrievanceStatus.CLOSED);
        grievanceDAO.updateGrievance(g);
    }

    @Override
    public Grievance getGrievanceById(int grievanceId) {
        return grievanceDAO.getGrievanceById(grievanceId);
    }

    @Override
    public List<Grievance> getGrievancesByFamily(int familyId) {
        return grievanceDAO.getGrievancesByFamily(familyId);
    }

    @Override
    public List<Grievance> getAllOpenGrievances() {
        return grievanceDAO.getOpenGrievances();
    }

    @Override
    public List<Grievance> getAllGrievances() {
        return grievanceDAO.getAllGrievances();
    }

    @Override
    public long getOpenCount() {
        return grievanceDAO.getOpenGrievanceCount();
    }
}
