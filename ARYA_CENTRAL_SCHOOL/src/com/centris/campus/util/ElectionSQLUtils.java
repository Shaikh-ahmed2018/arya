package com.centris.campus.util;

public class ElectionSQLUtils {
	
	//public static final String GET_GROUPNAME_BY_ACCYEAR ="SELECT ce.groupname,ce.election_group_id FROM campus_election_group_settings ce LEFT JOIN `campus_election_school_setting`cs ON cs.`election_group_id`=ce.`election_group_id` WHERE ce.accyear_id = ?";
	public static final String GET_GROUPNAME_BY_ACCYEAR ="SELECT groupname,accyear_id,election_group_id FROM`campus_election_group_settings` WHERE accyear_id=?";
	public static final String getGroupNameDropdown ="SELECT * FROM campus_election_group_settings WHERE `election_group_id` LIKE ?  ORDER BY `groupname`ASC";

	public static final String GET_ACADEMICYEAR_NAME = "select acadamic_year from campus_acadamicyear where acadamic_id=?";
	
	/*election Setting*/
	public static final String getAccYearByGroup ="SELECT DISTINCT ce.accyear_id,ca.acadamic_year FROM campus_election_group_settings ce LEFT JOIN campus_acadamicyear ca ON ca.acadamic_id=ce.accyear_id ORDER BY ca.acadamic_year ";
	public static final String InsertElectionData ="INSERT INTO campus_election_election_setting(electionSettingID,`accyearID`,`groupID`,`electionTitle`,`startDate`,`endDate`,`startTime`,`endTime`)VALUES(?,?,?,?,?,?,?,?)";
	public static final String getElectionDetails ="SELECT cee.electionSettingID,cee.accyearID,cee.groupID,cee.`electionTitle`,ca.`acadamic_year`,ceg.`groupname`  FROM `campus_election_election_setting` cee LEFT JOIN campus_election_group_settings ceg ON cee.groupID=ceg.election_group_id LEFT JOIN `campus_acadamicyear` ca ON cee.accyearID=ca.acadamic_id WHERE cee.accyearID LIKE  ? AND cee.groupID LIKE ?";
	public static final String getElectionDetailsFUpdatePage ="SELECT cee.`accyearID`,cee.`groupID`,cee.`electionSettingID`,ca.`acadamic_year`,cg.`groupname`, cee.`electionTitle`,cee.`startDate`,cee.`endDate`,cee.`startTime`,cee.`endTime` FROM campus_election_election_setting cee LEFT JOIN `campus_acadamicyear` ca ON ca.`acadamic_id`=cee.`accyearID` LEFT JOIN `campus_election_group_settings` cg ON cg.`election_group_id`=cee.`groupID` WHERE cee.`accyearID` =? AND cee.`groupID`=? AND cee.`electionSettingID`=?";
	public static final String ChkTitlDuplicate ="UPDATE `campus_election_election_setting` SET `electionTitle`=? WHERE `accyearID`=? AND `electionSettingID`=?";
	public static final String  DeleteExistTitle="DELETE FROM `campus_election_election_setting` WHERE `electionSettingID`=?";
	public static final String InsertUpdatedRecord ="UPDATE `campus_election_election_setting` SET `electionTitle`=?,`startDate`=?,`endDate`=?,`startTime`=?,`endTime`=? WHERE electionSettingID=? AND `accyearID`=? AND groupID=?";

	/*Category setting*/
	public static final String chkDuplicateCategoryName="SELECT COUNT(*) FROM campus_election_category_setting WHERE electionCategory=? AND  electionSettingID=? AND accyearID=? AND groupID=?";
	public static final String savePopUpData ="INSERT INTO `campus_election_category_setting`(`electionCategoryId`,`electionCategory`,`priority`,`classId`,`genderWise`,`houseWise`,`classWise`,`nominationFor`,nominationLevel,`electionSettingID`,`accyearID`,`groupID`,houseId)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String updatePopUpData="UPDATE  `campus_election_category_setting` SET `electionCategory`=?,`priority`=?,`classId`=?,`genderWise`=?,`houseWise`=?,`classWise`=?,`nominationFor`=?,nominationLevel=?,`electionSettingID`=?,`accyearID`=?,`groupID`=?,houseId=? WHERE `electionCategoryId`=?";
	public static final String getElectionCategoryList="SELECT `electionCategoryId`,`electionCategory`,`priority`,`classId`,`genderWise`,`houseWise`,`classWise`,`nominationFor`,nominationLevel FROM `campus_election_category_setting` WHERE  `accyearID`=? and `groupID`=? and `electionSettingID`=?";
	public static final String DeleteElectionCategoryList = "DELETE FROM `campus_election_category_setting` WHERE `electionCategoryId`=?";

	/*nomination Registration*/
	public static final String getElectionTitleByGroupName= "SELECT `electionTitle`,electionSettingID FROM `campus_election_election_setting` WHERE `accyearID` like ? AND `groupID` like ?";
	public static final String getElectionCategoryByTitle= "SELECT `electionCategoryId`,`electionCategory` FROM `campus_election_category_setting` WHERE `electionSettingID` LIKE ?";
	
