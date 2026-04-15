package com.shaheed.portal.dao.impl;

import com.shaheed.portal.dao.DocumentDAO;
import com.shaheed.portal.model.Document;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class DocumentDAOImpl implements DocumentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveDocument(Document document) {
        sessionFactory.getCurrentSession().save(document);
    }

    @Override
    public Document getDocumentById(int docId) {
        return sessionFactory.getCurrentSession().get(Document.class, docId);
    }

    @Override
    public List<Document> getDocumentsBySoldier(int soldierId) {
        Query<Document> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Document WHERE soldierId = :sid ORDER BY uploadedAt DESC", Document.class);
        q.setParameter("sid", soldierId);
        return q.getResultList();
    }

    @Override
    public List<Document> getPendingDocuments() {
        Query<Document> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Document WHERE verificationStatus = 'PENDING' ORDER BY uploadedAt ASC", Document.class);
        return q.getResultList();
    }

    @Override
    public List<Document> getDocumentsByVerificationStatus(Document.VerificationStatus status) {
        Query<Document> q = sessionFactory.getCurrentSession()
                .createQuery("FROM Document WHERE verificationStatus = :status ORDER BY uploadedAt DESC", Document.class);
        q.setParameter("status", status);
        return q.getResultList();
    }

    @Override
    public void updateDocument(Document document) {
        sessionFactory.getCurrentSession().update(document);
    }

    @Override
    public void updateVerificationStatus(int docId, Document.VerificationStatus status, int officerId, String remarks) {
        Query q = sessionFactory.getCurrentSession()
                .createQuery("UPDATE Document SET verificationStatus = :status, " +
                        "verifiedByOfficerId = :oid, verificationRemarks = :remarks, " +
                        "verifiedAt = :time WHERE docId = :id");
        q.setParameter("status", status);
        q.setParameter("oid", officerId);
        q.setParameter("remarks", remarks);
        q.setParameter("time", LocalDateTime.now());
        q.setParameter("id", docId);
        q.executeUpdate();
    }

    @Override
    public long getPendingVerificationCount() {
        Query<Long> q = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(d) FROM Document d WHERE d.verificationStatus = 'PENDING'", Long.class);
        return q.uniqueResult();
    }
}
