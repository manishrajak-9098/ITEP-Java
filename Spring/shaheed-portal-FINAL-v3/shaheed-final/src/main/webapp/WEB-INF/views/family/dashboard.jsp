<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <title>Parivar Dashboard | Shaheed Parivar Sahayata Portal</title>
    <style>
        * { margin:0; padding:0; box-sizing:border-box; }
        body { font-family:'Segoe UI',Arial,sans-serif; background:#f5f5f5; }
        .header {
            background:linear-gradient(135deg,#1a237e 0%,#283593 60%,#1b5e20 100%);
            color:white; padding:0;
        }
        .header-top {
            padding:12px 25px; display:flex; align-items:center; justify-content:space-between;
        }
        .header-brand { display:flex; align-items:center; gap:12px; }
        .header-brand .flag { font-size:32px; }
        .header-brand h1 { font-size:16px; line-height:1.4; }
        .header-brand p  { font-size:11px; opacity:0.75; }
        .header-nav { display:flex; gap:6px; align-items:center; }
        .header-nav a {
            padding:7px 14px; color:rgba(255,255,255,0.85); text-decoration:none;
            font-size:12px; font-weight:600; border-radius:6px; transition:background 0.2s;
        }
        .header-nav a:hover { background:rgba(255,255,255,0.15); color:white; }
        .header-nav a.active { background:rgba(255,255,255,0.2); color:white; }
        .btn-logout-sm { background:#e53935!important; color:white!important; }
        .saffron-line { height:4px; background:linear-gradient(to right,#FF9933,#FFFFFF,#138808); }
        .welcome-bar {
            background:rgba(0,0,0,0.15); padding:10px 25px;
            font-size:13px; display:flex; align-items:center; gap:8px;
        }
        .content { max-width:900px; margin:25px auto; padding:0 20px; }

        /* Welcome card */
        .welcome-card {
            background:linear-gradient(135deg,#1a237e,#283593);
            color:white; border-radius:12px; padding:25px 28px;
            margin-bottom:22px; position:relative; overflow:hidden;
        }
        .welcome-card::after {
            content:'🇮🇳'; position:absolute; right:20px; top:50%;
            transform:translateY(-50%); font-size:60px; opacity:0.15;
        }
        .welcome-card h2 { font-size:20px; font-weight:700; }
        .welcome-card p  { font-size:13px; opacity:0.85; margin-top:6px; }
        .app-id-display {
            display:inline-block; background:rgba(255,255,255,0.2);
            padding:5px 14px; border-radius:20px; font-size:13px; font-weight:700;
            margin-top:10px; letter-spacing:1px;
        }

        /* Quick links */
        .quick-links { display:grid; grid-template-columns:repeat(4,1fr); gap:14px; margin-bottom:22px; }
        .quick-link {
            background:white; border-radius:10px; padding:18px; text-align:center;
            text-decoration:none; box-shadow:0 2px 8px rgba(0,0,0,0.07);
            transition:transform 0.2s; border-bottom:4px solid transparent;
        }
        .quick-link:hover { transform:translateY(-3px); }
        .quick-link .ql-icon { font-size:32px; margin-bottom:8px; display:block; }
        .quick-link .ql-label { font-size:12px; font-weight:600; color:#333; }
        .ql-blue   { border-bottom-color:#1a237e; }
        .ql-green  { border-bottom-color:#2e7d32; }
        .ql-orange { border-bottom-color:#e65100; }
        .ql-red    { border-bottom-color:#c62828; }

        /* Status card */
        .section-title { font-size:15px; font-weight:700; color:#1a237e; margin-bottom:14px; }
        .cards-row { display:grid; grid-template-columns:repeat(3,1fr); gap:14px; margin-bottom:22px; }
        .mini-stat {
            background:white; border-radius:10px; padding:16px 18px;
            box-shadow:0 2px 8px rgba(0,0,0,0.06); border-left:4px solid transparent;
        }
        .mini-stat.orange { border-left-color:#e65100; }
        .mini-stat.green  { border-left-color:#2e7d32; }
        .mini-stat.blue   { border-left-color:#1565c0; }
        .mini-stat .num   { font-size:26px; font-weight:800; color:#1a237e; }
        .mini-stat .lbl   { font-size:12px; color:#777; margin-top:4px; }

        /* Grievance list */
        .grievance-card { background:white; border-radius:10px; padding:20px; box-shadow:0 2px 8px rgba(0,0,0,0.07); margin-bottom:22px; }
        table { width:100%; border-collapse:collapse; font-size:13px; }
        th { background:#1a237e; color:white; padding:9px 12px; text-align:left; font-size:11px; }
        td { padding:9px 12px; border-bottom:1px solid #f0f0f0; }
        tr:hover td { background:#f5f6ff; }
        .gstatus { padding:3px 10px; border-radius:12px; font-size:11px; font-weight:600; }
        .gs-OPEN        { background:#fff3e0; color:#e65100; }
        .gs-IN_PROGRESS { background:#e3f2fd; color:#1565c0; }
        .gs-RESOLVED    { background:#e8f5e9; color:#2e7d32; }
        .gs-CLOSED      { background:#f3e5f5; color:#6a1b9a; }

        .alert { padding:12px 14px; border-radius:8px; margin-bottom:16px; font-size:13px; }
        .alert-success { background:#e8f5e9; color:#2e7d32; border-left:4px solid #2e7d32; }
        .alert-danger  { background:#ffebee; color:#c62828; border-left:4px solid #c62828; }

        /* Helpline */
        .helpline-bar {
            background:#e8f5e9; border:1px solid #a5d6a7; border-radius:8px;
            padding:12px 18px; font-size:13px; color:#1b5e20; margin-bottom:22px;
            display:flex; align-items:center; justify-content:space-between;
        }
        @media(max-width:600px){
            .quick-links { grid-template-columns:repeat(2,1fr); }
            .cards-row   { grid-template-columns:repeat(2,1fr); }
        }
    </style>
</head>
<body>

<div class="header">
    <div class="header-top">
        <div class="header-brand">
            <div class="flag">🇮🇳</div>
            <div>
                <h1>Shaheed Parivar Sahayata Portal</h1>
                <p>Government of India | Ministry of Defence</p>
            </div>
        </div>
        <div class="header-nav">
            <a href="${pageContext.request.contextPath}/family/dashboard"   class="active">🏠 Dashboard</a>
            <a href="${pageContext.request.contextPath}/family/case-status">📋 Case Status</a>
            <a href="${pageContext.request.contextPath}/family/benefits">   💰 Benefits</a>
            <a href="${pageContext.request.contextPath}/family/documents">  📄 Documents</a>
            <a href="${pageContext.request.contextPath}/family/grievances"> 📨 Grievances</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn-logout-sm">Logout</a>
        </div>
    </div>
    <div class="saffron-line"></div>
    <div class="welcome-bar">
        👋 Namaste, <strong>&nbsp;${userName}&nbsp;</strong> | Shaheed Parivar Portal mein aapka swagat hai
    </div>
</div>

<div class="content">

    <c:if test="${not empty successMsg}"><div class="alert alert-success">✔ ${successMsg}</div></c:if>
    <c:if test="${not empty errorMsg}"><div class="alert alert-danger">⚠ ${errorMsg}</div></c:if>

    <!-- Welcome Card -->
    <div class="welcome-card">
        <h2>🙏 Jai Hind</h2>
        <p>Aapke shaheed parivaar ke balidaan ko desh hamesha yaad rakhega.<br>
           Is portal ke zariye aap apne saare government benefits track kar sakte hain.</p>
        <c:if test="${familyUser.linkedFamilyId != null}">
            <div class="app-id-display">Family ID: FAM${String.format('%05d', familyUser.linkedFamilyId)}</div>
        </c:if>
    </div>

    <!-- Helpline -->
    <div class="helpline-bar">
        <span>📞 Koi problem hai? Hamari helpline pe call karein: <strong>1800-XXX-XXXX</strong> (Toll Free, 24x7)</span>
        <a href="${pageContext.request.contextPath}/family/grievance/new"
           style="background:#2e7d32;color:white;padding:7px 16px;border-radius:6px;text-decoration:none;font-size:12px;font-weight:600;">
            ➕ Complaint Darj Karein
        </a>
    </div>

    <!-- Quick Links -->
    <div class="section-title">⚡ Quick Links</div>
    <div class="quick-links">
        <a href="${pageContext.request.contextPath}/family/case-status" class="quick-link ql-blue">
            <span class="ql-icon">🔍</span>
            <div class="ql-label">Case Status Track</div>
        </a>
        <a href="${pageContext.request.contextPath}/family/benefits" class="quick-link ql-green">
            <span class="ql-icon">💰</span>
            <div class="ql-label">Benefits Dekhein</div>
        </a>
        <a href="${pageContext.request.contextPath}/family/documents" class="quick-link ql-orange">
            <span class="ql-icon">📤</span>
            <div class="ql-label">Documents Upload</div>
        </a>
        <a href="${pageContext.request.contextPath}/family/grievance/new" class="quick-link ql-red">
            <span class="ql-icon">📨</span>
            <div class="ql-label">Complaint Darj</div>
        </a>
    </div>

    <!-- Benefits Stats -->
    <div class="section-title">💰 Benefits Ki Sthiti</div>
    <div class="cards-row">
        <div class="mini-stat orange">
            <div class="num">${pendingBenefits}</div>
            <div class="lbl">⏳ Pending Benefits</div>
        </div>
        <div class="mini-stat blue">
            <div class="num">${approvedBenefits}</div>
            <div class="lbl">✅ Approved Benefits</div>
        </div>
        <div class="mini-stat green">
            <div class="num">${paidBenefits}</div>
            <div class="lbl">💳 Paid Benefits</div>
        </div>
    </div>

    <!-- My Grievances -->
    <div class="grievance-card">
        <div style="display:flex;align-items:center;justify-content:space-between;margin-bottom:14px;">
            <div class="section-title" style="margin-bottom:0;">📨 Meri Complaints (${myGrievances.size()})</div>
            <a href="${pageContext.request.contextPath}/family/grievance/new"
               style="background:#1a237e;color:white;padding:6px 14px;border-radius:6px;text-decoration:none;font-size:12px;font-weight:600;">
                ➕ New Complaint
            </a>
        </div>
        <c:choose>
            <c:when test="${empty myGrievances}">
                <p style="text-align:center;color:#aaa;padding:20px;">
                    Abhi koi complaint nahi hai. ✔
                </p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>Grievance No.</th>
                        <th>Subject</th>
                        <th>Category</th>
                        <th>Status</th>
                        <th>Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="g" items="${myGrievances}">
                        <tr>
                            <td><strong style="color:#1a237e;">${g.grievanceNumber}</strong></td>
                            <td>${g.subject}</td>
                            <td>${g.category}</td>
                            <td><span class="gstatus gs-${g.status}">${g.status}</span></td>
                            <td style="font-size:12px;color:#888;">${g.createdAt}</td>
                        </tr>
                        <c:if test="${not empty g.response}">
                            <tr>
                                <td colspan="5" style="background:#e8f5e9;font-size:12px;color:#2e7d32;padding:8px 12px;">
                                    💬 Response: ${g.response}
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Important Info -->
    <div style="background:#fff8e1;border:1px solid #ffd54f;border-radius:8px;padding:14px 18px;font-size:13px;color:#555;margin-bottom:20px;">
        <strong style="color:#e65100;">ℹ️ Zaruri Jaankari:</strong><br>
        • Documents upload karne ke baad officer verification mein 3-7 working days lagte hain.<br>
        • Benefits status regular check karte rahein.<br>
        • Koi problem ho to grievance zaroor darj karein — hum 24 ghante mein jawab denge.
    </div>

</div>

</body>
</html>
