<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="edu.cqu.common.*,java.util.*,edu.cqu.filemanager.domain.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My file</title>
</head>
<body>
<table width="100%" border="0">  
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td><%@include file="menubar.jsp"%></td>
	</tr>
	<tr>
		<td><%@include file="msg.jsp"%></td>
	</tr>
	<tr>
		<td><a href="uploadfile.jsp">Upload file</a></td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="1" bordercolor="#333333">
			<tr>
				<td>Number</td>
				<td>File title</td>
				<td>File name</td>
				<td>Owner</td>
				<td>Date</td>
				<td>Operation</td>
			</tr>
			<%
				List l = (List) request.getAttribute(Constants.USER_FILES_KEY);
				if (l != null && l.size() > 0) {
					int i = 0;
					for (Object f0 : l) {
						File f = (File) f0;
						i++;
			%>
			<tr>
				<td><%=i%></td>
				<td><%=f.getFileSubject()%></td>
				<td><%=f.getFileName()%></td>
				<td><%=f.getFileOwner()%></td>
				<td><%=f.getFileCreated()%></td>
				<td><a href="deletefile?fileid=<%=f.getFileID() %>">delete</a> <a
					href="downloadfile?fileid=<%=f.getFileID() %>" target="_blank">Download</a></td>
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
