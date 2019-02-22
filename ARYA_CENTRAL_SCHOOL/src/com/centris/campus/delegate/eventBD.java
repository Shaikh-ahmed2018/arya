package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.Set;

import com.centris.campus.pojo.eventRegPojo;
import com.centris.campus.service.ReportsMenuService;
import com.centris.campus.serviceImpl.ReportsMenuServiceImpl;
import com.centris.campus.serviceImpl.eventsServiceIMPL;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.eventRegVo;

public class eventBD  {

	public String SaveEventRegistration(eventRegPojo pojo) throws Exception {
		return new eventsServiceIMPL().SaveEventRegistration( pojo);
	}

	public ArrayList<eventRegVo> getEventRegistrationList(eventRegPojo pojo) throws Exception {
		return new eventsServiceIMPL().getEventRegistrationList(pojo);
	}

	public String deleteEventRegistration(String id) {
		return new eventsServiceIMPL().deleteEventRegistration(id);
	}

	public ArrayList<eventRegVo> getDataForUpdate(String id) {
		return new eventsServiceIMPL().getDataForUpdate(id);
	}

	public ArrayList<eventRegVo> getClassList() throws Exception {
		return new eventsServiceIMPL().getClassList();
	}

	public ArrayList<eventRegVo> getEventName() {
		return new eventsServiceIMPL().getEventName();
	}

	public String saveCategory(eventRegPojo pojo) {
		return new eventsServiceIMPL().saveCategory(pojo);
	}

	public ArrayList<eventRegVo> getPrizeCategoryName(String id) {
		return new eventsServiceIMPL().getPrizeCategoryName(id);
	}

	public ArrayList<eventRegVo> getCategorySettingList(String eventId,String catId) {
		return new eventsServiceIMPL().getCategorySettingList(eventId,catId);
	}

	public String deleteCategory(String id) {
	return new eventsServiceIMPL().deleteCategory(id);
	}

	public ArrayList<eventRegVo> getDataForUpdateCategorySetting(String id) {
		return new eventsServiceIMPL().getDataForUpdateCategorySetting(id);
	}

	public String saveStageSetting(eventRegPojo pojo) {
		return new eventsServiceIMPL().saveStageSetting(pojo);
	}

	public ArrayList<eventRegVo> getstageList(String id) {
		return new eventsServiceIMPL().getstageList(id);
	}

	public ArrayList<eventRegVo> getstageSettingList(String eventId, String stageId) {
		return new eventsServiceIMPL().getstageSettingList( eventId,  stageId);
	}

	public String deleteStage(String id) {
		return new eventsServiceIMPL().deleteStage(id);
	}

	public ArrayList<eventRegVo> getDataForUpdateStage(String id) {
		return new eventsServiceIMPL().getDataForUpdateStage(id);
	}


	public String saveCriteriaSetting(eventRegPojo pojo) {
		return new eventsServiceIMPL().saveCriteriaSetting(pojo);
	}

	public ArrayList<eventRegVo> getCriteriaSettingList(String eventId,
			String programmeList, String categoryList) {
		return new eventsServiceIMPL().getCriteriaSettingList(eventId,programmeList,categoryList);
	}

	public ArrayList<eventRegVo> getCategory() {
		return new eventsServiceIMPL().getCategory();
	}

	public String deleteCriteria(String id) {
		return new eventsServiceIMPL().deleteCriteria(id);
	}

	public ArrayList<eventRegVo> getDataForUpdateCriteria(String id) {
		return new eventsServiceIMPL().getDataForUpdateCriteria(id);
	}

	public ArrayList<eventRegVo> getProgramName() {
		return new eventsServiceIMPL().getProgramName();
	}

	public ArrayList<eventRegVo> getCategoryList(String id) {
		return new eventsServiceIMPL().getCategoryList(id);
	}

	public ArrayList<eventRegVo> getProgrammeName(String id,String event_id) {
		return new eventsServiceIMPL().getProgrammeName(id,event_id);
	}

