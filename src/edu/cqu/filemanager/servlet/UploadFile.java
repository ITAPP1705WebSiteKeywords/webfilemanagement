package edu.cqu.filemanager.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.cqu.common.Constants;
import edu.cqu.common.MultipartRequest;
import edu.cqu.filemanager.exception.FileManagerException;
import edu.cqu.filemanager.service.FileManagerService;
public class UploadFile extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// crate a MultipartRequest object to handle it because a attachment is included in request object
		MultipartRequest mRequest = new MultipartRequest(request);
		FileManagerService srv = new FileManagerService();
		String forward = "showuserfile";
		try {// store file to the server
			srv.saveFile(mRequest);
			FileManagerException ex = new FileManagerException("File upload succed");
			request.setAttribute(Constants.MSG_KEY, ex);
		} catch (FileManagerException e) {
			e.printStackTrace();
			request.setAttribute(Constants.MSG_KEY, e);
		}
		request.getRequestDispatcher(forward).forward(request, response);
	}
}