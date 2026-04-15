<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <title>Officer Dashboard | Shaheed Parivar Sahayata Portal</title>
    <style>
        * { margin:0; padding:0; box-sizing:border-box; }
        body { font-family:'Segoe UI',Arial,sans-serif; background:#f0f2f5; display:flex; min-height:100vh; }
        .sidebar {
            width:230px; min-height:100vh; position:fixed; left:0; top:0; bottom:0; z-index:100;
            background:linear-gradient(180deg,#1b5e20 0%,#2e7d32 100%); color:white;
        }
        .sidebar-brand {
            padding:20px 15px; background:rgba(0,0,0,0.2);
            text-align:center; border-bottom:1px solid rgba(255,255,255,0.1);
        }
        .sidebar-brand .icon { font-size:28px; }
        .sidebar-brand h3 { font-size:13px; margin-top:6px; line-height:1.4; }
        .sidebar-brand small { font-size:10px; opacity:0.6; }
        .nav-section {
            padding:14px 0 4px 14px; font-size:10px; font-weight:700;
            letter-spacing:1px; text-transform:uppercase; opacity:0.6;
        }
        .sidebar a {
            display:flex; align-items:center; gap:10px;
            padding:11px 14px; color:rgba(255,255,255,0.85);
            text-decoration:none; font-size:13px; transition:background 0.2s;
        }
        .sidebar a:hover, .sidebar a.active {
            background:rgba(255,255,255,0.15); color:white;
            border-left:3px solid #FF9933;
        }
        .sidebar a .icon { font-size:16px; width:20px; text-align:center; }
        .badge { margin-left:auto; background:#e53935; color:white; font-size:10px; font-weight:700; padding:2px 7px; border-radius:10px; }
        .main { margin-left:230px; flex:1; }
        .topbar {
            background:white; padding:14px 25px;
            display:flex; align-items:center; justify-content:space-between;
            box-shadow:0 2px 8px rgba(0,0,0,0.08); position:sticky; top:0; z-index:50;
        }
        .topbar h1 { font-size:18px; color:#1b5e20; font-weight:700; }
        .user-info { font-size:13px; color:#555; }
        .user-info strong { color:#1b5e20; }
        .btn-logout { padding:7px 15px; background:#e53935; color:white; border:none; border-radius:6px; font-size:12px; cursor:pointer; text-decoration:none; font-weight:600; }
        .saffron-line { height:3px; background:linear-gradient(to right,#FF9933,#FFFFFF,#138808); }
        .content { padding:25px; }
        .stats-grid { display:grid; grid-template-columns:repeat(4,1fr); gap:16px; margin-bottom:25px; }
        .stat-card {
            background:white; border-radius:10px; padding:20px;
            box-shadow:0 2px 8px rgba(0,0,0,0.06); border-left:4px solid transparent;
        }
        .stat-card.green  { border-left-color:#2e7d32; }
        .stat-card.orange { border-left-color:#e65100; }
        .stat-card.blue   { border-left-color:#1565c0; }
        .stat-card.red    { border-left-color:#c62828; }
        .stat-num { font-size:30px; font-weight:800; color:#1b5e20; }
        .stat-icon { font-size:26px; float:right; }
        .stat-label { font-size:12px; color:#777; margin-top:6px; font-weight:500; }
        .section-title {
            font-size:15px; font-weight:700; color:#1b5e20;
            margin-bottom:14px; padding-bottom:8px; border-bottom:2px solid #e8f5e9;
        }
        .table-card { background:white; border-radius:10px; padding:20px; box-shadow:0 2px 8px rgba(0,0,0,0.06); margin-bottom:20px; }
        table { width:100%; border-collapse:collapse; }
        th { background:#1b5e20; color:white; padding:10px 14px; text-align:left; font-size:12px; }
        td { padding:10px 14px; font-size:13px; border-bottom:1px solid #f0f0f0; }
        tr:hover td { background:#f1f8e9; }
        .status-badge { padding:3px 10px; border-radius:12px; font-size:11px; font-weight:600; }
        .status-PENDING           { background:#fff3e0; color:#e65100; }
        .status-UNDER_VERIFICATION{ background:#e3f2fd; color:#1565c0; }
        .status-VERIFIED          { background:#e8f5e9; color:#2e7d32; }
        .status-COMPLETED         { background:#e8f5e9; color:#1b5e20; }
        .status-REJECTED          { background:#ffebee; color:#c62828; }
        .btn-sm { padding:5px 12px; border-radius:5px; font-size:11px; font-weight:600; cursor:pointer; text-decoration:none; display:inline-block; border:none; }
        .btn-primary { background:#1b5e20; color:white; }
        .btn-warning { background:#e65100; color:white; }
        .alert { padding:12px 14px; border-radius:8px; margin-bottom:16px; font-size:13px; }
        .alert-success { background:#e8f5e9; color:#2e7d32; border-left:4px solid #2e7d32; }
        .alert-danger  { background:#ffebee; color:#c62828; border-left:4px solid #c62828; }
        .quick-actions { display:flex; gap:12px; margin-bottom:25px; flex-wrap:wrap; }
        .quick-btn { padding:10px 18px; border-radius:8px; font-size:13px; font-weight:600; text-decoration:none; display:inline-flex; align-items:center; gap:7px; }
        .qb-green  { background:#1b5e20; color:white; }
        .qb-orange { background:#e65100; color:white; }
        .qb-blue   { background:#1565c0; color:white; }
    </style>
</head>
<body>

<div class="sidebar">
    <div class="sidebar-brand">
        <div class="icon">🪖</div>
        <h3>Shaheed Parivar<br>Sahayata Portal</h3>
        <small>OFFICER PANEL</small>
    </div>
    <div class="nav-section">Main</div>
    <a href="${pageContext.request.contextPath}/officer/dashboard" class="active">
        <span class="icon">📊</span> Dashboard
    </a>
    <div class="nav-section">Cases</div>
    <a href="${pageContext.request.contextPath}/officer/cases">
        <span class="icon">📁</span> Meri Cases
        <span class="badge">${totalAssigned}</span>
    </a>
    <div class="nav-section">Documents</div>
    <a href="${pageContext.request.contextPath}/officer/documents/pending">
        <span class="icon">📄</span> Pending Documents
        <c:if test="${pendingDocs > 0}">
            <span class="badge">${pendingDocs}</span>
        </c:if>
    </a>
    <div class="nav-section">Support</div>
    <a href="${pageContext.request.contextPath}/officer/grievances">
        <span class="icon">📨</span> Grievances
        <c:if test="${openGrievances > 0}">
            <span class="badge">${openGrievances}</span>
        </c:if>
    </a>
    <a href="${pageContext.request.contextPath}/logout">
        <span class="icon">🚪</span> Logout
    </a>
</div>

<div class="main">
    <div class="topbar">
        <h1>🪖 Officer Dashboard</h1>
        <div style="display:flex;align-items:center;gap:15px;">
            <div class="user-info">Namaste, <strong>${userName}</strong> &nbsp;|&nbsp; <span style="color:#1b5e20">👮 OFFICER</span></div>
            <a href="${pageContext.request.contextPath}/logout" class="btn-logout">Logout</a>
        </div>
    </div>
    <div class="saffron-line"></div>
    <div class="content">

        <c:if test="${not empty successMsg}"><div class="alert alert-success">✔ ${successMsg}</div></c:if>
        <c:if test="${not empty errorMsg}"><div class="alert alert-danger">⚠ ${errorMsg}</div></c:if>

        <div class="quick-actions">
            <a href="${pageContext.request.contextPath}/officer/cases"              class="quick-btn qb-green">📁 Meri Cases (${totalAssigned})</a>
            <a href="${pageContext.request.contextPath}/officer/documents/pending"  class="quick-btn qb-orange">📄 Pending Docs (${pendingDocs})</a>
            <a href="${pageContext.request.contextPath}/officer/grievances"         class="quick-btn qb-blue">📨 Grievances (${openGrievances})</a>
        </div>

        <div class="stats-grid">
            <div class="stat-card green">
                <span class="stat-icon">📁</span>
                <div class="stat-num">${totalAssigned}</div>
                <div class="stat-label">Mujhe Assign Cases</div>
            </div>
            <div class="stat-card orange">
                <span class="stat-icon">⏳</span>
                <div class="stat-num">${pendingVerification}</div>
                <div class="stat-label">Verification Pending</div>
            </div>
            <div class="stat-card green">
                <span class="stat-icon">✅</span>
                <div class="stat-num">${verifiedCases}</div>
                <div class="stat-label">Verified Cases</div>
            </div>
            <div class="stat-card red">
                <span class="stat-icon">📄</span>
                <div class="stat-num">${pendingDocs}</div>
                <div class="stat-label">Documents Pending</div>
            </div>
        </div>

        <!-- Recent Cases -->
        <div class="table-card">
            <div class="section-title">📋 Recent Assigned Cases</div>
            <c:choose>
                <c:when test="${empty recentCases}">
                    <p style="text-align:center;color:#999;padding:20px;">Abhi koi case assign nahi hai.</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                        <tr>
                            <th>Application ID</th>
                            <th>Naam</th>
                            <th>Rank</th>
                            <th>Unit</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="s" items="${recentCases}">
                            <tr>
                                <td><strong>${s.applicationId}</strong></td>
                                <td>${s.fullName}</td>
                                <td>${s.rank}</td>
                                <td>${s.unit}</td>
                                <td><span class="status-badge status-${s.caseStatus}">${s.caseStatus}</span></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/officer/case/${s.soldierId}" class="btn-sm btn-primary">View</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>

</body>
</html>
