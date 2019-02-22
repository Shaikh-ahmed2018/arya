package com.centris.campus.serviceImpl;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.centris.campus.dao.Inventory_DAO;
import com.centris.campus.daoImpl.Inventory_DAOIMPL;
import com.centris.campus.forms.InventoryForm;
import com.centris.campus.forms.InventoryTransactionForm;
import com.centris.campus.service.Inventory_Service;
import com.centris.campus.vo.AddorModifyorDeleteVO;
import com.centris.campus.vo.InventoryTransactionVO;
import com.centris.campus.vo.InventoryVO;

public class Inventory_ServiceIMPL implements Inventory_Service {
	
	Inventory_DAO dao = new Inventory_DAOIMPL();


	public List<InventoryVO> InventoryTypesList() {

		return dao.InventoryTypesList();
	}


	public List<InventoryVO> searchInventoryTypeList(String searchName) {

		return dao.searchInventoryTypeList(searchName);
	}


	public String AddInventoryType(InventoryForm form) {
		

		return dao.AddInventoryType(form);
	}


	public InventoryVO EditInventoryType(InventoryVO vo) {

		return dao.EditInventoryType(vo);
	}


	public String DeleteInventoryType(InventoryVO vo) {

		return dao.DeleteInventoryType(vo);
	}

	//Add or Modify or Delete
	public List<AddorModifyorDeleteVO> AddorModifyorDeleteList() {

		return dao.AddorModifyorDeleteList();
	}

	public String CreatingAddorModifyorDelete(InventoryForm form) {

		return dao.CreatingAddorModifyorDelete(form);
	}


	public AddorModifyorDeleteVO EditAddorModifyorDelete(AddorModifyorDeleteVO vo) {

		return dao.EditAddorModifyorDelete(vo);
	}


	public String DeleteAddorModifyorDelete(AddorModifyorDeleteVO vo) {

		return dao.DeleteAddorModifyorDelete(vo);
	}


	public List<AddorModifyorDeleteVO> SearchAddorModifyorDeleteList(
			String searchName) {

		return dao.SearchAddorModifyorDeleteList(searchName);
	}


	@Override
	public List<AddorModifyorDeleteVO> InventoryList() {

		return dao.InventoryList();
	}


	@Override
	public List<AddorModifyorDeleteVO> SearchInventoryList(String searchName) {
		// TODO Auto-generated method stub
		return dao.SearchInventoryList(searchName);
	}


	@Override
	public List<InventoryTransactionVO> InventoryTransactionList() {
		// TODO Auto-generated method stub
		return dao.InventoryTransactionList();
	}	
	
	
	
	public String CreateTransactionDetails(InventoryTransactionForm form ,String tid) {

		return dao.CreateTransactionDetails(form,tid);
	}


	@Override
	public String deleteInventoryTransaction(String id) {
		return dao.deleteInventoryTransaction(id);
	}
	
	public List<AddorModifyorDeleteVO> singleItemDetails(String id) {
		return dao.singleItemDetails(id);
	}
	
	public List<AddorModifyorDeleteVO> returnInventoryItem(String id) {
		return dao.returnInventoryItem(id);
	}
	
	public String updateReturnItem(InventoryTransactionForm form ,String tid) {

		return dao.updateReturnItem(form,tid);
	}
	public String getAvailableQuantity(String id, String issued) {
		return dao.getAvailableQuantity(id,issued);
	}
	
	public List<InventoryTransactionVO> usageReportList(InventoryTransactionForm invenTranForm) {
		return dao.usageReportList(invenTranForm);
	}
	public List<InventoryVO> getItemtypeByDepartmnet(String department) {
		return dao.getItemtypeByDepartmnet(department);
	}
	
	public List<InventoryTransactionVO> getNotReturnedReportAction(InventoryTransactionForm invenTranForm) {
		return dao.getNotReturnedReportAction(invenTranForm);
	}
	public List<InventoryTransactionVO> getItemnameByItemtype(String item_type) {
		return dao.getItemnameByItemtype(item_type);
	}
	
	
}
