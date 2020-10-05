<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Store</title>

    <%@ include file="/WEB-INF/include/base.jsp"%>
<%--    <%@ include file="/WEB-INF/include/header.jsp" %>--%>
    <!-- <link rel="stylesheet" type="text/css" href="static/css/books.css"> -->

    <style type="text/css">

        .book_img {

            width: 200px;
            height: 200px;
        }

        .b_list {
            float: left;
        }
    </style>
    <%@ include file="/WEB-INF/include/welcome.jsp" %>

</head>
<body>


<div class="book_cond">
    Price<input type="text" name="min" value="${param.min }">To<input type="text" name="max" value="${param.max }">
    <button>Go</button>
</div>
<br>
<%-- requestScope.page.list is ${requestScope.page.list }<br><br>
requestScope page is ${requestScope.page }<br><br>
requestScope is ${requestScope }<br><br> --%>
<%-- sessionScope.basket is ${sessionScope.basket}<br><br>
sessionScope.title ${sessionScope.title }<br><br> --%>

<div>
    <c:if test="${not empty sessionScope.basket.totalCount && sessionScope.basket.totalCount != 0 }">
        <span>${sessionScope.basket.totalCount }items in your basket</span>
    </c:if>
</div>

<div>
    <c:if test="${not empty sessionScope.title}">
        <span> ${sessionScope.title } added to your basket</span>
    </c:if>
    <c:if test="${not empty sessionScope.msg }">
        <span> ${sessionScope.msg }</span>
    </c:if>
    <c:remove var="msg"/>  <!--每次取完title数据后,将其从session域中移除  -->
</div>

<c:forEach items="${requestScope.page.list }" var="book">
    <div class="b_list">
        <div class="img_div">
            <img class="book_img" src="static/img/default.jpg" alt="">
        </div>
        <div class="book_info">
            <div class="book_title">
                <span class="sp1">Title:</span> <span class="sp2">${book.title }</span>
            </div>
            <div class="book_author">
                <span class="sp1">Author:</span> <span class="sp2">${book.author }</span>
            </div>
            <div class="book_price">
                <span class="sp1">Price:</span> <span class="sp2">${book.price }</span>
            </div>
            <div class="book_sale">
                <span class="sp1">Sales:</span> <span class="sp2">${book.sales }</span>
            </div>
            <div class="book_stock">
                <span class="sp1">Stock:</span> <span class="sp2">${book.stock }</span>
            </div>

            <div class="book_add">
                <button id="${book.id }">Add to Basket</button>
            </div>
        </div>
    </div>
</c:forEach>

<div id="page_nav">
    <c:choose>
        <c:when test="${page.totalPageNo < 5 }"> <!--如果总页数小于5的情况 -->
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${page.totalPageNo }"></c:set>
        </c:when>
        <c:when test="${page.pageNo <= 3 }"> <!--如果当前页<=3的情况 12[3]45-->
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="5"></c:set>
        </c:when><c:when test="${page.pageNo > 3 && page.pageNo <= page.totalPageNo - 2 }">
        <!--如果当前pageNo >3 && pageNo <= totalPageNo - 2的情况 23[4]56, 34[5]67, 45[6]78-->
        <c:set var="begin" value="${page.pageNo - 2 }"></c:set>
        <c:set var="end" value="${page.pageNo + 2 }"></c:set>
    </c:when>
        <c:otherwise> <!-- 如果当前pageNo + 2 > totalPageNo了 456[7]8 -->
            <c:set var="begin" value="${page.totalPageNo - page.PAGE_SIZE }"></c:set>
            <c:set var="end" value="${page.totalPageNo }"></c:set>
        </c:otherwise>
    </c:choose>

    <c:forEach var="i" begin="${begin }" end="${end }" step="1">
        <c:if test="${page.pageNo == i }">
            [${i }]
        </c:if>
        <c:if test="${page.pageNo != i }">
            <a href="BookClientServlet?method=getBooksByPageAndPrice&pageNo=${i}&min=${param.min}&max=${param.max}">${i }</a>
        </c:if>
    </c:forEach>

    <span id="totalPageNo">Page ${requestScope.page.pageNo }/${requestScope.page.totalPageNo }</span>
    <span id="totalRecord">${requestScope.page.totalRecord } Records
			</span>
    <input id="pn_input"
           value="${requestScope.page.pageNo }" name="pn">
    <input
            id="sub_page" type="button" value="Confirm"> <br>

</div>







<script type="text/javascript">
    $(function () {
        //实现确定按钮页面跳转
        $("#sub_page").click(function () {
            //取值
            var pageNo = $("#pn_input").val();
            //跳转页面
            //location.ref 简写为 locaition,指定跳转的页面,相当于把location等值放到浏览器中
            //(可以用于请求Servlet)
            location = "BookClientServlet?method=getBooksByPage&pageNo=" + pageNo + "";
            /* alert(location); */
        });

        //锁定查询button
        $(".book_cond button").click(function () {
            //取值pageNo, min, max
            var pageNo = $("#pn_input").val();
            var min = $("input[name='min']").val();
            var max = $("input[name='max']").val();

            //请求BookClientServlet
            location = "BookClientServlet?method=getBooksByPageAndPrice&pageNo=" + pageNo
                + "&min=" + min + "&max=" + max + "";
        });

        $(".book_add button").click(function () {
            //取值bookId, 通过给button tag添加id为bookId,用this来取值
            var bookId = $(this).attr("id");
            /* alert(bookId); */
            var pageNo = $("#pn_input").val();
            /* var min = $("input[name='min']").val();
            var max = $("input[name='max']").val(); */
            /* alert(currenPageNo);
            alert(min);
            alert(max); */

            //请求BasketServlet
            /* location = "BasketServlet?method=addBookToBasket&bookId=" + bookId + "&pageNo=" + pageNo +
                    "&min=" + min + "&max=" + max + ""; */
            //带数据重新请求的方式用获取Referer的方法替代了
            location = "BasketServlet?method=addBookToBasket&bookId=" + bookId + "";
        });

    });
</script>

</body>
</html>