<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Application - Shaheed Parmar Portal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/army-theme.css">
</head>
<body>
<div class="nav-bar">
    <div class="nav-title">
        <div class="nav-emblem">⚔</div>
        <div>New Application</div>
    </div>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/family/dashboard">Back to Dashboard</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</div>

<main class="main-layout">
    <h1 class="page-title">Submit Shaheed Details</h1>
    <p class="page-subtitle">
        कृपया शहीद जवान / अधिकारी की basic जानकारी भरें। बाद में इस पर government verification और
        facility allocation किया जाएगा।
    </p>

    <div class="form-card">
        <form method="post" action="${pageContext.request.contextPath}/family/application/submit">
            <div class="form-group">
                <label for="shaheedName">Shaheed Name</label>
                <input id="shaheedName" name="shaheedName" required>
            </div>
            <div class="form-group">
                <label for="rank">Rank</label>
                <input id="rank" name="rank" required>
            </div>
            <div class="form-group">
                <label for="unit">Army Unit</label>
                <input id="unit" name="unit" required>
            </div>
            <div class="form-group">
                <label for="serviceNumber">Service Number</label>
                <input id="serviceNumber" name="serviceNumber" required>
            </div>
            <div class="form-group">
                <label for="dateOfMartyrdom">Date of Martyrdom</label>
                <input id="dateOfMartyrdom" name="dateOfMartyrdom" type="date" required>
            </div>
            <button class="btn btn-primary" type="submit">Submit for Verification</button>
        </form>
    </div>
</main>
</body>
</html>

