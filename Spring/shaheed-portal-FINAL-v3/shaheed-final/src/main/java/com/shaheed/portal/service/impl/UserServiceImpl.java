package com.shaheed.portal.service.impl;

import com.shaheed.portal.dao.UserDAO;
import com.shaheed.portal.model.User;
import com.shaheed.portal.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * UserServiceImpl — Authentication + Account creation logic
 *
 * @Service → Spring ke liye Service bean
 * @Transactional → Database operations ek transaction mein
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * LOGIN METHOD
     * 1. Username se user dhundo
     * 2. BCrypt se password verify karo
     * 3. Last login update karo
     * 4. User object return karo (null = login failed)
     */
    @Override
    public User login(String username, String password) {
        if (username == null || password == null) return null;

        User user = userDAO.getUserByUsername(username.trim());
        if (user == null) return null;

        // BCrypt password check — plain text vs hashed
        if (!BCrypt.checkpw(password, user.getPassword())) return null;

        // Login time update
        userDAO.updateLastLogin(user.getUserId());
        return user;
    }

    /**
     * CHANGE PASSWORD — Old password verify karo, phir change karo
     */
    @Override
    public void changePassword(int userId, String oldPassword, String newPassword) {
        User user = userDAO.getUserById(userId);
        if (user == null) throw new RuntimeException("User not found");

        if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
            throw new RuntimeException("Purana password galat hai");
        }

        String hashedNew = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        userDAO.updatePassword(userId, hashedNew);
    }

    /**
     * FORCE CHANGE PASSWORD — Admin use karta hai, old password check nahi
     */
    @Override
    public void forceChangePassword(int userId, String newPassword) {
        String hashed = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        userDAO.updatePassword(userId, hashed);
    }

    /**
     * OFFICER ACCOUNT CREATE — Admin karta hai
     * Auto-generates username: firstname.lastname (e.g., raj.kumar)
     * Temp password generate hota hai
     */
    @Override
    public void createOfficerAccount(String fullName, String email, String phone, String department) {
        // Username generate karo from name
        String username = generateUsername(fullName);
        String tempPass  = generateTempPassword();

        User officer = new User();
        officer.setFullName(fullName);
        officer.setUsername(username);
        officer.setPassword(BCrypt.hashpw(tempPass, BCrypt.gensalt()));
        officer.setRole(User.Role.OFFICER);
        officer.setEmail(email);
        officer.setPhone(phone);
        officer.setPasswordChanged(false); // Pehli baar change karna hoga
        officer.setActive(true);
        officer.setCreatedAt(LocalDateTime.now());

        userDAO.saveUser(officer);

        // TODO: Real project mein ye email/SMS bheja jaata hai
        System.out.println("=== OFFICER ACCOUNT CREATED ===");
        System.out.println("Username : " + username);
        System.out.println("TempPass : " + tempPass);
        System.out.println("================================");
    }

    /**
     * FAMILY ACCOUNT CREATE — Soldier register hone ke baad Admin karta hai
     */
    @Override
    public void createFamilyAccount(int soldierId, int familyMemberId, String fullName, String email) {
        String username  = "FAM" + String.format("%05d", familyMemberId);
        String tempPass  = generateTempPassword();

        User familyUser = new User();
        familyUser.setFullName(fullName);
        familyUser.setUsername(username);
        familyUser.setPassword(BCrypt.hashpw(tempPass, BCrypt.gensalt()));
        familyUser.setRole(User.Role.FAMILY);
        familyUser.setEmail(email);
        familyUser.setLinkedFamilyId(familyMemberId);
        familyUser.setPasswordChanged(false);
        familyUser.setActive(true);
        familyUser.setCreatedAt(LocalDateTime.now());

        userDAO.saveUser(familyUser);

        System.out.println("=== FAMILY ACCOUNT CREATED ===");
        System.out.println("Username : " + username);
        System.out.println("TempPass : " + tempPass);
        System.out.println("===============================");
    }

    /**
     * Auto-generate username from full name
     * "Raj Kumar" → "raj.kumar"
     * If taken → "raj.kumar1"
     */
    private String generateUsername(String fullName) {
        String[] parts = fullName.trim().toLowerCase().split("\\s+");
        String base;
        if (parts.length >= 2) {
            base = parts[0] + "." + parts[parts.length - 1];
        } else {
            base = parts[0];
        }
        // Remove special chars
        base = base.replaceAll("[^a-z0-9.]", "");

        String candidate = base;
        int counter = 1;
        while (userDAO.usernameExists(candidate)) {
            candidate = base + counter;
            counter++;
        }
        return candidate;
    }

    /**
     * 8 character random temp password: letters + numbers
     * e.g. "Xk7pR2mQ"
     */
    @Override
    public String generateTempPassword() {
        String chars = "ABCDEFGHJKMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz23456789";
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            sb.append(chars.charAt(r.nextInt(chars.length())));
        }
        return sb.toString();
    }

    @Override
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public List<User> getAllOfficers() {
        return userDAO.getAllOfficers();
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void deactivateUser(int userId) {
        userDAO.deactivateUser(userId);
    }

    @Override
    public long getTotalOfficerCount() {
        return userDAO.getTotalOfficerCount();
    }

    @Override
    public long getTotalFamilyCount() {
        return userDAO.getTotalFamilyCount();
    }
}
