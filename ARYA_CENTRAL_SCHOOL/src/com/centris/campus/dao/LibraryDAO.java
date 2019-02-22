package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LibraryStockEntryDetailsForm;
import com.centris.campus.pojo.LibraryLocationPojo;
import com.centris.campus.pojo.LibrarySubsciberDetailsPojo;
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

public interface LibraryDAO {

	String insertCategoryTypeDetail(CategoryTypeVO insert_categoryType);

	List<CategoryTypeVO> getCategoryDetails();

	List<StudentRegistrationVo> studentSearchbyadmissionNo(StudentRegistrationVo registrationVo,String locid);

	List<TeacherVo> teacherSearchbyId(TeacherVo teacherVo);

	CategoryTypeVO editCategoryType(String id);

	SubCategoryTypeVO editSubCategoryType(String id);

	String inactiveCategoryType(String[] catIdlist);

	String insertSubCategoryType1Detail(SubCategoryType1VO sub1);

	List<SubCategoryType1VO> getSubCategoryType1Details();

	SubCategoryType1VO editSubCategoryType1(String id);

	List<SubCategoryTypeVO> getSubCategoryByCategory(String categoryCode);

	ArrayList<LibrarySearchIssueDetailsVO> getStudentIssuedList(String locid, String accyear);
	ArrayList<LibrarySearchSubscriberVO> getStudentListDetails(String academic_year, String location, String select, String classname, String sectionid);

	String inactiveSubCategoryType1(String[] id);

	boolean validateSubcategoryType1(SubCategoryType1VO sub1);

	ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentClassList(String locid, String accyear, String classname);

	ArrayList<LibrarySearchSubscriberVO> getStudentListByClassName(String academic_year, String location,
			String classname, String sectionid, String select);

	String inactiveSubCategoryType(String[] id, SubCategoryTypeVO vo);

	ArrayList<LibrarySearchSubscriberVO> getStudentListBySection(String academic_year, String location,
			String classname, String sectionid, String select);

	List<LibrarySearchSubscriberVO> searchsubscriberList(String searchTextVal, String academic_year, String location);

	List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByEndsWith(String searchTextVal,String location,String select,String academic_year,String classname,String sectionid);

	ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentSectionList(String locid, String accyear, String classname, String sectionnm);

	List<LibrarySearchIssueDetailsVO> getIssueDetailsByAnyWhere(String searchTextVal, String locid, String accyear);

	LibraryLocationVO editLibraryLocation(String id);

	String insertLibraryLocations(LibraryLocationPojo insert_libLoc);

	String updateLibLocations(LibraryLocationPojo insert_libLoc);

	ArrayList<LibraryLocationPojo> getLibLocationsDetails(String location);

	ArrayList<LibrarySearchSubscriberVO> getStaffListDetails(String accyear_ID, String loc_ID, String select,String department,String designation);

	ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDepartment(String accyear_ID, String loc_ID,
			String department, String designation);

	ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDesignation(String accyear_ID, String loc_ID,
			String department, String designation);

	ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByAnyWhere(String searchTextVal,String location,String select, String department, String designation, String accyear);

	ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByStartWith(String searchTextVal,String location,String select, String department, String designation, String accyear);

	ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByEndsWith(String searchTextVal,String location,String select, String department, String designation, String accyear);

	ArrayList<LibrarySearchSubscriberVO> getStaffListFilterByLocationAndAcyearid(String accyear_ID, String loc_ID);

	List<LibrarySearchIssueDetailsVO> getIssueDetailsByStartwith(String searchTextVal, String locid, String accyear, String selection);

	String deleteLibraryLocations(String[] librarylocid);

	ArrayList<LibraryLocationVO> getSchoolLocations(String id);

	List<CategoryTypeVO> getSubCategoryDetails();

	List<CategoryTypeVO> getSubCategoryDetails1();

	List<CategoryTypeVO> getSubCategoryDetails(String cattype,String status);

	List<SubCategoryType2VO> getSubCategoryType2Details();

	SubCategoryType2VO editSubCategoryType2(String id);

	List<SubCategoryType1VO> getSubCategory1ByCategoryAndSubCategory(String subCategoryTypeCode);

	String insertSubCategoryType2Detail(SubCategoryType2VO sub2);

	ArrayList<LibrarySearchIssueDetailsVO> getTeacherList(String locid,String accyear);

	ArrayList<LibrarySearchIssueDetailsVO> getTeacherDeptList(String locid,String accyear, String dept);

	ArrayList<LibrarySearchIssueDetailsVO> getTeacherDesgList(String locid,String accyear, String dept, String desg);

