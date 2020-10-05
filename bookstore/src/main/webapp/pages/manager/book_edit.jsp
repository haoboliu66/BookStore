<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Edit</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<%@ include file="/WEB-INF/include/header.jsp" %>
</head>
<body>

<form action="BookServlet?method=updateBook" method="post">
<table>

<tr>
<td>Title</td>
<td>Price</td>
<td>Author</td>
<td>Sales</td>
<td>Stock</td>
</tr>
<tr>
<td><input type="text" name="title" value=""></td>
<td><input type="text" name="price" value=""></td>
<td><input type="text" name="author" value=""></td>
<td><input type="text" name="sales" value=""></td>
<td><input type="text" name="stock" value=""></td>
</tr>
</table>
<input type="submit" value="submit">
</form>

</body>
</html>