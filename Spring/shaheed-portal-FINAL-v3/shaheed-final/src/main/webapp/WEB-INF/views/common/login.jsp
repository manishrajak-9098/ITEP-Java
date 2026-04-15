<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | Shaheed Parivar Sahayata Portal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: linear-gradient(135deg, #1a237e 0%, #283593 50%, #1b5e20 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .login-wrapper {
            background: white;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 20px 60px rgba(0,0,0,0.4);
            width: 420px;
            max-width: 95vw;
        }
        .login-header {
            background: linear-gradient(135deg, #1a237e, #283593);
            color: white;
            padding: 30px;
            text-align: center;
        }
        .gov-emblem {
            font-size: 48px;
            margin-bottom: 10px;
        }
        .login-header h1 {
            font-size: 18px;
            font-weight: 700;
            letter-spacing: 0.5px;
            margin-bottom: 4px;
        }
        .login-header p {
            font-size: 12px;
            opacity: 0.85;
        }
        .saffron-bar {
            height: 5px;
            background: linear-gradient(to right, #FF9933, #FFFFFF, #138808);
        }
        .login-body { padding: 35px 30px; }
        .login-body h2 {
            color: #1a237e;
            font-size: 20px;
            margin-bottom: 25px;
            text-align: center;
        }
        .form-group { margin-bottom: 18px; }
        .form-group label {
            display: block;
            font-size: 13px;
            font-weight: 600;
            color: #333;
            margin-bottom: 6px;
        }
        .form-group input {
            width: 100%;
            padding: 12px 14px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            font-size: 14px;
            transition: border-color 0.3s;
            outline: none;
        }
        .form-group input:focus { border-color: #1a237e; }
        .btn-login {
            width: 100%;
            padding: 13px;
            background: linear-gradient(135deg, #1a237e, #283593);
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 15px;
            font-weight: 600;
            cursor: pointer;
            margin-top: 8px;
            transition: opacity 0.2s;
        }
        .btn-login:hover { opacity: 0.9; }
        .alert {
            padding: 12px 14px;
            border-radius: 8px;
            margin-bottom: 18px;
            font-size: 13px;
            font-weight: 500;
        }
        .alert-danger  { background: #ffebee; color: #c62828; border-left: 4px solid #c62828; }
        .alert-success { background: #e8f5e9; color: #2e7d32; border-left: 4px solid #2e7d32; }
        .track-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            font-size: 13px;
            color: #555;
        }
        .track-link a { color: #1a237e; text-decoration: none; font-weight: 600; }
        .track-link a:hover { text-decoration: underline; }
        .login-footer {
            background: #f5f5f5;
            padding: 12px;
            text-align: center;
            font-size: 11px;
            color: #888;
            border-top: 1px solid #e0e0e0;
        }
        .info-note {
            background: #e3f2fd;
            border-left: 4px solid #1565c0;
            padding: 10px 14px;
            border-radius: 0 6px 6px 0;
            font-size: 12px;
            color: #1565c0;
            margin-bottom: 18px;
        }
    </style>
</head>
<body>
<div class="login-wrapper">
    <div class="login-header">
        <div class="gov-emblem">🇮🇳</div>
        <h1>Shaheed Parivar Sahayata Portal</h1>
        <p>Government of India | Ministry of Defence</p>
    </div>
    <div class="saffron-bar"></div>
    <div class="login-body">
        <h2>🔐 Secure Login</h2>

        <%-- Flash messages --%>
        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger">⚠ ${errorMsg}</div>
        </c:if>
        <c:if test="${not empty successMsg}">
            <div class="alert alert-success">✔ ${successMsg}</div>
        </c:if>
        <c:if test="${firstLogin}">
            <div class="alert alert-success">
                ✔ Pehla login! Kripya apna password abhi change karein.
            </div>
        </c:if>

        <div class="info-note">
            ℹ️ Ye portal sirf authorized persons ke liye hai.
            Self-registration nahi hai. Login credentials government se milte hain.
        </div>

        <form action="${pageContext.request.contextPath}/login" method="POST">
            <div class="form-group">
                <label for="username">Username / Application ID</label>
                <input type="text" id="username" name="username"
                       placeholder="Enter your username" required autocomplete="off">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password"
                       placeholder="Enter your password" required>
            </div>
            <button type="submit" class="btn-login">Login →</button>
        </form>

        <div class="track-link">
            Application ID hai? →
            <a href="${pageContext.request.contextPath}/family/track">
                Case Status Track Karein (without login)
            </a>
        </div>

        <div class="track-link" style="margin-top:10px;">
            Help chahiye? →
            <a href="tel:1800-XXX-XXXX">Helpline: 1800-XXX-XXXX</a>
        </div>
    </div>
    <div class="login-footer">
        © 2024 Ministry of Defence, Government of India | Secure Portal
    </div>
</div>
</body>
</html>
