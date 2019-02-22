package com.centris.campus.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//import org.apache.catalina.startup.SetAllPropertiesRule;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.UploadLibraryXLSDaoImpl;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.pojo.StockEntryPOJO;
import com.centris.campus.pojo.UploadLibraryXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.service.UploadLibraryXSLservice;
import com.centris.campus.service.UploadStudentXSLservice;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.StockEntryVo;
import com.centris.campus.vo.UploadLibraryXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.itextpdf.text.log.SysoLogger;


public class UploadLibraryXSLServiceIMPL implements UploadLibraryXSLservice {

	private static Logger logger = Logger.getLogger(UploadLibraryXSLServiceIMPL.class);


	public Set<UploadLibraryXlsVO> insertLibXSL(List<UploadLibraryXlsPOJO> list, String username, String duplicate,String currentLoc)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLServiceImpl : insertEmpXSL : Starting");

		Connection connection = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null,pstmt3=null,pstmt4=null,pstmt5=null;
		ResultSet rs=null;
		List<UploadLibraryXlsPOJO> successlist=new ArrayList<UploadLibraryXlsPOJO>();
		UploadLibraryXLSDaoImpl daoImpl=new UploadLibraryXLSDaoImpl();

		int count = 0;
		boolean pancardflag=false;
		boolean personalemailflag=false;
		boolean officialemailflag=false;
		int checkCategoryTypeName=0;

		Set<UploadLibraryXlsVO> failurelist = new LinkedHashSet<UploadLibraryXlsVO>();

		failurelist.clear();
		successlist.clear();

		try {

			connection=JDBCConnection.getSeparateConnection();

			String int_regex="^[0-9a-zA-Z]*$";
			String status="^[a-zA-Z]*$";
			/*String numbers_regx = "[0-9//]{10}";
			String string_regx="([a-zA-Z.,-)(]+\\s+)*[a-zA-Z.,-)(]+";
			String datePattern = "\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
			String regexpforEmailId="/[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]*$/i";*/

			for (Iterator<UploadLibraryXlsPOJO> iterator = list.iterator(); iterator.hasNext();) {

				UploadLibraryXlsPOJO UploadLibraryXlsPOJO = (UploadLibraryXlsPOJO) iterator.next();
				UploadLibraryXlsVO uploadLibraryXSLVo = new UploadLibraryXlsVO();
				uploadLibraryXSLVo.setCategorytypecode(UploadLibraryXlsPOJO.getCategorytypecode());
				uploadLibraryXSLVo.setCategorytypename(UploadLibraryXlsPOJO.getCategorytypename());
				uploadLibraryXSLVo.setCategorystatus(UploadLibraryXlsPOJO.getCategorystatus());
				uploadLibraryXSLVo.setCategorydescription(UploadLibraryXlsPOJO.getCategorydescription());

				uploadLibraryXSLVo.setSubcategorytypecode(UploadLibraryXlsPOJO.getSubcategorytypecode());
				uploadLibraryXSLVo.setSubcategorytypename(UploadLibraryXlsPOJO.getSubcategorytypename());
				uploadLibraryXSLVo.setSubcategorystatus(UploadLibraryXlsPOJO.getSubcategorystatus());
				uploadLibraryXSLVo.setSubcategorydescription(UploadLibraryXlsPOJO.getSubcategorydescription());

				uploadLibraryXSLVo.setSubcategorytype1code(UploadLibraryXlsPOJO.getSubcategorytype1code());
				uploadLibraryXSLVo.setSubcategorytype1name(UploadLibraryXlsPOJO.getSubcategorytype1name());
				uploadLibraryXSLVo.setSubcategory1status(UploadLibraryXlsPOJO.getSubcategory1status());
				uploadLibraryXSLVo.setSubcategory1description(UploadLibraryXlsPOJO.getSubcategory1description());

				uploadLibraryXSLVo.setSubcategorytype2code(UploadLibraryXlsPOJO.getSubcategorytype2code());
				uploadLibraryXSLVo.setSubcategorytype2name(UploadLibraryXlsPOJO.getSubcategorytype2name());
				uploadLibraryXSLVo.setSubcategory2status(UploadLibraryXlsPOJO.getSubcategory2status());
				uploadLibraryXSLVo.setSubcategory2description(UploadLibraryXlsPOJO.getSubcategory2description());

				uploadLibraryXSLVo.setSubcategorytype3code(UploadLibraryXlsPOJO.getSubcategorytype3code());
				uploadLibraryXSLVo.setSubcategorytype3name(UploadLibraryXlsPOJO.getSubcategorytype3name());
				uploadLibraryXSLVo.setSubcategory3status(UploadLibraryXlsPOJO.getSubcategory3status());
				uploadLibraryXSLVo.setSubcategory3description(UploadLibraryXlsPOJO.getSubcategory3description());


				int count1=0;
				int count2=0;
				int count3=0;
				int count4=0;
				int count5=0;
				checkCategoryTypeName = daoImpl.checkLibrarytypeName(UploadLibraryXlsPOJO.getCategorytypecode(),UploadLibraryXlsPOJO.getCategorytypename(), connection);
				pstmt= connection.prepareStatement("SELECT COUNT(`category_code`) FROM `library_category` WHERE `category_code`=?");
				pstmt.setString(1,UploadLibraryXlsPOJO.getCategorytypecode());
				rs = pstmt.executeQuery();
				while(rs.next()){
					count1=rs.getInt(1);
				}
				rs.close();
				pstmt2 =connection.prepareStatement("SELECT COUNT(`subcategory_code`) FROM `library_subcategory` WHERE `subcategory_code`=?");
				pstmt.setString(1,UploadLibraryXlsPOJO.getSubcategorytypecode());
				rs = pstmt.executeQuery();
				while(rs.next()){
					count2=rs.getInt(1);
				}
				rs.close();

				pstmt3 =connection.prepareStatement("SELECT COUNT(`subcategory1_code`) FROM `library_subcategory1` WHERE `subcategory1_code`=?");
				pstmt.setString(1,UploadLibraryXlsPOJO.getSubcategorytype1code());
				rs = pstmt.executeQuery();
				while(rs.next()){
					count3=rs.getInt(1);
				}
				rs.close();

				pstmt4 = connection.prepareStatement("SELECT COUNT(`subcategory2_code`) FROM `library_subcategory2` WHERE `subcategory2_code`=? ");
				pstmt.setString(1,
						UploadLibraryXlsPOJO.getSubcategorytype1code());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count4 = rs.getInt(1);
				}
				rs.close();
				pstmt5 = connection.prepareStatement("SELECT COUNT(`subcategory3_code`) FROM `library_subcategory3` WHERE `subcategory3_code`=?");
				pstmt.setString(1,UploadLibraryXlsPOJO.getSubcategorytype1code());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count5 = rs.getInt(1);
				}
				rs.close();

