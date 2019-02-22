package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Set;

import com.centris.campus.daoImpl.EventsDaoImpl;
import com.centris.campus.pojo.eventRegPojo;
import com.centris.campus.vo.eventRegVo;

public class eventsServiceIMPL {
	
	EventsDaoImpl<eventRegVo> eventdaoimpl = new EventsDaoImpl<eventRegVo>();

	public String SaveEventRegistration(eventRegPojo pojo) throws Exception {
	return eventdaoimpl.SaveEventRegistration(pojo);
	}

	public ArrayList<eventRegVo> getEventRegistrationList(eventRegPojo pojo) throws Exception {
	return eventdaoimpl.getEventRegistrationList(pojo);
	}

	public String deleteEventRegistration(String id) {
		return eventdaoimpl.deleteEventRegistration(id);
	}

	public ArrayList<eventRegVo> getDataForUpdate(String id) {
		return eventdaoimpl.getDataForUpdate(id);
	}

	public ArrayList<eventRegVo> getClassList() throws Exception {
		return eventdaoimpl.getClassList();
	}

	public ArrayList<eventRegVo> getEventName() {
		return eventdaoimpl.getEventName();
	}

	public String saveCategory(eventRegPojo pojo) {
		return eventdaoimpl.saveCategory(pojo);
	}

	public ArrayList<eventRegVo> getPrizeCategoryName(String id) {
		return eventdaoimpl.getPrizeCategoryName(id);
	}

	public ArrayList<eventRegVo> getCategorySettingList(String eventId,String catId) {
		return eventdaoimpl.getCategorySettingList(eventId,catId);
	}

	public String deleteCategory(String id) {
		return eventdaoimpl.deleteCategory(id);
	}

	public ArrayList<eventRegVo> getDataForUpdateCategorySetting(String id) {
		return eventdaoimpl.getDataForUpdateCategorySetting(id);
	}

	public String saveStageSetting(eventRegPojo pojo) {
		return eventdaoimpl.saveStageSetting(pojo);
	}

	public ArrayList<eventRegVo> getstageList(String id) {
		return eventdaoimpl.getstageList(id);
	}

	public ArrayList<eventRegVo> getstageSettingList(String eventId, String stageId) {
		return eventdaoimpl.getstageSettingList( eventId,  stageId);
	}

	public String deleteStage(String id) {
		return eventdaoimpl.deleteStage(id);
	}

	public ArrayList<eventRegVo> getDataForUpdateStage(String id) {
		return eventdaoimpl.getDataForUpdateStage(id);
	}


	public String saveCriteriaSetting(eventRegPojo pojo) {
		return eventdaoimpl.saveCriteriaSetting(pojo);
	}

	public ArrayList<eventRegVo> getCriteriaSettingList(String eventId,String programmeList, String categoryList) {
		return eventdaoimpl.getCriteriaSettingList(eventId,programmeList,categoryList);
	}

	public ArrayList<eventRegVo> getCategory() {
		return eventdaoimpl.getCategory();
	}

	public String deleteCriteria(String id) {
		return eventdaoimpl.deleteCriteria(id);
	}

	public ArrayList<eventRegVo> getDataForUpdateCriteria(String id) {
		return eventdaoimpl.getDataForUpdateCriteria(id);
	}

	public ArrayList<eventRegVo> getProgramName() {
		return eventdaoimpl.getProgramName();
	}

	public ArrayList<eventRegVo> getCategoryList(String id) {
		return eventdaoimpl.getcategorylist(id);
	}

	public ArrayList<eventRegVo> getProgrammeName(String id,String event_id) {
		return eventdaoimpl.getProgrammeName(id,event_id);
	}

	public ArrayList<eventRegVo> getCriteriaList(String id,String event_id,String cat_id) {
		return eventdaoimpl.getCriteriaList(id,event_id,cat_id);
	}

	public String validateCriteria(String eventName,
			String programmeName, String category, String criteria) {
		return eventdaoimpl.validateCriteria(eventName,programmeName,category,criteria);
	}




	public String saveGreenRoom(eventRegPojo pojo) {
		return eventdaoimpl.saveGreenRoom(pojo);
	}

	public ArrayList<eventRegVo> getGreenRoom(String id) {
	return eventdaoimpl.getGreenRoom(id);
	}

	public ArrayList<eventRegVo> getGreenRoomList(String eventId,String greenRoomId) {
		return eventdaoimpl.getGreenRoomList(eventId,greenRoomId);
	}

	public String deleteGreenRoom(String id) {
	return eventdaoimpl.deleteGreenRoom(id);
	}

	public ArrayList<eventRegVo> getDataForUpdateGreenRoom(String id) {
		return eventdaoimpl.getDataForUpdateGreenRoom(id);
	}

