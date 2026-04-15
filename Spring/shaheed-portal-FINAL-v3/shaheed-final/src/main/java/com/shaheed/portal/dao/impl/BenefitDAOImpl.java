package com.shaheed.portal.dao.impl;

import com.shaheed.portal.dao.BenefitDAO;
import com.shaheed.portal.model.Benefit;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class BenefitDAOImpl implements BenefitDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveBenefit(Benefit benefit) {
        sessionFactory.getCurrentSession().save(benefit);
    }

    @Override
    public Benefit getBenefitById(int benefitId) {
        return sessionFactory.getCurrentSession().get(Benefit.class, benefitId);
    }

    @Override
    public List<Benefit> getBenefitsBySoldier(int soldierId) {
        Query<Benefit> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Benefit WHERE soldierId = :sid ORDER BY createdAt DESC", Benefit.class);
        q.setParameter("sid", soldierId);
        return q.getResultList();
    }

    @Override
    public List<Benefit> getBenefitsByStatus(Benefit.BenefitStatus status) {
        Query<Benefit> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Benefit WHERE status = :status ORDER BY createdAt DESC", Benefit.class);
        q.setParameter("status", status);
        return q.getResultList();
    }

    @Override
    public List<Benefit> getBenefitsBySoldierAndStatus(int soldierId, Benefit.BenefitStatus status) {
        Query<Benefit> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Benefit WHERE soldierId = :sid AND status = :status", Benefit.class);
        q.setParameter("sid", soldierId);
        q.setParameter("status", status);
        return q.getResultList();
    }

    @Override
    public void updateBenefit(Benefit benefit) {
        sessionFactory.getCurrentSession().update(benefit);
    }

    @Override
    public void updateBenefitStatus(int benefitId, Benefit.BenefitStatus status, String remarks) {
        Query q = sessionFactory.getCurrentSession()
                .createQuery("UPDATE Benefit SET status = :status, remarks = :remarks WHERE benefitId = :id");
        q.setParameter("status", status);
        q.setParameter("remarks", remarks);
        q.setParameter("id", benefitId);
        q.executeUpdate();
    }

    @Override
    public long getCountByStatus(Benefit.BenefitStatus status) {
        Query<Long> q = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(b) FROM Benefit b WHERE b.status = :status", Long.class);
        q.setParameter("status", status);
        return q.uniqueResult();
    }

    @Override
    public long getCountBySoldier(int soldierId) {
        Query<Long> q = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(b) FROM Benefit b WHERE b.soldierId = :sid", Long.class);
        q.setParameter("sid", soldierId);
        return q.uniqueResult();
    }
}
