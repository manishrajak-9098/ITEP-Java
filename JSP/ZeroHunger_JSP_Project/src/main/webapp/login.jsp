<!DOCTYPE html>
<html>
<head>
    <title>Login | Zero Hunger</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="navbar">
        <h2>Zero Hunger</h2>
        <a href="index.jsp">Home</a>
    </div>

    <div class="main-content">
        <div class="form-card">
            <h2>Login</h2>
            <% if(request.getParameter("error")!=null){ %><p style="color:red; text-align:center; margin-bottom:10px;">Invalid Email or Password!</p><% } %>
            
            <form action="LoginServlet" method="post">
                <div class="form-group">
                    <input type="email" name="email" placeholder="Email Address" required>
                </div>
                <div class="form-group">
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <button type="submit" class="btn btn-orange">Login</button>
            </form>
            
            <p style="text-align:center; margin-top:20px; font-size:14px;">
                Don't have an account? <a href="register.jsp" style="color:#166534; font-weight:bold; text-decoration:none;">Register</a>
            </p>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>