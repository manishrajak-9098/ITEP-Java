<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <title>Case Track | Shaheed Parivar Sahayata Portal</title>
    <style>
        * { margin:0; padding:0; box-sizing:border-box; }
        body { font-family:'Segoe UI',Arial,sans-serif; background:linear-gradient(135deg,#1a237e 0%,#283593 60%,#1b5e20 100%); min-height:100vh; padding:30px 20px; }
        .container { max-width:700px; margin:0 auto; }
        .header-card {
            background:rgba(255,255,255,0.1); border-radius:12px;
            padding:22px 26px; color:white; text-align:center; margin-bottom:20px;
        }
        .header-card .flag { font-size:40px; margin-bottom:8px; }
        .header-card h1 { font-size:20px; font-weight:700; }
        .header-card p  { font-size:12px; opacity:0.8; margin-top:4px; }
        .saffron-line { height:3px; background:linear-gradient(to right,#FF9933,#FFFFFF,#138808); border-radius:2px; margin-bottom:20px; }
        .card { background:white; border-radius:12px; padding:25px; box-shadow:0 8px 32px rgba(0,0,0,0.2); }
        .card h2 { font-size:17px; color:#1a237e; font-weight:700; margin-bottom:18px; }
        .search-row { display:flex; gap:10px; }
        .search-row input {
            flex:1; padding:12px 14px; border:2px solid #e0e0e0;
            border-radius:8px; font-size:14px; outline:none;
        }
        .search-row input:focus { border-color:#1a237e; }
        .btn-search {
            padding:12px 24px; background:linear-gradient(135deg,#1a237e,#283593);
            color:white; border:none; border-radius:8px; font-size:14px;
            font-weight:600; cursor:pointer;
        }
        .btn-search:hover { opacity:0.9; }
        .not-found { text-align:center; padding:25px; color:#c62828; }
        .not-found .icon { font-size:40px; margin-bottom:8px; }

        /* Result Card */
        .result-card { margin-top:20px; border:2px solid #e8eaf6; border-radius:10px; overflow:hidden; }
        .result-header {
            background:linear-gradient(135deg,#1a237e,#283593); color:white;
            padding:14px 18px; display:flex; align-items:center; justify-content:space-between;
        }
        .result-header h3 { font-size:16px; }
        .app-id { background:rgba(255,255,255,0.2); padding:4px 12px; border-radius:12px; font-size:12px; font-weight:700; letter-spacing:1px; }
        .result-body { padding:18px; }
        .info-row { display:grid; grid-template-columns:1fr 1fr 1fr; gap:12px; margin-bottom:16px; }
        .info-item label { font-size:10px; font-weight:700; color:#999; text-transform:uppercase; display:block; margin-bottom:3px; }
        .info-item span  { font-size:13px; color:#222; font-weight:500; }

        /* Status pipeline */
        .status-pipeline { margin:18px 0; }
        .status-pipeline h4 { font-size:13px; color:#1a237e; font-weight:700; margin-bottom:12px; }
        .pipeline { display:flex; gap:0; }
        .pip-step {
            flex:1; text-align:center; position:relative;
        }
        .pip-step::after {
            content:''; position:absolute; top:14px; left:50%; right:-50%;
            height:3px; background:#e0e0e0; z-index:0;
        }
        .pip-step:last-child::after { display:none; }
        .pip-dot {
            width:30px; height:30px; border-radius:50%; border:3px solid #e0e0e0;
            background:white; display:flex; align-items:center; justify-content:center;
            margin:0 auto 6px; font-size:13px; position:relative; z-index:1;
        }
        .pip-dot.done     { background:#2e7d32; border-color:#2e7d32; color:white; }
        .pip-dot.active   { background:#1a237e; border-color:#1a237e; color:white; }
        .pip-label { font-size:10px; color:#777; font-weight:600; }
        .pip-label.done   { color:#2e7d32; }
        .pip-label.active { color:#1a237e; font-weight:700; }

        /* Benefits in track */
        .benefit-row {
            display:flex; align-items:center; justify-content:space-between;
            padding:10px 14px; background:#f9f9f9; border-radius:7px; margin-bottom:8px;
        }
        .benefit-name { font-size:13px; color:#333; font-weight:500; }
        .bstatus { padding:3px 10px; border-radius:12px; font-size:11px; font-weight:700; }
        .bs-PENDING       { background:#fff3e0; color:#e65100; }
        .bs-APPROVED      { background:#e3f2fd; color:#1565c0; }
        .bs-UNDER_PROCESS { background:#e3f2fd; color:#1565c0; }
        .bs-PAID          { background:#e8f5e9; color:#2e7d32; }
        .bs-REJECTED      { background:#ffebee; color:#c62828; }
        .bs-ON_HOLD       { background:#f3e5f5; color:#6a1b9a; }

        .back-btn { display:block; text-align:center; margin-top:16px; }
        .back-btn a { color:rgba(255,255,255,0.8); text-decoration:none; font-size:13px; }
        .back-btn a:hover { color:white; }
    </style>
</head>
<body>
<div class="container">
    <div class="header-card">
        <div class="flag">🇮🇳</div>
        <h1>Shaheed Parivar Sahayata Portal</h1>
        <p>Case Status Track Karein | Government of India</p>
    </div>
    <div class="saffron-line"></div>

    <div class="card">
        <h2>🔍 Application ID se Case Track Karein</h2>
        <form action="${pageContext.request.contextPath}/family/track/search" method="POST">
            <div class="search-row">
                <input type="text" name="applicationId"
                       value="${searchId}"
                       placeholder="Application ID daalen — jaise SP-2024-00001"
                       required>
                <button type="submit" class="btn-search">Track 🔍</button>
            </div>
        </form>

        <!-- Not found -->
        <c:if test="${notFound}">
            <div class="not-found">
                <div class="icon">❌</div>
                <strong>Application ID nahi mili: ${searchId}</strong><br>
                <small>Please ID dobara check karein ya helpline pe call karein: 1800-XXX-XXXX</small>
            </div>
        </c:if>

        <!-- Result -->
        <c:if test="${not empty soldier}">
            <div class="result-card">
                <div class="result-header">
                    <h3>🎖️ ${soldier.fullName} — ${soldier.rank}</h3>
                    <span class="app-id">${soldier.applicationId}</span>
                </div>
                <div class="result-body">
                    <div class="info-row">
                        <div class="info-item">
                            <label>Unit</label>
                            <span>${soldier.unit}</span>
                        </div>
                        <div class="info-item">
                            <label>Date of Martyrdom</label>
                            <span>${soldier.dateOfMartydom}</span>
                        </div>
                        <div class="info-item">
                            <label>Current Status</label>
                            <span style="font-weight:700;color:#1a237e;">${soldier.caseStatus}</span>
                        </div>
                    </div>

                    <!-- Pipeline -->
                    <div class="status-pipeline">
                        <h4>📋 Case Progress</h4>
                        <div class="pipeline">
                            <c:set var="cs" value="${soldier.caseStatus}"/>
                            <div class="pip-step">
                                <div class="pip-dot done">✔</div>
                                <div class="pip-label done">Registered</div>
                            </div>
                            <div class="pip-step">
                                <div class="pip-dot ${cs == 'DOCUMENTS_SUBMITTED' || cs == 'UNDER_VERIFICATION' || cs == 'VERIFIED' || cs == 'BENEFITS_PROCESSING' || cs == 'COMPLETED' ? 'done' : ''}">
                                    ${cs == 'DOCUMENTS_SUBMITTED' || cs == 'UNDER_VERIFICATION' || cs == 'VERIFIED' || cs == 'BENEFITS_PROCESSING' || cs == 'COMPLETED' ? '✔' : '2'}
                                </div>
                                <div class="pip-label ${cs == 'DOCUMENTS_SUBMITTED' ? 'active' : ''}">Docs Submitted</div>
                            </div>
                            <div class="pip-step">
                                <div class="pip-dot ${cs == 'VERIFIED' || cs == 'BENEFITS_PROCESSING' || cs == 'COMPLETED' ? 'done' : cs == 'UNDER_VERIFICATION' ? 'active' : ''}">
                                    ${cs == 'VERIFIED' || cs == 'BENEFITS_PROCESSING' || cs == 'COMPLETED' ? '✔' : '3'}
                                </div>
                                <div class="pip-label ${cs == 'UNDER_VERIFICATION' ? 'active' : ''}">Verification</div>
                            </div>
                            <div class="pip-step">
                                <div class="pip-dot ${cs == 'BENEFITS_PROCESSING' || cs == 'COMPLETED' ? 'done' : cs == 'VERIFIED' ? 'active' : ''}">
                                    ${cs == 'BENEFITS_PROCESSING' || cs == 'COMPLETED' ? '✔' : '4'}
                                </div>
                                <div class="pip-label ${cs == 'VERIFIED' ? 'active' : ''}">Verified</div>
                            </div>
                            <div class="pip-step">
                                <div class="pip-dot ${cs == 'COMPLETED' ? 'done' : cs == 'BENEFITS_PROCESSING' ? 'active' : ''}">
                                    ${cs == 'COMPLETED' ? '✔' : '5'}
                                </div>
                                <div class="pip-label ${cs == 'BENEFITS_PROCESSING' ? 'active' : ''}">Benefits</div>
                            </div>
                            <div class="pip-step">
                                <div class="pip-dot ${cs == 'COMPLETED' ? 'done active' : ''}">
                                    ${cs == 'COMPLETED' ? '✔' : '6'}
                                </div>
                                <div class="pip-label ${cs == 'COMPLETED' ? 'done' : ''}">Completed</div>
                            </div>
                        </div>
                    </div>

                    <!-- Benefits -->
                    <c:if test="${not empty benefits}">
                        <h4 style="font-size:13px;color:#1a237e;font-weight:700;margin-bottom:10px;">💰 Benefits Ki Sthiti</h4>
                        <c:forEach var="b" items="${benefits}">
                            <div class="benefit-row">
                                <div class="benefit-name">
                                    ${b.benefitName}
                                    <c:if test="${b.amount != null}">
                                        <small style="color:#888;"> — ₹${b.amount} ${b.amountUnit}</small>
                                    </c:if>
                                </div>
                                <span class="bstatus bs-${b.status}">${b.status}</span>
                            </div>
                        </c:forEach>
                    </c:if>

                    <div style="margin-top:14px;padding:10px 14px;background:#fff8e1;border-radius:7px;font-size:12px;color:#555;border-left:3px solid #ffd54f;">
                        📞 Koi sawal ho to helpline pe call karein: <strong>1800-XXX-XXXX</strong> (Toll Free)
                    </div>
                </div>
            </div>
        </c:if>
    </div>

    <div class="back-btn">
        <a href="${pageContext.request.contextPath}/login">← Login Page pe Wapas</a>
    </div>
</div>
</body>
</html>
