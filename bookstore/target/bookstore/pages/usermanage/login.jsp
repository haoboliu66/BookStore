<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>

<!-- differentiate request.getAttribute() and request.getParameter()  -->

<%-- <span><%=request.getAttribute("msg") == null ? "Please enter your username 
and password":request.getAttribute("msg") %></span><br>--%>

<c:if test="${empty requestScope.msg }">
<span class="errorMsg">Enter username and password</span>
</c:if>

<c:if test="${not empty requestScope.msg }">
<span class="errorMsg">${requestScope.msg }</span>
</c:if>


<form action="UserServlet?method=login" method="post">
<!-- 方法一 <input type="hidden" name="method" value="login"> -->
		<label>Username</label>
        <input type="text" name="username"><br>
        <label>Password</label>
       <input type="password" name="password"><br>
        <input type="submit">
        
    </form>
    
    <a href="pages/index.jsp">HomePage</a>

</body>
</html>