	public String saveProgram(eventRegPojo pojo, String judgeList) {
		return new EventsDaoImpl().saveProgram(pojo,judgeList);
	}

	public ArrayList<eventRegVo> getProgramSettingList(String evId,String catId, String progId) {
		return eventdaoimpl.getProgramSettingList(evId,catId,progId);
	}

	public String deleteProgram(String id) {
		return eventdaoimpl.deleteProgram(id);
	}

	public ArrayList<eventRegVo> getDataforUpdateProgram(String id) {
		return eventdaoimpl.getDataforUpdateProgram(id);
	}

	public ArrayList<eventRegVo> getHouse() {
		return eventdaoimpl.getHouse();
	}

	public String saveEventStudentReg(eventRegPojo pojo) {
		return eventdaoimpl.saveEventStudentReg(pojo);
	}

	public ArrayList<eventRegVo> getStudentRegList(eventRegPojo pojo) {
		return eventdaoimpl.getStudentRegList(pojo);
	}

	public String savePrizelevelSetting(eventRegPojo pojo) {
		return eventdaoimpl.savePrizelevelSetting(pojo);
	}

	public ArrayList<eventRegVo> getPrizeSettingList(String eventId,
			String programmeList, String categoryList) {
		return eventdaoimpl.getPrizeSettingList(eventId,programmeList,categoryList);
	}

	public ArrayList<eventRegVo> getDataForUpdatePrize(String id) {
		return eventdaoimpl.getDataForUpdatePrize(id);
	}

	public String deletePrize(String id) {
		return eventdaoimpl.deletePrize(id);
	}

	public ArrayList<eventRegVo> getPrizeList(String id, String event_id,
			String cat_id) {
		return eventdaoimpl.getPrizeList(id,event_id,cat_id);
	}

	public ArrayList<eventRegVo> getSeqList(String id, String event_id,
			String cat_id, String prog_id) {
		return eventdaoimpl.getSeqList(id,event_id,cat_id,prog_id);
	}

	public ArrayList<eventRegVo> getPointsList(String id, String event_id,
			String cat_id, String prog_id, String prize_id) {
		return eventdaoimpl.getPointsList(id,event_id,cat_id,prog_id,prize_id);
	}

	public String validateSeq(String eventName, String programmeName,
			String category, String seqNo, String prizelevel, String points) {
		return eventdaoimpl.validateSeq(eventName,programmeName,category,seqNo,prizelevel,points);
	}

	public String validatePrize(String eventName, String programmeName,
			String category, String seqNo, String prizelevel, String points) {
		return eventdaoimpl.validatePrize(eventName,programmeName,category,seqNo,prizelevel,points);
	}

	public ArrayList<eventRegVo> getHouseNameListGroup(String evId, String catId, String progId, String flag) {
		return eventdaoimpl.getHouseNameListGroup(evId,catId,progId,flag);
	}

	public ArrayList<eventRegVo> getEventStudentRegList(String evId,String progId, String catId, String houseId, String accId) {
		return eventdaoimpl.getEventStudentRegList(evId,progId,catId,houseId,accId);
	}

	public ArrayList<eventRegVo> getDataForUpdateEventStdRegis(String id) {
		return eventdaoimpl.getDataForUpdateEventStdRegis(id);
		
	}

	public String deleteEventStudReg(String id) {
		return eventdaoimpl.deleteEventStudReg(id);
	}

	public ArrayList<eventRegVo> getProgramList(String id) {
		return eventdaoimpl.getProgramList(id);
	}

	public String saveProgramScheduling(eventRegPojo pojo) {
		return eventdaoimpl.saveProgramScheduling(pojo);
	}

	public ArrayList<eventRegVo> getProgramSchedulingList(String eventId, String prog_date) {
		return eventdaoimpl.getProgramSchedulingList(eventId,prog_date);
	}

	public ArrayList<eventRegVo> getDataForUpdateScheduling(String id) {
		return eventdaoimpl.getDataForUpdateScheduling(id);
	}

	public String deleteProgramScheduling(String id) {
		return eventdaoimpl.deleteProgramScheduling(id);
	}

	public String validateScheduling(String eventName, String programmeList,
			String fromDate, String stageNameList, String greenRoomList) {
		return eventdaoimpl.validateScheduling(eventName,programmeList,fromDate,stageNameList,greenRoomList);
	}
	public ArrayList<eventRegVo> getStage(String event_id) {
		return eventdaoimpl.getStage(event_id);
	}

	public ArrayList<eventRegVo> getParticipants(String event, String stage) {
		return eventdaoimpl.getParticipants(event,stage);
	}

