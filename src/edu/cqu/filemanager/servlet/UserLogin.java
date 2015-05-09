package edu.cqu.filemanager.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.cqu.common.Constants;
import edu.cqu.filemanager.domain.User;
import edu.cqu.filemanager.exception.FileManagerException;
import edu.cqu.filemanager.service.FileManagerService;
public class UserLogin extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("UserID");
		String password = request.getParameter("UserPassword");
		FileManagerService srv = new FileManagerService();
		String forward = "myfilelist.jsp";
		User user = null;
		try {// get user object by ID
			user = srv.getUser(userID);
		} catch (FileManagerException e) {
			request.setAttribute(Constants.MSG_KEY, e);
		}
		FileManagerException ex = null;
		if (user == null) {// if user doesn't exit
			ex = new FileManagerException("User doesn't exit");
			request.setAttribute(Constants.MSG_KEY, ex);
			forward = "login.jsp";
		} else if (!srv.checkUserPassword(user, password)) {// check password
			ex = new FileManagerException("Wrong password");
			request.setAttribute(Constants.MSG_KEY, ex);
			forward = "login.jsp";
		} else {//if passed
			request.getSession().setAttribute(Constants.LOGIN_USER_KEY, user);
			forward = "showuserfile";
		}
		request.getRequestDispatcher(forward).forward(request, response);
	}
}