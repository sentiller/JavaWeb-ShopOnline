<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@page import="com.shop.dbclass.DataBuy, com.shop.entity.Buy"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>goods</title>

<style type="text/css">
	* {
		padding: 0;
		margin: 0;
	}
	
	html {
		height: 100%;
	}
	
	a {
		color: gray;
		text_decoration: none;
	}
	
	body {
		height: 100%;
		font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
		align-items: center;
		justify-content: center;
		background-color: #0e92b3;
	}
	
	.goods-wrapper{
		padding:20px;
		width:99%;
		margin:10px auto;
		opacity:0;
/* 		background-color:#000000; */
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
	
	.table{
		background-color:#ffffff;
		margin:auto;
		width:90%;
		text-align:center;
	}
</style>
</head>
<body>

<%@include file="/logout_navi.jsp" %>

<div class="goods-wrapper">

	<a href="goods.jsp">商品详情</a><br/>

 
  	<a href="#">浏览/销售情况</a>
  
  <table class="table" border="1">
      <tr>
          <td>类别</td>
          <td>用户</td>
          <td>商品名称</td>
          <td>时间</td>
      </tr>
        <%
        	DataBuy dao = new DataBuy();
           	List<Buy> list = dao.readBuy();    
           	for(Buy tl:list) {
        %>
          <tr>
              <td><%=tl.getType() %></td>
              <td><%=tl.getCustomer() %></td>
              <td><%=tl.getGoodsname() %></td>
              <td><%=tl.getTime() %></td>
          </tr>
         <%
          	}
         %>
  </table>
  
</div>


</body>
</html>