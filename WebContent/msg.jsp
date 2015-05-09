<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="edu.cqu.filemanager.exception.*,edu.cqu.common.*"%>


<table width="100%" border="0">
	<tr>
		<td>
		<div align="center" class="STYLE1">
		<%
			Object o = request.getAttribute(Constants.MSG_KEY);
			if (o != null)
				out.print(((FileManagerException) o).getMessage());
		%>
		</div>	  </td>
	</tr>
</table>

