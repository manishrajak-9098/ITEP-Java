<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<style>
body {
    font-family: Arial;
    background: #f4f6f9;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}
.box {
    background: white;
    padding: 25px;
    width: 380px;
    border-radius: 8px;
    box-shadow: 0 0 10px #aaa;
}
input, select, button {
    width: 100%;
    padding: 10px;
    margin: 8px 0;
}
button {
    background: #007bff;
    color: white;
    border: none;
}
</style>
</head>
<body>

<div class="box">
    <h2>Register</h2>

    <% if(request.getParameter("error") != null) { %>
        <p style="color:red;">Email already exists!</p>
    <% } %>

    <form action="RegisterServlet" method="post">
        <input type="text" name="name" placeholder="Full Name" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="text" name="address" placeholder="Address" required>
        <input type="date" name="dob" required>

        <select name="gender" required>
            <option value="">Select Gender</option>
            <option>Male</option>
            <option>Female</option>
            <option>Other</option>
        </select>

        <select name="role" required>
            <option value="">Select Role</option>
            <option value="DONOR">Donor</option>
            <option value="NGO">NGO</option>
        </select>

        <button type="submit">Register</button>
    </form>

    <p style="text-align:center;">
        <a href="login.jsp">Already have an account?</a>
    </p>
</div>

</body>
</html>
