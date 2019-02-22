package com.centris.campus.util;

public class FeeCollectionSqlUtils {
	

	/*	public static final String GETFEECOLLECTION_LIST = "select distinct s.student_admissionno_var,concat(s.student_fname_var,' ',s.student_lname_var) studentname,t.termname,t.termid,s.classdetail_id_int,s.classsection_id_int from  campus_fee_setup cfs,campus_student s,campus_fee_termdetails t where s.classdetail_id_int=cfs.ClassCode and s.fms_acadamicyear_id_int=cfs.AccyearCode and t.termid =cfs.Termcode and s.fms_acadamicyear_id_int=?";
*/	
	/*public static final String GET_FEECOLLECTION_LIST = "select is_paid,totalamount from campus_fee_collection where admissionNo=? and accYear=? and termcode=?";*/

	/*	public static final String GET_FEECOLLECTION_DEDDER ="select concat(s.student_fname_var,' ',s.student_lname_var) studentname,s.student_admissionno_var,a.acadamic_year,c.classdetails_name_var,sec.classsection_name_var,t.termname,conc.percentage from campus_acadamicyear a,campus_classdetail c,campus_classsection sec,campus_fee_termdetails t,campus_student s left outer join campus_fee_concessiondetails conc on s.student_scholorship_var=conc.concessionid where  a.acadamic_id=? and c.classdetail_id_int=s.classdetail_id_int and sec.classsection_id_int=s.classsection_id_int and s.student_admissionno_var=? and t.termid=?";
*/	
	
	public static final String GET_FEECOLLECTION_COUNT ="select count(*) from campus_fee_setup where ClassCode=? and AccyearCode=? and Termcode=?";

/*	public static final String GET_FEECOLLECTION_AMOUNT ="select fm.FeeCode,fm.FeeName,fm.IsConcession,fsd.feeAmount from campus_fee_master fm,campus_fee_setup fs,campus_fee_setupdetails fsd where fs.feeSettingcode=fsd.feeSettingCode and fm.FeeCode=fsd.feecode and fs.ClassCode=? and fs.AccyearCode=? and fs.Termcode=?";
*/	

/*public static final String GET_TRANSPORT_FEE = "SELECT fsd.feeAmount,fsd.stagecode from campus_fee_stagesetupdetails fsd ,campus_fee_stagesetup fs where  fsd.stageSettingCode=fs.stageSettingCode and fs.ClassCode=? and fs.AccyearCode=? and fs.Termcode=? and fsd.stagecode in (select StageId from campus_student where student_admissionno_var=?)";*/

/*	public static final String INSERT_FEE_COLLECTION = "insert into campus_fee_collection(feeCollectionCode,admissionNo,accYear,termcode,is_paid,totalamount,actualamount,conc_amount,conc_percent,paidDate,createdby,createdtime) values(?,?,?,?,?,?,?,?,?,?,?,?)";
*/	

/*	public static final String INSERT_FEE_COLLECTION_DETAILS = "insert into campus_fee_collectiondetails(feeCollectionCode,feeCode,feeAmount,finalFeeAmt) values(?,?,?,?)";
*/

/*	public static final String FEE_COLLECTION_COUNT ="select count(*) from campus_fee_collection where admissionNo=? and accYear=? and termcode=?";
*/	

/*public static final String GET_EXIST_FEECOLLECTION_AMOUNT = "select fm.FeeCode,fm.FeeName,fm.IsConcession,fcd.feeAmount,fcd.finalFeeAmt,fc.conc_percent,fc.actualamount,fc.conc_amount,fc.totalamount  from campus_fee_master fm,campus_fee_collection fc,campus_fee_collectiondetails fcd where fc.admissionNo=? and fc.accYear=? and fc.termcode=? and fc.feeCollectionCode=fcd.feeCollectionCode and fcd.feeCode=fm.FeeCode";*/
	
/*public static final String GET_EXIST_TRANSPORT_AMOUNT = "SELECT fcd.feeAmount,fs.stage_id from campus_fee_stage fs ,campus_fee_collection fc,campus_fee_collectiondetails fcd where fc.admissionNo=? and fc.accYear=? and fc.termcode=? and fc.feeCollectionCode=fcd.feeCollectionCode and fcd.feeCode=fs.stage_id";*/

/*	public static final String GET_COLLECTION_UPDATE_CNT = "select feeCollectionCode from campus_fee_collection where admissionNo=? and accYear=? and termcode=?";
*/
	
/*	public static final String UPDATE_FEE_COLLECTION = "update campus_fee_collection set totalamount=? , actualamount=? , conc_amount=?, conc_percent=? ,updatedby=? , updatedtime=? where feeCollectionCode=?";
*/	
	
/*	public static final String UPDATE_FEE_COLLECTION_DETAILS = "update campus_fee_collectiondetails set feeAmount=?,finalFeeAmt=? where feeCollectionCode=? and feeCode=?";
*/	
	
