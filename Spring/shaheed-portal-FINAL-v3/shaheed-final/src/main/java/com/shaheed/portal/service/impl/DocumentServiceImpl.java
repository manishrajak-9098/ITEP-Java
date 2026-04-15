package com.shaheed.portal.service.impl;

import com.shaheed.portal.dao.DocumentDAO;
import com.shaheed.portal.model.Document;
import com.shaheed.portal.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * DocumentServiceImpl
 * File server pe save karta hai + Database mein record store karta hai
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDAO documentDAO;

    /**
     * FILE UPLOAD LOGIC:
     * 1. File extension check karo (PDF/JPG/PNG only)
     * 2. Unique file name banao (UUID + original extension)
     * 3. Server directory mein save karo
     * 4. Database mein Document record save karo
     */
    @Override
    public void uploadDocument(MultipartFile file, int soldierId, int familyId,
                               Document.DocType docType, String docName, String uploadDir) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new Exception("File empty hai");
        }

        String originalName = file.getOriginalFilename();
        String ext = getExtension(originalName);

        // Allowed file types
        if (!ext.matches("pdf|jpg|jpeg|png")) {
            throw new Exception("Sirf PDF, JPG, PNG allowed hain");
        }

        // Max 10MB
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new Exception("File size 10MB se zyada nahi honi chahiye");
        }

        // Unique filename — security: original name server pe nahi jayega
        String savedName = UUID.randomUUID().toString() + "." + ext;

        // Directory create karo agar exist nahi karta
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // Save to disk
        File dest = new File(dir, savedName);
        file.transferTo(dest);

        // Database record
        Document doc = new Document();
        doc.setSoldierId(soldierId);
        doc.setUploadedByFamilyId(familyId > 0 ? familyId : null);
        doc.setDocType(docType);
        doc.setDocName(docName);
        doc.setFilePath(savedName);
        doc.setFileSize(file.getSize());
        doc.setFileType(ext.toUpperCase());
        doc.setVerificationStatus(Document.VerificationStatus.PENDING);

        documentDAO.saveDocument(doc);
    }

    @Override
    public void verifyDocument(int docId, Document.VerificationStatus status, int officerId, String remarks) {
        documentDAO.updateVerificationStatus(docId, status, officerId, remarks);
    }

    @Override
    public List<Document> getDocumentsBySoldier(int soldierId) {
        return documentDAO.getDocumentsBySoldier(soldierId);
    }

    @Override
    public List<Document> getPendingDocuments() {
        return documentDAO.getPendingDocuments();
    }

    @Override
    public long getPendingCount() {
        return documentDAO.getPendingVerificationCount();
    }

    private String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "";
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
    }
}