				if (count1 > 0) {
					uploadLibraryXSLVo.setReasone("Duplicate Category Type Code");
					failurelist.add(uploadLibraryXSLVo);
				}
				else if (count2 > 0) {
					uploadLibraryXSLVo.setReasone("Duplicate Sub Category Type Code");
					failurelist.add(uploadLibraryXSLVo);
				}
				else if (count3 > 0) {
					uploadLibraryXSLVo.setReasone("Duplicate Sub Category Type Code");
					failurelist.add(uploadLibraryXSLVo);
				}
				else if (count4 > 0) {
					uploadLibraryXSLVo.setReasone("Duplicate Sub Category Type Code");
					failurelist.add(uploadLibraryXSLVo);
				}
				else if (count5 > 0) {
					uploadLibraryXSLVo.setReasone("Duplicate Sub Category Type Code");
					failurelist.add(uploadLibraryXSLVo);
				}else if(UploadLibraryXlsPOJO.getCategorytypecode()=="" || UploadLibraryXlsPOJO.getCategorytypecode().equals("-")){
					uploadLibraryXSLVo.setReasone("Category Type Code Should Not Empty");
					failurelist.add(uploadLibraryXSLVo);
				}else if(!(UploadLibraryXlsPOJO.getCategorytypecode().matches(int_regex))){
					uploadLibraryXSLVo.setReasone("Please Enter the valid Category Type Code");
					failurelist.add(uploadLibraryXSLVo);
				}else if(UploadLibraryXlsPOJO.getCategorytypename()=="" || UploadLibraryXlsPOJO.getCategorytypename().equals("-")){
					uploadLibraryXSLVo.setReasone("Category Type Name Should Not Empty");
					failurelist.add(uploadLibraryXSLVo);
				}else if(UploadLibraryXlsPOJO.getCategorystatus()=="" || UploadLibraryXlsPOJO.getCategorystatus().equals("-")){
					uploadLibraryXSLVo.setReasone("Status Should Not Empty ");
					failurelist.add(uploadLibraryXSLVo);
				}else if(!(UploadLibraryXlsPOJO.getCategorystatus().matches(status))){
					uploadLibraryXSLVo.setReasone("Please Enter the valid Status");
					failurelist.add(uploadLibraryXSLVo);
				}
				
				else if(UploadLibraryXlsPOJO.getSubcategorytypecode()=="" || UploadLibraryXlsPOJO.getSubcategorytypecode().equals("-")){
					uploadLibraryXSLVo.setReasone("Sub Category Type Code Should Not Empty");
					failurelist.add(uploadLibraryXSLVo);
				}else if(!(UploadLibraryXlsPOJO.getSubcategorytypecode().matches(int_regex))){
					uploadLibraryXSLVo.setReasone("Please Enter the valid Sub Category Type Code");
					failurelist.add(uploadLibraryXSLVo);
				}
				else if(UploadLibraryXlsPOJO.getSubcategorytypename()=="" || UploadLibraryXlsPOJO.getSubcategorytypename().equals("-")){
					uploadLibraryXSLVo.setReasone("Sub Category Type Name Should Not Empty");
					failurelist.add(uploadLibraryXSLVo);
				}
				else if(UploadLibraryXlsPOJO.getSubcategorystatus()=="" || UploadLibraryXlsPOJO.getSubcategorystatus().equals("-")){
					uploadLibraryXSLVo.setReasone("Status Should Not Empty ");
					failurelist.add(uploadLibraryXSLVo);
				}else if(!(UploadLibraryXlsPOJO.getSubcategorystatus().matches(status))){
					uploadLibraryXSLVo.setReasone("Please Enter the valid Status");
					failurelist.add(uploadLibraryXSLVo);
				}
				
				
				else if(UploadLibraryXlsPOJO.getSubcategorytype1code()=="" || UploadLibraryXlsPOJO.getSubcategorytype1code().equals("-")){
					uploadLibraryXSLVo.setReasone("Sub Category Type 1 Code Should Not Empty");
					failurelist.add(uploadLibraryXSLVo);
				}else if(!(UploadLibraryXlsPOJO.getSubcategorytype1code().matches(int_regex))){
					uploadLibraryXSLVo.setReasone("Please Enter the valid Sub Category Type 1 Code");
					failurelist.add(uploadLibraryXSLVo);
				}else if(UploadLibraryXlsPOJO.getSubcategorytype1name()=="" || UploadLibraryXlsPOJO.getSubcategorytype1name().equals("-")){
					uploadLibraryXSLVo.setReasone("Sub Category Type 1 Name Should Not Empty");
					failurelist.add(uploadLibraryXSLVo);
				}else if(UploadLibraryXlsPOJO.getSubcategory1status()=="" || UploadLibraryXlsPOJO.getSubcategory1status().equals("-")){
					uploadLibraryXSLVo.setReasone("Status Should Not Empty ");
					failurelist.add(uploadLibraryXSLVo);
				}else if(!(UploadLibraryXlsPOJO.getSubcategory1status().matches(status))){
					uploadLibraryXSLVo.setReasone("Please Enter the valid Status");
					failurelist.add(uploadLibraryXSLVo);
				}
				