	public static final String GET_SEARCH_FEECOLLECTION_LIST = "select distinct s.student_id_int,s.student_admissionno_var,concat(s.student_fname_var,' ',s.student_lname_var) studentname, t.termname,t.termid,s.classdetail_id_int,s.classsection_id_int, cls.classdetails_name_var,sec.classsection_name_var ,aca.acadamic_year from  campus_fee_setup cfs,campus_student s,campus_fee_termdetails t ,campus_classdetail cls,campus_classsection sec ,campus_acadamicyear aca where s.classdetail_id_int=cfs.ClassCode and s.fms_acadamicyear_id_int=cfs.AccyearCode and t.termid =cfs.Termcode and cls.classdetail_id_int=s.classdetail_id_int and sec.classsection_id_int = s.classsection_id_int and aca.acadamic_id = s.fms_acadamicyear_id_int and t.termid like ? and s.classdetail_id_int like ? and s.classsection_id_int like ? and s.student_id_int like ? ";
	
	
	public static final String GET_STUDENT_DETAILS = "select student_id_int,concat(student_fname_var,' ',student_lname_var) as studentname from campus_student where classsection_id_int=? and fms_acadamicyear_id_int=? and classdetail_id_int=? order by student_fname_var";
	
	
	public static final String GET_ALL_FEES = "select distinct setu.feeSettingCode, fee.FeeCode,fee.FeeName,setu.feeAmount from campus_fee_master fee join campus_fee_setupdetails setu on setu.feecode = fee.FeeCode join campus_fee_setup setup on setup.feeSettingcode =  setu.feeSettingcode where setup.AccyearCode =? and setup.ClassCode =?";

