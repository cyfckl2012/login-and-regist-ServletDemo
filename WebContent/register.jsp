<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/WEB_TEST/Login" method="post">
		<label>用户名</label>
		<input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名"><br />
		<label >密码</label>
		<input type="password" class="form-control" id="inputPassword3" name="password" placeholder="请输入密码"><br />
		<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
		<input type="password" class="form-control" id="confirmpwd" placeholder="请输入确认密码"><br />
		<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
		<input type="email" class="form-control" id="inputEmail3" name="email" placeholder="Email"><br />
		<label for="usercaption" class="col-sm-2 control-label">姓名</label>
		<input type="text" class="form-control" id="usercaption" name="name" placeholder="请输入姓名"><br />
		<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
		<label class="radio-inline"> <input type="radio" name="sex" id="inlineRadio1" value="male">男</label> 
		<label class="radio-inline"> <input type="radio" name="sex" id="inlineRadio2" value="female">女</label><br />
		<input type="submit" width="100" value="注册" name="submit">
	</form>

</body>
</html>