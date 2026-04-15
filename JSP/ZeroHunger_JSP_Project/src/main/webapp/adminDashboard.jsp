<%@ page import="java.util.*, com.jsp.model.*" %>

<%
    User u = (User) session.getAttribute("user");
    if (u == null || !"ADMIN".equals(u.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<User> users = (List<User>) request.getAttribute("users");
    List<Food> foods = (List<Food>) request.getAttribute("foods");
    List<Feedback> fbs = (List<Feedback>) request.getAttribute("feedbacks");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet" href="css/style.css">
    <script>window.history.forward();</script>
</head>

<body>

<div class="navbar">
    <div class="logo-container">
        <img src="https://cdn-icons-png.flaticon.com/512/2921/2921822.png">
        <h2>Admin Panel</h2>
    </div>
    <a href="LogoutServlet">Logout</a>
</div>

<div class="container-wide main-content">

    <!-- USER MANAGEMENT -->
    <h3>User Management</h3>
    <table>
        <tr>
            <th>ID</th><th>Name</th><th>Email</th><th>Role</th>
        </tr>

        <%
            if (users != null && !users.isEmpty()) {
                for (User userObj : users) {
        %>
        <tr>
            <td><%= userObj.getUserId() %></td>
            <td><%= userObj.getName() %></td>
            <td><%= userObj.getEmail() %></td>
            <td><b><%= userObj.getRole() %></b></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="4">No users found</td></tr>
        <% } %>
    </table>

    <br>

    <!-- FOOD LOGS -->
    <h3>Food Donation Logs</h3>
    <table>
        <tr>
            <th>Food</th><th>Donor</th><th>Claimed By</th><th>Status</th>
        </tr>

        <%
            if (foods != null && !foods.isEmpty()) {
                for (Food f : foods) {
        %>
        <tr>
            <td><%= f.getFoodName() %></td>
            <td><%= f.getDonorName() %></td>
            <td><%= f.getNgoName() %></td>
            <td><%= f.getStatus() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="4">No food records found</td></tr>
        <% } %>
    </table>

    <br>

    <!-- FEEDBACK -->
    <h3>Feedback</h3>
    <table>
        <tr>
            <th>From</th><th>Message</th>
        </tr>

        <%
            if (fbs != null && !fbs.isEmpty()) {
                for (Feedback fb : fbs) {
        %>
        <tr>
            <td><%= fb.getName() %> (<%= fb.getEmail() %>)</td>
            <td><%= fb.getMessage() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="2">No feedback available</td></tr>
        <% } %>
    </table>

</div>

<%@ include file="footer.jsp" %>

</body>
</html>
