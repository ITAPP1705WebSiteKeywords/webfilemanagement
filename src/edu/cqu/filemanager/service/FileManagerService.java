package edu.cqu.filemanager.service;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import edu.cqu.common.Constants;
import edu.cqu.common.MultipartRequest;
import edu.cqu.filemanager.dao.FileDAO;
import edu.cqu.filemanager.dao.FileDAOImpl;
import edu.cqu.filemanager.dao.UserDAO;
import edu.cqu.filemanager.dao.UserDAOImpl;
import edu.cqu.filemanager.domain.User;
import edu.cqu.filemanager.exception.FileManagerException;
public class FileManagerService {
	private static Log log = LogFactory.getLog(FileManagerService.class);
	public static ResourceBundle apps = ResourceBundle.getBundle("jdbcswitch");
	// user registration
	public int regUser(User u) throws FileManagerException {
		int result = 0;
		UserDAO uDAO = this.getUserDAO();
		result = uDAO.save(u);
		return result;
	}
	// delete user
	public int deleteUser(User u) throws FileManagerException {
		int result = 0;
		UserDAO uDAO = this.getUserDAO();
		result = uDAO.delete(u);
		return result;
	}
	// get all users
	public List getAllUsers() throws FileManagerException {
		UserDAO uDAO = this.getUserDAO();
		return uDAO.selectAllUsers();
	}
	// return user object by ID
	public User getUser(String userID) throws FileManagerException {
		UserDAO uDAO = this.getUserDAO();
		return uDAO.selectUser(userID);
	}
	// check password of a user
	public boolean checkUserPassword(User user, String password) {
		boolean result = false;
		if (user != null && password != null) {
			return password.equals(user.getUserPassword());
		} else
			return result;
	}
	// return files uploaded by user
	public List getFilesOfUser(User user) throws FileManagerException {
		FileDAO fDAO = this.getFileDAO();
		return fDAO.selectFilesOfUser(user.getKeyValue());
	}
	// save attachment from Request object
	public int saveFile(MultipartRequest mRequest) throws FileManagerException {
		String fileSubject = mRequest.getParameter("FileSubject");
		FileItem[] fs = null;
		int result = 0;
		fs = mRequest.getFiles();
		User user = (User) mRequest.getRequest().getSession().getAttribute(
				Constants.LOGIN_USER_KEY);
		String fileDir = getFileDir(mRequest.getRequest());
		edu.cqu.filemanager.domain.File myfile = null;
		if (fs != null && fs.length > 0) {
			myfile = saveFiles(fs, user, fileDir);
		} else {
			throw new FileManagerException("没有可以处理的文件");
		}
		if (myfile != null) {
			myfile.setFileSubject(fileSubject);
			FileDAO fDAO = this.getFileDAO();
			result = fDAO.save(myfile);
		}
		return result;
	}
	// save file to designated path and return a Flie object
	private edu.cqu.filemanager.domain.File saveFiles(FileItem[] fs, User user,
			String fileDir) {
		edu.cqu.filemanager.domain.File myfile = null;
		for (FileItem f : fs) {
			if (f.getSize() >= 0) {
				myfile = new edu.cqu.filemanager.domain.File();
				String filename = f.getName();
				if ((filename != null) && (filename.length() > 0)) {
					filename = filename.substring(filename
							.lastIndexOf(File.separatorChar) + 1);
				}
				myfile.setFileName(filename);
				myfile.setFilePath(getNewFileName(filename));
				myfile.setFileOwner(user.getUserID());
				myfile.setFileCreated(new java.sql.Date(new Date().getTime()));
				try {
					if (f != null) {
						f.write(new File(fileDir + File.separatorChar
								+ myfile.getFilePath()));
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				log.info(fileDir + File.separatorChar + filename);
			}
		}
		return myfile;
	}
	private String getNewFileName(String filename) {
		return filename + System.currentTimeMillis();
	}
	// get stored path of attachment from Web.xml
	public String getFileDir(HttpServletRequest request) {
		String basepath = request.getSession().getServletContext().getRealPath(
				"");
		String filepath = basepath
				+ File.separatorChar
				+ request.getSession().getServletContext().getInitParameter(
						"filelocation");
		return filepath;
	}
	// get all files information
	public List getAllFiles() throws FileManagerException {
		String sql = "select * from tblFile order by fileCreated desc ";
		FileDAO fDAO = this.getFileDAO();
		return fDAO.selectAllFiles();
	}
	// delete file by ID
	public int deleteFile(String fileID) throws FileManagerException {
		int result = 0;
		FileDAO fDAO = this.getFileDAO();
		edu.cqu.filemanager.domain.File f = new edu.cqu.filemanager.domain.File();
		f.setFileID(fileID);
		result = fDAO.delete(f);
		return result;
	}
	// return a file object defined in the system
	public edu.cqu.filemanager.domain.File getFile(String fileID)
			throws FileManagerException {
		FileDAO fDAO = this.getFileDAO();
		return fDAO.selectFile(fileID);
	}
	// delete user by ID
	public int deleteUser(String userID) throws FileManagerException {
		int result = 0;
		User u = new User();
		u.setUserID(userID);
		result = this.deleteUser(u);
		return result;
	}
	protected UserDAO getUserDAO() {
		UserDAO uDAO = null;
		String daoImpl = getDAOImpl();
		String impl = apps.getString("jdbc.dao.impl.user");
		if (daoImpl == null)
			uDAO = new UserDAOImpl();
		else {
			try {
				uDAO = (UserDAO) Class.forName(daoImpl + "." + impl)
						.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return uDAO;
	}
	protected FileDAO getFileDAO() {
		FileDAO fDAO = null;
		String daoImpl = getDAOImpl();
		String impl = apps.getString("jdbc.dao.impl.file");
		if (daoImpl == null)
			fDAO = new FileDAOImpl();
		else {
			try {
				fDAO = (FileDAO) Class.forName(daoImpl + "." + impl)
						.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		log.info(fDAO.getClass().getPackage());
		return fDAO;
	}
	protected String getDAOImpl() {
		String daoImpl = apps.getString("jdbc.dao.impl");
		String daoBase = apps.getString("jdbc.dao.base");
		if (daoImpl == null || daoImpl.length() == 0)
			return null;
		else
			return daoBase + "." + daoImpl;
	}
}
