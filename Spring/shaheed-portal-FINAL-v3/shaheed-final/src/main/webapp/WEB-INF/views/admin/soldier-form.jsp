<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <title>Shaheed Register | Admin</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Arial, sans-serif; background: #f0f2f5; }
        .topbar {
            background: linear-gradient(135deg, #1a237e, #283593);
            color: white; padding: 14px 25px;
            display: flex; align-items: center; justify-content: space-between;
        }
        .topbar h1 { font-size: 18px; }
        .topbar a { color: rgba(255,255,255,0.8); text-decoration: none; font-size: 13px; }
        .topbar a:hover { color: white; }
        .saffron-line { height: 4px; background: linear-gradient(to right, #FF9933, #FFFFFF, #138808); }
        .content { max-width: 800px; margin: 30px auto; padding: 0 20px; }
        .form-card {
            background: white; border-radius: 12px;
            box-shadow: 0 4px 16px rgba(0,0,0,0.08); overflow: hidden;
        }
        .form-header {
            background: linear-gradient(135deg, #1a237e, #283593);
            color: white; padding: 20px 25px;
        }
        .form-header h2 { font-size: 18px; }
        .form-header p { font-size: 12px; opacity: 0.8; margin-top: 4px; }
        .form-body { padding: 25px; }
        .section-title {
            font-size: 14px; font-weight: 700; color: #1a237e;
            padding: 8px 12px; background: #e8eaf6; border-radius: 6px;
            margin: 20px 0 15px; border-left: 4px solid #1a237e;
        }
        .form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; }
        .form-group { margin-bottom: 16px; }
        .form-group label {
            display: block; font-size: 12px; font-weight: 600;
            color: #444; margin-bottom: 5px;
        }
        .form-group label .required { color: #e53935; }
        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%; padding: 10px 12px;
            border: 2px solid #e0e0e0; border-radius: 7px;
            font-size: 13px; outline: none; transition: border-color 0.2s;
            font-family: inherit;
        }
        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus { border-color: #1a237e; }
        .form-group textarea { resize: vertical; min-height: 80px; }
        .form-actions {
            display: flex; gap: 12px; padding: 20px 25px;
            border-top: 1px solid #f0f0f0; background: #fafafa;
        }
        .btn-submit {
            padding: 11px 28px; background: linear-gradient(135deg,#1a237e,#283593);
            color: white; border: none; border-radius: 8px; font-size: 14px;
            font-weight: 600; cursor: pointer; transition: opacity 0.2s;
        }
        .btn-submit:hover { opacity: 0.9; }
        .btn-cancel {
            padding: 11px 22px; background: #f5f5f5; color: #555;
            border: 2px solid #ddd; border-radius: 8px; font-size: 14px;
            font-weight: 600; text-decoration: none; display: inline-block;
        }
        .alert { padding: 12px 14px; border-radius: 8px; margin-bottom: 16px; font-size: 13px; }
        .alert-danger  { background: #ffebee; color: #c62828; border-left: 4px solid #c62828; }
        .alert-success { background: #e8f5e9; color: #2e7d32; border-left: 4px solid #2e7d32; }
    </style>
</head>
<body>

<div class="topbar">
    <h1>🎖️ Shaheed Register Karein</h1>
    <a href="${pageContext.request.contextPath}/admin/dashboard">← Dashboard</a>
</div>
<div class="saffron-line"></div>

<div class="content">
    <c:if test="${not empty errorMsg}">
        <div class="alert alert-danger">⚠ ${errorMsg}</div>
    </c:if>

    <div class="form-card">
        <div class="form-header">
            <h2>➕ New Shaheed Sainik Ka Record</h2>
            <p>Saari mandatory fields (*) fill karein. Application ID automatically generate hoga.</p>
        </div>

        <form action="${pageContext.request.contextPath}/admin/soldier/save" method="POST">
            <div class="form-body">

                <!-- Personal Information -->
                <div class="section-title">👤 Personal Information</div>
                <div class="form-row">
                    <div class="form-group">
                        <label>Full Name <span class="required">*</span></label>
                        <input type="text" name="fullName" placeholder="Jaise: Rajesh Kumar Singh" required>
                    </div>
                    <div class="form-group">
                        <label>Service Number <span class="required">*</span></label>
                        <input type="text" name="serviceNumber" placeholder="Jaise: 15723456P" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>Rank <span class="required">*</span></label>
                        <select name="rank" required>
                            <option value="">-- Select Rank --</option>
                            <optgroup label="Officers">
                                <option>General</option>
                                <option>Lieutenant General</option>
                                <option>Major General</option>
                                <option>Brigadier</option>
                                <option>Colonel</option>
                                <option>Lieutenant Colonel</option>
                                <option>Major</option>
                                <option>Captain</option>
                                <option>Lieutenant</option>
                            </optgroup>
                            <optgroup label="JCOs">
                                <option>Subedar Major</option>
                                <option>Subedar</option>
                                <option>Naib Subedar</option>
                            </optgroup>
                            <optgroup label="Other Ranks">
                                <option>Havildar</option>
                                <option>Naik</option>
                                <option>Lance Naik</option>
                                <option>Sepoy / Rifleman / Trooper</option>
                            </optgroup>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Unit <span class="required">*</span></label>
                        <input type="text" name="unit" placeholder="Jaise: 7 PARA SF" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>Regiment / Corps</label>
                        <input type="text" name="regiment" placeholder="Jaise: RAJPUTANA RIFLES">
                    </div>
                    <div class="form-group">
                        <label>Date of Birth</label>
                        <input type="date" name="dateOfBirth">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>Date of Joining Service</label>
                        <input type="date" name="dateOfJoining">
                    </div>
                    <div class="form-group">
                        <label>Awards / Gallantry Medals</label>
                        <input type="text" name="awards" placeholder="Jaise: Vir Chakra, Sena Medal">
                    </div>
                </div>

                <!-- Martyrdom Details -->
                <div class="section-title">🕯️ Martyrdom Details (Shahaadat Ka Vivaran)</div>
                <div class="form-row">
                    <div class="form-group">
                        <label>Date of Martyrdom <span class="required">*</span></label>
                        <input type="date" name="dateOfMartydom" required>
                    </div>
                    <div class="form-group">
                        <label>Place of Martyrdom</label>
                        <input type="text" name="placeOfMartydom" placeholder="Jaise: Galwan Valley, Ladakh">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>Category <span class="required">*</span></label>
                        <select name="martyrdomCategory" required>
                            <option value="">-- Select Category --</option>
                            <c:forEach var="cat" items="${categories}">
                                <option value="${cat}">${cat}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Assign Officer (for verification)</label>
                        <select name="assignedOfficerId">
                            <option value="">-- Assign Later --</option>
                            <c:forEach var="officer" items="${officers}">
                                <option value="${officer.userId}">${officer.fullName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

            </div>

            <div class="form-actions">
                <button type="submit" class="btn-submit">💾 Record Save Karein</button>
                <a href="${pageContext.request.contextPath}/admin/soldiers" class="btn-cancel">Cancel</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>
