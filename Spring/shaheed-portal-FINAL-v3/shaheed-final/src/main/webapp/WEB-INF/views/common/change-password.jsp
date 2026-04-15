<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <title>Password Change | Shaheed Portal</title>
    <style>
        * { margin:0; padding:0; box-sizing:border-box; }
        body { font-family:'Segoe UI',Arial,sans-serif; background:linear-gradient(135deg,#1a237e,#1b5e20); min-height:100vh; display:flex; align-items:center; justify-content:center; }
        .card { background:white; border-radius:12px; width:420px; max-width:95vw; overflow:hidden; box-shadow:0 20px 60px rgba(0,0,0,0.4); }
        .card-header { background:linear-gradient(135deg,#1a237e,#283593); color:white; padding:24px; text-align:center; }
        .card-header h2 { font-size:18px; }
        .card-header p  { font-size:12px; opacity:0.8; margin-top:5px; }
        .saffron-line { height:4px; background:linear-gradient(to right,#FF9933,#FFFFFF,#138808); }
        .card-body { padding:28px; }
        .alert { padding:11px 14px; border-radius:7px; margin-bottom:16px; font-size:13px; }
        .alert-success { background:#e8f5e9; color:#2e7d32; border-left:4px solid #2e7d32; }
        .alert-danger  { background:#ffebee; color:#c62828; border-left:4px solid #c62828; }
        .form-group { margin-bottom:16px; }
        .form-group label { display:block; font-size:12px; font-weight:600; color:#444; margin-bottom:5px; }
        .form-group input { width:100%; padding:11px 13px; border:2px solid #e0e0e0; border-radius:7px; font-size:14px; outline:none; }
        .form-group input:focus { border-color:#1a237e; }
        .btn-submit { width:100%; padding:12px; background:linear-gradient(135deg,#1a237e,#283593); color:white; border:none; border-radius:8px; font-size:15px; font-weight:600; cursor:pointer; }
        .btn-submit:hover { opacity:0.9; }
        .info-note { background:#e3f2fd; border-left:4px solid #1565c0; padding:10px 14px; border-radius:0 6px 6px 0; font-size:12px; color:#1565c0; margin-bottom:18px; }
    </style>
</head>
<body>
<div class="card">
    <div class="card-header">
        <h2>🔑 Password Change Karein</h2>
        <p>Apna naya password set karein</p>
    </div>
    <div class="saffron-line"></div>
    <div class="card-body">

        <c:if test="${firstLogin}">
            <div class="alert alert-success">✔ Pehla login! Security ke liye password change karna zaroori hai.</div>
        </c:if>
        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger">⚠ ${errorMsg}</div>
        </c:if>

        <div class="info-note">
            Password mein kam se kam 6 characters hone chahiye.
        </div>

        <form action="${pageContext.request.contextPath}/change-password" method="POST">
            <div class="form-group">
                <label>Purana Password</label>
                <input type="password" name="oldPassword" placeholder="Current password" required>
            </div>
            <div class="form-group">
                <label>Naya Password</label>
                <input type="password" name="newPassword" placeholder="New password (min 6 chars)" required minlength="6">
            </div>
            <div class="form-group">
                <label>Naya Password Confirm</label>
                <input type="password" name="confirmPassword" placeholder="Re-enter new password" required>
            </div>
            <button type="submit" class="btn-submit">Password Change Karein →</button>
        </form>
    </div>
</div>
</body>
</html>
