package edu.cqu.filemanager.dao.mysql;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.cqu.common.Entity;
import edu.cqu.filemanager.dao.UserDAO;
import edu.cqu.filemanager.domain.User;
import edu.cqu.filemanager.exception.FileManagerException;
public class UserDAOImpl extends DAOImpl implements UserDAO {
	public int save(Entity e) throws FileManagerException {
		User user = (User) e;
		int result = 0;
		String sql = "insert into "
				+ e.getTable()
				+ "(UserID,UserName,UserMail,UserPassword,UserType,UserCreated)values(?,?,?,?,?,?)";
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserMail());
			pstmt.setString(4, user.getUserPassword());
			pstmt.setInt(5, user.getUserType());
			pstmt.setDate(6, user.getUserCreated());
			result = pstmt.executeUpdate();
		} catch (Exception e1) {
			throw new FileManagerException(e1);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				// e1.printStackTrace();
			}
		}
		return result;
	}
	public List select(String sql) throws FileManagerException {
		List l = new ArrayList();
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserID(rs.getString("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setUserMail(rs.getString("UserMail"));
				user.setUserPassword(rs.getString("UserPassword"));
				user.setUserType(rs.getInt("UserType"));
				user.setUserCreated(rs.getDate("UserCreated"));
				l.add(user);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new FileManagerException(e1);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return l;
	}
	public int update(Entity e) throws FileManagerException {
		User user = (User) e;
		int result = 0;
		String sql = "update "
				+ e.getTable()
				+ "set userID=?,UserName=?,UserMail=?,UserPassword=?,UserType=?,UserCreated=?";
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserMail());
			pstmt.setString(4, user.getUserPassword());
			pstmt.setInt(5, user.getUserType());
			pstmt.setDate(6, user.getUserCreated());
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new FileManagerException(e1);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	public List selectAllUsers() throws FileManagerException {
		// TODO Auto-generated method stub
		String sql = "select * from tblUser order by userID asc";
		return select(sql);
	}
	public User selectUser(String userID) throws FileManagerException {
		// TODO Auto-generated method stub
		String sql = "select * from tblUser where userID='" + userID + "'";
		List l = select(sql);
		if (l == null || l.size() == 0) {
			return null;
		} else {
			return (User) l.get(0);
		}
	}
}
