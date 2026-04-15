<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.project.Item" %>
<%@ include file="noCache.jsp" %>
<%@ include file="navbar.jsp" %>

<%
    // Safety check agar session expire ho gaya ho
    if(session.getAttribute("email") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>My History | FindersHub</title>
    <!-- Path check: agar folder 'css' hai toh 'css/style.css' likhein -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <div class="wide-card">
            <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:30px;">
                <div>
                    <h2 style="margin-bottom:5px;">My Activity History</h2>
                    <p class="sub" style="margin-bottom:0;">Items you have reported personally</p>
                </div>
                <a href="home.jsp">
                    <button class="btn" style="background:#f1f5f9; color:var(--dark); border:1px solid var(--border);">
                        &larr; Dashboard
                    </button>
                </a>
            </div>

            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>Item Details</th>
                            <th>Date Reported</th>
                            <th>Status</th>
                            <th>Finder Name</th>
                        </tr>
                    </thead>
                   <!-- history.jsp -->

<tbody>
<% 
    List<Item> myHistoryList = (List<Item>) request.getAttribute("myItems");
    String myEmail = (String) session.getAttribute("email"); // Apna email nikalo check karne ke liye

    if(myHistoryList != null && !myHistoryList.isEmpty()) {
        for(Item i : myHistoryList) {
            // Check karein ki main Reporter hu ya Finder
            boolean isReporter = myEmail.equals(i.getEmail());
            boolean isFinder = myEmail.equals(i.getFinderEmail());
%>
<tr>
    <td>
        <strong><%= i.getItemName() %></strong>
        <br>
        <!-- Role Badge Dikhao -->
        <% if(isReporter) { %>
            <span style="font-size:10px; background:#e0f2fe; color:#0369a1; padding:2px 6px; border-radius:4px;">YOU REPORTED</span>
        <% } else if(isFinder) { %>
            <span style="font-size:10px; background:#dcfce7; color:#15803d; padding:2px 6px; border-radius:4px;">YOU RESOLVED</span>
        <% } %>
        <br>
        <small style="color:var(--text-muted)"><%= i.getLocation() %></small>
    </td>
    <td><%= i.getItemDate() != null ? i.getItemDate().replace("T", " ") : "-" %></td>
    <td><span class="badge status-<%= i.getStatus() %>"><%= i.getStatus() %></span></td>
    <td>
        <% if("Resolved".equals(i.getStatus())) { %>
            <% if(isFinder) { %>
                <span style="color:var(--primary); font-weight:bold;">You found this!</span>
            <% } else { %>
                <span style="font-weight:600; color:var(--primary);"><%= i.getFinderName() %></span>
            <% } %>
        <% } else { %>
            <span style="color:#cbd5e1; font-style:italic;">Pending...</span>
        <% } %>
    </td>
</tr>
<% 
        } 
    } else { 
%>
    <tr>
        <td colspan="4" style="text-align:center; padding:50px;">
            <p style="color:var(--text-muted);">No records found in your account.</p>
        </td>
    </tr>
<% } %>
</tbody>
                </table>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>