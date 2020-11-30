<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@page import="com.shop.dbclass.DataGoods, com.shop.entity.Goods"%>
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
	
	input{
		 border: 0;  // 去除未选中状态边框
        outline: none; // 去除选中状态边框
        background-color: rgba(0, 0, 0, 0);// 透明背景
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
	
	.submit-button{
		margin:10px auto;
		margin-left:30%;
	}

	.submit-button input[type="submit"] {
		background-color: #D6D2C1;
		border: 0;
		width: 30%;
		text-align: center;
		height: 30px;
		color: #000000;
		outline: none;
		border-radius: 5px; 
	}
	
	.submit-button input[type="submit"]:hover {
		cursor: pointer;
		color: white;
		background-image: linear-gradient(to right, #e8198b, #0eb4dd);
	}
</style>
</head>
<body>

<%@include file="/logout_navi.jsp" %>

<div class="goods-wrapper">

<a href="#">商品详情</a><br/>

<form name="form" action="modifygood" method="POST" onsubmit="return true">
 <table class="table" border="1">

      <tr>
          <td>商品名称</td>
          <td>商品价格</td>
          <td>剩余量</td>
      </tr>
        <%
        	DataGoods dao = new DataGoods();
           	List<Goods> list = dao.readGoods();    
           	for(Goods tl:list) {
        %>
          <tr>
              <td> <input type="text" name="name" value="<%=tl.getName() %> "> </td>
              <td> <input type="text" name="price" value="<%=tl.getPrice() %>"> </td>
              <td> <input type="text" name="store" value="<%=tl.getStore() %>" > </td>
          </tr>
         <%
          	}
         %>
  </table>
  <div class="submit-button">
			<input type="submit" value="修改">
		</div>
  </form>
  <a href="goods2.jsp">浏览/销售情况</a>
</div>


</body>
</html>