	/*nomination approval*/
	public static final String getNominationApprovalList ="SELECT DISTINCT cn.`admissionNo`,CONCAT(cs.student_fname_var, ' ',student_lname_var) AS studentName,cc.`classsection_name_var`,ccd.`classdetails_name_var`,cn.`isApproved`  FROM `campus_election_nomiation_registration` cn LEFT JOIN `campus_student` cs ON cs.student_id_int = cn.`studentId` LEFT JOIN `campus_classsection` cc ON cc.`classsection_id_int` = cn.`classsection_id_int` LEFT JOIN `campus_classdetail` ccd ON ccd.`classdetail_id_int`=cn.`classdetail_id_int`  WHERE  cn.`accyearID` LIKE ? AND `groupID` LIKE ? AND `electionCategoryId` LIKE ? AND `electionSettingID` LIKE ? ";
	
	
	/*booth setting*/
	public static final String insertBoothDetails ="INSERT INTO `campus_election_booth_setting`(`boothNameId_int`,`boothName_var`,`staffIncharge_id_int`,`centralSystem`,`centralSystemIp`,`voterClass_id`,`accyearID`,`groupID`,`electionSettingID`)VALUES(?,?,?,?,?,?,?,?,?)";
	//public static final String getBoothDetailsList  ="SELECT boothNameId_int,`boothName_var`,`staffIncharge_id_int`,`centralSystem`,`centralSystemIp`,`voterClass_id`,`accyearID`,`groupID`,`electionSettingID` FROM `campus_election_booth_setting` WHERE `accyearID`=? AND `groupID`=?  AND `electionSettingID`=?";
	public static final String getBoothDeat ="SELECT CONCAT(ct.`FirstName`,'',ct.`LastName`) AS StaffName,ce.boothNameId_int,`boothName_var`,ce.`staffIncharge_id_int`,ce.`centralSystem`,ce.`centralSystemIp`, ce.`voterClass_id`,ce.`accyearID`,ce.`groupID`,ce.`electionSettingID` FROM `campus_election_booth_setting` ce LEFT JOIN  `campus_teachers` ct ON ct.`TeacherID`=ce.`staffIncharge_id_int` WHERE ce.`accyearID`=? AND ce.`groupID`=?  AND ce.`electionSettingID`=?";
	public static final String getBoothDetails ="SELECT CONCAT(ct.`FirstName`,'',ct.`LastName`) AS StaffName,ce.boothNameId_int,`boothName_var`,ce.`staffIncharge_id_int`,ce.`centralSystem`,ce.`centralSystemIp`, ce.`voterClass_id`,ce.`accyearID`,ce.`groupID`,ce.`electionSettingID` FROM `campus_election_booth_setting` ce LEFT JOIN  `campus_teachers` ct ON ct.`TeacherID`=ce.`staffIncharge_id_int` WHERE ce.`centralSystemIp`=? AND ce.`accyearID`=? AND ce.`groupID`=?  AND ce.`electionSettingID`=?";
	
	public static final String deleteBoothSelectedRow ="DELETE FROM `campus_election_booth_setting` WHERE `boothNameId_int`=?";
	public static final String getUpdateBoothSetting ="SELECT `boothName_var`,`staffIncharge_id_int`,`centralSystem`,`centralSystemIp`,`voterClass_id` FROM `campus_election_booth_setting` WHERE `boothNameId_int`=?";
	public static final String updateBoothDetails ="UPDATE `campus_election_booth_setting` SET `boothName_var`=?,`staffIncharge_id_int`=?,`centralSystem`=?,`centralSystemIp`=?,`voterClass_id`=?,`accyearID`=?,`groupID`=?,`electionSettingID`=? WHERE `boothNameId_int`=?";
	public static final String checkDuplicateBoothName="SELECT COUNT(`boothName_var`) FROM `campus_election_booth_setting` WHERE  `boothName_var`=? AND `accyearID`=? AND `groupID`=? AND `electionSettingID`=?";
	public static final String checkDuplicateStaff ="SELECT COUNT(`staffIncharge_id_int`) FROM `campus_election_booth_setting` WHERE  `boothName_var`=? AND `staffIncharge_id_int`=? AND `accyearID`=? AND `groupID`=? AND `electionSettingID`=?";
	
	
	/*polling machine*/
	public static final String getBoothNameDropdown="SELECT boothNameId_int,`boothName_var` FROM `campus_election_booth_setting` WHERE `accyearID`like ? AND `groupID`like ?  AND `electionSettingID`like ?";
	public static final String insertPollingMachineDetails="INSERT INTO `campus_election_polling_machine_setting`(pollingMachineId_int_var,`boothNameId_int`,`machineName_var`,`systemName_var`,`systemIp_var`,`accyearID`,`groupID`,`electionSettingID`)VALUES(?,?,?,?,?,?,?,?)";
	public static final String getPollingMachineList ="SELECT pollingMachineId_int_var,`machineName_var`,`systemName_var`,`systemIp_var` FROM `campus_election_polling_machine_setting` WHERE `accyearID`like ? AND `groupID` like ? AND `electionSettingID` like ?  AND `boothNameId_int` like ?";
	public static final String deletePoleSelectedRow ="DELETE FROM `campus_election_polling_machine_setting` WHERE `pollingMachineId_int_var`=?";
	public static final String getUpdatePollingDetails ="SELECT * FROM `campus_election_polling_machine_setting` WHERE `pollingMachineId_int_var`=?";
	public static final String updatePollingDetails ="UPDATE `campus_election_polling_machine_setting` SET `boothNameId_int`=?,`machineName_var`=?,`systemName_var`=?,`systemIp_var`=?,`accyearID`=?,`groupID`=?,`electionSettingID`=? WHERE `pollingMachineId_int_var`=?";
	public static final String update ="UPDATE campus_election_polling_machine_setting SET machineName_var=?,systemName_var=?,systemIp_var=?,accyearID=?,groupID=?,electionSettingID=? WHERE boothNameId_int=?";

