<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <title>Complaint Darj Karein | Shaheed Portal</title>
    <style>
        * { margin:0; padding:0; box-sizing:border-box; }
        body { font-family:'Segoe UI',Arial,sans-serif; background:#f0f2f5; }
        .topbar { background:linear-gradient(135deg,#1a237e,#283593); color:white; padding:14px 25px; display:flex; align-items:center; justify-content:space-between; }
        .topbar h1 { font-size:17px; }
        .topbar a { color:rgba(255,255,255,0.85); text-decoration:none; font-size:13px; }
        .saffron-line { height:4px; background:linear-gradient(to right,#FF9933,#FFFFFF,#138808); }
        .content { max-width:650px; margin:28px auto; padding:0 20px; }
        .form-card { background:white; border-radius:12px; box-shadow:0 4px 16px rgba(0,0,0,0.08); overflow:hidden; }
        .form-header { background:linear-gradient(135deg,#1a237e,#283593); color:white; padding:20px 25px; }
        .form-header h2 { font-size:17px; }
        .form-header p  { font-size:12px; opacity:0.8; margin-top:4px; }
        .form-body { padding:25px; }
        .form-group { margin-bottom:16px; }
        .form-group label { display:block; font-size:12px; font-weight:600; color:#444; margin-bottom:5px; }
        .form-group .req { color:#e53935; }
        .form-group input,
        .form-group select,
        .form-group textarea {
            width:100%; padding:10px 12px; border:2px solid #e0e0e0;
            border-radius:7px; font-size:13px; outline:none; font-family:inherit;
        }
        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus { border-color:#1a237e; }
        .form-group textarea { resize:vertical; min-height:100px; }
        .form-actions { padding:18px 25px; background:#fafafa; border-top:1px solid #f0f0f0; display:flex; gap:12px; }
        .btn-submit { padding:11px 26px; background:linear-gradient(135deg,#1a237e,#283593); color:white; border:none; border-radius:8px; font-size:14px; font-weight:600; cursor:pointer; }
        .btn-cancel { padding:11px 20px; background:#f5f5f5; color:#555; border:2px solid #ddd; border-radius:8px; font-size:13px; font-weight:600; text-decoration:none; display:inline-block; }
        .alert { padding:12px 14px; border-radius:8px; margin-bottom:14px; font-size:13px; }
        .alert-success { background:#e8f5e9; color:#2e7d32; border-left:4px solid #2e7d32; }
        .alert-danger  { background:#ffebee; color:#c62828; border-left:4px solid #c62828; }
        .info-note { background:#fff8e1; border-left:4px solid #ffd54f; padding:10px 14px; border-radius:0 6px 6px 0; font-size:12px; color:#555; margin-bottom:16px; }
    </style>
</head>
<body>
<div class="topbar">
    <h1>📨 Complaint Darj Karein</h1>
    <a href="${pageContext.request.contextPath}/family/dashboard">← Dashboard</a>
</div>
<div class="saffron-line"></div>
<div class="content">
    <c:if test="${not empty successMsg}"><div class="alert alert-success">✔ ${successMsg}</div></c:if>
    <c:if test="${not empty errorMsg}"><div class="alert alert-danger">⚠ ${errorMsg}</div></c:if>

    <div class="form-card">
        <div class="form-header">
            <h2>📨 Nayi Complaint / Grievance</h2>
            <p>Apni samasya clearly batayein — hum 24 ghante mein jawab denge.</p>
        </div>
        <form action="${pageContext.request.contextPath}/family/grievance/file" method="POST">
            <div class="form-body">
                <div class="info-note">
                    ℹ️ Complaint number automatically generate hoga. Usse note karke rakhein future reference ke liye.
                </div>
                <div class="form-group">
                    <label>Application ID / Soldier ID <span class="req">*</span></label>
                    <input type="number" name="soldierId" placeholder="Shaheed ka Soldier ID daalen" required>
                </div>
                <div class="form-group">
                    <label>Complaint Category <span class="req">*</span></label>
                    <select name="category" required>
                        <option value="">-- Category select karein --</option>
                        <c:forEach var="cat" items="${categories}">
                            <option value="${cat}">${cat}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Subject <span class="req">*</span></label>
                    <input type="text" name="subject" placeholder="Complaint ka mukhya vishay likhein" required maxlength="200">
                </div>
                <div class="form-group">
                    <label>Description (Samasya ka Vivaran) <span class="req">*</span></label>
                    <textarea name="description" placeholder="Apni samasya ya sawaal ko clearly explain karein..." required maxlength="1000"></textarea>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn-submit">📨 Complaint Submit Karein</button>
                <a href="${pageContext.request.contextPath}/family/dashboard" class="btn-cancel">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
