<%@ include file="noCache.jsp" %>
<%@ include file="navbar.jsp" %>
<% if(session.getAttribute("email") != null) { response.sendRedirect("home.jsp"); return; } %>
<!DOCTYPE html><html><head><title>Login | FindersHub</title><link rel="stylesheet" href="css/style.css">
<script>window.history.forward();</script></head>
<body>
    <div class="container"><div class="card">
        <h2 style="text-align:center;">Welcome Back</h2>
        <p style="text-align:center; color:var(--text-muted); margin-bottom:30px;">Login to manage your items</p>
        
        <% String msg = (String) session.getAttribute("msg"); if(msg != null) { %>
            <div style="background:#fee2e2; color:#b91c1c; padding:10px; border-radius:8px; text-align:center; margin-bottom:20px; font-size:13px;"> <%= msg %> </div>
        <% session.removeAttribute("msg"); } %>

        <form action="LoginServlet" method="post">
            <label>Email Address</label><input type="email" name="email" required>
            <label>Password</label><input type="password" name="password" required>
            <button type="submit" class="btn btn-primary" style="width:100%; margin-top:10px;">Login Securely</button>
        </form>
        <p style="text-align:center; margin-top:20px; font-size:14px;">Don't have an account? <a href="register.jsp" style="color:var(--primary); font-weight:700; text-decoration:none;">Sign Up</a></p>
    </div></div>
    <%@ include file="footer.jsp" %>
</body></html>