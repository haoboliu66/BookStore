<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Management</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
<%@ include file="/WEB-INF/include/header.jsp"%>




<style type="text/css">
table {
	border: 1px solid black;
}
</style>

</head>
<body>

	<div id="main">
		<c:if test="${empty requestScope.page}">
			<h2>
				<span>Welcome</span><span></span>
			</h2>
		</c:if>

		<c:if test="${not empty requestScope.page}">

			<table>
				<tr>
					<td>Title</td>
					<td>Price</td>
					<td>Author</td>
					<td>Sales</td>
					<td>Stock</td>
					<td>Operation</td>
				</tr>
				<c:forEach items="${requestScope.page.list}" var="book">
					<tr>
						<td>${book.title}</td>
						<td>${book.price}</td>
						<td>${book.author}</td>
						<td>${book.sales}</td>
						<td>${book.stock}</td>
						<!-- 通过传值book.id到Servlet,然后Servlet带着值跳转到update页面,从而update内获取了数据 -->
						<td><a class="modifya"
							href="BookServlet?method=getBookById&bookId=${book.id }">Modify</a></td>
						<td><a class="dela" id=${ book.title }
							href="BookServlet?method=delBookById&bookId=${book.id}">Delete</a></td>
					</tr>
				</c:forEach>

			</table>
			<br>
			<div id="page_nav">
				<a href="BookServlet?method=getBooksByPage&pageNo=1">First Page</a>
				<c:if test="${page.pageNo > 1 }">
					<a href="BookServlet?method=getBooksByPage&pageNo=${requestScope.page.pageNo - 1 }">Previous Page</a>
				</c:if>
				 
				<c:if test="${page.pageNo < page.totalPageNo }">
					<a href="BookServlet?method=getBooksByPage&pageNo=${requestScope.page.pageNo + 1 }">Next Page</a>
				</c:if>
				 <a href="BookServlet?method=getBooksByPage&pageNo=${requestScope.page.totalPageNo }">LastPage</a>
				 <span id="totalPageNo">totalPage ${requestScope.page.pageNo }/${requestScope.page.totalPageNo }</span>
				 <span id="totalRecord">totalRecord ${requestScope.page.totalRecord }</span>
					<input id="pn_input" value="${requestScope.page.pageNo }" name="pn"> 
					<input id="sub_page" type="button" value="Confirm"> 
					<br>
					<br>
				<a href="pages/manager/book_update.jsp">Add Books</a>
		</c:if>
	</div>










			<script type="text/javascript">
				/* alert($); */

				$(function() {

					$(".dela").click(function() {
						var title = $(this).parents("tr").children().html();
						/* var tilte = $(this).attr("id") 第二种方法,将title指定为id,并获取id即可*/
						/* alert(title); */
						if (confirm("Confirm Delete " + title + " ?") == false) {
							return false;
						}
					})
					//实现确定按钮页面跳转
					$("#sub_page").click(function(){
						//取值
						var pageNo = $("#pn_input").val();
						//跳转页面
						//location.ref 简写为 locaition,指定跳转的页面,相当于把location等值放到浏览器中
						//(可以用于请求Servlet)
						location = "BookServlet?method=getBooksByPage&pageNo=" + pageNo + "";
						/* alert(location); */
					});



				});
			</script>
</body>
</html>