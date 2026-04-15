package com.shaheed.portal.dao.impl;

import com.shaheed.portal.dao.GrievanceDAO;
import com.shaheed.portal.model.Grievance;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class GrievanceDAOImpl implements GrievanceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveGrievance(Grievance grievance) {
        sessionFactory.getCurrentSession().save(grievance);
    }

    @Override
    public Grievance getGrievanceById(int grievanceId) {
        return sessionFactory.getCurrentSession().get(Grievance.class, grievanceId);
    }

    @Override
    public Grievance getGrievanceByNumber(String grievanceNumber) {
        Query<Grievance> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Grievance WHERE grievanceNumber = :gn", Grievance.class);
        q.setParameter("gn", grievanceNumber);
        return q.uniqueResult();
    }

    @Override
    public List<Grievance> getAllGrievances() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Grievance ORDER BY createdAt DESC", Grievance.class)
                .getResultList();
    }

    @Override
    public List<Grievance> getGrievancesByFamily(int familyId) {
        Query<Grievance> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Grievance WHERE familyId = :fid ORDER BY createdAt DESC", Grievance.class);
        q.setParameter("fid", familyId);
        return q.getResultList();
    }

    @Override
    public List<Grievance> getGrievancesBySoldier(int soldierId) {
        Query<Grievance> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Grievance WHERE soldierId = :sid ORDER BY createdAt DESC", Grievance.class);
        q.setParameter("sid", soldierId);
        return q.getResultList();
    }

    @Override
    public List<Grievance> getOpenGrievances() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Grievance WHERE status = 'OPEN' OR status = 'IN_PROGRESS' ORDER BY createdAt ASC", Grievance.class)
                .getResultList();
    }

    @Override
    public void updateGrievance(Grievance grievance) {
        sessionFactory.getCurrentSession().update(grievance);
    }

    @Override
    public long getOpenGrievanceCount() {
        Query<Long> q = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(g) FROM Grievance g WHERE g.status = 'OPEN' OR g.status = 'IN_PROGRESS'", Long.class);
        return q.uniqueResult();
    }
}
