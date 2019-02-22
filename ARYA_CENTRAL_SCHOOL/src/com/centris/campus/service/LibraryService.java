package com.centris.campus.service;

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

public interface LibraryService {

	String insertCategoryTypeDetail(CategoryTypeVO insert_categoryType);

	List<CategoryTypeVO> getCategoryDetails();

	List<StudentRegistrationVo> studentSearchbyadmissionNo(StudentRegistrationVo registrationVo, String locid);

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

	String insertSubCategoryTypeDetail(SubCategoryTypeVO insert_SubcategoryType);

	String inactiveSubCategoryType(String[] id, SubCategoryTypeVO vo);



	ArrayList<LibrarySearchSubscriberVO> getStudentListBySection(String academic_year, String location,
			String classname, String sectionid, String select);

	List<LibrarySearchSubscriberVO> searchsubscriberList(String searchTextVal, String academic_year, String location);


	ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentSectionList(String locid, String accyear, String classname, String sectionnm);

	LibraryLocationVO editLibraryLocation(String id);

	String updateLibLocations(LibraryLocationPojo insert_libLoc);

	String insertLibraryLocations(LibraryLocationPojo insert_libLoc);

	ArrayList<LibraryLocationPojo> getLibLocationsDetails(String location);

	ArrayList<LibrarySearchSubscriberVO> getStaffListDetails(String accyear_ID, String loc_ID, String select, String department, String designation);

	ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDepartment(String accyear_ID, String Loc_ID, String department,
			String designation);

	ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDesignation(String accyear_ID, String loc_ID, String department,
		String designation);

	ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByAnyWhere(String searchTextVal,String location,String select,String department,String designation,String accyear);

	ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByStartWith(String searchTextVal,String location,String select, String department, String designation, String accyear);

	ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByEndsWith(String searchTextVal,String location,String select, String department, String designation, String accyear);
	

	ArrayList<LibrarySearchSubscriberVO> getStaffListFilterByLocationAndAcyearid(String accyear_ID, String loc_ID);

	List<SubCategoryType1VO> getSubCategory1ByCategoryAndSubCategory(String subCategoryTypeCode);

	String insertSubCategoryType2Detail(SubCategoryType2VO sub2);

	String deleteLibraryLocations(String[] librarylocid);

	List<SubCategoryType2VO> getSubCategoryType2Details();

	SubCategoryType2VO editSubCategoryType2(String id);

	ArrayList<LibraryLocationVO> getSchoolLocations(String id);

	List<CategoryTypeVO> getSubCategoryDetails();
	
	List<CategoryTypeVO> getSubCategoryDetails1();
	
	
	List<CategoryTypeVO> getSubCategoryDetails(String cattype,String status);


	ArrayList<LibrarySearchIssueDetailsVO> getTeacherList(String locid,String accyear);

	ArrayList<LibrarySearchIssueDetailsVO> getTeacherDeptList(String locid,String accyear, String dept);

	ArrayList<LibrarySearchIssueDetailsVO> getTeacherDesgList(String locid,String accyear, String dept, String desg);

	List<LibrarySearchIssueDetailsVO> getIssueByStartwith(LibrarySearchIssueDetailsVO vo, String selection);


	boolean validateLibLocationUpdate(LibraryLocationVO lib);

	List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByAnyWhere(String searchTextVal,String location, String academic_year, String select,String classname, String sectionid);

	List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByStartWith(String searchTextVal, String location, String select,String classname,String sectionid, String academic_year);

	String inactiveSubCategoryType2(String[] id);


	ArrayList<LibrarySearchIssueDetailsVO> getOthersList(String locid,String accyear);

	List<LibrarySearchIssueDetailsVO> getIssueotherByStartwith(	String searchTextVal, String locid, String accyear, String selection);


	List<CategoryTypeVO> getSubCategoryTypeName(String categoryName);
	
	List<CategoryTypeVO> getSubCategoryList(String catcode,String subcatcode,String status);
	
	List<CategoryTypeVO> getbystatusList(String catcode,String subcatcode,String status) ;


	List<CategoryTypeVO> SearchCategoryTypeList(String catcode,String subcatcode,String status,String searchname) ;


	List<SubCategoryType1VO> getTabByCategoryType(String cattype, String status, String subcacode, String subcacode1);

	List<SubCategoryType1VO> getTableBycategorytypeandSub(String cattype, String status, String category, String subcacode1);

	String insertSubCategoryTypeDetail3(SubCategoryTypeVO insert_SubcategoryType);
	
	List<SubCategoryTypeVO> getSubCategoryDetails3();
	
	SubCategoryTypeVO editSubCategoryType3(String id);

	List<SubCategoryType1VO> getSubCategory2ByCategoryAndSubCategory(String subCategoryTypeCode);

	String inactiveSubCategoryType3(String[] id, SubCategoryTypeVO vo);
	
	List<CategoryTypeVO> getSubCategoryDetails3(String cattype,String subcatcode, String subcatcode1, String subcatcode2, String subcatcode3, String status);

