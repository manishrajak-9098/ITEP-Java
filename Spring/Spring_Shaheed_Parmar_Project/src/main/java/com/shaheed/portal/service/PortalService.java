package com.shaheed.portal.service;

import com.shaheed.portal.model.*;
import com.shaheed.portal.repository.InMemoryDatabase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PortalService {

    public FamilyMember registerFamily(FamilyMember member) {
        member.setAdmin(false);
        return InMemoryDatabase.saveFamilyMember(member);
    }

    public Optional<FamilyMember> login(String email, String password) {
        return InMemoryDatabase.findFamilyByEmail(email)
                .filter(m -> m.getPassword() != null && m.getPassword().equals(password));
    }

    public Application submitApplication(FamilyMember member,
                                         String shaheedName,
                                         String rank,
                                         String unit,
                                         String serviceNumber,
                                         LocalDate dateOfMartyrdom) {
        Shaheed shaheed = new Shaheed();
        shaheed.setName(shaheedName);
        shaheed.setRank(rank);
        shaheed.setUnit(unit);
        shaheed.setServiceNumber(serviceNumber);
        shaheed.setDateOfMartyrdom(dateOfMartyrdom);

        Application application = new Application();
        application.setFamilyMemberId(member.getId());
        application.setShaheed(shaheed);

        return InMemoryDatabase.saveApplication(application);
    }

    public List<Application> getApplicationsForFamily(Long familyId) {
        return InMemoryDatabase.findApplicationsByFamilyId(familyId);
    }

    public Optional<Application> getApplication(Long id) {
        return InMemoryDatabase.findApplicationById(id);
    }

    public List<Application> getAllApplications() {
        return InMemoryDatabase.findAllApplications();
    }

    public void verifyApplication(Long appId) {
        InMemoryDatabase.findApplicationById(appId).ifPresent(app -> {
            app.setStatus(ApplicationStatus.VERIFIED);
            InMemoryDatabase.saveApplication(app);
        });
    }

    public void rejectApplication(Long appId, String reason) {
        InMemoryDatabase.findApplicationById(appId).ifPresent(app -> {
            app.setStatus(ApplicationStatus.REJECTED);
            app.setRejectionReason(reason);
            InMemoryDatabase.saveApplication(app);
        });
    }

    public FacilityAllocation allocateFacility(Long appId, FacilityType type, LocalDate startDate, String remarks) {
        FacilityAllocation allocation = new FacilityAllocation();
        allocation.setApplicationId(appId);
        allocation.setFacilityType(type);
        allocation.setStartDate(startDate);
        allocation.setRemarks(remarks);
        allocation = InMemoryDatabase.saveAllocation(allocation);

        InMemoryDatabase.findApplicationById(appId).ifPresent(app -> {
            app.getAllocations().add(allocation);
            InMemoryDatabase.saveApplication(app);
        });

        return allocation;
    }

    public long countTotalApplications() {
        return InMemoryDatabase.countApplications();
    }

    public long countPending() {
        return InMemoryDatabase.countPending();
    }

    public long countVerified() {
        return InMemoryDatabase.countVerified();
    }

    public long countRejected() {
        return InMemoryDatabase.countRejected();
    }
}