	List<LibrarySearchIssueDetailsVO> getIssueByStartwith(LibrarySearchIssueDetailsVO vo,String selection);

	boolean validateLibLocationUpdate(LibraryLocationVO lib);

	List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByAnyWhere(String searchTextVal, String location,String academic_year,String select, String classname, String sectionid);

	List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByStartWith(String searchTextVal, String location, String select,String classname,String sectionid,String academic_year);

	String inactiveSubCategoryType2(String[] id);

	boolean validateSubcategoryType2(SubCategoryType2VO sub2);

	ArrayList<LibrarySearchIssueDetailsVO> getOthersList(String locid,String accyear);

	List<LibrarySearchIssueDetailsVO> getIssueotherByStartwith(String searchTextVal, String locid, String accyear,String selection);

	List<CategoryTypeVO> getSubCategoryTypeName(String categoryName);

	List<CategoryTypeVO> getSubCategoryList(String catcode,String subcatcode,String status);

	List<CategoryTypeVO> getbystatusList(String catcode,String subcatcode,String status);

	List<CategoryTypeVO> SearchCategoryTypeList(String catcode,String subcatcode,String status,String searchname);

	List<SubCategoryType1VO> getTabByCategoryType(String cattype, String status, String subcacode, String subcacode1);

	List<SubCategoryType1VO> getTableBycategorytypeandSub(String cattype, String status, String category, String subcacode1);

	List<SubCategoryTypeVO> getSubCategoryDetails3();

	SubCategoryTypeVO editSubCategoryType3(String id);


		List<LibraryStockEntryVO> getAccessionNo(LibraryStockEntryVO registrationVo);

		List<LibraryStockEntryVO> getBookIssueDetailsByAccessionNo(
				LibraryStockEntryVO libVo);

	List<SubCategoryType1VO> getSubCategory2ByCategoryAndSubCategory(String subCategoryTypeCode);


	List<SubCategoryType1VO> getTableBycategorytypeandSub1(String cattype, String status, String category, String subcacode);



		String insertBookIssueDetails(LibraryStockEntryVO insert_issue);



	



	String inactiveSubCategoryType3(String[] id, SubCategoryTypeVO vo);

	List<CategoryTypeVO> getSubCategoryDetails3(String cattype,String subcatcode, String subcatcode1, String subcatcode2, String subcatcode3, String status);

	List<CategoryTypeVO> getSubCategoryList3(String catcode,String subcatcode,String status);

	List<SubCategoryType1VO> getTableByStatus(String status, String categorycode, String subcategorycode, String subcategory1code);

	String ValidateSubcat(String subname);

	ArrayList<CategoryTypeVO> getcategorylist(String cateid ,String status);


	List<SubCategoryType1VO> getSubCategory3ByCategoryAndSubCategory(String subCategoryTypeCode);


	String ValidateSubcat3(String subname);
		
	String ValidateSubcatupdate(String subname);

	List<CategoryTypeVO> SearchCategoryType3List(String catcode,String subcatcode,String subcatcode1,String subcatcode2,String subcatcode3,String status,String searchname);

	ArrayList<LibraryStockEntryVO> getAccessionNoList();

