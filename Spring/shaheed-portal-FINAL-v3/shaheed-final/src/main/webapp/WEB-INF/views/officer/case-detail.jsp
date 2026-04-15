<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <title>Case Detail | Officer</title>
    <style>
        * { margin:0; padding:0; box-sizing:border-box; }
        body { font-family:'Segoe UI',Arial,sans-serif; background:#f0f2f5; }
        .topbar {
            background:linear-gradient(135deg,#1b5e20,#2e7d32); color:white;
            padding:14px 25px; display:flex; align-items:center; justify-content:space-between;
        }
        .topbar h1 { font-size:17px; }
        .topbar a { color:rgba(255,255,255,0.85); text-decoration:none; font-size:13px; }
        .saffron-line { height:4px; background:linear-gradient(to right,#FF9933,#FFFFFF,#138808); }
        .content { max-width:1000px; margin:25px auto; padding:0 20px; }
        .card { background:white; border-radius:10px; padding:22px; box-shadow:0 2px 10px rgba(0,0,0,0.07); margin-bottom:20px; }
        .card-title { font-size:15px; font-weight:700; color:#1b5e20; margin-bottom:16px; padding-bottom:8px; border-bottom:2px solid #e8f5e9; }
        .info-grid { display:grid; grid-template-columns:repeat(3,1fr); gap:14px; }
        .info-item label { font-size:11px; font-weight:600; color:#888; text-transform:uppercase; letter-spacing:0.5px; display:block; margin-bottom:3px; }
        .info-item span { font-size:14px; color:#222; font-weight:500; }
        .status-badge { padding:4px 12px; border-radius:12px; font-size:12px; font-weight:600; display:inline-block; }
        .status-PENDING            { background:#fff3e0; color:#e65100; }
        .status-UNDER_VERIFICATION { background:#e3f2fd; color:#1565c0; }
        .status-VERIFIED           { background:#e8f5e9; color:#2e7d32; }
        .status-COMPLETED          { background:#e8f5e9; color:#1b5e20; }
        .status-REJECTED           { background:#ffebee; color:#c62828; }
        table { width:100%; border-collapse:collapse; font-size:13px; }
        th { background:#1b5e20; color:white; padding:9px 12px; text-align:left; font-size:11px; }
        td { padding:9px 12px; border-bottom:1px solid #f0f0f0; }
        tr:hover td { background:#f1f8e9; }
        .btn { padding:6px 14px; border-radius:6px; font-size:12px; font-weight:600; cursor:pointer; border:none; text-decoration:none; display:inline-block; }
        .btn-verify  { background:#2e7d32; color:white; }
        .btn-reject  { background:#c62828; color:white; }
        .btn-resubmit{ background:#e65100; color:white; }
        .btn-save    { background:#1b5e20; color:white; padding:10px 24px; font-size:14px; }
        .btn-back    { background:#f5f5f5; color:#555; border:2px solid #ddd; padding:10px 20px; font-size:13px; }
        .form-group { margin-bottom:14px; }
        .form-group label { font-size:12px; font-weight:600; color:#444; display:block; margin-bottom:5px; }
        .form-group select,
        .form-group textarea,
        .form-group input {
            width:100%; padding:9px 11px; border:2px solid #e0e0e0;
            border-radius:7px; font-size:13px; outline:none; font-family:inherit;
        }
        .form-group select:focus,
        .form-group textarea:focus { border-color:#1b5e20; }
        .alert { padding:12px 14px; border-radius:8px; margin-bottom:16px; font-size:13px; }
        .alert-success { background:#e8f5e9; color:#2e7d32; border-left:4px solid #2e7d32; }
        .alert-danger  { background:#ffebee; color:#c62828; border-left:4px solid #c62828; }
        .doc-VERIFIED  { background:#e8f5e9; }
        .doc-REJECTED  { background:#ffebee; }
        .doc-PENDING   { background:#fff8e1; }
        .form-row { display:grid; grid-template-columns:1fr 1fr; gap:14px; }
        .remark-box { background:#fffde7; border:1px solid #f9a825; padding:10px 14px; border-radius:6px; font-size:12px; color:#555; margin-top:6px; }
        .award-chip { display:inline-block; background:#e8eaf6; color:#1a237e; padding:3px 10px; border-radius:12px; font-size:12px; font-weight:600; margin:2px; }
    </style>
</head>
<body>

<div class="topbar">
    <h1>📁 Case Detail — ${soldier.fullName}</h1>
    <div style="display:flex;gap:15px;">
        <a href="${pageContext.request.contextPath}/officer/cases">← Sab Cases</a>
        <a href="${pageContext.request.contextPath}/officer/dashboard">Dashboard</a>
    </div>
</div>
<div class="saffron-line"></div>

<div class="content">

    <c:if test="${not empty successMsg}"><div class="alert alert-success">✔ ${successMsg}</div></c:if>
    <c:if test="${not empty errorMsg}"><div class="alert alert-danger">⚠ ${errorMsg}</div></c:if>

    <!-- Soldier Info -->
    <div class="card">
        <div class="card-title">🎖️ Shaheed Ki Jankari</div>
        <div class="info-grid">
            <div class="info-item">
                <label>Application ID</label>
                <span style="color:#1b5e20;font-weight:700;">${soldier.applicationId}</span>
            </div>
            <div class="info-item">
                <label>Full Name</label>
                <span>${soldier.fullName}</span>
            </div>
            <div class="info-item">
                <label>Rank</label>
                <span>${soldier.rank}</span>
            </div>
            <div class="info-item">
                <label>Unit</label>
                <span>${soldier.unit}</span>
            </div>
            <div class="info-item">
                <label>Regiment</label>
                <span>${soldier.regiment != null ? soldier.regiment : '—'}</span>
            </div>
            <div class="info-item">
                <label>Service Number</label>
                <span>${soldier.serviceNumber}</span>
            </div>
            <div class="info-item">
                <label>Date of Martyrdom</label>
                <span>${soldier.dateOfMartydom}</span>
            </div>
            <div class="info-item">
                <label>Place of Martyrdom</label>
                <span>${soldier.placeOfMartydom != null ? soldier.placeOfMartydom : '—'}</span>
            </div>
            <div class="info-item">
                <label>Case Status</label>
                <span class="status-badge status-${soldier.caseStatus}">${soldier.caseStatus}</span>
            </div>
        </div>
        <c:if test="${not empty soldier.awards}">
            <div style="margin-top:14px;">
                <label style="font-size:11px;font-weight:600;color:#888;text-transform:uppercase;">Awards / Medals</label>
                <div style="margin-top:6px;">
                    <c:forTokens var="award" items="${soldier.awards}" delims=",">
                        <span class="award-chip">🏅 ${award.trim()}</span>
                    </c:forTokens>
                </div>
            </div>
        </c:if>
    </div>

    <!-- Update Status -->
    <div class="card">
        <div class="card-title">🔄 Case Status Update Karein</div>
        <form action="${pageContext.request.contextPath}/officer/case/${soldier.soldierId}/update-status" method="POST">
            <div class="form-row">
                <div class="form-group">
                    <label>Naya Status</label>
                    <select name="status" required>
                        <c:forEach var="st" items="${statuses}">
                            <option value="${st}" ${soldier.caseStatus == st ? 'selected' : ''}>${st}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Aapke Remarks (optional)</label>
                    <input type="text" name="remarks" placeholder="Verification remarks..." value="${soldier.officerRemarks}">
                </div>
            </div>
            <button type="submit" class="btn btn-save">Update Status</button>
        </form>
    </div>

    <!-- Documents -->
    <div class="card">
        <div class="card-title">📄 Documents (${documents.size()} uploaded)</div>
        <c:choose>
            <c:when test="${empty documents}">
                <p style="text-align:center;color:#999;padding:20px;">Abhi koi document upload nahi hua.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Document Name</th>
                        <th>Type</th>
                        <th>File Type</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="doc" items="${documents}" varStatus="loop">
                        <tr class="doc-${doc.verificationStatus}">
                            <td>${loop.index + 1}</td>
                            <td><strong>${doc.docName}</strong></td>
                            <td>${doc.docType}</td>
                            <td>${doc.fileType}</td>
                            <td><span class="status-badge status-${doc.verificationStatus == 'VERIFIED' ? 'VERIFIED' : doc.verificationStatus == 'REJECTED' ? 'REJECTED' : 'PENDING'}">${doc.verificationStatus}</span></td>
                            <td>
                                <c:if test="${doc.verificationStatus == 'PENDING'}">
                                    <form action="${pageContext.request.contextPath}/officer/document/verify" method="POST" style="display:inline;">
                                        <input type="hidden" name="docId"       value="${doc.docId}">
                                        <input type="hidden" name="soldierId"   value="${soldier.soldierId}">
                                        <input type="hidden" name="verificationStatus" value="VERIFIED">
                                        <input type="hidden" name="remarks"     value="Document verified by officer">
                                        <button type="submit" class="btn btn-verify">✔ Verify</button>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/officer/document/verify" method="POST" style="display:inline;margin-left:5px;">
                                        <input type="hidden" name="docId"       value="${doc.docId}">
                                        <input type="hidden" name="soldierId"   value="${soldier.soldierId}">
                                        <input type="hidden" name="verificationStatus" value="REJECTED">
                                        <input type="hidden" name="remarks"     value="Document rejected">
                                        <button type="submit" class="btn btn-reject">✗ Reject</button>
                                    </form>
                                </c:if>
                                <c:if test="${doc.verificationStatus != 'PENDING'}">
                                    <span style="font-size:12px;color:#999;">Already ${doc.verificationStatus}</span>
                                </c:if>
                            </td>
                        </tr>
                        <c:if test="${not empty doc.verificationRemarks}">
                            <tr>
                                <td colspan="6">
                                    <div class="remark-box">📝 Remarks: ${doc.verificationRemarks}</div>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Benefits Summary -->
    <div class="card">
        <div class="card-title">💰 Benefits Summary (${benefits.size()} benefits assigned)</div>
        <c:choose>
            <c:when test="${empty benefits}">
                <p style="text-align:center;color:#999;padding:16px;">Abhi koi benefit assign nahi hua.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr><th>Benefit Name</th><th>Type</th><th>Amount</th><th>Status</th></tr>
                    </thead>
                    <tbody>
                    <c:forEach var="b" items="${benefits}">
                        <tr>
                            <td>${b.benefitName}</td>
                            <td>${b.benefitType}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${b.amount != null}">
                                        ₹${b.amount} <small style="color:#888;">${b.amountUnit}</small>
                                    </c:when>
                                    <c:otherwise>—</c:otherwise>
                                </c:choose>
                            </td>
                            <td><span class="status-badge status-${b.status == 'PAID' ? 'VERIFIED' : b.status == 'REJECTED' ? 'REJECTED' : 'PENDING'}">${b.status}</span></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <div style="text-align:right;">
        <a href="${pageContext.request.contextPath}/officer/cases" class="btn btn-back">← Wapas Cases</a>
    </div>

</div>
</body>
</html>
