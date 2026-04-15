<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Family Dashboard - Shaheed Parmar Portal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/army-theme.css">
</head>
<body>
<div class="nav-bar">
    <div class="nav-title">
        <div class="nav-emblem">⚔</div>
        <div>Family Dashboard</div>
    </div>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/family/application/new">New Application</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</div>

<main class="main-layout">
    <h1 class="page-title">Your Applications</h1>
    <p class="page-subtitle">
        यहाँ आप अपने द्वारा submit की गई सभी applications की status और approved facilities देख सकते हैं।
    </p>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Shaheed</th>
            <th>Unit</th>
            <th>Status</th>
            <th>Facilities</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="app" items="${applications}">
            <tr>
                <td>${app.id}</td>
                <td>${app.shaheed.name}</td>
                <td>${app.shaheed.unit}</td>
                <td>
                    <c:choose>
                        <c:when test="${app.status == 'PENDING'}">
                            <span class="status-badge status-pending">Pending</span>
                        </c:when>
                        <c:when test="${app.status == 'VERIFIED'}">
                            <span class="status-badge status-verified">Verified</span>
                        </c:when>
                        <c:when test="${app.status == 'REJECTED'}">
                            <span class="status-badge status-rejected">Rejected</span>
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <c:forEach var="alloc" items="${app.allocations}">
                        <div style="font-size: 0.75rem;">
                            ${alloc.facilityType} (from ${alloc.startDate}) - ${alloc.remarks}
                        </div>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
</body>
</html>

