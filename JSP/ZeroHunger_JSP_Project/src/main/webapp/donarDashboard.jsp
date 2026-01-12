<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.jsp.model.User" %>
<% 
    User u = (User) session.getAttribute("user");
    if(u == null) { 
        response.sendRedirect("login.jsp"); 
        return; 
    }
%>
<!DOCTYPE html>
<html>
<head>
<title>Donor Dashboard</title>
<style>
    body { font-family: sans-serif; padding: 20px; background: #f4f4f4; }
    .box { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px #ccc; }
    input, button { width: 100%; padding: 10px; margin: 10px 0; }
    button { background: #28a745; color: white; border: none; cursor: pointer; }
</style>
</head>
<body>
    <div class="box">
        <h2>Welcome Donor: <%= u.getName() %></h2>
        <a href="LogoutServlet" style="color:red; float:right;">Logout</a>
        <hr>
        
        <h3>Donate Food</h3>
     
        <% if(request.getParameter("msg") != null) { %>
            <p style="color:green;">Food Added Successfully!</p>
        <% } %>

        <form action="AddFoodServlet" method="post">
            <input type="text" name="food" placeholder="Food Name (e.g. Rice)" required>
            <input type="text" name="qty" placeholder="Quantity (e.g. 5kg)" required>
            <input type="text" name="location" placeholder="Location" required>
            <button type="submit">Add Food</button>
        </form>
    </div>
</body>
</html>