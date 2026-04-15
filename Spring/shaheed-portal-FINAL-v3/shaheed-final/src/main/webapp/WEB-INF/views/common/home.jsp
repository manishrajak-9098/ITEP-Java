<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="hi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shaheed Parivar Sahayata Portal | Government of India</title>
    <style>
        * { margin:0; padding:0; box-sizing:border-box; }
        body { font-family:'Segoe UI',Arial,sans-serif; background:#fff; color:#333; }

        /* NAV */
        nav {
            background:linear-gradient(135deg,#1a237e,#283593);
            padding:12px 40px; display:flex; align-items:center; justify-content:space-between;
            box-shadow:0 2px 12px rgba(0,0,0,0.2);
        }
        .nav-brand { display:flex; align-items:center; gap:12px; color:white; }
        .nav-brand .flag { font-size:30px; }
        .nav-brand h1  { font-size:16px; line-height:1.4; }
        .nav-brand small { font-size:11px; opacity:0.75; display:block; }
        .nav-links { display:flex; gap:8px; align-items:center; }
        .nav-links a {
            color:rgba(255,255,255,0.85); text-decoration:none; padding:8px 16px;
            border-radius:6px; font-size:13px; font-weight:600; transition:background 0.2s;
        }
        .nav-links a:hover { background:rgba(255,255,255,0.15); color:white; }
        .btn-login-nav { background:rgba(255,255,255,0.2) !important; color:white !important; }

        /* SAFFRON */
        .saffron { height:4px; background:linear-gradient(to right,#FF9933,#FFFFFF,#138808); }

        /* HERO */
        .hero {
            background:linear-gradient(135deg,#1a237e 0%,#283593 50%,#1b5e20 100%);
            color:white; padding:70px 40px; text-align:center; position:relative; overflow:hidden;
        }
        .hero::before {
            content:'🇮🇳'; position:absolute; font-size:300px; opacity:0.04;
            top:50%; left:50%; transform:translate(-50%,-50%);
        }
        .hero h2  { font-size:36px; font-weight:800; margin-bottom:16px; position:relative; }
        .hero p   { font-size:16px; opacity:0.85; max-width:600px; margin:0 auto 30px; position:relative; line-height:1.7; }
        .hero-btns { display:flex; gap:14px; justify-content:center; flex-wrap:wrap; position:relative; }
        .btn-hero-primary {
            padding:14px 30px; background:white; color:#1a237e;
            border-radius:8px; text-decoration:none; font-size:15px;
            font-weight:700; transition:transform 0.2s;
        }
        .btn-hero-primary:hover { transform:translateY(-2px); }
        .btn-hero-outline {
            padding:14px 30px; background:transparent; color:white;
            border:2px solid rgba(255,255,255,0.7); border-radius:8px;
            text-decoration:none; font-size:15px; font-weight:600; transition:background 0.2s;
        }
        .btn-hero-outline:hover { background:rgba(255,255,255,0.15); }

        /* FEATURES */
        .features { padding:60px 40px; background:#f9f9f9; }
        .features h3 { text-align:center; font-size:24px; color:#1a237e; margin-bottom:35px; }
        .feature-grid { display:grid; grid-template-columns:repeat(3,1fr); gap:22px; max-width:900px; margin:0 auto; }
        .feature-card {
            background:white; border-radius:12px; padding:25px; text-align:center;
            box-shadow:0 3px 12px rgba(0,0,0,0.07); border-top:4px solid transparent;
            transition:transform 0.2s;
        }
        .feature-card:hover { transform:translateY(-4px); }
        .feature-card.f1 { border-top-color:#1a237e; }
        .feature-card.f2 { border-top-color:#2e7d32; }
        .feature-card.f3 { border-top-color:#e65100; }
        .feature-card.f4 { border-top-color:#1565c0; }
        .feature-card.f5 { border-top-color:#6a1b9a; }
        .feature-card.f6 { border-top-color:#c62828; }
        .feature-card .ficon { font-size:38px; margin-bottom:12px; display:block; }
        .feature-card h4 { font-size:14px; color:#1a237e; font-weight:700; margin-bottom:8px; }
        .feature-card p  { font-size:12px; color:#666; line-height:1.6; }

        /* TRACK SECTION */
        .track-section { padding:50px 40px; background:white; }
        .track-box { max-width:600px; margin:0 auto; text-align:center; }
        .track-box h3  { font-size:22px; color:#1a237e; margin-bottom:10px; }
        .track-box p   { font-size:14px; color:#666; margin-bottom:22px; }
        .track-form    { display:flex; gap:10px; }
        .track-form input {
            flex:1; padding:13px 16px; border:2px solid #e0e0e0;
            border-radius:8px; font-size:14px; outline:none;
        }
        .track-form input:focus { border-color:#1a237e; }
        .track-form button {
            padding:13px 24px; background:linear-gradient(135deg,#1a237e,#283593);
            color:white; border:none; border-radius:8px; font-size:14px;
            font-weight:700; cursor:pointer;
        }

        /* ROLES */
        .roles { padding:50px 40px; background:#f0f2f5; }
        .roles h3 { text-align:center; font-size:22px; color:#1a237e; margin-bottom:30px; }
        .roles-grid { display:flex; gap:20px; max-width:800px; margin:0 auto; flex-wrap:wrap; justify-content:center; }
        .role-card {
            background:white; border-radius:10px; padding:22px 26px; flex:1; min-width:200px;
            box-shadow:0 2px 10px rgba(0,0,0,0.08); text-align:center;
        }
        .role-card .ricon { font-size:36px; display:block; margin-bottom:8px; }
        .role-card h4 { font-size:14px; font-weight:700; color:#1a237e; margin-bottom:8px; }
        .role-card p  { font-size:12px; color:#666; line-height:1.6; }

        /* FOOTER */
        footer {
            background:linear-gradient(135deg,#1a237e,#283593); color:white;
            padding:25px 40px; text-align:center; font-size:12px;
        }
        footer p { opacity:0.8; margin-bottom:5px; }
        footer strong { opacity:1; }

        @media(max-width:768px){
            .feature-grid { grid-template-columns:repeat(2,1fr); }
            .hero h2 { font-size:24px; }
            .track-form { flex-direction:column; }
        }
    </style>
</head>
<body>

<nav>
    <div class="nav-brand">
        <div class="flag">🇮🇳</div>
        <div>
            <h1>Shaheed Parivar Sahayata Portal</h1>
            <small>Government of India | Ministry of Defence</small>
        </div>
    </div>
    <div class="nav-links">
        <a href="#track">Case Track</a>
        <a href="#features">Features</a>
        <a href="tel:1800-XXX-XXXX">📞 Helpline</a>
        <a href="${pageContext.request.contextPath}/login" class="btn-login-nav">Login →</a>
    </div>
</nav>
<div class="saffron"></div>

<!-- HERO -->
<div class="hero">
    <h2>🎖️ Shaheed Ke Parivaar Ki Seva<br>Hamaara Sankalp</h2>
    <p>Bharat ke shaheed sainikon ke parivaar ke liye ek safe, transparent aur digital welfare portal.
       Saare government benefits ek jagah — ek click mein track karein.</p>
    <div class="hero-btns">
        <a href="${pageContext.request.contextPath}/login" class="btn-hero-primary">Login Karein →</a>
        <a href="#track" class="btn-hero-outline">🔍 Case Track Karein</a>
    </div>
</div>
<div class="saffron"></div>

<!-- TRACK SECTION -->
<div class="track-section" id="track">
    <div class="track-box">
        <h3>🔍 Application ID se Case Track Karein</h3>
        <p>Login ki zarurat nahi. Apna Application ID (e.g., SP-2024-00001) daalen aur case ka status dekhein.</p>
        <form class="track-form" action="${pageContext.request.contextPath}/family/track/search" method="POST">
            <input type="text" name="applicationId" placeholder="Application ID daalen — SP-2024-XXXXX" required>
            <button type="submit">Track 🔍</button>
        </form>
    </div>
</div>

<!-- FEATURES -->
<div class="features" id="features">
    <h3>🌟 Portal Ki Visheshatayen</h3>
    <div class="feature-grid">
        <div class="feature-card f1">
            <span class="ficon">📊</span>
            <h4>Real-time Case Tracking</h4>
            <p>Application ID se case ka live status dekhein bina office ke chakkar lagaye.</p>
        </div>
        <div class="feature-card f2">
            <span class="ficon">💰</span>
            <h4>Benefits Dashboard</h4>
            <p>Ex-gratia, pension, education help — sab ek jagah track karein.</p>
        </div>
        <div class="feature-card f3">
            <span class="ficon">📄</span>
            <h4>Digital Document Upload</h4>
            <p>Ghar baith ke documents upload karein. Officer online verify karega.</p>
        </div>
        <div class="feature-card f4">
            <span class="ficon">🔐</span>
            <h4>Secure Role-based Access</h4>
            <p>ADMIN, Officer, Family — teen alag roles, poori security ke saath.</p>
        </div>
        <div class="feature-card f5">
            <span class="ficon">📨</span>
            <h4>Grievance System</h4>
            <p>Koi bhi problem ho — complaint darj karein, 24 ghante mein jawab paayein.</p>
        </div>
        <div class="feature-card f6">
            <span class="ficon">📋</span>
            <h4>Government Reports</h4>
            <p>Admin ke liye poori reporting — transparent welfare management.</p>
        </div>
    </div>
</div>

<!-- ROLES -->
<div class="roles">
    <h3>👥 Kaun Kaun Use Kar Sakta Hai?</h3>
    <div class="roles-grid">
        <div class="role-card">
            <span class="ricon">⚙️</span>
            <h4>Admin</h4>
            <p>System admin: Soldiers register karo, officers create karo, benefits manage karo.</p>
        </div>
        <div class="role-card">
            <span class="ricon">👮</span>
            <h4>Officer</h4>
            <p>Document verify karo, case status update karo, grievances resolve karo.</p>
        </div>
        <div class="role-card">
            <span class="ricon">👨‍👩‍👦</span>
            <h4>Shaheed Parivar</h4>
            <p>Benefits track karo, documents upload karo, complaints darj karo.</p>
        </div>
    </div>
</div>

<footer>
    <p><strong>Shaheed Parivar Sahayata Portal</strong></p>
    <p>© 2024 Ministry of Defence, Government of India</p>
    <p style="margin-top:8px;">📞 Helpline: 1800-XXX-XXXX (Toll Free, 24×7) &nbsp;|&nbsp; 📧 support@shaheedportal.gov.in</p>
</footer>

</body>
</html>
