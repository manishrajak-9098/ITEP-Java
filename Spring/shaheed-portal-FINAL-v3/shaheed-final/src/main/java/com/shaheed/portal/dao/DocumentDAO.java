package com.shaheed.portal.dao;

import com.shaheed.portal.model.Document;
import java.util.List;

public interface DocumentDAO {
    void saveDocument(Document document);
    Document getDocumentById(int docId);
    List<Document> getDocumentsBySoldier(int soldierId);
    List<Document> getPendingDocuments();
    List<Document> getDocumentsByVerificationStatus(Document.VerificationStatus status);
    void updateDocument(Document document);
    void updateVerificationStatus(int docId, Document.VerificationStatus status, int officerId, String remarks);
    long getPendingVerificationCount();
}
