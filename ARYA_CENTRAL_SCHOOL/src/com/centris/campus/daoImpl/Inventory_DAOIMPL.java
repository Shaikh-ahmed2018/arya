package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.centris.campus.dao.Inventory_DAO;
import com.centris.campus.forms.InventoryForm;
import com.centris.campus.forms.InventoryTransactionForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.AddorModifyorDeleteVO;
import com.centris.campus.vo.InventoryTransactionVO;
import com.centris.campus.vo.InventoryVO;

public class Inventory_DAOIMPL implements Inventory_DAO {

	private static Logger logger = Logger.getLogger(Inventory_DAOIMPL.class);

	
	public List<InventoryVO> InventoryTypesList() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<InventoryVO> list = new ArrayList<InventoryVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.INVENTORY_TYPES_LIST);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				InventoryVO vo = new InventoryVO();

				vo.setItem_type_id(rs.getString("Item_TYPE_ID"));
				vo.setItem_type_name(rs.getString("ITEM_TYPE_NAME"));
				vo.setDepartment(rs.getString("department"));
				vo.setItem_type_description(rs
						.getString("ITEM_TYPE_DESCRIPTION"));

				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return list;

	}
	
	public List<InventoryVO> searchInventoryTypeList(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<InventoryVO> list = new ArrayList<InventoryVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.INVENTORY_TYPES_LIST_SEARCH);

			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				InventoryVO vo = new InventoryVO();

				vo.setItem_type_id(rs.getString("Item_TYPE_ID"));
				vo.setItem_type_name(rs.getString("ITEM_TYPE_NAME"));
				vo.setItem_type_description(rs
						.getString("ITEM_TYPE_DESCRIPTION"));

				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return list;

	}
	
	public String AddInventoryType(InventoryForm form) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Starting");
		PreparedStatement pstmt = null;
		int rs = 0;

		String status = null;
		Connection conn = null;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();

		try {

			conn = JDBCConnection.getSeparateConnection();

			if (form.getInventory_id() == "" || form.getInventory_id()==null) {
				pstmt = conn
						.prepareStatement(SQLUtilConstants.INSERT_INVENTORY_TYPES_LIST);

				pstmt.setString(1,
						IDGenerator.getPrimaryKeyID("campus_inventory_types"));
				pstmt.setString(2, form.getInventory_type_name().trim());
				pstmt.setString(3, form.getDepartment().trim());
				pstmt.setString(4, form.getDescription().trim());
				pstmt.setString(5, form.getCurrent_user().trim());
				pstmt.setTimestamp(6, createdDate);

				System.out.println(pstmt);

				rs = pstmt.executeUpdate();

				if (rs >= 1) {
					status = "true";
				} else {
					status = "false";
				}
			}
			
			
			else
			
			{
				
				//Update Operation
				pstmt = conn
						.prepareStatement(SQLUtilConstants.UPDATE_INVENTORY_TYPES_LIST);

				
				pstmt.setString(1, form.getInventory_type_name().trim());
				pstmt.setString(2, form.getDepartment().trim());
				pstmt.setString(3, form.getDescription().trim());
				pstmt.setString(4, form.getCurrent_user().trim());
				pstmt.setTimestamp(5, createdDate);
				pstmt.setString(6, form.getInventory_id());

				System.out.println(pstmt);
				rs = pstmt.executeUpdate();

				if (rs >= 1) {
					status = "true1";
					form.setMessage("Inventory Type Updated Successfully");
					
				} else {
					status = "false1";
					form.setMessage("Inventory Type Not Updated Successfully");

				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Ending");
		return status;

	}
	
	public InventoryVO EditInventoryType(InventoryVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Boolean status = false;
		Connection conn = null;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_INVENTORY_TYPES_LIST);

			pstmt.setString(1, vo.getItem_type_id());

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				vo.setItem_type_name(rs.getString("ITEM_TYPE_NAME").trim());
				vo.setDepartment(rs.getString("department").trim());
				vo.setItem_type_description(rs
						.getString("ITEM_TYPE_DESCRIPTION").trim());
				
				vo.setItem_type_id(rs.getString("Item_TYPE_ID").trim());
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Ending");
		return vo;

	}
	
	public String DeleteInventoryType(InventoryVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no=0;

		String status = null;
		Connection conn = null;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();

		try {

			conn = JDBCConnection.getSeparateConnection();
			for(int i=0;i<vo.getGetDataArray().length;i++){ //-------6
			pstmt = conn
					.prepareStatement(SQLUtilConstants.DELETE_INVENTORY_TYPES_LIST);

			pstmt.setString(1, vo.getGetDataArray()[i]);

			System.out.println(pstmt);
			
			no = pstmt.executeUpdate();
			}
			System.out.println(no);
		

			if(no>0)
			{
				status="true";
			}
			else
			{
				status="false";
			}
			
			
			

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Ending");
		return status;

	}
	
	public List<AddorModifyorDeleteVO> AddorModifyorDeleteList() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.AddorModifyorDeleteList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AddorModifyorDeleteVO vo = new AddorModifyorDeleteVO();
				
				vo.setPurchase_id(rs.getString("Purchase_id"));
				vo.setPurchase_item_id(rs.getString("purchase_item_id"));
				vo.setPurchase_item_name(rs.getString("purchase_item_name"));
				vo.setDepartment(rs.getString("DEPT_NAME"));
				vo.setItem_type(rs.getString("ITEM_TYPE_NAME"));
				vo.setTotal_Quantity(rs.getString("total_Quantity"));
				vo.setPurchased_date(HelperClass.convertDatabaseToUI(rs.getString("purchased_date")));
				vo.setPurchased_by(rs.getString("purchased_by"));
				vo.setManufacturer(rs.getString("manufacturer"));
				vo.setAddress(rs.getString("address"));
				vo.setContact_no(rs.getString("contact_no"));
			
				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return list;

	}
	
	public String CreatingAddorModifyorDelete(InventoryForm form) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Starting");
		PreparedStatement pstmt = null;
		int rs = 0;
		PreparedStatement pstmt1 = null;
		int rs1 = 0;
		String id =null;
		String status = null;
		Connection conn = null;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();

		try {
			
			
			conn = JDBCConnection.getSeparateConnection();

			if (form.getPurchase_id() == " "||form.getPurchase_id() == "undefined" || form.getPurchase_id().isEmpty()) {
				
				id=IDGenerator.getPrimaryKeyID("campus_inventory_purchase_details");
				
				pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_INVENTORY_ADD_OR_MODIFY_DELETE);

				pstmt.setString(1,id);
				pstmt.setString(2, form.getItem_id());
				pstmt.setString(3, form.getItem_name().trim());
				pstmt.setString(4, form.getDepartment().trim());
				pstmt.setString(5, form.getItem_type());
				pstmt.setString(6, form.getTotal_quantity());
				pstmt.setString(7, HelperClass.convertUIToDatabase(form.getPurchased_date()));
				pstmt.setString(8, form.getPurchased_by());
				pstmt.setString(9, form.getManufacturer());
				pstmt.setString(10, form.getAddress().trim());
				pstmt.setString(11,form.getContact_number());
				pstmt.setString(12, form.getCurrent_user().trim());
				pstmt.setTimestamp(13, createdDate);
				
				System.out.println(pstmt);

				rs = pstmt.executeUpdate();
	

				if (rs >= 1) {
					int countval=0;
					String issued=null;
					PreparedStatement counts=conn.prepareStatement("SELECT COUNT(*), issued_quantity from campus_inventory_availability_items where item_id=?");
					counts.setString(1,form.getItem_id());
					ResultSet rscounts=counts.executeQuery();
					while(rscounts.next()){
						countval=rscounts.getInt(1);
						issued=rscounts.getString("issued_quantity");
					}
					
					String purchase_item_quantity=null;
					PreparedStatement itemCount=conn.prepareStatement("SELECT SUM(total_Quantity) total from campus_inventory_purchase_details where purchase_item_id=?");
					itemCount.setString(1,form.getItem_id());
					ResultSet rsItemCount=itemCount.executeQuery();
					if(rsItemCount.next()){
						purchase_item_quantity=rsItemCount.getString("total");
					}
					if(countval > 0){
					
					
					pstmt1 = conn.prepareStatement(SQLUtilConstants.UPDATE_INVENTORY_ADD_OR_MODIFY_DELETE_Total_quantitys);
					
					int avail=Integer.parseInt(purchase_item_quantity)-Integer.parseInt(issued);
					String avaialable=Integer.toString(avail);
					pstmt1.setString(1, purchase_item_quantity);
					pstmt1.setString(2, issued);	
					pstmt1.setString(3, avaialable);
					pstmt1.setString(4,form.getItem_id());

					System.out.println(pstmt1);

					rs1 = pstmt1.executeUpdate();
					}
					else{
						pstmt1 = conn
								.prepareStatement(SQLUtilConstants.INSERT_INVENTORY_ADD_OR_MODIFY_DELETE_Total_quantity);
										
						pstmt1.setString(1,form.getItem_id());
						pstmt1.setString(2, form.getItem_type());
						pstmt1.setString(3, form.getItem_name().trim());
						pstmt1.setString(4, purchase_item_quantity);
						pstmt1.setString(5, "0");	
						pstmt1.setString(6, purchase_item_quantity);
						rs1 = pstmt1.executeUpdate();
					}
					status = "true";
				} else {
					status = "false";
				}
				}
			
			
			else
			
			{
				
				//Update Operation
				pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_INVENTORY_ADD_OR_MODIFY_DELETE);

						pstmt.setString(1, form.getItem_id());
						pstmt.setString(2, form.getItem_name().trim());
						pstmt.setString(3, form.getDepartment().trim());
						pstmt.setString(4, form.getItem_type());
						pstmt.setString(5, form.getTotal_quantity());
						pstmt.setString(6, HelperClass.convertUIToDatabase(form.getPurchased_date()));
						pstmt.setString(7, form.getPurchased_by());
						pstmt.setString(8, form.getManufacturer());
						pstmt.setString(9, form.getAddress().trim());
						pstmt.setString(10,form.getContact_number());
						pstmt.setString(11, form.getCurrent_user().trim());
						pstmt.setTimestamp(12, createdDate);
						pstmt.setString(13,form.getPurchase_id());


				System.out.println(pstmt);
				rs = pstmt.executeUpdate();
				
				

				if (rs >= 1 ) {
					String purchase_item_quantity=null;
					PreparedStatement itemCount=conn.prepareStatement("SELECT SUM(total_Quantity) total from campus_inventory_purchase_details where purchase_item_id=?");
					itemCount.setString(1,form.getItem_id());
					ResultSet rsItemCount=itemCount.executeQuery();
					if(rsItemCount.next()){
						purchase_item_quantity=rsItemCount.getString("total");
					}
					
					String issued=null;
					PreparedStatement counts=conn.prepareStatement("SELECT  issued_quantity from campus_inventory_availability_items where item_id=?");
					counts.setString(1,form.getItem_id());
					ResultSet rscounts=counts.executeQuery();
					while(rscounts.next()){
						issued=rscounts.getString("issued_quantity");
					}
					pstmt1 = conn.prepareStatement(SQLUtilConstants.UPDATE_INVENTORY_ADD_OR_MODIFY_DELETE_Total_quantity_purchase);
					
					int avail=Integer.parseInt(purchase_item_quantity)-Integer.parseInt(issued);
					String avaialable=Integer.toString(avail);
					
					pstmt1.setString(1, purchase_item_quantity);	
					pstmt1.setString(2, avaialable);
					pstmt1.setString(3,form.getItem_id());


					System.out.println(pstmt1);

					rs1 = pstmt1.executeUpdate();
					status = "true1";
					
				} else {
					status = "false1";

				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Ending");
		return status;

	}
	
	public AddorModifyorDeleteVO EditAddorModifyorDelete(AddorModifyorDeleteVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_INVENTORY_ADD_OR_MODIFY_DELETE);

			pstmt.setString(1, vo.getPurchase_id());

			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
			vo.setPurchase_id(rs.getString("Purchase_id"));
			vo.setPurchase_item_id(rs.getString("purchase_item_id"));
			vo.setPurchase_item_name(rs.getString("purchase_item_name"));
			vo.setDepartment(rs.getString("department"));
			vo.setItem_type(rs.getString("item_type"));
			vo.setTotal_Quantity(rs.getString("total_Quantity"));
			vo.setPurchased_date(HelperClass.convertDatabaseToUI(rs.getString("purchased_date")));
			vo.setPurchased_by(rs.getString("purchased_by"));
			vo.setManufacturer(rs.getString("manufacturer"));
			vo.setAddress(rs.getString("address"));
			vo.setContact_no(rs.getString("contact_no"));

			}	

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Ending");
		return vo;

	}
	
	@Override
	
    public String DeleteAddorModifyorDelete(AddorModifyorDeleteVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no=0;

		String status = null;
		Connection conn = null;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();

		try {

			conn = JDBCConnection.getSeparateConnection();
			String purchase_item_id=null;
			String purchase_item_count=null;
			
			PreparedStatement itemID=conn.prepareStatement("SELECT  purchase_item_id,total_Quantity from campus_inventory_purchase_details where purchase_id=?");
			itemID.setString(1,vo.getPurchase_id());
			ResultSet rsItemID=itemID.executeQuery();
			if(rsItemID.next()){
				purchase_item_id=rsItemID.getString("purchase_item_id");
				purchase_item_count=rsItemID.getString("total_Quantity");
			}
			System.out.println(itemID);
			String purchase_item_quantity=null;
			
			PreparedStatement itemCount=conn.prepareStatement("SELECT SUM(total_Quantity) total from campus_inventory_purchase_details where purchase_item_id=?");
			itemCount.setString(1,purchase_item_id);
			ResultSet rsItemCount=itemCount.executeQuery();
			if(rsItemCount.next()){
				purchase_item_quantity=rsItemCount.getString("total");
			}
			
			
			PreparedStatement pstmt1 = conn.prepareStatement(SQLUtilConstants.UPDATE_INVENTORY_ADD_OR_MODIFY_DELETE_Total_quantity_purchase);
			
			int avail=Integer.parseInt(purchase_item_quantity)-Integer.parseInt(purchase_item_count);
			String avaialable=Integer.toString(avail);
			
			pstmt1.setString(1, avaialable);	
			pstmt1.setString(2, avaialable);
			pstmt1.setString(3,purchase_item_id);


			System.out.println(pstmt1);

		int	rs1 = pstmt1.executeUpdate();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.DELETE_INVENTORY_ADD_OR_MODIFY_DELETE);

			pstmt.setString(1, vo.getPurchase_id());

			System.out.println(pstmt);
			
			no = pstmt.executeUpdate();
			
			System.out.println(no);
		

			if(no>0)
			{
				
				status="true";
			}
			else
			{
				status="false";
			}
			
			
			

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Ending");
		return status;

	}

	@Override
	public List<AddorModifyorDeleteVO> SearchAddorModifyorDeleteList(
			String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.SEARCH_INVENTORY_ADD_OR_MODIFY_DELETE);

			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			pstmt.setString(4, "%" + searchName + "%");
			pstmt.setString(5, "%" + searchName + "%");
			pstmt.setString(6, "%" + searchName + "%");
			pstmt.setString(7, "%" + searchName + "%");
			pstmt.setString(8, "%" + searchName + "%");
			pstmt.setString(9, "%" + searchName + "%");
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AddorModifyorDeleteVO vo = new AddorModifyorDeleteVO();
				
				vo.setPurchase_id(rs.getString("Purchase_id"));
				vo.setPurchase_item_id(rs.getString("purchase_item_id"));
				vo.setPurchase_item_name(rs.getString("purchase_item_name"));
				vo.setDepartment(rs.getString("DEPT_NAME"));
				vo.setItem_type(rs.getString("ITEM_TYPE_NAME"));
				vo.setTotal_Quantity(rs.getString("total_Quantity"));
				vo.setPurchased_date(HelperClass.convertDatabaseToUI(rs.getString("purchased_date")));
				vo.setPurchased_by(rs.getString("purchased_by"));
				vo.setManufacturer(rs.getString("manufacturer"));
				vo.setAddress(rs.getString("address"));
				vo.setContact_no(rs.getString("contact_no"));
			
				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return list;

	}

	@Override
	public List<AddorModifyorDeleteVO> InventoryList() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;

		ArrayList<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.INVENTORY_LIST_Total_quantity);

			System.out.println(pstmt);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				
				AddorModifyorDeleteVO vo = new AddorModifyorDeleteVO();
				
				vo.setPurchase_item_id(rs.getString("item_id"));
				vo.setPurchase_item_name(rs.getString("item_name"));
				vo.setItem_type(rs.getString("ITEM_TYPE_NAME"));
				vo.setItem_type_id(rs.getString("Item_TYPE_ID"));
				vo.setTotal_Quantity(rs.getString("total_quantity"));
				vo.setIssued_quantity(rs.getString("issued_quantity"));
				
				String total_qty=rs.getString("total_quantity");
				int total_quantity=new Integer(total_qty);
				
				String issued = rs.getString("issued_quantity");
				int isuued_quantity=new Integer(issued);
				
				
				int available_Qty=total_quantity-isuued_quantity;
				
				vo.setAvailable_quantity(available_Qty+"");
			
				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return list;

	}

	@Override
	public List<AddorModifyorDeleteVO> SearchInventoryList(String searchName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.INVENTORY_LIST_SEARCH);

			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			pstmt.setString(4, "%" + searchName + "%");
			pstmt.setString(5, "%" + searchName + "%");
			pstmt.setString(6, "%" + searchName + "%");
			
			System.out.println(pstmt);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				AddorModifyorDeleteVO vo = new AddorModifyorDeleteVO();
				
				vo.setPurchase_item_id(rs.getString("item_id"));
				vo.setPurchase_item_name(rs.getString("item_name"));
				vo.setItem_type(rs.getString("ITEM_TYPE_NAME"));
				vo.setTotal_Quantity(rs.getString("total_Quantity"));
				vo.setIssued_quantity(rs.getString("issued_quantity"));
				vo.setAvailable_quantity(rs.getString("available_quantity"));
			
				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return list;

	}

	@Override
	public List<InventoryTransactionVO> InventoryTransactionList() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<InventoryTransactionVO> list = new ArrayList<InventoryTransactionVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.InventoryTransactionList);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				InventoryTransactionVO vo = new InventoryTransactionVO();
				
				vo.setTransaction_id(rs.getString("transaction_id"));
				vo.setItem_id(rs.getString("item_id"));
				vo.setItem_type(rs.getString("item_type"));
				vo.setItem_name(rs.getString("item_name"));
				vo.setRequested_by(rs.getString("requested_by"));
				vo.setIssued_by(rs.getString("issued_by"));
				vo.setIssued_to(rs.getString("issued_to"));
				vo.setIssued_date(HelperClass.convertDatabaseToUI(rs.getString("issued_date")));
				vo.setReturned_date(HelperClass.convertDatabaseToUI(rs.getString("returned_date")));
				vo.setRemarks(rs.getString("remarks"));
				vo.setStatus(rs.getString("status"));
				List<String> dateSize=HelperClass.getDateListBetweenDates(rs.getString("returned_date"),(HelperClass.getCurrentSqlDate()).toString());
				int delayedDate=dateSize.size()-1;
				String delayedstatus="";
				if(delayedDate >0){
					delayedstatus="Delayed "+delayedDate+ " Days";
					
				}
				else{
					delayedstatus=(delayedDate-1)+" Days is remained";
					delayedstatus=delayedstatus.replaceFirst("-", "*");
					//delayedstatus=rs.getString("delayed_status");
					
				}
				if(rs.getString("status").equalsIgnoreCase("returned")){
					delayedstatus="Already Returned";
					//vo.setDelayed_status("Already Returned");
					
				}
				vo.setDelayed_status(delayedstatus);
				System.out.println(delayedstatus);
			
				
				
				vo.setReturned_by(rs.getString("returned_by"));
				
				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return list;

	}

	public String CreateTransactionDetails(InventoryTransactionForm form,String tid) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Starting");
		PreparedStatement pstmt = null;
		int rs = 0;
		PreparedStatement pstmt1 = null;
		int rs1 = 0;
		String id =null;
		String status = null;
		Connection conn = null;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();

		try {
			
			
			conn = JDBCConnection.getSeparateConnection();
			System.out.println("tra in d"+form.getTransaction_id());

			if (tid == null || tid == "") {
				id=IDGenerator.getPrimaryKeyID("campus_inventory_transaction_details");
				System.out.println("id:::........: "+id);
				
				pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_INVENTORY_TRANSACTION_DETAILS);

				pstmt.setString(1,id);
				
				pstmt.setString(2, form.getItem_id());
				pstmt.setString(3, form.getItem_type().trim());
				pstmt.setString(4, form.getItem_name().trim());
				pstmt.setString(5, form.getRequested_by());
				pstmt.setString(6, form.getIssued_to());
				pstmt.setString(7, form.getIssued_by());
				pstmt.setString(8, HelperClass.convertUIToDatabase(form.getIssued_date()));
				pstmt.setString(9, HelperClass.convertUIToDatabase(form.getReturned_date()));
				//pstmt.setString(10, form.getStatus());
				pstmt.setString(10, form.getIssued_quantity());
				pstmt.setString(11, form.getIssuetime());
				
				pstmt.setString(12, form.getReturntime());
				
				System.out.println(pstmt);

				rs = pstmt.executeUpdate();
				
				

				if (rs >= 1) {
					String issued_item=null;
					PreparedStatement itemCount=conn.prepareStatement("SELECT SUM(issued_quantity) issued_item from campus_inventory_transaction_details where item_id=? and status='issued'");
					itemCount.setString(1,form.getItem_id());
					ResultSet rsItemCount=itemCount.executeQuery();
					if(rsItemCount.next()){
						 issued_item=rsItemCount.getString("issued_item");
					}
					String total=null;
					PreparedStatement counts=conn.prepareStatement("SELECT  total_quantity from campus_inventory_availability_items where item_id=?");
					counts.setString(1,form.getItem_id());
					ResultSet rscounts=counts.executeQuery();
					while(rscounts.next()){
						total=rscounts.getString("total_quantity");
					}
					
					String available=Integer.toString(Integer.parseInt(total)-Integer.parseInt(issued_item));
					pstmt1 = conn
							.prepareStatement(SQLUtilConstants.UPDATE_INVENTORY);
					
					pstmt1.setString(1, issued_item);	
					pstmt1.setString(2, available);	
					pstmt1.setString(3,form.getItem_id());
					
					System.out.println(pstmt1);
					rs1 = pstmt1.executeUpdate();
					status = "true";
				} else {
					status = "false";
				}
			}
				else{
					pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_INVENTORY_TRANSACTION_DETAILS);

				
					
					pstmt.setString(1, form.getItem_id());
					pstmt.setString(2, form.getItem_type().trim());
					pstmt.setString(3, form.getItem_name().trim());
					pstmt.setString(4, form.getRequested_by());
					pstmt.setString(5, form.getIssued_to());
					pstmt.setString(6, form.getIssued_by());
					pstmt.setString(7, HelperClass.convertUIToDatabase(form.getIssued_date()));
					pstmt.setString(8, HelperClass.convertUIToDatabase(form.getReturned_date()));
					pstmt.setString(9, form.getReturned_by());
					//pstmt.setString(9, form.getStatus());
					pstmt.setString(9, form.getIssued_quantity());
					
					pstmt.setString(10, tid);
					


			System.out.println(pstmt);
			rs = pstmt.executeUpdate();
			
			

			if (rs >= 1 ) {
				String purchase_item_quantity=null;
				PreparedStatement itemCount=conn.prepareStatement("SELECT SUM(total_Quantity) total from campus_inventory_purchase_details where purchase_item_id=? ");
				itemCount.setString(1,form.getItem_id());
				ResultSet rsItemCount=itemCount.executeQuery();
				if(rsItemCount.next()){
					purchase_item_quantity=rsItemCount.getString("total");
				}
				
				System.out.println(purchase_item_quantity);
				String issued=null;
				PreparedStatement counts=conn.prepareStatement("select sum(issued_quantity) issued_quantity from campus_inventory_transaction_details where status='issued' and item_id=?");
				counts.setString(1,form.getItem_id());
				ResultSet rscounts=counts.executeQuery();
				while(rscounts.next()){
					issued=rscounts.getString("issued_quantity");
					System.out.println(issued);
				}
				pstmt1 = conn.prepareStatement(SQLUtilConstants.UPDATE_INVENTORY_ADD_OR_MODIFY_DELETE_Total_quantity);
				
				int avail=Integer.parseInt(purchase_item_quantity)-Integer.parseInt(issued);
				String avaialable=Integer.toString(avail);
				
				pstmt1.setString(1, purchase_item_quantity);	
				pstmt1.setString(2, avaialable);
				pstmt1.setString(3, issued);
				pstmt1.setString(4,form.getItem_id());


				System.out.println(pstmt1);

				rs1 = pstmt1.executeUpdate();
				status = "true1";
				
			} else {
				status = "false1";

			}
		}

				}
				
				
				
	 catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Ending");
		return status;

	}

	@Override
	public String deleteInventoryTransaction(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: deleteInventoryTransaction : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no=0;

		String status = null;
		Connection conn = null;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.DELETE_INVENTORY_TRANSACTION_LIST);
			pstmt.setString(1, id);

			System.out.println(pstmt);
			
			no = pstmt.executeUpdate();
			
			System.out.println(no);
		

			if(no>0)
			{
				status="true";
			}
			else
			{
				status="false";
			}
			
			
			

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: deleteInventoryTransaction : Ending");
		return status;
	}

	@Override
	public List<AddorModifyorDeleteVO> singleItemDetails(String id) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_SINGLE_TRANSACTION);
			pstmt.setString(1, id);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			AddorModifyorDeleteVO vo = new AddorModifyorDeleteVO();
			while (rs.next()) {
				
				
				
				vo.setPurchase_item_id(rs.getString("item_id"));
				vo.setItem_type(rs.getString("item_type"));
				vo.setPurchase_item_name(rs.getString("item_name"));
				vo.setRequested_by(rs.getString("requested_by"));
				vo.setIssued_by(rs.getString("issued_by"));
				vo.setIssued_to(rs.getString("issued_to"));
				
				vo.setIssued_date(HelperClass.convertDatabaseToUI(rs.getString("issued_date")));
				vo.setReturned_date(HelperClass.convertDatabaseToUI(rs.getString("returned_date")));
				vo.setStatus(rs.getString("status"));
				vo.setIssued_quantity(rs.getString("issued_quantity"));
				
				
				list.add(vo);

			}
		

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return list;

	
	}

	@Override
	public List<AddorModifyorDeleteVO> returnInventoryItem(String id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<AddorModifyorDeleteVO> list = new ArrayList<AddorModifyorDeleteVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.RETURN_DETAILS_PAGE);
			pstmt.setString(1, id);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			AddorModifyorDeleteVO vo = new AddorModifyorDeleteVO();
			while (rs.next()) {
				
				
				
				vo.setPurchase_item_id(rs.getString("item_id"));
				vo.setItem_type(rs.getString("item_type"));
				vo.setPurchase_item_name(rs.getString("item_name"));
				vo.setRequested_by(rs.getString("requested_by"));
				vo.setIssued_by(rs.getString("issued_by"));
				vo.setIssued_to(rs.getString("issued_to"));
				
				vo.setIssued_date(HelperClass.convertDatabaseToUI(rs.getString("issued_date")));
				vo.setReturned_date(HelperClass.convertDatabaseToUI(rs.getString("returned_date")));
				vo.setStatus(rs.getString("remarks"));
				vo.setStatus(rs.getString("status"));
				vo.setIssued_quantity(rs.getString("issued_quantity"));
				
				
				list.add(vo);

			}
		

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return list;

	
	}


	public String updateReturnItem(InventoryTransactionForm form, String tid) {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Starting");
		PreparedStatement pstmt = null;
		int rs = 0;
		PreparedStatement pstmt1 = null;
		int rs1 = 0;
		String id =null;
		String status = null;
		Connection conn = null;
		Timestamp createdDate = HelperClass.getCurrentTimestamp();

		try {
			
			
			
			conn = JDBCConnection.getSeparateConnection();
					pstmt = conn.prepareStatement(SQLUtilConstants.RETURN_UPDATE_INVENTORY_TRANSACTION_DETAILS);

					
					pstmt.setString(1, form.getItem_id());
					pstmt.setString(2, form.getItem_type().trim());
					pstmt.setString(3, form.getItem_name().trim());
					pstmt.setString(4, form.getRequested_by());
					pstmt.setString(5, form.getIssued_to());
					pstmt.setString(6, form.getIssued_by());
					pstmt.setString(7, HelperClass.convertUIToDatabase(form.getIssued_date()));
					pstmt.setString(8, HelperClass.convertUIToDatabase(form.getReturned_date()));
					pstmt.setString(9, form.getStatus());
					pstmt.setString(10, form.getReturned_by());
					pstmt.setString(11, form.getIssued_quantity());
					//pstmt.setTimestamp(12, createdDate);
					pstmt.setString(12, tid);
					

					System.out.println("---------->> "+pstmt);
					rs = pstmt.executeUpdate();
			
			

			if (rs >= 1 ) {
				String purchase_item_quantity=null;
				PreparedStatement itemCount=conn.prepareStatement("SELECT SUM(total_Quantity) total from campus_inventory_purchase_details where purchase_item_id=?");
				itemCount.setString(1,form.getItem_id());
				ResultSet rsItemCount=itemCount.executeQuery();
				if(rsItemCount.next()){
					purchase_item_quantity=rsItemCount.getString("total");
				}
				
				String issued=null;
				PreparedStatement counts=conn.prepareStatement("SELECT  issued_quantity from campus_inventory_availability_items where item_id=? ");
				counts.setString(1,form.getItem_id());
				ResultSet rscounts=counts.executeQuery();
				while(rscounts.next()){
					issued=rscounts.getString("issued_quantity");
				}
				pstmt1 = conn.prepareStatement(SQLUtilConstants.RETURN_UPDATE_INVENTORY_ADD_OR_MODIFY_DELETE_Total_quantity);
				
				int avail=Integer.parseInt(purchase_item_quantity)-Integer.parseInt(issued)+Integer.parseInt(form.getIssued_quantity());
				String avaialable=Integer.toString(avail);
				int isue=Integer.parseInt(issued)-Integer.parseInt(form.getIssued_quantity());
				issued=Integer.toString(isue);
				
				pstmt1.setString(1, issued);	
				pstmt1.setString(2, avaialable);
				pstmt1.setString(3,form.getItem_id());


				System.out.println(pstmt1);

				rs1 = pstmt1.executeUpdate();
				status = "true1";
				
			} else {
				status = "false1";

			}
			
// for delay time//
			
			
			if (rs >= 1 ) {
				String purchase_item_quantity=null;
				PreparedStatement itemCount=conn.prepareStatement("SELECT  issued_date, returned_date from campus_inventory_transaction_details where item_id=? ");
				itemCount.setString(1,form.getItem_id());
				ResultSet rsItemCount=itemCount.executeQuery();
				if(rsItemCount.next()){
					purchase_item_quantity=rsItemCount.getString("total");
				}
				
				String issued=null;
				PreparedStatement counts=conn.prepareStatement("SELECT  issued_quantity from campus_inventory_availability_items where item_id=? ");
				counts.setString(1,form.getItem_id());
				ResultSet rscounts=counts.executeQuery();
				while(rscounts.next()){
					issued=rscounts.getString("issued_quantity");
				}
				pstmt1 = conn.prepareStatement(SQLUtilConstants.RETURN_UPDATE_INVENTORY_ADD_OR_MODIFY_DELETE_Total_quantity);
				
				int avail=Integer.parseInt(purchase_item_quantity)-Integer.parseInt(issued)+Integer.parseInt(form.getIssued_quantity());
				String avaialable=Integer.toString(avail);
				int isue=Integer.parseInt(issued)-Integer.parseInt(form.getIssued_quantity());
				issued=Integer.toString(isue);
				
				pstmt1.setString(1, issued);	
				pstmt1.setString(2, avaialable);
				pstmt1.setString(3,form.getItem_id());


				System.out.println(pstmt1);

				rs1 = pstmt1.executeUpdate();
				status = "true1";
				
			} else {
				status = "false1";

			}

		}
				
				
				
	 catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: AddInventoryType : Ending");
		return status;

	
		
	}

	@Override
	public String getAvailableQuantity(String id, String issued) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String status=null;
		
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			String total=null;
			PreparedStatement counts=conn.prepareStatement("SELECT  available_quantity from campus_inventory_availability_items where item_id=?");
			counts.setString(1,id);
			ResultSet rscounts=counts.executeQuery();
			System.out.println(counts);
			while(rscounts.next()){
				total=rscounts.getString("available_quantity");
				
			}
			int avail=Integer.parseInt(total)-Integer.parseInt(issued);
			
			if(avail >0){
				
				status="true";
				
			}
			else {
				status = "false";
				

			}
			

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return status;

	
	}

	@Override
	public List<InventoryTransactionVO> usageReportList(InventoryTransactionForm invenTranForm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: usageReportList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<InventoryTransactionVO> list = new ArrayList<InventoryTransactionVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.USAGE_REPORT_LIST);
			pstmt.setString(1, invenTranForm.getItem_type());
			pstmt.setString(2, HelperClass.convertUIToDatabase(invenTranForm.getStartdate()));
			pstmt.setString(3, HelperClass.convertUIToDatabase(invenTranForm.getTodate()));
			pstmt.setString(4, invenTranForm.getDepartment());
			
			System.out.println("DIOMPL tiem type : "+invenTranForm.getItem_type());
			System.out.println("DIOMPL getDepartment  : "+ invenTranForm.getDepartment());
			
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			
			int sno=0;
			while (rs.next()) {
				InventoryTransactionVO vo = new InventoryTransactionVO();
				sno++;
				vo.setSno(sno);
				vo.setItem_type(rs.getString("ITEM_TYPE_NAME"));
				vo.setItem_name(rs.getString("purchase_item_name"));
				vo.setRequested_by(rs.getString("requested_by"));
				vo.setIssued_by(rs.getString("issued_by"));
				vo.setIssued_to(rs.getString("issued_to"));
				vo.setIssued_date(HelperClass.convertDatabaseToUI(rs.getString("issued_date")));
				vo.setReturned_date(HelperClass.convertDatabaseToUI(rs.getString("returned_date")));
				vo.setIssued_quantity(rs.getString("issued_quantity"));
				vo.setDepartment(rs.getString("DEPT_NAME"));
				vo.setStatus(rs.getString("status"));
				vo.setReturned_by(rs.getString("returned_by"));
				
				list.add(vo);

			}
		

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: usageReportList : Ending");
		return list;

	
	
	}

	@Override
	public List<InventoryVO> getItemtypeByDepartmnet(String department) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<InventoryVO> list = new ArrayList<InventoryVO>();
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_ITEM_ID_BY_DEPARTMENT);

			pstmt.setString(1, department);
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				InventoryVO vo = new InventoryVO();
				vo.setItem_type_id(rs.getString("Item_TYPE_ID"));
				vo.setItem_type_name(rs.getString("ITEM_TYPE_NAME"));
				
				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return list;
	}

	@Override
	public List<InventoryTransactionVO> getNotReturnedReportAction(InventoryTransactionForm invenTranForm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: usageReportList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<InventoryTransactionVO> list = new ArrayList<InventoryTransactionVO>();

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.NOT_RETURNED_REPORT_LIST);
			pstmt.setString(1, invenTranForm.getItem_type());
			pstmt.setString(2, HelperClass.convertUIToDatabase(invenTranForm.getStartdate()));
			pstmt.setString(3, HelperClass.convertUIToDatabase(invenTranForm.getTodate()));
			pstmt.setString(4, invenTranForm.getDepartment());
			
			System.out.println("DIOMPL tiem type : "+invenTranForm.getItem_type());
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			int sno=0;
			while (rs.next()) {
				InventoryTransactionVO vo = new InventoryTransactionVO();
				sno++;
				vo.setSno(sno);
				vo.setItem_type(rs.getString("ITEM_TYPE_NAME"));
				vo.setItem_name(rs.getString("purchase_item_name"));
				vo.setRequested_by(rs.getString("requested_by"));
				vo.setIssued_by(rs.getString("issued_by"));
				vo.setIssued_to(rs.getString("issued_to"));
				vo.setIssued_date(HelperClass.convertDatabaseToUI(rs.getString("issued_date")));
				vo.setReturned_date(HelperClass.convertDatabaseToUI(rs.getString("returned_date")));
				vo.setDepartment(rs.getString("DEPT_NAME"));
				vo.setStatus(rs.getString("status"));
				vo.setReturned_by(rs.getString("returned_by"));
				List<String> dateSize=HelperClass.getDateListBetweenDates(rs.getString("returned_date"),(HelperClass.getCurrentSqlDate()).toString());
				int delayedDate=dateSize.size()-1;
				String delayedstatus="";
				if(delayedDate >0){
					delayedstatus="Delayed "+delayedDate+ " Days";
					
					list.add(vo);
					
				}
				
				System.out.println(delayedstatus);
			
				
				
				
				
				

			}
		

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: usageReportList : Ending");
		return list;
	}

	@Override
	public List<InventoryTransactionVO> getItemnameByItemtype(String item_type) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<InventoryTransactionVO> list = new ArrayList<InventoryTransactionVO>();
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SQLUtilConstants.ITEM_NAME_BY_ITEM_TYPE);

			pstmt.setString(1, item_type);
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				InventoryTransactionVO vo = new InventoryTransactionVO();
				vo.setItem_id(rs.getString("purchase_item_id"));

				vo.setItem_name(rs.getString("purchase_item_name"));
				System.out.println("DIOMPL: purchase Item Name: "+rs.getString("purchase_item_name"));
				
				list.add(vo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in Inventory_DAOIMPL: InventoryTypesList : Ending");
		return list;
	}
	}
	
	



