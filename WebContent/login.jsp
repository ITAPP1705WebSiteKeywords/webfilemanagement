<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log in</title>
</head>
<body>
<form action="login" method="post">
<%@include file="menubar.jsp" %>
<%@include file="msg.jsp" %>
<table width="60%" border="0" align="center">
  <tr>
    <td colspan="2"><div align="center">Log in</div></td>
    </tr>
  <tr>
    <td><div align="left">User name</div></td>
    <td><label>
      <input name="UserID" type="text" id="UserID">
    </label></td>
  </tr>
  <tr>
    <td><div align="left">Password</div></td>
    <td><input type="password" name="UserPassword"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input name="btnSubmit" type="submit" id="btnSubmit" value="Submit"> <input type="reset" name="Submit" value="Reset"></td>
  </tr>
</table>
</form>
</body>
</html>
