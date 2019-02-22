package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.PhoneDirectoryReportDao;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.vo.PhoneDirectoryVo;

public class PhoneDirectoryReportDaoImpl implements PhoneDirectoryReportDao{

	
	private static final Logger logger = Logger
			.getLogger(PhoneDirectoryReportDaoImpl.class);
	
	public List<PhoneDirectoryVo> getPhoneDirectoryNamesDao(PhoneDirectoryVo vo) {
		
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryReportDaoImpl : getPhoneDirectoryNamesDao Starting");
		
		
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs=null;
		Connection conn = null;
		List<PhoneDirectoryVo> directoryList=new ArrayList<PhoneDirectoryVo>();
		int count=0;
		
		
		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			
			
			
			if(vo.getCategory().contains("admin")){
				
				
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_ADMIN_LIST);
				
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					PhoneDirectoryVo obj = new PhoneDirectoryVo();
					
				
					obj.setId(rs.getString("TeacherID"));
					obj.setName(rs.getString("adminname"));
					obj.setCategory(rs.getString("RoleName"));
					
					
					
					directoryList.add(obj);
					
				}
				
				
				
				
			}
			
			else if(vo.getCategory().contains("students")){
				
				
				
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENTS_LIST);
				
				System.out.println("students list"+pstmt);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					PhoneDirectoryVo obj = new PhoneDirectoryVo();
					
					obj.setId(rs.getString("student_id_int"));
					obj.setName(rs.getString("studentname"));
					
					
					directoryList.add(obj);

				}
				
				
				
				
			}
			
	      else if(vo.getCategory().contains("teachers")){
				
	    	  
	    	  
	    	  pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_TEACHERS_LIST);
				
	    	  System.out.println("students list"+pstmt);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					PhoneDirectoryVo obj = new PhoneDirectoryVo();
					
					obj.setId(rs.getString("TeacherID"));
					obj.setName(rs.getString("teachername"));
					obj.setCategory(rs.getString("RoleName"));
					
					
					directoryList.add(obj);

				}
	    	  
	    	  
	    	  
	    	  
	    	  
				
			}
			
	      else{
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_ALL_TEACHERS_LIST);
	    	 
	    	  System.out.println("ALL_TEACHERS list"+pstmt);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					PhoneDirectoryVo obj = new PhoneDirectoryVo();
					
					obj.setId(rs.getString("TeacherID"));
					obj.setName(rs.getString("teachername"));
										
					
					directoryList.add(obj);

				}
	    	  
	    	  
				 pstmt1 = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENTS_LIST);
				 
				 System.out.println("ALL_STUDENTS list"+pstmt1);
					rs = pstmt1.executeQuery();
					
					while(rs.next()){
						
						PhoneDirectoryVo obj1 = new PhoneDirectoryVo();
						
						obj1.setId(rs.getString("student_id_int"));
						obj1.setName(rs.getString("studentname"));
						
						
						directoryList.add(obj1);

					
				}
	    	  
	      }
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryReportDaoImpl : getPhoneDirectoryNamesDao Ending");
		
		
		
		return directoryList;
	}

	
	public List<PhoneDirectoryVo> getPhoneDirectoryListDao(PhoneDirectoryVo vo) {
		
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryReportDaoImpl : getPhoneDirectoryListDao Starting");
		
		

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs=null;
		Connection conn = null;
		List<PhoneDirectoryVo> directoryList=new ArrayList<PhoneDirectoryVo>();
		int count=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			
			if(vo.getCategory().contains("admin")){
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_ADMIN_PHONE_DIRECTORYLIST);
				
				pstmt.setString(1, vo.getId());
				
				
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					PhoneDirectoryVo obj = new PhoneDirectoryVo();
					count++;
					obj.setCount(count);
					obj.setId(rs.getString("TeacherID"));
					obj.setName(rs.getString("adminname"));
					obj.setCategory(rs.getString("RoleName"));
					obj.setPhone(rs.getString("mobileNo"));
					obj.setEmail(rs.getString("emailId"));
					obj.setAddress(rs.getString("permanentAddress"));
					
					
					
					
					
					directoryList.add(obj);
					
				}
				
			}
			
			else if(vo.getCategory().contains("students")){
				
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENTSPHONE_DIRECTORY_LIST);
				
				pstmt.setString(1, vo.getId());
				
				
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					PhoneDirectoryVo obj = new PhoneDirectoryVo();
					count++;
					obj.setCount(count);
					obj.setId(rs.getString("student_id_int")); 
					obj.setName(rs.getString("studentname"));
					obj.setCategory("Student");
					obj.setPhone(rs.getString("FatherMobile"));
					obj.setEmail(rs.getString("FatherMailId"));
					obj.setAddress(rs.getString("address"));
					
					
					
					
					directoryList.add(obj);

				}
				
				
				
				
				
			}
			
			
			 else if(vo.getCategory().contains("teachers")){
				 
				 pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_TEACHERS_PHONE_DIRECTORY_LIST);
					
				 
				 pstmt.setString(1, vo.getId());
				 
					
					
					rs = pstmt.executeQuery();
					
					while(rs.next()){
						
						PhoneDirectoryVo obj = new PhoneDirectoryVo();
						count++;
						obj.setCount(count);
						obj.setId(rs.getString("TeacherID"));
						obj.setName(rs.getString("teachername"));
						obj.setCategory(rs.getString("RoleName"));
						
						obj.setPhone(rs.getString("mobileNo"));
						obj.setEmail(rs.getString("emailId"));
						obj.setAddress(rs.getString("permanentAddress"));
						
						
						
						directoryList.add(obj);

					} 
				 
				 
			 }
			
			 else{
			
				 		
				 if(vo.getId().contains("TEA")){
					 
					
								
							   pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_ALL_TEACHERS_DIRECTORY_LIST1);
								 
								 pstmt.setString(1, vo.getId());
								 
						    	  
									
									rs = pstmt.executeQuery();
									
									while(rs.next()){
										
										PhoneDirectoryVo obj = new PhoneDirectoryVo();
										count++;
										obj.setCount(count);
										obj.setId(rs.getString("TeacherID"));
										obj.setName(rs.getString("teachername"));
										obj.setCategory(rs.getString("RoleName"));					
									
										
										obj.setPhone(rs.getString("mobileNo"));
										obj.setEmail(rs.getString("emailId"));
										obj.setAddress(rs.getString("permanentAddress"));
										
										directoryList.add(obj);

									}
								
							}
						   
						   
						   
				 else if(vo.getId().contains("STU")){
							   
					
					 
					 pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_ALL_STUDENTSPHONE_DIRECTORY_LIST1);
					 
						
					 pstmt.setString(1, vo.getId());
						
						rs = pstmt.executeQuery();
						
						while(rs.next()){
							
							PhoneDirectoryVo obj1 = new PhoneDirectoryVo();
							count++;
							obj1.setCount(count);
							obj1.setId(rs.getString("student_id_int"));
							obj1.setName(rs.getString("studentname"));
							obj1.setCategory("Student");
							obj1.setPhone(rs.getString("FatherMobile"));
							obj1.setEmail(rs.getString("FatherMailId"));
							obj1.setAddress(rs.getString("student_address"));
							
							
							
							directoryList.add(obj1);
						 }
					 	   
						   }
						   
					 
					else{
						
						 pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_ALL_TEACHERS_DIRECTORY_LIST);
						 
						 pstmt.setString(1, vo.getId());
							rs = pstmt.executeQuery();
							
							while(rs.next()){
								
								PhoneDirectoryVo obj = new PhoneDirectoryVo();
								count++;
								obj.setCount(count);
								obj.setId(rs.getString("TeacherID"));
								obj.setName(rs.getString("teachername"));
								obj.setCategory(rs.getString("RoleName"));					
							
								
								obj.setPhone(rs.getString("mobileNo"));
								obj.setEmail(rs.getString("emailId"));
								obj.setAddress(rs.getString("permanentAddress"));
								
								directoryList.add(obj);

							}
				    	  
				    	  
							 pstmt1 = conn.prepareStatement(ReportsMenuSqlConstants.GET_ALL_STUDENTSPHONE_DIRECTORY_LIST);
							 
							
							 
								
								rs = pstmt1.executeQuery();
								
								while(rs.next()){
									
									PhoneDirectoryVo obj1 = new PhoneDirectoryVo();
									count++;
									obj1.setCount(count);
									obj1.setId(rs.getString("student_id_int"));
									obj1.setName(rs.getString("studentname"));
									obj1.setCategory("Student");
									obj1.setPhone(rs.getString("FatherMobile"));
									obj1.setEmail(rs.getString("FatherMailId"));
									obj1.setAddress(rs.getString("address"));
									
									
									
									directoryList.add(obj1);
								 }
						
						
					}
				 
				 
				
			 }
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryReportDaoImpl : getPhoneDirectoryListDao Ending");
		
		
		return directoryList;
	}


	
	public PhoneDirectoryVo getSelectedValueNameDao(PhoneDirectoryVo vo) {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryReportDaoImpl : getSelectedValueNameDao Starting");
		
		
		

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs=null;
		Connection conn = null;
		PhoneDirectoryVo phvo= new PhoneDirectoryVo();
		int count=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			if(vo.getId().contains("TEA")){
				
				
				 pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_TEACHERS_NAME);
				 
				 
				 pstmt.setString(1, vo.getId());
				 
					rs = pstmt.executeQuery();
					
					while(rs.next()){
						
						
						phvo.setId(rs.getString("TeacherID"));
						
						phvo.setName(rs.getString("teachername"));
						
					}
				 
				
			}
			
			else{
				
           	 pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENTS_NAME);
				 
				 
				 pstmt.setString(1, vo.getId());
				 
					rs = pstmt.executeQuery();
					
					while(rs.next()){
						
						
						phvo.setId(rs.getString("student_id_int"));
						
						phvo.setName(rs.getString("studentname"));
						
					}
				
				
				
			}
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PhoneDirectoryReportDaoImpl : getSelectedValueNameDao Ending");
		
		return phvo;
	}

}
