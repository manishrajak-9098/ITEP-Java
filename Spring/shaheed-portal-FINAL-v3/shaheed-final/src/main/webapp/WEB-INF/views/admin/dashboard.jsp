<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard | Shaheed Parivar Sahayata Portal</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Arial, sans-serif; background: #f0f2f5; display: flex; min-height: 100vh; }

        /* ===== SIDEBAR ===== */
        .sidebar {
            width: 240px; min-height: 100vh;
            background: linear-gradient(180deg, #1a237e 0%, #283593 100%);
            color: white; padding: 0; position: fixed; left: 0; top: 0; bottom: 0; z-index: 100;
        }
        .sidebar-brand {
            padding: 20px 15px;
            background: rgba(0,0,0,0.2);
            text-align: center;
            border-bottom: 1px solid rgba(255,255,255,0.1);
        }
        .sidebar-brand .flag { font-size: 28px; }
        .sidebar-brand h3 { font-size: 13px; margin-top: 6px; line-height: 1.4; }
        .nav-section {
            padding: 15px 0 5px 15px;
            font-size: 10px; font-weight: 700; letter-spacing: 1px;
            text-transform: uppercase; opacity: 0.6;
        }
        .sidebar a {
            display: flex; align-items: center; gap: 10px;
            padding: 11px 15px; color: rgba(255,255,255,0.85);
            text-decoration: none; font-size: 13px; transition: background 0.2s;
        }
        .sidebar a:hover, .sidebar a.active {
            background: rgba(255,255,255,0.15); color: white;
            border-left: 3px solid #FF9933;
        }
        .sidebar a .icon { font-size: 16px; width: 20px; text-align: center; }
        .badge-count {
            margin-left: auto; background: #e53935;
            color: white; font-size: 10px; font-weight: 700;
            padding: 2px 7px; border-radius: 10px;
        }

        /* ===== MAIN CONTENT ===== */
        .main { margin-left: 240px; padding: 0; flex: 1; }

        /* TOP BAR */
        .topbar {
            background: white; padding: 14px 25px;
            display: flex; align-items: center; justify-content: space-between;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08); position: sticky; top: 0; z-index: 50;
        }
        .topbar h1 { font-size: 18px; color: #1a237e; font-weight: 700; }
        .topbar-right { display: flex; align-items: center; gap: 15px; }
        .user-info { font-size: 13px; color: #555; }
        .user-info strong { color: #1a237e; }
        .btn-logout {
            padding: 7px 15px; background: #e53935; color: white;
            border: none; border-radius: 6px; font-size: 12px;
            cursor: pointer; text-decoration: none; font-weight: 600;
        }
        .saffron-line { height: 3px; background: linear-gradient(to right, #FF9933, #FFFFFF, #138808); }

        /* CONTENT AREA */
        .content { padding: 25px; }

        /* STATS CARDS */
        .stats-grid {
            display: grid; grid-template-columns: repeat(4, 1fr); gap: 16px; margin-bottom: 25px;
        }
        .stat-card {
            background: white; border-radius: 10px;
            padding: 20px; box-shadow: 0 2px 8px rgba(0,0,0,0.06);
            border-left: 4px solid transparent; transition: transform 0.2s;
        }
        .stat-card:hover { transform: translateY(-2px); }
        .stat-card.blue   { border-left-color: #1565c0; }
        .stat-card.orange { border-left-color: #e65100; }
        .stat-card.green  { border-left-color: #2e7d32; }
        .stat-card.red    { border-left-color: #c62828; }
        .stat-card.purple { border-left-color: #6a1b9a; }
        .stat-card.teal   { border-left-color: #00695c; }
        .stat-num {
            font-size: 32px; font-weight: 800; color: #1a237e; line-height: 1;
        }
        .stat-label { font-size: 12px; color: #777; margin-top: 6px; font-weight: 500; }
        .stat-icon { font-size: 28px; float: right; margin-top: -5px; }

        /* SECTION TITLE */
        .section-title {
            font-size: 16px; font-weight: 700; color: #1a237e;
            margin-bottom: 15px; padding-bottom: 8px;
            border-bottom: 2px solid #e8eaf6;
        }

        /* TABLE */
        .table-card {
            background: white; border-radius: 10px;
            padding: 20px; box-shadow: 0 2px 8px rgba(0,0,0,0.06); margin-bottom: 20px;
        }
        table { width: 100%; border-collapse: collapse; }
        th {
            background: #1a237e; color: white; padding: 10px 14px;
            text-align: left; font-size: 12px; font-weight: 600; letter-spacing: 0.4px;
        }
        td { padding: 10px 14px; font-size: 13px; border-bottom: 1px solid #f0f0f0; }
        tr:hover td { background: #f5f6ff; }
        .status-badge {
            padding: 3px 10px; border-radius: 12px; font-size: 11px; font-weight: 600;
        }
        .status-PENDING          { background:#fff3e0; color:#e65100; }
        .status-UNDER_VERIFICATION{ background:#e3f2fd; color:#1565c0; }
        .status-VERIFIED         { background:#e8f5e9; color:#2e7d32; }
        .status-COMPLETED        { background:#e8f5e9; color:#1b5e20; }
        .status-REJECTED         { background:#ffebee; color:#c62828; }
        .btn-sm {
            padding: 5px 12px; border-radius: 5px; font-size: 11px;
            font-weight: 600; cursor: pointer; text-decoration: none; display: inline-block;
        }
        .btn-primary { background: #1a237e; color: white; border: none; }
        .btn-success { background: #2e7d32; color: white; border: none; }

        /* ALERTS */
        .alert { padding: 12px 16px; border-radius: 8px; margin-bottom: 16px; font-size: 13px; }
        .alert-success { background: #e8f5e9; color: #2e7d32; border-left: 4px solid #2e7d32; }
        .alert-danger  { background: #ffebee; color: #c62828; border-left: 4px solid #c62828; }

        /* QUICK ACTIONS */
        .quick-actions { display: flex; gap: 12px; margin-bottom: 25px; flex-wrap: wrap; }
        .quick-btn {
            padding: 10px 20px; border-radius: 8px; font-size: 13px; font-weight: 600;
            text-decoration: none; display: inline-flex; align-items: center; gap: 7px;
            transition: opacity 0.2s;
        }
        .quick-btn:hover { opacity: 0.85; }
        .qb-blue   { background: #1a237e; color: white; }
        .qb-green  { background: #2e7d32; color: white; }
        .qb-orange { background: #e65100; color: white; }
        .qb-red    { background: #c62828; color: white; }

        @media(max-width:768px){
            .sidebar { display: none; }
            .main { margin-left: 0; }
            .stats-grid { grid-template-columns: repeat(2,1fr); }
        }
    </style>
</head>
<body>

<!-- ===== SIDEBAR ===== -->
<div class="sidebar">
    <div class="sidebar-brand">
        <div class="flag">🇮🇳</div>
        <h3>Shaheed Parivar<br>Sahayata Portal</h3>
        <small style="opacity:0.6;font-size:10px;">ADMIN PANEL</small>
    </div>

    <div class="nav-section">Main Menu</div>
    <a href="${pageContext.request.contextPath}/admin/dashboard" class="active">
        <span class="icon">📊</span> Dashboard
    </a>

    <div class="nav-section">Soldier Management</div>
    <a href="${pageContext.request.contextPath}/admin/soldiers">
        <span class="icon">🎖️</span> All Soldiers
        <span class="badge-count">${totalSoldiers}</span>
    </a>
    <a href="${pageContext.request.contextPath}/admin/soldier/add">
        <span class="icon">➕</span> Register Shaheed
    </a>

    <div class="nav-section">Team</div>
    <a href="${pageContext.request.contextPath}/admin/officers">
        <span class="icon">👮</span> Officers
        <span class="badge-count">${totalOfficers}</span>
    </a>
    <a href="${pageContext.request.contextPath}/admin/officer/create">
        <span class="icon">➕</span> Create Officer
    </a>

    <div class="nav-section">Benefits & Docs</div>
    <a href="${pageContext.request.contextPath}/admin/benefits">
        <span class="icon">💰</span> Benefits
        <c:if test="${pendingBenefits > 0}">
            <span class="badge-count">${pendingBenefits}</span>
        </c:if>
    </a>
    <a href="${pageContext.request.contextPath}/admin/benefit/add">
        <span class="icon">➕</span> Add Benefit
    </a>

    <div class="nav-section">Support</div>
    <a href="${pageContext.request.contextPath}/admin/grievances">
        <span class="icon">📨</span> Grievances
        <c:if test="${openGrievances > 0}">
            <span class="badge-count">${openGrievances}</span>
        </c:if>
    </a>
    <a href="${pageContext.request.contextPath}/admin/reports">
        <span class="icon">📋</span> Reports
    </a>
    <a href="${pageContext.request.contextPath}/logout">
        <span class="icon">🚪</span> Logout
    </a>
</div>

<!-- ===== MAIN CONTENT ===== -->
<div class="main">
    <div class="topbar">
        <h1>📊 Admin Dashboard</h1>
        <div class="topbar-right">
            <div class="user-info">
                Namaste, <strong>${userName}</strong> &nbsp;|&nbsp;
                <span style="color:#1b5e20">⚙️ ADMIN</span>
            </div>
            <a href="${pageContext.request.contextPath}/logout" class="btn-logout">Logout</a>
        </div>
    </div>
    <div class="saffron-line"></div>

    <div class="content">

        <%-- Flash messages --%>
        <c:if test="${not empty successMsg}">
            <div class="alert alert-success">✔ ${successMsg}</div>
        </c:if>
        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger">⚠ ${errorMsg}</div>
        </c:if>

        <!-- Quick Action Buttons -->
        <div class="quick-actions">
            <a href="${pageContext.request.contextPath}/admin/soldier/add"   class="quick-btn qb-blue">
                ➕ Shaheed Register
            </a>
            <a href="${pageContext.request.contextPath}/admin/officer/create" class="quick-btn qb-green">
                👮 Officer Create
            </a>
            <a href="${pageContext.request.contextPath}/admin/benefit/add"   class="quick-btn qb-orange">
                💰 Benefit Add
            </a>
            <a href="${pageContext.request.contextPath}/admin/grievances"    class="quick-btn qb-red">
                📨 Grievances (${openGrievances})
            </a>
        </div>

        <!-- ===== STATS CARDS ===== -->
        <div class="stats-grid">
            <div class="stat-card blue">
                <span class="stat-icon">🎖️</span>
                <div class="stat-num">${totalSoldiers}</div>
                <div class="stat-label">Total Shaheed Records</div>
            </div>
            <div class="stat-card orange">
                <span class="stat-icon">⏳</span>
                <div class="stat-num">${pendingCases}</div>
                <div class="stat-label">Pending Cases</div>
            </div>
            <div class="stat-card green">
                <span class="stat-icon">✅</span>
                <div class="stat-num">${verifiedCases}</div>
                <div class="stat-label">Verified Cases</div>
            </div>
            <div class="stat-card teal">
                <span class="stat-icon">🏆</span>
                <div class="stat-num">${completedCases}</div>
                <div class="stat-label">Completed Cases</div>
            </div>
            <div class="stat-card blue">
                <span class="stat-icon">👮</span>
                <div class="stat-num">${totalOfficers}</div>
                <div class="stat-label">Active Officers</div>
            </div>
            <div class="stat-card purple">
                <span class="stat-icon">👨‍👩‍👦</span>
                <div class="stat-num">${totalFamilies}</div>
                <div class="stat-label">Registered Families</div>
            </div>
            <div class="stat-card orange">
                <span class="stat-icon">💰</span>
                <div class="stat-num">${pendingBenefits}</div>
                <div class="stat-label">Benefits Pending</div>
            </div>
            <div class="stat-card green">
                <span class="stat-icon">💳</span>
                <div class="stat-num">${paidBenefits}</div>
                <div class="stat-label">Benefits Paid</div>
            </div>
        </div>

        <!-- ===== MORE STATS ===== -->
        <div style="display:grid;grid-template-columns:repeat(2,1fr);gap:16px;margin-bottom:25px;">
            <div class="stat-card red">
                <span class="stat-icon">📄</span>
                <div class="stat-num">${pendingDocs}</div>
                <div class="stat-label">Documents Pending Verification</div>
            </div>
            <div class="stat-card red">
                <span class="stat-icon">📨</span>
                <div class="stat-num">${openGrievances}</div>
                <div class="stat-label">Open Grievances</div>
            </div>
        </div>

        <!-- ===== RECENT CASES TABLE ===== -->
        <div class="table-card">
            <div class="section-title">🕐 Recent Pending Cases</div>
            <c:choose>
                <c:when test="${empty recentSoldiers}">
                    <p style="color:#999;text-align:center;padding:20px;">
                        Koi pending case nahi hai abhi.
                    </p>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                        <tr>
                            <th>Application ID</th>
                            <th>Shaheed Ka Naam</th>
                            <th>Rank</th>
                            <th>Unit</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="s" items="${recentSoldiers}">
                            <tr>
                                <td><strong>${s.applicationId}</strong></td>
                                <td>${s.fullName}</td>
                                <td>${s.rank}</td>
                                <td>${s.unit}</td>
                                <td>
                                    <span class="status-badge status-${s.caseStatus}">${s.caseStatus}</span>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/soldier/${s.soldierId}"
                                       class="btn-sm btn-primary">View</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div style="text-align:right;margin-top:12px;">
                        <a href="${pageContext.request.contextPath}/admin/soldiers"
                           style="font-size:12px;color:#1a237e;text-decoration:none;font-weight:600;">
                            All Soldiers dekho →
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>

</body>
</html>
