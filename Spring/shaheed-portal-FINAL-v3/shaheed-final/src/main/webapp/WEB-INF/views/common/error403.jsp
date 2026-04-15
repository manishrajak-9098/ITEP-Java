<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <title>Access Denied | Shaheed Portal</title>
    <style>
        * { margin:0; padding:0; box-sizing:border-box; }
        body { font-family:'Segoe UI',Arial,sans-serif; background:linear-gradient(135deg,#b71c1c,#c62828); min-height:100vh; display:flex; align-items:center; justify-content:center; color:white; }
        .box { text-align:center; padding:40px; }
        .icon { font-size:80px; margin-bottom:20px; }
        h1 { font-size:28px; margin-bottom:10px; }
        p  { font-size:15px; opacity:0.85; margin-bottom:25px; }
        a  { color:white; background:rgba(255,255,255,0.2); padding:10px 24px; border-radius:8px; text-decoration:none; font-weight:600; }
        a:hover { background:rgba(255,255,255,0.3); }
    </style>
</head>
<body>
<div class="box">
    <div class="icon">🚫</div>
    <h1>Access Denied (403)</h1>
    <p>Aapke paas is page ko access karne ki permission nahi hai.<br>
       Agar aapko lagta hai ye galti hai, admin se contact karein.</p>
    <a href="${pageContext.request.contextPath}/login">← Login Page pe Wapas</a>
</div>
</body>
</html>
