package com.centris.campus.util;

public class TransportUtilConstants {

	public static final String GET_ALL_VEHICLE_DETAILS = "select VehicleCode,VehicleName,Vehicle_Reg_No,VehicleType,Engine_number,Tax_paid,TaxExpiryDate,Pollution,Chassis_No from transport_vehicle order by Vehicle_Reg_No";
	public static final String SAVE_VEHICLE_DETAILS = "insert into transport_vehicle(VehicleCode, Vehicle_Reg_No,VehicleName,Engine_number,Chassis_No,VehicleType,Tax_Paid,TaxExpiryDate,Pollution,RCFileUpload,CreateDate,CreateUser) values(?,?,?,?,?,?,?,?,?,?,now(),?)";
	public static final String CHECKING_VEHICLE_INSURANCE_DATE = "select count(*) from transport_vehicle_insurancedetails where VehicleCode= ? and (? between IssuedDate and ExpiryDate or ? between IssuedDate and ExpiryDate)";
	public static final String SAVE_VEHICLE_INSURANCE_DETAILS = "insert into transport_vehicle_insurancedetails(VehicleCode,CompanyName,IssuedDate,ExpiryDate,doneby,Fc,Permit_validity,InsuranceFleUpload,updatedBy,updatedTime) values(?,?,?,?,?,?,?,?,?,now())";
	public static final String GET_VEHCILE_INSURANCE_DETAILS = "select veh.VehicleCode,veh.Vehicle_Reg_No,veh.VehicleName,veh.Engine_number,veh.Chassis_No,veh.VehicleType,veh.Tax_paid,veh.TaxExpiryDate,veh.Pollution,veh.RCFileUpload,ins.CompanyName,ins.IssuedDate,ins.ExpiryDate,ins.doneby,ins.Fc,ins.Permit_validity,ins.InsuranceFleUpload from transport_vehicle veh,transport_vehicle_insurancedetails ins where ins.VehicleCode=veh.VehicleCode and veh.VehicleCode=?";
	public static final String UPDATE_VEHICLE_DETAILS = "update transport_vehicle set Vehicle_Reg_No=?,VehicleName=?,Engine_number=?,Chassis_No=?,VehicleType=?,Tax_Paid=?, TaxExpiryDate=?,Pollution=?, RCFileUpload=?, UpdateDate=?,UpdateUser=? where VehicleCode=?";
	public static final String UPDATE_VEHICLE_INSURANCE_DETAILS = "update transport_vehicle_insurancedetails set CompanyName=?,IssuedDate=?,ExpiryDate=?,doneby=?,Fc=?,Permit_validity=?,InsuranceFleUpload=?,updatedBy=?,updatedTime=? where VehicleCode=?";
	public static final String DELETE_VEHICLE_DETAILS = "delete from transport_vehicle where VehicleCode=?";
	public static final String VALIDATE_VEHICLE_REG_NO = "select count(*) from transport_vehicle where Vehicle_Reg_No= ?";
	public static final String VALIDATE_UPDATE_VEHICLE_REG_NO = "select count(*) from transport_vehicle where Vehicle_Reg_No = ? and VehicleCode !=?";
	public static final String VALIDATE_VEHICLE_CHASSIS_NO = "select count(*) from transport_vehicle where Chassis_No= ?";
	public static final String VALIDATE_UPDATE_VEHICLE_CHASSIS_NO = "select count(*) from transport_vehicle where Chassis_No = ? and VehicleCode !=?";
	public static final String DELETE_INSURANCE_DETAILS = "delete from transport_vehicle_insurancedetails where VehicleCode=?";
	public static final String DELETE_DRIVER_VEHICLE_MAPPING = "delete from transport_driver_vehicle_mapping where VehicleCode=?";
	public static final String DELETE_VEHICLE_ROUTE_MAPPIG = "delete from transport_vehicle_route_mapping where VehicleCode=?";
	
