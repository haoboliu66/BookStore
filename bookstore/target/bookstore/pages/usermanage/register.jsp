<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Register Page</title>
    <%@ include file="/WEB-INF/include/base.jsp" %>
    
    <style>
    	.errorMsg{
    	color:red;
    	}
    
    </style>
    
    <script type="text/javascript">
    	$(function(){
    		$("#codeImg").click(function(){
    			$(this).attr("src","KaptchaServlet");
    		});
    		
    		//ajax校验用户名存在问题
    		$("#username").change(function(){
    			var uname = $(this).val();
    			$.get("UserServlet?method=checkUserName",{"uname":uname},function(msg){
    				if($.trim(msg) == "true"){
    					$(".errorMsg").html("username already exists").css("color","red");
    				}else{
    					$(".errorMsg").html("username okay").css("color","green");
    				}
    			})
    		});
    		
    		
    		
    		
    		
    	});
    </script>
</head>

<body>

<span class="errorMsg"><%=request.getAttribute("msg") == null? "":request.getAttribute("msg") %></span><br>
    <form action="UserServlet?method=register" method="post">
		<!--方法一 <input type="hidden" name="method" value="register"> -->
		<label>Username</label> 
        <input id="username" value="${param.username}"  type="text" placeholder="Enter your Username" name="username"> <br> 
        <label>Password</label>
        <input type="password" placeholder="Enter your Password" name="password"><br> 
        <label>Confirm Password</label>
         <input type="password" name="confirm"><br> 
        <label>Email</label>
       <%--  <input value="<%=request.getParameter("email") == null? "": request.getParameter("email") %>" type="text" name="email"><br> --%>
        <%-- <input value="${param.email}" type="text" name="email"><br> --%>
        <input value="${param.email }" type="text" name="email"><br>
        <label>VerifyCode</label>
        <input type="text" name="code"><br>
        
        <img id="codeImg" src="KaptchaServlet"><!-- 请求KaptchaServlet实现验证码图片随机生成 -->
        <input type="submit">
    </form>
   
    
<a href="pages/index.jsp">HomePage</a>
</body>

</html>