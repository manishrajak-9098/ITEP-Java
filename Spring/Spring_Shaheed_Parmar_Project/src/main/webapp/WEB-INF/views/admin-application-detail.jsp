<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Application Detail - Shaheed Parmar Portal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/army-theme.css">
</head>
<body>
<div class="nav-bar">
    <div class="nav-title">
        <div class="nav-emblem">⚔</div>
        <div>Application #${application.id}</div>
    </div>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/admin/dashboard">Back to Dashboard</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</div>

<main class="main-layout">
    <h1 class="page-title">Verification & Facility Allocation</h1>
    <p class="page-subtitle">
        Family द्वारा भरी गई shaheed details verify करें और उचित government facilities assign करें।
    </p>

    <div class="card" style="margin-bottom: 1.5rem;">
        <div class="card-title">Shaheed Details</div>
        <div class="card-text">
            <strong>Name:</strong> ${application.shaheed.name} <br>
            <strong>Rank:</strong> ${application.shaheed.rank} <br>
            <strong>Unit:</strong> ${application.shaheed.unit} <br>
            <strong>Service Number:</strong> ${application.shaheed.serviceNumber}
        </div>
    </div>

    <div class="card" style="margin-bottom: 1.5rem;">
        <div class="card-title">Current Status</div>
        <div class="card-text">
            Status: ${application.status}
            <c:if test="${application.status == 'REJECTED'}">
                <br>
                Reason: ${application.rejectionReason}
            </c:if>
        </div>
    </div>

    <div class="card" style="margin-bottom: 1.5rem;">
        <div class="card-title">Verification Actions</div>
        <div class="card-text">
            <form method="post" action="${pageContext.request.contextPath}/admin/application/${application.id}/verify" style="display:inline;">
                <button class="btn btn-primary" type="submit">Mark as Verified</button>
            </form>
            <form method="post" action="${pageContext.request.contextPath}/admin/application/${application.id}/reject" style="display:inline;margin-left:0.5rem;">
                <input type="text" name="reason" placeholder="Rejection reason" required
                       style="padding:0.3rem 0.5rem;border-radius:999px;border:1px solid #4b5563;background:#020617;color:#e5e7eb;font-size:0.8rem;">
                <button class="btn btn-outline" type="submit">Reject</button>
            </form>
        </div>
    </div>

    <div class="card" style="margin-bottom: 1.5rem;">
        <div class="card-title">Allocate Facilities</div>
        <div class="card-text">
            <form method="post" action="${pageContext.request.contextPath}/admin/application/${application.id}/facility">
                <div class="form-group">
                    <label for="facilityType">Facility Type</label>
                    <select id="facilityType" name="facilityType" required>
                        <c:forEach var="type" items="${facilityTypes}">
                            <option value="${type}">${type}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="startDate">Start Date</label>
                    <input id="startDate" name="startDate" type="date" required>
                </div>
                <div class="form-group">
                    <label for="remarks">Remarks</label>
                    <input id="remarks" name="remarks" type="text" required>
                </div>
                <button class="btn btn-primary" type="submit">Add Facility</button>
            </form>
        </div>
    </div>

    <div class="card">
        <div class="card-title">Allocated Facilities</div>
        <div class="card-text">
            <c:forEach var="alloc" items="${application.allocations}">
                <div style="margin-bottom:0.3rem;">
                    <strong>${alloc.facilityType}</strong> – from ${alloc.startDate} (${alloc.remarks})
                </div>
            </c:forEach>
            <c:if test="${empty application.allocations}">
                <div>No facilities allocated yet.</div>
            </c:if>
        </div>
    </div>
</main>
</body>
</html>

