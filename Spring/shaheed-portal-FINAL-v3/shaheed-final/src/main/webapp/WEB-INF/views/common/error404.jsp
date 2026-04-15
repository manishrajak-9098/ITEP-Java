<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <title>Page Not Found | Shaheed Portal</title>
    <style>
        * { margin:0; padding:0; box-sizing:border-box; }
        body { font-family:'Segoe UI',Arial,sans-serif; background:linear-gradient(135deg,#1a237e,#283593); min-height:100vh; display:flex; align-items:center; justify-content:center; color:white; }
        .box { text-align:center; padding:40px; }
        .icon { font-size:80px; margin-bottom:20px; }
        h1 { font-size:28px; margin-bottom:10px; }
        p  { font-size:15px; opacity:0.85; margin-bottom:25px; }
        a  { color:white; background:rgba(255,255,255,0.2); padding:10px 24px; border-radius:8px; text-decoration:none; font-weight:600; }
    </style>
</head>
<body>
<div class="box">
    <div class="icon">🔍</div>
    <h1>Page Not Found (404)</h1>
    <p>Aap jo page dhundh rahe hain wo exist nahi karta.<br>
       URL dobara check karein ya home page pe jaayein.</p>
    <a href="${pageContext.request.contextPath}/login">← Home Page</a>
</div>
</body>
</html>
