<!DOCTYPE html>
<html>
<head>
    <title>Contact Us | Zero Hunger</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="navbar">
        <a href="index.jsp" class="logo-container">
            <img src="https://cdn-icons-png.flaticon.com/512/2921/2921822.png">
            <h2>Zero Hunger</h2>
        </a>
        <a href="index.jsp" class="btn btn-outline" style="padding:5px 15px;">Home</a>
    </div>

    <div class="main-content">
        <div class="contact-wrapper">
            <div class="contact-info">
                <h2>Get in Touch</h2>
                <p>Have any questions? We would love to hear from you.</p>
                <div style="margin-top:40px;">
                    <p> Mumbai, India</p>
                    <p> +91 98765 43210</p>
                    <p> support@zerohunger.org</p>
                </div>
            </div>
            <div class="contact-form">
                <h2>Message Us</h2>
                <% if(request.getParameter("msg")!=null){ %><p class="success">Message Sent Successfully!</p><% } %>
                <form action="ContactServlet" method="post">
                    <input type="text" name="name" placeholder="Full Name" required>
                    <input type="email" name="email" placeholder="Email Address" required>
                    <textarea name="message" placeholder="How can we help?" rows="4" required></textarea>
                    <button type="submit" class="btn btn-orange" style="width:100%">Send Message</button>
                </form>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>