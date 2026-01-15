<%@ page import="com.jsp.model.User" %>
<%
User u = (User) session.getAttribute("user");
if(u == null || !"DONOR".equals(u.getRole())) {
    response.sendRedirect("login.jsp");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Donor Dashboard</title>
<style>
body {
 font-family: Arial;
 background:#f4f4f4;
  padding:20px;
   }
.box {
 background:white;
 padding:20px;
  border-radius:8px;
   width:500px;
    }
input, textarea, button {
    width:100%;
     padding:10px;
      margin:8px 0;
}
button {
 background:#28a745; 
 color:white;
  border:none; }
</style>
</head>
<body>

<a href="LogoutServlet" style="float:right;color:red;">Logout</a>

<div class="box">
<h2>Welcome Donor: <%= u.getName() %></h2>

<% if(request.getParameter("msg") != null) { %>
    <p style="color:green;">Food Added Successfully</p>
<% } %>

<form action="AddFoodServlet" method="post">
    <input type="text" name="foodName" placeholder="Food Name" required>
    <input type="text" name="quantity" placeholder="Quantity" required>
    <input type="text" name="placeName" placeholder="Place Name" required>
    <input type="text" name="fullAddress" placeholder="Full Address" required>
    <input type="text" name="pickupTime" placeholder="Pickup Time" required>
    <textarea name="note" placeholder="Additional Note"></textarea>
    <button type="submit">Add Food</button>
</form>
</div>

</body>
</html>
