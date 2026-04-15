<%@ page import="com.jsp.model.User" %>
<% User u = (User) session.getAttribute("user"); if(u==null) { response.sendRedirect("login.jsp"); return; } %>
<!DOCTYPE html>
<html>
<head><title>Select Role</title><link rel="stylesheet" href="css/style.css">
<script>function preventBack(){window.history.forward();}setTimeout("preventBack()",0);window.onunload=function(){null};</script>
</head>
<body>
    <div class="navbar">
        <a href="#" class="logo-container"><img src="https://cdn-icons-png.flaticon.com/512/2921/2921822.png"><h2>Zero Hunger</h2></a>
        <a href="LogoutServlet" class="btn btn-orange" style="padding:5px 15px;">Logout</a>
    </div>
    <div class="main-content" style="text-align:center; padding-top:60px;">
        <h2>Hi <%= u.getName() %>, Choose your role:</h2>
        <form action="RoleUpdateServlet" method="post" style="display:flex; justify-content:center; gap:40px; margin-top:40px;">
            <button type="submit" name="roleChoice" value="DONOR" style="background:none; border:none; cursor:pointer;">
                <div class="form-card" style="width:280px; border-top:6px solid #166534; margin:0;"><span style="font-size:50px;"></span><h3>Donor</h3></div>
            </button>
            <button type="submit" name="roleChoice" value="NGO" style="background:none; border:none; cursor:pointer;">
                <div class="form-card" style="width:280px; border-top:6px solid #ea580c; margin:0;"><span style="font-size:50px;"></span><h3>NGO</h3></div>
            </button>
        </form>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>