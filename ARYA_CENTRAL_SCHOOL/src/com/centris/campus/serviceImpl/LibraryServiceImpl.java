package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.dao.LibraryDAO;
import com.centris.campus.daoImpl.LibraryDAOIMPL;
import com.centris.campus.forms.LibraryStockEntryDetailsForm;
import com.centris.campus.pojo.LibraryLocationPojo;
import com.centris.campus.pojo.LibrarySubsciberDetailsPojo;
import com.centris.campus.service.LibraryService;
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

public class LibraryServiceImpl implements LibraryService{
	private static final String String = null;

	@Override
	public String insertCategoryTypeDetail(CategoryTypeVO insert_categoryType) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertCategoryTypeDetail(insert_categoryType);
	}

	@Override
	public List<CategoryTypeVO> getCategoryDetails() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getCategoryDetails();
	}
	
	public List<StudentRegistrationVo> studentSearchbyadmissionNo(StudentRegistrationVo registrationVo,String locid) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.studentSearchbyadmissionNo(registrationVo,locid);
		
	}
	public List<CategoryTypeVO> getSubCategoryDetails() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryDetails();
	}
	
	public List<CategoryTypeVO> getSubCategoryDetails1() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryDetails1();
	}
	
	public List<CategoryTypeVO> getSubCategoryDetails(String cattype,String status) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryDetails(cattype,status);
	}

	@Override
	public List<TeacherVo> teacherSearchbyId(TeacherVo teacherVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.teacherSearchbyId(teacherVo);
		
	}


	@Override
	public CategoryTypeVO editCategoryType(String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editCategoryType(id);
	}
	
	public SubCategoryTypeVO editSubCategoryType(String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editSubCategoryType(id);
	}

	@Override
	public String inactiveCategoryType(String[] catIdlist) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.inactiveCategoryType(catIdlist);
		
	}
	
	@Override
	public String inactiveSubCategoryType(String[] id,SubCategoryTypeVO vo) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.inactiveSubCategoryType(id,vo);
		
	}
 

	public ArrayList<LibrarySubscribVO> getStaffData(String staffid,String locId) {
		return new LibraryDAOIMPL().getStaffData(staffid, locId);
	}
    public ArrayList<LibrarySubscribVO> getStudentData(String academicYear,String admissionNo) {
		return new LibraryDAOIMPL().getStudentData(academicYear, admissionNo);
    }



	@Override
	public String insertSubCategoryType1Detail(SubCategoryType1VO sub1) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertSubCategoryType1Detail(sub1);
	}

	@Override
	public List<SubCategoryType1VO> getSubCategoryType1Details() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryType1Details();
	}

	@Override
	public SubCategoryType1VO editSubCategoryType1(String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editSubCategoryType1(id);
	}

	@Override
	public List<SubCategoryTypeVO> getSubCategoryByCategory(String categoryCode) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryByCategory(categoryCode);
	}
	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getStudentIssuedList(String locid, String accyear) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStudentIssuedList(locid,accyear);
	}
	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStudentListDetails(String academic_year, String location,String select,String classname,String sectionid) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStudentListDetails(academic_year,location,select,classname,sectionid);
	}

	public String insertSubCategoryTypeDetail(SubCategoryTypeVO insert_SubcategoryType) {
		return new LibraryDAOIMPL().insertSubCategoryTypeDetail(insert_SubcategoryType);

	}
	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentClassList(
			String locid, String accyear, String classname) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getIssueStudentClassList(locid,accyear,classname);
	}
	


	public String saveSubscriberDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().saveSubscriberDetails(pojo);
	}


	@Override
	public String inactiveSubCategoryType1(String[] id) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.inactiveSubCategoryType1(id);
		
	}

	@Override
	public boolean validateSubcategoryType1(SubCategoryType1VO sub1) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.validateSubcategoryType1(sub1);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStudentListByClassName(String academic_year, String location,
			String classname,String sectionid,String select) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStudentListByClassName(academic_year,location,classname,sectionid,select);
	}

	public ArrayList<LibrarySubscribVO> getSubscriberDetailsListPage(String academicYear, String locId, String classId,String sectionName, String suscriberType, String department, String designation, String otherName) {
		return  new LibraryDAOIMPL().getSubscriberDetailsListPage( academicYear,  locId,  classId, sectionName,  suscriberType,department,designation,otherName);
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getIssueStudentSectionList(
			String locid, String accyear, String classname, String sectionnm) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getIssueStudentSectionList(locid,accyear, classname,sectionnm);
	}


	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStudentListBySection(String academic_year, String location,
			String classname, String sectionid,String select) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStudentListBySection(academic_year,location,classname,sectionid,select);
	}

	@Override
	public List<LibrarySearchSubscriberVO> searchsubscriberList(String searchTextVal,String academic_year, String location) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.searchsubscriberList(searchTextVal,academic_year,location);
	}

	

	public List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByEndsWith(String searchTextVal,String location,String select,
			java.lang.String academic_year,java.lang.String classname,java.lang.String sectionid) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchSubscriberDetailsByEndsWith(searchTextVal,location,select,academic_year,classname,sectionid);
	}



	public List<LibrarySearchIssueDetailsVO> getIssueDetailsByAnyWhere(String searchTextVal, String locid, String accyear) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getIssueDetailsByAnyWhere(searchTextVal,locid,accyear);
	}


	@Override
	public String insertLibraryLocations(LibraryLocationPojo insert_libLoc) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertLibraryLocations(insert_libLoc);
	}

	@Override
	public LibraryLocationVO editLibraryLocation(String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editLibraryLocation(id);	}

	@Override
	public String updateLibLocations(LibraryLocationPojo insert_libLoc) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.updateLibLocations(insert_libLoc);
	}

	@Override
	public ArrayList<LibraryLocationPojo> getLibLocationsDetails(String location) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getLibLocationsDetails(location);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStaffListDetails(String accyear_ID, String loc_ID,String select,String department,String designation) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStaffListDetails(accyear_ID,loc_ID,select,department,designation);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDepartment(String accyear_ID, String Loc_ID,
			String department,String designation) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStaffdetailsByDepartment(accyear_ID,Loc_ID,department,designation);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStaffdetailsByDesignation(String accyear_ID, String Loc_ID,
			String department, String designation) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStaffdetailsByDesignation (accyear_ID,Loc_ID, department,designation);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByAnyWhere(String searchTextVal,String location,String select,String department,
			String designation,String accyear) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchStaffDetailsByAnyWhere(searchTextVal,location,select,department,designation,accyear);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByStartWith(String searchTextVal,String location,String select,
			String department,String designation,String accyear) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchStaffDetailsByStartWith(searchTextVal,location,select,department,designation,accyear);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchStaffDetailsByEndsWith(String searchTextVal,String location,
			String select,String department,String designation,String accyear) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchStaffDetailsByEndsWith(searchTextVal,location,select,department,designation,accyear);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getStaffListFilterByLocationAndAcyearid(String accyear_ID,
			String loc_ID) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStaffListFilterByLocationAndAcyearid(accyear_ID,loc_ID);
	}

	

	public List<LibrarySearchIssueDetailsVO> getIssueDetailsByStartwith(String searchTextVal, String locid, String accyear, String selection) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getIssueDetailsByStartwith(searchTextVal,locid,accyear,selection);
	}


	@Override
	public ArrayList<LibraryLocationVO> getSchoolLocations(String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSchoolLocations(id);
	}


	@Override
	public List<SubCategoryType2VO> getSubCategoryType2Details() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryType2Details();
	}

	@Override
	public SubCategoryType2VO editSubCategoryType2(String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editSubCategoryType2(id);
	}

	@Override
	public List<SubCategoryType1VO> getSubCategory1ByCategoryAndSubCategory(String subCategoryTypeCode) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategory1ByCategoryAndSubCategory(subCategoryTypeCode);
	}

	@Override
	public String insertSubCategoryType2Detail(SubCategoryType2VO sub2) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertSubCategoryType2Detail(sub2);
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherList(String locid,String accyear) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeacherList( locid, accyear);
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherDeptList(String locid, String accyear, String dept) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeacherDeptList( locid, accyear,dept);
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getTeacherDesgList(String locid, String accyear, String dept, String desg) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeacherDesgList( locid, accyear,dept,desg);
	}

	@Override
	public List<LibrarySearchIssueDetailsVO> getIssueByStartwith(LibrarySearchIssueDetailsVO vo, String selection) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getIssueByStartwith( vo,selection);
	}

	@Override
	public boolean validateLibLocationUpdate(LibraryLocationVO lib) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.validateLibLocationUpdate(lib);
	}

	@Override
	public List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByAnyWhere(String searchTextVal,String location,String academic_year,String select,String classname, String sectionid) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchSubscriberDetailsByAnyWhere(searchTextVal,location,academic_year,select,classname,sectionid);
	}

	@Override
	public List<LibrarySearchSubscriberVO> SearchSubscriberDetailsByStartWith(String searchTextVal,String location,
			String select,String classname, String sectionid,String academic_year) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchSubscriberDetailsByStartWith(searchTextVal,location,select,classname,sectionid,academic_year);
	}

	@Override
	public String inactiveSubCategoryType2(String[] id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.inactiveSubCategoryType2(id);
	}

	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getOthersList(String locid,String accyear) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOthersList(locid,accyear);
	}

	@Override
	public List<LibrarySearchIssueDetailsVO> getIssueotherByStartwith(
			String searchTextVal, String locid, String accyear, String selection) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getIssueotherByStartwith( searchTextVal, locid,  accyear,selection);
	}

	@Override
	public List<CategoryTypeVO> getSubCategoryTypeName(String categoryName) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryTypeName(categoryName);
	}
	
	@Override
	public List<CategoryTypeVO> getSubCategoryList(String catcode,String subcatcode,String status) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryList(catcode,subcatcode,status);
	}
	
	@Override
	public List<CategoryTypeVO> getbystatusList(String catcode,String subcatcode,String status) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getbystatusList(catcode,subcatcode,status);
	}
	
	@Override
	public List<CategoryTypeVO> SearchCategoryTypeList(String catcode,String subcatcode,String status,String searchname) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchCategoryTypeList(catcode,subcatcode,status,searchname);
	}

	@Override
	public String insertSubCategoryTypeDetail3(SubCategoryTypeVO insert_SubcategoryType) {
		return new LibraryDAOIMPL().insertSubCategoryTypeDetail3(insert_SubcategoryType);

	}


	@Override
	public String deleteLibraryLocations(String[] librarylocid) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.deleteLibraryLocations(librarylocid);
	}


	@Override
	public List<SubCategoryType1VO> getTabByCategoryType(String cattype, String status, String subcacode, String subcacode1) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTabByCategoryType(cattype,status,subcacode,subcacode1);
	}

	@Override
	public List<SubCategoryType1VO> getTableBycategorytypeandSub(String cattype, String status, String category, String subcacode1) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTableBycategorytypeandSub(cattype,status,category,subcacode1);
	}

	@Override
	public List<SubCategoryType1VO> getTableBycategorytypeandSub1(String cattype, String status, String category, String subcacode) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTableBycategorytypeandSub1(cattype,status,category,subcacode);
	}

	@Override
	public List<SubCategoryType1VO> getTableByStatus(String status,String categorycode, String subcategorycode, String subcategory1code) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTableByStatus(status,categorycode,subcategorycode,subcategory1code);
	}

	@Override
	public List<SubCategoryType2VO> getTabBySub2CategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTabBySub2CategoryType(cattype,status,subcategory,subcategory1,subcategory2);
	}

	@Override
	public List<SubCategoryType2VO> getTabBySub2subCategoryType(String cattype, String status, String subcategory, String subcategory1, String subcategory2) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTabBySub2subCategoryType(cattype,status,subcategory,subcategory1,subcategory2);
	}

	@Override
	public List<SubCategoryType2VO> getTabBySub2subCategory1Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTabBySub2subCategory1Type(cattype,status,subcategory,subcategory1,subcategory2);
	}

	@Override
	public List<SubCategoryType1VO> searchSubCatType1(String searchname, String catcode, String subcatcode, String subcatcode1,String status) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.searchSubCatType1(searchname,catcode,subcatcode,subcatcode1,status);
	}

	@Override
	public List<SubCategoryType2VO> getTableBySub2Status(String cattype, String status, String subcategory, String subcategory1, String subcategory2) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTableBySub2Status(cattype,status,subcategory,subcategory1,subcategory2);
	}

	
	public List<SubCategoryTypeVO> getSubCategoryDetails3() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryDetails3();
	}

	public ArrayList<LibrarySubscribVO> getOtherSubscribeNunmber(String loc, String subscriberType) {
		return new  LibraryDAOIMPL().getOtherSubscribeNunmber(loc,subscriberType);
	}

	public ArrayList<LibrarySubscribVO> showBlockListedData(String loc,String subscriberType, String subscriberNo) {
		return new LibraryDAOIMPL().showBlockListedData(loc,subscriberType,subscriberNo);
	}

	@Override
	public ArrayList<CategoryTypeVO> getcategorylist(String cateid ,String status) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getcategorylist(cateid,status);
	}


	@Override
	public ArrayList<LibraryStockEntryVO> getAccessionNoList() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getAccessionNoList();
	}


	@Override
	public ArrayList<CategoryTypeVO> getclassdescrlist(String cateid) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao. getclassdescrlist(cateid);
	}

	@Override
	public ArrayList<CategoryTypeVO> getlibcategorysectionlist(String cateid,
			String classid) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao. getlibcategorysectionlist(cateid,classid);
	}

	@Override
	public ArrayList<CategoryTypeVO> getlibcategorydivisionlist(String sectionid) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao. getlibcategorydivisionlist(sectionid);
	}

	@Override
	public String saveStockEnteryDetails(LibraryStockEntryDetailsForm libform) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao. saveStockEnteryDetails(libform);
	}



	@Override
	public List<LibraryStockEntryVO> getAccessionNo(
			LibraryStockEntryVO registrationVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getAccessionNo(registrationVo);
		
	}

	@Override
	public List<LibraryStockEntryVO> getBookIssueDetailsByAccessionNo(
			LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getBookIssueDetailsByAccessionNo(libVo);
		
	}

	@Override
	public String insertBookIssueDetails(LibraryStockEntryVO insert_issue) {
		
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertBookIssueDetails(insert_issue);
	}

	
	public SubCategoryTypeVO editSubCategoryType3(String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editSubCategoryType3(id);
	}

	
	@Override
	public List<SubCategoryType1VO> getSubCategory2ByCategoryAndSubCategory(String subCategoryTypeCode) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategory2ByCategoryAndSubCategory(subCategoryTypeCode);
	}

	@Override
	public String inactiveSubCategoryType3(String[] id,SubCategoryTypeVO vo) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.inactiveSubCategoryType3(id,vo);		
	}
	
	@Override
	public List<CategoryTypeVO> getSubCategoryDetails3(String cattype,String subcatcode, String subcatcode1, String subcatcode2, String subcatcode3, String status) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryDetails3(cattype,subcatcode,subcatcode1,subcatcode2,subcatcode3,status);
	}
	
	@Override
	public List<CategoryTypeVO> getSubCategoryList3(String catcode,String subcatcode,String status) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategoryList3(catcode,subcatcode,status);
	}
 
	@Override
	public List<SubCategoryType1VO> getSubCategory3ByCategoryAndSubCategory(String subCategoryTypeCode) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubCategory3ByCategoryAndSubCategory(subCategoryTypeCode);
	}
	
	@Override
	public List<CategoryTypeVO> SearchCategoryType3List(String catcode,String subcatcode,String subcatcode1,String subcatcode2,String subcatcode3,String status,String searchname) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchCategoryType3List(catcode,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname);
	}
	
	@Override
	public String ValidateSubcat(String subname) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.ValidateSubcat(subname);
	}
	
	
	
	@Override
	public String ValidateSubcatupdate(String subname) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.ValidateSubcatupdate(subname);
	}
	
	@Override
	public String ValidateSubcat3(String subname) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.ValidateSubcat3(subname);
	}

	@Override
	public List<SubCategoryType2VO> getTabBySub2subCategory2Type(String cattype, String status, String subcategory, String subcategory1, String subcategory2) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTabBySub2subCategory2Type(cattype,status,subcategory,subcategory1,subcategory2);
	}

	@Override
	public List<SubCategoryType2VO> searchSubCatType2(
			String searchname, String categorytype, String subcategorytype, String subcategorytype1, String subcategorytype2,String status) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.searchSubCatType2(searchname,categorytype,subcategorytype,subcategorytype1,subcategorytype2,status);
	}
	
	@Override
	public ArrayList<LibrarySearchSubscriberVO> getOthersListDetails( String location,
			String select,String academic_year) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOthersListDetails(location,select,academic_year);
	}
	
	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByAnyWhere(String searchTextVal,String location,String select,String accyear) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchOthersDetailsByAnyWhere(searchTextVal,location,select,accyear);
	}
	
	@Override

	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByStartWith(String searchTextVal,String location,String select,String accyear) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchOthersDetailsByStartWith(searchTextVal,location,select,accyear);
	}
	
	@Override
	public ArrayList<LibrarySearchSubscriberVO> SearchOthersDetailsByEndsWith(String searchTextVal,String location,String select,String accyear) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SearchOthersDetailsByEndsWith(searchTextVal,location,select,accyear);
	}
	

	@Override
	public LibrarySubscribVO gotosubscribersDetails(String location, String subId, String academic_year,String subscriberType) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.gotosubscribersDetails(location,subId,academic_year,subscriberType);
	}
	
	@Override
	public String updateSubscriberDetails(LibrarySubscribVO resultData) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.updateSubscriberDetails(resultData);
	}

	public LibrarySubscribVO IssueStatementBySubScriberType(String location,String subId,String academic_year,String subscriberType) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.IssueStatementBySubScriberType(location,subId,academic_year,subscriberType);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> IssueStatementTable(String location, String subId, String academic_year,
			String subscriberType) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.IssueStatementTable(location,subId,academic_year,subscriberType);
	}

	@Override
	public LibrarySubscribVO issuestatementissue(String subId,String issueId,String subscriberType) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.issuestatementissue(subId,issueId,subscriberType);
	}
	@Override
	public List<LibraryStockEntryDetailsForm> getStockEntryBookList() {
	LibraryDAO dao=new LibraryDAOIMPL();
	return dao.getStockEntryBookList();
	}

	@Override
	public LibraryStockEntryDetailsForm editStockEntryDetail(String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editStockEntryDetail(id);
	}

	public String blockTheSubscriber(String subscriberNo) {
		return new LibraryDAOIMPL().blockTheSubscriber( subscriberNo);
	}

	public ArrayList<LibrarySubscribVO> getStaffRegId(String loc, java.lang.String searchterm) {
		return new LibraryDAOIMPL().getStaffRegId(loc,searchterm);
	}

	public String duplicateDataCheck(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().duplicateDataCheck(pojo);
	}
	
	@Override
	public String activeSubCategoryType(String[] id,SubCategoryTypeVO vo) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.activeSubCategoryType(id,vo);
		
	}
	
	@Override
	public String activeSubCategoryType3(String[] id,SubCategoryTypeVO vo) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.activeSubCategoryType3(id,vo);
		
	}

	public ArrayList<LibrarySubscribVO> getBlockListedStaffData(String accyear,String location) {
		return new LibraryDAOIMPL().getBlockListedStaffData( accyear, location);
	}

	public ArrayList<LibrarySubscribVO> getBlockListedStudentData(String accyear, String location) {
		return new LibraryDAOIMPL().getBlockListedStudentData( accyear,  location);
	}

	public ArrayList<LibrarySubscribVO> getBlockListedOtherData(String accyear,String location) {
		return new LibraryDAOIMPL().getBlockListedOtherData( accyear, location);
	}

	public String unblockSubscriber(String id) {
		return new LibraryDAOIMPL().unblockSubscriber(id);
	}

	@Override
	public java.lang.String validateStockEnteryDetails(java.lang.String accno) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.validateStockEnteryDetails(accno);
	}

	public ArrayList<CategoryTypeVO> getCategoryListBySearch(String cateid, String status, String searchname) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.getCategoryListBySearch(cateid,status,searchname);
	}

	@Override
	public java.lang.String insertBookReturnDetails(LibraryStockEntryVO insert_issue) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertBookReturnDetails(insert_issue);
	}

	@Override
	public List<LibraryStockEntryVO> getBookReturnDetailsByAccessionNo(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getBookReturnDetailsByAccessionNo(libVo);
	}

	@Override
	public List<LibraryStockEntryVO> getAccessionNoByIssue(LibraryStockEntryVO registrationVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getAccessionNoByIssue(registrationVo);
	}


	@Override
	public java.lang.String activeSubCategoryType1(java.lang.String[] id,
			SubCategoryType1VO vo) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.activeSubCategoryType1(id,vo);
		
	}

	@Override
	public java.lang.String activeSubCategoryType2(java.lang.String[] id,
			SubCategoryType2VO vo) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.activeSubCategoryType2(id,vo);
		
	}

	@Override
	public java.lang.String activeCategoryType(java.lang.String[] id,
			CategoryTypeVO vo) {
		LibraryDAO dao=new LibraryDAOIMPL();
	    return  dao.activeCategoryType(id,vo);
		
	}

	@Override
	public LibrarySubscribVO GOtOIssueReturns(String subId,String subscriberType,String issueId) {
		LibraryDAO dao=new LibraryDAOIMPL();
		  return  dao.GOtOIssueReturns(subId,subscriberType,issueId);
	}

	@Override
	public java.lang.String publisherSettings(LibraryStockEntryVO obj) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.publisherSettings(obj);
	}

	@Override
	public List<LibraryStockEntryVO> getPublisherSettingList() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getPublisherSettingList();	
		}

	@Override
	public LibraryStockEntryVO editpublisherSetting(java.lang.String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editpublisherSetting(id);
	}

	@Override
	public java.lang.String deletepublisherSetting(String deleteId[]) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.deletepublisherSetting(deleteId);
	}

	@Override
	public java.lang.String validationpubsettings(String pub, String address, String email, String telphone, String mobilenum) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.validationpubsettings(pub,address,email,telphone,mobilenum);
		
	}

	@Override
	public ArrayList<ReportMenuVo> getLibraryLocation() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getLibraryLocation();
	}

	@Override
	public java.lang.String TransferStudent(java.lang.String[] subscriberId,
			java.lang.String locid) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.TransferStudent(subscriberId,locid);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTranferStudentListDetails(java.lang.String academic_year, java.lang.String location,java.lang.String select, java.lang.String classname,java.lang.String sectionid, java.lang.String libloc) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTranferStudentListDetails(academic_year,location,select,classname,sectionid,libloc);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListBySection(java.lang.String academic_year, java.lang.String location,java.lang.String classname, java.lang.String sectionid,java.lang.String select,java.lang.String liblocation) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao. getTrasferStudentListBySection(academic_year,location,classname,sectionid,select,liblocation);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTrasferStudentListByClassName(java.lang.String academic_year, java.lang.String location,java.lang.String classname,java.lang.String select,java.lang.String liblocation) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao. getTrasferStudentListByClassName(academic_year,location,classname,select,liblocation);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffListDetails(java.lang.String accyear_ID, 
			java.lang.String loc_ID,java.lang.String select, java.lang.String department,java.lang.String designation,java.lang.String libloc) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTransferStaffListDetails(accyear_ID,loc_ID,select,department,designation,libloc);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO>getTransferOthersListDetails(String location, String select,String academic_year,String libloc) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTransferOthersListDetails(location,select,academic_year,libloc);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getlocationStudentList(
			java.lang.String libloc, java.lang.String select) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getlocationStudentList(libloc,select);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getliblocationstafflist(
			java.lang.String libloc, java.lang.String select) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getliblocationstafflist(libloc,select);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> getliblocatinotherlist(java.lang.String academic_year, java.lang.String location,java.lang.String libloc, java.lang.String select) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getliblocatinotherlist(academic_year,location,libloc,select);
	}

	@Override
	public List<LibrarySearchSubscriberVO> TransferSubscriberbySearch(java.lang.String searchTextVal, java.lang.String location,
			java.lang.String academic_year, java.lang.String liblocid,java.lang.String select,java.lang.String classname,java.lang.String sectionid) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.TransferSubscriberbySearch(searchTextVal,location,academic_year,liblocid,select,classname,sectionid);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyStaffSearch(java.lang.String searchTextVal, java.lang.String location,java.lang.String liblocid,java.lang.String select,java.lang.String department,
			java.lang.String designation,java.lang.String accyear) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.TransferSubscriberbyStaffSearch(searchTextVal,location,liblocid,select,department,designation,accyear);
	}

	@Override
	public ArrayList<LibrarySearchSubscriberVO> TransferSubscriberbyotherSearch(
			java.lang.String searchTextVal, java.lang.String location,java.lang.String accyear,
			java.lang.String select, java.lang.String liblocid) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.TransferSubscriberbyotherSearch(searchTextVal,location,select,liblocid,accyear);
	}

	@Override
	public java.lang.String addSupplierSettings(LibraryStockEntryVO obj) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.addSupplierSettings(obj);
	}

	@Override
	public List<LibraryStockEntryVO> getSupplierSettingList() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSupplierSettingList();
	}

	@Override
	public LibraryStockEntryVO editSupplierSetting(java.lang.String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editSupplierSetting(id);
	}

	@Override
	public java.lang.String deleteSupplierSetting(java.lang.String[] deleteId) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.deleteSupplierSetting(deleteId);
	}

	@Override
	public java.lang.String validationsubsettings(java.lang.String suplier,
			java.lang.String supadd, java.lang.String emailid, java.lang.String telephone, java.lang.String supnum) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.validationsubsettings(suplier,supadd,emailid,telephone,supnum);
	}

	@Override
	public List<TeacherVo> othersSearchbyId(TeacherVo registrationVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.othersSearchbyId(registrationVo);
	}


	@Override
	public ArrayList<LibraryStockEntryVO> publisherDetailsSearch(
			java.lang.String searchTextVal, java.lang.String pub) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.publisherDetailsSearch(searchTextVal,pub);
	}

	@Override
	public ArrayList<LibraryStockEntryVO> SupplierDetailsSearch(
			java.lang.String searchTextVal, java.lang.String sup) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.SupplierDetailsSearch(searchTextVal,sup);
	}


	public List<LibraryStockEntryVO> getStudentIssueDetailsBySubscriberNo(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStudentIssueDetailsBySubscriberNo(libVo);
	}

	@Override
	public List<LibraryStockEntryVO> getTeacherIssueDetails(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeacherIssueDetails(libVo);
	}

	@Override
	public java.lang.String insertBookReservationDetails(
			LibraryStockEntryVO insert_issue) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.insertBookReservationDetails(insert_issue);
	}

	@Override
	public List<LibraryStockEntryDetailsForm> getReservationListDetails() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getReservationListDetails();
	}
	@Override
	public List<LibraryStockEntryVO> getOtherIssueDetails(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOtherIssueDetails(libVo);
	}


	@Override
	public List<LibraryStockEntryVO> getBookReservationDetailsByAccNo(
			LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getBookReservationDetailsByAccNo(libVo);
	}

	@Override
	public List<LibraryStockEntryVO> getAccessionList() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getAccessionList();
	}

	@Override
	public List<LibraryStockEntryVO> getTeachSubscriberName(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeachSubscriberName(libVo);
	}

	@Override
	public List<LibraryStockEntryVO> getStuSubscriberName(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStuSubscriberName(libVo);
	}

	@Override
	public List<LibraryStockEntryVO> getStudentSubNo(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStudentSubNo(libVo);
	}

	@Override
	public List<LibraryStockEntryVO> getTeacherSubNo(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeacherSubNo(libVo);
	}
	@Override
	public List<LibraryStockEntryVO> getStuAccessionNo(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStuAccessionNo(libVo);
	}
	
	@Override
	public List<LibraryStockEntryVO> getTeachAccessionNo(
			LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getTeachAccessionNo(libVo);
	}

	@Override
	public List<LibraryStockEntryVO> getOtherSubName(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOtherSubName(libVo);
	}

	@Override
	public List<LibraryStockEntryVO> getOtherSubNo(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOtherSubNo(libVo);
	}

	@Override
	public List<LibraryStockEntryVO> getOtherAccessionNo(
			LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOtherAccessionNo(libVo);
	}

	@Override
	public List<LibraryStockEntryVO> getFromDateList(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getFromDateList(libVo);
	}

	@Override

	public List<LibraryStockEntryVO> getToDateList(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getToDateList(libVo);
	}


	@Override

	public ArrayList<LibrarySearchSubscriberVO> IssueReturnTable(java.lang.String location, java.lang.String subId,
			java.lang.String academic_year, java.lang.String subscriberType) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.IssueReturnTable(location,subId,academic_year,subscriberType);
	}

	@Override

	public java.lang.String addGeneralSettings(LibraryStockEntryVO obj) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.addGeneralSettings(obj);
	}

	@Override
	public List<LibraryStockEntryVO> getGenarelSettingList() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getGenarelSettingList();
	}

	@Override
	public LibraryStockEntryVO editGenarelSetting(java.lang.String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editGenarelSetting(id);
	}

	@Override
	public java.lang.String editGenarelSetting(java.lang.String[] deleteId) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editGenarelSetting(deleteId);
	}

	@Override
	public ArrayList<LibraryStockEntryVO> GenarelDetailsSearch(
			java.lang.String searchTextVal) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.GenarelDetailsSearch(searchTextVal);
	}
	
	public LibrarySubscribVO IssueReturnBySubScriberType(String location,String subId,String academic_year,String subscriberType) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.IssueReturnBySubScriberType(location,subId,academic_year,subscriberType);
	}

	@Override
	public LibraryStockEntryVO editReservationBook(java.lang.String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editReservationBook(id);
	}


	@Override
	public ArrayList<LibraryVO> getcodeList() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getcodeList();
	}

	@Override
	public List<LibraryStockEntryVO> getcodeName(LibraryStockEntryVO registrationVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getcodeName(registrationVo);
	}

	@Override
	public List<LibraryStockEntryVO> getCodeByCodeName(LibraryStockEntryVO libVo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getCodeByCodeName(libVo);
	}

	@Override
	public java.lang.String updateBookReservationDetails(
			LibraryStockEntryVO insert_issue) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.updateBookReservationDetails(insert_issue);
	}

	@Override
	public java.lang.String deleteReservedBook(java.lang.String[] librarylocid) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.deleteReservedBook(librarylocid);
	}

	@Override
	public boolean validateReservedBook(LibraryStockEntryVO reserve) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.validateReservedBook(reserve);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListDetails(String academic_year, String location,
			String select, String classname, String sectionid) {
		return new LibraryDAOIMPL().getMostWantedStudentListDetails(academic_year,location,select,classname,sectionid);
	}

	public List<LibrarySearchSubscriberVO> SearchMostWantedStudentDetailsByAnyWhere(String searchTextVal,String location,
			String academic_year, String select, String startwith, java.lang.String classname, java.lang.String sectionid) {
		return new LibraryDAOIMPL().SearchMostWantedStudentDetailsByAnyWhere(searchTextVal,location,academic_year,select,startwith,classname,sectionid);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListByClassName(String academic_year,
			String location, String classname, String sectionid, String select) {
		return new LibraryDAOIMPL().getMostWantedStudentListByClassName(academic_year,location,classname,sectionid,select);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStudentListBySection(String academic_year, String location,
			String classname, String sectionid, String select) {
		return new LibraryDAOIMPL().getMostWantedStudentListBySection(academic_year,location,classname,sectionid,select);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffListDetails(String academic_year, String location,
			String select, String department, String designation) {
		return new LibraryDAOIMPL().getMostWantedStaffListDetails(academic_year,location,select,department,designation);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchMostWantedStaffDetailsByAnyWhere(String searchTextVal,
			String location, String select, String startwith) {
		return new LibraryDAOIMPL().SearchMostWantedStaffDetailsByAnyWhere(searchTextVal,location,select,startwith);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffdetailsByDepartment(String accyear_ID, String loc_ID,
			String department, String designation, java.lang.String select) {
		return new LibraryDAOIMPL().getMostWantedStaffdetailsByDepartment(accyear_ID,loc_ID,department,designation,select);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedStaffdetailsByDesignation(String accyear_ID, String loc_ID,
			String department, String designation, String select) {
		return new LibraryDAOIMPL().getMostWantedStaffdetailsByDesignation(accyear_ID,loc_ID,department,designation,select);
	}

	public ArrayList<LibrarySearchSubscriberVO> getMostWantedOthersListDetails(String location, String select,
			String academic_year) {
		return new LibraryDAOIMPL().getMostWantedOthersListDetails(location,select,academic_year);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchMostWantedOthersDetailsByAnyWhere(String searchTextVal,
			String location, String select, String startwith){
		return new LibraryDAOIMPL().SearchMostWantedOthersDetailsByAnyWhere(searchTextVal,location,select,startwith);
	}
	@Override
	public java.lang.String savejournalsubscriptiondetail(
			LibraryJournalSubscriptionVo obj) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.savejournalsubscriptiondetail(obj);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getAllBookDetails(pojo);
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByaccNoandTitleAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().SearchBookSearchByaccNoandTitleAnyWhere(pojo);
	}


	@Override
	public List<LibraryJournalSubscriptionVo> getJournalSubscriptionList() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getJournalSubscriptionList();
	}

	@Override
	public LibraryJournalSubscriptionVo editeLibraryJournalSubscription(
			java.lang.String id) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.editeLibraryJournalSubscription(id);
	}

	@Override
	public java.lang.String deleteJournalSbscription(java.lang.String[] deleteId) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.deleteJournalSbscription(deleteId);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookPublisherDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getAllBookPublisherDetails(pojo);
	}
	@Override
	public List<LibraryJournalSubscriptionVo> getJournalSubscriptioncodelist() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getJournalSubscriptioncodelist();
	}

	@Override
	public List<LibraryJournalSubscriptionVo> getnamelist() {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getnamelist();
	}

	@Override
	public ArrayList<LibraryJournalSubscriptionVo> journalsubscriptionDetailsSearch(
			java.lang.String searchTextVal,java.lang.String name) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.journalsubscriptionDetailsSearch(searchTextVal,name);
	}

	@Override
	public List<LibraryStockEntryVO> getstockEntryList(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId) {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.getstockEntryList(locId,itemId,regdateId,booktitle,authorId,pubId);
	}

	@Override
	public List<LibraryStockEntryVO> getlocationlist(String locId, String itemId, String regdateId, String booktitle, String authorId, String pubId) {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.getlocationlist(locId,itemId,regdateId,booktitle,authorId,pubId);
	}

	@Override
	public List<LibraryStockEntryVO> getitemlistList() {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.getitemlistList();
	}

	@Override
	public List<LibraryStockEntryVO> booklist() {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.booklist();
	}

	@Override
	public List<LibraryStockEntryVO> authorlist() {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.authorlist();
	}
	
	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getSubscriberDetailStudentExcelReport(
			LibrarySearchIssueDetailsVO obj) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getSubscriberDetailStudentExcelReport(obj);
	}
	
	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getStaffSubscriberDetailReport(
			LibrarySearchIssueDetailsVO obj) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getStaffSubscriberDetailReport(obj);
	}


	@Override
	public List<LibraryStockEntryVO> getReservationListReport(String location, String accId, String subId, String accNo, String bookId, String fromdat, String todate,String date) {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.getReservationListReport(location,accId,subId,accNo,bookId,fromdat,todate,date);
	}

	@Override
	public List<LibraryStockEntryDetailsForm> getReservationAccNo(String subtype, String accyear) {
		LibraryDAO dao=new LibraryDAOIMPL();	
		return dao.getReservationAccNo(subtype,accyear);
	}

	@Override
	public List<LibraryStockEntryDetailsForm> getbooktitleList(String subtype, String accyear, String accNo) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getbooktitleList(subtype,accyear,accNo);
	}
	
	@Override
	public ArrayList<LibrarySearchIssueDetailsVO> getOtherSubscriberDetailReport(
			LibrarySearchIssueDetailsVO obj) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getOtherSubscriberDetailReport(obj);
	}

	@Override
	public List<LibraryStockEntryVO> getNewArrivalListReport(
			java.lang.String checkedVal,java.lang.String fromdate,java.lang.String toDate) {
		LibraryDAO dao=new LibraryDAOIMPL();
		return dao.getNewArrivalListReport(checkedVal,fromdate,toDate);
	}

	
	public List<LibrarySearchSubscriberVO> SearchBookSearchByPublisherAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().SearchBookSearchByPublisherAnyWhere(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookItemTypeDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getAllBookItemTypeDetails(pojo);
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByItemTypeAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().SearchBookSearchByItemTypeAnyWhere(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookDDCDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getAllBookDDCDetails(pojo);
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByDDCAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().SearchBookSearchByDDCAnyWhere(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookContentDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getAllBookContentDetails(pojo);
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByContentAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().SearchBookSearchByContentAnyWhere(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookLanguageDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getAllBookLanguageDetails(pojo);
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchByLanguageAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().SearchBookSearchByLanguageAnyWhere(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getAllBookSupplierDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getAllBookSupplierDetails(pojo);
	}

	public List<LibrarySearchSubscriberVO> SearchBookSearchBySupplierAnyWhere(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().SearchBookSearchBySupplierAnyWhere(pojo);
	}

	public LibraryStockEntryDetailsForm gotostockDetails(String id) {
		return new LibraryDAOIMPL().gotostockDetails(id);
	}

	public LibrarySubscribVO IssueStatementByStockEntryId(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().IssueStatementByStockEntryId(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> IssueStatementTableByStockEntryId(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().IssueStatementTableByStockEntryId(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> ReturnStatementTableByStockEntryId(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().ReturnStatementTableByStockEntryId(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getOverDueStatement(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getOverDueStatement(pojo);
	}

	public List<LibrarySubscribVO> getClassByLibraryLocation(String locationid) {
		return new LibraryDAOIMPL().getClassByLibraryLocation(locationid);
	}

	public List<LibrarySubscribVO> getLibraryClassSection(String classidVal) {
		return new LibraryDAOIMPL().getLibraryClassSection(classidVal);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentOverDueStatementByClass(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getStudentOverDueStatementByClass(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getStudentOverDueStatementBySection(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getStudentOverDueStatementBySection(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> SearchOverDueStudentDetailsByAnyWhere(
			LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().SearchOverDueStudentDetailsByAnyWhere(pojo);
	}

	public ArrayList<LibraryStockEntryDetailsForm> getStudentOverDueByOrderwise(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getStudentOverDueByOrderwise(pojo);
	}

	public List<LibrarySearchIssueDetailsVO> getAllOverDueListDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getAllOverDueListDetails(pojo);
	}

	public ArrayList<LibrarySubsciberDetailsPojo> getAllIssueReturnDetails(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getAllIssueReturnDetails(pojo);
	}

	public List<LibrarySubsciberDetailsPojo> IndividualSearchInIssueReturnStatement(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().IndividualSearchInIssueReturnStatement(pojo);
	}

	public List<LibraryStockEntryVO> getBookIssueReturnDetailsByAccessionNo(LibraryStockEntryVO libVo) {
		return new LibraryDAOIMPL().getBookIssueReturnDetailsByAccessionNo(libVo);
	}

	@Override
	public ArrayList<LibraryStockEntryVO> getJournalNameList(
			java.lang.String accyear) {
		return new LibraryDAOIMPL().getJournalNameList(accyear);
	}

	@Override  
	public List<LibraryJournalSubscriptionVo> getJournalListReport(
			java.lang.String checkedVal, java.lang.String fromdate,
			java.lang.String toDate,String accyear,String journalName) {
		return new LibraryDAOIMPL().getJournalListReport(checkedVal,fromdate,toDate,accyear,journalName);
	}
	
	public ArrayList<LibrarySearchSubscriberVO> getAllBookDetailsDownloadandPrint(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getAllBookDetailsDownloadandPrint(pojo);
	}

	public List<LibraryStockEntryVO> getIssueReturnAccessionNo(LibraryStockEntryVO registrationVo) {
		return new LibraryDAOIMPL().getIssueReturnAccessionNo(registrationVo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffdetailsByDepartment(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getTransferStaffdetailsByDepartment(pojo);
	}

	public ArrayList<LibrarySearchSubscriberVO> getTransferStaffdetailsByDesignation(LibrarySubsciberDetailsPojo pojo) {
		return new LibraryDAOIMPL().getTransferStaffdetailsByDesignation(pojo);
	}

	public List<LibraryStockEntryVO> getAccessionNoByIssueStatus(
			LibraryStockEntryVO registrationVo) {
		return new LibraryDAOIMPL().getAccessionNoByIssueStatus(registrationVo);
	}
	
}
	

