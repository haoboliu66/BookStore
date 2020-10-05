<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Out</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
</head>
<body>

<%@ include file="/WEB-INF/include/welcome.jsp" %>

<div id="main">

<h1>Your orderId is <span id="orderId">${sessionScope.orderId }</span></h1>

</div>





</body>
</html>