	public String saveStageParticipantMapping(eventRegPojo pojo) {
		return eventdaoimpl.saveStageParticipantMapping(pojo);
	}

	
	public ArrayList<eventRegVo> geteventList() {
		return eventdaoimpl.geteventList();
	}

	public ArrayList<eventRegVo> eventCategory(String eid) {
		return eventdaoimpl.eventCategory(eid);
	}
	
	public ArrayList<eventRegVo> eventProgramName(String cid) {
		return eventdaoimpl.eventProgramName(cid);
	}
	
	public ArrayList<eventRegVo> getEventIdList(eventRegPojo filterpojo) {
		return eventdaoimpl.getEventIdList(filterpojo);
	}

	public ArrayList<eventRegVo> getMappedParticipants(String stageVal) {
		return eventdaoimpl.getMappedParticipants(stageVal);
	}
	public ArrayList<eventRegVo> getProgramNumberingDetails(String event, String stage) {
		return eventdaoimpl.getProgramNumberingDetails(event,stage);
	}

	
	public ArrayList<eventRegVo> getEventIdListToPrint(eventRegPojo filterpojo) {
		return eventdaoimpl.getEventIdListToPrint(filterpojo);
	}

	public ArrayList<eventRegVo> getEventIdPrintList(String evId,String catId, String regNo,String accyear) {
		return eventdaoimpl.getEventIdPrintList(evId, catId,regNo);
	}


	public String saveProgramNumberingDetails(eventRegPojo pojo) {
		return eventdaoimpl.saveProgramNumberingDetails(pojo);
	}

	public ArrayList<eventRegVo> getAllocatedParticipants(String event,
			String stage) {
		return eventdaoimpl.getAllocatedParticipants(event,stage);
	}

	public ArrayList<eventRegVo> getMappedProgramNumberingDetails(String event,
			String stage) {
		return eventdaoimpl.getMappedProgramNumberingDetails(event,stage);
	}
	
	public ArrayList<eventRegVo> getDataforUpdateChestNoGeneration(String id) {
		return eventdaoimpl.getDataforUpdateChestNoGeneration(id);
	}

	public String deleteProgramNumber(String[] id) {
		return eventdaoimpl.deleteProgramNumber(id);
	}

	public String udpateIndividualSave(eventRegPojo pojo) {
		return eventdaoimpl.udpateIndividualSave(pojo);
	}

	public ArrayList<eventRegVo> getEventNameList_Group(String val) {
		return eventdaoimpl.getEventNameList_Group(val);
	}

	public ArrayList<eventRegVo> getEventNameStudentReg() {
		return eventdaoimpl.getEventNameStudentReg();
	}


	public ArrayList<eventRegVo> getcategorylist(String event_id) {
		return eventdaoimpl.getcategorylist(event_id);
	}

/*	public ArrayList<eventRegVo> getprogramlist(String cat_id, String evId) {
		return eventdaoimpl.getprogramlist(cat_id,evId);
	}*/

	public ArrayList<eventRegVo> getstagelist(String event_id, String flagVal) {
		return eventdaoimpl.getstagelist(event_id,flagVal);
	}

	public ArrayList<eventRegVo> getparticipanteventList(String event_name) {
		return eventdaoimpl.getparticipanteventList(event_name);
	}


	public ArrayList<eventRegVo> getParticipantList_Group(String evId,
			String catId, String progId) {
		return eventdaoimpl.getParticipantList_Group(evId,catId,progId);
	}

	public ArrayList<eventRegVo> getStageNameListGroup(String evId,String progId) {
		return eventdaoimpl.getStageNameListGroup(evId,progId);
	}

	public ArrayList<eventRegVo> getProgrammeDate(String event_id,
			String programId, String stageId) {
		return eventdaoimpl.getProgrammeDate(event_id,programId,stageId);
		}

	public ArrayList<eventRegVo> getTableByData(String event_id, String catId,
			String progId, String houseId, String accId) {
		return eventdaoimpl.getTableByData(event_id,catId,progId,houseId,accId);
		}

	public ArrayList<eventRegVo> getHouseWise(String event_id, String catId,
			String progId) {
		return eventdaoimpl.getHouseWise(event_id,catId,progId);
		}

	public ArrayList<eventRegVo> getTableByHouse(String event_id, String catId,
			String progId, String houseId, String accId, String stageId) {
		return eventdaoimpl.getTableByHouse(event_id,catId,progId,houseId,accId,stageId);
		}

	public eventRegVo getEventDetail(String event_id, String catId,
			String progId,String houseId) {
		return eventdaoimpl.getEventDetail(event_id,catId,progId,houseId);
		}

	public ArrayList<eventRegVo> getStudents(String event_id, String catId, String progId,String acyYear, String certificateon) {
		return eventdaoimpl.getStudents(event_id,catId,progId,acyYear,certificateon);
		}

