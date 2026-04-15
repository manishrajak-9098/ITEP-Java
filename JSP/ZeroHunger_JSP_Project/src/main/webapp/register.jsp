<!DOCTYPE html>
<html>
<head>
    <title>Register | Zero Hunger</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="navbar">
        <h2>Zero Hunger</h2>
        <a href="login.jsp">Login</a>
    </div>

    <div class="main-content">
        <div class="form-card" style="max-width: 550px;"> <!-- Register ke liye thoda bada -->
            <h2>Create Account</h2>
            <% if(request.getParameter("error")!=null){ %><p style="color:red; text-align:center; margin-bottom:10px;">Email already exists!</p><% } %>
            
            <form action="RegisterServlet" method="post">
                <div class="form-group">
                    <input type="text" name="name" placeholder="Full Name" required>
                </div>
                <div class="form-group">
                    <input type="email" name="email" placeholder="Email Address" required>
                </div>
                <div class="form-group" style="display: flex; gap: 15px;">
                    <input type="password" name="password" placeholder="Password" required>
                    <input type="tel" name="phone" placeholder="Phone Number" required>
                </div>
                <div class="form-group">
                    <input type="text" name="address" placeholder="Full Address" required>
                </div>
                <div class="form-group">
                    <label>Date of Birth</label>
                    <input type="date" name="dob" required>
                </div>
                <div class="form-group">
                    <select name="gender" required>
                        <option value="">Select Gender</option>
                        <option>Male</option>
                        <option>Female</option>
                        <option>Other</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-green">Register Now</button>
            </form>
            
            <p style="text-align:center; margin-top:20px; font-size:14px;">
                Already have an account? <a href="login.jsp" style="color:#ea580c; font-weight:bold; text-decoration:none;">Login</a>
            </p>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>