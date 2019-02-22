package com.centris.campus.util;

public class StageFeeSetupSqlUtilConstants {
	
	public static final String GET_CLASS_DETAILS ="select distinct a.acadamic_id,a.acadamic_year,cd.classdetail_id_int,cd.classdetails_name_var,t.termid,t.termname from campus_acadamicyear a,campus_classdetail cd,campus_fee_termdetails t where a.acadamic_id=? and isTransportTerm = 'Y' group by a.acadamic_id,cd.classdetail_id_int,t.termid order by length(a.acadamic_id),length(cd.classdetail_id_int),length(t.termid),a.acadamic_id,cd.classdetail_id_int,t.termid"; 
	public static final String GET_STAGE_COUNT ="select count(fsd.stagecode) as stagecount from campus_fee_stagesetup fs,campus_fee_stagesetupdetails fsd where fs.stageSettingCode = fsd.stageSettingCode and fs.ClassCode=? and fs.AccyearCode=? and fs.Termcode=?";
	public static final String SERCH_FEE_DETAILS="select distinct a.acadamic_id,a.acadamic_year,cd.classdetail_id_int,cd.classdetails_name_var,t.termid,t.termname from campus_acadamicyear a,campus_classdetail cd,campus_fee_termdetails t where (a.acadamic_year like ? or cd.classdetails_name_var like ? or t.termname like ?) and a.acadamic_id=? group by a.acadamic_id,cd.classdetail_id_int,t.termid order by length(a.acadamic_id),length(cd.classdetail_id_int),length(t.termid),a.acadamic_id,cd.classdetail_id_int,t.termid";
	public static final String GET_APPROVED_STAGES = "select distinct sm.stage_id,sm.stage_name,fsd.stageSettingCode,fsd.feeAmount from campus_fee_stagesetupdetails fsd,campus_fee_stagesetup fs,campus_fee_stage sm where fsd.stageSettingCode=fs.stageSettingCode and fsd.stagecode=sm.stage_id and fs.ClassCode=? and fs.AccyearCode=? and fs.Termcode=?";
	public static final String GET_ALL_STAGES = "select distinct sm.stage_id,sm.stage_name from campus_fee_stage sm where sm.stage_id  not in(select fsdd.stagecode from campus_fee_stagesetupdetails fsdd, campus_fee_stagesetup fss1 where  fss1.stageSettingCode=fsdd.stageSettingCode  and  fss1.ClassCode=? and fss1.AccyearCode=? and fss1.Termcode=?)";

	public static final String INSERT_APPROVED_FEES_IN_FEE_SETUP = "insert into campus_fee_stagesetup(stageSettingCode,ClassCode,AccyearCode,Termcode,createdby,createdtime)values(?,?,?,?,?,now())";
	public static final String INSERT_APPROVED_FEES = "insert into  campus_fee_stagesetupdetails(stageSettingCode,stagecode,feeAmount)values(?,?,?);";
	public static final String DELETE_FEE_CODE = "delete from campus_fee_stagesetupdetails where stageSettingCode=? and stagecode=?";
	public static final String UPDATE_FEE_AMOUNT = "update campus_fee_stagesetupdetails set feeAmount=? where stageSettingCode in (select stageSettingCode from campus_fee_stagesetup where ClassCode=? and AccyearCode=? and Termcode=?) and stagecode=?";
	public static final String FEE_SETUP_COUNT ="select count(*) from campus_fee_stagesetup where ClassCode=? and AccyearCode=? and Termcode=?";
	public static final String INSERT_APPROVED_FEES_AMT="insert into  campus_fee_stagesetupdetails(stageSettingCode,stagecode,feeAmount)values((select stageSettingCode from campus_fee_stagesetup where ClassCode=? and AccyearCode=? and Termcode=? ),?,?)";

}