	public ArrayList<eventRegVo> getCertificateDetail(String event_id,
			String catId, String progId, String accId) {
		return eventdaoimpl.getCertificateDetail(event_id,catId,progId,accId);
		}


	public ArrayList<eventRegVo> getProgramScheduledList(String eventId,
			String prog_date) {
		return eventdaoimpl.getProgramScheduledList(eventId,prog_date);
	}


	public String saveChestNoRegister(eventRegPojo pojo) {
		return eventdaoimpl.saveChestNoRegister(pojo);
	}


	public ArrayList<eventRegVo> getChestNoList(String evId, String catId, String progId) {
		return eventdaoimpl.getChestNoList(evId,catId,progId);
	}

	public String saveGeneratedchestNo(eventRegPojo pojo) {
		return eventdaoimpl.saveGeneratedchestNo(pojo);
	}

	public Set<eventRegVo> getEventStudentRegChestNoList(String evId,String catId,String progId,String accyear) {
		return eventdaoimpl.getEventStudentRegChestNoList(evId,catId,progId,accyear);
	}

	public ArrayList<eventRegVo> getEventNameList() {
		return eventdaoimpl.getEventNameList();
	}

	public ArrayList<eventRegVo> getVolunteerList(String eveId, String locId) {
		return eventdaoimpl.getVolunteerList(eveId,locId);
	}

	public ArrayList<eventRegVo> getDataforUpdateVolanteer(String id) {
		return eventdaoimpl.getDataforUpdateVolanteer(id);
	}

	public String savevolunteer(eventRegPojo pojo) {
		return eventdaoimpl.savevolunteer(pojo);
	}

	public ArrayList<eventRegVo> getEventName(String id) {
		return eventdaoimpl.getEventName(id);
	}

	public String deletevolanteer(String id) {
		return eventdaoimpl.deletevolanteer(id);
	}
	public ArrayList<eventRegVo> getProgramNumberingList(String event,
			String stage) {
		return eventdaoimpl.getProgramNumberingList(event,stage);
	}

	public ArrayList<eventRegVo> getStageAllocatedParticipants(String event,
			String stageVal) {
		return eventdaoimpl.getStageAllocatedParticipants(event,stageVal);
	}
	public ArrayList<eventRegVo> getCategoryName(String id, String group) {
		return eventdaoimpl.getCategoryName(id,group);
	}

	public ArrayList<eventRegVo> getCriteriaForJudgementSheet(String event_id,
			String progId, String catId) {
		return eventdaoimpl.getCriteriaForJudgementSheet(event_id,progId,catId);
		}

	public ArrayList<eventRegVo> getEventStudentMeriList(String evId,
			String progId, String catId, String houseId, String accId) {
		return eventdaoimpl.getEventStudentMeritList(evId,progId,catId,houseId,accId);
	}

	public ArrayList<eventRegVo> getEventStudentMeritListByOnlyEvent(
			String evId, String progId, String catId, String houseId,
			String accId, int place) {
		return eventdaoimpl.getEventStudentMeritListByOnlyEvent(evId,progId,catId,houseId,accId,place);
		}

	public ArrayList<eventRegVo> getEventStudentMeritListByProgramme(
			String evId, String progId, String catId, String houseId,
			String accId, String resultType) {
		return eventdaoimpl.getEventStudentMeritListByProgramme(evId,progId,catId,houseId,accId,resultType);
		}

	public ArrayList<eventRegVo> EventResultPrint(String evId, String progId,
			String catId, String houseId, String accId, int place) {
		return eventdaoimpl.EventResultPrint(evId,progId,catId,houseId,accId,place);
		}

	public ArrayList<eventRegVo> getEventStudentOverallResult(String evId) {
		return eventdaoimpl.getEventStudentOverallResult(evId);
		}

	public eventRegVo getPartEventDetail(String event_id, String catId,
			String progId, String houseId, String stageId) {
		return eventdaoimpl.getPartEventDetail(event_id,catId,progId,houseId,stageId);
		}

	public ArrayList<eventRegVo> getProgramNameByCategory(String eventid, String categoryid) {
		return eventdaoimpl.getProgramNameByCategory(eventid,categoryid);
	}

	public ArrayList<eventRegVo> getCategoryNameByEvent(String id) {
		return eventdaoimpl.getCategoryNameByEvent(id);
	}

	public ArrayList<eventRegVo> printChestNo(eventRegVo obj) {
		return eventdaoimpl.printChestNo(obj);
	}

	public eventRegVo getEventCertificateDetail(String event_id, String catId, String progId, String accId) {
		return eventdaoimpl.getEventCertificateDetail(event_id,catId,progId,accId);
		}
}
