package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.util.ErrorMessage;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;

public class JDBCConnection {

	private static final Logger logger = Logger.getLogger(JDBCConnection.class);

	static Connection connection = null;
	static PreparedStatement statement = null;
	private static String DriverName = null;
	private static String protocal = null;
	private static String ipaddress = null;
	private static String portno = null;
	private static String database = null;
	private static String username = null;
	private static String password = null;
	private static String ConnecionString = null;
	private static String godaddyIp=null;
	private static String godaddyusername=null;
	private static String godaddypassword=null;
	 static Connection godaddyConnecion=null;
	static {

		try {

			ResourceBundle res = ResourceBundle
					.getBundle("com/centris/campus/properties/CAMPUS");
			DriverName = res.getString("mySqlDriver");
			protocal = res.getString("protocal");
			ipaddress = res.getString("ipaddress");
			portno = res.getString("portno");
			database = res.getString("database");
			username = res.getString("username");
			password = res.getString("password");
			godaddyIp=res.getString("godaddyIp");
			godaddyusername=res.getString("godaddyusername");
			godaddypassword=res.getString("godaddypassword");
			
			ConnecionString = protocal + "://" + ipaddress + ":" + portno + "/"
					+ database;
			
			logger.info("Starting Database Connection");
			Class.forName(DriverName);
			connection = (Connection) DriverManager.getConnection(
					ConnecionString, username, password);
			
			
		} catch (Exception e) {
			logger.error("Problem in getting Connection, Database Not Connected");
			logger.error(e.getMessage(), e);
			ErrorMessage EM = ErrorMessage.getInstance();
			EM.setContent("DataBase Not Connected");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("---------------------------");
			System.out.println(" Connection is established ");
			System.out.println("---------------------------");

		} else {
			System.out.println("---------------------------");
			System.out.println(" Connection is NOT established ");
			System.out.println("---------------------------");
		}

	}

	public static Connection getSeparateConnection() throws Exception {
		Connection conn = null;
		try {
		
			String driverName = DriverName;
			String hostName = protocal + "://" + ipaddress + ":" + portno+"/";
			String dbName = database;
			String dbuserName = username;
			String dbpassword = password;

			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(hostName + dbName, dbuserName,
					dbpassword);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public static Connection getSeparateGodaddyConnection() throws Exception {
		Connection conn = null;
		try {
		
			String driverName = DriverName;
			String hostName = protocal + "://" + godaddyIp + ":" + portno+"/";
			String dbName = database;
			String dbuserName = godaddyusername;
			String dbpassword = godaddypassword;

			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(hostName + dbName, dbuserName,
					dbpassword);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnection() {

		logger.setLevel(Level.DEBUG);
		Date date_start = new Date();
		JLogger.log(0, JDate.getTimeString(date_start)
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(date_start)
				+ MessageConstants.START_POINT);

		try {
			if (connection == null) {
				Class.forName(DriverName);
				connection = (Connection) DriverManager.getConnection(
						ConnecionString, username, password);
			}

		} catch (Exception e) {
			logger.error("Problem in getting Connection, Database Not Connected");
			logger.error(e.getMessage(), e);
			System.out.println(" Problem in getting ReConnection ");
			e.printStackTrace();
			ErrorMessage EM = ErrorMessage.getInstance();
			EM.setContent("DataBase Not Connected");
			System.out.println(EM.getMessage());
		}
		return connection;

	}

	public static Connection reConnect() {
		try {
			if (connection == null) {
				Class.forName(DriverName);
				connection = (Connection) DriverManager.getConnection(
						ConnecionString, username, password);
			}

		} catch (Exception e) {
			logger.error("Trying to Reconnect,Problem in getting Connection, Database Not Connected");
			logger.error(e.getMessage(), e);
			ErrorMessage EM = ErrorMessage.getInstance();
			EM.setContent("DataBase Not Connected");
			System.out.println(EM.getMessage());
			return null;
		}
		return connection;
	}

	public static Statement getStatement(String sql) {
		try {

			if (connection == null) {
				connection = JDBCConnection.reConnect();
				if (connection == null) {
					return null;
				}
			}
			connection = JDBCConnection.reConnect();
			statement = (PreparedStatement) connection.prepareStatement(sql);

		} catch (SQLException e) {
			logger.error("Promlem in Creating Statement Object");
			logger.error(e.getMessage(), e);
			ErrorMessage EM = ErrorMessage.getInstance();
			EM.setContent("DataBase Problem");
			System.out.println(EM.getMessage());
			System.out.println(" Promlem in creating Statement object ");

			e.printStackTrace();
		}
		return statement;
	}

	public static void closeStatement() {
		try {
			statement.close();
		} catch (SQLException e) {
			logger.info("Promlem in Closing Statement object");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
}