				else if(UploadLibraryXlsPOJO.getSubcategorytype2code()=="" || UploadLibraryXlsPOJO.getSubcategorytype2code().equals("-")){
					uploadLibraryXSLVo.setReasone("Sub Category Type 2 Code Should Not Empty");
					failurelist.add(uploadLibraryXSLVo);
				}else if(!(UploadLibraryXlsPOJO.getSubcategorytype2code().matches(int_regex))){
					uploadLibraryXSLVo.setReasone("Please Enter the valid Sub Category Type 2 Code");
					failurelist.add(uploadLibraryXSLVo);
				}
				else if(UploadLibraryXlsPOJO.getSubcategorytype2name()=="" || UploadLibraryXlsPOJO.getSubcategorytype2name().equals("-")){
					uploadLibraryXSLVo.setReasone("Sub Category Type 2 Name Should Not Empty");
					failurelist.add(uploadLibraryXSLVo);
				}
				else if(UploadLibraryXlsPOJO.getSubcategory2status()=="" || UploadLibraryXlsPOJO.getSubcategory2status().equals("-")){
					uploadLibraryXSLVo.setReasone("Status Should Not Empty ");
					failurelist.add(uploadLibraryXSLVo);
				}else if(!(UploadLibraryXlsPOJO.getSubcategory2status().matches(status))){
					uploadLibraryXSLVo.setReasone("Please Enter the valid Status");
					failurelist.add(uploadLibraryXSLVo);
				}
				
				
				else if(UploadLibraryXlsPOJO.getSubcategorytype3code()=="" || UploadLibraryXlsPOJO.getSubcategorytype3code().equals("-")){
					uploadLibraryXSLVo.setReasone("Sub Category Type 3 Code Should Not Empty");
					failurelist.add(uploadLibraryXSLVo);
				}else if(!(UploadLibraryXlsPOJO.getSubcategorytype3code().matches(int_regex))){
					uploadLibraryXSLVo.setReasone("Please Enter the valid Sub Category Type 3 Code");
					failurelist.add(uploadLibraryXSLVo);
				}
				else if(UploadLibraryXlsPOJO.getSubcategorytype3name()=="" || UploadLibraryXlsPOJO.getSubcategorytype3name().equals("-")){
					uploadLibraryXSLVo.setReasone("Sub Category Type 3 Name Should Not Empty");
					failurelist.add(uploadLibraryXSLVo);
				}
				else if(UploadLibraryXlsPOJO.getSubcategory3status()=="" || UploadLibraryXlsPOJO.getSubcategory3status().equals("-")){
					uploadLibraryXSLVo.setReasone("Status Should Not Empty ");
					failurelist.add(uploadLibraryXSLVo);
				}else if(!(UploadLibraryXlsPOJO.getSubcategory3status().matches(status))){
					uploadLibraryXSLVo.setReasone("Please Enter the valid Status");
					failurelist.add(uploadLibraryXSLVo);
				}
				
