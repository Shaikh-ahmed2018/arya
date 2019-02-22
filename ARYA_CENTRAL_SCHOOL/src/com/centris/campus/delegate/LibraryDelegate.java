package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LibraryStockEntryDetailsForm;
import com.centris.campus.pojo.LibraryLocationPojo;
import com.centris.campus.pojo.LibrarySubsciberDetailsPojo;
import com.centris.campus.service.LibraryService;
import com.centris.campus.service.ReportsMenuService;
import com.centris.campus.serviceImpl.LibraryServiceImpl;
import com.centris.campus.serviceImpl.ReportsMenuServiceImpl;
import com.centris.campus.serviceImpl.StudentRegistrationServiceImpl;
import com.centris.campus.vo.CategoryTypeVO;
import com.centris.campus.vo.LibraryJournalSubscriptionVo;
import com.centris.campus.vo.LibraryLocationVO;
import com.centris.campus.vo.LibrarySearchIssueDetailsVO;
import com.centris.campus.vo.LibrarySearchSubscriberVO;
import com.centris.campus.vo.LibraryStockEntryVO;
import com.centris.campus.vo.LibrarySubscribVO;
import com.centris.campus.vo.LibraryVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.SubCategoryType1VO;
import com.centris.campus.vo.SubCategoryType2VO;
import com.centris.campus.vo.SubCategoryTypeVO;
import com.centris.campus.vo.TeacherVo;

public class LibraryDelegate {

	static LibraryService service;
	static {
		service=new LibraryServiceImpl();
	}

	public String insertCategoryTypeDetail(CategoryTypeVO insert_categoryType) {
		LibraryService service=new LibraryServiceImpl();
		return service.insertCategoryTypeDetail(insert_categoryType);
	}

	public List<CategoryTypeVO> getCategoryDetails() {
		LibraryService service=new LibraryServiceImpl();
		return service.getCategoryDetails();
	}

	public List<CategoryTypeVO> getSubCategoryDetails() {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryDetails();
	}
	
	public List<CategoryTypeVO> getSubCategoryDetails1() {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryDetails1();
	}
	
