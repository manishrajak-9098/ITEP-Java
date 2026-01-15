<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body style="text-align:center; padding-top:50px;">

    <h2>User Login</h2>

    <% if ("invalid".equals(request.getParameter("error"))) { %>
        <p style="color:red;">Invalid Email or Password</p>
    <% } %>

    <% if ("registered".equals(request.getParameter("msg"))) { %>
        <p style="color:green;">Registration successful. Please login.</p>
    <% } %>

    <% if ("role_updated".equals(request.getParameter("msg"))) { %>
        <p style="color:green;">Role updated successfully. Please login.</p>
    <% } %>

    <form action="LoginServlet" method="post"
          style="display:inline-block; border:1px solid #ccc; padding:20px;">
        <input type="email" name="email" placeholder="Email" required><br><br>
        <input type="password" name="password" placeholder="Password" required><br><br>
        <button type="submit">Login</button>
    </form>

    <p><a href="register.jsp">New User? Register</a></p>

</body>
</html>
