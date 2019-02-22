package com.centris.campus.delegate;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.centris.campus.forms.InventoryForm;
import com.centris.campus.forms.InventoryTransactionForm;
import com.centris.campus.service.Inventory_Service;
import com.centris.campus.serviceImpl.Inventory_ServiceIMPL;
import com.centris.campus.vo.AddorModifyorDeleteVO;
import com.centris.campus.vo.InventoryTransactionVO;
import com.centris.campus.vo.InventoryVO;

public class Inventory_Delegate {
	
	Inventory_Service service = new Inventory_ServiceIMPL();

	public List<InventoryVO> InventoryTypesList() {
		// TODO Auto-generated method stub
		return service.InventoryTypesList();
	}

	public List<InventoryVO> searchInventoryTypeList(String searchName) {
		// TODO Auto-generated method stub
		return service.searchInventoryTypeList(searchName);
	}

	public String AddInventoryType(InventoryForm form) {
		// TODO Auto-generated method stub
		return service.AddInventoryType(form);
	}

	public InventoryVO EditInventoryType(InventoryVO vo) {
		// TODO Auto-generated method stub
		return service.EditInventoryType(vo);
	}

	public String DeleteInventoryType(InventoryVO vo) {
		// TODO Auto-generated method stub
		return service.DeleteInventoryType(vo);
	}

	public List<AddorModifyorDeleteVO> AddorModifyorDeleteList() {
		// TODO Auto-generated method stub
		return service.AddorModifyorDeleteList();
	}
	
//Add or Modify or Delete

	public String CreatingAddorModifyorDelete(InventoryForm form) {
		// TODO Auto-generated method stub
		return service.CreatingAddorModifyorDelete(form);
	}

	public AddorModifyorDeleteVO EditAddorModifyorDelete(AddorModifyorDeleteVO vo) {
		// TODO Auto-generated method stub
		return service.EditAddorModifyorDelete(vo);
	}

	public String DeleteAddorModifyorDelete(AddorModifyorDeleteVO vo) {
		// TODO Auto-generated method stub
		return service.DeleteAddorModifyorDelete(vo);
	}

	public List<AddorModifyorDeleteVO> SearchAddorModifyorDeleteList(
			String searchName) {
		// TODO Auto-generated method stub
		return service.SearchAddorModifyorDeleteList(searchName);
	}

	public List<AddorModifyorDeleteVO> InventoryList() {
		// TODO Auto-generated method stub
		return service.InventoryList();
	}

	public List<AddorModifyorDeleteVO> SearchInventoryList(String searchName) {
		// TODO Auto-generated method stub
		return service.SearchInventoryList(searchName);
	}

	public List<InventoryTransactionVO> InventoryTransactionList() {
		// TODO Auto-generated method stub
		return service.InventoryTransactionList();	}

	
	public String CreateTransactionDetails(InventoryTransactionForm form, String tid) {
		return service.CreateTransactionDetails(form,tid);
	}

	
	public String deleteInventoryTransaction(String id){
		return service.deleteInventoryTransaction(id);
	}

	public List<AddorModifyorDeleteVO> singleItemDetails(String id) {
		return service.singleItemDetails(id);
	}

	public List<AddorModifyorDeleteVO> returnInventoryItem(String id) {
		return service.returnInventoryItem(id);
	}

	public String updateReturnItem(InventoryTransactionForm form, String tid) {
		return service.updateReturnItem(form,tid);
	}

	

	public String getAvailableQuantity(String id,String issued ) {
		return service.getAvailableQuantity(id, issued);
	}

	public List<InventoryTransactionVO> usageReportList(InventoryTransactionForm invenTranForm) {
		return service.usageReportList(invenTranForm);
	}

	public List<InventoryVO> getItemtypeByDepartmnet(String department) {
		return service.getItemtypeByDepartmnet(department);
	}

	public List<InventoryTransactionVO> getNotReturnedReportAction(InventoryTransactionForm invenTranForm) {
		return service.getNotReturnedReportAction(invenTranForm);
	}

	public List<InventoryTransactionVO> getItemnameByItemtype(String item_type) {
		return service.getItemnameByItemtype(item_type);
	}

		
}
