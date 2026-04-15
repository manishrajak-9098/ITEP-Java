<%@ page import="java.util.List, com.jsp.model.*" %>
<% 
    User u = (User) session.getAttribute("user"); if(u==null) { response.sendRedirect("login.jsp"); return; }
    String backLink = "DONOR".equals(u.getRole()) ? "donorDashboard.jsp" : "ViewFoodServlet";
%>
<!DOCTYPE html>
<html>
<head><title>History</title><link rel="stylesheet" href="css/style.css"></head>
<body>
    <div class="navbar">
        <div class="logo-container"><img src="https://cdn-icons-png.flaticon.com/512/2921/2921822.png"><h2>History</h2></div>
        <div><a href="<%= backLink %>">Back to Dashboard</a><a href="LogoutServlet">Logout</a></div>
    </div>
    <div class="container-wide main-content">
        <h2 style="text-align:center;
         margin-bottom:20px;
         "><%= request.getAttribute("title") %></h2>
        <table>
            <tr>
            <th>Food</th>
            <th>Qty</th><th>Date</th>
            <th>Status</th>
            <% if("DONOR".equals(u.getRole())) 
            { %><th>NGO Details</th><% } %>
            <th>Action</th></tr>
            <% List<Food> list = (List<Food>) request.getAttribute("list");
               if(list!=null && !list.isEmpty()){ for(Food f : list){ %>
            <tr>
            <td><%=f.getFoodName()%></td><td><%=f.getQuantity()%></td>
            <td><%=f.getPostedTime()%></td>
            <td style="font-weight:bold; color:<%=f.getStatus().equals("AVAILABLE")?"green":"#ea580c"%>"><%=f.getStatus()%></td>
                <% if("DONOR".equals(u.getRole())) { 
          %><td>
                <%=f.getNgoName()%></td>
                <% } %>
           <td><% if("NGO".equals(u.getRole()) && "CLAIMED".equals(f.getStatus()))
                { %>
               <a href="CollectFoodServlet?id=<%=f.getFoodId()%>" 
                  class="btn btn-green"
                  style="padding:5px 10px; font-size:12px;">Collected</a>
                <% } else { %>-<% } %></td></tr>
            <% }} %>
        </table>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>