	public ArrayList<eventRegVo> getCriteriaList(String id,String event_id,String cat_id) {
		return new eventsServiceIMPL().getCriteriaList(id,event_id,cat_id);
		}

	public String validateCriteria(String eventName,
			String programmeName, String category, String criteria) {
		return new eventsServiceIMPL().validateCriteria(eventName,programmeName,category,criteria);
	}



	public String saveGreenRoom(eventRegPojo pojo) {
		return new eventsServiceIMPL().saveGreenRoom(pojo);
	}

	public ArrayList<eventRegVo> getGreenRoom(String id) {
		return new eventsServiceIMPL().getGreenRoom(id);
	}

	public ArrayList<eventRegVo> getGreenRoomList(String eventId,String greenRoomId) {
		return new eventsServiceIMPL().getGreenRoomList(eventId,greenRoomId);
	}

	public String deleteGreenRoom(String id) {
		return new eventsServiceIMPL().deleteGreenRoom(id);
	}

	public ArrayList<eventRegVo> getDataForUpdateGreenRoom(String id) {
		return new eventsServiceIMPL().getDataForUpdateGreenRoom(id);
	}

	public String saveProgram(eventRegPojo pojo, String judgeList) {
		return new eventsServiceIMPL().saveProgram(pojo,judgeList);
	}

/*	public ArrayList<eventRegVo> getprogramName(String evId, String catId, String group) {
		return new eventsServiceIMPL().getprogramName(evId,catId,group);
	}*/

	public ArrayList<eventRegVo> getProgramSettingList(String evId,String catId, String progId) {
		return new eventsServiceIMPL().getProgramSettingList(evId,catId,progId);
	}

	public String deleteProgram(String id) {
	return new eventsServiceIMPL().deleteProgram(id);
	}

	public ArrayList<eventRegVo> getDataforUpdateProgram(String id) {
		return new eventsServiceIMPL().getDataforUpdateProgram(id);
	}

	public ArrayList<eventRegVo> getHouse() {
		return new eventsServiceIMPL().getHouse();
	}

	public String saveEventStudentReg(eventRegPojo pojo) {
		return new eventsServiceIMPL().saveEventStudentReg(pojo);
	}

	public ArrayList<eventRegVo> getStudentRegList(eventRegPojo pojo) {
		return new eventsServiceIMPL().getStudentRegList(pojo);
	}

	public String savePrizelevelSetting(eventRegPojo pojo) {
		return new eventsServiceIMPL().savePrizelevelSetting(pojo);
	}

	public ArrayList<eventRegVo> getPrizeSettingList(String eventId,
			String programmeList, String categoryList) {
		return new eventsServiceIMPL().getPrizeSettingList(eventId,programmeList,categoryList);
	}

	public ArrayList<eventRegVo> getDataForUpdatePrize(String id) {
		return new eventsServiceIMPL().getDataForUpdatePrize(id);
	}

	public String deletePrize(String id) {
		return new eventsServiceIMPL().deletePrize(id);
	}

	public ArrayList<eventRegVo> getPrizeList(String id, String event_id,
			String cat_id) {
		return new eventsServiceIMPL().getPrizeList(id,event_id,cat_id);
	}

	public ArrayList<eventRegVo> getSeqList(String id, String event_id,
			String cat_id, String prog_id) {
		return new eventsServiceIMPL().getSeqList(id,event_id,cat_id,prog_id);
	}

	public ArrayList<eventRegVo> getPointsList(String id, String event_id,
			String cat_id, String prog_id, String prize_id) {
		return new eventsServiceIMPL().getPointsList(id,event_id,cat_id,prog_id,prize_id);
	}

	public String validateSeq(String eventName, String programmeName,
			String category, String seqNo, String prizelevel, String points) {
		return new eventsServiceIMPL().validateSeq(eventName,programmeName,category,seqNo,prizelevel,points);
	}

	public String validatePrize(String eventName, String programmeName,
			String category, String seqNo, String prizelevel, String points) {
		return new eventsServiceIMPL().validatePrize(eventName,programmeName,category,seqNo,prizelevel,points);
	}

