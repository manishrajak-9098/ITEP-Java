<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Family Registration - Shaheed Parmar Portal</title>
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
        <a href="${pageContext.request.contextPath}/family/login">Login</a>
    </div>
</div>

<main class="main-layout">
    <h1 class="page-title">Family Registration</h1>
    <p class="page-subtitle">
        शहीद जवान / अधिकारी के परिवार का कोई सदस्य (wife / son / daughter / dependent) यहाँ से अपना खाता बना सकता है।
    </p>

    <div class="form-card">
        <form method="post" action="${pageContext.request.contextPath}/family/register">
            <div class="form-group">
                <label for="fullName">Full Name</label>
                <input id="fullName" name="fullName" type="text" required>
            </div>
            <div class="form-group">
                <label for="relation">Relation with Shaheed</label>
                <select id="relation" name="relation" required>
                    <option value="">Select</option>
                    <option value="WIFE">Wife</option>
                    <option value="SON">Son</option>
                    <option value="DAUGHTER">Daughter</option>
                    <option value="DEPENDENT">Dependent</option>
                </select>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input id="email" name="email" type="email" required>
            </div>
            <div class="form-group">
                <label for="phone">Mobile Number</label>
                <input id="phone" name="phone" type="text" required>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <textarea id="address" name="address" rows="3" required></textarea>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" type="password" required>
            </div>
            <button class="btn btn-primary" type="submit">Register</button>
        </form>
    </div>
</main>
</body>
</html>

