<%@ include file="noCache.jsp" %>
<%@ include file="navbar.jsp" %>
<% if(session.getAttribute("email") != null) { response.sendRedirect("home.jsp"); return; } %>
<!DOCTYPE html><html><head><title>Register | FindersHub</title><link rel="stylesheet" href="css/style.css"></head>
<body>
    <div class="container"><div class="card">
        <h2 style="text-align:center;">Create Account</h2>
        <p style="text-align:center;
         color:var(--text-muted);
          margin-bottom:30px;
          ">Join our community today</p>
          
        <form action="RegisterServlet" method="post">
        
            <label>Full Name</label>
            <input type="text" name="username" required>
            <label>Email Address</label>
            <input type="email" name="email" required>
            <label>Password</label>
            <input type="password" name="password" required>
            <div style="display:flex; gap:10px;">
                <div style="flex:1">
                <label>Date of Birth</label>
                <input type="date" name="dob" required>
                </div>
                <div style="flex:1">
                <label>Gender</label>
                <select name="gender"><option>Male</option><option>Female</option></select
                ></div>
            </div>
            <label>Address</label><textarea name="address" rows="2" required></textarea>
            <button type="submit" class="btn btn-primary" style="width:100%;">Create Account</button>
        </form>
    </div></div>
    <%@ include file="footer.jsp" %>
</body></html>