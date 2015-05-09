package edu.cqu.filemanager.dao.mysql;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.cqu.common.Entity;
import edu.cqu.filemanager.dao.FileDAO;
import edu.cqu.filemanager.domain.File;
import edu.cqu.filemanager.exception.FileManagerException;
public class FileDAOImpl extends DAOImpl implements FileDAO {
	public int save(Entity e) throws FileManagerException {
		File myfile = (File) e;
		int result = 0;
		String sql = "insert into "
				+ e.getTable()
				+ "(FileSubject,FileName,FilePath,FileType,FileOwner,FileCreated)values(?,?,?,?,?,?)";
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myfile.getFileSubject());
			pstmt.setString(2, myfile.getFileName());
			pstmt.setString(3, myfile.getFilePath());
			pstmt.setString(4, myfile.getFileType());
			pstmt.setString(5, myfile.getFileOwner());
			pstmt.setDate(6, myfile.getFileCreated());
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new FileManagerException(e1);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return result;
	}
	public List select(String sql) throws FileManagerException {
		// TODO Auto-generated method stub
		List l = new ArrayList();
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				File file = new File();
				file.setFileID(rs.getString("FileID"));
				file.setFileName(rs.getString("FileName"));
				file.setFilePath(rs.getString("FilePath"));
				file.setFileType(rs.getString("FileType"));
				file.setFileOwner(rs.getString("FileOwner"));
				file.setFileCreated(rs.getDate("FileCreated"));
				file.setFileSubject(rs.getString("FileSubject"));
				l.add(file);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new FileManagerException(e1);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return l;
	}
	public int update(Entity e) throws FileManagerException {
		// TODO Auto-generated method stub
		File myfile = (File) e;
		int result = 0;
		String sql = "update "
				+ e.getTable()
				+ "set FileSubject=?,set FileName=?,set FilePath=?,set FileType=?,set FileOwner=?,set FileCreated=?";
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myfile.getFileSubject());
			pstmt.setString(2, myfile.getFileName());
			pstmt.setString(3, myfile.getFilePath());
			pstmt.setString(4, myfile.getFileType());
			pstmt.setString(5, myfile.getFileOwner());
			pstmt.setDate(6, myfile.getFileCreated());
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new FileManagerException(e1);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return result;
	}
	public List selectAllFiles() throws FileManagerException {
		// TODO Auto-generated method stub
		String sql = "select * from tblFile order by fileCreated desc ";
		return select(sql);
	}
	public File selectFile(String fileID) throws FileManagerException {
		// TODO Auto-generated method stub
		// FileDAO fDAO = (FileDAO) this.getFileDAO();
		String sql = "select * from tblFile where fileID='" + fileID + "'";
		List l = select(sql);
		if (l == null || l.size() == 0) {
			return null;
		} else {
			return (edu.cqu.filemanager.domain.File) l.get(0);
		}
	}
	public List selectFilesOfUser(String userID) throws FileManagerException {
		// TODO Auto-generated method stub
		String sql = "select * from tblFile where fileOwner='" + userID
				+ "' order by fileCreated desc";
		// FileDAO fDAO = this.getFileDAO();
		return select(sql);
	}
}
