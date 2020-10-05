<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Basket</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
<script type="text/javascript">

	$(function(){
		/* delete a book from basket confirm operation */
		$(".dela").click(function(){
			var title = $(this).parents("tr").children().html();
			if (confirm("Confirm Delete " + title + " ?") == false) {
				return false;
			}		
		});
		/*  empty basket confirm operation 待完成*/
		$(".span_basket.3").click(function(){
			/* span tag可以绑定click事件 */
			if (confirm("Confirm empty your Basket ?") == false) {
				return false;
			}	
		});
		/* update the count of an item Method~1   -----> change to Ajax then */
		/* $(".basketItemCount").change(function(){
			//获取默认值
			var dValue = this.defaultValue;
			//获取bookId
			var bookId = $(this).attr("id");
			//获取用户输入值
			var count = $(this).val();
			//定义正则判断count合理性(非0 int)
			var countReg = /^\+?[1-9][0-9]*$/;
			if(!countReg.test(count)){
				alert("invalid number");
				$(this).val(dValue);
				return false;
			}
			//验证stock, count <= stock
			//因为count和stock都是string类型,为减小风险,转成int再比较
			/* alert(typeof(count)); */
		/* 	var stock = $(this).attr("name");
			if(parseInt(count) > parseInt(stock)){
				alert("Stock Left " + stock + "!");
				$(this).val(dValue);
				return false;
			}
			//pass parameters to BasketServlet
			location = "BasketServlet?method=updateBasketItemCount&bookId=" + bookId + "&count=" + count + "";
		}); */
		
		/* update the count of an item Method~2 */
		/* $(".minus").click(function(){
			var bookId = $(this).attr("name");
			var count = $(this).parents("td").html();
			alert(bookId);
			alert(count);
		});
		 */
		 
		 
		$(".basketItemCount").change(function(){
			var count = $(this).val();
			var bookId = $(this).attr("id");
			var count = $(this).val();
			//定义正则判断count合理性(非0 int)
			var countReg = /^\+?[1-9][0-9]*$/;
			if(!countReg.test(count)){
				alert("invalid number");
				$(this).val(dValue);
				return false;
			}
			//验证stock, count <= stock
			//因为count和stock都是string类型,为减小风险,转成int再比较
			/* alert(typeof(count)); */
			var stock = $(this).attr("name");
			if(parseInt(count) > parseInt(stock)){
				alert("Stock Left " + stock + "!");
				$(this).val(dValue);
				return false;
			}
			
			$.getJSON("BasketServlet?method=updateBasketItemCount", {"count":count, "bookId": bookId}, function(msg){
				var amount = msg.amount;
				var totalCount = msg.totalCount;
				var totalAmount = msg.totalAmount;
				
				$(".basket_count").html(totalCount);
				$(".basket_amount").html(totalAmount);
				$(".basketItemCount").parent().next().next().html(amount);
			});
		});
		 
		 
		 
		 
		 
		 
		 
		 
	});
</script>
</head>
<body>
<!-- #nav bar included in welcome.jsp -->
<%@ include file="/WEB-INF/include/welcome.jsp" %>

<!-- #main: basket info -->
<c:if test="${empty sessionScope.basket.basketItems}">
<span>Nothing in your Basket, <a href="pages/index.jsp">go shopping</a></span>
</c:if>
<c:if test="${not empty sessionScope.basket.basketItems}">
	<table>
		<tr>
			<td>Title</td>
			<td>Count</td>
			<td>Price</td>
			<td>Sum</td>
			<td>Operation</td>
		</tr>
		<!-- EL直接去bean中get后的属性,是Basket中map的values -->
		<c:forEach items="${sessionScope.basket.basketItems}" var="basketItem">
			<tr>
				<td>${basketItem.book.title}</td>
				<td><input id="${basketItem.book.id }" class="basketItemCount" type="text" name="${basketItem.book.stock }" value="${basketItem.count}" size="3" ></td>
				<!-- <td>
				
				<button class="minus" type="button" name="minus${basketItem.book.id }">-</button> 
					${basketItem.count} 
				<button class="plus" type="button" name="plus${basketItem.book.id }">+</button>
				
				</td> -->
				
				<td>${basketItem.book.price}</td>
				<td>${basketItem.amount}</td>
				<td><a class="dela"
					href="BasketServlet?method=delBasketItem&bookId=${basketItem.book.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<div class="basket_info">
	<span class="span_basket"><span class="basket_count">${sessionScope.basket.totalCount }</span> items in your Basket</span>
		<span class="span_basket"><span class="basket_amount">${sessionScope.basket.totalAmount }</span> $ in total</span>
		<span class="span_basket"><a href="BasketServlet?method=emptyBasket">Empty Basket</a></span>
		<span class="span_basket"><a href="OrderServlet?method=checkout&">CheckOut</a></span>
		<span class="span_basket"><a href="pages/index.jsp">Continue Shopping</a></span>
</div>
</c:if>


</body>
</html>