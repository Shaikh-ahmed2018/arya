package com.centris.campus.serviceImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.SettingsFileUploadDaoImpl;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.StudentExcelUploadPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.classVo;
import com.centris.campus.vo.studentExcelUploadVo;

public class SettingsXLUploadServiceImpl {
	private static Logger logger = Logger.getLogger(SettingsXLUploadServiceImpl.class);
	public Set<studentExcelUploadVo> saveStudentExam(List<StudentExcelUploadPojo> list) throws SQLException {
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SettingsXLUploadServiceImpl : insertStreamXSL : Starting");
			
			Connection connection = null;
			PreparedStatement pstmt=null;
			PreparedStatement pstm1=null;
			PreparedStatement pstmt2=null;
			PreparedStatement pstmt3=null,pstmt4=null,pstmt5=null,pstmt6=null,pstmt7=null,pstm2=null,pstmt1=null;
			ResultSet rs1=null,rs2=null,rs=null,rs3=null,rs4=null,rs5=null,rs6=null,rs7=null,rs8=null,rs0=null;
			List<StudentExcelUploadPojo> successlist=new ArrayList<StudentExcelUploadPojo>();
			SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
			Set<studentExcelUploadVo> failureListFromDiompl=new LinkedHashSet<studentExcelUploadVo>();
			Set<studentExcelUploadVo> failurelist = new LinkedHashSet<studentExcelUploadVo>();
			failurelist.clear();
			successlist.clear();
			
			try {
				connection=JDBCConnection.getSeparateConnection();
				int cont=0;
				
				//String int_regex="^[0-9a-zA-Z]*$";
				String string_regx="^[a-zA-Z0-9_ ]*$";

				String int_regex = "\\d+";
				
	/*			pstmt=connection.prepareStatement("SELECT `examtypename` FROM `campus_examtype`");
				rs = pstmt.executeQuery();
				while(rs.next()){
					=rs.getString("examtypename");
				}
				*/
				
				
				
				
				
				for (Iterator<StudentExcelUploadPojo> iterator = list.iterator(); iterator.hasNext();) {
					
					String grade[] ={"A","B","C","D","E","F"};
					String examType[] ={"Periodic Exam","Half Yearly Exam","Yearly Exam"};
					int count=0;
					cont++;
					int count1=0;
					StudentExcelUploadPojo pojo = (StudentExcelUploadPojo) iterator.next();
					studentExcelUploadVo vo = new studentExcelUploadVo();
						vo.setStudentName(pojo.getStudentName());
						vo.setStudentIdNo(pojo.getStudentIdNo());
						vo.setSchoolName(pojo.getSchoolName());
						vo.setAcademicYear(pojo.getAcademicYear());
						vo.setStreamName(pojo.getStreamName());
						vo.setClassName(pojo.getClassName());
						vo.setSectionName(pojo.getSectionName());
						vo.setAttandance(pojo.getAttandance());
						vo.setExamType(pojo.getExamType());
						vo.setExamName(pojo.getExamName());
						vo.setExamCode(pojo.getExamCode());
						vo.setStartdate(pojo.getStartdate());
						vo.setEnddate(pojo.getEnddate());
						vo.setSubjectName(pojo.getSubjectName());
						vo.setTestMaximumMarks(pojo.getTestMaximumMarks());
						vo.setTestScoredMarks(pojo.getTestScoredMarks());
						
						vo.setNotebookMaximumMarks(pojo.getNotebookMaximumMarks());
						vo.setNotebookScoredMarks(pojo.getNotebookScoredMarks());
						
						vo.setSubjectMaximumMarks(pojo.getSubjectMaximumMarks());
						vo.setSubjectScoredMarks(pojo.getSubjectScoredMarks());
						
						vo.setWorkEducation(pojo.getWorkEducation());
						vo.setArtEducation(pojo.getArtEducation());
						vo.setHealthEducation(pojo.getHealthEducation());
						vo.setDescipline(pojo.getDescipline());
						vo.setExamTypePrefix(pojo.getExamTypePrefix());
						vo.setRemarks(pojo.getRemarks());
						System.out.println(pojo.getExamType());
						
						int maxTestmarks = Integer.parseInt(pojo.getTestMaximumMarks());
						int scoredTestmarks = Integer.parseInt(pojo.getTestScoredMarks());
						int notebookMaxmarks = Integer.parseInt(pojo.getNotebookMaximumMarks());
						int notebookScoredMarks = Integer.parseInt(pojo.getNotebookScoredMarks());
						int subjectMaxmarks = Integer.parseInt(pojo.getSubjectMaximumMarks());
						int subjectScoredMarks = Integer.parseInt(pojo.getSubjectScoredMarks());
						
						pstm2=connection.prepareStatement("UPDATE campus_examination SET `isapplicableperiodic`= CASE WHEN `examtype` IN ('EMT3','EMT1') THEN 'N' ELSE 'Y' END");
						pstm2.executeUpdate();
						pstm2.close();
						
						
						
						if(!(pojo.getNotebookMaximumMarks().matches(int_regex))){
							vo.setReason("Invalid notebook maximum marks");
							failurelist.add(vo);	
						}
						else if(!(pojo.getNotebookScoredMarks().matches(int_regex))){
							vo.setReason("Invalid notebook scored marks");
							failurelist.add(vo);	
						}
						else if( scoredTestmarks > maxTestmarks){
							vo.setReason("Test scored marks cannot be greater than maximim marks");
						}
						
						else if(!(pojo.getSubjectMaximumMarks().matches(int_regex))){
							vo.setReason("Invalid subject maximum marks");
							failurelist.add(vo);	
						}
						else if(!(pojo.getSubjectScoredMarks().matches(int_regex))){
							vo.setReason("Invalid subject scored marks");
							failurelist.add(vo);	
						}
						else if( subjectScoredMarks > subjectMaxmarks){
							vo.setReason("Subject scored marks cannot be greater than maximim marks");
						}
					
						else if(!(pojo.getNotebookScoredMarks().matches(int_regex))){
							vo.setReason("Invalid notebook scored marks");
							failurelist.add(vo);	
						}
						else if(!(pojo.getNotebookMaximumMarks().matches(int_regex))){
							vo.setReason("Invalid notebook maximum marks");
							failurelist.add(vo);	
						}
						else if( notebookScoredMarks > notebookMaxmarks){
							vo.setReason("Notebook scored marks cannot be greater than maximim marks");
						}
						/*else if(!(Arrays.asList(grade).contains(pojo.getWorkEducation().trim()))){
							vo.setReason("Invalid work edu Grades");
							failurelist.add(vo);	
						}else if(!(Arrays.asList(grade).contains(pojo.getArtEducation().trim()))){
							vo.setReason("Invalid art education Grades");
							failurelist.add(vo);	
						}else if(!(Arrays.asList(grade).contains(pojo.getHealthEducation().trim()))){
							vo.setReason("Invalid health Grades");
							failurelist.add(vo);	
						}else if(!(Arrays.asList(grade).contains(pojo.getDescipline().trim()))){
							vo.setReason("Invalid Descipline Grade");
							failurelist.add(vo);	
						}*/
						else if(!(Arrays.asList(examType).contains(pojo.getExamType().trim()))){
							vo.setReason("Invalid Exam Type");
							failurelist.add(vo);
						}
						else if(count>0){
							vo.setReason("Duplicate serviceImpl");
							failurelist.add(vo);
						}
						else if(pojo.getStudentName()=="" || pojo.getStudentName().equals("-")){
							vo.setReason("Student Name Can't Be Empty");
							failurelist.add(vo);
						}
						
						
						else {
							pstmt= connection.prepareStatement("SELECT `acadamic_id` FROM `campus_acadamicyear` WHERE `acadamic_year`=?");
							pstmt.setString(1,pojo.getAcademicYear());
							rs = pstmt.executeQuery();
							if(rs.next()){
									pojo.setAccyearId(rs.getString("acadamic_id"));
									pstm1=connection.prepareStatement("SELECT `Location_Id` FROM `campus_location` WHERE `Location_Name`=?");
									pstm1.setString(1,pojo.getSchoolName());
									rs1 = pstm1.executeQuery();
									if(rs1.next()){
										pojo.setLocationId(rs1.getString("Location_Id"));
											pstmt2=connection.prepareStatement("SELECT `student_id_int` FROM `campus_student` WHERE `student_admissionno_var`=?");
											pstmt2.setString(1,pojo.getStudentIdNo());
											rs2 = pstmt2.executeQuery();
											if(rs2.next()){
												pojo.setStudentId(rs2.getString("student_id_int"));
												
														pstm1=connection.prepareStatement("SELECT `examtypeid`,exam_prefix FROM `campus_examtype` WHERE `examtypename`=?");
														pstm1.setString(1,pojo.getExamType());
														
														rs8 = pstm1.executeQuery();
														if(rs8.next()){
															pojo.setExamType(rs8.getString("examtypeid"));
															pojo.setExamTypePrefix(rs8.getString("exam_prefix"));
															
															pstmt4=connection.prepareStatement("SELECT `classstream_id_int` FROM `campus_classstream` WHERE `classstream_name_var`=? AND `locationId`=?");
															pstmt4.setString(1,pojo.getStreamName());
															pstmt4.setString(2,pojo.getLocationId());
															rs4 = pstmt4.executeQuery();
															if(rs4.next()){
																pojo.setStreamId(rs4.getString("classstream_id_int"));
																	pstmt5=connection.prepareStatement("SELECT `classdetail_id_int` FROM `campus_classdetail` WHERE `classdetails_name_var`=? AND `classstream_id_int`=? AND `locationId`=?");
																	pstmt5.setString(1,pojo.getClassName());
																	pstmt5.setString(2,pojo.getStreamId());
																	pstmt5.setString(3,pojo.getLocationId());
																	rs5 = pstmt5.executeQuery();
																	if(rs5.next()){
																		pojo.setClassId(rs5.getString("classdetail_id_int"));
																			pstmt6=connection.prepareStatement("SELECT `subjectID` FROM `campus_subject` WHERE `subjectName`=? AND `locationId`=? AND `classid`=?");
																			pstmt6.setString(1,pojo.getSubjectName());
																			pstmt6.setString(2,pojo.getLocationId());
																			pstmt6.setString(3,pojo.getClassId());
																			rs6 = pstmt6.executeQuery();
																			if(rs6.next()){
																				pojo.setSubjectId(rs6.getString("subjectID"));
																					pstmt7=connection.prepareStatement("SELECT `classsection_id_int` FROM `campus_classsection` WHERE `classsection_name_var`=? AND `classdetail_id_int`=? AND `locationId`=? ");
																					pstmt7.setString(1,pojo.getSectionName());
																					pstmt7.setString(2,pojo.getClassId());
																					pstmt7.setString(3,pojo.getLocationId());
																					rs7 = pstmt7.executeQuery();
																					if(rs7.next()){
																						pojo.setSectionId(rs7.getString("classsection_id_int"));
																								pstmt1= connection.prepareStatement("SELECT `acadamic_id` FROM `campus_acadamicyear` WHERE ?  BETWEEN startDate AND endDate and acadamic_year=?");
																								pstmt1.setString(1, HelperClass.convertUIToDatabase(pojo.getStartdate()));
																								pstmt1.setString(2, pojo.getAcademicYear());
																								rs1 = pstmt1.executeQuery();
																								if(rs1.next()){
																									pstmt3=connection.prepareStatement("SELECT `examid` FROM `campus_examination` WHERE `examcode`=? AND `loc_id`=? AND `acadamicyear`=? and examtype=? and classid=?");
																									pstmt3.setString(1,pojo.getExamCode());
																									pstmt3.setString(2,pojo.getLocationId());
																									pstmt3.setString(3,pojo.getAccyearId());
																									pstmt3.setString(4,pojo.getExamType());
																									pstmt3.setString(5,pojo.getClassId());
																									rs3 = pstmt3.executeQuery();
																									if(rs3.next()){
																										pojo.setExamId(rs3.getString("examid"));
																										successlist.add(pojo);
																									}
																									else{
																										vo.setReason("Exam Name Not Register In Exam Type and Exam Code");
																										failurelist.add(vo);
																									}

																								}else{
																									vo.setReason("Not Valid Start and End Dates");
																									failurelist.add(vo);
																								}
																								rs1.close();
																								}
																								else{
																									vo.setReason("Section Name Is Not Valid");
																									failurelist.add(vo);
																								}
																					}else{
																						vo.setReason("Subject Name Is Not Valid");
																						failurelist.add(vo);
																					}
																			}else{
																				vo.setReason("Stream Name Is Not Valid");
																				failurelist.add(vo);
																			}
																	}
																	else{
																		vo.setReason("Stream Name Is Not Valid");
																		failurelist.add(vo);
																	}
																	/*}
																	else{
																		vo.setReason("Exam name not register in exam type and exam code");
																		failurelist.add(vo);
																	}*/
															}else{
																vo.setReason("Exam Type Is Not Valid");
																failurelist.add(vo);
															}
														}
													else{
														vo.setReason("Student Id No Is Not Valid");
														failurelist.add(vo);
													}
												}
													else{
										vo.setReason("School Name Is Not Valid");
										failurelist.add(vo);
									}
								}		
							else{
								vo.setReason("Academic Year Is Not Valid");
								failurelist.add(vo);
							}
							rs.close();
						}

			
				for (Iterator<studentExcelUploadVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
					studentExcelUploadVo failureDiomplVo = (studentExcelUploadVo) it.next();
					studentExcelUploadVo uploadStream = new studentExcelUploadVo();

					uploadStream.setAcademicYear(failureDiomplVo.getAcademicYear());
					uploadStream.setStudentName(failureDiomplVo.getStudentName());
					uploadStream.setStudentIdNo(failureDiomplVo.getStudentIdNo());
					uploadStream.setSchoolName(failureDiomplVo.getSchoolName());
					uploadStream.setClassName(failureDiomplVo.getClassName());
					uploadStream.setSectionName(failureDiomplVo.getSectionName());
					uploadStream.setExamName(failureDiomplVo.getExamName());
					uploadStream.setExamCode(failureDiomplVo.getExamCode());
					uploadStream.setStartdate(failureDiomplVo.getStartdate());
					uploadStream.setEnddate(failureDiomplVo.getEnddate());
					uploadStream.setSubjectName(failureDiomplVo.getSubjectName());
					uploadStream.setAttandance(failureDiomplVo.getAttandance());
					uploadStream.setTestMaximumMarks(failureDiomplVo.getTestMaximumMarks());
					uploadStream.setTestScoredMarks(failureDiomplVo.getTestScoredMarks());
					uploadStream.setNotebookMaximumMarks(failureDiomplVo.getNotebookMaximumMarks());
					uploadStream.setNotebookScoredMarks(failureDiomplVo.getNotebookScoredMarks());
					uploadStream.setSubjectMaximumMarks(failureDiomplVo.getSubjectMaximumMarks());
					uploadStream.setSubjectScoredMarks(failureDiomplVo.getSubjectScoredMarks());
					uploadStream.setWorkEducation(failureDiomplVo.getWorkEducation());
					uploadStream.setArtEducation(failureDiomplVo.getArtEducation());
					uploadStream.setHealthEducation(failureDiomplVo.getHealthEducation());
					uploadStream.setDescipline(failureDiomplVo.getDescipline());
					failurelist.add(uploadStream);
				}
			}
				if(successlist.size()>0){
					failureListFromDiompl=daoImpl.insertExamReport(successlist,failurelist,connection);
				}
			}//try
			catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}finally{

			try{
				if(pstmt !=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				 if(pstm1 !=null &&!(pstm1.isClosed())){
					pstm1.close();
				}
				 if(pstmt2 !=null && !(pstmt2.isClosed())){
					pstmt2.close();
				}
				 if(pstmt3 !=null && !(pstmt3.isClosed())){
					pstmt3.close();
				}
				 if(pstmt4 !=null && !(pstmt4.isClosed())){
					pstmt4.close();
				}
				 if(pstmt5 !=null && !(pstmt5.isClosed())){
					pstmt5.close();
				}
				 if(pstmt6 !=null && !(pstmt6.isClosed())){
					pstmt6.close();
				}
				
				 if(pstmt7 !=null && !(pstmt7.isClosed())){
					pstmt7.close();
				}
				 if(pstm2 !=null && !(pstm2.isClosed())){
					pstm2.close();
				}
				 if(pstmt1 !=null && !(pstmt1.isClosed())){
					pstmt1.close();
				}
				 if(rs1 !=null && !(rs1.isClosed())){
					rs1.close();
				}
				 if(rs2 !=null && !(rs2.isClosed())){
					rs2.close();
				}
				 if(rs !=null && !(rs.isClosed())){
					rs.close();
				}
				 if(rs3 !=null && !(rs3.isClosed())){
					rs3.close();
				}
				 if(rs4 !=null && !(rs4.isClosed())){
					rs4.close();
				}
				 if(rs5 !=null && !(rs5.isClosed())){
					rs5.close();
				}
				 if(rs6 !=null && !(rs6.isClosed())){
					rs6.close();
				}
				if(rs7 !=null && (!rs7.isClosed())){
					rs7.close();
				}
				 if(rs8 !=null && (!rs8.isClosed())){
					rs8.close();
				}
				 if (connection != null && (!connection.isClosed())) {
					 connection.close();
					}
			}	
			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SettingsXLUploadServiceImpl : insertStreamXSL : Ending");
			return failurelist;
	}
	
	
	
	
	
	
	
	
	
	public Set<classVo> insertSubjectDetails(List<ClassPojo> list, String userId, String accyear, String log_audit_session) {
	
			Connection connection = null;
			PreparedStatement pstmt=null,pstmt1=null,pstmt2=null,pstmt3=null;
			ResultSet rs=null,rs1=null,rs2=null;
			List<ClassPojo> successlist=new ArrayList<ClassPojo>();
			SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
			
			
			Set<classVo> failurelist = new LinkedHashSet<classVo>();
			failurelist.clear();
			successlist.clear();
			
			try {
				connection=JDBCConnection.getSeparateConnection();
				int cont=0,count=0;
				String int_regex="[0-9_ ]+";
				String status="^[a-zA-Z]*$";
				String string_regx="^[a-zA-Z0-9_ ]*$";

				for (Iterator<ClassPojo> iterator = list.iterator(); iterator.hasNext();) {
					cont++;
					System.out.println("hjasfgdhjsfvdjasfvdjha==="+list.size()+" hkjhkl="+cont);
					boolean flag=true;
					String checknum="false";
					ClassPojo pojo = (ClassPojo) iterator.next();
					classVo vo = new classVo();
						
					
						vo.setLocationName(pojo.getLocationName());
						vo.setClassName(pojo.getClassName());
						vo.setSpecializationName(pojo.getSpecializationName());
						vo.setSubjectName(pojo.getSubjectName());
						vo.setSubjectCode(pojo.getSubjectCode());
						vo.setIsLanguage(pojo.getIsLanguage());
						vo.setTotalMarks(pojo.getTotalMarks());
						vo.setPassmarks(pojo.getPassmarks());
						vo.setDescription(pojo.getDescription());
						
						
	/*					for(int i=0;i<list.size();i++){
							if(i==cont-1){
								continue;
							}
							if(pojo.getLocationName().equalsIgnoreCase(list.get(i).getLocationName()) && pojo.getStreamName().equalsIgnoreCase(list.get(i).getStreamName()) && pojo.getClassName().equalsIgnoreCase(list.get(i).getClassName())&& pojo.getSpecializationName().equalsIgnoreCase(list.get(i).getSpecializationName()) ){
								if(cont-1<i){
									flag =false;
								}
							}
						}
						
						for(int i = 0; i < pojo.getSpecializationName().length(); i++) {
						     if(Character.isWhitespace(pojo.getSpecializationName().charAt(i))){
						    	 count++;
						     }
						     else{
						    	 if(count<2){
						    		 count=0;
						    	 }
						     }
						}*/
						
					
						
						if(pojo.getLocationName()=="" || pojo.getLocationName().equals("-")){
							vo.setReason("School should not empty");
							failurelist.add(vo);
						}
						else if(pojo.getClassName()=="" || pojo.getClassName().equals("-")){
							vo.setReason("Class should not empty");
							failurelist.add(vo);
						}
						else if(pojo.getSpecializationName()=="" || pojo.getSpecializationName().equals("-")){
							vo.setReason("Specialization should not empty");
							failurelist.add(vo);
						}
						else if(pojo.getSubjectName()=="" || pojo.getSubjectName().equals("-")){
							vo.setReason("Subject Name should not empty");
							failurelist.add(vo);
						}
						else if(pojo.getSubjectCode()=="" || pojo.getSubjectCode().equals("-")){
							vo.setReason("Subject Code should not empty");
							failurelist.add(vo);
						}
					/*	else if(!pojo.getTotalMarks().matches(int_regex)){
							vo.setReason("'Total Marks' accepts only Numbers ");
							failurelist.add(vo);
						}
						else if(!pojo.getPassmarks().matches(int_regex)){
							vo.setReason("'Pass Marks' accepts only Numbers");
							failurelist.add(vo);
						}*/
					/*	else if(pojo.getTotalMarks()=="" || pojo.getTotalMarks().equals("-")){
							vo.setReason("Subject Code should not empty");
							failurelist.add(vo);
						}*/
						
						
				/*		
					
						else if(!flag){
							vo.setReason("Duplicate entry in excel sheet");
							failurelist.add(vo);
						}*/
						else {
							//successlist.add(pojo);
						
							pstmt= connection.prepareStatement("SELECT `Location_Id` FROM `campus_location` WHERE `Location_Name`=?");
							pstmt.setString(1,pojo.getLocationName());
							System.out.println("check condition::::::::::::"+pstmt);
							rs = pstmt.executeQuery();
							if(rs.next()){
									pojo.setLocationId(rs.getString("Location_Id"));
									
									pstmt3= connection.prepareStatement("SELECT `classdetail_id_int` FROM `campus_classdetail` WHERE `classdetails_name_var`=?  AND `locationId`=?");
									pstmt3.setString(1,pojo.getClassName());
									pstmt3.setString(2,pojo.getLocationId());
									System.out.println(pstmt3);
									rs2 = pstmt3.executeQuery();
									if(rs2.next()){
										pojo.setClassId(rs2.getString("classdetail_id_int"));
										
										pstmt1= connection.prepareStatement("SELECT `Specialization_Id` FROM `campus_class_specialization` WHERE `Specialization_name`=? AND `locationId`=? AND `ClassDetails_Id`=?" );
										pstmt1.setString(1,pojo.getSpecializationName());
										pstmt1.setString(2,pojo.getLocationId());
										pstmt1.setString(3,pojo.getClassId());
										System.out.println("check condition::::::::::::"+pstmt1);
										rs1 = pstmt1.executeQuery();
										if(rs1.next()){
												pojo.setSpecializationId(rs1.getString("Specialization_Id"));
												successlist.add(pojo);
										}
										else{
											vo.setReason("Invalid specialization for Class");
											failurelist.add(vo);
										}
										rs1.close();
									}
									else{
										vo.setReason("Class is not mapped with Location");
										failurelist.add(vo);
									}
									rs1.close();
								}
							else{
								vo.setReason("School Name is not valid");
								failurelist.add(vo);
							}
							rs.close();
						}
				}
				Set<classVo> failureListFromDiompl=new LinkedHashSet<classVo>();
				for (Iterator<classVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
					classVo failureDiomplVo = (classVo) it.next();
					classVo uploadStream = new classVo();

					uploadStream.setLocationName(failureDiomplVo.getLocationName());
					uploadStream.setClassName(failureDiomplVo.getClassName());
					uploadStream.setSpecializationName(failureDiomplVo.getSpecializationName());
					uploadStream.setReason(failureDiomplVo.getReason());
					failurelist.add(uploadStream);
				}
				if(successlist.size()>0){
					failureListFromDiompl=daoImpl.insertSubjectDetails(successlist,failurelist,connection,userId,log_audit_session);
				}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}finally{
			try {
				if(pstmt!=null && (!pstmt.isClosed())){
					connection.close();
				}if(pstmt1!=null && (!pstmt1.isClosed())){
					pstmt1.close();
				}if(pstmt2!=null && (!pstmt2.isClosed())){
					pstmt2.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
			return failurelist;
		}









	public Set<classVo> insertHolidayXSL(List<ClassPojo> list, String userId, String accyear, String log_audit_session) {
		Connection connection = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null;
		ResultSet rs=null,rs1=null,rs2=null,rs10=null;
		int count4=0;int count5=0;
		List<ClassPojo> successlist=new ArrayList<ClassPojo>();
		SettingsFileUploadDaoImpl daoImpl=new SettingsFileUploadDaoImpl();
		
		Set<classVo> failurelist = new LinkedHashSet<classVo>();
		failurelist.clear();
		successlist.clear();
		
		try {
			connection=JDBCConnection.getSeparateConnection();
			int cont=0;
			String int_regex="^[0-9a-zA-Z]*$";
			String int_regex1="^[0-9/]*$";
			String status="^[a-zA-Z]*$";
			String string_regx="^[a-zA-Z0-9_ ]*$";
			String specialCharacters="!#$%&*+;<=>?@[]^`{|}";
			//String dateregex ="^\d{4}-\d{2}-\d{2}$";
			
			 String dateregex = "^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$";
			String specialError="false";
			String checknum="false";
			boolean flag=true;
			boolean flag1=true;
			
			int count=0;
			String dateformat=null;

			for (Iterator<ClassPojo> iterator = list.iterator(); iterator.hasNext();) {
				boolean dateflag =true;
				boolean flagYear=true;
				boolean monthExceedFlag=true;
				boolean flag3=true;
				cont++;
				System.out.println("hjasfgdhjsfvdjasfvdjha==="+list.size()+" hkjhkl="+cont);
				
				ClassPojo pojo = (ClassPojo) iterator.next();
				classVo vo = new classVo();
					vo.setLocationName(pojo.getLocationName());
					vo.setHolidayDate(pojo.getHolidayDate());
					vo.setHolidayName(pojo.getHolidayName());
					vo.setHolidaytype(pojo.getHolidaytype());
					
					String[] holidayTypeList ={"First Half","Second Half","Full Day"};

					
			
					
					dateformat=pojo.getHolidayDate();
			
					System.out.println("@@@@@@@@@@@@@@@@@@@@@"+dateformat);
					String data[] = dateformat.split("[-/]");
					System.out.println("!!!!!!!!!!!!!!!!!!!!!"+data[2]);
					System.out.println("!!!!!!!!!!!!!!!!!!!!!"+data[0]);
					String year = data[2];
					String date = data[0];
					String month = data[1];
					int chkyear=Integer.parseInt(year);
					int chkdate=Integer.parseInt(date);
					int chkmonth=Integer.parseInt(month);
				
					if ( chkyear % 100 == 0 ){
						if(chkyear % 400 == 0 && chkdate >= 29  && chkmonth==2  ){
							flagYear = true;
						}
						else{
							if(chkdate >= 29 && chkmonth==2 )
								flagYear = false;
						}
					}else{
						if(chkyear % 4 == 0 && chkdate >= 29 && chkmonth==2 ){
							flagYear = true;
						}
						else{
							if(chkdate >= 29 && chkmonth==2 )
							flagYear = false;
						}
					}
					
					
					if( (chkmonth==1 || chkmonth==3 || chkmonth==5 || chkmonth==7 || chkmonth==8 || chkmonth==10 ||  chkmonth==12) && chkdate>31 ){
						monthExceedFlag=false;
						
					}
					else if( (chkmonth==4 || chkmonth==6 || chkmonth==9 || chkmonth==11) && chkdate>30 ){
						monthExceedFlag=false;
					}
				
						
				
					
					
					
					for (int i = 0; i < pojo.getHolidayName().length(); i++) {
					    if (specialCharacters.contains(Character.toString(pojo.getHolidayName().charAt(i))))
					    {
					    	specialError="true";
					    }  
					  }
					
					for(int i = 0; i < pojo.getHolidayName().length(); i++) {
					     if(Character.isWhitespace(pojo.getHolidayName().charAt(i))){
					    	 count++;
					     }
					     else{
					    	 if(count<2){
					    		 count=0;
					    	 }
					     }
					}
					
					
					for(int i=0;i<list.size();i++){
						if(i==cont-1){
							continue;
						}
						if(pojo.getLocationName().equalsIgnoreCase(list.get(i).getLocationName()) && pojo.getHolidayDate().equalsIgnoreCase(list.get(i).getHolidayDate()) && pojo.getHolidayName().equalsIgnoreCase(list.get(i).getHolidayName())&& pojo.getHolidaytype().equalsIgnoreCase(list.get(i).getHolidaytype()) ){
							if(cont-1<i){
								flag =false;
							}
						}
						else if(pojo.getLocationName().equalsIgnoreCase(list.get(i).getLocationName()) && pojo.getHolidayDate().equalsIgnoreCase(list.get(i).getHolidayDate()) ){
							if(cont-1<i){
								flag1 =false;
							}
						}
					}
					
					for(int i = 0; i < pojo.getHolidayName().length(); i++) {
					if(Character.isDigit(pojo.getHolidayName().charAt(0)))
						checknum="true";
					}
				
					 if(pojo.getLocationName()=="" || pojo.getLocationName().equals("-")){
						vo.setReason("Location name should not empty");
						failurelist.add(vo);
					}
					
					else if(pojo.getHolidayName()=="" || pojo.getHolidayName().equals("-")){
						vo.setReason("Holiday name should not empty");
						failurelist.add(vo);
					}
					/*else if(specialError.equalsIgnoreCase("true")){
						vo.setReason("Holiday Name will not accpet #$%*+;<=>?@[]^`{|} charector");
						failurelist.add(vo);
					}*/
					else if(count>1){
						vo.setReason("More that two spaces bewteen words are not valid");
						failurelist.add(vo);
					}
					else if(checknum.equalsIgnoreCase("true")){
						vo.setReason("Holiday name should not begin with numbers");
						failurelist.add(vo);
					}
					
					else if(pojo.getHolidayDate()=="" || pojo.getHolidayDate().equals("-")){
						vo.setReason("Holiday Date should not empty");
						failurelist.add(vo);
					}
					else if(pojo.getHolidayDate()=="" || pojo.getHolidayDate().equals("-")){
						vo.setReason("Holiday Date should not empty");
						failurelist.add(vo);
					}
					else if(!(Arrays.asList(holidayTypeList).contains(pojo.getHolidaytype()))){
						vo.setReason("Invalid Holiday type");
						failurelist.add(vo);
					}
					else if(!flag){
						vo.setReason("Duplicate entry in Excel sheet");
						failurelist.add(vo);
					}
					else if(!flag1){
						vo.setReason("Duplicate Holiday date with school");
						failurelist.add(vo);
					}
					else if(!flagYear){
						vo.setReason("29 February "+year+" not available");
						failurelist.add(vo);
					}
					else if(!monthExceedFlag){
						vo.setReason("date:"+date+" not valid for month: "+month+" ");
						failurelist.add(vo);
					}
				
					else {	
					pstmt= connection.prepareStatement("SELECT `Location_Id` FROM `campus_location` WHERE `Location_Name`=?");
					pstmt.setString(1, pojo.getLocationName());
					System.out.println("check condition::::::::::::"+pstmt);
					rs = pstmt.executeQuery();
					if(rs.next()){
						pojo.setLocationId(rs.getString("Location_Id"));
						pstmt1= connection.prepareStatement("SELECT `acadamic_id`,`acadamic_year`,`startDate`,`endDate` FROM `campus_acadamicyear` WHERE ?  BETWEEN startDate AND endDate");
						//pstmt1.setString(1, HelperClass.convertUIToDatabase(vo.getHolidayDate()));
						
						pstmt1.setString(1, HelperClass.convertUIToDatabaseSettingReports(vo.getHolidayDate()));
						System.out.println("check condition::::::::::::"+pstmt1);
						rs1 = pstmt1.executeQuery();
						
						if(rs1.next()){
								pojo.setAccyearId(rs1.getString("acadamic_id"));
								//successlist.add(pojo);
								
								pstmt2= connection.prepareStatement("SELECT COUNT(*) FROM `campus_holidaymaster` WHERE  HOLIDAY_DATE=? and `LOC_ID`=? AND `CURRENT_YEAR`=?");
								//pstmt2.setString(1, HelperClass.convertUIToDatabase(pojo.getHolidayDate()));
								pstmt2.setString(1, HelperClass.convertUIToDatabaseSettingReports(pojo.getHolidayDate()));
								pstmt2.setString(2, pojo.getLocationId());
								pstmt2.setString(3, pojo.getAccyearId());
								System.out.println("check condition::::::::::::********* ^^^^^^^^^^^^^^"+pstmt2);
								rs2 = pstmt2.executeQuery();
								while(rs2.next()){
									//successlist.add(pojo);
									count4= rs2.getInt(1);
									System.out.println("count4 &&&&&&&&&&&&&&&&&&&"+count4);
								}
									
								if(count4 > 0){
									vo.setReason("Duplicate Holiday setup in school");
									failurelist.add(vo);
								}	else{
									successlist.add(pojo);
								}
							}		
					
						else{
							vo.setReason("Invalid Academic year / date format");
							failurelist.add(vo);
						}
						rs1.close();
					}
					else{
						vo.setReason("Invalid School Name");
						failurelist.add(vo);
					}
					rs.close();
					}
			}
			Set<classVo> failureListFromDiompl=new LinkedHashSet<classVo>();
			for (Iterator<classVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				classVo failureDiomplVo = (classVo) it.next();
				classVo uploadStream = new classVo();

				uploadStream.setLocationName(failureDiomplVo.getLocationName());
				uploadStream.setLocationId(failureDiomplVo.getLocationId());
				uploadStream.setHolidayDate(failureDiomplVo.getHolidayDate());
				uploadStream.setHolidayName(failureDiomplVo.getHolidayName());
				uploadStream.setHolidaytype(failureDiomplVo.getHolidaytype());
				uploadStream.setAccyearName(failureDiomplVo.getAccyearName());
				uploadStream.setReason(failureDiomplVo.getReason());
				failurelist.add(uploadStream);
			}
			System.out.println("successlist.size()"+successlist.size());
			System.out.println("failurelist.size()"+failurelist.size());
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertHoliday(successlist,failurelist,connection,userId,accyear,log_audit_session);
			}
			
	}catch (SQLException sqle) {
		sqle.printStackTrace();
		logger.error(sqle.getMessage(),sqle);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}finally{
		try {
			if(pstmt!=null && (!pstmt.isClosed())){
				connection.close();
			}if(pstmt1!=null && (!pstmt1.isClosed())){
				pstmt1.close();
			}if(pstmt2!=null && (!pstmt2.isClosed())){
				pstmt2.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(), sqle);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
		return failurelist;
	}


}
