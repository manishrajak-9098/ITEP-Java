<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <title>Server Error | Shaheed Portal</title>
    <style>
        * { margin:0; padding:0; box-sizing:border-box; }
        body { font-family:'Segoe UI',Arial,sans-serif; background:linear-gradient(135deg,#e65100,#bf360c); min-height:100vh; display:flex; align-items:center; justify-content:center; color:white; }
        .box { text-align:center; padding:40px; }
        .icon { font-size:80px; margin-bottom:20px; }
        h1 { font-size:28px; margin-bottom:10px; }
        p  { font-size:15px; opacity:0.85; margin-bottom:25px; }
        a  { color:white; background:rgba(255,255,255,0.2); padding:10px 24px; border-radius:8px; text-decoration:none; font-weight:600; }
    </style>
</head>
<body>
<div class="box">
    <div class="icon">⚠️</div>
    <h1>Internal Server Error (500)</h1>
    <p>Server mein koi technical problem aayi hai.<br>
       Thodi der baad try karein ya helpline pe call karein: 1800-XXX-XXXX</p>
    <a href="${pageContext.request.contextPath}/login">← Login Page</a>
</div>
</body>
</html>
