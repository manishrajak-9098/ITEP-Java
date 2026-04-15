package com.shaheed.portal.dao.impl;

import com.shaheed.portal.dao.UserDAO;
import com.shaheed.portal.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * UserDAOImpl — Hibernate SessionFactory use karke database operations
 *
 * @Repository → Spring ko pata chalta hai ye DAO bean hai
 * @Transactional → Har method apna transaction manage karta hai
 */
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserById(int userId) {
        return sessionFactory.getCurrentSession().get(User.class, userId);
    }

    @Override
    public User getUserByUsername(String username) {
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE username = :username AND active = true", User.class);
        query.setParameter("username", username);
        return query.uniqueResult(); // null agar nahi mila
    }

    @Override
    public List<User> getAllOfficers() {
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE role = 'OFFICER' AND active = true ORDER BY fullName", User.class);
        return query.getResultList();
    }

    @Override
    public List<User> getAllUsers() {
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("FROM User ORDER BY createdAt DESC", User.class);
        return query.getResultList();
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void updatePassword(int userId, String hashedPassword) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("UPDATE User SET password = :pass, passwordChanged = true WHERE userId = :id");
        query.setParameter("pass", hashedPassword);
        query.setParameter("id", userId);
        query.executeUpdate();
    }

    @Override
    public void updateLastLogin(int userId) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("UPDATE User SET lastLogin = :time WHERE userId = :id");
        query.setParameter("time", LocalDateTime.now());
        query.setParameter("id", userId);
        query.executeUpdate();
    }

    @Override
    public void deactivateUser(int userId) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("UPDATE User SET active = false WHERE userId = :id");
        query.setParameter("id", userId);
        query.executeUpdate();
    }

    @Override
    public boolean usernameExists(String username) {
        Query<Long> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class);
        query.setParameter("username", username);
        return query.uniqueResult() > 0;
    }

    @Override
    public long getTotalOfficerCount() {
        Query<Long> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(u) FROM User u WHERE u.role = 'OFFICER' AND u.active = true", Long.class);
        return query.uniqueResult();
    }

    @Override
    public long getTotalFamilyCount() {
        Query<Long> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(u) FROM User u WHERE u.role = 'FAMILY' AND u.active = true", Long.class);
        return query.uniqueResult();
    }
}
