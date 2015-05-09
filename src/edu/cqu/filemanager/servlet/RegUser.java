package edu.cqu.filemanager.servlet;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.cqu.common.Constants;
import edu.cqu.filemanager.domain.User;
import edu.cqu.filemanager.exception.FileManagerException;
import edu.cqu.filemanager.service.FileManagerService;
public class RegUser extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	public RegUser() {
		super();
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User u = new User();
		u.setUserID(request.getParameter("UserID"));
		u.setUserName(request.getParameter("UserName"));
		u.setUserMail(request.getParameter("UserMail"));
		u.setUserPassword(request.getParameter("UserPassword"));
		String userPassword2 = request.getParameter("UserPassword2");
		u.setUserCreated(new Date(new java.util.Date().getTime()));
		FileManagerService srv = new FileManagerService();
		FileManagerException msg = null;
		try {
			// check if two passwords are the same
			if (checkPassword(u.getUserPassword(), userPassword2)) {
				// use method of service tier to register user
				if (srv.regUser(u) > 0) {
					msg = new FileManagerException(
							"Successfully registered,<a href='login.jsp'>Pleas log in</a>");
				}
			} else {
				msg = new FileManagerException("Wrong password,please return。");
			}
			request.setAttribute(Constants.MSG_KEY, msg);
			request.getRequestDispatcher("msg.jsp").forward(request, response);
		} catch (FileManagerException e) {
			e.printStackTrace();
			request.setAttribute(Constants.MSG_KEY, e);
			request.getRequestDispatcher("reguser.jsp").forward(request,
					response);
		}
	}
	// check if two passwords are the same
	private boolean checkPassword(String userPassword, String userPassword2) {
		if (userPassword != null && userPassword2 != null) {
			return userPassword.equals(userPassword2);
		} else
			return false;
	}
}