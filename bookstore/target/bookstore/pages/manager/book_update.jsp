<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Update</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
</head>
<body>

<form action="BookServlet?method=updateBook" method="post">
<input type="hidden" name="bookId" value="${book.id }">
<table>

<tr>
<td>Title</td>
<td>Price</td>
<td>Author</td>
<td>Sales</td>
<td>Stock</td>
</tr>
<tr>
<td><input type="text" name="title" value="${book.title }"></td>
<td><input type="text" name="price" value="${book.price }"></td>
<td><input type="text" name="author" value="${book.author }"></td>
<td><input type="text" name="sales" value="${book.sales }"></td>
<td><input type="text" name="stock" value="${book.stock }"></td>
</tr>
</table>
<input type="submit" value="submit">
</form>


</body>
</html>