	public List<CategoryTypeVO> getSubCategoryDetails(String cattype,String status) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryDetails(cattype,status);
	}
	
	public List<StudentRegistrationVo> studentSearchbyadmissionNo(StudentRegistrationVo registrationVo, String locid) {
		LibraryService service=new LibraryServiceImpl();
		return service.studentSearchbyadmissionNo(registrationVo,locid);
	}

	public List<TeacherVo> teacherSearchbyId(TeacherVo TeacherVo) {
		LibraryService service=new LibraryServiceImpl();
		return service.teacherSearchbyId(TeacherVo);
	}

	public CategoryTypeVO editCategoryType(String id) {
		LibraryService service=new LibraryServiceImpl();
		return service.editCategoryType(id);
	}

	public SubCategoryTypeVO editSubCategoryType(String id) {
		LibraryService service=new LibraryServiceImpl();
		return service.editSubCategoryType(id);
	}

	public String inactiveCategoryType(String[] catIdlist) {
		LibraryService service=new LibraryServiceImpl();
		return service.inactiveCategoryType(catIdlist);
	}
	
	public String inactiveSubCategoryType(String[] id, SubCategoryTypeVO vo) {
		LibraryService service=new LibraryServiceImpl();
	return service.inactiveSubCategoryType(id,vo);
		
	}

	public String insertSubCategoryTypeDetail(SubCategoryTypeVO insert_SubcategoryType) {
		LibraryService service=new LibraryServiceImpl();
		return service.insertSubCategoryTypeDetail(insert_SubcategoryType);
	}

	public String insertSubCategoryType1Detail(SubCategoryType1VO sub1) {
		LibraryService service=new LibraryServiceImpl();
		return service.insertSubCategoryType1Detail(sub1);
	}

	public List<SubCategoryType1VO> getSubCategoryType1Details() {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryType1Details();
	}

	public SubCategoryType1VO editSubCategoryType1(String id) {
		LibraryService service=new LibraryServiceImpl();
		return service.editSubCategoryType1(id);
	}

	public List<SubCategoryTypeVO> getSubCategoryByCategory(String categoryCode) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryByCategory(categoryCode);
	}

	public ArrayList<LibrarySubscribVO> getStaffData(String staffid,String locId) {
		return new LibraryServiceImpl().getStaffData(staffid,locId);
	}


	public ArrayList<LibrarySubscribVO> getStudentData(String academicYear,String admissionNo) {
		return new LibraryServiceImpl().getStudentData(academicYear, admissionNo);
	}


	public String saveSubscriberDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().saveSubscriberDetails(pojo);
	}

	public List<StudentRegistrationVo> StudentList(
			StudentRegistrationVo registrationVo,String locid) {
		LibraryService service=new LibraryServiceImpl();
		return service.studentSearchbyadmissionNo(registrationVo,locid);
	}
	public ArrayList<LibrarySearchIssueDetailsVO> getStudentIssuedList(String locid, String accyear) {
		return service.getStudentIssuedList(locid,accyear);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentListDetails(String academic_year, String location, String select, String classname, String sectionid) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStudentListDetails(academic_year,location,select,classname,sectionid);
	}

	public String inactiveSubCategoryType1(String[] id) {
		LibraryService service=new LibraryServiceImpl();
		return service.inactiveSubCategoryType1(id);

	}

	public boolean validateSubcategoryType1(SubCategoryType1VO sub1) {
		LibraryService service=new LibraryServiceImpl();
		return service.validateSubcategoryType1(sub1);
	}

	public ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentClassList(
			String locid, String accyear, String classname) {

		return service.getIssueStudentClassList(locid, accyear,classname);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentListByClassName(String academic_year, String location,
			String classname, String sectionid, String select) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStudentListByClassName(academic_year,location,classname,sectionid,select);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentListBySection(String academic_year, String location,
			String classname, String sectionid, String select) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStudentListBySection(academic_year,location,classname,sectionid,select);
	}

	public java.util.List<LibrarySearchSubscriberVO> searchsubscriberList(String searchTextVal, String academic_year, String location) {
		/*LibraryService service=new LibraryServiceImpl();*/
		return new LibraryServiceImpl().searchsubscriberList(searchTextVal,academic_year,location);
	}

	

	public java.util.List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByEndsWith(String searchTextVal, String location, String select,String academic_year,
			String classname,String sectionid) {
		return new LibraryServiceImpl().SearchSubscriberDetailsByEndsWith(searchTextVal,location,select,academic_year,classname,sectionid);
	}

	public ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentSectionList(String locid, String accyear, String classname, String sectionnm) {
		return service.getIssueStudentSectionList(locid, accyear,classname,sectionnm);
	}

	public String insertLibraryLocations(LibraryLocationPojo insert_libLoc) {
		return service.insertLibraryLocations(insert_libLoc);
	}


	public ArrayList<LibrarySubscribVO> getSubscriberDetailsListPage(String academicYear, String locId, String classId,String sectionName, String suscriberType, String department, String designation, String otherName) {
		return new LibraryServiceImpl().getSubscriberDetailsListPage( academicYear,  locId,  classId, sectionName,  suscriberType,department,designation,otherName);
	}


	public LibraryLocationVO editLibraryLocation(String id) {
		LibraryService service=new LibraryServiceImpl();
		return service.editLibraryLocation(id);
	}


	public String updateLibLocations(LibraryLocationPojo insert_libLoc) {
		LibraryService service=new LibraryServiceImpl();
		return service.updateLibLocations(insert_libLoc);
	}

	public java.util.List<LibrarySearchIssueDetailsVO> getIssueDetailsByAnyWhere(String searchTextVal, String locid, String accyear) {
		return new LibraryServiceImpl().getIssueDetailsByAnyWhere(searchTextVal,locid,accyear);
	}
	public ArrayList<LibraryLocationPojo> getLibLocationsDetails(String location) {
		LibraryService service=new LibraryServiceImpl();
		return service.getLibLocationsDetails(location);
	}
	public String deleteLibraryLocations(String[] librarylocid) {
		return service.deleteLibraryLocations(librarylocid);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStaffListDetails(String accyear_ID, String loc_ID, String select, String department, String designation) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStaffListDetails(accyear_ID,loc_ID,select,department,designation);
	}


	public ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDepartment(String accyear_ID, String Loc_ID,
			String department,String designation) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStaffdetailsByDepartment(accyear_ID, Loc_ID,department,designation);
	}


	public ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDesignation(String accyear_ID, String Loc_ID,
			String department, String designation) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStaffdetailsByDesignation(accyear_ID, Loc_ID,department,designation);
	}


	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByAnyWhere(String searchTextVal, String location, String select,String department,String designation,String accyear) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchStaffDetailsByAnyWhere(searchTextVal,location,select,department,designation,accyear);
	}


	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByStartWith(String searchTextVal, String location, String select, String department, String designation, String accyear) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchStaffDetailsByStartWith(searchTextVal,location,select,department,designation,accyear);
	}


	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByEndsWith(String searchTextVal, String location, String select, String department, String designation, String accyear) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchStaffDetailsByEndsWith(searchTextVal,location,select,department,designation,accyear);
	}


	public ArrayList<LibrarySearchSubscriberVO> getStaffListFilterByLocationAndAcyearid(String accyear_ID,
			String loc_ID) {
		LibraryService service=new LibraryServiceImpl();
		return service.getStaffListFilterByLocationAndAcyearid(accyear_ID,loc_ID);
	}


	public ArrayList<LibraryLocationVO> getSchoolLocations(String id) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSchoolLocations(id);
	}
	public java.util.List<LibrarySearchIssueDetailsVO> getIssueDetailsByStartwith(String searchTextVal, String locid, String accyear, String selection) {
		return new LibraryServiceImpl().getIssueDetailsByStartwith(searchTextVal,locid,accyear,selection);
	}
	public List<SubCategoryType2VO> getSubCategoryType2Details() {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryType2Details();
	}

	public SubCategoryType2VO editSubCategoryType2(String id) {
		LibraryService service=new LibraryServiceImpl();
		return service.editSubCategoryType2(id);
	}


	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherList(String locid,String accyear) {
		return service. getTeacherList(locid,accyear);
	}

	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherDeptList(String locid, String accyear, String dept) {
		
		return service.getTeacherDeptList(locid,accyear, dept);
	}

	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherDesgList(String locid, String accyear, String dept, String desg) {
		
		return service.getTeacherDesgList(locid,accyear, dept,desg);
	}

	public java.util.List<LibrarySearchIssueDetailsVO> getIssueByStartwith(LibrarySearchIssueDetailsVO vo, String selection) {
		
		return service.getIssueByStartwith(vo,selection);
	}



	public boolean validateLibLocationUpdate(LibraryLocationVO lib) {
		LibraryService service=new LibraryServiceImpl();
		return service.validateLibLocationUpdate(lib);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByAnyWhere(String searchTextVal, String location, String academic_year, String select,
			String classname, String sectionid) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchSubscriberDetailsByAnyWhere(searchTextVal,location,academic_year,select,classname,sectionid);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByStartWith(String searchTextVal, String location, String select,
			String classname, String sectionid, String academic_year) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchSubscriberDetailsByStartWith(searchTextVal,location,select,classname,sectionid,academic_year);
	}

	public List<SubCategoryType1VO> getSubCategory1ByCategoryAndSubCategory(
			String subCategoryTypeCode) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategory1ByCategoryAndSubCategory(subCategoryTypeCode);
	}

	public String insertSubCategoryType2Detail(SubCategoryType2VO sub2) {
		LibraryService service=new LibraryServiceImpl();
		return service.insertSubCategoryType2Detail(sub2);
	}

	public String inactiveSubCategoryType2(String[] id) {
		LibraryService service=new LibraryServiceImpl();
		return service.inactiveSubCategoryType2(id);
	}

	public ArrayList<LibrarySearchIssueDetailsVO> getOthersList(String locid,String accyear) {
	
		return service.getOthersList(locid, accyear);
	}

	public java.util.List<LibrarySearchIssueDetailsVO> getIssueotherByStartwith(String searchTextVal, String locid, String accyear, String selection) {
		
		return service.getIssueotherByStartwith( searchTextVal, locid, accyear, selection);
	}



	public List<CategoryTypeVO> getSubCategoryTypeName(String categoryName) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryTypeName(categoryName);
	}
	
	
	public List<CategoryTypeVO> getSubCategoryList(String catcode,String subcatcode,String status) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryList(catcode,subcatcode,status);
	}
	
	public List<CategoryTypeVO> getbystatusList(String catcode,String subcatcode,String status) {
		LibraryService service=new LibraryServiceImpl();
		return service.getbystatusList(catcode,subcatcode,status);
	}
	
	
	public List<CategoryTypeVO> SearchCategoryTypeList(String catcode,String subcatcode,String status,String searchname) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchCategoryTypeList(catcode,subcatcode,status,searchname);
	}


	public List<SubCategoryType1VO> getTabByCategoryType(String cattype, String status, String subcacode, String subcacode1) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTabByCategoryType(cattype,status,subcacode,subcacode1);
	}

	public List<SubCategoryType1VO> getTableBycategorytypeandSub(String cattype, String status, String category, String subcacode1) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTableBycategorytypeandSub(cattype,status,category,subcacode1);
	}

	public List<SubCategoryType1VO> getTableBycategorytypeandSub1(String cattype, String status, String category, String subcacode) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTableBycategorytypeandSub1(cattype,status,category,subcacode
);
	}

	
	public String insertSubCategoryTypeDetail3(SubCategoryTypeVO insert_SubcategoryType) {
		LibraryService service=new LibraryServiceImpl();
		return service.insertSubCategoryTypeDetail3(insert_SubcategoryType);
	}
	
	
	public List<SubCategoryTypeVO> getSubCategoryDetails3() {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryDetails3();
	}
	
	
	public SubCategoryTypeVO editSubCategoryType3(String id) {
		LibraryService service=new LibraryServiceImpl();
		return service.editSubCategoryType3(id);
	}
	
	public List<SubCategoryType1VO> getSubCategory2ByCategoryAndSubCategory(
			String subCategoryTypeCode) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategory2ByCategoryAndSubCategory(subCategoryTypeCode);
	}
	
	
	
	public String inactiveSubCategoryType3(String[] id, SubCategoryTypeVO vo) {
		LibraryService service=new LibraryServiceImpl();
	return service.inactiveSubCategoryType3(id,vo);
		
	}
	
	public List<CategoryTypeVO> getSubCategoryDetails3(String cattype,String subcatcode, String subcatcode1, String subcatcode2, String subcatcode3, String status) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryDetails3(cattype,subcatcode,subcatcode1,subcatcode2,subcatcode3,status);
	}
	
	public List<CategoryTypeVO> getSubCategoryList3(String catcode,String subcatcode,String status) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategoryList3(catcode,subcatcode,status);
	}
	
	public List<SubCategoryType1VO> getSubCategory3ByCategoryAndSubCategory(
			String subCategoryTypeCode) {
		LibraryService service=new LibraryServiceImpl();
		return service.getSubCategory3ByCategoryAndSubCategory(subCategoryTypeCode);
	}
	
	public List<CategoryTypeVO> SearchCategoryType3List(String catcode,String subcatcode,String subcatcode1,String subcatcode2,String subcatcode3,String status,String searchname) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchCategoryType3List(catcode,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname);
	}

	public ArrayList<CategoryTypeVO> getcategorylist(String cateid ,String status) {
		
		return service.getcategorylist(cateid,status);
	}

	public List<SubCategoryType1VO> getTableByStatus(String status, String categorycode, String subcategorycode, String subcategory1code) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTableByStatus(status,categorycode,subcategorycode,subcategory1code);
	}

	public ArrayList<LibraryStockEntryVO> getAccessionNoList() {
		LibraryService service=new LibraryServiceImpl();
		return service.getAccessionNoList();
	}


	public ArrayList<CategoryTypeVO> getclassdescrlist(String cateid) {
		return service. getclassdescrlist( cateid);
	}

	public ArrayList<CategoryTypeVO> getlibcategorysectionlist(String cateid,String classid) {
		
		return service.getlibcategorysectionlist(cateid, classid);
	}

	public ArrayList<CategoryTypeVO> getlibcategorydivisionlist(String sectionid) {
		
		return service.getlibcategorydivisionlist(sectionid);
	}

	public String saveStockEnteryDetails(LibraryStockEntryDetailsForm libform) {
		
		return service.saveStockEnteryDetails(libform);
	}



	public List<LibraryStockEntryVO> getAccessionNo(
			LibraryStockEntryVO registrationVo) {
		LibraryService service=new LibraryServiceImpl();
		return service.getAccessionNo(registrationVo);
		
	}

	public static  List<LibraryStockEntryVO> getBookIssueDetailsByAccessionNo(
			LibraryStockEntryVO libVo) {
		LibraryService service=new LibraryServiceImpl();
		return service.getBookIssueDetailsByAccessionNo(libVo);
	}

	public String insertBookIssueDetails(LibraryStockEntryVO insert_issue) {
		return service.insertBookIssueDetails(insert_issue);
	}


	

	public List<SubCategoryType2VO> getTabBySub2CategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTabBySub2CategoryType(cattype,status,subcategory,subcategory1,subcategory2);
	}

	public List<SubCategoryType2VO> getTabBySub2subCategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTabBySub2subCategoryType(cattype,status,subcategory,subcategory1,subcategory2);
	}

	public List<SubCategoryType2VO> getTabBySub2subCategory1Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTabBySub2subCategory1Type(cattype,status,subcategory,subcategory1,subcategory2);
	}

	public List<SubCategoryType1VO> searchSubCatType1(String searchname, String catcode, String subcatcode, String subcatcode1, String status) {
		LibraryService service=new LibraryServiceImpl();
		return service.searchSubCatType1(searchname,catcode,subcatcode,subcatcode1,status);
	}

	public List<SubCategoryType2VO> getTableBySub2Status(String cattype, String status, String subcategory, String subcategory1, String subcategory2) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTableBySub2Status(cattype,status,subcategory,subcategory1,subcategory2);
	}




	public ArrayList<LibrarySubscribVO> getOtherSubscribeNunmber(String loc, String subscriberType) {
		return new LibraryServiceImpl().getOtherSubscribeNunmber(loc,subscriberType);
	}

	public ArrayList<LibrarySubscribVO> showBlockListedData(String loc,String subscriberType, String subscriberNo) {
		return new LibraryServiceImpl().showBlockListedData(loc,subscriberType,subscriberNo);
	}
	
