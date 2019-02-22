package com.centris.campus.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class eventRegVo {
	
private int judgeNos;
	private String teacherId;
	private String teacherName;
	private String scoredmarks;
	private String place;
	private int count;
	private String classSec;
	private int sno;
	private String locationIdHidden;
	private String eventName;
	private String eventId;
	private String startsOn;
	private String endsOn;
	private String eventType;
	private String startsRegis;
	private String endsRegis;
	private String status;
	private String ishouseWise;
	private String isAprovPps;
	private String description;
	private String accyear;
	private String location;
	private String locId;
	private String studentId;
	private String classId;
	private String className;
	private String rollNumber;
	private String categoryId;
	private String categoryName;
	private String classList[];
	private String tempList[];
	private String stageName;
	private String stageId;
	private String registrationNo;
	private List<eventRegVo> eventRgoList;
	private String building;
	private String floorName;
	private String roomNo;
	private String greenRoomName;
	private String greenRoomType;
	private String greenRoomId;
	private String registrationId;
	private String participateType;
	
	private String progName;
	private String partiType;
	private String termsAllowed;
	private String PartiNo;
	private String duration;
	private String progType;
	private String infoGeneral;
	private String progDate;
	private String infoStaff;
	private String NoOfChildHouse;
	private String isHouse;
	private String progId;
	private String houseId;
	private String houseName;
	private String studentName;
	private String admissionNo;
	private String ParticipantsAdmisNos[];
	private String imageUrl;
	private String infoSynopsis;
	private String infoReq;
	private String captainFullName;
	
	private String captainAdmnNo;
	private String partiAdmnNo;
	private String captainHighlight;
	
	private String status1;
	private String programNumberId;
	private String criteriaData[];
	private int criterialength;
	private double totNumber;
	private double criteriaMarks;
	
	private String startFrom;
	private String endTo;
	
	

	public List<eventRegVo> getEventRgoList() {
		return eventRgoList;
	}
	public void setEventRgoList(List<eventRegVo> eventRgoList) {
		this.eventRgoList = eventRgoList;
	}
	public double getCriteriaMarks() {
		return criteriaMarks;
	}
	public void setCriteriaMarks(double criteriaMarks) {
		this.criteriaMarks = criteriaMarks;
	}
	public double getTotNumber() {
		return totNumber;
	}
	public void setTotNumber(double totNumber) {
		this.totNumber = totNumber;
	}
	public String getStartFrom() {
		return startFrom;
	}
	public void setStartFrom(String startFrom) {
		this.startFrom = startFrom;
	}
	public String getEndTo() {
		return endTo;
	}
	public void setEndTo(String endTo) {
		this.endTo = endTo;
	}
	public String getScoredmarks() {
		return scoredmarks;
	}
	public void setScoredmarks(String scoredmarks) {
		this.scoredmarks = scoredmarks;
	}
	public int getCriterialength() {
		return criterialength;
	}
	public void setCriterialength(int criterialength) {
		this.criterialength = criterialength;
	}

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getClassSec() {
		return classSec;
	}
	public void setClassSec(String classSec) {
		this.classSec = classSec;
	}

	public String getProgramNumberId() {
		return programNumberId;
	}
	public void setProgramNumberId(String programNumberId) {
		this.programNumberId = programNumberId;
	}
	public String getStatus1() {
		return status1;
	}
	public void setStatus1(String status1) {
		this.status1 = status1;
	}
	public String getSetParticipantsAdmisNos() {
		return setParticipantsAdmisNos;
	}
	public void setSetParticipantsAdmisNos(String setParticipantsAdmisNos) {
		this.setParticipantsAdmisNos = setParticipantsAdmisNos;
	}
	public String getCaptainHighlight() {
		return captainHighlight;
	}
	public void setCaptainHighlight(String captainHighlight) {
		this.captainHighlight = captainHighlight;
	}
	public String getCaptainFullName() {
		return captainFullName;
	}
	public void setCaptainFullName(String captainFullName) {
		this.captainFullName = captainFullName;
	}
	private String programNumber;
	
	public String getProgramNumber() {
		return programNumber;
	}
	public void setProgramNumber(String programNumber) {
		this.programNumber = programNumber;
	}
	public String getCaptainAdmnNo() {
		return captainAdmnNo;
	}
	public void setCaptainAdmnNo(String captainAdmnNo) {
		this.captainAdmnNo = captainAdmnNo;
	}
	public String getPartiAdmnNo() {
		return partiAdmnNo;
	}
	public void setPartiAdmnNo(String partiAdmnNo) {
		this.partiAdmnNo = partiAdmnNo;
	}


	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	private ArrayList<eventRegVo> participantsList;
	
	public ArrayList<eventRegVo> getParticipantsList() {
		return participantsList;
	}
	public void setParticipantsList(ArrayList<eventRegVo> participantsList) {
		this.participantsList = participantsList;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	
private String JudgeNames;
	private String volanteerId;
	private String volanteerAdmissionNo;
	private String volanteerName;
	private String startTime;
	private String endTime;
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	private String chestNumber;
	private String prizeLevel;
	private String points;
	private String prize_id;
	
	private String scheduling_id;
	private String prog_date;
	private String makeup;
	private String backStage;
	private String prog_time;
	private String participantId;
	private String participantName;
	
	private String chestNoId;
	private String admissionno;
	
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getProg_time() {
		return prog_time;
	}
	public void setProg_time(String prog_time) {
		this.prog_time = prog_time;
	}

	public String getMakeup() {
		return makeup;
	}
	public void setMakeup(String makeup) {
		this.makeup = makeup;
	}
	public String getBackStage() {
		return backStage;
	}
	public void setBackStage(String backStage) {
		this.backStage = backStage;
	}
	public String getProg_date() {
		return prog_date;
	}
	public void setProg_date(String prog_date) {
		this.prog_date = prog_date;
	}
	public String getScheduling_id() {
		return scheduling_id;
	}
	public void setScheduling_id(String scheduling_id) {
		this.scheduling_id = scheduling_id;
	}
	public String getPrize_id() {
		return prize_id;
	}
	public void setPrize_id(String prize_id) {
		this.prize_id = prize_id;
	}



	

	
	public String getPrizeLevel() {
		return prizeLevel;
	}
	public void setPrizeLevel(String prizeLevel) {
		this.prizeLevel = prizeLevel;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getProgName() {
		return progName;
	}
	public void setProgName(String progName) {
		this.progName = progName;
	}
	public String getPartiType() {
		return partiType;
	}
	public void setPartiType(String partiType) {
		this.partiType = partiType;
	}
	public String getTermsAllowed() {
		return termsAllowed;
	}
	public void setTermsAllowed(String termsAllowed) {
		this.termsAllowed = termsAllowed;
	}
	public String getPartiNo() {
		return PartiNo;
	}
	public void setPartiNo(String partiNo) {
		PartiNo = partiNo;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getProgType() {
		return progType;
	}
	public void setProgType(String progType) {
		this.progType = progType;
	}
	public String getInfoGeneral() {
		return infoGeneral;
	}
	public void setInfoGeneral(String infoGeneral) {
		this.infoGeneral = infoGeneral;
	}
	public String getProgDate() {
		return progDate;
	}
	public void setProgDate(String progDate) {
		this.progDate = progDate;
	}
	public String getInfoStaff() {
		return infoStaff;
	}
	public void setInfoStaff(String infoStaff) {
		this.infoStaff = infoStaff;
	}
	public String getNoOfChildHouse() {
		return NoOfChildHouse;
	}
	public void setNoOfChildHouse(String noOfChildHouse) {
		NoOfChildHouse = noOfChildHouse;
	}
	public String getIsHouse() {
		return isHouse;
	}
	public void setIsHouse(String isHouse) {
		this.isHouse = isHouse;
	}
	public String getProgId() {
		return progId;
	}
	public void setProgId(String progId) {
		this.progId = progId;
	}

	
	public String getGreenRoomId() {
		return greenRoomId;
	}
	public void setGreenRoomId(String greenRoomId) {
		this.greenRoomId = greenRoomId;
	}
	public String getGreenRoomName() {
		return greenRoomName;
	}
	public void setGreenRoomName(String greenRoomName) {
		this.greenRoomName = greenRoomName;
	}
	public String getGreenRoomType() {
		return greenRoomType;
	}
	public void setGreenRoomType(String greenRoomType) {
		this.greenRoomType = greenRoomType;
	}

	
	private String programmeName;
	private String category;
	private String criteria;
	private String maxMarks;
	
	private String seqNo;
	
	private String criteriaId;
	private String hiddenCriteriaId;
	
	private String programmeId;
	private String criteriaArray[];
	private String judgeList[];
	
	
	public String[] getJudgeList() {
		return judgeList;
	}
	public void setJudgeList(String[] judgeList) {
		this.judgeList = judgeList;
	}
	public String getProgrammeId() {
		return programmeId;
	}
	public void setProgrammeId(String programmeId) {
		this.programmeId = programmeId;
	}
	
	public String getHiddenCriteriaId() {
		return hiddenCriteriaId;
	}
	public void setHiddenCriteriaId(String hiddenCriteriaId) {
		this.hiddenCriteriaId = hiddenCriteriaId;
	}
	
	
	public String getCriteriaId() {
		return criteriaId;
	}
	public void setCriteriaId(String criteriaId) {
		this.criteriaId = criteriaId;
	}
	
	
	public String getProgrammeName() {
		return programmeName;
	}
	public void setProgrammeName(String programmeName) {
		this.programmeName = programmeName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public String getMaxMarks() {
		return maxMarks;
	}
	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getStageId() {
		return stageId;
	}
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}

	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	
	
	
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	private String accId;
	public String setParticipantsAdmisNos;
	
	
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getStartsOn() {
		return startsOn;
	}
	public void setStartsOn(String startsOn) {
		this.startsOn = startsOn;
	}
	public String getEndsOn() {
		return endsOn;
	}
	public void setEndsOn(String endsOn) {
		this.endsOn = endsOn;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getStartsRegis() {
		return startsRegis;
	}
	public void setStartsRegis(String startsRegis) {
		this.startsRegis = startsRegis;
	}
	public String getEndsRegis() {
		return endsRegis;
	}
	public void setEndsRegis(String endsRegis) {
		this.endsRegis = endsRegis;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIshouseWise() {
		return ishouseWise;
	}
	public void setIshouseWise(String ishouseWise) {
		this.ishouseWise = ishouseWise;
	}
	public String getIsAprovPps() {
		return isAprovPps;
	}
	public void setIsAprovPps(String isAprovPps) {
		this.isAprovPps = isAprovPps;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String[] getClassList() {
		return classList;
	}
	public void setClassList(String classList[]) {
		this.classList = classList;
	}
	public String getVolanteerId() {
		return volanteerId;
	}
	public void setVolanteerId(String volanteerId) {
		this.volanteerId = volanteerId;
	}
	public String getVolanteerAdmissionNo() {
		return volanteerAdmissionNo;
	}
	public void setVolanteerAdmissionNo(String volanteerAdmissionNo) {
		this.volanteerAdmissionNo = volanteerAdmissionNo;
	}
	public String getVolanteerName() {
		return volanteerName;
	}
	public void setVolanteerName(String volanteerName) {
		this.volanteerName = volanteerName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getChestNoId() {
		return chestNoId;
	}
	public void setChestNoId(String chestNoId) {
		this.chestNoId = chestNoId;
	}
	public String getAdmissionno() {
		return admissionno;
	}
	public void setAdmissionno(String admissionno) {
		this.admissionno = admissionno;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public String[] getParticipantsAdmisNos() {
		return ParticipantsAdmisNos;
	}
	public void setParticipantsAdmisNos(String participantsAdmisNos[]) {
		ParticipantsAdmisNos = participantsAdmisNos;
	}
	public String[] getTempList() {
		return tempList;
	}
	public void setTempList(String tempList[]) {
		this.tempList = tempList;
	}
	public String getInfoReq() {
		return infoReq;
	}
	public void setInfoReq(String infoReq) {
		this.infoReq = infoReq;
	}
	public String getInfoSynopsis() {
		return infoSynopsis;
	}
	public void setInfoSynopsis(String infoSynopsis) {
		this.infoSynopsis = infoSynopsis;
	}
	public String getParticipateType() {
		return participateType;
	}
	public void setParticipateType(String participateType) {
		this.participateType = participateType;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getChestNumber() {
		return chestNumber;
	}
	public void setChestNumber(String chestNumber) {
		this.chestNumber = chestNumber;
	}
	public String[] getCriteriaArray() {
		return criteriaArray;
	}
	public void setCriteriaArray(String criteriaArray[]) {
		this.criteriaArray = criteriaArray;
	}
	public String[] getCriteriaData() {
		return criteriaData;
	}
	public void setCriteriaData(String criteriaData[]) {
		this.criteriaData = criteriaData;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getJudgeNames() {
		return JudgeNames;
	}
	public void setJudgeNames(String judgeNames) {
		JudgeNames = judgeNames;
	}
	public int getJudgeNos() {
		return judgeNos;
	}
	public void setJudgeNos(int judgeNos) {
		this.judgeNos = judgeNos;
	}
	public String getLocationIdHidden() {
		return locationIdHidden;
	}
	public void setLocationIdHidden(String locationIdHidden) {
		this.locationIdHidden = locationIdHidden;
	}
	
}
