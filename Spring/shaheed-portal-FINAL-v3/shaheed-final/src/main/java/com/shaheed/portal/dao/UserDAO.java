package com.shaheed.portal.dao;

import com.shaheed.portal.model.User;
import java.util.List;

/**
 * UserDAO Interface — User CRUD operations
 * Spring MVC pattern: Interface define karo, Impl mein implement karo
 */
public interface UserDAO {

    // ===== CREATE =====
    void saveUser(User user);

    // ===== READ =====
    User getUserById(int userId);
    User getUserByUsername(String username);
    List<User> getAllOfficers();
    List<User> getAllUsers();

    // ===== UPDATE =====
    void updateUser(User user);
    void updatePassword(int userId, String hashedPassword);
    void updateLastLogin(int userId);

    // ===== DELETE / DEACTIVATE =====
    void deactivateUser(int userId);

    // ===== UTILITY =====
    boolean usernameExists(String username);
    long getTotalOfficerCount();
    long getTotalFamilyCount();
}
