package com.shaheed.portal.service;

import com.shaheed.portal.model.Document;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface DocumentService {
    void uploadDocument(MultipartFile file, int soldierId, int familyId,
                        Document.DocType docType, String docName, String uploadDir) throws Exception;
    void verifyDocument(int docId, Document.VerificationStatus status, int officerId, String remarks);
    List<Document> getDocumentsBySoldier(int soldierId);
    List<Document> getPendingDocuments();
    long getPendingCount();
}