	List<CategoryTypeVO> getSubCategoryList3(String catcode,String subcatcode,String status);

	ArrayList<CategoryTypeVO> getcategorylist(String cateid,String status);


	List<SubCategoryType1VO> getTableBycategorytypeandSub1(String cattype, String status, String category, String subcacode);

	List<SubCategoryType1VO> getTableByStatus(String status, String categorycode, String subcategorycode, String subcategory1code);

	List<SubCategoryType2VO> getTabBySub2CategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2);

	List<SubCategoryType2VO> getTabBySub2subCategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2);

	List<SubCategoryType2VO> getTabBySub2subCategory1Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2);

	List<SubCategoryType1VO> searchSubCatType1(String searchname, String catcode, String subcatcode, String subcatcode1, String status);

	List<SubCategoryType2VO> getTableBySub2Status(String status, String categorycode, String subcategorycode, String subcategory1code, String subcategory2code);

	ArrayList<LibraryStockEntryVO> getAccessionNoList();

	ArrayList<CategoryTypeVO> getclassdescrlist(String cateid);

	ArrayList<CategoryTypeVO> getlibcategorysectionlist(String cateid,String classid);

	ArrayList<CategoryTypeVO> getlibcategorydivisionlist(String sectionid);


	String saveStockEnteryDetails(LibraryStockEntryDetailsForm libform);
	

	List<LibraryStockEntryVO> getAccessionNo(LibraryStockEntryVO registrationVo);

	List<LibraryStockEntryVO> getBookIssueDetailsByAccessionNo(
			LibraryStockEntryVO libVo);

	List<SubCategoryType1VO> getSubCategory3ByCategoryAndSubCategory(String subCategoryTypeCode);
	
	List<CategoryTypeVO> SearchCategoryType3List(String catcode,String subcatcode,String subcatcode1,String subcatcode2,String subcatcode3,String status,String searchname) ;

	String ValidateSubcat(String subname);

	
	String ValidateSubcatupdate(String subname);


	String ValidateSubcat3(String subname);





	String insertBookIssueDetails(LibraryStockEntryVO insert_issue);




	List<SubCategoryType2VO> getTabBySub2subCategory2Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2);


	List<SubCategoryType2VO> searchSubCatType2(String searchname, String categorytype, String subcategorytype, String subcategorytype1, String subcategorytype2, String status);

	ArrayList<LibrarySearchSubscriberVO> getOthersListDetails(String location, String select, String academic_year);

	ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByAnyWhere(String searchTextVal,String location, String select, String accyear);

	ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByStartWith(String searchTextVal,String location,String select,String accyear);

	ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByEndsWith(String searchTextVal,String location, String select,String accyear);

	LibrarySubscribVO gotosubscribersDetails(String location, String subId, String academic_year,
			String subscriberType);

	String updateSubscriberDetails(LibrarySubscribVO resultData);
	
	String activeSubCategoryType(String[] id, SubCategoryTypeVO vo);
	
	String activeSubCategoryType3(String[] id, SubCategoryTypeVO vo);



	LibrarySubscribVO IssueStatementBySubScriberType(String location, String subId, String academic_year,String subscriberType);

	ArrayList<LibrarySearchSubscriberVO> IssueStatementTable(String location, String subId, String academic_year,
			String subscriberType);


	LibrarySubscribVO issuestatementissue(String subId,String issueId,String subscriberType);

	String validateStockEnteryDetails(String accno);

	String activeSubCategoryType1(String[] id, SubCategoryType1VO vo);

	String activeSubCategoryType2(String[] id, SubCategoryType2VO vo);

	String activeCategoryType(String[] id, CategoryTypeVO vo);






	List<LibraryStockEntryDetailsForm> getStockEntryBookList();

	LibraryStockEntryDetailsForm editStockEntryDetail(String id);

	String insertBookReturnDetails(LibraryStockEntryVO insert_issue);

	List<LibraryStockEntryVO> getBookReturnDetailsByAccessionNo(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getAccessionNoByIssue(LibraryStockEntryVO registrationVo);




	LibrarySubscribVO GOtOIssueReturns(String subId, String subscriberType, String issueId);


	String publisherSettings(LibraryStockEntryVO obj);

	List<LibraryStockEntryVO> getPublisherSettingList();

	LibraryStockEntryVO editpublisherSetting(String id);

	String deletepublisherSetting(String[] deleteId);

	String validationpubsettings(String pub, String address, String email, String telphone, String mobilenum);

	ArrayList<ReportMenuVo> getLibraryLocation();
	String TransferStudent(String[] subscriberId, String locid);

	ArrayList<LibrarySearchSubscriberVO> getTranferStudentListDetails(String academic_year, String location, String select,String classname, String sectionid, String libloc);

	ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListBySection(String academic_year, String location, String classname,String sectionid, String select, String liblocation);

	ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListByClassName(String academic_year, String location, String classname,String select,String liblocation);

	ArrayList<LibrarySearchSubscriberVO> getTransferStaffListDetails(String accyear_ID, String loc_ID, String select, String department,String designation,String libloc);

	ArrayList<LibrarySearchSubscriberVO> getTransferOthersListDetails(String location, String select,String academic_year, String libloc);

	ArrayList<LibrarySearchSubscriberVO> getlocationStudentList(String libloc,String select);

	ArrayList<LibrarySearchSubscriberVO> getliblocationstafflist(String libloc,String select);

	ArrayList<LibrarySearchSubscriberVO> getliblocatinotherlist(String academic_year, String location, String libloc, String select);

	List<LibrarySearchSubscriberVO> TransferSubscriberbySearch(String searchTextVal, String location, String academic_year,String liblocid, String select,String classname,String sectionid);

	ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyStaffSearch(String searchTextVal, String location, String liblocid,String select, String department, String designation, String accyear);

	ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyotherSearch(String searchTextVal, String location, String select,String liblocid, String accyear);

	String addSupplierSettings(LibraryStockEntryVO obj);

	List<LibraryStockEntryVO> getSupplierSettingList();

	LibraryStockEntryVO editSupplierSetting(String id);

	String deleteSupplierSetting(String[] deleteId);

	String validationsubsettings(String suplier, String supadd,String emailid,String telephone,String supnum );
	
	List<TeacherVo> othersSearchbyId(TeacherVo registrationVo);


	ArrayList<LibraryStockEntryVO> publisherDetailsSearch(String searchTextVal,String pub);

	ArrayList<LibraryStockEntryVO> SupplierDetailsSearch(String searchTextVal,String sup);


	List<LibraryStockEntryVO> getStudentIssueDetailsBySubscriberNo(LibraryStockEntryVO libVo);

	List<LibraryStockEntryVO> getTeacherIssueDetails(LibraryStockEntryVO libVo);

	String insertBookReservationDetails(LibraryStockEntryVO insert_issue);

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



	ArrayList<LibrarySearchSubscriberVO> IssueReturnTable(String location, String subId, String academic_year,
			String subscriberType);


	String addGeneralSettings(LibraryStockEntryVO obj);


	List<LibraryStockEntryVO> getGenarelSettingList();

	LibraryStockEntryVO editGenarelSetting(String id);

	String editGenarelSetting(String[] deleteId);

	ArrayList<LibraryStockEntryVO> GenarelDetailsSearch(String searchTextVal);


	LibraryStockEntryVO editReservationBook(String id);


	LibrarySubscribVO IssueReturnBySubScriberType(String location, String subId, String academic_year,
			String subscriberType);

	ArrayList<LibraryVO> getcodeList();

	List<LibraryStockEntryVO> getcodeName(LibraryStockEntryVO registrationVo);

	List<LibraryStockEntryVO> getCodeByCodeName(LibraryStockEntryVO libVo);

	String updateBookReservationDetails(LibraryStockEntryVO insert_issue);

	String deleteReservedBook(String[] librarylocid);

	boolean validateReservedBook(LibraryStockEntryVO reserve);

	String savejournalsubscriptiondetail(LibraryJournalSubscriptionVo obj);

	List<LibraryJournalSubscriptionVo> getJournalSubscriptionList();

	LibraryJournalSubscriptionVo editeLibraryJournalSubscription(String id);

	String deleteJournalSbscription(String[] deleteId);

	List<LibraryJournalSubscriptionVo> getJournalSubscriptioncodelist();

	List<LibraryJournalSubscriptionVo> getnamelist();

	ArrayList<LibraryJournalSubscriptionVo> journalsubscriptionDetailsSearch(String searchTextVal, String name);

	List<LibraryStockEntryVO> getstockEntryList(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId);

	List<LibraryStockEntryVO> getlocationlist(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId);

	List<LibraryStockEntryVO> getitemlistList();

	List<LibraryStockEntryVO> booklist();

	List<LibraryStockEntryVO> authorlist();
	
	ArrayList<LibrarySearchIssueDetailsVO> getSubscriberDetailStudentExcelReport(LibrarySearchIssueDetailsVO obj);


	ArrayList<LibrarySearchIssueDetailsVO> getStaffSubscriberDetailReport(LibrarySearchIssueDetailsVO obj);




	List<LibraryStockEntryDetailsForm> getReservationAccNo(String subtype, String accyear);

	List<LibraryStockEntryDetailsForm> getbooktitleList(String subtype, String accyear, String accNo);
	
	ArrayList<LibrarySearchIssueDetailsVO> getOtherSubscriberDetailReport(LibrarySearchIssueDetailsVO obj);


	List<LibraryStockEntryVO> getNewArrivalListReport(String checkedVal, String fromdate, String toDate);

	List<LibraryStockEntryVO> getReservationListReport(String location,String accId, String subId, String accNo, String bookId,String fromdat, String todate, String date);

	ArrayList<LibraryStockEntryVO> getJournalNameList(String accyear);

	List<LibraryJournalSubscriptionVo> getJournalListReport(String checkedVal,
			String fromdate, String toDate, String accyear, String journalName);


	
}
