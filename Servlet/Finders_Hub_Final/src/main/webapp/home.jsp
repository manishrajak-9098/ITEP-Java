<%@ include file="noCache.jsp" %>
<%@ include file="navbar.jsp" %>
<% if(session.getAttribute("email") == null) {
	response.sendRedirect("login.jsp");
	return;
	} %>
<!DOCTYPE html><html><head><title>Dashboard | FindersHub</title><link rel="stylesheet" href="css/style.css">
<script>window.history.forward();</script></head>
<body>
    <div class="container"><div class="card" style="text-align:center;">
        <div style="background:#f1f5f9;
         width:80px;
          height:80px;
           border-radius:50%;
            margin:0 auto 20px; 
            display:flex;
             align-items:center; 
             justify-content:center;
              font-size:32px;
              "></div>
        <h2>Hello, <%= session.getAttribute("username") %></h2>
        <p style="color:var(--text-muted); margin-bottom:35px;"><%= session.getAttribute("email") %></p>
        
        <div style="display:flex; flex-direction:column; gap:15px;">
            <a href="addItem.jsp"><button class="btn btn-primary" style="width:100%;">+ Report Item</button></a>
            <a href="UserHistoryServlet"><button class="btn" style="width:100%; background:white; color:var(--dark); border:1.5px solid var(--border);">My Activity History</button></a>
            <a href="ViewItemServlet"><button class="btn" style="width:100%; background:white; color:var(--dark); border:1.5px solid var(--border);">Browse Public List</button></a>
        </div>
    </div></div>
    <%@ include file="footer.jsp" %>
</body></html>