	public static final String SEARCH_VEHICLE_DETAILS = "select * from transport_vehicle where Vehicle_Reg_No like ? or VehicleName like ? or VehicleType like ? or Engine_number like ? or Tax_Paid like ?";
	public static final String CHECK_FOR_DUPLICATE_ADD_TIME = "select count(*) from transport_vehicle where Vehicle_Reg_No=? and VehicleName=? and Engine_number=? and Chassis_No=? and VehicleType=? and Tax_paid=? and Pollution=?";
	public static final String CHECK_FOR_DUPLICATE_UPDATE_TIME = "select count(*) from transport_vehicle where Vehicle_Reg_No=? and VehicleName=? and Engine_number=? and Chassis_No=? and VehicleType=?  and Tax_Paid=? and Pollution=? and VehicleCode!=?";
	public static final String VEHICLE_NAME="select VehicleCode,Vehicle_Reg_No,VehicleName,VehicleType,Fuel_Type from transport_vehicle order by VehicleName";
	public static final String DRIVER_NAME = "select DriverCode,Name from transport_driver order by Name ;";
	// route master start
	public static final String ROUTE_ALLLISTDATAS = "SELECT DISTINCT tr.Route_No,tr.RouteCode,tr.RouteName,tr.Start_Time,tr.End_Time,tr.Total_Stops,tr.TotalDistance,tr.HaltTime FROM transport_route tr JOIN transport_route_stage_mapping trs ON tr.RouteCode=trs.RouteCode WHERE trs.accyear=? ORDER BY tr.Route_No";
	public static final String ROUTE_INSERT = "insert into transport_route(RouteCode,RouteName,Route_No,Route_logical_name,HaltTime,Start_Time,End_Time,Total_Stops,TotalDistance,CreateDate,CreateUser) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String TRANSPORT_ROUTECHECK = "SELECT distinct(count(*)) FROM transport_route where Route_No=?";
	public static final String TRANSPORT_ROUTECHECK_WHILE_UPDATING = "SELECT distinct(count(*)) FROM transport_route where Route_No=? and RouteCode != ?";
	public static final String ROUTE_CREATEDTIME = "select CreateDate  from transport_route where RouteCode  =?";
	public static final String ROUTE_UPDATEROUTEMASTER = "update transport_route set RouteName=?,Route_No=?,Route_logical_name=?,HaltTime=?,Start_Time=?,End_Time=?,Total_Stops=?,TotalDistance=?,UpdateDate=?,UpdateUser=? where RouteCode=?";
	public static final String ROUTE_REMOVEFEE = "delete from transport_route where RouteCode=?";
	public static final String ADD_ROUTE_STOPDETAILS = "insert into transport_stopsdetails(StopCode,RouteCode,Stop_Name,ArrivalTime,HaltTime,DepTime,Distance) values(?,?,?,?,?,?,?)";
	public static final String ETID_ROUTE_ALLLISTDATAS = "select RouteName,RouteCode,Route_No,Route_logical_name,HaltTime,Start_Time,End_Time,Total_Stops,TotalDistance from transport_route where RouteCode =?";
	public static final String ROUTE_SEARCHLISTDATAS = "SELECT DISTINCT tr.* FROM transport_route tr JOIN transport_route_stage_mapping trs ON tr.RouteCode=trs.RouteCode WHERE (tr.Route_No like ? or tr.RouteName like ? or tr.Route_logical_name like ? or tr.RouteType like ?) and trs.accyear=? order by Route_No";
	
	public static final String GET_STOP_NAMES = "select stage_id,stage_name from campus_fee_stage";

	// route master end

	// fuel maintenance

