package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import com.centris.campus.util.ErrorMessage;

public class JDBCConnectionNoLoggers {
	

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

				ConnecionString = protocal + "://" + ipaddress + ":" + portno + "/"
						+ database;
				Class.forName(DriverName);
				connection = (Connection) DriverManager.getConnection(
						ConnecionString, username, password);
			} catch (Exception e) {
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

		public static Connection getConnection() {

			
			try {
				if (connection == null) {
					Class.forName(DriverName);
					connection = (Connection) DriverManager.getConnection(
							ConnecionString, username, password);
				}

			} catch (Exception e) {
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
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	

