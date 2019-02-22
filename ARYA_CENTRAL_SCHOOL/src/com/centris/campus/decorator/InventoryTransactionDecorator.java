package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.AddorModifyorDeleteVO;
import com.centris.campus.vo.InventoryTransactionVO;
import com.centris.campus.vo.InventoryVO;

public class InventoryTransactionDecorator extends TableDecorator {
	
	
	public String getInventoryCheckBox() {
		
		
		
		InventoryVO vo=(InventoryVO)getCurrentRowObject();
			
			String desc=vo.getItem_type_id();
			if(desc==null){
				desc="";
			}
			
			 String inventoryCheckBox = "<input name=\"check_box\" class=\"InventoryTypesList_Checkbox_Class\" type=\"checkbox\" id=\"itemtype_idCheckBox_"
					+ vo.getItem_type_id()
					+ ","
					+ vo.getItem_type_name()
					+ ","
					+ desc + "\"/>";

			
			
			return inventoryCheckBox;
		
	}
	
	
		public String getAdd_modify_delete_checkbox() {
		
		
		
			AddorModifyorDeleteVO vo=(AddorModifyorDeleteVO)getCurrentRowObject();
			
			String desc=vo.getPurchase_id();
			
			if(desc==null){
				desc="";
			}
			
			 String inventoryCheckBox = "<input class=\"list_Checkbox_Class\" type=\"checkbox\" id=\"purchase_item_type_idCheckBox_"
					+ vo.getPurchase_id()
					+ ","
					+ vo.getPurchase_item_name()
					+ ","
					+ desc + "\"/>";

			
			
			return inventoryCheckBox;
		
	}
	
		public String getTransactionCheckBox() {
			
			
			
			InventoryTransactionVO vo=(InventoryTransactionVO)getCurrentRowObject();
				
				String desc=vo.getTransaction_id();
				if(desc==null){
					desc="";
				}
				
				 String transactionCheckBox = "<input name=\"check_box\" class=\"TransactionDetailsList_Checkbox_Class\" type=\"checkbox\" id=\"transaction_idCheckBox_"
						+ vo.getTransaction_id()
						+ vo.getDelayed_status()
						+ "\"/>";

				
				 
				 
				return transactionCheckBox;
			
		}
		
		
		
		
public String getStatus() {
			
			
			
			InventoryTransactionVO vo=(InventoryTransactionVO)getCurrentRowObject();
				
				String status1=vo.getStatus();
				
				
				 String status = status1+  " / " +vo.getDelayed_status();

				
				 
				 
				return status;
			
		}
	

}
