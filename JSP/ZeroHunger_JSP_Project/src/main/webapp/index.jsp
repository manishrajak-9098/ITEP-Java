<%@ page import="com.jsp.model.User" %>
<% 
    User u = (User) session.getAttribute("user"); 
    if(u != null) {
        if("ADMIN".equals(u.getRole())) response.sendRedirect("AdminServlet");
        else response.sendRedirect("select_role.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Zero Hunger</title><link rel="stylesheet" href="css/style.css"></head>
<body>
    <div class="navbar">
        <a href="index.jsp" class="logo-container"><img src="https://cdn-icons-png.flaticon.com/512/2921/2921822.png"><h2>Zero Hunger</h2></a>
        <div><a href="login.jsp">Login</a><a href="register.jsp">Register</a><a href="about.jsp">About</a><a href="contact.jsp">Contact</a></div>
    </div>
    <div class="hero">
        <h1>Share Food, Save Lives</h1>
        <p>Donate surplus food or join as an NGO to distribute it.</p>
        <div style="display:flex; gap:20px;"><a href="login.jsp" class="btn btn-orange">Donate Now</a><a href="about.jsp" class="btn btn-green">Learn More</a></div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>