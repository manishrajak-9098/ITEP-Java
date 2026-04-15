<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Family Login - Shaheed Parmar Portal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/army-theme.css">
</head>
<body>
<div class="nav-bar">
    <div class="nav-title">
        <div class="nav-emblem">⚔</div>
        <div>Shaheed Parmar Portal</div>
    </div>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/index">Home</a>
        <a href="${pageContext.request.contextPath}/family/register">Family Registration</a>
    </div>
</div>

<main class="main-layout">
    <h1 class="page-title">Family / Admin Login</h1>
    <p class="page-subtitle">
        Registered family members और pre-approved government admin यहाँ से login कर सकते हैं।
        Admin के लिए default demo credentials: <strong>admin@gov.in / admin123</strong>
    </p>

    <div class="form-card">
        <c:if test="${not empty error}">
            <div class="text-error">${error}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="text-success">${message}</div>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/family/login">
            <div class="form-group">
                <label for="email">Email</label>
                <input id="email" name="email" type="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" type="password" required>
            </div>
            <button class="btn btn-primary" type="submit">Login</button>
        </form>
    </div>
</main>
</body>
</html>