				else{
					successlist.add(UploadLibraryXlsPOJO);
				}
			}
			Set<UploadLibraryXlsVO> failureListFromDiompl=new LinkedHashSet<UploadLibraryXlsVO>();
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertLibXSL(successlist,connection,username);
			}
			for (Iterator<UploadLibraryXlsVO> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				UploadLibraryXlsVO failureDiomplVo = (UploadLibraryXlsVO) it.next();
				UploadLibraryXlsVO uploadLibraryXSLVo = new UploadLibraryXlsVO();

				uploadLibraryXSLVo.setCategorytypename(failureDiomplVo.getCategorytypename());
				uploadLibraryXSLVo.setCategorytypecode(failureDiomplVo.getCategorytypecode());
				uploadLibraryXSLVo.setCategorystatus(failureDiomplVo.getCategorystatus());
				uploadLibraryXSLVo.setCategorydescription(failureDiomplVo.getCategorydescription());

				uploadLibraryXSLVo.setSubcategorytypename(failureDiomplVo.getSubcategorytypename());
				uploadLibraryXSLVo.setSubcategorytypecode(failureDiomplVo.getSubcategorytypecode());
				uploadLibraryXSLVo.setSubcategorystatus(failureDiomplVo.getSubcategorystatus());
				uploadLibraryXSLVo.setSubcategorydescription(failureDiomplVo.getSubcategorydescription());

				uploadLibraryXSLVo.setSubcategorytype1name(failureDiomplVo.getSubcategorytype1name());
				uploadLibraryXSLVo.setSubcategorytype1code(failureDiomplVo.getSubcategorytype1code());
				uploadLibraryXSLVo.setSubcategory1status(failureDiomplVo.getSubcategory1status());
				uploadLibraryXSLVo.setSubcategory1description(failureDiomplVo.getSubcategory1description());

				uploadLibraryXSLVo.setSubcategorytype2name(failureDiomplVo.getSubcategorytype2name());
				uploadLibraryXSLVo.setSubcategorytype2code(failureDiomplVo.getSubcategorytype2code());
				uploadLibraryXSLVo.setSubcategory2status(failureDiomplVo.getSubcategory2status());
				uploadLibraryXSLVo.setSubcategory2description(failureDiomplVo.getSubcategory2description());

				uploadLibraryXSLVo.setSubcategorytype3name(failureDiomplVo.getSubcategorytype3name());
				uploadLibraryXSLVo.setSubcategorytype3code(failureDiomplVo.getSubcategorytype3code());
				uploadLibraryXSLVo.setSubcategory3status(failureDiomplVo.getSubcategory3status());
				uploadLibraryXSLVo.setSubcategory3description(failureDiomplVo.getSubcategory3description());
				uploadLibraryXSLVo.setReasone(failureDiomplVo.getReasone());

				failurelist.add(uploadLibraryXSLVo);
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
				}if(pstmt3!=null && (!pstmt3.isClosed())){
					pstmt3.close();
				}if(pstmt4!=null && (!pstmt4.isClosed())){
					pstmt4.close();
				}if(pstmt5!=null && (!pstmt5.isClosed())){
					pstmt5.close();
				}if(connection!=null && (!connection.isClosed())){
					connection.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadLibraryXSLServiceImpl : insertEmpXSL : Ending");
		return failurelist;
	}

	public Set<UploadLibraryXlsVO> insertsubscriberDetailXSL(List<UploadLibraryXlsPOJO> list,String username,String demo,String schoolLocation) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLServiceImpl : insertEmpXSL : Starting");

		Connection connection = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null,pstmt3=null,pstmt4=null,pstmt5=null;
		ResultSet rs=null,rs1=null;
		int count1=0;
		List<UploadLibraryXlsPOJO> successlist=new ArrayList<UploadLibraryXlsPOJO>();
		UploadLibraryXLSDaoImpl daoImpl=new UploadLibraryXLSDaoImpl();

		Set<UploadLibraryXlsVO> failurelist = new LinkedHashSet<UploadLibraryXlsVO>();

		failurelist.clear();
		successlist.clear();

		try {
			connection=JDBCConnection.getSeparateConnection();
			String liblocid=null,stuId=null;
			String int_regex="^[0-9a-zA-Z]*$";
			String status="^[a-zA-Z]*$";
			String othersname="^[a-zA-Z.]*$";
			String numbers_regx = "[0-9//]{10}";
			String amount ="^[0-9]*$";
			String cardNo ="^[0-9]*$";
			String regexpforEmailId="/[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]*$/i";
			String datePattern ="\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
			/*String string_regx="([a-zA-Z.,-)(]+\\s+)*[a-zA-Z.,-)(]+";
			*/

			for (Iterator<UploadLibraryXlsPOJO> iterator = list.iterator(); iterator.hasNext();) {

				UploadLibraryXlsPOJO UploadLibraryXlsPOJO = (UploadLibraryXlsPOJO) iterator.next();
				UploadLibraryXlsVO uploadLibraryXSLVo = new UploadLibraryXlsVO();
				uploadLibraryXSLVo.setLocation(UploadLibraryXlsPOJO.getLocation());
				uploadLibraryXSLVo.setSubscriberType(UploadLibraryXlsPOJO.getSubscriberType());
				uploadLibraryXSLVo.setSubscriberNumber(UploadLibraryXlsPOJO.getSubscriberNumber());
				uploadLibraryXSLVo.setStaffID(UploadLibraryXlsPOJO.getStaffID());
				uploadLibraryXSLVo.setAdmissionNumber(UploadLibraryXlsPOJO.getAdmissionNumber());
				uploadLibraryXSLVo.setContactNumber(UploadLibraryXlsPOJO.getContactNumber());
				uploadLibraryXSLVo.setEmailID(UploadLibraryXlsPOJO.getEmailID());
				uploadLibraryXSLVo.setName(UploadLibraryXlsPOJO.getName());
				uploadLibraryXSLVo.setGender(UploadLibraryXlsPOJO.getGender());
				uploadLibraryXSLVo.setDepositType(UploadLibraryXlsPOJO.getDepositType());
				uploadLibraryXSLVo.setAmount(UploadLibraryXlsPOJO.getAmount());
				uploadLibraryXSLVo.setCradNo(UploadLibraryXlsPOJO.getCradNo());
				uploadLibraryXSLVo.setStatus(UploadLibraryXlsPOJO.getStatus());
				uploadLibraryXSLVo.setDate(UploadLibraryXlsPOJO.getDate());
				uploadLibraryXSLVo.setAddress(UploadLibraryXlsPOJO.getAddress());

				
				if(!UploadLibraryXlsPOJO.getLocation().equalsIgnoreCase("")){
					String locName=UploadLibraryXlsPOJO.getLocation();
					pstmt =connection.prepareStatement("SELECT library_loc_id FROM campus_library_location WHERE library_location_name=?");
					pstmt.setString(1,locName);
					rs = pstmt.executeQuery();
					if(rs.next()){
						liblocid=rs.getString(1);
					}else{
						liblocid = "notfound";
					}
					rs.close();
				}
				
				if(!liblocid.equalsIgnoreCase("notfound") && liblocid!=null){
					
					pstmt1 =connection.prepareStatement("SELECT  count(subscriberNumber) FROM campus_library_subscriber_details WHERE subscriberNumber=?");
					pstmt1.setString(1, UploadLibraryXlsPOJO.getSubscriberNumber());
						 rs1 = pstmt1.executeQuery();
						 while(rs1.next()){
							 count1=rs1.getInt(1);
						 }
					
					if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("student") && !UploadLibraryXlsPOJO.getAdmissionNumber().equalsIgnoreCase(""))
					{
						pstmt =connection.prepareStatement("SELECT student_id_int FROM campus_student WHERE student_admissionno_var=?");
						pstmt.setString(1, UploadLibraryXlsPOJO.getAdmissionNumber());
						System.out.println("Inside the Student Validations"+pstmt);
						 rs = pstmt.executeQuery();
						 if(rs.next()){
						     stuId=rs.getString(1);
						 }
						 else{
							 stuId="no_stuId";
						 }
					}
					if((UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("staff") || UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("teacher")) && !UploadLibraryXlsPOJO.getAdmissionNumber().equalsIgnoreCase(" "))
					{
						pstmt =connection.prepareStatement("SELECT TeacherID FROM campus_teachers WHERE registerId=?");
						pstmt.setString(1, UploadLibraryXlsPOJO.getAdmissionNumber());
						System.out.println("Inside the Teacher Validations"+pstmt);
						 rs = pstmt.executeQuery();
						 if(rs.next()){
							 stuId=rs.getString(1);
						 }
						 else{
							 stuId="no_stuId";
						 }
					}
				}
					if(UploadLibraryXlsPOJO.getLocation().equalsIgnoreCase("")){
						uploadLibraryXSLVo.setReasone("Library Location Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}else if(liblocid.equalsIgnoreCase("notfound")){
						uploadLibraryXSLVo.setReasone("Library Location doesn't Exists");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("")){
						uploadLibraryXSLVo.setReasone("SubscriberType Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(count1>0)
					{
						uploadLibraryXSLVo.setReasone("SubscriberNumber Should Not Duplicate");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getSubscriberNumber().equalsIgnoreCase("")||UploadLibraryXlsPOJO.getSubscriberNumber().equalsIgnoreCase("-")){
						uploadLibraryXSLVo.setReasone("SubscriberNumber Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("student") && UploadLibraryXlsPOJO.getAdmissionNumber().equalsIgnoreCase("")){
						uploadLibraryXSLVo.setReasone("Student Admission No. Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("student") && stuId.equalsIgnoreCase("no_stuId")){
						uploadLibraryXSLVo.setReasone(" Admission No. doesn't Exists");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("staff") && UploadLibraryXlsPOJO.getAdmissionNumber().equalsIgnoreCase("")){
						uploadLibraryXSLVo.setReasone("Staff Id Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("staff") && stuId.equalsIgnoreCase("no_stuId")){
						uploadLibraryXSLVo.setReasone("Staff Id doesn't Exists");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("others") && UploadLibraryXlsPOJO.getName().equalsIgnoreCase("")){
						uploadLibraryXSLVo.setReasone("others Name Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("others") && !UploadLibraryXlsPOJO.getName().matches(othersname)){
						uploadLibraryXSLVo.setReasone("Invalid others Name");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("others") && UploadLibraryXlsPOJO.getContactNumber().equalsIgnoreCase("")){
						uploadLibraryXSLVo.setReasone("Others Contact No. Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("others") && !UploadLibraryXlsPOJO.getContactNumber().matches(numbers_regx)){
						uploadLibraryXSLVo.setReasone("Invalid Others Contact No");
						failurelist.add(uploadLibraryXSLVo);
					}
					
					
					else if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("others") && UploadLibraryXlsPOJO.getEmailID().equalsIgnoreCase("")){
						uploadLibraryXSLVo.setReasone("Others EmailID Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("others") && !UploadLibraryXlsPOJO.getEmailID().matches(regexpforEmailId)){
						uploadLibraryXSLVo.setReasone("Invalid Others EmailID");
						failurelist.add(uploadLibraryXSLVo);
					}
					
					
					else if(UploadLibraryXlsPOJO.getSubscriberType().equalsIgnoreCase("others") && (UploadLibraryXlsPOJO.getAddress().equalsIgnoreCase("")|| UploadLibraryXlsPOJO.getAddress().equalsIgnoreCase("-"))){
						uploadLibraryXSLVo.setReasone("Others Address Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getDepositType().equalsIgnoreCase(" ") || UploadLibraryXlsPOJO.getDepositType().equals("-")){
						uploadLibraryXSLVo.setReasone("DepositType Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getDepositType().equalsIgnoreCase("cash") && UploadLibraryXlsPOJO.getAmount().equalsIgnoreCase(" "))
					{
						uploadLibraryXSLVo.setReasone("Amount Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}		
					else if(UploadLibraryXlsPOJO.getDepositType().equalsIgnoreCase("cash") && !UploadLibraryXlsPOJO.getAmount().matches(amount)){
						uploadLibraryXSLVo.setReasone("Invalid Amount");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getDepositType().equalsIgnoreCase("cheque") && UploadLibraryXlsPOJO.getCradNo().equalsIgnoreCase(" ")){
						uploadLibraryXSLVo.setReasone("Cheque No. Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getDepositType().equalsIgnoreCase("cheque") && !UploadLibraryXlsPOJO.getCradNo().matches(int_regex)){
						uploadLibraryXSLVo.setReasone("Invalid Cheque No.");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getDepositType().equalsIgnoreCase("card") && UploadLibraryXlsPOJO.getCradNo().equalsIgnoreCase(" ")){
						uploadLibraryXSLVo.setReasone("Card No. Should Not Empty");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getDepositType().equalsIgnoreCase("card") && !UploadLibraryXlsPOJO.getCradNo().matches(cardNo)){
						uploadLibraryXSLVo.setReasone("Invalid Card No.");
						failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getDate()=="" || UploadLibraryXlsPOJO.getDate().equals("-")){
									System.out.println("Date");
									uploadLibraryXSLVo.setReasone("Date Should Not Empty ");
									failurelist.add(uploadLibraryXSLVo);
					}
					else if(UploadLibraryXlsPOJO.getDate()=="" || !UploadLibraryXlsPOJO.getDate().matches(datePattern)){
						System.out.println("Date");
						uploadLibraryXSLVo.setReasone("Invalid Date Entry ");
						failurelist.add(uploadLibraryXSLVo);
	              	}
					else if(!(UploadLibraryXlsPOJO.getStatus().matches(status))){
									System.out.println("Status");
									uploadLibraryXSLVo.setReasone("Please Enter the valid Status");
									failurelist.add(uploadLibraryXSLVo);
					}
					else{
						successlist.add(UploadLibraryXlsPOJO);
					}
				
			}
			Set<UploadLibraryXlsVO> failureListFromDiompl=new LinkedHashSet<UploadLibraryXlsVO>();
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertsubscriberDetailXSL(successlist,connection);
			}
			for (Iterator<UploadLibraryXlsVO> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				UploadLibraryXlsVO failureDiomplVo = (UploadLibraryXlsVO) it.next();
				UploadLibraryXlsVO uploadLibraryXSLVo = new UploadLibraryXlsVO();
				uploadLibraryXSLVo.setReasone(failureDiomplVo.getReasone());
				uploadLibraryXSLVo.setLocation(failureDiomplVo.getLocation());
				uploadLibraryXSLVo.setSubscriberType(failureDiomplVo.getSubscriberType());
				uploadLibraryXSLVo.setSubscriberNumber(failureDiomplVo.getSubscriberNumber());
				uploadLibraryXSLVo.setAdmissionNumber(failureDiomplVo.getAdmissionNumber());
				uploadLibraryXSLVo.setGender(failureDiomplVo.getGender());
				uploadLibraryXSLVo.setContactNumber(failureDiomplVo.getContactNumber());
				uploadLibraryXSLVo.setName(failureDiomplVo.getName());
				uploadLibraryXSLVo.setEmailID(failureDiomplVo.getEmailID());
				uploadLibraryXSLVo.setDepositType(failureDiomplVo.getDepositType());
				uploadLibraryXSLVo.setAmount(failureDiomplVo.getAmount());
				uploadLibraryXSLVo.setCradNo(failureDiomplVo.getCradNo());
				uploadLibraryXSLVo.setStatus(failureDiomplVo.getStatus());
				uploadLibraryXSLVo.setDate(failureDiomplVo.getDate());
				uploadLibraryXSLVo.setAddress(failureDiomplVo.getAddress());
				failurelist.add(uploadLibraryXSLVo);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}finally{
			try {
				if(rs!=null && (!rs.isClosed())){
					connection.close();
				}
				if(rs1!=null && (!rs1.isClosed())){
					connection.close();
				}
				if(pstmt!=null && (!pstmt.isClosed())){
					connection.close();
				}if(pstmt1!=null && (!pstmt1.isClosed())){
					pstmt1.close();
				}if(pstmt2!=null && (!pstmt2.isClosed())){
					pstmt2.close();
				}if(pstmt3!=null && (!pstmt3.isClosed())){
					pstmt3.close();
				}if(pstmt4!=null && (!pstmt4.isClosed())){
					pstmt4.close();
				}if(pstmt5!=null && (!pstmt5.isClosed())){
					pstmt5.close();
				}if(connection!=null && (!connection.isClosed())){
					connection.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadLibraryXSLServiceImpl : insertEmpXSL : Ending");
		return failurelist;
	}
	
	public Set<StockEntryVo> insertStockEntryXSL(List<StockEntryPOJO> list, String username, String duplicate,String currentLoc)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadEmpXSLServiceImpl : insertEmpXSL : Starting");

		Connection conn = null;
		PreparedStatement pstmt=null,pstmt1=null,pstmt2=null,pstmt3=null,pstmt4=null,pstmt5=null,pstmt6=null,pstmt7=null,pstmt8=null;
		ResultSet rs=null,rs1=null,rs4=null,rs2=null,rs3=null,rs7=null,rs8=null,rs6=null;
		List<StockEntryVo> successlist=new ArrayList<StockEntryVo>();
		UploadLibraryXLSDaoImpl daoImpl=new UploadLibraryXLSDaoImpl();

		StockEntryPOJO StockEntryPOJO=new StockEntryPOJO();
		
		int count = 0;
		boolean pancardflag=false;
		boolean personalemailflag=false;
		boolean officialemailflag=false;
		int checkCategoryTypeName=0;

		Set<StockEntryVo> failurelist = new LinkedHashSet<StockEntryVo>();

		failurelist.clear();
		successlist.clear();

		try {

			conn=JDBCConnection.getSeparateConnection();

			String int_regex="^[0-9a-zA-Z]*$";
			String status="^[a-zA-Z]*$";
			String datePattern ="\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
			/*String numbers_regx = "[0-9//]{10}";
			String string_regx="([a-zA-Z.,-)(]+\\s+)*[a-zA-Z.,-)(]+";
			String datePattern = "\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
			String regexpforEmailId="/[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]*$/i";*/

			for (Iterator<StockEntryPOJO> iterator = list.iterator(); iterator.hasNext();) {
				
				    String Category=null;
	                String SubCategory=null;
	                String SubCategoryType1=null;
	                String SubCategoryType2=null;
	                String LibraryLocation=null;
	                String supplier=null;
	                String publisher=null;
				StockEntryPOJO StockEntryxLSPOJO = (StockEntryPOJO) iterator.next();
				System.out.println("SERVICE IMPL COUNT "+count);
				StockEntryVo UploadStockEntryXLSVo= new StockEntryVo();
				UploadStockEntryXLSVo.setAccessionno(StockEntryxLSPOJO.getAccessionno());
				UploadStockEntryXLSVo.setAuthor(StockEntryxLSPOJO.getAuthor());
				UploadStockEntryXLSVo.setBilldate(StockEntryxLSPOJO.getBilldate());
				UploadStockEntryXLSVo.setBillno(StockEntryxLSPOJO.getBillno());
				UploadStockEntryXLSVo.setBooktitle(StockEntryxLSPOJO.getBooktitle());
				UploadStockEntryXLSVo.setCategory(StockEntryxLSPOJO.getCategory());
				UploadStockEntryXLSVo.setContentsearch(StockEntryxLSPOJO.getContentsearch());
				UploadStockEntryXLSVo.setCostpercopy(StockEntryxLSPOJO.getCostpercopy());
				UploadStockEntryXLSVo.setCurrentstatus(StockEntryxLSPOJO.getCurrentstatus());
				UploadStockEntryXLSVo.setEdition(StockEntryxLSPOJO.getEdition());
				UploadStockEntryXLSVo.setEditor(StockEntryxLSPOJO.getEditor());
				UploadStockEntryXLSVo.setGeneralinfo(StockEntryxLSPOJO.getGeneralinfo());
				UploadStockEntryXLSVo.setIsbnno(StockEntryxLSPOJO.getIsbnno());
				UploadStockEntryXLSVo.setItemtype(StockEntryxLSPOJO.getItemtype());
				UploadStockEntryXLSVo.setLanguage(StockEntryxLSPOJO.getLanguage());
				UploadStockEntryXLSVo.setLibrarylocation(StockEntryxLSPOJO.getLibrarylocation());
				UploadStockEntryXLSVo.setNoofcopies(StockEntryxLSPOJO.getNoofcopies());
				UploadStockEntryXLSVo.setPublicationyear(StockEntryxLSPOJO.getPublicationyear());
				UploadStockEntryXLSVo.setPublisher(StockEntryxLSPOJO.getPublisher());
				UploadStockEntryXLSVo.setRegisterdDate(StockEntryxLSPOJO.getRegisterdDate());
				UploadStockEntryXLSVo.setShelfno(StockEntryxLSPOJO.getShelfno());
				UploadStockEntryXLSVo.setSize(StockEntryxLSPOJO.getSize());
				UploadStockEntryXLSVo.setSubcategorytype1(StockEntryxLSPOJO.getSubcategorytype1());
				UploadStockEntryXLSVo.setSubcategorytype2(StockEntryxLSPOJO.getSubcategorytype2());
				UploadStockEntryXLSVo.setSubCtegory(StockEntryxLSPOJO.getSubCtegory());
				UploadStockEntryXLSVo.setSuppliedby(StockEntryxLSPOJO.getSuppliedby());
				UploadStockEntryXLSVo.setTotalcost(StockEntryxLSPOJO.getTotalcost());
				UploadStockEntryXLSVo.setNoofpages(StockEntryxLSPOJO.getNoofpages());
				
				System.out.println("NO OF PAGES "+StockEntryxLSPOJO.getNoofpages());
				
				pstmt6=conn.prepareStatement("SELECT `library_loc_id`  FROM `campus_library_location` WHERE `library_location_name`=?");
			     pstmt6.setString(1,StockEntryxLSPOJO.getLibrarylocation());
			     rs6=pstmt6.executeQuery();
			     if(rs6.next()){
			    	 LibraryLocation=rs6.getString(1);
			     }else{
			    	 LibraryLocation="no";
			     }
				pstmt1=conn.prepareStatement("SELECT COUNT(`Accession_number`) FROM `campus_library_stock_entry` WHERE `Accession_number`=?");
				pstmt1.setString(1, StockEntryxLSPOJO.getAccessionno());
				rs=pstmt1.executeQuery();
				while(rs.next()){
	            count=rs.getInt(1);				
				}
				System.out.println("NULL CHECK "+UploadStockEntryXLSVo.getItemtype());
				
				pstmt1=conn.prepareStatement("SELECT `category_code` FROM `library_category` WHERE `category_name`=?");
			     pstmt1.setString(1,StockEntryxLSPOJO.getCategory());
			     rs1=pstmt1.executeQuery();
			     if(rs1.next()){
			    	 Category=rs1.getString(1);
			     }else{
			    	 Category="no";
			     }
			     pstmt2=conn.prepareStatement("SELECT `subcategory_code` FROM `library_subcategory` WHERE `subcategory_name`=?");
			     pstmt2.setString(1,StockEntryxLSPOJO.getSubCtegory());
			     rs2=pstmt2.executeQuery();
			     if(rs2.next()){
			    	 SubCategory=rs2.getString(1);
			     }else{
			    	 SubCategory="no";
			     }	    
			     pstmt3=conn.prepareStatement("SELECT `subcategory1_code` FROM `library_subcategory1` WHERE `subcategory1_name`=?");
			     pstmt3.setString(1,StockEntryxLSPOJO.getSubcategorytype1());
			     rs3=pstmt3.executeQuery();
			     if(rs3.next()){
			    	 SubCategoryType1=rs3.getString(1);
			     }else{
			    	 SubCategoryType1="no";
			     }	  
			     pstmt4=conn.prepareStatement("SELECT `subcategory2_code` FROM `library_subcategory2` WHERE `subcategory2_name`=?");
			     pstmt4.setString(1,StockEntryxLSPOJO.getSubcategorytype2());
			     rs4=pstmt4.executeQuery();
			     if(rs4.next()){
			    	 SubCategoryType2=rs4.getString(1);
			     }else{
			    	 SubCategoryType2="no";
			     }
			     pstmt7=conn.prepareStatement("SELECT `Entry_Id` FROM `campus_library_supplier_settings` WHERE `Supplier_Name`=?");
			     pstmt7.setString(1,StockEntryxLSPOJO.getSuppliedby());
			     rs7=pstmt7.executeQuery();
			     if(rs7.next()){
			    	 supplier=rs7.getString(1);
			     }else{
			    	 supplier="no";
			     }
			     pstmt8=conn.prepareStatement("SELECT`Entry_id` FROM `campus_library_publisher_settings` WHERE `Publisher_Name`=?");
			     pstmt8.setString(1,StockEntryxLSPOJO.getPublisher());
			     rs8=pstmt8.executeQuery();
			     if(rs8.next()){
			    	 publisher=rs8.getString(1);
			     }else{
			    	 publisher="no";
			     }
				
				if(count>0){
					UploadStockEntryXLSVo.setReason("Duplicate Accession No");
					failurelist.add(UploadStockEntryXLSVo);
					}
				else if(UploadStockEntryXLSVo.getItemtype()==""){
					UploadStockEntryXLSVo.setReason("Item Type cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}
				else if(LibraryLocation.equalsIgnoreCase("no")){
					UploadStockEntryXLSVo.setReason("LibraryLocation Not Found");
					failurelist.add(UploadStockEntryXLSVo);
				}
				else if(Category.equalsIgnoreCase("no")){
					UploadStockEntryXLSVo.setReason("Invalied Category Type");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(SubCategory.equalsIgnoreCase("no")){
					UploadStockEntryXLSVo.setReason("Invalied Sub Category Type");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(SubCategoryType1.equalsIgnoreCase("no")){
					UploadStockEntryXLSVo.setReason("Invalied Sub Category Type 1");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(SubCategoryType2.equalsIgnoreCase("no")){
					UploadStockEntryXLSVo.setReason("Invalied Sub Category Type 2");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(supplier.equalsIgnoreCase("no")){
					UploadStockEntryXLSVo.setReason("Supplier Not Found");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(publisher.equalsIgnoreCase("no")){
					UploadStockEntryXLSVo.setReason("Publisher Not Found");
					failurelist.add(UploadStockEntryXLSVo);
				}
				
				else if(UploadStockEntryXLSVo.getRegisterdDate()==""){
					UploadStockEntryXLSVo.setReason("Reg Date cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getBooktitle()==""){
					UploadStockEntryXLSVo.setReason("Reg Date cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(!UploadStockEntryXLSVo.getRegisterdDate().matches(datePattern)){
					UploadStockEntryXLSVo.setReason("Invalid Reg Date");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(StockEntryPOJO.getAuthor()==""){
					UploadStockEntryXLSVo.setReason("Author cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getCategory()==""){
					UploadStockEntryXLSVo.setReason("Category cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getSubCtegory()==""){
					UploadStockEntryXLSVo.setReason("SubCtegory cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getSubcategorytype1()==""){
					UploadStockEntryXLSVo.setReason("Subcategory Type1 cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getSubcategorytype2()==""){
					UploadStockEntryXLSVo.setReason("ubcategory Type 2 cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getLanguage()==""){
					UploadStockEntryXLSVo.setReason("Language cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getPublisher()==""){
					UploadStockEntryXLSVo.setReason("Publisher cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getNoofcopies()==""){
					UploadStockEntryXLSVo.setReason("No of copies cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getCostpercopy()==""){
					UploadStockEntryXLSVo.setReason("Cost cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getEdition()==""){
					UploadStockEntryXLSVo.setReason("Edition cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getIsbnno()==""){
					UploadStockEntryXLSVo.setReason("ISBN no cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getSize()==""){
					UploadStockEntryXLSVo.setReason("Size cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getSuppliedby()==""){
					UploadStockEntryXLSVo.setReason("Supplier cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getGeneralinfo()==""){
					UploadStockEntryXLSVo.setReason("General Info cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getPublicationyear()==""){
					UploadStockEntryXLSVo.setReason("Publication Year cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getTotalcost()==""){
					UploadStockEntryXLSVo.setReason("Total Cost cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getEditor()==""){
					UploadStockEntryXLSVo.setReason("Editor cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getBilldate()==""){
					UploadStockEntryXLSVo.setReason("Bill Date cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(!UploadStockEntryXLSVo.getBilldate().matches(datePattern)){
					UploadStockEntryXLSVo.setReason("Invalid Bill Date");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getContentsearch()==""){
					UploadStockEntryXLSVo.setReason("content cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getShelfno()==""){
					UploadStockEntryXLSVo.setReason("Shelf No cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getLibrarylocation()==""){
					UploadStockEntryXLSVo.setReason("Location cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}else if(UploadStockEntryXLSVo.getCurrentstatus()==""){
					UploadStockEntryXLSVo.setReason("Current cannot be null");
					failurelist.add(UploadStockEntryXLSVo);
				}
				else{
					successlist.add(UploadStockEntryXLSVo);
				}
			}
			Set<StockEntryVo> failureListFromDiompl=new LinkedHashSet<StockEntryVo>();
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertStockEntryXSL(successlist,conn,username);
			}
			for (Iterator<StockEntryVo> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				StockEntryVo failureStockEntryVo = (StockEntryVo) it.next();
				StockEntryVo UploadStockEntryXLSVo= new StockEntryVo();
				UploadStockEntryXLSVo.setAccessionno(failureStockEntryVo.getAccessionno());
				UploadStockEntryXLSVo.setAuthor(failureStockEntryVo.getAuthor());
				UploadStockEntryXLSVo.setBilldate(failureStockEntryVo.getBilldate());
				UploadStockEntryXLSVo.setBillno(failureStockEntryVo.getBillno());
				UploadStockEntryXLSVo.setBooktitle(failureStockEntryVo.getBooktitle());
				UploadStockEntryXLSVo.setCategory(failureStockEntryVo.getCategory());
				UploadStockEntryXLSVo.setContentsearch(failureStockEntryVo.getContentsearch());
				UploadStockEntryXLSVo.setCostpercopy(failureStockEntryVo.getCostpercopy());
				UploadStockEntryXLSVo.setCurrentstatus(failureStockEntryVo.getCurrentstatus());
				UploadStockEntryXLSVo.setEdition(failureStockEntryVo.getEdition());
				UploadStockEntryXLSVo.setEditor(failureStockEntryVo.getEditor());
				UploadStockEntryXLSVo.setGeneralinfo(failureStockEntryVo.getGeneralinfo());
				UploadStockEntryXLSVo.setIsbnno(failureStockEntryVo.getIsbnno());
				UploadStockEntryXLSVo.setItemtype(failureStockEntryVo.getItemtype());
				UploadStockEntryXLSVo.setLanguage(failureStockEntryVo.getLanguage());
				UploadStockEntryXLSVo.setLibrarylocation(failureStockEntryVo.getLibrarylocation());
				UploadStockEntryXLSVo.setNoofcopies(failureStockEntryVo.getNoofcopies());
				UploadStockEntryXLSVo.setPublicationyear(failureStockEntryVo.getPublicationyear());
				UploadStockEntryXLSVo.setPublisher(failureStockEntryVo.getPublisher());
				UploadStockEntryXLSVo.setRegisterdDate(failureStockEntryVo.getRegisterdDate());
				UploadStockEntryXLSVo.setShelfno(failureStockEntryVo.getShelfno());
				UploadStockEntryXLSVo.setSize(failureStockEntryVo.getSize());
				UploadStockEntryXLSVo.setSubcategorytype1(failureStockEntryVo.getSubcategorytype1());
				UploadStockEntryXLSVo.setSubcategorytype2(failureStockEntryVo.getSubcategorytype2());
				UploadStockEntryXLSVo.setSubCtegory(failureStockEntryVo.getSubCtegory());
				UploadStockEntryXLSVo.setSuppliedby(failureStockEntryVo.getSuppliedby());
				UploadStockEntryXLSVo.setTotalcost(failureStockEntryVo.getTotalcost());
				UploadStockEntryXLSVo.setNoofpages(failureStockEntryVo.getNoofpages());
				UploadStockEntryXLSVo.setReason(failureStockEntryVo.getReason());
				failurelist.add(UploadStockEntryXLSVo);
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
					conn.close();
				}if(pstmt1!=null && (!pstmt1.isClosed())){
					pstmt1.close();
				}if(pstmt2!=null && (!pstmt2.isClosed())){
					pstmt2.close();
				}if(pstmt3!=null && (!pstmt3.isClosed())){
					pstmt3.close();
				}if(pstmt4!=null && (!pstmt4.isClosed())){
					pstmt4.close();
				}if(pstmt5!=null && (!pstmt5.isClosed())){
					pstmt5.close();
				}if(conn!=null && (!conn.isClosed())){
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadLibraryXSLServiceImpl : insertEmpXSL : Ending");
		return failurelist;
	}
}