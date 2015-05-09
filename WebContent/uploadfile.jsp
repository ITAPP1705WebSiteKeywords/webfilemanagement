<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload file</title>
</head>
<body>
<%@include file="menubar.jsp" %>
<form action="uploadfile" method="post" enctype="multipart/form-data">

<table width="100%" border="0">
	<tr>
		<td colspan="2">
		<div align="center">Upload file</div>
		</td>
	</tr>
	<tr>
		<td>File title</td>
		<td><input name="FileSubject" type="text" id="FileSubject"></td>
	</tr>
	<tr>
		<td>File</td>
		<td><input name="MyFile" type="file" id="MyFile"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><input name="btnSubmit" type="submit" id="btnSubmit"
			value="Submit"> <input type="reset" name="Submit" value="Reset"></td>
	</tr>
</table>
</form>
</body>
</html>
