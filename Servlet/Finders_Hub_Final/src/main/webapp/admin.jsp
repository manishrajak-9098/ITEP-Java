<%@ page import="java.util.*, com.project.Item" %>
<%@ include file="noCache.jsp" %>
<%@ include file="navbar.jsp" %>
<% if(!"admin".equals(session.getAttribute("role"))) { response.sendRedirect("login.jsp"); return; } %>
<!DOCTYPE html><html><head><title>Admin Dashboard | FindersHub</title><link rel="stylesheet" href="css/style.css"></head>
<body>
    <div class="container"><div class="wide-card">
        <h2>System Analytics</h2>
        <% Map<String, Integer> s = (Map<String, Integer>) request.getAttribute("stats"); %>
        <div class="stats-grid">
            <div class="stat-box" style="border-top-color:var(--primary)"><h1><%= s.get("total") %></h1><p>Total Submissions</p></div>
            <div class="stat-box" style="border-top-color:#10b981"><h1><%= s.get("resolved") %></h1><p>Resolved</p></div>
            <div class="stat-box" style="border-top-color:#ef4444"><h1><%= s.get("lost") %></h1><p>Pending Lost</p></div>
        </div>
        <h3 style="margin-top:50px;">Global Record Master List</h3>
        <div class="table-wrapper"><table>
            <thead><tr><th>ID</th><th>User</th><th>Item</th><th>Date</th><th>Status</th></tr></thead>
            <tbody>
            <% List<Item> list = (List<Item>) request.getAttribute("allItems");
               if(list != null) for(Item i : list) { %>
                <tr><td>#<%= i.getId() %></td><td><strong><%= i.getUsername() %></strong><br><small><%= i.getEmail() %></small></td><td><%= i.getItemName() %></td><td><%= i.getItemDate().replace("T"," ") %></td><td><span class="badge status-<%= i.getStatus() %>"><%= i.getStatus() %></span></td></tr>
            <% } %>
            </tbody>
        </table></div>
    </div></div>
    <%@ include file="footer.jsp" %>
</body></html>