	public ArrayList<eventRegVo> getHouseNameListGroup(String evId, String catId, String progId, String flag) {
		return new eventsServiceIMPL().getHouseNameListGroup(evId,catId,progId,flag);
	}

	public ArrayList<eventRegVo> getEventStudentRegList(String evId, String  progId,String catId,String houseId, String accId) {
		return new eventsServiceIMPL().getEventStudentRegList(evId,progId,catId,houseId,accId);
	}

	public ArrayList<eventRegVo> getDataForUpdateEventStdRegis(String id) {
		return new eventsServiceIMPL().getDataForUpdateEventStdRegis(id); 
	}

	public String deleteEventStudReg(String id) {
		return new eventsServiceIMPL().deleteEventStudReg(id);
	}

	public ArrayList<eventRegVo> getProgramList(String id) {
		return new eventsServiceIMPL().getProgramList(id);
	}

	public String saveProgramScheduling(eventRegPojo pojo) {
		return new eventsServiceIMPL().saveProgramScheduling(pojo);
	}

	public ArrayList<eventRegVo> getProgramSchedulingList(String eventId, String prog_date) {
		return new eventsServiceIMPL().getProgramSchedulingList(eventId,prog_date);
	}

	public ArrayList<eventRegVo> getDataForUpdateScheduling(String id) {
		return new eventsServiceIMPL().getDataForUpdateScheduling(id);
	}

	public String deleteProgramScheduling(String id) {
		return new eventsServiceIMPL().deleteProgramScheduling(id);
	}

	public String validateScheduling(String eventName, String programmeList,
			String fromDate, String stageNameList, String greenRoomList) {
		return new eventsServiceIMPL().validateScheduling(eventName,programmeList,fromDate,stageNameList,greenRoomList);
	}
	
	public ArrayList<eventRegVo> geteventList() {
		return new eventsServiceIMPL().geteventList();
	}
	
	public ArrayList<eventRegVo> eventCategory(String eid) {
		return new eventsServiceIMPL().eventCategory(eid);
	}
	
	public ArrayList<eventRegVo> eventProgramName(String cid) {
		return new eventsServiceIMPL().eventProgramName(cid);
	}
	
	public ArrayList<eventRegVo> getEventIdList(eventRegPojo filterpojo) throws Exception {
		return new eventsServiceIMPL().getEventIdList(filterpojo);
	}
	
	public ArrayList<eventRegVo> getStage(String event_id) {
		return new eventsServiceIMPL().getStage(event_id);
	}

	public ArrayList<eventRegVo> getParticipants(String event, String stage) {
		return new eventsServiceIMPL().getParticipants(event,stage);
	}

	public String saveStageParticipantMapping(eventRegPojo pojo) {
		return new eventsServiceIMPL().saveStageParticipantMapping(pojo);
	}

	public ArrayList<eventRegVo> getMappedParticipants(String stageVal) {
		return new eventsServiceIMPL().getMappedParticipants(stageVal);
	}
	
	public ArrayList<eventRegVo> getEventIdListToPrint(eventRegPojo filterpojo) throws Exception {
		return new eventsServiceIMPL().getEventIdListToPrint(filterpojo);
	}

	public ArrayList<eventRegVo> getEventIdPrintList(String evId,String catId, String regNo,String accyear) {
		return new eventsServiceIMPL().getEventIdPrintList(evId,catId,regNo,accyear);
	}
	
	/*public ArrayList<eventRegVo> getEventIdPrintList(String evId, String regNo,String accyear) {
		return new eventsServiceIMPL().getEventIdPrintList(evId,regNo,accyear);
	}*/

	public ArrayList<eventRegVo> getProgramNumberingDetails(String event, String stage) {
		return new eventsServiceIMPL().getProgramNumberingDetails(event,stage);
	}

	

	public ArrayList<eventRegVo> getDataforUpdateChestNoGeneration(String id) {
		return new eventsServiceIMPL().getDataforUpdateChestNoGeneration(id);
	}



