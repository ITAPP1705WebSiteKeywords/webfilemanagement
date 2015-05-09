package edu.cqu.filemanager.servlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.cqu.common.Constants;
import edu.cqu.filemanager.exception.FileManagerException;
import edu.cqu.filemanager.service.FileManagerService;
public class ShowAllUserList extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getAllUsers(request, response);
	}
	private void getAllUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// create object of service class
		FileManagerService srv = new FileManagerService();
		// display user list
		String forward = "alluserlist.jsp";
		List l = null;
		try {
			//call method of service class
			l = srv.getAllUsers();
		} catch (FileManagerException e) {
			// store exception information into request object
			request.setAttribute(Constants.MSG_KEY, e);
		}
		if (l != null) {
			// store user list into request object
			request.setAttribute(Constants.USER_ALL_KEY, l);
		}
		// display user list
		request.getRequestDispatcher(forward).forward(request, response);
	}
}