package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.itextpdf.text.log.SysoLogger;

public class IDGenerator {

	private static final Logger logger = Logger.getLogger(IDGenerator.class);

	public static String getPrimaryKeyID(String tableName) throws Exception {
		
		System.out.println("getPrimaryKeyID Started");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPrimaryKeyID Starting");
		String NextID = null;
		try {
			tableName = tableName.trim().toLowerCase();
			System.out.println("tableName "+tableName);
			int currentID = 0;
			String pre = null;
			String previousID = getPreviousID(tableName);
			System.out.println("previousID "+previousID);
			if (previousID == null) {
				currentID = 0;
			} else {
				String id = previousID.substring(3);
				currentID = Integer.parseInt(id);
				System.out.println("currentID "+currentID);
			}
			if (tableName.equalsIgnoreCase("campus_parents"))
				pre = "PAR";

			else if (tableName.equalsIgnoreCase("campus_department"))
				pre = "DEP";

			else if (tableName.equalsIgnoreCase("transport_vehicle")) {
				pre = "VEH";
			}
			else if(tableName.equalsIgnoreCase("campus_location")){
			
			pre = "LOC";
			}
			else if(tableName.equalsIgnoreCase("campus_class_specialization")){
				pre= "SPE";
			}
			else if (tableName.equalsIgnoreCase("campus_examination")) {
				pre = "EXM";
			}

			else if (tableName.equalsIgnoreCase("campus_comments")) {
				pre = "RMK";
			}

			else if (tableName.equalsIgnoreCase("campus_meeting")) {
				pre = "MET";
			}
			else if (tableName.equalsIgnoreCase("campus_dob_details")) {
				pre = "DOB";
			}
			else if (tableName.equalsIgnoreCase("campus_feedback")) {
				pre = "FED";
			}
			else if (tableName.equalsIgnoreCase("campus_classteacher")) {
				pre = "CTC";
			}
			else if (tableName.equalsIgnoreCase("campus_sec_admission_details")) {
				pre = "ACS";
			}
			else if (tableName.equalsIgnoreCase("campus_third_admission_details")) {
				pre = "ATD";
			}
			
			else if (tableName.equalsIgnoreCase("campus_subject_marks_wise")) {
				pre = "SBM";
			}
			else if (tableName.equalsIgnoreCase("campus_third_admission_details")) {
				pre = "ACT";
			}
			else if (tableName.equalsIgnoreCase("campus_fee_master"))
				pre = "CFM";
			else if (tableName.equalsIgnoreCase("campus_acadamicyear"))
				pre = "ACY";
			else if (tableName.equalsIgnoreCase("campus_designation"))
				pre = "DES";
			else if (tableName.equalsIgnoreCase("campus_classdetail"))
				pre = "CCD";
			else if (tableName.equalsIgnoreCase("campus_role"))
				pre = "ROL";

			else if (tableName.equalsIgnoreCase("campus_subject"))
				pre = "SUB";

			else if (tableName.equalsIgnoreCase("campus_classstream"))
				pre = "CLS";

			else if (tableName.equalsIgnoreCase("campus_classsection")) {
				pre = "CCS";
			}
            else if (tableName.equalsIgnoreCase("campus_studentwise_mark_details")) {
				pre = "SDM";
			}
			else if (tableName.equalsIgnoreCase("campus_quota"))
				pre = "CQA";
			else if (tableName.equalsIgnoreCase("transport_typedetails"))
				pre = "TTD";
			else if (tableName.equalsIgnoreCase("campus_teachers"))
				pre = "TEA";
			else if (tableName.equalsIgnoreCase("campus_careers"))
				pre = "JOB";
			else if (tableName.equalsIgnoreCase("campus_fee_termdetails"))
				pre = "TRM";
			else if (tableName.equalsIgnoreCase("transport_fuel_maintainence"))
				pre = "FUE";
			else if (tableName.equalsIgnoreCase("campus_accyearplan"))
				pre = "APL";
			else if (tableName.equalsIgnoreCase("campus_remainder"))
				pre = "RDR";
			else if (tableName.equalsIgnoreCase("campus_accyearplan"))
				pre = "APL";
			else if (tableName.equalsIgnoreCase("transport_route"))
				pre = "ROU";
			else if (tableName.equalsIgnoreCase("campus_fee_stage"))
				pre = "FSM";
			else if (tableName.equalsIgnoreCase("transport_stopsdetails"))
				pre = "STO";
			else if (tableName.equalsIgnoreCase("campus_classteacher"))
				pre = "CCT";
			else if (tableName.equalsIgnoreCase("campus_student"))
				pre = "STU";
			else if (tableName.equalsIgnoreCase("transport_driver"))
				pre = "DRV";
			else if (tableName.equalsIgnoreCase("campus_fee_setup"))
				pre = "FMS";
			else if (tableName.equalsIgnoreCase("campus_fee_stagesetup"))
				pre = "SFS";
			else if (tableName.equalsIgnoreCase("campus_fee_concessiondetails"))
				pre = "CNC";
			else if (tableName.equalsIgnoreCase("campus_fee_collection"))
				pre = "CFC";
			else if (tableName.equalsIgnoreCase("campus_student_enquiry"))
				pre = "ENQ";
			else if (tableName.equalsIgnoreCase("campus_studentpromotion"))
				pre = "STP";
			else if (tableName.equalsIgnoreCase("campus_assignment"))
				pre = "ASM";
			else if (tableName.equalsIgnoreCase("campus_user"))
				pre = "USR";
			else if (tableName.equalsIgnoreCase("campus_timetable_student"))
				pre = "CST";
			else if (tableName.equalsIgnoreCase("campus_timetable_teacher"))
				pre = "CTT";
			else if (tableName.equalsIgnoreCase("campus_marks_upload"))
				pre = "CMU";
			else if (tableName.equalsIgnoreCase("campus_book_issue_details"))
				pre = "BKS";
			else if (tableName.equalsIgnoreCase("campus_book_details"))
				pre = "BUK";
			else if (tableName.equalsIgnoreCase("campus_homework"))
				pre = "HWK";
			else if (tableName.equalsIgnoreCase("sms_meeting")) {
				pre = "EVN";
			}
			else if (tableName.equalsIgnoreCase("sms_suddenholidays_details"))
				pre = "SDH";
			else if (tableName.equalsIgnoreCase("latecomers_sms")) {
				pre = "LAT";
			}
			else if (tableName.equalsIgnoreCase("uniform_sms")) {
				pre = "UNI";
			}
			else if (tableName.equalsIgnoreCase("sms_absent_details")) {
				pre = "ABS";
			}
			else if (tableName.equalsIgnoreCase("campus_inventory_types")) {
				pre = "INV";
			}
			
			else if (tableName.equalsIgnoreCase("campus_inventory_purchase_details")) {
				pre = "PUR";
			}
			else if (tableName.equalsIgnoreCase("campus_inventory_transaction_details")) {
				pre = "TRA";
			}
			else if (tableName.equalsIgnoreCase("campus_parent_enquiry_details")) {
				pre = "PNQ";
			}
			else if (tableName.equalsIgnoreCase("campus_fee_indetail")) {
				pre = "FID";
			}
			else if (tableName.equalsIgnoreCase("campus_temporary_admisssion_details")) {
				pre = "TAD";
			}
			else if (tableName.equalsIgnoreCase("campus_payment_collection")){
				pre = "CFP";
			}
			else if (tableName.equalsIgnoreCase("campus_religion")){
				pre = "RLG";
			}
			else if (tableName.equalsIgnoreCase("campus_caste")){
				pre = "CST";
			}
			else if (tableName.equalsIgnoreCase("campus_caste_category")){
				pre = "CAT";
			}
			else if (tableName.equalsIgnoreCase("campus_occupation")){
				pre = "OCP";
			}
			
			else if (tableName.equalsIgnoreCase("campus_holidaymaster")){
				pre = "HOL";
			}
			else if (tableName.equalsIgnoreCase("campus_new_leave_bank")){
				pre = "LEA";
			}
			else if (tableName.equalsIgnoreCase("campus_fee_transport_termdetails")){
				pre = "TTR";
			}
			
			else if (tableName.equalsIgnoreCase("campus_exam_gradesettings")){
				pre = "GRA";
			}

			else if(tableName.equalsIgnoreCase("campus_confidential_report")){
				pre = "CFD";
			}
			else if (tableName.equalsIgnoreCase("campus_house_settings")){
				pre = "HOU";
			}
			else if (tableName.equalsIgnoreCase("campus_generate_house")){
				pre = "GHS";
			}

			else if (tableName.equalsIgnoreCase("campus_election_group_settings")){
				pre = "GRP";
			}

			else if (tableName.equalsIgnoreCase("campus_student_age_certificate")){
				pre = "SAC";
			}
			else if (tableName.equalsIgnoreCase("campus_student_bonafied_certificate")){
				pre = "SBC";
			}
			else if (tableName.equalsIgnoreCase("campus_election_school_setting")){
				pre = "CSE";
			}
			else if (tableName.equalsIgnoreCase("campus_student_conduct_certificate")){
				pre = "SCC";
			}
			else if (tableName.equalsIgnoreCase("campus_student_course_conduct_cetificate")){
				pre = "CCC";
			}
			else if (tableName.equalsIgnoreCase("campus_student_visa_certificate")){
				pre = "SVC";
			}

			else if (tableName.equalsIgnoreCase("campus_exam_timetable")){
				pre = "ETT";
			}

			else if (tableName.equalsIgnoreCase("campus_election_election_setting")){
				pre = "CES";
			}
			else if (tableName.equalsIgnoreCase("campus_election_category_setting")){
				pre = "ECS";
			}else if(tableName.equalsIgnoreCase("campus_generate_payroll")){
				pre = "PAY";
			}
			else if (tableName.equalsIgnoreCase("campus_election_booth_setting")){
				pre = "EBS";
			}
			else if (tableName.equalsIgnoreCase("campus_election_polling_machine_setting")){
				pre = "EPS";
			}
			else if (tableName.equalsIgnoreCase("library_category")){
				pre = "LCT";
			}
			else if (tableName.equalsIgnoreCase("campus_library_location")){
				pre = "LLC";
			}
			
			else  if (tableName.equalsIgnoreCase("campus_library_subscriber_details")){ 
				pre ="LSD";
			}
			else  if (tableName.equalsIgnoreCase("campus_library_stock_entry")){ 
				pre ="LSE";
			}else if (tableName.equalsIgnoreCase("campus_event_registration")){
				pre ="EVR";
			}else if(tableName.equalsIgnoreCase("campus_event_category")){
				pre="CEC";
			}else if(tableName.equalsIgnoreCase("campus_event_stage")){
				pre="CES";
			}
			else  if (tableName.equalsIgnoreCase("campus_library_book_issue_details")){ 
				pre ="LBI";
			}
			else  if (tableName.equalsIgnoreCase("campus_library_book_return_details")){ 
				pre ="LBR";
			}
			else if (tableName.equalsIgnoreCase("laboratory_details")){
				pre ="LAB";
			}else if(tableName.equalsIgnoreCase("campus_event_greenroom")){
				pre="CEG";
			}
			else if (tableName.equalsIgnoreCase("campus_event_programcreation")){
				pre="CEP";
			}

			else if (tableName.equalsIgnoreCase("campus_event_criteria")){
				pre ="CEC";
			}

			else if (tableName.equalsIgnoreCase("campus_library_publisher_settings")){
				pre ="LPS";
			}

			else if (tableName.equalsIgnoreCase("campus_event_volunteerregistation")){
				pre ="EVR";
			}
			else if (tableName.equalsIgnoreCase("campus_event_studentregistration")){
				pre ="ESR";
			}
			
			else if (tableName.equalsIgnoreCase("campus_event_prizelevel")){
				pre ="CEP";
			}

			else if (tableName.equalsIgnoreCase("campus_project")){
				pre ="PRJ";
			}

			else if (tableName.equalsIgnoreCase("campus_event_program_scheduling")){
				pre ="CES";
			}
			else if (tableName.equalsIgnoreCase("campus_event_chestnumber")){
				pre ="CCN";
			}
			else if (tableName.equalsIgnoreCase("campus_event_stage_allocation")){
				pre ="CSA";
			}
			else if (tableName.equalsIgnoreCase("campus_library_Supplier_settings")){
				pre ="LSS";
			}
			else if (tableName.equalsIgnoreCase("campus_event_program_numbering")){
				pre ="EPN";
			}
			else if(tableName.equalsIgnoreCase("campus_library_reservation_details"))
			{
				pre="RSV";
			}
			else if(tableName.equalsIgnoreCase("campus_library_generalsettings"))
			{
				pre="CGS";
			}
			else if(tableName.equalsIgnoreCase("campus_event_marksentry"))
			{
				pre="CEM";
			}
			
			else if(tableName.equalsIgnoreCase("campus_tc_details"))
			{
				pre="TCG";
			}
			else if(tableName.equalsIgnoreCase("campus_library_journal_subscription"))
			{
				pre="LJS";
			}
			else if(tableName.equalsIgnoreCase("online_transactionid_table")){
				pre="ONT";
			}
			
			
           NextID = pre + ++currentID;

			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPrimaryKeyID Ending");
		
		System.out.println("getPrimaryKeyID Ended");
		
		return NextID;
	}
	
public static String getPrimaryKeyID(String tableName,Connection con) throws Exception {
		
		System.out.println("getPrimaryKeyID Started");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPrimaryKeyID Starting");
		String NextID = null;
		try {
			tableName = tableName.trim().toLowerCase();
			System.out.println("tableName "+tableName);
			int currentID = 0;
			String pre = null;
			String previousID = getPreviousID(tableName,con);
			System.out.println("previousID "+previousID);
			if (previousID == null) {
				currentID = 0;
			} else {
				String id = previousID.substring(3);
				currentID = Integer.parseInt(id);
				System.out.println("currentID "+currentID);
			}
			if (tableName.equalsIgnoreCase("campus_parents"))
				pre = "PAR";

			else if (tableName.equalsIgnoreCase("campus_department"))
				pre = "DEP";

			else if (tableName.equalsIgnoreCase("transport_vehicle")) {
				pre = "VEH";
			}
			else if(tableName.equalsIgnoreCase("campus_location")){
			
			pre = "LOC";
			}
			else if(tableName.equalsIgnoreCase("campus_class_specialization")){
				pre= "SPE";
			}
			else if (tableName.equalsIgnoreCase("campus_examination")) {
				pre = "EXM";
			}

			else if (tableName.equalsIgnoreCase("campus_comments")) {
				pre = "RMK";
			}

			else if (tableName.equalsIgnoreCase("campus_meeting")) {
				pre = "MET";
			}
			else if (tableName.equalsIgnoreCase("campus_dob_details")) {
				pre = "DOB";
			}
			else if (tableName.equalsIgnoreCase("campus_feedback")) {
				pre = "FED";
			}
			else if (tableName.equalsIgnoreCase("campus_classteacher")) {
				pre = "CTC";
			}
			else if (tableName.equalsIgnoreCase("campus_sec_admission_details")) {
				pre = "ACS";
			}
			else if (tableName.equalsIgnoreCase("campus_third_admission_details")) {
				pre = "ATD";
			}
			
			else if (tableName.equalsIgnoreCase("campus_subject_marks_wise")) {
				pre = "SBM";
			}
			else if (tableName.equalsIgnoreCase("campus_third_admission_details")) {
				pre = "ACT";
			}
			else if (tableName.equalsIgnoreCase("campus_fee_master"))
				pre = "CFM";
			else if (tableName.equalsIgnoreCase("campus_acadamicyear"))
				pre = "ACY";
			else if (tableName.equalsIgnoreCase("campus_designation"))
				pre = "DES";
			else if (tableName.equalsIgnoreCase("campus_classdetail"))
				pre = "CCD";
			else if (tableName.equalsIgnoreCase("campus_role"))
				pre = "ROL";

			else if (tableName.equalsIgnoreCase("campus_subject"))
				pre = "SUB";

			else if (tableName.equalsIgnoreCase("campus_classstream"))
				pre = "CLS";

			else if (tableName.equalsIgnoreCase("campus_classsection")) {
				pre = "CCS";
			}
            else if (tableName.equalsIgnoreCase("campus_studentwise_mark_details")) {
				pre = "SDM";
			}
			else if (tableName.equalsIgnoreCase("campus_quota"))
				pre = "CQA";
			else if (tableName.equalsIgnoreCase("transport_typedetails"))
				pre = "TTD";
			else if (tableName.equalsIgnoreCase("campus_teachers"))
				pre = "TEA";
			else if (tableName.equalsIgnoreCase("campus_careers"))
				pre = "JOB";
			else if (tableName.equalsIgnoreCase("campus_fee_termdetails"))
				pre = "TRM";
			else if (tableName.equalsIgnoreCase("transport_fuel_maintainence"))
				pre = "FUE";
			else if (tableName.equalsIgnoreCase("campus_accyearplan"))
				pre = "APL";
			else if (tableName.equalsIgnoreCase("campus_remainder"))
				pre = "RDR";
			else if (tableName.equalsIgnoreCase("campus_accyearplan"))
				pre = "APL";
			else if (tableName.equalsIgnoreCase("transport_route"))
				pre = "ROU";
			else if (tableName.equalsIgnoreCase("campus_fee_stage"))
				pre = "FSM";
			else if (tableName.equalsIgnoreCase("transport_stopsdetails"))
				pre = "STO";
			else if (tableName.equalsIgnoreCase("campus_classteacher"))
				pre = "CCT";
			else if (tableName.equalsIgnoreCase("campus_student"))
				pre = "STU";
			else if (tableName.equalsIgnoreCase("transport_driver"))
				pre = "DRV";
			else if (tableName.equalsIgnoreCase("campus_fee_setup"))
				pre = "FMS";
			else if (tableName.equalsIgnoreCase("campus_fee_stagesetup"))
				pre = "SFS";
			else if (tableName.equalsIgnoreCase("campus_fee_concessiondetails"))
				pre = "CNC";
			else if (tableName.equalsIgnoreCase("campus_fee_collection"))
				pre = "CFC";
			else if (tableName.equalsIgnoreCase("campus_student_enquiry"))
				pre = "ENQ";
			else if (tableName.equalsIgnoreCase("campus_studentpromotion"))
				pre = "STP";
			else if (tableName.equalsIgnoreCase("campus_assignment"))
				pre = "ASM";
			else if (tableName.equalsIgnoreCase("campus_user"))
				pre = "USR";
			else if (tableName.equalsIgnoreCase("campus_timetable_student"))
				pre = "CST";
			else if (tableName.equalsIgnoreCase("campus_timetable_teacher"))
				pre = "CTT";
			else if (tableName.equalsIgnoreCase("campus_marks_upload"))
				pre = "CMU";
			else if (tableName.equalsIgnoreCase("campus_book_issue_details"))
				pre = "BKS";
			else if (tableName.equalsIgnoreCase("campus_book_details"))
				pre = "BUK";
			else if (tableName.equalsIgnoreCase("campus_homework"))
				pre = "HWK";
			else if (tableName.equalsIgnoreCase("sms_meeting")) {
				pre = "EVN";
			}
			else if (tableName.equalsIgnoreCase("sms_suddenholidays_details"))
				pre = "SDH";
			else if (tableName.equalsIgnoreCase("latecomers_sms")) {
				pre = "LAT";
			}
			else if (tableName.equalsIgnoreCase("uniform_sms")) {
				pre = "UNI";
			}
			else if (tableName.equalsIgnoreCase("sms_absent_details")) {
				pre = "ABS";
			}
			else if (tableName.equalsIgnoreCase("campus_inventory_types")) {
				pre = "INV";
			}
			
			else if (tableName.equalsIgnoreCase("campus_inventory_purchase_details")) {
				pre = "PUR";
			}
			else if (tableName.equalsIgnoreCase("campus_inventory_transaction_details")) {
				pre = "TRA";
			}
			else if (tableName.equalsIgnoreCase("campus_parent_enquiry_details")) {
				pre = "PNQ";
			}
			else if (tableName.equalsIgnoreCase("campus_fee_indetail")) {
				pre = "FID";
			}
			else if (tableName.equalsIgnoreCase("campus_temporary_admisssion_details")) {
				pre = "TAD";
			}
			else if (tableName.equalsIgnoreCase("campus_payment_collection")){
				pre = "CFP";
			}
			else if (tableName.equalsIgnoreCase("campus_religion")){
				pre = "RLG";
			}
			else if (tableName.equalsIgnoreCase("campus_caste")){
				pre = "CST";
			}
			else if (tableName.equalsIgnoreCase("campus_caste_category")){
				pre = "CAT";
			}
			else if (tableName.equalsIgnoreCase("campus_occupation")){
				pre = "OCP";
			}
			
			else if (tableName.equalsIgnoreCase("campus_holidaymaster")){
				pre = "HOL";
			}
			else if (tableName.equalsIgnoreCase("campus_new_leave_bank")){
				pre = "LEA";
			}
			else if (tableName.equalsIgnoreCase("campus_fee_transport_termdetails")){
				pre = "TTR";
			}
			
			else if (tableName.equalsIgnoreCase("campus_exam_gradesettings")){
				pre = "GRA";
			}

			else if(tableName.equalsIgnoreCase("campus_confidential_report")){
				pre = "CFD";
			}
			else if (tableName.equalsIgnoreCase("campus_house_settings")){
				pre = "HOU";
			}
			else if (tableName.equalsIgnoreCase("campus_generate_house")){
				pre = "GHS";
			}

			else if (tableName.equalsIgnoreCase("campus_election_group_settings")){
				pre = "GRP";
			}

			else if (tableName.equalsIgnoreCase("campus_student_age_certificate")){
				pre = "SAC";
			}
			else if (tableName.equalsIgnoreCase("campus_student_bonafied_certificate")){
				pre = "SBC";
			}
			else if (tableName.equalsIgnoreCase("campus_election_school_setting")){
				pre = "CSE";
			}
			else if (tableName.equalsIgnoreCase("campus_student_conduct_certificate")){
				pre = "SCC";
			}
			else if (tableName.equalsIgnoreCase("campus_student_course_conduct_cetificate")){
				pre = "CCC";
			}
			else if (tableName.equalsIgnoreCase("campus_student_visa_certificate")){
				pre = "SVC";
			}

			else if (tableName.equalsIgnoreCase("campus_exam_timetable")){
				pre = "ETT";
			}

			else if (tableName.equalsIgnoreCase("campus_election_election_setting")){
				pre = "CES";
			}
			else if (tableName.equalsIgnoreCase("campus_election_category_setting")){
				pre = "ECS";
			}else if(tableName.equalsIgnoreCase("campus_generate_payroll")){
				pre = "PAY";
			}
			else if (tableName.equalsIgnoreCase("campus_election_booth_setting")){
				pre = "EBS";
			}
			else if (tableName.equalsIgnoreCase("campus_election_polling_machine_setting")){
				pre = "EPS";
			}
			else if (tableName.equalsIgnoreCase("library_category")){
				pre = "LCT";
			}
			else if (tableName.equalsIgnoreCase("campus_library_location")){
				pre = "LLC";
			}
			
			else  if (tableName.equalsIgnoreCase("campus_library_subscriber_details")){ 
				pre ="LSD";
			}
			else  if (tableName.equalsIgnoreCase("campus_library_stock_entry")){ 
				pre ="LSE";
			}else if (tableName.equalsIgnoreCase("campus_event_registration")){
				pre ="EVR";
			}else if(tableName.equalsIgnoreCase("campus_event_category")){
				pre="CEC";
			}else if(tableName.equalsIgnoreCase("campus_event_stage")){
				pre="CES";
			}
			else  if (tableName.equalsIgnoreCase("campus_library_book_issue_details")){ 
				pre ="LBI";
			}
			else  if (tableName.equalsIgnoreCase("campus_library_book_return_details")){ 
				pre ="LBR";
			}
			else if (tableName.equalsIgnoreCase("laboratory_details")){
				pre ="LAB";
			}else if(tableName.equalsIgnoreCase("campus_event_greenroom")){
				pre="CEG";
			}
			else if (tableName.equalsIgnoreCase("campus_event_programcreation")){
				pre="CEP";
			}

			else if (tableName.equalsIgnoreCase("campus_event_criteria")){
				pre ="CEC";
			}

			else if (tableName.equalsIgnoreCase("campus_library_publisher_settings")){
				pre ="LPS";
			}

			else if (tableName.equalsIgnoreCase("campus_event_volunteerregistation")){
				pre ="EVR";
			}
			else if (tableName.equalsIgnoreCase("campus_event_studentregistration")){
				pre ="ESR";
			}
			
			else if (tableName.equalsIgnoreCase("campus_event_prizelevel")){
				pre ="CEP";
			}

			else if (tableName.equalsIgnoreCase("campus_project")){
				pre ="PRJ";
			}

			else if (tableName.equalsIgnoreCase("campus_event_program_scheduling")){
				pre ="CES";
			}
			else if (tableName.equalsIgnoreCase("campus_event_chestnumber")){
				pre ="CCN";
			}
			else if (tableName.equalsIgnoreCase("campus_event_stage_allocation")){
				pre ="CSA";
			}
			else if (tableName.equalsIgnoreCase("campus_library_Supplier_settings")){
				pre ="LSS";
			}
			else if (tableName.equalsIgnoreCase("campus_event_program_numbering")){
				pre ="EPN";
			}
			else if(tableName.equalsIgnoreCase("campus_library_reservation_details"))
			{
				pre="RSV";
			}
			else if(tableName.equalsIgnoreCase("campus_library_generalsettings"))
			{
				pre="CGS";
			}
			else if(tableName.equalsIgnoreCase("campus_event_marksentry"))
			{
				pre="CEM";
			}
			
			else if(tableName.equalsIgnoreCase("campus_tc_details"))
			{
				pre="TCG";
			}
			else if(tableName.equalsIgnoreCase("campus_library_journal_subscription"))
			{
				pre="LJS";
			}
			else if(tableName.equalsIgnoreCase("online_transactionid_table")){
				pre="ONT";
			}
			
			
           NextID = pre + ++currentID;

			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPrimaryKeyID Ending");
		
		System.out.println("getPrimaryKeyID Ended");
		
		return NextID;
	}
	

	private static String getPreviousID(String tableName) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPreviousID Starting");
		int counter = 0;
		String columnName = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con=JDBCConnection.getSeparateConnection();
			String sql = "SHOW KEYS FROM " + tableName
					+ " WHERE Key_name = 'PRIMARY'";

			ps =con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				columnName = (String) rs.getString("Column_name");
				System.out.println("columnName"+columnName);
			}
			ps.close();
			String sql2 = "select max(" + columnName + ") from " + tableName
					+ " group by length(" + columnName + ") order by " + columnName + "  desc limit 1";
			System.out.println(sql2);
			ps = con.prepareStatement(sql2);
			rs = ps.executeQuery();
			while (rs.next()) {
				counter++;
				columnName = (String) rs.getString(1);
			}

			ps.close();
			if (counter == 0) {
				return null;
			} else {

			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}

				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPreviousID Ending");
		return columnName;
	}
	

