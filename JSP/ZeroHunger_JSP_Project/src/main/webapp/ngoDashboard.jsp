<%@ page import="java.util.*, com.jsp.model.Food, com.jsp.model.User" %>
<%
User u = (User) session.getAttribute("user");
if(u == null || !"NGO".equals(u.getRole())) {
    response.sendRedirect("login.jsp");
    return;
}
List<Food> list = (List<Food>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<title>NGO Dashboard</title>
<style>
body { font-family: Arial; background:#f4f4f4; padding:20px; }
table { width:100%; background:white; border-collapse:collapse; }
th, td { padding:10px; border:1px solid #ccc; }
th { background:#007bff; color:white; }
a.btn { background:#28a745; color:white; padding:6px 10px; text-decoration:none; }
</style>
</head>
<body>

<h2>Welcome NGO: <%= u.getName() %></h2>
<a href="LogoutServlet" style="color:red;">Logout</a>

<h3>Available Food</h3>

<table>
<tr>
    <th>Food</th>
    <th>Qty</th>
    <th>Place</th>
    <th>Address</th>
    <th>Time</th>
    <th>Action</th>
</tr>

<% if(list != null && !list.isEmpty()) {
   for(Food f : list) { %>
<tr>
<td><%= f.getFoodName() %></td>
<td><%= f.getQuantity() %></td>
<td><%= f.getPlaceName() %></td>
<td><%= f.getFullAddress() %></td>
<td><%= f.getPickupTime() %></td>
<td><a class="btn" href="ClaimFoodServlet?id=<%=f.getFoodId()%>">Claim</a></td>
</tr>
<% }} else { %>
<tr><td colspan="6">No food available</td></tr>
<% } %>

</table>
</body>
</html>
