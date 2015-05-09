package edu.cqu.filemanager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cqu.common.Constants;
import edu.cqu.filemanager.exception.FileManagerException;
import edu.cqu.filemanager.service.FileManagerService;

/**
 * Servlet implementation class for Servlet: UserDeleteFile
 * 
 */
public class UserDeleteFile extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public UserDeleteFile() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileID = request.getParameter("fileid");
		int result = 0;
		FileManagerService srv = new FileManagerService();
		String forward = "showuserfile";
		try {
			result = srv.deleteFile(fileID);
			forward = "showuserfile";
			FileManagerException e = new FileManagerException("Successfully deleted");
			request.setAttribute(Constants.MSG_KEY, e);
		} catch (FileManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute(Constants.MSG_KEY, e);
		}
		request.getRequestDispatcher(forward).forward(request, response);

	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}