	public static final String GET_STUDENT_VAL = "select cl.classdetails_name_var,st.student_id_int,concat(st.student_fname_var,' ',st.student_lname_var) studentname,st.student_admissionno_var,st.classdetail_id_int from campus_student st join campus_classdetail cl on cl.classdetail_id_int=st.classdetail_id_int where student_id_int=?";
	public static final String GET_PAMENT_TYPE_DETAILS = "select patment_id,academicyear_id,paid_amount,payment_date,january,february,march,april,may,june,july,august,september,october,november,december from campus_payment_collection where student_id=? and student_admission=? and feesettings_id=? and classid=?";
	public static final String GET_TOTAL_FEES="select distinct sum(feeAmount) as amount from campus_fee_master fee join campus_fee_setupdetails setu on setu.feecode = fee.FeeCode join campus_fee_setup setup on setup.feeSettingcode =  setu.feeSettingcode where setup.AccyearCode =? and setup.ClassCode =?";
	public static final String GET_FEECOLLECTIONCOUNT="select count(*) from campus_payment_collection where student_id = ? ";
	public static final String INSERT_FEE_PAYMENT_COLLECTION = "insert into campus_payment_collection(patment_id,academicyear_id,feesettings_id,student_admission,student_id,payment_mode,payment_type,paid_amount,total_amount,due_amount,payment_date,january,february,march,april,may,june,july,august,september,october,november,december,classid,create_user,create_date)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_FEE_PAYMENT_COLLECTION = "update campus_payment_collection set feesettings_id= ?,student_admission= ?,payment_mode= ?,payment_type= ?,paid_amount= ?,total_amount= ?,due_amount= ?,payment_date= ?,january= ?,february= ?,march= ?,april= ?,may= ?,june= ?,july= ?,august= ?,september= ?,october= ?,november= ?,december= ?,modify_user=?,modify_date=? where student_id= ? and academicyear_id = ? and classid= ?";
	public static final String GET_FEE_PARTICULARS = "select * from campus_payment_collection where student_id=? and academicyear_id=?";
	public static final String GET_FEE_COLLECTION_DETAILS="insert into campus_payment_collection_details(payment_collection_id,fee_set_up_id,student_id,payment_mode,cheque_no,payment_type,months,paid_amount,balance,total,payment_date,created_by,created_date) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String GETFEECOLLECTION_LIST = "SELECT DISTINCT aca.acadamic_id, s.student_id_int,s.student_admissionno_var,CONCAT(s.student_fname_var,' ',s.student_lname_var) studentname, s.specilization,t.termname,cls.classdetails_name_var,sec.classsection_name_var, t.termid, s.classdetail_id_int,s.classsection_id_int ,aca.acadamic_year FROM  campus_fee_setup cfs,campus_student s,campus_fee_termdetails t,campus_classdetail cls,campus_classsection sec ,campus_acadamicyear aca WHERE s.classdetail_id_int=cfs.ClassCode AND s.specilization=cfs.specialization AND s.fms_acadamicyear_id_int=cfs.AccyearCode AND t.termid =cfs.Termcode AND cls.classdetail_id_int=s.classdetail_id_int AND sec.classsection_id_int = s.classsection_id_int AND aca.acadamic_id = s.fms_acadamicyear_id_int ORDER BY aca.acadamic_id ,cls.classdetails_name_var,sec.classsection_name_var, studentname DESC";
	public static final String GET_FEECOLLECTION_LIST = "select is_paid,totalamount,balance_amount,amount_paid from campus_fee_collection where admissionNo=? and accYear=? and termcode=?";
    public static final String GET_FEECOLLECTION_DEDDER ="select cstd.student_imgurl_var imgurl,cstd.classdetail_id_int,cstd.specilization, concat(s.student_fname_var,' ',s.student_lname_var) studentname,s.student_admissionno_var,a.acadamic_year,c.classdetails_name_var,sec.classsection_name_var,conc.percentage from campus_acadamicyear a,campus_classdetail c,campus_classsection sec,campus_student s left outer join campus_fee_concessiondetails conc on s.student_scholorship_var=conc.concessionid left join campus_student_classdetails cstd on s.student_id_int=cstd.student_id_int where  c.classdetail_id_int=cstd.classdetail_id_int and c.locationId=cstd.locationId and sec.classsection_id_int=cstd.classsection_id_int and s.student_id_int=? and cstd.fms_acadamicyear_id_int=a.acadamic_id and a.acadamic_id=? and cstd.locationId=?";
	public static final String FEE_COLLECTION_COUNT ="select count(*),paidDate,chln_no,fineAmount,due_amount,advance_amount,paymentMode,paymentParticulars,dd_cheque_date from campus_fee_collection where admissionNo=? and accYear=? and termcode=?";
	public static final String GET_FEECOLLECTION_AMOUNT ="select fm.FeeCode,fm.FeeName,fm.IsConcession,fsd.feeAmount from campus_fee_master fm,campus_fee_setup fs,campus_fee_setupdetails fsd where fs.feeSettingcode=fsd.feeSettingCode and fm.FeeCode=fsd.feecode and fs.ClassCode=? and fs.AccyearCode=? and fs.Termcode=? and fs.specialization=?";
	public static final String GET_TRANSPORT_FEE = "SELECT fsd.feeAmount,fsd.stagecode from campus_fee_stagesetupdetails fsd ,campus_fee_stagesetup fs where  fsd.stageSettingCode=fs.stageSettingCode and fs.ClassCode=? and fs.AccyearCode=? and fs.Termcode=? and fsd.stagecode in (select StageId from campus_student where student_admissionno_var=?)";
	public static final String GET_EXIST_FEECOLLECTION_AMOUNT = "select fc.balance_amount,fc.amount_paid,fm.FeeCode,fm.FeeName,fm.IsConcession,fcd.feeAmount,fcd.finalFeeAmtcollected,fc.conc_percent,fc.actualamount,fc.conc_amount,fc.totalamount,fcd.outstandingfee,fcd.concessionPercent,fcd.feepaiddate from campus_fee_master fm, campus_fee_collection fc,campus_fee_collectiondetails fcd where fc.admissionNo=? and fc.accYear=? and fc.termcode=? and fc.feeCollectionCode=fcd.feeCollectionCode and fcd.feeCode=fm.FeeCode";
	public static final String GET_EXIST_TRANSPORT_AMOUNT = "SELECT fc.balance_amount,fc.amount_paid, fcd.feeAmount,fs.stage_id from campus_fee_stage fs ,campus_fee_collection fc,campus_fee_collectiondetails fcd where fc.admissionNo=? and fc.accYear=? and fc.termcode=? and fc.feeCollectionCode=fcd.feeCollectionCode and fcd.feeCode=fs.stage_id";
	public static final String GET_COLLECTION_UPDATE_CNT = "select feeCollectionCode from campus_fee_collection where admissionNo=? and accYear=? and termcode=?";
	public static final String INSERT_FEE_COLLECTION = "insert into campus_fee_collection(feeCollectionCode,admissionNo,accYear,termcode,is_paid,totalamount,actualamount,due_amount,advance_amount,paidDate,createdby,createdtime,amount_paid,balance_amount,fineAmount,paymentParticulars,paymentMode,dd_cheque_date,bank_name,concessionAmt,paidByExcel) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_FEE_COLLECTION_DETAILS = "insert into campus_fee_collectiondetails(feeCollectionCode, feeCode,feeAmount,finalFeeAmtcollected,outstandingfee,concessionPercent,consfeeAmount)values(?,?,?,?,?,?,?)";
	public static final String INSERT_FEE_RECIEPT = "insert into campus_fee_reciept(feeCollectionCode, feeCode,feeAmount,finalFeeAmtcollected,outstandingfee,concessionPercent,consfeeAmount)values(?,?,?,?,?,?,?)";
	public static final String INSERT_FEE_COLLECTION_IN_DETAILS = "insert into campus_fee_collectiondetails_daywise(feeCollectionCode, feeCode,feeAmount,finalFeeAmtcollected,outstandingfee)values(?,?,?,?,?)";

