package com.centris.campus.util;

public class ExamSqlUtils {

	public static final String GET_EXAM_DETAILS = "select e.examid,e.examname,e.description,e.startdate,e.enddate,e.createuser,a.acadamic_year from campus_examination e,campus_acadamicyear a where e.acadamicyear=a.acadamic_id order by length(examid),examid";
	public static final String GET_ALL_EXAMNAMES = "select examname from campus_examination where accadamicyear=?";
	public static final String GET_ALL_ACCYEARS = "select acadamic_id,acadamic_year from campus_acadamicyear order by acadamic_year;";
	public static final String insertExaminationQuery = "insert into campus_examination (examid,examname,description,acadamicyear,startdate,enddate,createuser) values (?,?,?,?,?,?,?)";
	public static final String DELETE_EXAM_DETAILS = "DELETE FROM campus_examination WHERE examid =?";
	public static final String  GET_EXAMID_ID ="select ac.acadamic_year,ex.examid,ex.examname,ex.description,ex.acadamicyear,ex.startdate,ex.enddate from campus_examination ex join campus_acadamicyear ac on ac.acadamic_id=ex.acadamicyear where ex.examid = ?";
	public static final String UPDATE_EXAM_DETAILS ="update campus_examination set examname=?,description=?,acadamicyear=?,startdate=?,enddate=?,createuser=?,modifiedby=?,modifiedDate=? where examid=?";
	public static final String SEARCH_Exam_DETAILS="select * from campus_examination where  examname like ? or description like ? or acadamicyear like ? or startdate like ? or enddate like ? or createuser like ?";
	public static final String VALIDATE_EXAM_NAME_UPDATE = "select count(*)  from campus_examination where examname=? and acadamicyear=?";
	public static final String  VALIDATE_EXAM_NAME_EDIT = "select count(*)  from campus_examination where examid!=? and examname=?";
	
	
}
