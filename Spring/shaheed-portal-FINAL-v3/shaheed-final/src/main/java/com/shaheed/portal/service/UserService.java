package com.shaheed.portal.service;

import com.shaheed.portal.model.User;
import java.util.List;

/**
 * UserService Interface
 * Business logic: Authentication, Authorization, Account creation
 */
public interface UserService {

    // ===== AUTHENTICATION =====
    User login(String username, String password);
    void changePassword(int userId, String oldPassword, String newPassword);
    void forceChangePassword(int userId, String newPassword);

    // ===== ACCOUNT MANAGEMENT (Admin only) =====
    void createOfficerAccount(String fullName, String email, String phone, String department);
    void createFamilyAccount(int soldierId, int familyMemberId, String fullName, String email);
    String generateTempPassword();

    // ===== GETTERS =====
    User getUserById(int userId);
    List<User> getAllOfficers();
    List<User> getAllUsers();

    // ===== STATUS =====
    void deactivateUser(int userId);

    // ===== DASHBOARD COUNTS =====
    long getTotalOfficerCount();
    long getTotalFamilyCount();
}