	private static String getPreviousID(String tableName,Connection con) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPreviousID Starting");
		int counter = 0;
		String columnName = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SHOW KEYS FROM " + tableName
					+ " WHERE Key_name = 'PRIMARY'";

			ps =con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				columnName = (String) rs.getString("Column_name");
				System.out.println("columnName"+columnName);
			}
			ps.close();
			String sql2 = "select max(" + columnName + ") from " + tableName
					+ " group by length(" + columnName + ") order by " + columnName + " desc limit 1";
			System.out.println(sql2);
			ps = con.prepareStatement(sql2);
			rs = ps.executeQuery();
			while (rs.next()) {
				counter++;
				columnName = (String) rs.getString(1);
			}

			ps.close();
			if (counter == 0) {
				return null;
			} else {

			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}

				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPreviousID Ending");
		return columnName;
	}
	
	
	
//public static String getPrimaryKeyID(String tableName) throws SQLException {
//		
//		System.out.println("getPrimaryKeyID Started");
//
//		logger.setLevel(Level.DEBUG);
//		JLogger.log(0, JDate.getTimeString(new Date())
//				+ MessageConstants.START_POINT);
//		logger.info(JDate.getTimeString(new Date())
//				+ " Control in IDGenerator: getPrimaryKeyID Starting");
//		String NextID = null;
//		try {
//			tableName = tableName.trim().toLowerCase();
//			int currentID = 0;
//			String pre = null;
//			String previousID = getPreviousID(tableName);
//
//			if (previousID == null) {
//				currentID = 0;
//			} else {
//				String id = previousID.substring(3);
//				currentID = Integer.parseInt(id);
//			}
//			if (tableName.equalsIgnoreCase("campus_temporary_admisssion_details"))
//				pre = "ASK";
//
//			
//
//			
//			else {
//			}
//
//			NextID = pre + ++currentID;
//			System.out.println("New ID" + NextID);
//		} catch (SQLException sqlExp) {
//			logger.error(sqlExp.getMessage(), sqlExp);
//			sqlExp.getStackTrace();
//		}
//		JLogger.log(0, JDate.getTimeString(new Date())
//				+ MessageConstants.END_POINT);
//		logger.info(JDate.getTimeString(new Date())
//				+ " Control in IDGenerator: getPrimaryKeyID Ending");
//		
//		System.out.println("getPrimaryKeyID Ended");
//		
//		return NextID;
//	}
	
	
	public static String getAdmId(String tableName) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPrimaryKeyID Starting");
		String NextID = null;
		try {
			tableName = tableName.trim().toLowerCase();
			int currentID = 0;
			String pre = null;
			String previousID = getPreviousID(tableName);

			if (previousID == null) {
				currentID = 0;
			} else {
				String id = previousID.substring(3);
				currentID = Integer.parseInt(id);
			}
			if (tableName.equalsIgnoreCase("campus_temporary_admisssion_details"))
				pre = "ASK";
			else if(tableName.equalsIgnoreCase("campus_sec_admission_details"))
				pre="ACS";
			else if(tableName.equalsIgnoreCase("campus_third_admission_details"))
				pre="ACS";
			

			else
			{
				
			}
			NextID = pre + ++currentID;
			System.out.println("New ID" + NextID);
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPrimaryKeyID Ending");
		
		System.out.println("getPrimaryKeyID Ended");
		
		return NextID;

		
		}




	public List<PageFilterVo> getstaffIdCardDesignList(PageFilterpojo filterpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		
		try{
			conn=JDBCConnection.getSeparateConnection();
		
			psmt=conn.prepareStatement("SELECT ca.acadamic_id,ca.acadamic_year,cl.Location_Id,cl.Location_Name,cl.Location_Address,cl.Location_phone,cd.DEPT_ID,cd.DEPT_NAME FROM campus_acadamicyear ca,campus_department cd ,campus_location cl WHERE cl.Location_Id LIKE ? AND ca.acadamic_id LIKE ? AND cd.DEPT_ID LIKE ? ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cd.DEPT_ID,4)AS UNSIGNED)");
			psmt.setString(1, filterpojo.getLocationId());
			psmt.setString(2, filterpojo.getAcademicYear());
			
			psmt.setString(3, filterpojo.getDepartmentId());
		
			System.out.println(psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
				filterVo.setSno(sno);
				filterVo.setAcademicYear(rs.getString("acadamic_year"));
				filterVo.setAcademicYearCode(rs.getString("acadamic_id"));
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDepartmentId(rs.getString("DEPT_ID"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				filterVo.setTemplateName(rs.getString("acadamic_year")+rs.getString("Location_Name")+rs.getString("DEPT_NAME"));
				filterVo.setTemplateId(rs.getString("acadamic_id")+rs.getString("Location_Id")+rs.getString("DEPT_ID"));
				filterVo.setLocation_address(rs.getString("Location_Address"));
				filterVo.setLocation_phone(rs.getString("Location_phone"));
				list.add(filterVo);
				
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Ending");
		
		return list;
	}



	/*here*/
	public List<PageFilterVo> getSingleStaffCardDesignList(
			PageFilterpojo filterpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		
		try{
			
			
			conn=JDBCConnection.getSeparateConnection();
		
			//psmt=conn.prepareStatement("SELECT ca.acadamic_id,ca.acadamic_year,cl.Location_Id,cl.Location_Name,cd.DesignationCode,cd.designationName FROM campus_acadamicyear ca,campus_designation cd ,campus_location cl WHERE cl.Location_Id LIKE ? AND ca.acadamic_id LIKE ? AND cd.DesignationCode LIKE ? ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cd.DesignationCode,4)AS UNSIGNED)");
			psmt=conn.prepareStatement("SELECT ca.acadamic_id,ca.acadamic_year,cl.Location_Id,cl.Location_Name,cd.DesignationCode,cd.designationName,cdp.DEPT_ID,cdp.DEPT_NAME,ct.TeacherID FROM campus_acadamicyear ca,campus_designation cd ,campus_location cl,campus_department cdp,campus_teachers ct WHERE cl.Location_Id LIKE ? AND ca.acadamic_id LIKE ? AND cd.DesignationCode LIKE ? AND cdp.DEPT_ID LIKE ?  ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cd.DesignationCode,4)AS UNSIGNED)");
			//psmt=conn.prepareStatement("SELECT ca.acadamic_id,ca.acadamic_year,cl.Location_Id,cl.Location_Name,cd.DesignationCode,cd.designationName,cdp.DEPT_ID,cdp.DEPT_NAME, ct.TeacherID,CONCAT(ct.FirstName,' ',ct.LastName) AS StaffName,ct.mobileNo,ct.presentAddress,ct.isActive FROM campus_acadamicyear ca,campus_designation cd ,campus_location cl,campus_department cdp,campus_teachers ct WHERE cl.Location_Id LIKE '%%' AND ca.acadamic_id LIKE '%%'  AND cd.DesignationCode LIKE '%%' AND cdp.DEPT_NAME LIKE '%%'  AND ct.TeacherID LIKE '%%' AND ct.FirstName LIKE '%%' AND ct.mobileNo LIKE '%%' AND ct.presentAddress LIKE '%%' AND ct.isActive LIKE '%%' ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cd.DesignationCode,4)AS UNSIGNED)");
			
			
			
			psmt.setString(1, filterpojo.getLocationId());
			psmt.setString(2, filterpojo.getAcademicYear());

			psmt.setString(3, filterpojo.getDesignationId());
			psmt.setString(4, filterpojo.getDepartmentId());
	
			System.out.println(psmt);
			
			rs=psmt.executeQuery();
			
			while(rs.next()){
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
				filterVo.setSno(sno);
				filterVo.setAcademicYear(rs.getString("acadamic_year"));
				filterVo.setAcademicYearCode(rs.getString("acadamic_id"));
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDesignationId(rs.getString("DesignationCode"));
				filterVo.setDesignationName(rs.getString("designationName"));
				filterVo.setDepartmentId(rs.getString("DEPT_ID"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				
				filterVo.setTeacherID(rs.getString("TeacherID"));
				
		/*		filterVo.setTeacherID(rs.getString("TeacherID"));
				filterVo.setTeacherName(rs.getString("StaffName"));
				filterVo.setMobile(rs.getString("mobileNo"));
				filterVo.setAddress(rs.getString("presentAddress"));
				filterVo.setStatus(rs.getString("isActive"));*/
				
				
			
		
			
				list.add(filterVo);
				
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Ending");
		
		return list;
	}




	public List<PageFilterVo> getstaffSingleIDCard(PageFilterpojo filterpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Starting");
		
		Connection conn = null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		
		try{
			
			
			conn=JDBCConnection.getSeparateConnection();
		
			//psmt=conn.prepareStatement("SELECT ca.acadamic_id,ca.acadamic_year,cl.Location_Id,cl.Location_Name,cd.DesignationCode,cd.designationName FROM campus_acadamicyear ca,campus_designation cd ,campus_location cl WHERE cl.Location_Id LIKE ? AND ca.acadamic_id LIKE ? AND cd.DesignationCode LIKE ? ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cd.DesignationCode,4)AS UNSIGNED)");
			psmt=conn.prepareStatement("SELECT cl.Location_Name,cl.Location_Id,cdp.DEPT_NAME,cd.designationName,ct.* FROM campus_teachers ct LEFT JOIN  campus_designation cd ON ct.designation=cd.DesignationCode LEFT JOIN campus_department cdp ON ct.department=cdp.DEPT_ID LEFT JOIN campus_location cl ON ct.Loc_ID=cl.Location_Id WHERE ct.department LIKE ? AND ct.designation LIKE ? AND ct.Loc_ID LIKE ?  ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cd.DesignationCode,4)AS UNSIGNED)");
			
		/*	psmt.setString(1, filterpojo.getLocationId());
			psmt.setString(2, filterpojo.getAcademicYear());*/
			psmt.setString(1, filterpojo.getDepartmentId());
			psmt.setString(2, filterpojo.getDesignationId());
			psmt.setString(3, filterpojo.getLocationId());
		
			System.out.println(psmt);
			rs=psmt.executeQuery();
			while(rs.next()){
				String accyear=null;
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
				filterVo.setSno(sno);
				PreparedStatement acd=conn.prepareStatement("SELECT * FROM campus_acadamicyear where acadamic_id like ?");
				acd.setString(1, filterpojo.getAcademicYear());
				ResultSet rsc=acd.executeQuery();
				while(rsc.next()){
					accyear=rsc.getString("acadamic_year");
				}
				filterVo.setAcademicYearCode(filterpojo.getAcademicYear());
				filterVo.setAcademicYear(accyear);
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDesignationId(rs.getString("designation"));
				filterVo.setDesignationName(rs.getString("designationName"));
				filterVo.setDepartmentId(rs.getString("department"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
			
				
		
			
				list.add(filterVo);
				
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Ending");
		
		return list;
	}




	public List<StudentRegistrationVo> getStudentListforPrint(
			String locationid, String accyear, String classname,
			String sectionid, String streamId, String flag) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno=0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			if(flag !=null && flag.equalsIgnoreCase("transport")){
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTDETAILS_FOR_TRANSPORT_PRINT);
			}
			else{
			pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_FILTERED_STUDENTDETAILS_FOR_PRINT);
			}
			pst.setString(1, locationid);
			pst.setString(2, accyear);
			pst.setString(3, classname);
			pst.setString(4, sectionid);
			pst.setString(5, streamId);
			System.out.println(pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("studentname"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setImage(rs.getString("student_imgurl_var"));

				list.add(registrationVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getStudentDetails  Ending");

		return list;
	}
	
	
	
	public List<PageFilterVo> getstaffIdCardprintList(PageFilterpojo filterpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Starting");
		
		Connection conn = null;
		PreparedStatement psmt2=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		String academicYear=null;
		String academicYearId=null;
		try{
			
			
			conn=JDBCConnection.getSeparateConnection();
		
			//psmt=conn.prepareStatement("SELECT ca.acadamic_id,ca.acadamic_year,cl.Location_Id,cl.Location_Name,cd.DesignationCode,cd.designationName FROM campus_acadamicyear ca,campus_designation cd ,campus_location cl WHERE cl.Location_Id LIKE ? AND ca.acadamic_id LIKE ? AND cd.DesignationCode LIKE ? ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cd.DesignationCode,4)AS UNSIGNED)");
			psmt2=conn.prepareStatement("SELECT cl.Location_Id,cl.Location_Name,cd.DesignationCode,cd.designationName,cdp.DEPT_ID,cdp.DEPT_NAME,ct.TeacherID,ct.FirstName,ct.TeacherID,ct.isActive,ct.mobileNo,ct.presentAddress FROM campus_designation cd ,campus_location cl,campus_department cdp,campus_teachers ct WHERE ct.department=cdp.DEPT_ID AND ct.designation=cd.DesignationCode AND ct.Loc_ID=cl.Location_Id AND ct.TeacherID LIKE ? AND cl.Location_Id LIKE ? ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cd.DesignationCode,4)AS UNSIGNED)");
			//psmt=conn.prepareStatement("SELECT ca.acadamic_id,ca.acadamic_year,cl.Location_Id,cl.Location_Name,cd.DesignationCode,cd.designationName,cdp.DEPT_ID,cdp.DEPT_NAME, ct.TeacherID,CONCAT(ct.FirstName,' ',ct.LastName) AS StaffName,ct.mobileNo,ct.presentAddress,ct.isActive FROM campus_acadamicyear ca,campus_designation cd ,campus_location cl,campus_department cdp,campus_teachers ct WHERE cl.Location_Id LIKE '%%' AND ca.acadamic_id LIKE '%%'  AND cd.DesignationCode LIKE '%%' AND cdp.DEPT_NAME LIKE '%%'  AND ct.TeacherID LIKE '%%' AND ct.FirstName LIKE '%%' AND ct.mobileNo LIKE '%%' AND ct.presentAddress LIKE '%%' AND ct.isActive LIKE '%%' ORDER BY CAST(SUBSTR(cl.Location_Id,4)AS UNSIGNED),CAST(SUBSTR(cd.DesignationCode,4)AS UNSIGNED)");
			
			
			
			psmt2.setString(1, filterpojo.getTeacherID());
			psmt2.setString(2, filterpojo.getLocationId());

			
	
			System.out.println(psmt2);
			System.out.println("doimplllllllllllllllllllllllllllllllllllllll");
			rs=psmt2.executeQuery();
			
			while(rs.next()){
		
				PreparedStatement pmt=conn.prepareStatement("SELECT * FROM `campus_acadamicyear` WHERE `acadamic_id`=?");
				pmt.setString(1, filterpojo.getAcademicYear());
				ResultSet rn=pmt.executeQuery();
				while(rn.next()){
					academicYearId=rn.getString(1);
					academicYear=rn.getString(2);
				}
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
			
				filterVo.setAcademicYear(academicYear);
				filterVo.setAcademicYearCode(academicYearId);
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDesignationId(rs.getString("DesignationCode"));
				filterVo.setDesignationName(rs.getString("designationName"));
				filterVo.setDepartmentId(rs.getString("DEPT_ID"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				filterVo.setTeacherName(rs.getString("FirstName"));
				filterVo.setTeacherID(rs.getString("TeacherID"));
				filterVo.setStatus(rs.getString("isActive"));
				filterVo.setMobile(rs.getString("mobileNo"));
				filterVo.setAddress(rs.getString("presentAddress"));
				
					
			
				list.add(filterVo);
				
			}
		
			System.out.println(list.size());
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt2 != null && (!psmt2.isClosed())) {
					psmt2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getNewstudentIdCardDesignList : Ending");
		
		return list;
	}



	

	public List<PageFilterVo> getstaffSearch(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getstaffSearch : Starting");
		
		Connection conn = null;
		PreparedStatement psmt2=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		String academicYear=null;
		String academicYearId=null;
		String searchValue = searchTerm;
		try{
			PageFilterVo filterVo = new PageFilterVo();
			
conn=JDBCConnection.getSeparateConnection();
psmt2=conn.prepareStatement("SELECT DISTINCT ct.TeacherID,ct.FirstName,ct.department,cd.DEPT_NAME,ct.presentAddress,ct.designation,des.designationName,cay.acadamic_id,cay.acadamic_year,cl.Location_Id,cl.Location_Name,ct.isActive,ct.mobileNo FROM campus_acadamicyear cay,campus_teachers ct LEFT JOIN campus_department cd ON cd.DEPT_ID=ct.department LEFT JOIN campus_designation des ON des.DesignationCode=ct.designation LEFT JOIN campus_location cl ON ct.Loc_ID=cl.Location_Id WHERE (des.DesignationName LIKE ? OR cd.DEPT_NAME LIKE ? OR ct.TeacherID LIKE ? OR ct.FirstName LIKE ? OR ct.mobileNo LIKE ? OR ct.presentAddress LIKE ? OR ct.isActive LIKE ?) ");
			

			psmt2.setString(1, "%"+searchValue+"%");
			psmt2.setString(2, "%"+searchValue+"%");
			psmt2.setString(3, "%"+searchValue+"%");
			psmt2.setString(4, "%"+searchValue+"%");
			psmt2.setString(5, "%"+searchValue+"%");
			psmt2.setString(6, "%"+searchValue+"%");
			psmt2.setString(7, "%"+searchValue+"%");
		
			
			rs = psmt2.executeQuery();
	
			System.out.println(psmt2);
			System.out.println("doimplllllllllsearch by all***********************");
			rs=psmt2.executeQuery();
			
			while(rs.next()){
				
				filterVo.setAcademicYear(academicYear);
				filterVo.setAcademicYearCode(academicYearId);
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDesignationId(rs.getString("DesignationCode"));
				filterVo.setDesignationName(rs.getString("designationName"));
				filterVo.setDepartmentId(rs.getString("DEPT_ID"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				filterVo.setTeacherName(rs.getString("FirstName"));
				filterVo.setTeacherID(rs.getString("TeacherID"));
				filterVo.setStatus(rs.getString("isActive"));
				filterVo.setMobile(rs.getString("mobileNo"));
				filterVo.setAddress(rs.getString("presentAddress"));
		
			list.add(filterVo);
				
			}
		
			System.out.println(list.size());
	
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt2 != null && (!psmt2.isClosed())) {
					psmt2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getstaffSearch : Ending");
		
		return list;
	}
	


	public List<PageFilterVo> getstaffSearch(String searchTerm,String academicYear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getstaffSearch : Starting");
		
		Connection conn = null;
		PreparedStatement psmt2=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		
		String academicYearId=academicYear;
		String searchValue = searchTerm;
		try{
			
		
conn=JDBCConnection.getSeparateConnection();
//psmt2=conn.prepareStatement("SELECT DISTINCT ct.TeacherID,ct.FirstName,ct.department,cd.DEPT_NAME,ct.designation,des.designationName,cay.acadamic_id,cay.acadamic_year,cl.Location_Id,cl.Location_Name FROM campus_acadamicyear cay,campus_teachers ct LEFT JOIN campus_department cd ON cd.DEPT_ID=ct.department LEFT JOIN campus_designation des ON des.DesignationCode=ct.designation LEFT JOIN campus_location cl ON ct.Loc_ID=cl.Location_Id WHERE cay.acadamic_id LIKE ? AND (des.DesignationName LIKE ? OR cd.DEPT_NAME LIKE ? OR ct.TeacherID LIKE ? OR ct.FirstName LIKE ? OR ct.mobileNo LIKE ? OR ct.presentAddress LIKE ? OR ct.isActive LIKE ?) ORDER BY CAST(SUBSTR(cay.acadamic_id,4)AS UNSIGNED),CAST(SUBSTR(ct.Loc_ID,4)AS UNSIGNED),CAST(SUBSTR(cd.DEPT_ID,4)AS UNSIGNED),CAST(SUBSTR(des.DesignationCode,4)AS UNSIGNED)");
	psmt2=conn.prepareStatement("SELECT DISTINCT ct.TeacherID,ct.FirstName,ct.department,cd.DEPT_NAME,ct.presentAddress,ct.designation,des.designationName,cay.acadamic_id,cay.acadamic_year,cl.Location_Id,cl.Location_Name,ct.isActive,ct.mobileNo FROM campus_acadamicyear cay,campus_teachers ct LEFT JOIN campus_department cd ON cd.DEPT_ID=ct.department LEFT JOIN campus_designation des ON des.DesignationCode=ct.designation LEFT JOIN campus_location cl ON ct.Loc_ID=cl.Location_Id WHERE (des.DesignationName LIKE ? OR cd.DEPT_NAME LIKE ? OR ct.TeacherID LIKE ? OR ct.FirstName LIKE ? OR ct.mobileNo LIKE ? OR ct.presentAddress LIKE ? OR ct.isActive LIKE ?) ");	



		
			psmt2.setString(1, "%"+searchValue+"%");
			psmt2.setString(2, "%"+searchValue+"%");
			psmt2.setString(3, "%"+searchValue+"%");
			psmt2.setString(4, "%"+searchValue+"%");
			psmt2.setString(5, "%"+searchValue+"%");
			psmt2.setString(6, "%"+searchValue+"%");
			psmt2.setString(7, "%"+searchValue+"%");
		
			
			rs = psmt2.executeQuery();
	
			System.out.println(psmt2);
			System.out.println("doimplllllllllsearch by all***********************");
			rs=psmt2.executeQuery();
			
			while(rs.next()){
				String accyear=null;
		
				PreparedStatement acd=conn.prepareStatement("SELECT * FROM campus_acadamicyear where acadamic_id like ?");
				acd.setString(1, academicYear);
				ResultSet rsc=acd.executeQuery();
				while(rsc.next()){
					accyear=rsc.getString("acadamic_year");
				}
		
			while(rs.next()){
				
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
			
				filterVo.setAcademicYear(accyear);
				filterVo.setAcademicYearCode(academicYearId);
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDesignationId(rs.getString("designation"));
				filterVo.setDesignationName(rs.getString("designationName"));
				filterVo.setDepartmentId(rs.getString("department"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				filterVo.setTeacherName(rs.getString("FirstName"));
				filterVo.setTeacherID(rs.getString("TeacherID"));
				filterVo.setStatus(rs.getString("isActive"));
				filterVo.setMobile(rs.getString("mobileNo"));
				filterVo.setAddress(rs.getString("presentAddress"));
		
			list.add(filterVo);
				list.size();
			}
		
			System.out.println(list.size());
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt2 != null && (!psmt2.isClosed())) {
					psmt2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getstaffSearch : Ending");
		
		return list;
	}
	



	public List<PageFilterVo> getstaffSearch(String searchTerm,
			String locationId, String academicYear, String department) {
		// TODO Auto-generated method stub
		return null;
	}




	public List<PageFilterVo> getstaffSearch(String searchTerm,
			String locationId, String academicYear, String department,
			String designation) {
		// TODO Auto-generated method stub
		return null;
	}




	public List<PageFilterVo> getstaffSearchByLocation(String searchTerm,String locationId, String academicYear) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getstaffSearch : Starting");
		
		Connection conn = null;
		PreparedStatement psmt2=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		
		String location =locationId;
		String searchValue = searchTerm;
		String academicYearId=academicYear;
		try{
			
		
			conn=JDBCConnection.getSeparateConnection();

			psmt2=conn.prepareStatement("SELECT DISTINCT ct.TeacherID,ct.FirstName,ct.department,cd.DEPT_NAME,ct.presentAddress,ct.designation,des.designationName,cay.acadamic_id,cay.acadamic_year,cl.Location_Id,cl.Location_Name,ct.isActive,ct.mobileNo FROM campus_acadamicyear cay,campus_teachers ct LEFT JOIN campus_department cd ON cd.DEPT_ID=ct.department LEFT JOIN campus_designation des ON des.DesignationCode=ct.designation LEFT JOIN campus_location cl ON ct.Loc_ID=cl.Location_Id WHERE ct.Loc_ID LIKE ? AND (des.DesignationName LIKE ? OR cd.DEPT_NAME LIKE ? OR ct.TeacherID LIKE ? OR ct.FirstName LIKE ? OR ct.mobileNo LIKE ? OR ct.presentAddress LIKE ? OR ct.isActive LIKE ?) ");	

			psmt2.setString(1, location);
			psmt2.setString(2, "%"+searchValue+"%");
			psmt2.setString(3, "%"+searchValue+"%");
			psmt2.setString(4, "%"+searchValue+"%");
			psmt2.setString(5, "%"+searchValue+"%");
			psmt2.setString(6, "%"+searchValue+"%");
			psmt2.setString(7, "%"+searchValue+"%");
			psmt2.setString(8, "%"+searchValue+"%");
		
			
			rs = psmt2.executeQuery();
	
			System.out.println(psmt2);
			System.out.println("doimplllllllllsearch by all***********************");
			rs=psmt2.executeQuery();
			
			while(rs.next()){
				String accyear=null;
		
				PreparedStatement acd=conn.prepareStatement("SELECT * FROM campus_acadamicyear where acadamic_id like ?");
				acd.setString(1, academicYear);
				ResultSet rsc=acd.executeQuery();
				while(rsc.next()){
					accyear=rsc.getString("acadamic_year");
				}
		
			while(rs.next()){
				
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
			
				filterVo.setAcademicYear(accyear);
				filterVo.setAcademicYearCode(academicYearId);
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDesignationId(rs.getString("designation"));
				filterVo.setDesignationName(rs.getString("designationName"));
				filterVo.setDepartmentId(rs.getString("department"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				filterVo.setTeacherName(rs.getString("FirstName"));
				filterVo.setTeacherID(rs.getString("TeacherID"));
				filterVo.setStatus(rs.getString("isActive"));
				filterVo.setMobile(rs.getString("mobileNo"));
				filterVo.setAddress(rs.getString("presentAddress"));
		
			list.add(filterVo);
				list.size();
			}
		
			System.out.println(list.size());
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt2 != null && (!psmt2.isClosed())) {
					psmt2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getstaffSearch : Ending");
		
		return list;
	}




	public List<PageFilterVo> getstaffSearchByDepartment(String searchTerm,String locationId, String academicYear, String department) {
		
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getstaffSearch : Starting");
		
		Connection conn = null;
		PreparedStatement psmt2=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		
		
		String searchValue = searchTerm;
		String academicYearId=academicYear;
		try{
			
		
			conn=JDBCConnection.getSeparateConnection();

			psmt2=conn.prepareStatement("SELECT DISTINCT ct.TeacherID,ct.FirstName,ct.department,cd.DEPT_NAME,ct.presentAddress,ct.designation,des.designationName,cay.acadamic_id,cay.acadamic_year,cl.Location_Id,cl.Location_Name,ct.isActive,ct.mobileNo FROM campus_acadamicyear cay,campus_teachers ct LEFT JOIN campus_department cd ON cd.DEPT_ID=ct.department LEFT JOIN campus_designation des ON des.DesignationCode=ct.designation LEFT JOIN campus_location cl ON ct.Loc_ID=cl.Location_Id WHERE (des.DesignationName LIKE ? OR cd.DEPT_NAME LIKE ? OR ct.TeacherID LIKE ? OR ct.FirstName LIKE ? OR ct.mobileNo LIKE ? OR ct.presentAddress LIKE ? OR ct.isActive LIKE ?) AND ct.Loc_ID LIKE ? AND ct.department LIKE ?");	

			psmt2.setString(1, "%"+searchValue+"%");
			psmt2.setString(2, "%"+searchValue+"%");
			psmt2.setString(3, "%"+searchValue+"%");
			psmt2.setString(4, "%"+searchValue+"%");
			psmt2.setString(5, "%"+searchValue+"%");
			psmt2.setString(6, "%"+searchValue+"%");
			psmt2.setString(7, locationId);
			psmt2.setString(8, department);
		
			
			rs = psmt2.executeQuery();
	
			System.out.println(psmt2);
			System.out.println("doimplllllllllsearch by all***********************");
			rs=psmt2.executeQuery();
			
			while(rs.next()){
				String accyear=null;
		
				PreparedStatement acd=conn.prepareStatement("SELECT * FROM campus_acadamicyear where acadamic_id like ?");
				acd.setString(1, academicYear);
				ResultSet rsc=acd.executeQuery();
				while(rsc.next()){
					accyear=rsc.getString("acadamic_year");
				}
		
			while(rs.next()){
				
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
			
				filterVo.setAcademicYear(accyear);
				filterVo.setAcademicYearCode(academicYearId);
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDesignationId(rs.getString("designation"));
				filterVo.setDesignationName(rs.getString("designationName"));
				filterVo.setDepartmentId(rs.getString("department"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				filterVo.setTeacherName(rs.getString("FirstName"));
				filterVo.setTeacherID(rs.getString("TeacherID"));
				filterVo.setStatus(rs.getString("isActive"));
				filterVo.setMobile(rs.getString("mobileNo"));
				filterVo.setAddress(rs.getString("presentAddress"));
		
			list.add(filterVo);
				list.size();
			}
		
			System.out.println(list.size());
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt2 != null && (!psmt2.isClosed())) {
					psmt2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getstaffSearch : Ending");
		
		return list;
	}




	public List<PageFilterVo> getstaffSearchByAll(String searchTerm,String locationId, String academicYear, String department,String designation) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getstaffSearch : Starting");
		
		Connection conn = null;
		PreparedStatement psmt2=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		
		
		String searchValue = searchTerm;
		String academicYearId=academicYear;
		try{
			
		
			conn=JDBCConnection.getSeparateConnection();

			psmt2=conn.prepareStatement("SELECT DISTINCT ct.TeacherID,ct.FirstName,ct.department,cd.DEPT_NAME,ct.presentAddress,ct.designation,des.designationName,cay.acadamic_id,cay.acadamic_year,cl.Location_Id,cl.Location_Name,ct.isActive,ct.mobileNo FROM campus_acadamicyear cay,campus_teachers ct LEFT JOIN campus_department cd ON cd.DEPT_ID=ct.department LEFT JOIN campus_designation des ON des.DesignationCode=ct.designation LEFT JOIN campus_location cl ON ct.Loc_ID=cl.Location_Id WHERE (des.DesignationName LIKE ? OR cd.DEPT_NAME LIKE ? OR ct.TeacherID LIKE ? OR ct.FirstName LIKE ? OR ct.mobileNo LIKE ? OR ct.presentAddress LIKE ? OR ct.isActive LIKE ?) AND ct.Loc_ID LIKE ? AND ct.department LIKE ? AND des.DesignationCode LIKE ? AND cay.acadamic_id LIKE ?");	

			psmt2.setString(1, "%"+searchValue+"%");
			psmt2.setString(2, "%"+searchValue+"%");
			psmt2.setString(3, "%"+searchValue+"%");
			psmt2.setString(4, "%"+searchValue+"%");
			psmt2.setString(5, "%"+searchValue+"%");
			psmt2.setString(6, "%"+searchValue+"%");
			psmt2.setString(7, "%"+searchValue+"%");
			psmt2.setString(8, locationId);
			psmt2.setString(9, department);
			psmt2.setString(10, designation);
			psmt2.setString(11, academicYearId);
		
			
			rs = psmt2.executeQuery();
	
			System.out.println(psmt2);
			System.out.println("doimplllllllllsearch by all***********************");
			rs=psmt2.executeQuery();
			
			while(rs.next()){
				String accyear=null;
		
				PreparedStatement acd=conn.prepareStatement("SELECT * FROM campus_acadamicyear where acadamic_id like ?");
				acd.setString(1, academicYearId);
				ResultSet rsc=acd.executeQuery();
				while(rsc.next()){
					accyear=rsc.getString("acadamic_year");
				}
		
		
				
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
			
				filterVo.setAcademicYear(accyear);
				filterVo.setAcademicYearCode(academicYearId);
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDesignationId(rs.getString("designation"));
				filterVo.setDesignationName(rs.getString("designationName"));
				filterVo.setDepartmentId(rs.getString("department"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				filterVo.setTeacherName(rs.getString("FirstName"));
				filterVo.setTeacherID(rs.getString("TeacherID"));
				filterVo.setStatus(rs.getString("isActive"));
				filterVo.setMobile(rs.getString("mobileNo"));
				filterVo.setAddress(rs.getString("presentAddress"));
		
			list.add(filterVo);
				list.size();
			
		
			System.out.println(list.size());
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt2 != null && (!psmt2.isClosed())) {
					psmt2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getstaffSearch : Ending");
		
		return list;
	}




	public List<PageFilterVo> getstaffSearchByDesig(String searchTerm,String locationId, String academicYear, String designation) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getstaffSearch : Starting");
		
		Connection conn = null;
		PreparedStatement psmt2=null;
		ResultSet rs=null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		int sno=0;
		
		
		String searchValue = searchTerm;
		String academicYearId=academicYear;
		try{
			
		
			conn=JDBCConnection.getSeparateConnection();

			psmt2=conn.prepareStatement("SELECT DISTINCT ct.TeacherID,ct.FirstName,ct.department,cd.DEPT_NAME,ct.presentAddress,ct.designation,des.designationName,cay.acadamic_id,cay.acadamic_year,cl.Location_Id,cl.Location_Name,ct.isActive,ct.mobileNo FROM campus_acadamicyear cay,campus_teachers ct LEFT JOIN campus_department cd ON cd.DEPT_ID=ct.department LEFT JOIN campus_designation des ON des.DesignationCode=ct.designation LEFT JOIN campus_location cl ON ct.Loc_ID=cl.Location_Id WHERE (des.DesignationName LIKE ? OR cd.DEPT_NAME LIKE ? OR ct.TeacherID LIKE ? OR ct.FirstName LIKE ? OR ct.mobileNo LIKE ? OR ct.presentAddress LIKE ? OR ct.isActive LIKE ?) AND ct.Loc_ID LIKE ? AND des.DesignationCode LIKE ?");	

			psmt2.setString(1, "%"+searchValue+"%");
			psmt2.setString(2, "%"+searchValue+"%");
			psmt2.setString(3, "%"+searchValue+"%");
			psmt2.setString(4, "%"+searchValue+"%");
			psmt2.setString(5, "%"+searchValue+"%");
			psmt2.setString(6, "%"+searchValue+"%");
			psmt2.setString(7, "%"+searchValue+"%");
			psmt2.setString(8, locationId);
			psmt2.setString(9, designation);
		
			
			rs = psmt2.executeQuery();
	
			System.out.println(psmt2);
			System.out.println("doimplllllllllsearch by all***********************");
			rs=psmt2.executeQuery();
			
			while(rs.next()){
				String accyear=null;
		
				PreparedStatement acd=conn.prepareStatement("SELECT * FROM campus_acadamicyear where acadamic_id like ?");
				acd.setString(1, academicYear);
				ResultSet rsc=acd.executeQuery();
				while(rsc.next()){
					accyear=rsc.getString("acadamic_year");
				}
		
			while(rs.next()){
				
				sno++;
				PageFilterVo filterVo = new PageFilterVo();
			
				filterVo.setAcademicYear(accyear);
				filterVo.setAcademicYearCode(academicYearId);
				filterVo.setLocationName(rs.getString("Location_Name"));
				filterVo.setLocationId(rs.getString("Location_Id"));
				filterVo.setDesignationId(rs.getString("designation"));
				filterVo.setDesignationName(rs.getString("designationName"));
				filterVo.setDepartmentId(rs.getString("department"));
				filterVo.setDepartmentName(rs.getString("DEPT_NAME"));
				filterVo.setTeacherName(rs.getString("FirstName"));
				filterVo.setTeacherID(rs.getString("TeacherID"));
				filterVo.setStatus(rs.getString("isActive"));
				filterVo.setMobile(rs.getString("mobileNo"));
				filterVo.setAddress(rs.getString("presentAddress"));
		
			list.add(filterVo);
				list.size();
			}
		
			System.out.println(list.size());
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt2 != null && (!psmt2.isClosed())) {
					psmt2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in studentIDDaoImpl: getstaffSearch : Ending");
		
		return list;
	}
		}



		