	public static final String GET_FUEL_DETAILS = "select f.VehicleCode,f.DriverCode,f.FuelCode,SUBSTR(f.fueldate,1,10) as fueldate,v.VehicleName,v.Vehicle_Reg_No,d.Name,f.FuelType,f.Quantity,f.FuelCost,f.MeterReading,f.Location from transport_vehicle v,transport_fuel_maintainence f,transport_driver d where f.DriverCode=d.DriverCode and f.VehicleCode=v.VehicleCode";
	public static final String ADD_FUEL_DETAILS = "insert into transport_fuel_maintainence(FuelCode,VehicleCode,fueldate,FuelType,DriverCode,Quantity,FuelCost,MeterReading,Location,CreateDate,CreateUser) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SEARCH_VEHICLE = "select VehicleCode,Vehicle_Reg_No,VehicleType from transport_vehicle where VehicleType like ?";
	public static final String GET_VEHICLE_NO = "select VehicleCode,Vehicle_Reg_No,Fuel_Type from transport_vehicle where VehicleCode=?";
	public static final String DELETE_FUEL_DETAILS = "Delete from transport_fuel_maintainence where FuelCode=?";
	public static final String EDIT_FUEL_DETAILS = "select ve.VehicleCode,dr.DriverCode,fu.FuelCode,ve.VehicleName,fu.fueldate,ve.Vehicle_Reg_No,dr.Name,fu.FuelType,fu.Quantity,fu.FuelCost,fu.MeterReading,fu.Location from transport_fuel_maintainence fu join transport_driver dr on dr.DriverCode=fu.DriverCode join transport_vehicle ve on ve.VehicleCode=fu.VehicleCode where fu.FuelCode= ?";
	public static final String UPDATE_FUEL_DETAILS = "update transport_fuel_maintainence set VehicleCode=?,fueldate=?,FuelType=?,DriverCode=?,Quantity=?,FuelCost=?,MeterReading=?,Location=?,UpdateDate=?,UpdateUser=? where FuelCode=?";
	public static final String SEARCH_FUEL_DETAILS = "select fu.fueldate,fu.Location,fu.FuelType,ve.VehicleType,ve.Vehicle_Reg_No,dr.Name,fu.Quantity,fu.FuelCost,fu.MeterReading from transport_fuel_maintainence fu join transport_vehicle ve on ve.VehicleCode=fu.VehicleCode join transport_driver dr on dr.DriverCode=fu.DriverCode where (fu.fueldate like ? or fu.Location like ? or fu.FuelType like ? or ve.VehicleType like ? or ve.Vehicle_Reg_No like ? or dr.Name LIKE ? )";
	public static final String SEARCH_DRIVER = "select DriverCode,Name from transport_driver where Name like ?";

	// Driver master

	public static final String GET_DRIVER_LIST = "select * from transport_driver order by Name";
	public static final String ADD_DRIVER = "insert into transport_driver (DriverCode,type,Name,FatherName,DOB,MobileNo,EmergencyContactNo,Address,DOJ,Age,Gender,DLNo,DLIssuedDate,DLExpirayDate,LicencetoDrive,CreateDate,CreateUser,Experience,DrivingLicenceFile) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_DRIVER_DETAILS = "select DriverCode,Name,FatherName,DOB,MobileNo,EmergencyContactNo,Experience,Address,DOJ,Age,Gender,DLNo,DLIssuedDate,DLExpirayDate,LicencetoDrive,DrivingLicenceFile from transport_driver where DriverCode=?";
	public static final String DELETE_DRIVER = "delete from transport_driver where DriverCode=?";
	public static final String SEARCH_DRIVER_DETAILS = "select * from transport_driver where  Name like ? or DOJ like ? or MobileNo like ? or DLNo like ? or DLIssuedDate like ? or DLExpirayDate like ?";
	public static final String UPDATE_DRIVER = "update transport_driver set Name=?,FatherName=?,DOB=?,MobileNo=?,EmergencyContactNo=?,Address=?,DOJ=?,Age=?,Gender=?,DLNo=?,DLIssuedDate=?,DLExpirayDate=?,LicencetoDrive=? ,UpdateDate=? ,UpdateUser=?,Experience=?,DrivingLicenceFile=? where DriverCode=?";
	public static final String VALIDATE_DRIVER = "select count(*) driverCount from transport_driver where  Name=? and DOB=? and MobileNo=? and Address=? and DOJ=?";

