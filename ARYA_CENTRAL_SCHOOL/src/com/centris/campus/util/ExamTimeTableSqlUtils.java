package com.centris.campus.util;

public class ExamTimeTableSqlUtils {

	public static final String GET_ALL_EXAM_DETAILS = "select ce.examid,ce.examname,ce.description,ce.startdate,ce.enddate,ct.classid,ct.examdate,ct.examtime,cd.classdetails_name_var,ct.endtime,ct.subjectid from campus_examination ce,campus_examination_timetable ct,campus_classdetail cd where ce.examid=ct.examinationid and cd.classdetail_id_int=ct.classid";
	public static final String GET_EXAM_TIME_TABLE = "select * from campus_examination_timetable where classid=? and subjectid=? and examinationid=?";
	public static final String GET_EXAM_DATE = "select e.startdate, e.enddate from campus_examination e where examid=?";
	public static final String GET_ALL_SUBJECTS = "select s.subjectID,s.subjectName from campus_subject s where classid=?";
	public static final String CHECK_DUPLICATE_EXAM = "select count(*) examdate from campus_examination_timetable where  classid=? and subjectid=? and examinationid=?";
	public static final String UPDATE_EXAMDATE = "update campus_examination_timetable set examdate=?,examtime=?,endtime=?,updateuser=?,updatedate=now()  where classid=? and subjectid=? and examinationid=?";
	public static final String STORE_EXAM_TIMETABLE_DETAILS = "insert into campus_examination_timetable(classid,subjectid,examinationid,examdate,examtime,endtime,createuser,createdate) values (?,?,?,?,?,?,?,now())";

	// chiru

	public static final String GET_EXAMDETAILS = "select examid,examname,examcode from campus_examination";
	public static final String GET_CLASSDETAILS = "select classdetail_id_int,classdetails_name_var from campus_classdetail";
	public static final String GET_EXAMYEAR = "select acadamic_id,acadamic_year,startDate,endDate from campus_acadamicyear ";
	public static final String GET_EXAMYEAR1 = "select acadamic_id,acadamic_year,startDate,endDate,Location_Id,Location_Name from campus_acadamicyear,campus_location where acadamic_id like ? and Location_Id like ? order by startDate,Location_Name";
	public static final String GET_ACCYEAR_COUNT="select count(*) as accyearcount from campus_examination where acadamicyear=? and loc_id=?";

	public static final String GET_TIMETABLE_ACCYEAR_COUNT="select count(*) as accyearcount from campus_examination_timetable where acadamic_id=?";
	public static final String GET_CLASS_DETAILS ="SELECT  a.acadamic_id,a.acadamic_year,a.startDate,a.endDate,cd.classdetail_id_int,cd.classdetails_name_var ,SUBSTR(cd.classdetail_id_int,4) id FROM campus_acadamicyear a,campus_classdetail cd WHERE a.acadamic_id=? ORDER BY CAST(id AS UNSIGNED)";
	public static final String GET_EXAM_TIMETABLE_COUNT="select count(acadamic_id) as examcount from campus_examination_timetable where classid=? and acadamic_id=?";
	public static final String GET_EXAM_HEADING = "SELECT (SELECT classdetails_name_var  FROM campus_classdetail WHERE classdetail_id_int=?) AS className ,(SELECT acadamic_year  FROM campus_acadamicyear WHERE acadamic_id=?) AS year,(SELECT COUNT(Specialization_Id)  FROM campus_class_specialization WHERE ClassDetails_Id=?) AS SpecializationCount";
	public static final String GET_CLASS_SECTION = "select ccs.classsection_id_int,ccs.classsection_name_var,ccs.classdetail_id_int,ccd.classdetails_name_var from campus_classsection ccs join campus_classdetail ccd on ccs.classdetail_id_int=ccd.classdetail_id_int where ccd.classdetail_id_int=?";
	public static final String GET_EXAMINATION_TIMETABLE = "select a.examid,a.examcode,a.examname,a.startdate,a.enddate,b.acadamic_year from campus_examination a,campus_acadamicyear b where a.acadamicyear=b.acadamic_id and a.acadamicyear=?";
	public static final String GET_EXAMINATION_TIMETABLE_COUNT="select count(acadamic_id) as examcount from campus_examination_timetable where acadamic_id=? and sectionid=? and classid=?";

