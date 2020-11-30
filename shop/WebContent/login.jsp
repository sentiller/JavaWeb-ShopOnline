<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome to login</title>

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
	display: flex;
	align-items: center;
 	justify-content: center; 
	background-color: #0e92b3;
}

.form-wrapper {
	text-align: center;
	width: 300px;
	background-color: rgb(41, 45, 62);
	color: #ffffff;
	border-radius: 10px;
	padding: 50px;
	box-shadow: 1px 1px 10px #000;
	animation: fade-in; /*动画名称*/
	animation-duration: 1.5s; /*动画持续时间*/
	-webkit-animation: fade-in 1.5s; /*针对webkit内核*/
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

.form-wrapper .header {
	font-size: 35px;
	text-transform: uppercase;
	line-height: 100px;
}

.form-wrapper .input-wrapper {
	width: 100%;
	border-radius: 5px; 
}

.form-wrapper .input-wrapper .border-wrapper {
	background-image: linear-gradient(to right, #e8198b, #0eb4dd);
	width: 100%;
	height: 50px;
	margin-bottom: 20px;
	border-radius: 5px; 
	display: flex;
	align-items: center;
	justify-content: center;
	border-radius: 5px; 
}

.form-wrapper .input-wrapper .mini-wrapper {
	border: 2px white;
	width: 100%;
	height: 100%;
	text-align: center;
	font-size: 15px;
	margin: 2px;
	margin-bottom: 2px;
	border-radius: 5px; 
	box-shadow: 0 0 3px 0px #fff;
	border-radius: 5px; 
}

.form-wrapper .input-wrapper input[type="text"] {
	background-color: #D6D2C1;
	border: 0;
	width: 100%;
	height: 100%;
	text-align: center;
	color: #000000;
	outline: none;
	border-radius: 5px; 
}

.form-wrapper .input-wrapper input[type="password"] {
	background-color: #D6D2C1;
	border: 0;
	width: 100%;
	text-align: center;
	height: 100%;
	color: #000000;
	outline: none;
	text-align: center;
	border-radius: 5px; 
}

.form-wrapper .input-wrapper input:hover {
	background-color: #EFEBD8;
	border-radius: 5px; 
}

.form-wrapper .input-wrapper input:focus {
	background-color: #EFEBD8;
	border-radius: 5px; 
}

.form-wrapper .input-wrapper .radio-wrapper {
	display: flex;
	width: 100%;
	height: 100%;
	width: 100%;
	border-radius: 5px; 
}

.form-wrapper .input-wrapper .radio {
	height: 100%;
	width: 100%;
	text-align: center;
	border-radius: 5px; 
}

.form-wrapper .input-wrapper .radio input[type="radio"] {
	margin-top: 20px;
}

.form-wrapper .input-wrapper .radio:hover {
	color: black;
	cursor: pointer;
	background: #D6D2C1;
	opacity: 0.5;
	cursor: pointer;
}

.form-wrapper .input-wrapper input::placeholder {
	text-transform: uppercase;
}

.form-wrapper .input-wrapper input[type="submit"] {
	background-color: #D6D2C1;
	border: 0;
	width: 80%;
	text-align: center;
	height: 50px;
	color: #000000;
	outline: none;
	border-radius: 5px; 
}

.form-wrapper .input-wrapper input[type="submit"]:hover {
	cursor: pointer;
	color: white;
	background-image: linear-gradient(to right, #e8198b, #0eb4dd);
}


</style>

<script type="text/javascript">
	function check() {
		var username = document.getElementById("username");
		var userpwd = document.getElementById("userpwd");

		if (username.value == "") {
			alert("用户名不可为空！");
			username.focus();
			return false;
		}

		if (userpwd.value == "") {
			alert("密码不可为空！");
			userpwd.focus();
			return false;
		}
		return true;
	}
</script>

<script>
	function checkthis(n) {
		var business = document.getElementById("business");
		var customer = document.getElementById("customer");
		if (n == 1) {
			business.checked = true;
		} else {
			customer.checked = true;
		}
	}
</script>

</head>

<body>

	<%
		String username = "";
	String password = "";
	Cookie[] cookies = request.getCookies();
	if (cookies != null && cookies.length > 0) {
		for (Cookie c : cookies) {
			if (c.getName().equalsIgnoreCase("username")) {
		username = c.getValue();
			}
			if (c.getName().equalsIgnoreCase("password")) {
		password = c.getValue();
			}
		}
	}
	%>

	<form class="form-wrapper" name="form" action="login" method="POST"
		onsubmit="return check()">
		<div class="header">登录</div>
		<table class="input-wrapper">
			<tr class="border-wrapper">
				<td class="mini-wrapper"><input type="text" name="username"
					id="username" placeholder="username"></td>
			</tr>
			<tr class="border-wrapper">
				<td class="mini-wrapper"><input type="password" name="userpwd"
					id="userpwd" placeholder="password"></td>
			</tr>
			<tr class="border-wrapper">
				<td class="radio-wrapper">
					<div class="radio" onclick="checkthis(1)">
						<input type="radio" name="status" id="business" value="1">
						<span>商家</span>
					</div>
					<div class="radio" onclick="checkthis(2)">
						<input type="radio" name="status" id="customer" value="2" checked><span>顾客</span>
					</div>
				</td>
			</tr>
		</table>
		<br>
		<div class="input-wrapper">
			<input type="submit" value="登录">
		</div>

		<br> <a href="register.jsp">注册</a>

	</form>




</body>
</html>