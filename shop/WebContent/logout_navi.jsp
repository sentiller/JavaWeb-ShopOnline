<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false" import="com.shop.entity.User"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>

<style type="text/css">
a {
	font-size: 18px; color : gray;
	text_decoration: none;
	color: #CFCFCF;
	right:10px;
}

.navigate{
/* 	position:fixed; */
/* 	top:10px; */
	width: 90%;
	background-color: rgb(41, 45, 62);
	border-radius: 10px;
	margin:10px auto;
	margin-top:10px;
	padding: 20px;
	box-shadow: 1px 1px 10px #000;
 	animation: my-fade-in; /*动画名称*/  	
 	animation-duration: 0.7s; /*动画持续时间*/ 
	animation-timing-function: linear; 
	animation-fill-mode：forwards;
 	-webkit-animation: my-fade-in; /*动画名称*/  	
 	-webkit-animation-duration: 0.7s; /*动画持续时间*/ 
	-webkit-animation-timing-function: linear; 
	-webkit-animation-fill-mode：forwards;
	
}
@keyframes my-fade-in {  
    0% {
	width: 300px;
	height:420px;
	padding: 50px;
    } 
    50% {
    height:270px;
	padding: 35px;
	}
    100% {
    height:60px;
    padding: 20px;
    } 
}  
.navi-content{
	dispaly:inline;
	margin-left:10px;
	margin-rigt:70%;
	font-size:35px;
	color:white;
}

</style>

</head>
<body>

	<div class="navigate">
		<span class="navi-content"> 
		<%
		 	 HttpSession session = request.getSession(false);
			 if (session != null) {
			 	User user = (User) session.getAttribute("loginUser");
			 	out.print("welcome, " + user.getName());
			 }
		 %>
		</span>
	
		<a href="logout">[注销]</a>
	</div>

</body>
</html>