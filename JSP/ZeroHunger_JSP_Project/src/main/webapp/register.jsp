<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register | Zero Hunger</title>
<style>
    body { font-family: Arial, sans-serif; background: #f4f6f9; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
    .box { background: white; padding: 30px; border-radius: 10px; box-shadow: 0px 0px 10px 0px #aaa; width: 350px; text-align: center; }
    input, select, button { width: 100%; padding: 12px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; box-sizing: border-box; }
    button { background: #007bff; color: white; border: none; font-size: 16px; cursor: pointer; }
    button:hover { background: #0056b3; }
    .error { color: red; font-size: 14px; margin-bottom: 10px; }
    a { color: #007bff; text-decoration: none; }
    a:hover { text-decoration: underline; }
</style>
</head>
<body>

<div class="box">
    <h2>Register</h2>

   
    <% if(request.getParameter("error") != null) { %>
        <p class="error"> Registration Failed! Check Console/DB.</p>
    <% } %>

    <form action="RegisterServlet" method="post">
        <input type="text" name="name" placeholder="Full Name" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>

        <select name="role" required>
            <option value="">-- Select Role --</option>
            <option value="DONOR">Donor (I want to give food)</option>
            <option value="NGO">NGO (I want to collect food)</option>
        </select>

        <button type="submit">Register</button>
    </form>

   
    <p style="margin-top: 15px;">
        Already have an account? <br>
        <a href="login.jsp">Login Here</a>
    </p>

</div>

</body>
</html>