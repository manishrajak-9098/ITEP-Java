<%@ page import="java.util.List, com.jsp.model.*" %>
<% 
    User u = (User) session.getAttribute("user"); 
    if(u == null || !"NGO".equals(u.getRole())) { response.sendRedirect("login.jsp"); return; }
    List<Food> list = (List<Food>) request.getAttribute("list");
    if(list == null) { response.sendRedirect("ViewFoodServlet"); return; }
%>
<!DOCTYPE html>
<html>
<head>
    <title>NGO Panel</title><link rel="stylesheet" href="css/style.css">
    <script>window.history.forward();</script>
</head>
<body>
    <div class="navbar">
        <div class="logo-container">
            <img src="https://cdn-icons-png.flaticon.com/512/2921/2921822.png">
            <h2>Zero Hunger</h2>
        </div>
        <div><a href="HistoryServlet">My Claims</a><a href="LogoutServlet">Logout</a></div>
    </div>
    <div class="container-wide main-content">
        <h2 style="text-align:center;">Available Food</h2>
        <% if(request.getParameter("error")!=null){ %><p class="error">You cannot claim your own donation!</p><% } %>
        <% if(request.getParameter("msg")!=null){ %><p class="success">Claimed! Check My Claims.</p><% } %>
        <div class="grid">
            <% for(Food f : list){ %>
            <div class="card">
                <h3><%=f.getFoodName()%></h3>
                <p>Qty: <%=f.getQuantity()%></p>
                <p>Loc: <%=f.getPlaceName()%></p>
                <br>
                <a href="ClaimFoodServlet?id=<%=f.getFoodId()%>" class="btn btn-green" style="width:100%">Claim This</a>
            </div>
            <% } %>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>