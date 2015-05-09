<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="edu.cqu.filemanager.exception.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New user registration</title>
</head>
<body>
<%@include file="menubar.jsp"%>
<%@include file="msg.jsp"%>
<form method="post" action="reguser">
<table width="100%" border="0" align="center">
	<tbody>
		<tr>
			<td colspan="2"><div align="center">New user registration</div></td>

		</tr>
		<tr>
			<td>User name(50 characters max)<br>
			</td>
			<td><input type="text" maxlength="50" size="50" name="UserID"></td>
		</tr>
		<tr>
			<td>Name（10 characters max）</td>
			<td><input type="text" maxlength="5" size="5" name="UserName"></td>
		</tr>
		<tr>
			<td>Email（50 characters max）</td>
			<td><input type="text" maxlength="50" size="50" name="UserMail"></td>
		</tr>
		<tr>
			<td>Password（50 characters max）</td>
			<td><input type="password" maxlength="50" size="50"
				name="UserPassword"></td>
		</tr>
		<tr>
			<td>Confirm password</td>
			<td><input type="password" maxlength="50" size="50"
				name="UserPassword2"></td>
		</tr>
		<tr>
			<td valign="top"><br>
			</td>
			<td valign="top"><input type="submit" value="提交"
				name="btnSubmit"> <input type="button" value="取消"
				name="btnCancel"></td>
		</tr>
	</tbody>
</table>
</form>
</body>
</html>
