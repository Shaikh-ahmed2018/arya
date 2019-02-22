package com.centris.campus.vo;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ExaminationDetailsVo {


	private int count;

	private int attendence_per;
	private int lab_scored; 
	private int project_scored;
	private int assignment_scored;
	private String other;
	private int outOffG;
	private int maintotal;
	private String depPerce;
	private String dependentExamCode;
	private int depExamScoredMarks;
	private int depExamTotalMarks;
	private String depExamGrade;
	private String examName;
	private String examid;
	private String subjectId;
	private String subjectName;
	private String examinationdate;
	private String startTime;
	private String endTime;
	private String classId;
	private String classname;
	private String accyearId;
	private String accyear;
	private String createdBy;
	private Timestamp createDate;
	private String startDate;
	private String endDate;
	private String section;
	private String category;
	private int sno1;
	private String sno;
	private String description;

	private String examDetailsCheckBox;
	private String studentid;
	private String hiddenid;

	private String maxmarks;
	private String requiredmarks;
	private int accyearcount;
	private String accyearid;
	private String status;
	private int examcount;
	private String classid;
	private int specilazationCount;
	private String examcode;
	private String sectionid;
	private String examCode;
	private String gradeid;
	private String gradename;
	private String max_marks;
	private String min_marks;
	private String comments;
	private String sectionName;
	private String studentrollno;
	private String admissionno;
	private String scoredmarks;
	private String notebooks;
	private String subjectenrichmarks;
	private String locationid;
	private String[] subid;
	private String[] scoremarks;
	private String[] statusvalues;
	private String[] studentids;
	private String[] submarksid;
	private String studenprimid;
	private String[] stuprimaryid;
	private String[] notebookmarks;
	private String[] subjectenrichment;
	private String[] periodictest;
	private String locname;
	private String status1;
	private String islanguage;
	private String timetableid;
	private String examstarttime;
	private String examendtime;


	private String labname;
	private String labcode;
	private String labtotmarks;
	private String labscoredmarks;
	private String labid;


	private String mainexam;
	private String mainexamgrade;
	private int mainexammark;
	private ArrayList<ExaminationDetailsVo> examlist;
	private int indscoredmarks;
	private int indtotaldmarks;
	private int indpassdmarks;
	private int totalDepScoredMarks;
	private String specialization;

	private int totseccount;
	private int setseccount;
	private String setmsg;
	private int count1;
	private int count2;
	private int count3;
	private int count4;
	private int count5;
	private int count6;
	private int count7;
	private int count8;
	private int count9;
	private int mainCount;
	private String mainName;
	private String rank;
	private String pertest;
	private String classcode;
	private String max_notebook_marks;
	private String max_subenrich_marks;
	private String[] max_scored_marks;
	private String[] maxnotebookmarks;
	private String[] maxsubenrichmentmarks;
	private String[] maxperiodicmarks;
	private String[] periodicscoredmarks;
	private String workedu_grade;
	private String artedu_grade;
	private String disciplinedu_grade;
	private String healthedu_grade;
	private String examtypeid;
	private String examtypename;
	private String examtypeprefix;
	private String isapplicableperiodic;
	private String maxperiodicmark;
	
	

	public String getMaxperiodicmark() {
		return maxperiodicmark;
	}

	public void setMaxperiodicmark(String maxperiodicmark) {
		this.maxperiodicmark = maxperiodicmark;
	}

	public String[] getMaxperiodicmarks() {
		return maxperiodicmarks;
	}

	public void setMaxperiodicmarks(String[] maxperiodicmarks) {
		this.maxperiodicmarks = maxperiodicmarks;
	}

	public String[] getPeriodicscoredmarks() {
		return periodicscoredmarks;
	}

	public void setPeriodicscoredmarks(String[] periodicscoredmarks) {
		this.periodicscoredmarks = periodicscoredmarks;
	}

	public String getExamtypeprefix() {
		return examtypeprefix;
	}

	public void setExamtypeprefix(String examtypeprefix) {
		this.examtypeprefix = examtypeprefix;
	}

	public String getIsapplicableperiodic() {
		return isapplicableperiodic;
	}

	public void setIsapplicableperiodic(String isapplicableperiodic) {
		this.isapplicableperiodic = isapplicableperiodic;
	}

	public String getExamtypeid() {
		return examtypeid;
	}

	public void setExamtypeid(String examtypeid) {
		this.examtypeid = examtypeid;
	}

	public String getExamtypename() {
		return examtypename;
	}

	public void setExamtypename(String examtypename) {
		this.examtypename = examtypename;
	}

	public String getWorkedu_grade() {
		return workedu_grade;
	}

	public void setWorkedu_grade(String workedu_grade) {
		this.workedu_grade = workedu_grade;
	}

	public String getArtedu_grade() {
		return artedu_grade;
	}

	public void setArtedu_grade(String artedu_grade) {
		this.artedu_grade = artedu_grade;
	}

	public String getDisciplinedu_grade() {
		return disciplinedu_grade;
	}

	public void setDisciplinedu_grade(String disciplinedu_grade) {
		this.disciplinedu_grade = disciplinedu_grade;
	}

	public String getHealthedu_grade() {
		return healthedu_grade;
	}

	public void setHealthedu_grade(String healthedu_grade) {
		this.healthedu_grade = healthedu_grade;
	}

	public String[] getMax_scored_marks() {
		return max_scored_marks;
	}

	public void setMax_scored_marks(String[] max_scored_marks) {
		this.max_scored_marks = max_scored_marks;
	}

	public String[] getMaxnotebookmarks() {
		return maxnotebookmarks;
	}

	public void setMaxnotebookmarks(String[] maxnotebookmarks) {
		this.maxnotebookmarks = maxnotebookmarks;
	}

	public String[] getMaxsubenrichmentmarks() {
		return maxsubenrichmentmarks;
	}

	public void setMaxsubenrichmentmarks(String[] maxsubenrichmentmarks) {
		this.maxsubenrichmentmarks = maxsubenrichmentmarks;
	}

	public String getMax_notebook_marks() {
		return max_notebook_marks;
	}

	public void setMax_notebook_marks(String max_notebook_marks) {
		this.max_notebook_marks = max_notebook_marks;
	}

	public String getMax_subenrich_marks() {
		return max_subenrich_marks;
	}

	public void setMax_subenrich_marks(String max_subenrich_marks) {
		this.max_subenrich_marks = max_subenrich_marks;
	}

	public String getClasscode() {
		return classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}

	public String getPertest() {
		return pertest;
	}

	public void setPertest(String pertest) {
		this.pertest = pertest;
	}

	public String[] getPeriodictest() {
		return periodictest;
	}

	public void setPeriodictest(String[] periodictest) {
		this.periodictest = periodictest;
	}

	public String getNotebooks() {
		return notebooks;
	}

	public void setNotebooks(String notebooks) {
		this.notebooks = notebooks;
	}

	public String getSubjectenrichmarks() {
		return subjectenrichmarks;
	}

	public void setSubjectenrichmarks(String subjectenrichmarks) {
		this.subjectenrichmarks = subjectenrichmarks;
	}

	public String[] getNotebookmarks() {
		return notebookmarks;
	}

	public void setNotebookmarks(String[] notebookmarks) {
		this.notebookmarks = notebookmarks;
	}

	public String[] getSubjectenrichment() {
		return subjectenrichment;
	}

	public void setSubjectenrichment(String[] subjectenrichment) {
		this.subjectenrichment = subjectenrichment;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getMainName() {
		return mainName;
	}

	public void setMainName(String mainName) {
		this.mainName = mainName;
	}

	public int getMainCount() {
		return mainCount;
	}

	public void setMainCount(int mainCount) {
		this.mainCount = mainCount;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public int getCount3() {
		return count3;
	}

	public void setCount3(int count3) {
		this.count3 = count3;
	}

	public int getCount4() {
		return count4;
	}

	public void setCount4(int count4) {
		this.count4 = count4;
	}

	public int getCount5() {
		return count5;
	}

	public void setCount5(int count5) {
		this.count5 = count5;
	}

	public int getCount6() {
		return count6;
	}

	public void setCount6(int count6) {
		this.count6 = count6;
	}

	public int getCount7() {
		return count7;
	}

	public void setCount7(int count7) {
		this.count7 = count7;
	}

	public int getCount8() {
		return count8;
	}

	public void setCount8(int count8) {
		this.count8 = count8;
	}

	public int getCount9() {
		return count9;
	}

	public void setCount9(int count9) {
		this.count9 = count9;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public int getAttendence_per() {
		return attendence_per;
	}

	public void setAttendence_per(int attendence_per) {
		this.attendence_per = attendence_per;
	}

	public int getLab_scored() {
		return lab_scored;
	}

	public void setLab_scored(int lab_scored) {
		this.lab_scored = lab_scored;
	}

	public int getProject_scored() {
		return project_scored;
	}

	public void setProject_scored(int project_scored) {
		this.project_scored = project_scored;
	}

	public int getAssignment_scored() {
		return assignment_scored;
	}

	public void setAssignment_scored(int assignment_scored) {
		this.assignment_scored = assignment_scored;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}


	public String getSetmsg() {
		return setmsg;
	}

	public void setSetmsg(String setmsg) {
		this.setmsg = setmsg;
	}

	public int getTotseccount() {
		return totseccount;
	}

	public void setTotseccount(int totseccount) {
		this.totseccount = totseccount;
	}

	public int getSetseccount() {
		return setseccount;
	}

	public void setSetseccount(int setseccount) {
		this.setseccount = setseccount;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}


	public int getOutOffG() {
		return outOffG;
	}

	public void setOutOffG(int outOffG) {
		this.outOffG = outOffG;
	}

	public int getTotalDepScoredMarks() {
		return totalDepScoredMarks;
	}

	public void setTotalDepScoredMarks(int totalDepScoredMarks) {
		this.totalDepScoredMarks = totalDepScoredMarks;
	}

	public int getMaintotal() {
		return maintotal;
	}

	public void setMaintotal(int maintotal) {
		this.maintotal = maintotal;
	}

	public int getIndscoredmarks() {
		return indscoredmarks;
	}

	public void setIndscoredmarks(int indscoredmarks) {
		this.indscoredmarks = indscoredmarks;
	}

	public int getIndtotaldmarks() {
		return indtotaldmarks;
	}

	public void setIndtotaldmarks(int indtotaldmarks) {
		this.indtotaldmarks = indtotaldmarks;
	}

	public int getIndpassdmarks() {
		return indpassdmarks;
	}

	public void setIndpassdmarks(int indpassdmarks) {
		this.indpassdmarks = indpassdmarks;
	}

	public int getDepExamScoredMarks() {
		return depExamScoredMarks;
	}

	public void setDepExamScoredMarks(int depExamScoredMarks) {
		this.depExamScoredMarks = depExamScoredMarks;
	}

	public int getDepExamTotalMarks() {
		return depExamTotalMarks;
	}

	public void setDepExamTotalMarks(int depExamTotalMarks) {
		this.depExamTotalMarks = depExamTotalMarks;
	}

	public String getDepExamGrade() {
		return depExamGrade;
	}

	public void setDepExamGrade(String depExamGrade) {
		this.depExamGrade = depExamGrade;
	}

	public ArrayList<ExaminationDetailsVo> getExamlist() {
		return examlist;
	}

	public void setExamlist(ArrayList<ExaminationDetailsVo> examlist) {
		this.examlist = examlist;
	}

	public int getMainexammark() {
		return mainexammark;
	}

	public void setMainexammark(int mainexammark) {
		this.mainexammark = mainexammark;
	}

	public String getDepPerce() {
		return depPerce;
	}

	public String getMainexam() {
		return mainexam;
	}

	public void setMainexam(String mainexam) {
		this.mainexam = mainexam;
	}

	public String getMainexamgrade() {
		return mainexamgrade;
	}

	public void setMainexamgrade(String mainexamgrade) {
		this.mainexamgrade = mainexamgrade;
	}

	public void setDepPerce(String depPerce) {
		this.depPerce = depPerce;
	}

	public String getDependentExamCode() {
		return dependentExamCode;
	}

	public void setDependentExamCode(String dependentExamCode) {
		this.dependentExamCode = dependentExamCode;
	}


	public String getLabname() {
		return labname;
	}
	public void setLabname(String labname) {
		this.labname = labname;
	}
	public String getLabcode() {
		return labcode;
	}
	public void setLabcode(String labcode) {
		this.labcode = labcode;
	}
	public String getLabtotmarks() {
		return labtotmarks;
	}
	public void setLabtotmarks(String labtotmarks) {
		this.labtotmarks = labtotmarks;
	}
	public String getLabscoredmarks() {
		return labscoredmarks;
	}
	public void setLabscoredmarks(String labscoredmarks) {
		this.labscoredmarks = labscoredmarks;
	}
	public String getLabid() {
		return labid;
	}
	public void setLabid(String labid) {
		this.labid = labid;
	}
	public String getExamstarttime() {
		return examstarttime;
	}

	public void setExamstarttime(String examstarttime) {
		this.examstarttime = examstarttime;
	}

	public String getExamendtime() {
		return examendtime;
	}

	public void setExamendtime(String examendtime) {
		this.examendtime = examendtime;
	}

	public String getTimetableid() {
		return timetableid;
	}

	public void setTimetableid(String timetableid) {
		this.timetableid = timetableid;
	}

	public String getIslanguage() {
		return islanguage;
	}

	public void setIslanguage(String islanguage) {
		this.islanguage = islanguage;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getLocname() {
		return locname;
	}

	public void setLocname(String locname) {
		this.locname = locname;
	}

	public String[] getStuprimaryid() {
		return stuprimaryid;
	}

	public void setStuprimaryid(String[] stuprimaryid) {
		this.stuprimaryid = stuprimaryid;
	}

	public String getStudenprimid() {
		return studenprimid;
	}

	public void setStudenprimid(String studenprimid) {
		this.studenprimid = studenprimid;
	}

	private String primaryid;

	public String getPrimaryid() {
		return primaryid;
	}

	public void setPrimaryid(String primaryid) {
		this.primaryid = primaryid;
	}

	public String[] getSubmarksid() {
		return submarksid;
	}

	public void setSubmarksid(String[] submarksid) {
		this.submarksid = submarksid;
	}

	public String[] getStudentids() {
		return studentids;
	}

	public void setStudentids(String[] studentids) {
		this.studentids = studentids;
	}

	public String[] getSubid() {
		return subid;
	}

	public void setSubid(String[] subid) {
		this.subid = subid;
	}

	public String[] getScoremarks() {
		return scoremarks;
	}

	public void setScoremarks(String[] scoremarks) {
		this.scoremarks = scoremarks;
	}

	public String[] getStatusvalues() {
		return statusvalues;
	}

	public void setStatusvalues(String[] statusvalues) {
		this.statusvalues = statusvalues;
	}

	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public String getScoredmarks() {
		return scoredmarks;
	}

	public void setScoredmarks(String scoredmarks) {
		this.scoredmarks = scoredmarks;
	}

	public String getStudentrollno() {
		return studentrollno;
	}

	public void setStudentrollno(String studentrollno) {
		this.studentrollno = studentrollno;
	}

	public String getAdmissionno() {
		return admissionno;
	}

	public void setAdmissionno(String admissionno) {
		this.admissionno = admissionno;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getTot_strength() {
		return tot_strength;
	}

	public void setTot_strength(String tot_strength) {
		this.tot_strength = tot_strength;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getPassmarks() {
		return passmarks;
	}

	public void setPassmarks(String passmarks) {
		this.passmarks = passmarks;
	}

	public String getAttendace() {
		return attendace;
	}

	public void setAttendace(String attendace) {
		this.attendace = attendace;
	}

	public String getSecuredmarks() {
		return securedmarks;
	}

	public void setSecuredmarks(String securedmarks) {
		this.securedmarks = securedmarks;
	}

	private String tot_strength;
	private String subId;
	private String subCode;
	private String tot_marks;
	private String passmarks;
	private String attendace;
	private String securedmarks;
	private String rollno;
	private String studentname;
	private String studId;
	private String addmisiionno;

	private String examstartdate;
	private String examenddate;

	public String getAddmisiionno() {
		return addmisiionno;
	}

	public void setAddmisiionno(String addmisiionno) {
		this.addmisiionno = addmisiionno;
	}

	public String getRollno() {
		return rollno;
	}

	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getStudId() {
		return studId;
	}

	public void setStudId(String studId) {
		this.studId = studId;
	}

	public String getTot_marks() {
		return tot_marks;
	}

	public void setTot_marks(String tot_marks) {
		this.tot_marks = tot_marks;
	}

	public String getExamstartdate() {
		return examstartdate;

	}

	public void setExamstartdate(String examstartdate) {
		this.examstartdate = examstartdate;
	}

	public String getExamenddate() {
		return examenddate;
	}

	public void setExamenddate(String examenddate) {
		this.examenddate = examenddate;
	}

	public String getSectionid() {
		return sectionid;
	}

	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}

	public String getExamcode() {
		return examcode;
	}

	public void setExamcode(String examcode) {
		this.examcode = examcode;
	}

	public int getSpecilazationCount() {
		return specilazationCount;
	}

	public void setSpecilazationCount(int specilazationCount) {
		this.specilazationCount = specilazationCount;
	}

	public int getExamcount() {
		return examcount;
	}

	public void setExamcount(int examcount) {
		this.examcount = examcount;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getGradeid() {
		return gradeid;
	}

	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public String getMax_marks() {
		return max_marks;
	}

	public void setMax_marks(String max_marks) {
		this.max_marks = max_marks;
	}

	public String getMin_marks() {
		return min_marks;
	}

	public void setMin_marks(String min_marks) {
		this.min_marks = min_marks;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAccyearcount() {
		return accyearcount;
	}

	public void setAccyearcount(int accyearcount) {
		this.accyearcount = accyearcount;
	}

	public String getAccyearid() {
		return accyearid;
	}

	public void setAccyearid(String accyearid) {
		this.accyearid = accyearid;
	}

	public String getMaxmarks() {
		return maxmarks;
	}

	public void setMaxmarks(String maxmarks) {
		this.maxmarks = maxmarks;
	}

	public String getRequiredmarks() {
		return requiredmarks;
	}

	public void setRequiredmarks(String requiredmarks) {
		this.requiredmarks = requiredmarks;
	}

	public String getExamDetailsCheckBox() {
		return examDetailsCheckBox;
	}

	public void setExamDetailsCheckBox(String examDetailsCheckBox) {
		this.examDetailsCheckBox = examDetailsCheckBox;
	}

	public String getAccyear() {
		return accyear;
	}

	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getExaminationdate() {
		return examinationdate;
	}

	public void setExaminationdate(String examinationdate) {
		this.examinationdate = examinationdate;
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

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getAccyearId() {
		return accyearId;
	}

	public void setAccyearId(String accyearId) {
		this.accyearId = accyearId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getExamid() {
		return examid;
	}

	public void setExamid(String examid) {
		this.examid = examid;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public int getSno1() {
		return sno1;
	}

	public void setSno1(int sno1) {
		this.sno1 = sno1;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getHiddenid() {
		return hiddenid;
	}

	public void setHiddenid(String hiddenid) {
		this.hiddenid = hiddenid;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

}