	public String saveProgramNumberingDetails(eventRegPojo pojo) {
		return new eventsServiceIMPL().saveProgramNumberingDetails(pojo);
	}

	public ArrayList<eventRegVo> getAllocatedParticipants(String event,
			String stage) {
		return new eventsServiceIMPL().getAllocatedParticipants(event,stage);
	}

	public ArrayList<eventRegVo> getMappedProgramNumberingDetails(String event,
			String stage) {
		return new eventsServiceIMPL().getMappedProgramNumberingDetails(event,stage);
	}



	public ArrayList<eventRegVo> getcategorylist(String event_id) {
		return new eventsServiceIMPL().getcategorylist(event_id);
	}

	public ArrayList<eventRegVo> getstagelist(String event_id, String flagVal) {
		return new eventsServiceIMPL().getstagelist(event_id,flagVal);
	}

	public ArrayList<eventRegVo> getparticipanteventList(String event_name) {
		return new eventsServiceIMPL().getparticipanteventList(event_name);
	}

	public String deleteProgramNumber(String[] id) {
		return new eventsServiceIMPL().deleteProgramNumber(id);
	}

	public String udpateIndividualSave(eventRegPojo pojo) {
		return new eventsServiceIMPL().udpateIndividualSave(pojo);
	}

	public ArrayList<eventRegVo> getEventNameList_Group(String val) {
		return new eventsServiceIMPL().getEventNameList_Group(val);
	}

	public ArrayList<eventRegVo> getEventNameStudentReg() {
		return new eventsServiceIMPL().getEventNameStudentReg();
	}


	public ArrayList<eventRegVo> getParticipantList_Group(String evId,
			String catId, String progId) {
		return new eventsServiceIMPL().getParticipantList_Group(evId,catId,progId);
	}

	public ArrayList<eventRegVo> getStageNameListGroup(String evId,String progId) {
		return new eventsServiceIMPL().getStageNameListGroup(evId,progId);
	}

	public ArrayList<eventRegVo> getProgrammeDate(String event_id,
			String programId, String stageId) {
		return new eventsServiceIMPL().getProgrammeDate(event_id,programId,stageId);
		}

	public ArrayList<eventRegVo> getTableByData(String event_id, String catId,
			String progId, String houseId, String accId) {
		return new eventsServiceIMPL().getTableByData(event_id,catId,progId,houseId,accId);
		}

	public ArrayList<eventRegVo> getTableByHouse(String event_id, String catId,
			String progId, String houseId, String accId, String stageId) {
		return new eventsServiceIMPL().getTableByHouse(event_id,catId,progId,houseId,accId,stageId);
		}

	public ArrayList<eventRegVo> getHouseWise(String event_id, String catId,
			String progId) {
		return new eventsServiceIMPL().getHouseWise(event_id,catId,progId);
		}

	public eventRegVo getEventDetail(String event_id, String catId,
			String progId, String houseId) {
		return new eventsServiceIMPL().getEventDetail(event_id,catId,progId,houseId);
		}
	
	public ArrayList<eventRegVo> getStudents(String event_id, String catId,String progId,String AcyYear, String certificateon) {
		return new eventsServiceIMPL().getStudents(event_id,catId,progId,AcyYear,certificateon);
		}

	public ArrayList<eventRegVo> getCertificateDetail(String event_id,
			String catId, String progId, String accId) {
		return new eventsServiceIMPL().getCertificateDetail(event_id,catId,progId,accId);
		}
	public ArrayList<eventRegVo> getProgramScheduledList(String eventId,
			String prog_date) {
		return new eventsServiceIMPL().getProgramScheduledList(eventId,prog_date);
	}


	public String saveChestNoRegister(eventRegPojo pojo) {
		return new eventsServiceIMPL().saveChestNoRegister(pojo);
	}


	public ArrayList<eventRegVo> getChestNoList(String evId, String catId, String progId) {
		return new eventsServiceIMPL().getChestNoList(evId,catId,progId);
	}

	public String saveGeneratedchestNo(eventRegPojo pojo) {
		return new eventsServiceIMPL().saveGeneratedchestNo(pojo);
	}

