<%@ include file="noCache.jsp" %>
<%@ include file="navbar.jsp" %>
<% if(session.getAttribute("email") == null) { response.sendRedirect("login.jsp"); return; } %>
<!DOCTYPE html><html><head><title>Report Item | FindersHub</title><link rel="stylesheet" href="css/style.css"></head>
<body>
    <div class="container"><div class="card">
        <h2 style="text-align:center;">Post New Report</h2>
        <p style="text-align:center; color:var(--text-muted); margin-bottom:30px;">Fill in item details</p>
        <form action="AddItemServlet" method="post">
            <label>Item Name</label><input type="text" name="itemName" placeholder="e.g. Black Wallet" required>
            <label>Location</label><input type="text" name="location" placeholder="e.g. City Park" required>
            <label>Date & Time</label><input type="datetime-local" name="itemDate" required>
            <label>Contact Info</label><input type="tel" name="contact" pattern="[0-9]{10}" placeholder="Phone Number" required>
            <label>Status</label>
            <select name="status"><option value="Lost">I Lost this</option><option value="Found">I Found this</option></select>
            <label>Description</label><textarea name="description" rows="3" placeholder="Color, brand, etc." required></textarea>
            <button type="submit" class="btn btn-primary" style="width:100%;">Submit Report</button>
        </form>
    </div></div>
    <%@ include file="footer.jsp" %>
</body></html>