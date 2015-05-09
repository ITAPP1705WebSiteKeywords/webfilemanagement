package edu.cqu.common;
import java.sql.*;
import java.util.ResourceBundle;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class DBPool {
	private static DBPool pool = null;
	static Context initContext = null;
	static DataSource ds = null;
	protected static Log log = LogFactory.getLog(DBPool.class);
	public static ResourceBundle apps = ResourceBundle
			.getBundle("DBPoolResources");
	static String driver = null;
	static String user = null;
	static String password = null;
	static String connstr = null;
	static {
		try {
			// get database pool from the server
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/filedb");
			log.info("dbpool success");
		} catch (Exception e) {
			try {
				// creat own database pool
				driver = apps.getString("db.driver");
				Class.forName(driver);
				log.info("user's connection success");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public DBPool() {
		driver = apps.getString("db.driver");
		connstr = apps.getString("db.connstr");
		user = apps.getString("db.user");
		password = apps.getString("db.password");
	}
	public Connection getLocalConnection() throws SQLException,
			ClassNotFoundException {
		Connection conn = DriverManager.getConnection(connstr, user, password);
		return conn;
	}
	public Connection getConnection() throws SQLException,
			ClassNotFoundException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			log.info("create a pool's connection... ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (conn == null) {
			conn = this.getLocalConnection();
			log.info("create a user's connection... ");
		}
		return conn;
	}
	public static DBPool getInstance() {
		if (pool == null)
			return new DBPool();
		else
			return pool;
	}
}
