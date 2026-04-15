<%@ page session="true" %>
<% 
    // Variable names unique rakhe hain taaki conflict na ho
    String navRole = (String) session.getAttribute("role"); 
    String navUser = (String) session.getAttribute("username"); 
%>
<nav class="navbar">
    <a href="index.jsp" class="logo-container">
        <div class="logo-icon">FH</div>
        <div class="logo-text">Finders<span>Hub</span></div>
    </a>

    <div class="nav-links">
        <% if (navUser != null) { %>
            <% if ("admin".equals(navRole)) { %>
                <a href="AdminDashboardServlet">Admin Panel</a>
                <a href="ViewItemServlet">Global Records</a>
            <% } else { %>
                <a href="home.jsp">Dashboard</a>
                <a href="addItem.jsp">Report Item</a>
                <a href="UserHistoryServlet">My History</a>
            <% } %>
            
            <div style="display:flex; align-items:center; gap:15px; border-left:1px solid #eee; padding-left:20px; margin-left:10px;">
                <span style="font-size:14px; font-weight:700;"><%= navUser %></span>
                <a href="LogoutServlet" style="color:#ef4444; font-size:13px; font-weight:700; text-decoration:none;">Logout</a>
            </div>
        <% } else { %>
            <a href="index.jsp">Home</a>
            <a href="login.jsp">Login</a>
            <a href="register.jsp" class="btn btn-primary" style="color:white; text-decoration:none;">Join Community</a>
        <% } %>
    </div>
</nav>