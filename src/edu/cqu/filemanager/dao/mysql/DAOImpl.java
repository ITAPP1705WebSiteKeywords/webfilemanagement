package edu.cqu.filemanager.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import edu.cqu.common.DBPool;
import edu.cqu.common.Entity;
import edu.cqu.filemanager.dao.DAO;
import edu.cqu.filemanager.exception.FileManagerException;

public abstract class DAOImpl implements DAO {
	static DBPool pool = DBPool.getInstance();

	// private static Log log = LogFactory.getLog(DAOImpl.class);

	protected Connection conn = null;

	protected Statement stmt = null;

	protected PreparedStatement pstmt = null;

	protected ResultSet rs = null;

	public int delete(Entity e) throws FileManagerException {
		// TODO Auto-generated method stub
		int result = 0;
		if (e != null)
			try {
				String sql = "DELETE FROM " + e.getTable() + " WHERE " + e.getKey()
						+ "='" + e.getKeyValue() +"'";
				conn = pool.getConnection();
				stmt = conn.createStatement();
				result = stmt.executeUpdate(sql);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new FileManagerException(e1);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new FileManagerException(e1);
			}

		return result;
	}

}
