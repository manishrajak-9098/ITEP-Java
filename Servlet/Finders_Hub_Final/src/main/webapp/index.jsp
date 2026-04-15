<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>FindersHub | Recover What Matters</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="hero">
        <!-- Main Impactful Heading -->
        <h1>Lost it? Found it? <br><span>We'll Help You Reconnect.</span></h1>
        
        <!-- Subtitle related to Lost & Found -->
        <p>The smartest community-driven platform to report lost belongings or return found items. Help someone today and make our community more honest and connected.</p>
        
        <div style="display:flex; justify-content:center; gap:15px; margin-top:30px;">
            <% if (navUser == null) { %>
                <a href="login.jsp" style="text-decoration:none;">
                    <button class="btn btn-primary" style="padding:15px 45px; font-size:16px; background:white; color:var(--primary);">Report Lost Item</button>
                </a>
                <a href="register.jsp" style="text-decoration:none;">
                    <button class="btn" style="background:rgba(255,255,255,0.1); border:1px solid white; padding:15px 45px; font-size:16px;">I Found Something</button>
                </a>
            <% } else { %>
                <a href="addItem.jsp" style="text-decoration:none;">
                    <button class="btn btn-primary" style="padding:15px 45px; font-size:16px; background:white; color:var(--primary);">Post a New Report</button>
                </a>
            <% } %>
        </div>
    </div>

    <div class="container" style="text-align:center;">
        <h2 style="margin-bottom:10px; font-size:34px; color:var(--dark);">How FindersHub Works</h2>
        <p style="color:var(--text-muted); margin-bottom:50px;">Three simple steps to recover your belongings.</p>
        
        <div class="stats-grid">
            <!-- Box 1: Report -->
            <div class="stat-box">
                <div style="font-size:40px; margin-bottom:15px;"></div>
                <h3 style="color:var(--primary)">1. Post a Report</h3>
                <p style="color:var(--text-muted); margin-top:10px;">Lost your wallet? Found a phone? Upload details, location, and a description in seconds.</p>
            </div>
            
            <!-- Box 2: Connection -->
            <div class="stat-box" style="border-top-color:var(--secondary)">
                <div style="font-size:40px; margin-bottom:15px;"></div>
                <h3 style="color:var(--secondary)">2. Smart Matching</h3>
                <p style="color:var(--text-muted); margin-top:10px;">Our moderators analyze reports. If a lost item matches a found one, we initiate the connection.</p>
            </div>
            
            <!-- Box 3: Return -->
            <div class="stat-box" style="border-top-color:var(--dark)">
                <div style="font-size:40px; margin-bottom:15px;"></div>
                <h3 style="color:var(--dark)">3. Safe Recovery</h3>
                <p style="color:var(--text-muted); margin-top:10px;">Verify ownership through secure communication and coordinate a safe return of the item.</p>
            </div>
        </div>
    </div>

    <!-- Small Community Impact Section -->
    <div style="background: white; padding: 60px 0; border-top: 1px solid var(--border);">
        <div class="container" style="text-align:center;">
            <h2 style="color:var(--dark); margin-bottom:15px;">Join a Trusted Community</h2>
            <p style="color:var(--text-muted); max-width:600px; margin:0 auto 30px;">Every report helps. By using FindersHub, you're helping build a world where lost items always find their way back home.</p>
            <a href="about.jsp" style="color:var(--primary); font-weight:700; text-decoration:none;">Learn more about our mission &rarr;</a>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>