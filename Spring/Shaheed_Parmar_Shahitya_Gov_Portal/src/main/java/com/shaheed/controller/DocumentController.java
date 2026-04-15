package com.shaheed.controller;

import com.shaheed.entity.*;
import com.shaheed.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@Controller
public class DocumentController {

    @Autowired private DocumentService documentService;
    @Autowired private ApplicationService applicationService;

    @GetMapping("/uploadDocuments")
    public String uploadPage(HttpSession session, Model model) {
        FamilyUser user = (FamilyUser) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";
        try {
            model.addAttribute("existingDoc", documentService.findByFamilyId(user.getId()));
        } catch (Exception e) { 
            model.addAttribute("existingDoc", null); 
        }
        return "upload-document";
    }

    @PostMapping("/uploadDocuments")
    public String uploadDocs(
            @RequestParam("aadhaar") MultipartFile aadhaar,
            @RequestParam("armyId") MultipartFile armyId,
            @RequestParam("certificate") MultipartFile certificate,
            @RequestParam("familyProof") MultipartFile familyProof,
            @RequestParam("photo") MultipartFile photo,
            HttpSession session, Model model) {

        FamilyUser user = (FamilyUser) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

       
        String uploadDir = "C:/Mpif itep java/Spring/Shaheed_Parmar_Shahitya_Gov_Portal/src/main/webapp/uploads/";

        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            Document existing = null;
            try {
                existing = documentService.findByFamilyId(user.getId());
            } catch (Exception ignored) {}

            Document doc = (existing != null) ? existing : new Document();
            doc.setFamilyId(user.getId());
            
            Long shaheedId = (Long) session.getAttribute("savedShaheedId");
            if (shaheedId != null) doc.setShaheedId(shaheedId);

            String prefix = "user_" + user.getId() + "_";

            // 1. Aadhaar Upload
            if (!aadhaar.isEmpty()) {
                String name = prefix + "aadhaar_" + sanitize(aadhaar.getOriginalFilename());
                aadhaar.transferTo(new File(uploadDir + name));
                doc.setAadhaarPath(name);
            }
            
            // 2. Army ID Upload
            if (!armyId.isEmpty()) {
                String name = prefix + "armyId_" + sanitize(armyId.getOriginalFilename());
                armyId.transferTo(new File(uploadDir + name));
                doc.setArmyIdPath(name);
            }
            
            // 3. Certificate Upload
            if (!certificate.isEmpty()) {
                String name = prefix + "cert_" + sanitize(certificate.getOriginalFilename());
                certificate.transferTo(new File(uploadDir + name));
                doc.setCertificatePath(name);
            }
            
            // 4. Family Proof Upload
            if (!familyProof.isEmpty()) {
                String name = prefix + "fproof_" + sanitize(familyProof.getOriginalFilename());
                familyProof.transferTo(new File(uploadDir + name));
                doc.setFamilyProofPath(name);
            }
            
            // 5. Shaheed Photo Upload
            if (!photo.isEmpty()) {
                String name = prefix + "photo_" + sanitize(photo.getOriginalFilename());
                photo.transferTo(new File(uploadDir + name));
                doc.setShaheedPhotoPath(name);
            }

            // Database  save 
            Document savedDoc = documentService.save(doc);

            // Application check aur create karna
            if (applicationService.findByFamilyUserId(user.getId()) == null) {
                Application app = new Application();
                app.setFamilyUserId(user.getId()); 
                app.setShaheedId(shaheedId);
                app.setDocumentId(savedDoc.getId()); 
                app.setVerificationStatus("PENDING");
                applicationService.save(app);
            }
            
            return "redirect:/dashboard?uploaded=true";

        } catch (Exception e) {
            model.addAttribute("error", "Upload failed: " + e.getMessage());
            // Existing data wapis bhej rahe hain taaki view khali na dikhe
            try {
                model.addAttribute("existingDoc", documentService.findByFamilyId(user.getId()));
            } catch (Exception ignored) {}
            return "upload-document";
        }
    }

    private String sanitize(String filename) {
        if (filename == null) return "file";
        // Filename se spaces aur special characters hatane ke liye
        return filename.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}