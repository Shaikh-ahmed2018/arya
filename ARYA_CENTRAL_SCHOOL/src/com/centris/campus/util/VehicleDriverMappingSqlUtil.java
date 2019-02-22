package com.centris.campus.util;

public class VehicleDriverMappingSqlUtil {

	
	public static final String GET_DRIVER_LIST = "select TeacherID,concat(FirstName,'',LastName)as drivername from campus_teachers where teachingType='Non-Teaching' and isActive='Y'";
	public static final String GET_AVAILABLE_VEHICLES_LIST = "select v.VehicleCode,concat(v.VehicleName,'-',VehicleType ) as vehiclename from transport_vehicle v where v.VehicleCode not in (select VehicleCode from transport_driver_vehicle_mapping)";
	public static final String GET_MAPPED_VEHICLES_LIST = "select dvm.VehicleCode,concat(v.VehicleName,'-',VehicleType ) as vehiclename from transport_driver_vehicle_mapping dvm,transport_vehicle v where dvm.VehicleCode=v.VehicleCode and dvm.DriverCode=?";
	public static final String INSERT_DRIVER_VEHICLE_MAP_DETAILS ="insert into transport_driver_vehicle_mapping(DriverCode,VehicleCode,Createdby,Createtime) values(?,?,?,?)";
	public static final String GET_DRIVER_VEHICLE_MAP_DETAILS = "select dvm.Sno,concat(d.FirstName,'',d.LastName)as drivername,concat(v.VehicleName,'-',v.VehicleType)as vehiclename from transport_vehicle v,transport_driver_vehicle_mapping dvm,campus_teachers d where v.VehicleCode=dvm.VehicleCode and d.TeacherID=dvm.DriverCode";
	public static final String GET_SINGLE_DRIVER_VEHICLE_MAP_DETAILS = "select dvm.DriverCode,dvm.VehicleCode,concat(d.FirstName,'',d.LastName)as drivername,concat(v.VehicleName,'-',v.VehicleType)as vehiclename from transport_vehicle v,transport_driver_vehicle_mapping dvm,campus_teachers d where v.VehicleCode=dvm.VehicleCode and d.TeacherID=dvm.DriverCode and dvm.Sno=?";
	public static final String UPDATE_DRIVER_VEHICLE_MAP_DETAILS ="update transport_driver_vehicle_mapping set DriverCode=?,VehicleCode=?,Modifiedby=?,ModifiedDate=? where Sno=?";
	public static final String DELETE_DRIVER_VEHICLE_MAP_DETAILS = "delete from transport_driver_vehicle_mapping where DriverCode=?";
	public static final String GET_SEARCH_DRIVER_VEHICLE_MAP_DETAILS="select dvm.Sno,concat(d.FirstName,'',d.LastName)as drivername,concat(v.VehicleName,'-',v.VehicleType)as vehiclename from transport_vehicle v,transport_driver_vehicle_mapping dvm,campus_teachers d where v.VehicleCode=dvm.VehicleCode and d.TeacherID=dvm.DriverCode and (d.FirstName like ? or d.LastName like ? or v.VehicleName like ? or v.VehicleType like ?)";
	public static final String DELETE_VEHICLE_DRIVER_MAPPING="delete from transport_driver_vehicle_mapping where Sno=?";
}