public String ValidateSubcat(String subname) {
		
		return service.ValidateSubcat(subname);
	}


public String ValidateSubcatupdate(String subname) {
	
	return service.ValidateSubcatupdate(subname);
}




	public List<SubCategoryType2VO> getTabBySub2subCategory2Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2) {
		LibraryService service=new LibraryServiceImpl();
		return service.getTabBySub2subCategory2Type(cattype,status,subcategory,subcategory1,subcategory2);
	}

	public List<SubCategoryType2VO> searchSubCatType2(String searchname, String categorytype, String subcategorytype, String subcategorytype1, String subcategorytype2, String status) {
		LibraryService service=new LibraryServiceImpl();
		return service.searchSubCatType2(searchname,categorytype,subcategorytype,subcategorytype1,subcategorytype2,status);
	}

	public ArrayList<LibrarySearchSubscriberVO> getOthersListDetails( String location,String select, String academic_year) {
		LibraryService service=new LibraryServiceImpl();
		return service.getOthersListDetails(location,select,academic_year);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByAnyWhere(String searchTextVal,String location, String select, String accyear) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchOthersDetailsByAnyWhere(searchTextVal,location,select,accyear);
	}
	
	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByStartWith(String searchTextVal,String location,String select,String accyear) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchOthersDetailsByStartWith(searchTextVal,location,select,accyear);
	}
	
	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByEndsWith(String searchTextVal,String location, String select,String accyear) {
		LibraryService service=new LibraryServiceImpl();
		return service.SearchOthersDetailsByEndsWith(searchTextVal,location,select,accyear);
	}

	public String ValidateSubcat3(String subname) {
	return service.ValidateSubcat3(subname);
}

	

	public LibrarySubscribVO gotosubscribersDetails(String location, String subId, String academic_year, String subscriberType) {
		LibraryService service=new LibraryServiceImpl();
		return service.gotosubscribersDetails(location,subId,academic_year,subscriberType);
	}
	
	public String updateSubscriberDetails(LibrarySubscribVO resultData) {
		LibraryService service=new LibraryServiceImpl();
		return service.updateSubscriberDetails(resultData);
	}



	public LibrarySubscribVO IssueStatementBySubScriberType(String location, String subId, String academic_year,
			String subscriberType) {
		LibraryService service=new LibraryServiceImpl();
		return service.IssueStatementBySubScriberType(location,subId,academic_year,subscriberType);
	}

	public ArrayList<LibrarySearchSubscriberVO> IssueStatementTable(String location, String subId, String academic_year,
			String subscriberType) {
		LibraryService service=new LibraryServiceImpl();
		return service.IssueStatementTable(location,subId,academic_year,subscriberType);
	}

	public LibrarySubscribVO issuestatementissue(String subId,String issueId,String subscriberType) {
		LibraryService service=new LibraryServiceImpl();
		return service.issuestatementissue(subId,issueId,subscriberType);
	}




	public List<LibraryStockEntryDetailsForm> getStockEntryBookList() {
		
		return service. getStockEntryBookList();
	}

	public LibraryStockEntryDetailsForm editStockEntryDetail(String id) {
		
		return service.editStockEntryDetail(id);
	}



	public String blockTheSubscriber(String subscriberNo) {
		return new LibraryServiceImpl().blockTheSubscriber(subscriberNo);
	}

	public ArrayList<LibrarySubscribVO> getStaffRegId(String loc, String searchterm) {
		return new LibraryServiceImpl().getStaffRegId(loc,searchterm);
	}

	public String duplicateDataCheck(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().duplicateDataCheck(pojo);
	}
	
	public String activeSubCategoryType(String[] id, SubCategoryTypeVO vo) {
		LibraryService service=new LibraryServiceImpl();
	return service.activeSubCategoryType(id,vo);
		
	}
	
	
	public String activeSubCategoryType3(String[] id, SubCategoryTypeVO vo) {
		LibraryService service=new LibraryServiceImpl();
	return service.activeSubCategoryType3(id,vo);
		
	}

	public ArrayList<LibrarySubscribVO> getBlockListedStaffData(String accyear,String location) {
		return new LibraryServiceImpl().getBlockListedStaffData(accyear, location);
	}

	public ArrayList<LibrarySubscribVO> getBlockListedStudentData(String accyear, String location) {
		return new LibraryServiceImpl().getBlockListedStudentData( accyear,  location);
	}

	public ArrayList<LibrarySubscribVO> getBlockListedOtherData(String accyear,String location) {
		return new LibraryServiceImpl().getBlockListedOtherData( accyear, location);
	}

	public String unblockSubscriber(String id) {
		return new LibraryServiceImpl().unblockSubscriber(id);
	}

	public String validateStockEnteryDetails(String accno) {
		
		return service. validateStockEnteryDetails(accno);
	}



	public String insertBookReturnDetails(LibraryStockEntryVO insert_issue) {
		return service.insertBookReturnDetails(insert_issue);
	}

	public static List<LibraryStockEntryVO> getBookReturnDetailsByAccessionNo(LibraryStockEntryVO libVo) {
		LibraryService service=new LibraryServiceImpl();
		return service.getBookReturnDetailsByAccessionNo(libVo);
	}

	public List<LibraryStockEntryVO> getAccessionNoByIssue(LibraryStockEntryVO registrationVo) {
		LibraryService service=new LibraryServiceImpl();
		return service.getAccessionNoByIssue(registrationVo);
	}



	public ArrayList<CategoryTypeVO> getCategoryListBySearch(String cateid, String status, String searchname) {
		return new LibraryServiceImpl().getCategoryListBySearch(cateid,status,searchname);
	}

	public String activeSubCategoryType1(String[] id, SubCategoryType1VO vo) {
		LibraryService service=new LibraryServiceImpl();
	return service.activeSubCategoryType1(id,vo);
		
	}

	public String activeSubCategoryType2(String[] id, SubCategoryType2VO vo) {
		LibraryService service=new LibraryServiceImpl();
	return service.activeSubCategoryType2(id,vo);
		
	}

	public String activeCategoryType(String[] id, CategoryTypeVO vo) {
		LibraryService service=new LibraryServiceImpl();
		return service.activeCategoryType(id,vo);
	}

	public LibrarySubscribVO GOtOIssueReturns(String subId, String subscriberType, String issueId) {
		LibraryService service=new LibraryServiceImpl();
		return service.GOtOIssueReturns(subId,subscriberType,issueId);
	}

	public String publisherSettings(LibraryStockEntryVO obj) {
	
		return service.publisherSettings(obj);
	}

	public List<LibraryStockEntryVO> getPublisherSettingList() {
		return service.getPublisherSettingList();
	}

	public LibraryStockEntryVO editpublisherSetting(String id) {
		return service.editpublisherSetting(id);
	}

	public String deletepublisherSetting(String[] deleteId) {
		return service.deletepublisherSetting(deleteId);
	}

	public String validationpubsettings(String pub, String address,
			String email, String telphone, String mobilenum) {
		return service.validationpubsettings(pub,address,email,telphone,mobilenum);
	}

	public ArrayList<ReportMenuVo> getLibraryLocation() {
		LibraryService service=new LibraryServiceImpl();
		return service.getLibraryLocation();
	}

	public String TransferStudent(String[] subscriberId ,String locid) {
	
		return service.TransferStudent(subscriberId,locid);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTranferStudentListDetails(String academic_year, String location, String select,
			String classname, String sectionid, String libloc) {
		return service.getTranferStudentListDetails(academic_year,location,select,classname,sectionid,libloc);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListBySection(String academic_year, String location, String classname,String sectionid, String select, String liblocation) {
		return service.getTrasferStudentListBySection(academic_year,location,classname,sectionid,select,liblocation);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListByClassName(
			String academic_year, String location, String classname,
			String select, String liblocation) {
		return service.getTrasferStudentListByClassName(academic_year,location,classname,select,liblocation);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffListDetails(String accyear_ID, String loc_ID, String select,
			String department, String designation,String libloc) {
			LibraryService service=new LibraryServiceImpl();
			return service.getTransferStaffListDetails(accyear_ID,loc_ID,select,department,designation,libloc);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferOthersListDetails(String location,String select,String academic_year,String libloc) {
	
		return service.getTransferOthersListDetails(location,select,academic_year,libloc);
	}

	public ArrayList<LibrarySearchSubscriberVO> getlocationStudentList(String libloc, String select) {
		return service.getlocationStudentList(libloc,select);
	}

	public ArrayList<LibrarySearchSubscriberVO> getliblocationstafflist(String libloc, String select) {
		return service.getliblocationstafflist(libloc,select);
	}

	public ArrayList<LibrarySearchSubscriberVO> getliblocatinotherlist(String academic_year, String location, String libloc, String select) {
		
		return service.getliblocatinotherlist(academic_year,location,libloc,select);
	}

	public java.util.List<LibrarySearchSubscriberVO> TransferSubscriberbySearch(String searchTextVal, String location, String academic_year,String liblocid, String select,String classname, String sectionid) {
		
		return service.TransferSubscriberbySearch(searchTextVal,location,academic_year,liblocid,select,classname,sectionid);
	}

	public ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyStaffSearch(String searchTextVal, String location, String liblocid,String select, String department, String designation, String accyear) {
		return service.TransferSubscriberbyStaffSearch(searchTextVal,location,liblocid,select,department,designation,accyear);
	}

	public ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyotherSearch(
			String searchTextVal, String location, String select,String liblocid, String accyear) {
		return service.TransferSubscriberbyotherSearch(searchTextVal,location,select,liblocid,accyear);
	}

	public String addSupplierSettings(LibraryStockEntryVO obj) {
		
		return service.addSupplierSettings(obj);
	}

	public List<LibraryStockEntryVO> getSupplierSettingList() {
		
		return service.getSupplierSettingList();
	}

	public LibraryStockEntryVO editSupplierSetting(String id) {
		
		return service.editSupplierSetting(id);
	}

	public String deleteSupplierSetting(String[] deleteId) {
	
		return service.deleteSupplierSetting(deleteId);
	}

	public String validationsubsettings(String suplier, String supadd,String emailid,String telephone,String supnum) {
		
		return service.validationsubsettings(suplier,supadd,emailid,telephone,supnum);
	}

	public List<TeacherVo> othersSearchbyId(TeacherVo registrationVo) {
		return service.othersSearchbyId(registrationVo);
	}

	public static List<LibraryStockEntryVO> getStudentIssueDetailsBySubscriberNo(LibraryStockEntryVO libVo) {
		return service.getStudentIssueDetailsBySubscriberNo(libVo);
	}
	public String insertBookReservationDetails(LibraryStockEntryVO insert_issue) {
		return service.insertBookReservationDetails(insert_issue);
	}

	public List<LibraryStockEntryDetailsForm> getReservationListDetails() {
		return service.getReservationListDetails();
	}

	public ArrayList<LibraryStockEntryVO> publisherDetailsSearch(
			String searchTextVal, String pub) {
		
		return service.publisherDetailsSearch(searchTextVal,pub);
	}

	public ArrayList<LibraryStockEntryVO> SupplierDetailsSearch(
			String searchTextVal, String sup) {
		return service.SupplierDetailsSearch(searchTextVal,sup);
	}

	


	public static List<LibraryStockEntryVO> getTeacherIssueDetails(LibraryStockEntryVO libVo) {
		return service.getTeacherIssueDetails(libVo);
	}

	public static List<LibraryStockEntryVO> getOtherIssueDetails(LibraryStockEntryVO libVo) {
		return service.getOtherIssueDetails(libVo);
	}

	public ArrayList<LibrarySearchSubscriberVO> IssueReturnTable(String location, String subId, String academic_year,
			String subscriberType) {
		LibraryService service=new LibraryServiceImpl();
		return service.IssueReturnTable(location,subId,academic_year,subscriberType);
	}

	public String addGeneralSettings(LibraryStockEntryVO obj) {
		
		return service.addGeneralSettings(obj);
	}

	public List<LibraryStockEntryVO> getGenarelSettingList() {
		return service.getGenarelSettingList();
	}

	public LibraryStockEntryVO editGenarelSetting(String id) {
		return service.editGenarelSetting(id);
	}

	public String deleteGenarelSetting(String[] deleteId) {
		return service.editGenarelSetting(deleteId);
	}

	public ArrayList<LibraryStockEntryVO> GenarelDetailsSearch(
			String searchTextVal) {
		return service.GenarelDetailsSearch(searchTextVal);
	}

	public LibrarySubscribVO IssueReturnBySubScriberType(String location, String subId, String academic_year,
			String subscriberType) {
		LibraryService service=new LibraryServiceImpl();
		return service.IssueReturnBySubScriberType(location,subId,academic_year,subscriberType);
	}

	public ArrayList<LibraryVO> getcodeList() {
		LibraryService service=new LibraryServiceImpl();
		return service.getcodeList();
	}

	public List<LibraryStockEntryVO> getcodeName(LibraryStockEntryVO registrationVo) {
		LibraryService service=new LibraryServiceImpl();
		return service.getcodeName(registrationVo);
	}

	public static List<LibraryStockEntryVO> getCodeByCodeName(LibraryStockEntryVO libVo) {
		LibraryService service=new LibraryServiceImpl();
		return service.getCodeByCodeName(libVo);
	}

	public LibraryStockEntryVO editReservationBook(String id) {
		LibraryService service=new LibraryServiceImpl();
		return service.editReservationBook(id);
	}

	public String updateBookReservationDetails(LibraryStockEntryVO insert_issue) {
		LibraryService service=new LibraryServiceImpl();
		return service.updateBookReservationDetails(insert_issue);
	}

	public static List<LibraryStockEntryVO> getBookReservationDetailsByAccNo(
			LibraryStockEntryVO libVo) {
		LibraryService service=new LibraryServiceImpl();
		return service.getBookReservationDetailsByAccNo(libVo);
	}

	public String deleteReservedBook(String[] librarylocid) {
		LibraryService service=new LibraryServiceImpl();
		return service.deleteReservedBook(librarylocid);
	}

	public boolean validateReservedBook(LibraryStockEntryVO reserve) {
		LibraryService service=new LibraryServiceImpl();
		return service.validateReservedBook(reserve);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListDetails(String academic_year, String location,
			String select, String classname, String sectionid) {
		return new LibraryServiceImpl().getMostWantedStudentListDetails(academic_year,location,select,classname,sectionid);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchMostWantedStudentDetailsByAnyWhere(String searchTextVal,String location,String academic_year,
			String select, String startwith, String classname, String sectionid) {
		return new LibraryServiceImpl().SearchMostWantedStudentDetailsByAnyWhere(searchTextVal,location,academic_year,select,startwith,classname,sectionid);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListByClassName(String academic_year,
			String location, String classname, String sectionid, String select) {
		return new LibraryServiceImpl().getMostWantedStudentListByClassName(academic_year,location,classname,sectionid,select);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListBySection(String academic_year, String location,
			String classname, String sectionid, String select) {
		return new LibraryServiceImpl().getMostWantedStudentListBySection(academic_year,location,classname,sectionid,select);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffListDetails(String academic_year, String location,
			String select, String department, String designation) {
		return new LibraryServiceImpl().getMostWantedStaffListDetails(academic_year,location,select,department,designation);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchMostWantedStaffDetailsByAnyWhere(String searchTextVal,String location,String select,String startwith) {
		return new LibraryServiceImpl().SearchMostWantedStaffDetailsByAnyWhere(searchTextVal,location,select,startwith);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffdetailsByDepartment(String accyear_ID, String loc_ID,
			String department, String designation,String select) {
		return new LibraryServiceImpl().getMostWantedStaffdetailsByDepartment(accyear_ID,loc_ID, department,designation,select);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffdetailsByDesignation(String accyear_ID, String loc_ID,
			String department, String designation, String select) {
		return new LibraryServiceImpl().getMostWantedStaffdetailsByDesignation(accyear_ID,loc_ID,department,designation,select);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedOthersListDetails(String location, String select,
			String academic_year) {
		return new LibraryServiceImpl().getMostWantedOthersListDetails(location,select,academic_year);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchMostWantedOthersDetailsByAnyWhere(String searchTextVal,
			String location, String select, String startwith) {
		return new LibraryServiceImpl().SearchMostWantedOthersDetailsByAnyWhere(searchTextVal,location,select,startwith);
	}

	public String savejournalsubscriptiondetail(LibraryJournalSubscriptionVo obj) {
	
		return service.savejournalsubscriptiondetail(obj);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getAllBookDetails(pojo);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchByaccNoandTitleAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().SearchBookSearchByaccNoandTitleAnyWhere(pojo);
	}


	public List<LibraryJournalSubscriptionVo> getJournalSubscriptionList() {
		
		return service.getJournalSubscriptionList();
	}

	public LibraryJournalSubscriptionVo editeLibraryJournalSubscription(
			String id) {
		
		return service.editeLibraryJournalSubscription(id);
	}

	public String deleteJournalSbscription(String[] deleteId) {
		
		return service.deleteJournalSbscription(deleteId);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookPublisherDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getAllBookPublisherDetails(pojo);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchByPublisherAnyWhere(
			LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().SearchBookSearchByPublisherAnyWhere(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookItemTypeDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getAllBookItemTypeDetails(pojo);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchByItemTypeAnyWhere(
			LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().SearchBookSearchByItemTypeAnyWhere(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookDDCDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getAllBookDDCDetails(pojo);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchByDDCAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().SearchBookSearchByDDCAnyWhere(pojo);
	}
	public List<LibraryJournalSubscriptionVo> getJournalSubscriptioncodelist() {
		
		return service.getJournalSubscriptioncodelist();
	}

	public List<LibraryJournalSubscriptionVo> getnamelist() {
		
		return service.getnamelist();
	}

	public ArrayList<LibraryJournalSubscriptionVo> journalsubscriptionDetailsSearch(
			String searchTextVal, String name) {
		
		return service.journalsubscriptionDetailsSearch(searchTextVal,name);
	}

	public List<LibraryStockEntryVO> getstockEntryList(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId) {
		
		return service.getstockEntryList(locId,itemId,regdateId,booktitle,authorId,pubId);
	}

	public List<LibraryStockEntryVO> getlocationlist(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId) {
		
		return service.getlocationlist(locId,itemId,regdateId,booktitle,authorId,pubId);
	}

	public List<LibraryStockEntryVO> getitemlistList() {
		
		return service.getitemlistList();
	}

	public List<LibraryStockEntryVO> booklist() {
		
		return service.booklist();
	}

	public List<LibraryStockEntryVO> authorlist() {
		
		return service.authorlist();
	}
	
	public ArrayList<LibrarySearchIssueDetailsVO> getSubscriberDetailStudentExcelReport(LibrarySearchIssueDetailsVO obj) {
		
		return service.getSubscriberDetailStudentExcelReport(obj);
	}

public ArrayList<LibrarySearchIssueDetailsVO> getStaffSubscriberDetailReport(LibrarySearchIssueDetailsVO obj) {
		
		return service.getStaffSubscriberDetailReport(obj);
	}
public List<LibraryStockEntryVO> getReservationListReport(String location, String accId, String subId, String accNo, String bookId, String fromdat, String todate, String date) {
   
	 return service.getReservationListReport(location,accId,subId,accNo,bookId,fromdat,todate,date);
	}

	
	public List<LibraryStockEntryDetailsForm> getbooktitleList(String subtype, String accyear, String accNo) {
		
		return service.getbooktitleList(subtype,accyear,accNo);
	}
	
public ArrayList<LibrarySearchIssueDetailsVO> getOtherSubscriberDetailReport(LibrarySearchIssueDetailsVO obj) {
		
		return service.getOtherSubscriberDetailReport(obj);
	}

	public List<LibraryStockEntryVO> getNewArrivalListReport(String checkedVal, String fromdate, String toDate) {
		return service.getNewArrivalListReport(checkedVal,fromdate,toDate);
	}


	
	public ArrayList<LibrarySearchSubscriberVO> getAllBookContentDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getAllBookContentDetails(pojo);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchByContentAnyWhere(
			LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().SearchBookSearchByContentAnyWhere(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookLanguageDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getAllBookLanguageDetails(pojo);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchByLanguageAnyWhere(
			LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().SearchBookSearchByLanguageAnyWhere(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookSupplierDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getAllBookSupplierDetails(pojo);
	}

	public java.util.List<LibrarySearchSubscriberVO> SearchBookSearchBySupplierAnyWhere(
			LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().SearchBookSearchBySupplierAnyWhere(pojo);
	}

	public LibraryStockEntryDetailsForm gotostockDetails(String id) {
		return new LibraryServiceImpl().gotostockDetails(id);
	}

	public LibrarySubscribVO IssueStatementByStockEntryId(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().IssueStatementByStockEntryId(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> IssueStatementTableByStockEntryId(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().IssueStatementTableByStockEntryId(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> ReturnStatementTableByStockEntryId(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().ReturnStatementTableByStockEntryId(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getOverDueStatement(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getOverDueStatement(pojo);
	}

	public List<LibrarySubscribVO> getClassByLibraryLocation(String locationid) {
		return new LibraryServiceImpl().getClassByLibraryLocation(locationid);
	}

	public java.util.List<LibrarySubscribVO> getLibraryClassSection(String classidVal) {
		return new LibraryServiceImpl().getLibraryClassSection(classidVal);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentOverDueStatementByClass(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getStudentOverDueStatementByClass(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentOverDueStatementBySection(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getStudentOverDueStatementBySection(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchOverDueStudentDetailsByAnyWhere(
			LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().SearchOverDueStudentDetailsByAnyWhere(pojo);
	}

	public ArrayList<LibraryStockEntryDetailsForm> getStudentOverDueByOrderwise(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getStudentOverDueByOrderwise(pojo);
	}

	public List<LibrarySearchIssueDetailsVO> getAllOverDueListDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getAllOverDueListDetails(pojo);
	}
	
	public ArrayList<LibrarySearchSubscriberVO> getAllBookDetailsDownloadandPrint(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getAllBookDetailsDownloadandPrint(pojo);
	}

	public List<LibraryStockEntryDetailsForm> getReservationAccNo(
			String subtype, String accyear) {
		
		return service.getReservationAccNo(subtype,accyear);
	}

	public ArrayList<LibrarySubsciberDetailsPojo> getAllIssueReturnDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getAllIssueReturnDetails(pojo);
	}

	public List<LibrarySubsciberDetailsPojo> IndividualSearchInIssueReturnStatement(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().IndividualSearchInIssueReturnStatement(pojo);
	}
	public ArrayList<LibraryStockEntryVO> getJournalNameList(String accyear) {
		return service.getJournalNameList(accyear);
	}

	public List<LibraryJournalSubscriptionVo> getJournalListReport(String checkedVal,
			String fromdate, String toDate, String accyear, String journalName) {
		return service.getJournalListReport(checkedVal,fromdate,toDate,accyear,journalName);
	}


	public static List<LibraryStockEntryVO> getBookIssueReturnDetailsByAccessionNo(LibraryStockEntryVO libVo) {
		return new LibraryServiceImpl().getBookIssueReturnDetailsByAccessionNo(libVo);
	}

	public List<LibraryStockEntryVO> getIssueReturnAccessionNo(LibraryStockEntryVO registrationVo) {
		return new LibraryServiceImpl().getIssueReturnAccessionNo(registrationVo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffdetailsByDepartment(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getTransferStaffdetailsByDepartment(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffdetailsByDesignation(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryServiceImpl().getTransferStaffdetailsByDesignation(pojo);
	}

	public List<LibraryStockEntryVO> getAccessionNoByIssueStatus(
			LibraryStockEntryVO registrationVo) {
		return new LibraryServiceImpl().getAccessionNoByIssueStatus(registrationVo);
	}
	
}


