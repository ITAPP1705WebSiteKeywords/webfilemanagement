package edu.cqu.filemanager.servlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.cqu.common.Constants;
import edu.cqu.filemanager.domain.User;
import edu.cqu.filemanager.exception.FileManagerException;
import edu.cqu.filemanager.service.FileManagerService;
public class ShowFileListOfUser extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getFilesOfUser(request, response);
	}
	private void getFilesOfUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute(
				Constants.LOGIN_USER_KEY);
		FileManagerService srv = new FileManagerService();
		String forward = "myfilelist.jsp";
		List l = null;
		if (user != null) {
			try {// inquire all user file by calling method of service tier
				l = srv.getFilesOfUser(user);
			} catch (FileManagerException e) {
				e.printStackTrace();
				request.setAttribute(Constants.MSG_KEY, e);
			}
		} else {// display log in form if user object doesn't exit
			forward = "login.jsp";
		}
		if (l != null) {// store file list into request object
			request.setAttribute(Constants.USER_FILES_KEY, l);
		}
		request.getRequestDispatcher(forward).forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getFilesOfUser(request, response);
	}
}