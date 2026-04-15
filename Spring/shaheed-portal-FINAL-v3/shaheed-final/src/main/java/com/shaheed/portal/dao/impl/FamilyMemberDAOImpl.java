package com.shaheed.portal.dao.impl;

import com.shaheed.portal.dao.FamilyMemberDAO;
import com.shaheed.portal.model.FamilyMember;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FamilyMemberDAOImpl implements FamilyMemberDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveFamilyMember(FamilyMember member) {
        sessionFactory.getCurrentSession().save(member);
    }

    @Override
    public FamilyMember getFamilyMemberById(int familyId) {
        return sessionFactory.getCurrentSession().get(FamilyMember.class, familyId);
    }

    @Override
    public List<FamilyMember> getFamilyBySoldier(int soldierId) {
        Query<FamilyMember> q = sessionFactory.getCurrentSession()
                .createQuery("FROM FamilyMember WHERE soldierId = :sid ORDER BY primaryBeneficiary DESC", FamilyMember.class);
        q.setParameter("sid", soldierId);
        return q.getResultList();
    }

    @Override
    public FamilyMember getPrimaryBeneficiary(int soldierId) {
        Query<FamilyMember> q = sessionFactory.getCurrentSession()
                .createQuery("FROM FamilyMember WHERE soldierId = :sid AND primaryBeneficiary = true", FamilyMember.class);
        q.setParameter("sid", soldierId);
        return q.uniqueResult();
    }

    @Override
    public void updateFamilyMember(FamilyMember member) {
        sessionFactory.getCurrentSession().update(member);
    }

    @Override
    public long getCountBySoldier(int soldierId) {
        Query<Long> q = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(f) FROM FamilyMember f WHERE f.soldierId = :sid", Long.class);
        q.setParameter("sid", soldierId);
        return q.uniqueResult();
    }
}
