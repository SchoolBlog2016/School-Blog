<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Product From</title>
</head>
<body>

<div id="productFrom">
	<form action="api/user/register" method="post">
		<fieldset>
			<legend>Add a User</legend>
			<p>
				<label for="StuNum">学号: </label>
				<input type="text" id="StuNum" name="StuNum" tabindex="1"/>
			</p>
			<p>
				<label for="idNum">密码: </label>
				<input type="text" id="idNum" name="idNum" tabindex="2"/>
			</p>
			<p>
				<label for="name">姓名: </label>
				<input type="text" id="name" name="name" tabindex="3"/>
			</p>
			<p>
				<label for="gender">性别: </label>
				<input type="text" id="gender" name="gender" tabindex="4"/>
			</p>
			<p>
				<label for="grade">年级: </label>
				<input type="text" id="grade" name="grade" tabindex="5"/>
			</p>

			<p id="buttons">
				<input id="reset" type="reset" tabindex="6"></input>
				<input id="submit" type="submit" tabindex="7"
				value="注册"></input>
				</p>
		</fieldset>
	
	</form>
	
</div>

</body>
</html>