	public Set<eventRegVo> getEventStudentRegChestNoList(String evId,String catId,String progId,String accyear) {
		return new eventsServiceIMPL().getEventStudentRegChestNoList(evId,catId,progId,accyear);
	}

	public ArrayList<eventRegVo> getEventNameList() {
		return new eventsServiceIMPL().getEventNameList();
	}

	public ArrayList<eventRegVo> getVolunteerList(String eveId, String locId) {
		return new eventsServiceIMPL().getVolunteerList(eveId,locId);
	}

	public ArrayList<eventRegVo> getDataforUpdateVolanteer(String id) {
		return new eventsServiceIMPL().getDataforUpdateVolanteer(id);
	}

	public String savevolunteer(eventRegPojo pojo) {
		return new eventsServiceIMPL().savevolunteer(pojo);
	}
	public ArrayList<eventRegVo> getEventName(String id) {
		return new eventsServiceIMPL().getEventName(id);
	}

	public String deletevolanteer(String id) {
		return new eventsServiceIMPL().deletevolanteer(id);
	}
	public ArrayList<eventRegVo> getProgramNumberingList(String event,
			String stage) {
		return new eventsServiceIMPL().getProgramNumberingList(event,stage);
	}

	public ArrayList<eventRegVo> getStageAllocatedParticipants(String event,
			String stageVal) {
		return new eventsServiceIMPL().getStageAllocatedParticipants(event,stageVal);
	}
	public ArrayList<eventRegVo> getCategoryName(String id, String group) {
		return new eventsServiceIMPL().getCategoryName(id,group);
	}

	public ArrayList<eventRegVo> getCriteriaForJudgementSheet(String event_id,
			String progId, String catId) {
		return new eventsServiceIMPL().getCriteriaForJudgementSheet(event_id,progId,catId);
		}


	public ArrayList<eventRegVo> getEventStudentMeriList(String evId,
			String progId, String catId, String houseId, String accId) {
		return new eventsServiceIMPL().getEventStudentMeriList(evId,progId,catId,houseId,accId);
	}

	public ArrayList<eventRegVo> getEventStudentMeritListByOnlyEvent(
			String evId, String progId, String catId, String houseId,
			String accId, int place) {
		return new eventsServiceIMPL().getEventStudentMeritListByOnlyEvent(evId,progId,catId,houseId,accId,place);
		}

	public ArrayList<eventRegVo> getEventStudentMeritListByProgramme(
			String evId, String progId, String catId, String houseId,
			String accId, String resultType) {
		return new eventsServiceIMPL().getEventStudentMeritListByProgramme(evId,progId,catId,houseId,accId,resultType);
		}

	public ArrayList<eventRegVo> EventResultPrint(String evId, String progId,
			String catId, String houseId, String accId, int place) {
		return new eventsServiceIMPL().EventResultPrint(evId,progId,catId,houseId,accId,place);
		}

	public ArrayList<eventRegVo> getEventStudentOverallResult(String evId) {
		return new eventsServiceIMPL().getEventStudentOverallResult(evId);
		}

	public eventRegVo getPartEventDetail(String event_id, String catId,
			String progId, String houseId, String stageId) {
		return new eventsServiceIMPL().getPartEventDetail(event_id,catId,progId,houseId,stageId);
		}

	public ArrayList<eventRegVo> getProgramNameByCategory(String eventid, String categoryid) {
		return new eventsServiceIMPL().getProgramNameByCategory(eventid,categoryid);
	}

	public ArrayList<eventRegVo> getCategoryNameByEvent(String id) {
		return new eventsServiceIMPL().getCategoryNameByEvent(id);
	}


	public ArrayList<eventRegVo> printChestNo(eventRegVo obj) {
		return new eventsServiceIMPL().printChestNo(obj);
	}

	public eventRegVo getEventCertificateDetail(String event_id, String catId, String progId, String accId) {
		return new eventsServiceIMPL().getEventCertificateDetail(event_id,catId,progId,accId);
		}

}
