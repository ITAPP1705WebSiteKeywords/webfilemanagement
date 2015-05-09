package edu.cqu.filemanager.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.cqu.common.DownloadFile;
import edu.cqu.filemanager.domain.File;
import edu.cqu.filemanager.exception.FileManagerException;
import edu.cqu.filemanager.service.FileManagerService;
public class UserDownloadFile extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	public UserDownloadFile() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fileID = request.getParameter("fileid");
		FileManagerService srv = new FileManagerService();
		try {
			File f = srv.getFile(fileID);// get file object by ID
			String filepath = srv.getFileDir(request)
					+ java.io.File.separatorChar + f.getFilePath();
			// download file
			DownloadFile down = new DownloadFile(f.getFileName(), filepath);
			down.download(response);
		} catch (FileManagerException e) {
			e.printStackTrace();
		}
	}
}