package edu.cqu.filemanager.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class UserLogout extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "login.jsp";
		request.getSession().invalidate();
		request.getRequestDispatcher(forward).forward(request, response);
	}
}