	List<SubCategoryType2VO> getTabBySub2CategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2);

	List<SubCategoryType2VO> getTabBySub2subCategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2);

	List<SubCategoryType2VO> getTabBySub2subCategory1Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2);

	List<SubCategoryType1VO> searchSubCatType1(String searchname, String catcode, String subcatcode, String subcatcode1, String status);

	List<SubCategoryType2VO> getTableBySub2Status(String status, String categorycode, String subcategorycode, String subcategory1code, String subcategory2code);

	List<SubCategoryType2VO> getTabBySub2subCategory2Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2);


		ArrayList<CategoryTypeVO> getclassdescrlist(String cateid);

		ArrayList<CategoryTypeVO> getlibcategorysectionlist(String cateid,String classid);

		ArrayList<CategoryTypeVO> getlibcategorydivisionlist(String sectionid);

		String saveStockEnteryDetails(LibraryStockEntryDetailsForm libform);

	List<SubCategoryType2VO> searchSubCatType2(String searchname, String categorytype, String subcategorytype, String subcategorytype1, String subcategorytype2, String status);

	int validateCategoryType(CategoryTypeVO sub1);

	ArrayList<LibrarySearchSubscriberVO> getOthersListDetails(String location, String select, String academic_year);

	ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByAnyWhere(String searchTextVal,String location,String select, String accyear);

	ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByStartWith(String searchTextVal,String location,String select, String accyear);

	ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByEndsWith(String searchTextVal,String location, String select, String accyear);

	LibrarySubscribVO gotosubscribersDetails(String location, String subId, String academic_year,
			String subscriberType);

	String updateSubscriberDetails(LibrarySubscribVO resultData);


	LibrarySubscribVO IssueStatementBySubScriberType(String location, String subId, String academic_year, String subscriberType);

	ArrayList<LibrarySearchSubscriberVO> IssueStatementTable(String location, String subId, String academic_year,
			String subscriberType);

	LibrarySubscribVO issuestatementissue(String subId,String issueId,String subscriberType);


	boolean validateSubcategoryType(SubCategoryTypeVO sub);

	boolean validateSubcategoryType3(SubCategoryTypeVO sub);
	
	String activeSubCategoryType(String[] id, SubCategoryTypeVO vo);
	
	String activeSubCategoryType3(String[] id, SubCategoryTypeVO vo);


	List<LibraryStockEntryDetailsForm> getStockEntryBookList();

	LibraryStockEntryDetailsForm editStockEntryDetail(String id);

	String validateStockEnteryDetails(java.lang.String accno);



	java.lang.String insertBookReturnDetails(LibraryStockEntryVO insert_issue);

	List<LibraryStockEntryVO> getBookReturnDetailsByAccessionNo(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getAccessionNoByIssue(LibraryStockEntryVO registrationVo);





	ArrayList<CategoryTypeVO> getCategoryListBySearch(java.lang.String cateid, java.lang.String status,java.lang.String searchname);



	java.lang.String activeSubCategoryType1(java.lang.String[] id,
			SubCategoryType1VO vo);

	java.lang.String activeSubCategoryType2(java.lang.String[] id,
			SubCategoryType2VO vo);

	java.lang.String activeCategoryType(java.lang.String[] id, CategoryTypeVO vo);

	LibrarySubscribVO GOtOIssueReturns(String subId,String subscriberType, String issueId);

	java.lang.String publisherSettings(LibraryStockEntryVO obj);

	List<LibraryStockEntryVO> getPublisherSettingList();

	LibraryStockEntryVO editpublisherSetting(java.lang.String id);

	java.lang.String deletepublisherSetting(String[] deleteId);

	java.lang.String validationpubsettings(java.lang.String pub,
			java.lang.String address, java.lang.String email, String telphone, String mobilenum);
	
	ArrayList<ReportMenuVo> getLibraryLocation();

	
	java.lang.String TransferStudent(String[] subscriberId,
			java.lang.String locid);

	ArrayList<LibrarySearchSubscriberVO> getTranferStudentListDetails(java.lang.String academic_year, java.lang.String location,java.lang.String select, java.lang.String classname,java.lang.String sectionid, java.lang.String libloc);

	ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListBySection(java.lang.String academic_year, java.lang.String location,java.lang.String classname, java.lang.String sectionid,java.lang.String select, String liblocation);

	ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListByClassName(java.lang.String academic_year, java.lang.String location,java.lang.String classname, java.lang.String select,String liblocation);

	ArrayList<LibrarySearchSubscriberVO> getTransferStaffListDetails(java.lang.String accyear_ID, java.lang.String loc_ID,java.lang.String select, java.lang.String department,java.lang.String designation, String libloc);

	ArrayList<LibrarySearchSubscriberVO> getTransferOthersListDetails(String location,String select,String academic_year, String libloc);

	ArrayList<LibrarySearchSubscriberVO> getlocationStudentList(java.lang.String libloc, java.lang.String select);

	ArrayList<LibrarySearchSubscriberVO> getliblocationstafflist(String libloc, String select);

	ArrayList<LibrarySearchSubscriberVO> getliblocatinotherlist(java.lang.String academic_year, java.lang.String location,java.lang.String libloc, java.lang.String select);

	List<LibrarySearchSubscriberVO> TransferSubscriberbySearch(java.lang.String searchTextVal, java.lang.String location,java.lang.String academic_year, java.lang.String liblocid,java.lang.String select, String classname, String sectionid);

	ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyStaffSearch(java.lang.String searchTextVal, java.lang.String location,java.lang.String liblocid, java.lang.String select, String department, String designation,String accyear);

	ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyotherSearch(java.lang.String searchTextVal, java.lang.String location,java.lang.String select, java.lang.String liblocid, String accyear);

	java.lang.String addSupplierSettings(LibraryStockEntryVO obj);

	List<LibraryStockEntryVO> getSupplierSettingList();

	LibraryStockEntryVO editSupplierSetting(java.lang.String id);

	java.lang.String deleteSupplierSetting(java.lang.String[] deleteId);

	java.lang.String validationsubsettings(java.lang.String suplier,java.lang.String supadd, java.lang.String emailid, java.lang.String telephone, java.lang.String supnum);

	List<TeacherVo> othersSearchbyId(TeacherVo registrationVo);


	ArrayList<LibraryStockEntryVO> publisherDetailsSearch(java.lang.String searchTextVal, java.lang.String pub);

	ArrayList<LibraryStockEntryVO> SupplierDetailsSearch(java.lang.String searchTextVal, java.lang.String sup);


	List<LibraryStockEntryVO> getStudentIssueDetailsBySubscriberNo(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getTeacherIssueDetails(LibraryStockEntryVO libVo);

	java.lang.String insertBookReservationDetails(
			LibraryStockEntryVO insert_issue);

	List<LibraryStockEntryDetailsForm> getReservationListDetails();

	List<LibraryStockEntryVO> getOtherIssueDetails(LibraryStockEntryVO libVo);



	List<LibraryStockEntryVO> getBookReservationDetailsByAccNo(
			LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getAccessionList();

	List<LibraryStockEntryVO> getTeachSubscriberName(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getStuSubscriberName(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getStudentSubNo(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getTeacherSubNo(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getStuAccessionNo(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getTeachAccessionNo(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getOtherSubName(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getOtherSubNo(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getOtherAccessionNo(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getFromDateList(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getToDateList(LibraryStockEntryVO libVo);



	ArrayList<LibrarySearchSubscriberVO> IssueReturnTable(java.lang.String location, java.lang.String subId,
			java.lang.String academic_year, java.lang.String subscriberType);


	java.lang.String addGeneralSettings(LibraryStockEntryVO obj);

	List<LibraryStockEntryVO> getGenarelSettingList();

	LibraryStockEntryVO editGenarelSetting(java.lang.String id);

	java.lang.String editGenarelSetting(java.lang.String[] deleteId);

	ArrayList<LibraryStockEntryVO> GenarelDetailsSearch(java.lang.String searchTextVal);


	LibraryStockEntryVO editReservationBook(java.lang.String id);

	LibrarySubscribVO IssueReturnBySubScriberType(String location,String subId, String academic_year,String subscriberType);

	List<LibraryStockEntryVO> getcodeName(LibraryStockEntryVO registrationVo);

	ArrayList<LibraryVO> getcodeList();


	List<LibraryStockEntryVO> getCodeByCodeName(LibraryStockEntryVO libVo);

	java.lang.String updateBookReservationDetails(
			LibraryStockEntryVO insert_issue);

	java.lang.String deleteReservedBook(java.lang.String[] librarylocid);

	boolean validateReservedBook(LibraryStockEntryVO reserve);

	java.lang.String savejournalsubscriptiondetail(LibraryJournalSubscriptionVo obj);

	List<LibraryJournalSubscriptionVo> getJournalSubscriptionList();

	LibraryJournalSubscriptionVo editeLibraryJournalSubscription(
			java.lang.String id);

	java.lang.String deleteJournalSbscription(java.lang.String[] deleteId);

	List<LibraryJournalSubscriptionVo> getJournalSubscriptioncodelist();

	List<LibraryJournalSubscriptionVo> getnamelist();

	ArrayList<LibraryJournalSubscriptionVo> journalsubscriptionDetailsSearch(java.lang.String searchTextVal, String name);

	List<LibraryStockEntryVO> getstockEntryList(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId);

	List<LibraryStockEntryVO> getlocationlist(java.lang.String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId);

	List<LibraryStockEntryVO> getitemlistList();

	List<LibraryStockEntryVO> booklist();

	List<LibraryStockEntryVO> authorlist();
	
	ArrayList<LibrarySearchIssueDetailsVO> getSubscriberDetailStudentExcelReport(LibrarySearchIssueDetailsVO obj);


   List<LibraryStockEntryVO> getReservationListReport(String location, String accId, String subId, String accNo, String bookId, String fromdat, String todate, String date);

	ArrayList<LibrarySearchIssueDetailsVO> getStaffSubscriberDetailReport(LibrarySearchIssueDetailsVO obj);


	List<LibraryStockEntryDetailsForm> getReservationAccNo(String subtype, String accyear);

	List<LibraryStockEntryDetailsForm> getbooktitleList(String subtype, String accyear, String accNo);
	
	ArrayList<LibrarySearchIssueDetailsVO> getOtherSubscriberDetailReport(LibrarySearchIssueDetailsVO obj);


	List<LibraryStockEntryVO> getNewArrivalListReport(
			java.lang.String checkedVal, String fromdate, String toDate);

	ArrayList<LibrarySearchSubscriberVO> getTransferStaffdetailsByDepartment(LibrarySubsciberDetailsPojo pojo);



}
