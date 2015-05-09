<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="edu.cqu.common.*,edu.cqu.filemanager.domain.*"%>

<table width="100%" height="30" border="0" bgcolor="#CCCCCC">
	<tbody>
		<tr>
			<td valign="top">Current user:<%
				User user = (User) session.getAttribute(Constants.LOGIN_USER_KEY);
				if (user != null) {
					out.println(user.getUserID());
					out.println("<a href='logout'>Log out</a>");
				} else {
					out.println("<a href='login.jsp'>Log in</a>");
				}
			%>			</td>
			<td><a href="reguser.jsp">User registration</a></td>
			<td><a href="showuserfile">My file</a></td>

			<td><a href="showallfile">All files</a></td>
		    <td><a href="showalluser">All users</a></td>
		</tr>
	</tbody>
</table>

