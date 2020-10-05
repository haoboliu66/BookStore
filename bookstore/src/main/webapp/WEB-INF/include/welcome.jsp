<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/include/base.jsp" %>

<c:if test="${not empty sessionScope.user}">
    <!-- 如果登录取到了session中的user信息 -->
    <ul class="nav">
        <li class="nav-item">
            <a href="pages/index.jsp">Home</a>
        </li>
        <li class="nav-item">
            <span>Welcome</span>&nbsp;<span>${sessionScope.user.userName}</span>
        </li>
        <li class="nav-item">
            <a href="pages/order/order.jsp">My Order</a>
        </li>
        <li class="nav-item">
            <a href="pages/basket/basket.jsp">Basket</a>
        </li>
        <li class="nav-item">
            <a href="UserServlet?method=logout">LogOut</a>
        </li>
        <li>
            <a href="pages/manager/manager.jsp">Manage</a>
        </li>
    </ul>

    <%--    <ul>	--%>
    <%--	<li><a href="pages/index.jsp">Home</a></li>--%>
    <%--    <li><span>Welcome</span>&nbsp;<span>${sessionScope.user.userName}</span></li>--%>
    <%--    <li><a href="pages/order/order.jsp">My Order</a></li>--%>
    <%--    <li><a href="pages/basket/basket.jsp">Basket</a></li>--%>
    <%--    <li><a href="UserServlet?method=logout">LogOut</a></li>--%>
    <%--    <li><a href="pages/manager/manager.jsp">Manage</a></li>--%>
    <%--    </ul>--%>

</c:if>


<c:if test="${empty sessionScope.user}">
    <!-- 如果没有取到session中的user信息 -->
    <ul class="nav">
        <li class="nav-item">
            <a href="pages/index.jsp">Home</a>
        </li>
        <li class="nav-item">
            <a href="pages/usermanage/login.jsp">Login</a>
        </li>
        <li class="nav-item">
            <a href="pages/usermanage/register.jsp">Register</a>
        </li>
        <li class="nav-item">
            <a href="pages/basket/basket.jsp">Basket</a>
        </li>
        <li>
            <a href="pages/manager/manager.jsp">Manage</a>
        </li>
    </ul>

    <%--     <ul>--%>
    <%--     <li><a href="pages/index.jsp">Home</a></li>--%>
    <%--    <li><a href="pages/usermanage/login.jsp">Login</a></li>--%>
    <%--    <li><a href="pages/usermanage/register.jsp">Register</a></li>--%>
    <%--    <li><a href="pages/basket/basket.jsp">Basket</a></li>--%>
    <%--    <li><a href="pages/manager/manager.jsp">Manage</a></li>--%>
    <%--    </ul>--%>
</c:if>