<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.jsp.model.Food, com.jsp.model.User" %>
<% 
   
    User u = (User) session.getAttribute("user");
    if(u == null || !u.getRole().equals("NGO")) { 
        response.sendRedirect("login.jsp"); 
        return; 
    }
    

    List<Food> list = (List<Food>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>NGO Dashboard</title>
    <style>
        body { font-family: Arial; padding: 20px; background-color: #f4f4f4; }
        .header { display: flex; justify-content: space-between; align-items: center; background: white; padding: 15px; border-radius: 8px; margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; background: white; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #007bff; color: white; }
        .btn-claim { background: #007bff; color: white; padding: 8px 12px; text-decoration: none; border-radius: 4px; }
        .logout { color: red; text-decoration: none; font-weight: bold; }
    </style>
</head>
<body>

    <div class="header">
        <h2>Welcome, <%= u.getName() %> (NGO)</h2>
        <a href="LogoutServlet" class="logout">Logout</a>
    </div>

    <h3>Available Food for Pickup</h3>

    <table>
        <tr>
            <th>Food Item</th>
            <th>Quantity</th>
            <th>Location</th>
            <th>Action</th>
        </tr>
        <% 
        if(list != null && !list.isEmpty()) {
            for(Food f : list) { 
        %>
        <tr>
            <td><%= f.getFoodName() %></td>
            <td><%= f.getQuantity() %></td>
            <td><%= f.getLocation() %></td>
            <td>
                <a href="ClaimFoodServlet?id=<%= f.getFoodId() %>" class="btn-claim">Claim / Collect</a>
            </td>
        </tr>
        <% 
            } 
        } else { 
        %>
            <tr><td colspan="4" style="text-align:center;">No food available right now.</td></tr>
        <% } %>
    </table>

</body>
</html>