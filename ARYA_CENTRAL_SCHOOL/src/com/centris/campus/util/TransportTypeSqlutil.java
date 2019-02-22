package com.centris.campus.util;

public class TransportTypeSqlutil {

	public static final String GET_TRANSPORT_TYPE_DETAILS = "select type_id,type_name,type_collectFee,type_description from transport_typedetails order by type_name";
	public static final String GET_SEARCH_DETAILS = "select type_id,type_name,type_collectFee,type_description from transport_typedetails  where type_name like ? or type_collectFee like ? or type_description like ?  order by type_name";
	public static final String VALIDATE_TYPE_NAME = "select count(*) from transport_typedetails where type_name=?";
	public static final String VALIDATE_TYPE_NAME_UPDATE = "select count(*) from transport_typedetails where type_name=? and type_id!=?";
	public static final String ADD_TRANSPORT_TYPE = "insert into transport_typedetails(type_id,type_name,type_collectFee,type_description,createdby,createddate)values(?,?,?,?,?,?)";
	public static final String UPDATE_TRANSPORT_TYPE = "update  transport_typedetails set type_name=?,type_collectFee=?,type_description=?,updatedby=?,updaeddate=? where type_id=?";
	public static final String GET_SINGLE_TRANSPORT_TYPE = "select type_id,type_name,type_collectFee,type_description from transport_typedetails where type_id=?";
	public static final String DELETE_TRANSPORT_TYPE = "delete from transport_typedetails where type_id=?";

}