	/*voter search list::: module-7*/
	public static final String getTitleByAccyear="SELECT `electionTitle`,electionSettingID FROM `campus_election_election_setting` WHERE `accyearID`=?";
	public static final String getClassByAccyearTitle="SELECT cn.`classdetail_id_int`,ccd.`classdetails_name_var` FROM `campus_election_nomiation_registration` cn LEFT JOIN `campus_classdetail` ccd ON ccd.`classdetail_id_int`=cn.`classdetail_id_int`  WHERE `accyearID` =? AND `electionSettingID`=?";

	public static final String getSectionByAccyearTitle="SELECT cn.`classsection_id_int`,cc.`classsection_name_var` FROM `campus_election_nomiation_registration` cn LEFT JOIN `campus_classsection` cc ON cc.`classsection_id_int` = cn.`classsection_id_int` WHERE `accyearID` =? AND `electionSettingID`=?";

	//public static final String getVoterSearchList="SELECT `admissionNo`,`studentName`,`classdetail_id_int`,`classsection_id_int` FROM `campus_election_nomiation_registration` WHERE  `accyearID`LIKE ? AND`classsection_id_int` LIKE ?  AND `classdetail_id_int` LIKE ? AND `electionSettingID` LIKE ?";
	//public static final String  getVoterSearchList="SELECT cr.`admissionNo`,cr.`studentName`,cr.`classdetail_id_int`,cr.`classsection_id_int` ,cs.student_rollno FROM `campus_election_nomiation_registration` cr,campus_student cs WHERE  `accyearID`LIKE ? AND`classsection_id_int`  LIKE ? AND `classdetail_id_int` LIKE ? AND `electionSettingID` LIKE ?";
	public static final String getVoterSearchList="SELECT cn.`admissionNo`,CONCAT(cs.student_fname_var, ' ',student_lname_var) AS studentName,cn.`studentId`, cc.`classsection_name_var`,ccd.`classdetails_name_var`,csc.`student_rollno` FROM `campus_election_nomiation_registration` cn LEFT JOIN `campus_student` cs ON cs.student_id_int = cn.`studentId`  LEFT JOIN `campus_classsection` cc ON cc.`classsection_id_int` = cn.`classsection_id_int`   LEFT JOIN `campus_classdetail` ccd ON ccd.`classdetail_id_int`=cn.`classdetail_id_int` LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`= cn.`studentId` WHERE  cn.`accyearID` LIKE ? AND cn.`electionSettingID` LIKE ? AND cn.`classdetail_id_int` LIKE ? AND cn.`classsection_id_int` LIKE ?";
	public static final String getgetvoterDetailsViewUpdate="SELECT ca.`acadamic_year`,ca.`acadamic_id`, cn.`admissionNo`,CONCAT(cs.student_fname_var, ' ',student_lname_var) AS studentName, cn.`studentId`, cc.`classsection_name_var`,cc.`classsection_id_int`,ccd.`classdetails_name_var`,ccd.`classdetail_id_int`,  csc.`student_rollno`, csc.`student_status`,csc.`student_imgurl_var`,chs.`housename`,cl.`Location_Name` FROM `campus_election_nomiation_registration` cn  LEFT JOIN `campus_student` cs ON cs.student_id_int = cn.`studentId`  LEFT JOIN `campus_classsection` cc ON cc.`classsection_id_int` = cn.`classsection_id_int`  LEFT JOIN `campus_classdetail` ccd ON ccd.`classdetail_id_int`=cn.`classdetail_id_int` LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`= cn.`studentId`  LEFT JOIN `campus_house_settings` chs ON chs.`house_id`=csc.`student_house` LEFT JOIN `campus_acadamicyear` ca ON ca.`acadamic_id`=cn.`accyearID` LEFT JOIN `campus_location` cl ON cl.`Location_Id`=csc.`locationId`  WHERE cn.`admissionNo` LIKE ? AND cn.`studentId` LIKE ?";
	
	
	
}





