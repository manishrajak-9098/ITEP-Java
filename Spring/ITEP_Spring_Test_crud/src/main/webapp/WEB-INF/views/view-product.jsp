<%@ page import="java.util.*, com.spring.entity.Product" %>

<html>
<head><title>View Products</title></head>
<body>

<h2>All Products</h2>

<table border="1">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Category</th>
    <th>Description</th>
</tr>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");

    for(Product p : products) {
%>
<tr>
    <td><%= p.getId() %></td>
    <td><%= p.getProductName() %></td>
    <td><%= p.getCategory() %></td>
    <td><%= p.getDescription() %></td>
</tr>
<%
    }
%>

</table>

<a href="${pageContext.request.contextPath}/">Back</a>

</body>
</html>
