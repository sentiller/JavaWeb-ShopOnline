<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
    <%@page import="com.shop.dbclass.DataGoods, com.shop.entity.Goods"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cart</title>
<style type="text/css">
	* {
		padding: 0;
		margin: 0;
	}
	
	html {
		height: 100%;
	}
	
	body {
		height: 100%;
		font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
		align-items: center;
		justify-content: center;
		background-color: #0e92b3;
	}
	
	.cart-wrapper{
		padding:10px;
		width:95%;
		margin:5px auto;
		opacity:0;
		background-color:#0099CC;
		animation: fade-in ease-in; /*动画名称*/
		animation-duration: 1.5s; /*动画持续时间*/
		animation-delay:1s;
		animation-fill-mode：forwards;
		-webkit-animation: fade-in ease-in 1.5s; /*针对webkit内核*/
		-webkit-animation-delay:1s;
		-webkit-animation-fill-mode:forwards;
	}	
			
	@keyframes fade-in {  
	    0% {opacity: 0;} 
	    20% {opacity:0.4}
	    40% {opacity: 0.7}  
	    60% {opacity:0.9}
	    100% {opacity: 1;} 
	}  
	@-webkit-keyframes fade-in {/*针对webkit内核*/  
	    0% {opacity: 0;} 
	    20% {opacity:0.4}
	    40% {opacity: 0.7}  
	    60% {opacity:0.9}
	    100% {opacity: 1;}  
	    }
	    
    .one-good{
    	margin-top:10px;
    	background:white;
    	width:90%;
    	height:100px;
    	border-radius:5px;
    	box-shadow:0 0 5px 0 #ffffff;
    	padding:10px;
    	margin:10px auto;
    }
</style>

</head>
<body>

<%@include file="/logout_navi.jsp" %>

<div class="cart-wrapper">
        <%
        	DataGoods dao = new DataGoods();
           	List<Goods> list = dao.readGoods();    
           	for(Goods tl:list) {
        %>
        <div class="one-good">
	        <div class="good-name"> <%=tl.getName() %></div>
	        <div class="price"><%=tl.getPrice() %></div>
	        <div class="store"><%=tl.getStore() %></div>
	        <div class="addtocar">添加购物车</div>
        </div>
        
        <%
          	}
        %>

</div>


</body>
</html>