	// driver mapping
	public static final String GET_UNMAPPED_DRIVER_DETAILS1 = "select distinct DriverCode,Name from transport_driver order by Name";
	public static final String GET_UNMAPPED_DRIVER_DETAILS = "select distinct td.DriverCode,td.Name from transport_driver td,transport_driver_vehicle_mapping tdm where td.DriverCode not in (select DriverCode from transport_driver_vehicle_mapping)";
	public static final String GET_DRIVER_DETAILS_BY_ID = "select DriverCode,Name,MobileNo,Experience,DOJ,DLNo,DLIssuedDate,DLExpirayDate,LicencetoDrive from transport_driver where DriverCode=? order by Name ";
	public static final String GET_UNMAPPED_DRIVER_DETAILS_WHILE_UPDATE = "select distinct td.DriverCode,td.Name from transport_driver td,transport_driver_vehicle_mapping tdm where td.DriverCode not in (select DriverCode from transport_driver_vehicle_mapping where VehicleCode != ?)";
	public static final String GET_SINGLE_DRIVER_DETAILS = "select distinct dr.DriverCode,dr.Name,dr.MobileNo,dr.Experience,dr.DOJ,dr.DLNo,dr.DLIssuedDate,dr.DLExpirayDate,dr.LicencetoDrive from transport_vehicle veh,transport_vehicle_insurancedetails ins,transport_driver dr,transport_driver_vehicle_mapping drm where ins.VehicleCode=veh.VehicleCode and dr.DriverCode in(select DriverCode from transport_driver_vehicle_mapping where VehicleCode =?) and veh.VehicleCode=?";
	public static final String MAP_DRIVER_VEHICLE_INSERTING = "insert into transport_driver_vehicle_mapping(DriverCode,VehicleCode,Createdby,Createtime)value(?,?,?,now())";
	public static final String MAP_DRIVER_VEHICLE_WHILE_UPDATE = "update transport_driver_vehicle_mapping set DriverCode=?,VehicleCode=?,Modifiedby=?,ModifiedDate=now() where VehicleCode=?";
	public static final String CHECKING_DRIVER = "select count(*) from transport_driver_vehicle_mapping where DriverCode=?";
	public static final String VALIDATE_LICENSE = "select count(*) driverCount from transport_driver where  DLNo=?  ";
	// route
	public static final String GET_ROUTE_NAMES = "select RouteCode,RouteName from transport_route order by RouteName";
	public static final String GET_ROUTE_DETAILS = "select RouteCode,RouteName,TotalDistance,Route_No,Total_Stops,Destination,HaltTime from transport_route where RouteCode = ? order by RouteName";
	public static final String MAP_DRIVER_VEHICLE_ROUTE_MAPPING = "insert into transport_vehicle_route_mapping(RouteCode,VehicleCode,CreateUser,CreateDate)value(?,?,?,now())";
	public static final String MAP_DRIVER_VEHICLE_ROUTE_MAPPING_UPDATING = "update transport_vehicle_route_mapping set RouteCode=?,VehicleCode=?,CreateUser=?,CreateDate=now() where  VehicleCode =?";
	public static final String CHECK_VEHICLE_MAPPING = "select count(*) from transport_vehicle_route_mapping where VehicleCode = ?";
	public static final String GET_ROUTE_DETAILS_EDIT = "select rt.RouteCode,rt.RouteName,rt.TotalDistance,rt.Route_No,rt.Total_Stops,rt.HaltTime from transport_route rt,transport_vehicle_route_mapping tmv where rt.RouteCode = tmv.RouteCode and tmv.VehicleCode in(select VehicleCode from transport_vehicle where VehicleCode = ?)";

	public static final String ROUTE_ALLLISTDATASTOP = "select tr.RouteName,tr.RouteCode,tr.Route_No,tr.Start_Time,tr.End_Time,tr.Total_Stops,tr.TotalDistance,tr.HaltTime,tr.Destination,sto.Stop_Name,sto.ArrivalTime,sto.DepTime,sto.Distance from transport_route tr join transport_stopsdetails sto on sto.RouteCode=tr.RouteCode";
	public static final String ROUTE_INSERT_STAGE = "insert into transport_route_stage_mapping(RouteCode,StageCode,CreatedBy,accyear) values(?,?,?,?)";
	public static final String ROUTE_MAPPED_STAGE = "update campus_fee_stage set status='mapped' where stage_id=?" ;
	public static final String ETID_ROUTE_STAGES = "select DISTINCT cfs.stage_name,trsm.StageCode from transport_route_stage_mapping trsm join campus_fee_stage cfs on trsm.StageCode=cfs.stage_id where trsm.RouteCode=? and trsm.accyear=? order by stage_name" ;
	public static final String UNMMAPEDSTAGES = "SELECT  DISTINCT css.stage_name,css.stage_id FROM campus_fee_stage css  WHERE css.accyear=?";
	public static final String DELETE_INSERT_STAGE = "delete from transport_route_stage_mapping where RouteCode=? and accyear=?";

