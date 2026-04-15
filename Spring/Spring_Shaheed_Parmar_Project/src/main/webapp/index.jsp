<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shaheed Parmar Portal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/army-theme.css">
</head>
<body>
<div class="nav-bar">
    <div class="nav-title">
        <div class="nav-emblem">⚔</div>
        <div>
            <div>Shaheed Parmar Portal</div>
            <div style="font-size: 0.7rem; color: #9ca3af;">Government Welfare System for Martyrs’ Families</div>
        </div>
    </div>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/family/login">Family Login</a>
        <a href="${pageContext.request.contextPath}/family/register">Family Registration</a>
        <a href="${pageContext.request.contextPath}/family/login">Admin Login</a>
    </div>
    </div>

<main class="hero">
    <section>
        <h1 class="hero-title">सैनिकों के परिवार के लिए पारदर्शी सुविधा प्रबंधन</h1>
        <p class="hero-subtitle">
            यह Spring MVC आधारित पोर्टल शहीद जवानों / अधिकारियों के परिवारों को मिलने वाली सरकारी
            सुविधाओं को digitally register, verify और track करने के लिए बनाया गया है।
        </p>
        <div class="hero-badges">
            <div class="badge gold">Digital Verification Workflow</div>
            <div class="badge">Pension & Job Facility Tracking</div>
            <div class="badge">Transparent Audit Logs</div>
        </div>
        <div class="hero-actions">
            <a href="${pageContext.request.contextPath}/family/register">
                <button class="btn btn-primary">Family Registration</button>
            </a>
            <a href="${pageContext.request.contextPath}/family/login">
                <button class="btn btn-outline">Existing User / Admin Login</button>
            </a>
        </div>
    </section>
    <section class="card-grid">
        <div class="card">
            <div class="card-title">Family Portal</div>
            <div class="card-text">
                Registration, Shaheed details submission, दस्तावेज़ upload, real-time verification status और
                approved facilities की complete visibility।
            </div>
        </div>
        <div class="card">
            <div class="card-title">Admin Portal</div>
            <div class="card-text">
                Government workflow जैसा verification process, facility allocation (pension, job, awards)
                और complete audit trail for transparency.
            </div>
        </div>
        <div class="card">
            <div class="card-title">Army-Themed Secure Interface</div>
            <div class="card-text">
                Army colours, structured dashboards और आसान navigation के साथ एक respectful अनुभव।
            </div>
        </div>
    </section>
</main>
</body>
</html>

