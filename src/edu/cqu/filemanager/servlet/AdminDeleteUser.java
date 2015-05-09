package edu.cqu.filemanager.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.cqu.common.Constants;
import edu.cqu.filemanager.exception.FileManagerException;
import edu.cqu.filemanager.service.FileManagerService;
public class AdminDeleteUser extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("userid");
		int result = 0;
		FileManagerService srv = new FileManagerService();
		String forward = "showalluser";
		try {// use method of service tier to delete user
			result = srv.deleteUser(userID);
			forward = "showalluser";
			FileManagerException e;
			if (result > 0) {
				e = new FileManagerException("Successfully deleted");
			} else {
				e = new FileManagerException("Failed to delete!");
			}
			request.setAttribute(Constants.MSG_KEY, e);
		} catch (FileManagerException e) {
			e.printStackTrace();
			request.setAttribute(Constants.MSG_KEY, e);
		}
		// call Servlet to inqure user
		request.getRequestDispatcher(forward).forward(request, response);
	}
}