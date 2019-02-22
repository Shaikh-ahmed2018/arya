package com.centris.campus.util;

public class StreamSqlUtils {

	public static final String INSERT_STREAM_DETAILS = "INSERT INTO campus_classstream (classstream_id_int,classstream_name_var,description, createuser, createdate,modifyuser,modifydate,locationId) VALUES (?,?,?,?,?,?,?,?)";
	public static final String GET_STREAM_DETAILS = "SELECT ccs.*,cl.Location_Name FROM   campus_classstream ccs JOIN  campus_location cl ON ccs.locationId=cl.Location_Id WHERE ccs.locationId like ? ORDER BY CASE WHEN ccs.classstream_name_var = 'Montessori' THEN '1' WHEN ccs.classstream_name_var = 'Primary' THEN '2' WHEN  ccs.classstream_name_var = 'Secondary' THEN '3' WHEN ccs.classstream_name_var = 'Sr.Secondary' THEN '4' ELSE ccs.classstream_name_var END";
	public static final String GET_STREAM_ID = "select * from campus_classstream where classstream_id_int = ?";
	/*public static final String UPDATE_STREAM_DETAILS = "update campus_classstream set classstream_name_var= ?,description=?,createuser=?,createdate=?,modifyuser=?,modifydate=? where classstream_id_int= ?";*/
	
	public static final String UPDATE_STREAM_DETAILS = "update campus_classstream set classstream_name_var= ?,description=?,modifyuser=?,modifydate=?,locationId=? where classstream_id_int= ?";
	
	public static final String DELETE_STREAM_DETAILS = "DELETE FROM campus_classstream WHERE classstream_id_int =?";
	public static final String SEARCH_STREAM_DETAILS = "select cs.*,cl.Location_Name from campus_classstream cs left join campus_location cl on cs.locationId=cl.Location_Id where  (cs.classstream_name_var like ? or cs.description like ? or cl.Location_Name like ?) and cs.locationId like ?";
	public static final String VALIDATE_STREAM_NAME_UPDATE = "select count(*)  from campus_classstream where classstream_name_var=? and locationId=? ";
	public static final String VALIDATE_STREAM_NAME_EDIT = "select count(*)  from campus_classstream where classstream_id_int!=? and classstream_name_var=?";
    public static final String  DELETE_STREAM_MAPPING= "select  count(*) classstream_id_int from campus_classdetail where classstream_id_int=?";
}
