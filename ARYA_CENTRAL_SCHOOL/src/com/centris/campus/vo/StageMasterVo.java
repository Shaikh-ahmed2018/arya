/**
 * 
 */
package com.centris.campus.vo;

/**
 * @author satish
 *
 */
public class StageMasterVo {
private String stageName;
private String amount;
private String description;
private String stageCode;
private String userCode;
private String accyearCode;
private String term1_amount;
private String term2_amount;
//private int slNo;


/*public int getSlNo() {
	return slNo;
}
public void setSlNo(int slNo) {
	this.slNo = slNo;
}*/
public String getTerm1_amount() {
	return term1_amount;
}
public void setTerm1_amount(String term1_amount) {
	this.term1_amount = term1_amount;
}
public String getTerm2_amount() {
	return term2_amount;
}
public void setTerm2_amount(String term2_amount) {
	this.term2_amount = term2_amount;
}
public String getAccyearCode() {
	return accyearCode;
}
public void setAccyearCode(String accyearCode) {
	this.accyearCode = accyearCode;
}
public String getStageName() {
	return stageName;
}
public void setStageName(String stageName) {
	this.stageName = stageName;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getStageCode() {
	return stageCode;
}
public void setStageCode(String stageCode) {
	this.stageCode = stageCode;
}
public String getUserCode() {
	return userCode;
}
public void setUserCode(String userCode) {
	this.userCode = userCode;
}


}