	public static final String GET_ROUTE_NAMES_BY_LOCATION = "select tr.RouteCode,tr.RouteName from transport_route tr,transport_route_stage_mapping tsm where tr.RouteCode=tsm.RouteCode and tsm.StageCode=?";
	
	public static final String GET_ROUTE_DETAILS_BY_VECHILE = "select * from transport_route order by RouteName";
	public static final String INSERT_VECHICLE_TYPE = "insert into transport_typedetails(type_id,type_name,type_description,createdby) VALUES(?,?,?,?)";
	public static final String GET_ALL_VEHICLE_TYPE_DETAILS = "select type_id,type_name,type_description from transport_typedetails order by type_name";
	public static final String GET_FEE_COLLECTION_PAYMODE_ONLINE_REPORT = "SELECT cft.termname,fc.paidDate,fc.chln_no,CONCAT(cd.classdetails_name_var, ' ',cs.classsection_name_var) AS class, CONCAT(st.student_fname_var,' ',st.student_lname_var)AS student ,pr.address,fc.paymentMode,fc.amount_paid FROM  campus_fee_collection fc JOIN campus_student_classdetails csc ON fc.admissionNo = csc.student_id_int AND fc.accYear=csc.fms_acadamicyear_id_int JOIN campus_student st  ON fc.admissionNo = st.student_id_int JOIN campus_parentchildrelation par ON par.stu_addmissionNo = fc.admissionNo JOIN  campus_parents pr ON pr.parentid = par.ParentID JOIN campus_classdetail cd ON cd.classdetail_id_int=csc.classdetail_id_int AND cd.locationId = csc.locationId JOIN campus_classsection cs ON cs.classsection_id_int=csc.classsection_id_int AND cs.locationId = csc.locationId  JOIN campus_fee_termdetails cft ON cft.termid=fc.termcode where csc.locationId LIKE ? and fc.accYear = ? and cd. classdetail_id_int like ? and cs.classsection_id_int like ? and fc.termcode like ? and fc.paymentMode = 'Online' order by fc.chln_no and cd.classdetails_name_var and cs.classsection_name_var and st.student_fname_var and st.student_lname_var";


