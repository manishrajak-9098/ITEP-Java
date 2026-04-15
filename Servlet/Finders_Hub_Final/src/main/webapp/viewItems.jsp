<%@ page import="java.util.*, com.project.Item" %>
<%@ include file="navbar.jsp" %>

<!-- Security Check: Agar login nahi hai to login page par bhejo -->
<% 
    if(session.getAttribute("email") == null) { 
        response.sendRedirect("login.jsp"); 
        return; 
    } 
%>

<!DOCTYPE html>
<html>
<head>
    <title>Browse Reports | FindersHub</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        /* Popup Styling */
        .popup-overlay { display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.5); z-index:1500; }
        .popup-box { display:none; position:fixed; top:50%; left:50%; transform:translate(-50%,-50%); background:white; padding:30px; border-radius:12px; width:400px; z-index:2000; box-shadow:0 10px 25px rgba(0,0,0,0.2); }
    </style>
</head>
<body>

    <!-- Popup UI (Hidden by default) -->
    <div id="overlay" class="popup-overlay" onclick="closePopup()"></div>
    <div id="popup" class="popup-box">
        <h3 style="margin-top:0;">Claim / Resolve Item</h3>
        <p style="font-size:13px; color:#64748b; margin-bottom:20px;">
            Are you the owner, or have you found this item?
        </p>
        
        <form action="ResolveItemServlet" method="post">
            <input type="hidden" name="id" id="itemIdInput">
            
            <!-- Auto-filled user details (Editable) -->
            <label>Your Name</label>
            <input type="text" name="finderName" value="<%= session.getAttribute("username") %>" required>
            
            <label>Your Email</label>
            <input type="email" name="finderEmail" value="<%= session.getAttribute("email") %>" readonly style="background:#f1f5f9;">
            
            <button type="submit" class="btn btn-primary" style="width:100%; margin-top:15px;">Mark as Resolved</button>
            <button type="button" class="btn" style="width:100%; margin-top:10px; background:#e2e8f0; color:black;" onclick="closePopup()">Cancel</button>
        </form>
    </div>

    <!-- Main Content -->
    <div class="container">
        <div class="wide-card">
            <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:20px;">
                <h2>Public Lost & Found List</h2>
                <a href="addItem.jsp"><button class="btn btn-primary">+ Report New Item</button></a>
            </div>

            <!-- Message Show karna -->
            <% String msg = (String) session.getAttribute("msg"); if(msg != null) { %>
                <div style="background:#dcfce7; color:#166534; padding:10px; border-radius:6px; margin-bottom:15px;"> <%= msg %> </div>
            <% session.removeAttribute("msg"); } %>

            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>Item Name</th>
                            <th>Description</th>
                            <th>Location</th>
                            <th>Contact</th>
                            <th>Status / Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% 
                       List<Item> list = (List<Item>) request.getAttribute("allItems");
                       String currentUserEmail = (String) session.getAttribute("email");

                       if(list != null && !list.isEmpty()) { 
                           for(Item i : list) { 
                    %>
                        <tr>
                            <td>
                                <strong><%= i.getItemName() %></strong><br>
                                <small style="color:#94a3b8"><%= i.getItemDate() %></small>
                            </td>
                            <td><%= i.getDescription() %></td>
                            <td><%= i.getLocation() %></td>
                            <td><%= i.getContact() %></td>
                            <td>
                                <% if("Resolved".equalsIgnoreCase(i.getStatus())) { %>
                                    <!-- Agar Resolved hai to Green Text -->
                                    <span style="color:#10b981; font-weight:bold;">Resolved</span>
                                    <br><small>by <%= i.getFinderName() %></small>
                                <% } else { %>
                                    <!-- Agar Pending hai, to Resolve button dikhao -->
                                    <!-- Logic: Agar ye item MERA nahi hai, tabhi main resolve kar sakta hu (Optional logic) -->
                                    
                                    <button onclick="openResolve(<%= i.getId() %>)" 
                                            class="btn" 
                                            style="padding:5px 10px; font-size:12px; background:#3b82f6; color:white;">
                                        Click to Resolve
                                    </button>
                                <% } %>
                            </td>
                        </tr>
                    <% 
                           } 
                       } else { 
                    %> 
                        <tr><td colspan="5" style="text-align:center;">No items reported yet.</td></tr> 
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <script>
        function openResolve(id) {
            document.getElementById('itemIdInput').value = id;
            document.getElementById('popup').style.display = 'block';
            document.getElementById('overlay').style.display = 'block';
        }

        function closePopup() {
            document.getElementById('popup').style.display = 'none';
            document.getElementById('overlay').style.display = 'none';
        }
    </script>
    
    <%@ include file="footer.jsp" %>
</body>
</html>