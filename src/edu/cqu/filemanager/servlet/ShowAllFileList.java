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

/**
 * Servlet implementation class for Servlet: ShowAllFileList
 * 
 */
public class ShowAllFileList extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ShowAllFileList() {
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
		getAllFiles(request, response);
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

	private void getAllFiles(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute(
				Constants.LOGIN_USER_KEY);
		FileManagerService srv = new FileManagerService();
		String forward = "allfilelist.jsp";
		List l = null;
		if (user != null) {
			try {
				l = srv.getAllFiles();
			} catch (FileManagerException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				request.setAttribute(Constants.MSG_KEY, e);
			}
		} else {
			forward = "login.jsp";
		}
		if (l != null) {
			request.setAttribute(Constants.USER_FILES_KEY, l);
		}
		request.getRequestDispatcher(forward).forward(request, response);
	}
}