	public static final String INSERT_EXAMS="insert into campus_examination(examid,examcode,examname,startdate,enddate,acadamicyear,createuser,loc_id,classid,`examtype`,`isapplicableperiodic`) values (?,?,?,?,?,?,?,?,?,?,?)";
	//public static final String GETEXAMSETTINGSDETAILS = "select * from campus_examination where acadamicyear=? and loc_id like ? order by startdate";
	public static final String GETEXAMSETTINGSDETAILS = "SELECT exm.classid,exm.`examid`,exm.`examcode`,exm.`examname`,exm.`startdate`,exm.`enddate`,exm.`isapplicableperiodic`,cls.`classdetails_name_var`,cet.`examtypename`,cet.`examtypeid` FROM campus_examination exm LEFT JOIN `campus_classdetail` cls ON cls.`classdetail_id_int`=exm.`classid` LEFT JOIN `campus_examtype` cet ON cet.`examtypeid`=exm.`examtype` WHERE acadamicyear=? AND loc_id =? ORDER BY LENGTH(cls.classdetail_id_int),cls.classdetail_id_int,exm.`startdate`";
	public static final String DELETEEXAMSETTINGDETAILS="delete from campus_examination where examid=? and acadamicyear = ? and loc_id=?";
	public static final String EDITEXAMSETTINGDETAILS="update campus_examination set examcode=?,examname=?,startdate=?,enddate=?,classid=?,`examtype`=?,`isapplicableperiodic`=?,`modifiedby`=?,`modifiedDate`=? where examid=? and acadamicyear=? and loc_id = ? ";
	public static final String GETEXAMSTATUSSETTINGSDETAILS="select count(*) as classcount from campus_classdetail where locationId=?";

	//public static final String GET_EXAMYEAR_FOR_MARKS="select examid,examcode,examname,startdate,enddate from campus_examination where acadamicyear=? and loc_id=? order by startdate";

	public static final String GET_CLASSSUBJECT_DETAILS ="select cet.examcode,ce.examname,ce.startdate as examstartdate,ce.enddate as examenddate,ay.acadamic_year,ay.acadamic_id,cs.subjectID,cs.subjectName,ccd.classdetails_name_var,ccs.classsection_name_var from campus_examination_timetable cet join campus_examination ce on cet.examcode=ce.examcode join campus_acadamicyear ay on ay.acadamic_id=cet.acadamic_id join campus_classdetail ccd on ccd.classdetail_id_int=cet.classid join campus_classsection ccs on ccs.classsection_id_int=cet.sectionid join campus_subject cs on cs.classid=cet.classid where cet.acadamic_id=? and cet.classid=?";
	public static final String GET_CLASSWISEEXAM_TIMETABLE ="select cet.examcode,ce.examname,ce.startdate as examstartdate,ce.enddate as examenddate,ay.acadamic_year,ay.acadamic_id,ccd.classdetails_name_var,ccs.classsection_name_var from campus_examination_timetable cet join campus_examination ce on cet.examcode=ce.examcode join campus_acadamicyear ay on ay.acadamic_id=cet.acadamic_id join campus_classdetail ccd on ccd.classdetail_id_int=cet.classid join campus_classsection ccs on ccs.classsection_id_int=cet.sectionid where cet.acadamic_id=? and cet.classid=? and ce.examid=?";
	public static final String VALIDATION_ADDEXAMS ="select count(examcode) as examcount from campus_examination where examcode=? and acadamicyear=?";
	public static final String GET_EXAMDETAILS1 = "SELECT examid,examname,examcode FROM campus_examination WHERE loc_id=?";
	public static final String GET_EXAM_DEPEND_DETAILS1 = "select examid,examcode,examname from campus_examination where acadamicyear=? and loc_id=? and examid!=? and (startdate<? and enddate<?)";
	public static final String INS_EXM_DEPENDENCY = "INSERT INTO `campus_exam_dependency`(`Exam_code`,`Exam_name`,`Dependency_Exam_code`,`Dependency_perce`) VALUES(?,?,?,?)";
	public static final String VALIDATE_DEPENDENCY = "SELECT COUNT(`Exam_code`) FROM `campus_exam_dependency` WHERE `Exam_code`=? AND `Dependency_Exam_code`=?";
	public static final String DEPENDENCY_INSERTION = "INSERT INTO `campus_grade_dependency`(`acc_year`,`loc_id`,`class_Id`,`section_Id`,`exam_code`,`project`,`assignment`,`practical`,`attendance`) VALUES(?,?,?,?,?,?,?,?,?)";
	public static final String VALIDATE_DEPENDENCY_INSERTION = "SELECT COUNT(*) FROM  `campus_grade_dependency` WHERE `loc_id`=? AND `class_Id`=? AND `section_Id`=? AND `exam_code`=? AND `acc_year`=?";
	public static final String GET_EXAMTYPE_DETAILS = "SELECT `examtypeid`,`examtypename` FROM `campus_examtype` ";
	public static final String GET_EXAMNAME_DETAILS = "SELECT ce.`examid`,ce.`examtype`,ce.`examcode`,ce.`examname`,ce.`startdate`,ce.`enddate`,cet.`examtypename` FROM `campus_examination` ce LEFT JOIN `campus_examtype` cet ON ce.examtype=cet.`examtypeid`  WHERE `loc_id`=? AND classid=? AND `acadamicyear`=? ORDER BY startdate";
	public static final String GET_EXAMNAME_DETAILS1 = "SELECT ce.`examid`,ce.`examtype`,ce.`examcode`,ce.`examname`,ce.`startdate`,ce.`enddate`,cet.`examtypename` FROM `campus_examination` ce LEFT JOIN `campus_examtype` cet ON ce.examtype=cet.`examtypeid`  WHERE `loc_id`=? AND classid=? AND `acadamicyear`=? AND cet.`examtypename` IN('Half Yearly Exam','Yearly Exam') ORDER BY startdate";

}
