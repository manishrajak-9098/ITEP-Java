<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Shaheed Parmar Portal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/army-theme.css">
</head>
<body>
<div class="nav-bar">
    <div class="nav-title">
        <div class="nav-emblem">⚔</div>
        <div>Admin Dashboard</div>
    </div>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</div>

<main class="main-layout">
    <h1 class="page-title">Government Verification Overview</h1>
    <p class="page-subtitle">
        यहाँ admin सभी received applications, pending verifications, verified और rejected cases देख सकता है।
    </p>

    <div class="card-grid">
        <div class="card">
            <div class="card-title">Total Applications</div>
            <div class="card-text">${total}</div>
        </div>
        <div class="card">
            <div class="card-title">Pending Verifications</div>
            <div class="card-text">${pending}</div>
        </div>
        <div class="card">
            <div class="card-title">Verified</div>
            <div class="card-text">${verified}</div>
        </div>
        <div class="card">
            <div class="card-title">Unverified / Rejected</div>
            <div class="card-text">${rejected}</div>
        </div>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Shaheed</th>
            <th>Unit</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="app" items="${applications}">
            <tr>
                <td>${app.id}</td>
                <td>${app.shaheed.name}</td>
                <td>${app.shaheed.unit}</td>
                <td>${app.status}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/application/${app.id}">View / Verify</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
</body>
</html>

