package com.centris.campus.util;

public class UploadStageXLSqlUtil {
	
	public static final String CHECK_BEFORINSERT_COUNT= "select count(*) from campus_teachers";
	
	
	public static final String CHECK_STAGE_ID= "select count(*) from campus_fee_stage where  stage_id=?";
	
	public static final String INSERT_STAGE="insert into campus_fee_stage(stage_id, stage_name, amount, description, createdby, createdtime,accyear) values (?,?,?,?,?,?,?)";
	
	public static final String STAGE_DUPLICATE = "select  count(*)  from campus_fee_stage where stage_name=? and accyear=?";
	
}
