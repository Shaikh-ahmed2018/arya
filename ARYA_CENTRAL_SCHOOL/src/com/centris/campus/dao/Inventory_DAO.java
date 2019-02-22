package com.centris.campus.dao;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.centris.campus.forms.InventoryForm;
import com.centris.campus.forms.InventoryTransactionForm;
import com.centris.campus.vo.AddorModifyorDeleteVO;
import com.centris.campus.vo.InventoryTransactionVO;
import com.centris.campus.vo.InventoryVO;

public interface Inventory_DAO {

	List<InventoryVO> InventoryTypesList();

	List<InventoryVO> searchInventoryTypeList(String searchName);

	String AddInventoryType(InventoryForm form);

	InventoryVO EditInventoryType(InventoryVO vo);

	String DeleteInventoryType(InventoryVO vo);

	List<AddorModifyorDeleteVO> AddorModifyorDeleteList();
	//Add or Modify or Delete
	String CreatingAddorModifyorDelete(InventoryForm form);

	AddorModifyorDeleteVO EditAddorModifyorDelete(AddorModifyorDeleteVO vo);

	String DeleteAddorModifyorDelete(AddorModifyorDeleteVO vo);

	List<AddorModifyorDeleteVO> SearchAddorModifyorDeleteList(String searchName);

	List<AddorModifyorDeleteVO> InventoryList();

	List<AddorModifyorDeleteVO> SearchInventoryList(String searchName);

	List<InventoryTransactionVO> InventoryTransactionList();

	

	String CreateTransactionDetails(InventoryTransactionForm form, String tid);

	String deleteInventoryTransaction(String id);

	List<AddorModifyorDeleteVO> singleItemDetails(String id);

	List<AddorModifyorDeleteVO> returnInventoryItem(String id);

	String updateReturnItem(InventoryTransactionForm form, String tid);

	String getAvailableQuantity(String id, String issued); 

	List<InventoryTransactionVO> usageReportList(InventoryTransactionForm invenTranForm);

	List<InventoryVO> getItemtypeByDepartmnet(String department);

	List<InventoryTransactionVO> getNotReturnedReportAction(InventoryTransactionForm invenTranForm);

	List<InventoryTransactionVO> getItemnameByItemtype(String item_type);



	
}
