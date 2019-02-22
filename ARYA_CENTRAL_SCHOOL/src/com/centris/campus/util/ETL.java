package com.centris.campus.util;

import com.centris.campus.daoImpl.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ETL {

	public static void main(String[] args) {
		backendDataexchange();

	}
	public static void backendDataexchange() {
		
		Connection conn1=null,conn2=null;
		PreparedStatement ps1=null,ps2=null;
		ResultSet rs=null,rs2=null;
		
		
		try {
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_student",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_student_classdetails",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_parents",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_parentchildrelation",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_student_transportdetails",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_student_house",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			
			copy("campus_student_promotion",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_student_route_stage_mapping",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_studentpromotion",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_collection",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_collection_details",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_collectiondetails",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_indetail",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_master",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_reciept",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_setup",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_setupdetails",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_stage",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			
			copy("campus_fee_stagesetup",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_stagesetupdetails",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_termdetails",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_transport_termdetails",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fineconfiguration",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_tranport_fee_collection_details",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_transport_fees_payments",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_setupdetails",conn1,conn2);
			conn1.close();
			conn2.close();
			conn2 = JDBCConnection.getSeparateConnection();
			conn1 = JDBCConnection.getSeparateGodaddyConnection();
			
			copy("campus_fee_stage",conn1,conn2);
			
			
			System.out.println("updated");
		
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (ps1 != null&& (!ps1.isClosed())) {
					ps1.close();
				}
				if (ps2 != null&& (!ps2.isClosed())) {
					ps2.close();
				}
				if (conn1 != null&& (!conn1.isClosed())) {
					conn1.close();
				}
				if (conn2 != null && (!conn2.isClosed())) {
					conn2.close();
				}
			} catch (SQLException sqle) {

				
				sqle.printStackTrace();
			} catch (Exception e1) {

				
				e1.printStackTrace();
			}
		}
	}
	
	
	public static void copy(String table, Connection from, Connection to) throws SQLException {
		PreparedStatement trn=to.prepareStatement("TRUNCATE TABLE "+table);
		trn.executeUpdate();
		
	    try (PreparedStatement s1 = from.prepareStatement("select * from " + table);
	         ResultSet rs = s1.executeQuery()) {
	        ResultSetMetaData meta = rs.getMetaData();

	        List<String> columns = new ArrayList<>();
	        for (int i = 1; i <= meta.getColumnCount(); i++)
	            columns.add(meta.getColumnName(i));

	        try (PreparedStatement s2 = to.prepareStatement(
	                "INSERT INTO " + table + " ("
	              + columns.stream().collect(Collectors.joining(", "))
	              + ") VALUES ("
	              + columns.stream().map(c -> "?").collect(Collectors.joining(", "))
	              + ")"
	        )) {

	            while (rs.next()) {
	                for (int i = 1; i <= meta.getColumnCount(); i++)
	                    s2.setObject(i, rs.getObject(i));

	                s2.addBatch();
	            }

	            s2.executeBatch();
	        }
	    }
	}

}
