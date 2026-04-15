package com.shaheed.portal.repository;

import com.shaheed.portal.model.Application;
import com.shaheed.portal.model.FacilityAllocation;
import com.shaheed.portal.model.FamilyMember;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryDatabase {

    private static final Map<Long, FamilyMember> FAMILY_MEMBERS = new HashMap<>();
    private static final Map<Long, Application> APPLICATIONS = new HashMap<>();
    private static final Map<Long, FacilityAllocation> ALLOCATIONS = new HashMap<>();

    private static final AtomicLong FAMILY_SEQ = new AtomicLong(1);
    private static final AtomicLong APPLICATION_SEQ = new AtomicLong(1);
    private static final AtomicLong ALLOCATION_SEQ = new AtomicLong(1);

    // Pre-configured admin for demo
    static {
        FamilyMember admin = new FamilyMember();
        admin.setId(FAMILY_SEQ.getAndIncrement());
        admin.setFullName("Government Admin");
        admin.setEmail("admin@gov.in");
        admin.setPassword("admin123");
        admin.setAdmin(true);
        FAMILY_MEMBERS.put(admin.getId(), admin);
    }

    public static FamilyMember saveFamilyMember(FamilyMember member) {
        if (member.getId() == null) {
            member.setId(FAMILY_SEQ.getAndIncrement());
        }
        FAMILY_MEMBERS.put(member.getId(), member);
        return member;
    }

    public static Optional<FamilyMember> findFamilyByEmail(String email) {
        return FAMILY_MEMBERS.values().stream()
                .filter(m -> email.equalsIgnoreCase(m.getEmail()))
                .findFirst();
    }

    public static Optional<FamilyMember> findFamilyById(Long id) {
        return Optional.ofNullable(FAMILY_MEMBERS.get(id));
    }

    public static Application saveApplication(Application application) {
        if (application.getId() == null) {
            application.setId(APPLICATION_SEQ.getAndIncrement());
        }
        APPLICATIONS.put(application.getId(), application);
        return application;
    }

    public static Optional<Application> findApplicationById(Long id) {
        return Optional.ofNullable(APPLICATIONS.get(id));
    }

    public static List<Application> findApplicationsByFamilyId(Long familyId) {
        List<Application> list = new ArrayList<>();
        for (Application app : APPLICATIONS.values()) {
            if (Objects.equals(app.getFamilyMemberId(), familyId)) {
                list.add(app);
            }
        }
        return list;
    }

    public static List<Application> findAllApplications() {
        return new ArrayList<>(APPLICATIONS.values());
    }

    public static FacilityAllocation saveAllocation(FacilityAllocation allocation) {
        if (allocation.getId() == null) {
            allocation.setId(ALLOCATION_SEQ.getAndIncrement());
        }
        ALLOCATIONS.put(allocation.getId(), allocation);
        return allocation;
    }

    public static List<FacilityAllocation> findAllocationsByApplicationId(Long applicationId) {
        List<FacilityAllocation> list = new ArrayList<>();
        for (FacilityAllocation allocation : ALLOCATIONS.values()) {
            if (Objects.equals(allocation.getApplicationId(), applicationId)) {
                list.add(allocation);
            }
        }
        return list;
    }

    public static long countApplications() {
        return APPLICATIONS.size();
    }

    public static long countPending() {
        return APPLICATIONS.values().stream()
                .filter(a -> a.getStatus() != null && a.getStatus().name().equals("PENDING"))
                .count();
    }

    public static long countVerified() {
        return APPLICATIONS.values().stream()
                .filter(a -> a.getStatus() != null && a.getStatus().name().equals("VERIFIED"))
                .count();
    }

    public static long countRejected() {
        return APPLICATIONS.values().stream()
                .filter(a -> a.getStatus() != null && a.getStatus().name().equals("REJECTED"))
                .count();
    }
}