	public static final String EDIT_TRANSPORT_CATEGORY_DETAILS = "select type_id,type_name,type_description from transport_typedetails  where type_id=?";
	public static final String DELETE_VEHICLE_TYPE_DETAILS = "delete from transport_typedetails where type_id=?";
	public static final String VALIDATE_VEHICLE_TYPE = "select count(*) from transport_typedetails where type_name= ?";
	public static final String UPDATE_VECHICLE_TYPE = "update transport_typedetails set type_name=?,type_description=?,updatedby=?,updaeddate=? where type_id=?";
	public static final String SEARCH_VEHICLE_TYPE = "select type_id,type_name,type_description from transport_typedetails where (type_id like ? or type_name like ? or type_description like ?) order by type_name";
	public static final String STUDENT_DETAILS_TRANSPORT = "select cs.student_admissionno_var,concat(cs.student_fname_var,' ',cs.student_lname_var) as student,cp.parentid,cc.classdetail_id_int,cc.classdetails_name_var  from campus_student cs join campus_parentchildrelation cp  on cs.student_id_int= cp.stu_addmissionNo and cs.student_id_int=? and cp.stu_addmissionNo=? and cs.fms_acadamicyear_id_int=? and cs.locationId=? join campus_classdetail cc  where cc.classdetail_id_int=? and cc.locationId=? ";
	public static final String GET_STUDENTS_BY_LOCATION_ACCYEAR = "select distinct st.student_id_int,cstd.classsection_id_int,cstd.fms_acadamicyear_id_int,cstd.classdetail_id_int,cstd.locationId,case when cstd.student_rollno is null then '-' else cstd.student_rollno end student_rollno,st.student_admissionno_var,cstd.classdetail_id_int,cstd.classsection_id_int,case when st.student_lname_var is null then st.student_fname_var else concat(st.student_fname_var,' ',st.student_lname_var)end studentName,cls.classdetails_name_var,sec.classsection_name_var,st.student_dob_var,st.student_status_var from campus_student st join campus_student_classdetails cstd on st.student_id_int=cstd.student_id_int join campus_classdetail cls on cls.classdetail_id_int=cstd.classdetail_id_int join campus_classsection sec on sec.classsection_id_int=cstd.classsection_id_int join campus_acadamicyear acc on acc.acadamic_id=cstd.fms_acadamicyear_id_int left join campus_student_transportdetails tra on tra.student_id_int = cstd.student_id_int where cstd.locationId like ? and cstd.fms_acadamicyear_id_int like ? and tra.isTransport = 'Y' order by length(cls.classdetail_id_int),cls.classdetail_id_int,sec.classsection_name_var,studentName";
	public static final String GET_STUDENT_LIST = "select distinct csc.locationId,csc.classsection_id_int,csc.classdetail_id_int,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on csc.classdetail_id_int=ccd.classdetail_id_int join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_student_transportdetails tra on tra.student_id_int = csc.student_id_int  where csc.locationId like ? and csc.fms_acadamicyear_id_int=? and tra.isTransport = 'Y' order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),length(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,studentname";
	public static final String GET_FILTERED_STUDENTDETAILS = "select distinct csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,csc.classdetail_id_int,ccs.classsection_id_int,stu.student_status_var,acy.acadamic_year, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var)end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var,stu.student_dob_var from campus_student stu JOIN campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on csc.classdetail_id_int=ccd.classdetail_id_int join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int join campus_acadamicyear acy on csc.fms_acadamicyear_id_int=acy.acadamic_id left join campus_student_transportdetails tra on tra.student_id_int = csc.student_id_int where tra.isTransport = 'Y' and  csc.locationId like ? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int like ?  order by length(csc.classdetail_id_int),csc.classdetail_id_int,ccs.classsection_name_var,studentName";
	public static final String GET_FILTERED_STUDENTD_BY_SECTION = "select distinct ccd.classdetail_id_int,csc.classsection_id_int,csc.secondlanguage,csc.thirdlanguage,csc.specilization,stu.student_admissionno_var,stu.student_gender_var,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,acy.acadamic_year , case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var)end studentname,CASE WHEN csc.student_rollno IS NULL THEN '-' ELSE csc.student_rollno END student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu JOIN campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on csc.classdetail_id_int=ccd.classdetail_id_int join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int join campus_acadamicyear acy on csc.fms_acadamicyear_id_int=acy.acadamic_id left join campus_student_transportdetails tra on tra.student_id_int = csc.student_id_int where csc.locationId like ? and csc.fms_acadamicyear_id_int=? and tra.isTransport = 'Y' and csc.classdetail_id_int=? and csc.classsection_id_int like ? order by CAST(csc.student_rollno AS UNSIGNED)";
	public static final String GET_STUDENTS_SEARCH_BY_LIST = "select distinct csc.classdetail_id_int,csc.classsection_id_int,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentName,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on csc.classdetail_id_int=ccd.classdetail_id_int join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_student_transportdetails tra on tra.student_id_int = csc.student_id_int where tra.isTransport = 'Y' and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or concat(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ?)";
	public static final String GET_STU_DETAILS = "select concat(st.student_fname_var,' ',st.student_lname_var)as student,tra.isTransport,tra.StageId,tra.route,tra.start_month,tra.end_month,st.student_admissionno_var,cd.classdetails_name_var,csc.student_status,cs.classsection_name_var,pc.address from campus_student st left join campus_student_classdetails csc on st.student_id_int = csc.student_id_int left join campus_parentchildrelation par on par.stu_addmissionNo = st.student_id_int left join campus_parents pc on pc.ParentID = par.parentid left join campus_classdetail cd on cd.classdetail_id_int = csc.classdetail_id_int left join campus_classsection cs on cs.classsection_id_int = csc.classsection_id_int left join campus_student_transportdetails tra on tra.student_id_int = csc.student_id_int where st.student_id_int = ? and csc.locationId = ? and csc.classdetail_id_int = ? and csc.classsection_id_int = ? and csc.fms_acadamicyear_id_int = ?";
	public static final String GET_STUDENTS_SEARCH_BY_ACCYEAR = "select distinct csc.classdetail_id_int,csc.classsection_id_int,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentName,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on csc.classdetail_id_int=ccd.classdetail_id_int join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_student_transportdetails tra on tra.student_id_int = csc.student_id_int where tra.isTransport = 'Y' and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or concat(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ?) and csc.fms_acadamicyear_id_int like ?";
	public static final String GET_STUDENTS_SEARCH_BY_LOCATION = "select distinct csc.classdetail_id_int,csc.classsection_id_int,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentName,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno ,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on csc.classdetail_id_int=ccd.classdetail_id_int join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_student_transportdetails tra on tra.student_id_int = csc.student_id_int where tra.isTransport = 'Y' and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or concat(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ?) and csc.locationId like ?";
	public static final String GET_STUDENTS_SEARCH_BY_FILTER = "select distinct csc.classdetail_id_int,csc.classsection_id_int,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentName,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno ,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on csc.classdetail_id_int=ccd.classdetail_id_int join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_student_transportdetails tra on tra.student_id_int = csc.student_id_int where tra.isTransport = 'Y' and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or concat(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ?) and csc.locationId like ?";
	public static final String GET_STUDENTS_SEARCH_BY_CLASS = "select distinct csc.classdetail_id_int,csc.classsection_id_int,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentName,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno ,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on csc.classdetail_id_int=ccd.classdetail_id_int join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_student_transportdetails tra on tra.student_id_int = csc.student_id_int where tra.isTransport = 'Y' and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or concat(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ?) and csc.locationId like ? and csc.fms_acadamicyear_id_int like ? and ccd.classdetail_id_int like ?";
	public static final String GET_STUDENTS_SEARCH_BY_ALL_FILTER = "select distinct csc.classdetail_id_int,ccs.classsection_id_int,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentName,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on csc.classdetail_id_int=ccd.classdetail_id_int join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_student_transportdetails tra on tra.student_id_int = csc.student_id_int where tra.isTransport = 'Y' and (stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or concat(stu.student_fname_var,' ',stu.student_lname_var) like ? or ccs.classsection_name_var like ? or ccd.classdetails_name_var like ? or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ?) and csc.locationId like ? and csc.fms_acadamicyear_id_int like ? and ccd.classdetail_id_int=? and ccs.classsection_id_int=?";
	public static final String UPDATE_TRANSPORT_REQUEST = "update campus_student_transportdetails set isTransport = 'Y',StageId = ?,route = ?,start_month = ?,end_month = ?,NumberOfMonth=? where student_id_int = ? and fms_acadamicyear_id_int = ? and locationId = ?";
	public static final String UPDATE_TRANSPORT_WAIVEDOFF = "update campus_student_transportdetails set isTransport = 'N',StageId ='NIL',route ='NIL' where student_id_int = ? and fms_acadamicyear_id_int = ? and locationId = ?";
	public static final String GETSTUDENTBUSCARDDETAILS = "SELECT CONCAT(st.student_fname_var,' ',st.student_lname_var) AS student,pa.mobileno,CASE WHEN trf.reciept_no IS NULL THEN '-' ELSE trf.reciept_no END reciept_no,CONCAT(cd.classdetails_name_var,' ',cs.classsection_name_var)AS class,cf.stage_name,CASE WHEN rn.RouteName IS NULL THEN '-' ELSE rn.RouteName END RouteName FROM campus_student st JOIN campus_student_transportdetails tra ON st.student_id_int = tra.student_id_int JOIN campus_student_classdetails csc ON csc.student_id_int = st.student_id_int JOIN campus_classdetail cd ON  csc.classdetail_id_int = cd.classdetail_id_int AND csc.locationId = cd.locationId JOIN campus_classsection cs ON csc.classsection_id_int = cs.classsection_id_int AND csc.locationId = cs.locationId JOIN campus_parentchildrelation pra ON pra.stu_addmissionNo = tra.student_id_int JOIN campus_parents pa ON pa.parentid = pra.ParentID  JOIN transport_route rn ON rn.RouteCode = tra.route JOIN campus_fee_stage cf ON cf.stage_id = tra.StageId   LEFT  JOIN campus_tranport_fee_collection_details trf ON tra.student_id_int=trf.admissionNo AND trf.accYear = ? AND trf.termcode = ? WHERE  isTransport = 'Y' AND csc.student_id_int = ? AND csc.fms_acadamicyear_id_int = ? AND csc.locationId =? GROUP BY trf.termcode";

}
