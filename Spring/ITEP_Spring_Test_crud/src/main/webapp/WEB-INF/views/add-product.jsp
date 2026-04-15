
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head><title>Add Product</title></head>
<body>
<h2>Add Product</h2>

<form:form action="save" modelAttribute="product" method="post">
Product Name: <form:input path="productName" /> <br><br>
Category: <form:input path="category" /> <br><br>
Description: <form:textarea path="description" /> <br><br>
<input type="submit" value="Save">
</form:form>


</body>
</html>