	public static final String UPDATE_FEE_COLLECTION = "update campus_fee_collection set actualamount=?,conc_amount=?,conc_percent=?,updatedby=?,updatedtime=?,balance_amount=?,amount_paid=?,paidDate=?,is_paid=? where feeCollectionCode=?";
	public static final String UPDATE_FEE_COLLECTION_DETAILS = "update campus_fee_collectiondetails set feepaiddate=?, feeAmount=?,finalFeeAmtcollected=?,outstandingfee=? where feeCollectionCode=? and feeCode=?";
	public static final String UPDATE_FEE_RECIEPT = "update campus_fee_reciept set feepaiddate=?, feeAmount=?,finalFeeAmtcollected=?,outstandingfee=?,consfeeAmount=? where feeCollectionCode=? and feeCode=?";
	public static final String INSERT_FEE_COLLECTION_D = "insert into campus_fee_collection_details(admissionNo,accYear,termcode,is_paid,totalamount,actualamount,conc_amount,conc_percent,paidDate,createdby,createdtime,amount_paid,balance_amount) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_FEECOLLECTION_TOTAL_AMOUNT = "SELECT SUM(feeAmount) totalFeeAmount FROM campus_fee_setupdetails WHERE feeSettingCode=?";
	public static final String GET_FEEPAID_TOTAL_AMOUNT = "SELECT * FROM campus_fee_collection WHERE admissionNo=? AND accYear=? AND termcode=?";

	public static final String GET_TRANSPORT_FEECOLLECTION_DEDDER = "select cstd.classdetail_id_int,cstd.specilization,cst.StageId,cst.start_month srtm ,cst.end_month endm,cst.NumberOfMonth, concat(s.student_fname_var,' ',s.student_lname_var) studentname,s.student_admissionno_var,a.acadamic_year,c.classdetails_name_var,sec.classsection_name_var,conc.percentage from campus_acadamicyear a,campus_classdetail c,campus_classsection sec,campus_student s left outer join campus_fee_concessiondetails conc on s.student_scholorship_var=conc.concessionid left join campus_student_transportdetails cst on s.student_id_int=cst.student_id_int left join campus_student_classdetails cstd on s.student_id_int=cstd.student_id_int where  c.classdetail_id_int=cstd.classdetail_id_int and c.locationId=cstd.locationId and sec.classsection_id_int=cstd.classsection_id_int and s.student_id_int=? and cstd.fms_acadamicyear_id_int=a.acadamic_id and a.acadamic_id=? and cstd.locationId=?";

	public static final String GET_FEEPAID_AMOUNT = "SELECT feeCollectionCode FROM campus_fee_collection WHERE admissionNo=? AND accYear=? AND termcode=?";
	//defenderFee 
	public static final String getDefaulterFeeList ="SELECT CONCAT(cs.student_fname_var, ' ',student_lname_var) AS studentName,cs.`student_admissionno_var`,cs.`student_id_int`,l.Location_Name,cc.classdetails_name_var,ccs.classsection_name_var,t.`termname`,t.`termid`,csc.`classdetail_id_int`,csc.fms_acadamicyear_id_int,csc.locationId FROM `campus_student` cs  JOIN `campus_student_classdetails` csc ON cs.student_id_int = csc.student_id_int   JOIN campus_classdetail cc ON (csc.`classdetail_id_int`=cc.classdetail_id_int AND csc.locationId=cc.locationId)  JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int   JOIN `campus_location` l ON l.`Location_Id`=cs.`locationId`   JOIN `campus_fee_termdetails` t ON t.`locationId`=cs.`locationId` WHERE cs.`student_id_int`  NOT IN (SELECT `admissionNo` FROM `campus_fee_collection` WHERE `accYear`=? AND `termcode`= ?) AND csc.fms_acadamicyear_id_int=? AND cs.`locationId`= ? AND csc.classdetail_id_int LIKE ? AND ccs.classsection_id_int LIKE ? AND t.`termid`=?";  

}
