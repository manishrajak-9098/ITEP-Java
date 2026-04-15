package com.shaheed.portal.dao.impl;

import com.shaheed.portal.dao.SoldierDAO;
import com.shaheed.portal.model.Soldier;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SoldierDAOImpl implements SoldierDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveSoldier(Soldier soldier) {
        sessionFactory.getCurrentSession().save(soldier);
    }

    @Override
    public Soldier getSoldierById(int soldierId) {
        return sessionFactory.getCurrentSession().get(Soldier.class, soldierId);
    }

    @Override
    public Soldier getSoldierByApplicationId(String applicationId) {
        Query<Soldier> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Soldier WHERE applicationId = :appId", Soldier.class);
        q.setParameter("appId", applicationId);
        return q.uniqueResult();
    }

    @Override
    public Soldier getSoldierByServiceNumber(String serviceNumber) {
        Query<Soldier> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Soldier WHERE serviceNumber = :sn", Soldier.class);
        q.setParameter("sn", serviceNumber);
        return q.uniqueResult();
    }

    @Override
    public List<Soldier> getAllSoldiers() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Soldier ORDER BY createdAt DESC", Soldier.class)
                .getResultList();
    }

    @Override
    public List<Soldier> getSoldiersByStatus(Soldier.CaseStatus status) {
        Query<Soldier> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Soldier WHERE caseStatus = :status ORDER BY createdAt DESC", Soldier.class);
        q.setParameter("status", status);
        return q.getResultList();
    }

    @Override
    public List<Soldier> getSoldiersAssignedToOfficer(int officerId) {
        Query<Soldier> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Soldier WHERE assignedOfficerId = :oid ORDER BY updatedAt DESC", Soldier.class);
        q.setParameter("oid", officerId);
        return q.getResultList();
    }

    @Override
    public void updateSoldier(Soldier soldier) {
        sessionFactory.getCurrentSession().update(soldier);
    }

    @Override
    public void updateCaseStatus(int soldierId, Soldier.CaseStatus status) {
        Query q = sessionFactory.getCurrentSession()
                .createQuery("UPDATE Soldier SET caseStatus = :status WHERE soldierId = :id");
        q.setParameter("status", status);
        q.setParameter("id", soldierId);
        q.executeUpdate();
    }

    @Override
    public long getTotalSoldierCount() {
        Query<Long> q = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(s) FROM Soldier s", Long.class);
        return q.uniqueResult();
    }

    @Override
    public long getCountByStatus(Soldier.CaseStatus status) {
        Query<Long> q = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(s) FROM Soldier s WHERE s.caseStatus = :status", Long.class);
        q.setParameter("status", status);
        return q.uniqueResult();
    }

    @Override
    public boolean applicationIdExists(String applicationId) {
        Query<Long> q = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(s) FROM Soldier s WHERE s.applicationId = :id", Long.class);
        q.setParameter("id", applicationId);
        return q.uniqueResult() > 0;
    }

    @Override
    public boolean serviceNumberExists(String serviceNumber) {
        Query<Long> q = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(s) FROM Soldier s WHERE s.serviceNumber = :sn", Long.class);
        q.setParameter("sn", serviceNumber);
        return q.uniqueResult() > 0;
    }
}
