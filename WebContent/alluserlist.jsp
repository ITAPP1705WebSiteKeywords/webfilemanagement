<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="edu.cqu.common.*,java.util.*,edu.cqu.filemanager.domain.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All users</title>
</head>
<body>
<table width="100%" border="0">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td><%@include file="msg.jsp"%> <%@include
			file="menubar.jsp"%></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td><a href="reguser.jsp">Register</a></td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="1" bordercolor="#333333">
			<tr>
				<td>Number</td>
				<td>User ID</td>
				<td>Nmae</td>
				<td>Email</td>
				<td>Date</td>
				<td>Operation</td>
			</tr>
			<%
				//display user list stored in request object
				List l = (List) request.getAttribute(Constants.USER_ALL_KEY);
				if (l != null && l.size() > 0) {
					int i = 0;
					for (Object u0 : l) {
						User u = (User) u0;
						i++;
			%>
			<tr>
				<td><%=i%></td>
				<td><%=u.getUserID()%></td>
				<td><%=u.getUserName()%></td>
				<td><%=u.getUserMail()%></td>
				<td><%=u.getUserCreated()%></td>
				<td><a href="deleteuser?userid=<%=u.getUserID() %>">Delete</a></td>
			</tr>
			<%
				}
				}
			%>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		 </td>
	</tr>
</table>
</body>
</html>
