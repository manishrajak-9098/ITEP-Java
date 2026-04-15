<%@ page import="com.jsp.model.User" %>
<% User u = (User) session.getAttribute("user"); if(u==null || !"DONOR".equals(u.getRole())) { response.sendRedirect("login.jsp"); return; } %>
<!DOCTYPE html>
<html>
<head><title>Donor Panel</title><link rel="stylesheet" href="css/style.css"><script>window.history.forward();</script></head>
<body>
    <div class="navbar">
        <div class="logo-container"><img src="https://cdn-icons-png.flaticon.com/512/2921/2921822.png"><h2>Donor Panel</h2></div>
        <div><a href="HistoryServlet">My Donations</a><a href="LogoutServlet">Logout</a></div>
    </div>
    <div class="main-content">
        <div class="form-card">
            <h2>Donate Food</h2>
            <% if(request.getParameter("msg")!=null){ %><p class="success">Food Added Successfully!</p><% } %>
            <form action="AddFoodServlet" method="post">
                <input type="text" name="foodName" placeholder="Food Items" required>
                <input type="text" name="quantity" placeholder="Quantity" required>
                <input type="text" name="placeName" placeholder="Place Name" required>
                <input type="text" name="fullAddress" placeholder="Address" required>
                <input type="text" name="contact_no" placeholder="Phone" required>
                <input type="text" name="pickupTime" placeholder="Pickup Time" required>
                <textarea name="note" placeholder="Special Note"></textarea>
                <button type="submit" class="btn btn-green" style="width:100%">Submit Donation</button>
            </form>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>