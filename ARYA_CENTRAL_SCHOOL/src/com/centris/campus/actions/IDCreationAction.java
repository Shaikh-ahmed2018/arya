package com.centris.campus.actions;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.StudentIDDaoImpl;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentIDBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.forms.InventoryTransactionForm;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentIDVo;
import com.centris.campus.vo.StudentInfoVO;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONObject;



public class IDCreationAction extends DispatchAction{
	
	static ResourceBundle res=ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName=res.getString("ImageName");
	private static String IdTemplate=res.getString("IdTemplate1");
	
	
	private static final Logger logger =Logger.getLogger(IDCreationAction.class);
	
	public ActionForward StudentIDFilter(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : StudentIDFilter Starting");
		try{
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream();

			request.setAttribute("AccYearList", accYearList);

			System.out.println("streamList ::: " + streamList.size());

			request.setAttribute("StreamList", streamList);
			
			
		}catch(Exception e){
			
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : StudentIDFilter Ending");
		
		
		
		return mapping.findForward(MessageConstants.STUDENTID);
	}
	
	
	

	
	public ActionForward getStudentDetailsReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			System.out.println();
			ReportMenuForm reform = (ReportMenuForm) form;

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears();

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
					.getStream();

			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("StreamList", streamList);

			ArrayList<StudentInfoVO> studentInfoList = new ReportsMenuBD()
					.getStudentDetailsReport(reform);

			ReportMenuVo selectedItems = new ReportsMenuBD()
					.getSelectedItems(reform);

			request.setAttribute("StudentInfoList", studentInfoList);
			request.setAttribute("CurrentForm", selectedItems);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Ending");

		return mapping.findForward(MessageConstants.STUDENTID);
	}
	
	
	
	
	
	public ActionForward studentIDPDFReport(ActionMapping mapping,ActionForm from,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Starting");
		
		String accyear=request.getParameter("AccId");
		String section= request.getParameter("Section");
		String className = request.getParameter("Class");
		String student=request.getParameter("studentname");
		String studentSize[]=student.split(",");
		System.out.println("accyear"+accyear);
		System.out.println("section"+section);
		System.out.println("className"+className);
		System.out.println("student"+student);
		
		try{
			
			List<StudentIDVo> studentList = new StudentIDBD().getstudentIDPDFReport(accyear,section,className,student);
			
			
			
			List<StudentIDVo> list=new ArrayList<StudentIDVo>();
			
			for(int i=0;i<studentList.size();i++){
				
				StudentIDVo vo=new StudentIDVo();
				String fileName=studentList.get(i).getImage();
				System.out.println("fileName-------------"+ i +fileName);
				String filePath = getServlet().getServletContext()
						.getRealPath("/") + "FIles\\STUDENTIMAGES\\"+fileName.trim();
				System.out.println("filePath"+ i +filePath);
				vo.setImages(filePath);
				
				vo.setStuName(studentList.get(i).getStuName());
				vo.setClassName(studentList.get(i).getClassName()+"&"+studentList.get(i).getSection());
				vo.setSection(studentList.get(i).getSection());
				vo.setFatherName(studentList.get(i).getFatherName());
				vo.setAdress(studentList.get(i).getAdress());
				vo.setPhone(studentList.get(i).getPhone());
				vo.setSecodaryPhone(studentList.get(i).getSecodaryPhone());
				vo.setValidity(studentList.get(i).getValidity());
				vo.setAdmissionno(studentList.get(i).getAdmissionno());
				vo.setMotherName(studentList.get(i).getMotherName());
				list.add(vo);
				
			}
			
			
			request.setAttribute("StudentList", list);

			
			String IDtemplate1=getServlet().getServletContext()
					.getRealPath("/") + "\\images\\IDCardTemplates\\"+IdTemplate.trim();
			
			//System.out.println(PropfilePath);
			
			Map mapdata = new HashMap();
			
			//mapdata.put("image", filePath);
			mapdata.put("IDtemplate", IDtemplate1);
			
			
			String sourceFileName = request
					.getRealPath("Reports/StudentID.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, beanColDataSource);
			
			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentID - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		
		
		
		}
		catch(Exception e){
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");
		return null;
	}
	
	public ActionForward NewstaffIdCardDesignList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Starting");
		boolean bool = false;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		List<PageFilterVo> newlist=new ArrayList<PageFilterVo>();
		
		try {
			String department=request.getParameter("department");
			if(department.equalsIgnoreCase("all")){
				department="%%";
			}
			String locationId=request.getParameter("locationId");
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			String academicYear=request.getParameter("academicYear");
			if(academicYear.equalsIgnoreCase("all")){
				academicYear="%%";
			}
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(locationId);
			filterpojo.setAcademicYear(academicYear);
			filterpojo.setDepartmentId(department);
			list=new  IDGenerator().getstaffIdCardDesignList(filterpojo);
			for(int i=0;i<list.size();i++){
				PageFilterVo filterVo = new PageFilterVo();
				filterVo.setSno(list.get(i).getSno());
				filterVo.setAcademicYear(list.get(i).getAcademicYear());
				filterVo.setAcademicYearCode(list.get(i).getAcademicYearCode());
				filterVo.setLocationName(list.get(i).getLocationName());
				filterVo.setLocationId(list.get(i).getLocationId());
				filterVo.setDepartmentId(list.get(i).getDepartmentId());
				filterVo.setDepartmentName(list.get(i).getDepartmentName());
				filterVo.setTemplateName(list.get(i).getTemplateName());
				filterVo.setTemplateId(list.get(i).getTemplateId());
				
				String template=list.get(i).getTemplateId();
				File file = new File(request.getRealPath("/")+ "CSS/IdCard/StaffId/"+template+".css");
				bool = file.exists();
				if(bool == true) {
					
					filterVo.setStatus("SET");
				}
				else{
					
					filterVo.setStatus("NOT SET");
					
					
				}
				newlist.add(filterVo);
				
			}

			JSONObject obj=new JSONObject();
			obj.put("departmentList", newlist);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Ending");

		return null;
	}
	
	
	
	public ActionForward staffDesignSingle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Starting");

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			String designation=request.getParameter("designation");
			if(designation.equalsIgnoreCase("all")){
				designation="%%";
			}
			String locationId=request.getParameter("locationId");
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			String academicYear=request.getParameter("academicYear");
			if(academicYear.equalsIgnoreCase("all")){
				academicYear="%%";
			}
			String departmentId=request.getParameter("departmentId");
			if(departmentId.equalsIgnoreCase("all")){
				departmentId="%%";
			}
			
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(locationId);
			filterpojo.setAcademicYear(academicYear);
			filterpojo.setDesignationId(designation);
			list=new  IDGenerator().getstaffIdCardDesignList(filterpojo);

			JSONObject obj=new JSONObject();
			obj.put("designationList", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Ending");

		return null;
	}
	
	
	public ActionForward staffSingleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Starting");

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			String designation=request.getParameter("designation");
			if(designation.equalsIgnoreCase("all")){
				designation="%%";
			}
			String locationId=request.getParameter("locationId");
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			String academicYear=request.getParameter("academicYear");
			if(academicYear.equalsIgnoreCase("all")){
				academicYear="%%";
			}
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(locationId);
			filterpojo.setAcademicYear(academicYear);
			filterpojo.setDesignationId(designation);
			list=new  IDGenerator().getSingleStaffCardDesignList(filterpojo);

			JSONObject obj=new JSONObject();
			obj.put("designationList", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}
	
	


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Ending");

		return null;
	}
	
	
	
	public ActionForward SingleStaffIDCardDesignList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Starting");

		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		try {
			/*String designation=request.getParameter("designation");
			if(designation.equalsIgnoreCase("all")){
				designation="%%";
			}
			String department=request.getParameter("department");
			if(department.equalsIgnoreCase("all")){
				department="%%";
			}*/
			String locationId=request.getParameter("locationId");
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			String academicYear=request.getParameter("academicYear");
			if(academicYear.equalsIgnoreCase("all")){
				academicYear="%%";
			}
			PageFilterpojo filterpojo=new PageFilterpojo();
			filterpojo.setLocationId(locationId);
			filterpojo.setAcademicYear(academicYear);
/*	filterpojo.setDesignationId(department);
	filterpojo.setDesignationId(designation);*/
			list=new  IDGenerator().getstaffSingleIDCard(filterpojo);

			JSONObject obj=new JSONObject();
			obj.put("designationList", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}
	
	


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : NewstudentIdCardDesign Ending");

		return null;
	}
	
	
	
	
	public ActionForward IdDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		String mainCss = request.getParameter("mainCss");
		String layout=request.getParameter("layout");
		System.out.println("layout"+layout);
		String newCssArray[] = mainCss.split("}");
		
		File file = new File(request.getRealPath("/")+ "CSS/IdCard/"+layout+".css");
		if(file.exists()){
			
		}
		else{
			file.createNewFile();
		}
		
		for (String css : newCssArray) {
			System.out.println("each css" + css);

		}
		BufferedWriter bw = null;
		FileWriter fw = null;
		BufferedReader br = null;
		FileReader fr = null;
		String FILENAME =request.getRealPath("/")+ "CSS/IdCard/"+layout+".css";
		File temp=null;
		temp = new File("IdCard.css");
		String absolutePath = temp.getAbsolutePath();
		System.out.println("absolutePath  "+absolutePath);
		try {

			System.out.println(FILENAME);

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				String[] words = sCurrentLine.split("\\s");
				String sCurrentLineArray[] = sCurrentLine.split("}");
				for (String css : words) {
					System.out.println("each css" + css);

				}
			}

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(mainCss);

			System.out.println("Done");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");
		return null;

	}
	
	
	public ActionForward StaffIdDesign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		String mainCss = request.getParameter("mainCss");
		String layout=request.getParameter("layout");
		System.out.println("layout"+layout);
		String newCssArray[] = mainCss.split("}");
		
		File file = new File(request.getRealPath("/")+ "CSS/IdCard/StaffId/"+layout+".css");
		if(file.exists()){
			
		}
		else{
			file.createNewFile();
		}
		
		for (String css : newCssArray) {
			System.out.println("each css" + css);

		}
		BufferedWriter bw = null;
		FileWriter fw = null;
		BufferedReader br = null;
		FileReader fr = null;
		String FILENAME =request.getRealPath("/")+ "CSS/IdCard/StaffId/"+layout+".css";
		File temp=null;
		temp = new File("StaffIdCard.css");
		String absolutePath = temp.getAbsolutePath();
		System.out.println("absolutePath  "+absolutePath);
		try {

			System.out.println(FILENAME);

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				String[] words = sCurrentLine.split("\\s");
				String sCurrentLineArray[] = sCurrentLine.split("}");
				for (String css : words) {
					System.out.println("each css" + css);

				}
			}

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(mainCss);

			System.out.println("Done");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");
		return null;

	}
	
	public ActionForward saveLayoutImage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addLocation Starting");

		try {
			System.out.println("Inside the changeImage");
			InventoryTransactionForm formObj = (InventoryTransactionForm) form;
			System.out.println("formObj :" + formObj);
			
			FormFile file = formObj.getInputfile();
			String layout=formObj.getLayoutDetails();
			
			File filename = new File(request.getRealPath("/")+ "images/IdCard/"+layout+".jpg");
			if(filename.exists()){
				
			}
			else{
				filename.createNewFile();
			}
			
			String filepath = null, base = null, filecuurentpath = null;
			BufferedImage bufferedImage = null;
			filecuurentpath = request.getRealPath("/")+ "images/IdCard/"+layout+".jpg";
			System.out.println(filecuurentpath);
			File f1 = new File(filecuurentpath);
			if (f1 != null) {
				f1.delete();
			}

			String extension = "";
			int j = (file).getFileName().lastIndexOf('.');
			if (j >= 0) {
				base = (String) ((j == -1) ? file : (file).getFileName()
						.substring(0, j));
				extension = (file).getFileName().substring(j + 1);
				base = layout;
			}
			System.out.println("extension is " + extension);
			if (extension.equalsIgnoreCase("jpg")) {

				filepath = request.getRealPath("/")+ "images/IdCard/" + base + "." + extension;

				System.out.println(filepath);
				byte[] btDataFile = (file).getFileData();
				File of = new File(filepath);
				FileOutputStream osf = new FileOutputStream(of);
				osf.write(btDataFile);
				osf.close();
			} else if (extension.equalsIgnoreCase("jpeg")) {

				filepath = request.getRealPath("/")+ "images/IdCard/" + base + "." + extension;
				System.out.println(filepath);

				byte[] btDataFile = (file).getFileData();
				File of = new File(filepath);
				FileOutputStream osf = new FileOutputStream(of);
				osf.write(btDataFile);
				osf.close();
			} else if (extension.equalsIgnoreCase("png")) {
				filepath = request.getRealPath("/")+ "images/IdCard/" + base + "." + extension;
				System.out.println(filepath);

				// read image file
				bufferedImage = ImageIO.read(new File(filepath));

				// create a blank, RGB, same width and height, and a white
				// background
				BufferedImage newBufferedImage = new BufferedImage(
						bufferedImage.getWidth(), bufferedImage.getHeight(),
						BufferedImage.TYPE_INT_RGB);
				newBufferedImage.createGraphics().drawImage(bufferedImage, 0,
						0, Color.WHITE, null);

				// write to jpeg file
				ImageIO.write(
						newBufferedImage,
						"jpg",
						new File(request.getRealPath("/")+ "images/IdCard/"+layout+".jpg"));

			}
			// byte[] btDataFile = (file).getFileData();

			// ImageIO.write(image, "png",new File("C:\\out.png"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : addLocation Ending");

		return null;
	}
	
	public ActionForward StaffSingleIDCardDesignNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : StaffSingleIDCardDesign Starting");
		
		
	String accyear=request.getParameter("academicYear");
		String location=request.getParameter("locationId");
		String desigantion=request.getParameter("designationId");
	String department=request.getParameter("departmentId");
		
	System.out.println("accyear==="+accyear);
	System.out.println("location==="+location);
	System.out.println("desigantion==="+desigantion);
	System.out.println("department==="+department);
	
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		
		try {
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			
			String locationId=request.getParameter("locationId");
			if(locationId!=null){
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			}
			else{
				locationId="%%";
			}
			String academicYear=request.getParameter("academicYear");
			if(academicYear!=null){
			if(academicYear.equalsIgnoreCase("all")){
				academicYear="%%";
			}
			}
			else{
				academicYear="%%";
			}
			String designationId=request.getParameter("designationId");
			if(designationId!=null){
			if(designationId.equalsIgnoreCase("all")){
				designationId="%%";
			}
			}
			else{
				designationId="%%";
			}
			String departmentId=request.getParameter("departmentId");
			if(departmentId!=null){
			if(departmentId.equalsIgnoreCase("all")){
				departmentId="%%";
			}
			}
			else{
				departmentId="%%";
			}
			PageFilterpojo filterpojo=new PageFilterpojo();
	
			filterpojo.setAcademicYear(academicYear);
			filterpojo.setLocationId(locationId);
			filterpojo.setDesignationId(designationId);
			filterpojo.setDepartmentId(departmentId);
			list = new StudentRegistrationDelegate().getIDCardStaff(filterpojo);
			
			JSONObject obj=new JSONObject();
			obj.put("streamList", list);
			response.getWriter().print(obj);
			
		
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : StaffSingleIDCardDesign Ending");
		
				

		return null;
	}	
	
/*	holdit*/
	public ActionForward getStudentSearchByList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentSearchByList Starting");
		
		//List<StudentRegistrationVo> list = null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		
		try {
			
			String accyear = request.getParameter("academicYear");
			String location = request.getParameter("locationId");
			String department = request.getParameter("designationId");
			String designation = request.getParameter("departmentId");
			
			System.out.println("accyear "+accyear);
			System.out.println("location "+location);
			System.out.println("department "+department);
			System.out.println("designation "+designation);
			
			String searchTerm = request.getParameter("searchname".trim());

			
			
			
/*	if(locationId.equalsIgnoreCase("all") && academicYear.equalsIgnoreCase("all")){
				
				list = new StudentRegistrationDelegate().getStudentSearchByList(searchTerm);
			}
			else if(locationid.equalsIgnoreCase("all") && !(accyear.equalsIgnoreCase("all"))){
				
				list = new StudentRegistrationDelegate().getStudentSearchListByAccYear(searchTerm,accyear);
			}
			else if(accyear.equalsIgnoreCase("all") && !(locationid.equalsIgnoreCase("all"))){
				
				list = new StudentRegistrationDelegate().getStudentSearchListByLocation(searchTerm,locationid);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")) && classname.equalsIgnoreCase("all")){
				
				list = new StudentRegistrationDelegate().getStudentSearchByFilter(searchTerm,locationid,accyear);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all")) && sectionid.equalsIgnoreCase("all")){
				
				list = new StudentRegistrationDelegate().getStudentSearchByClass(searchTerm,locationid,accyear,classname);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all") && sectionid.equalsIgnoreCase("all"))){
				
				list = new StudentRegistrationDelegate().getStudentSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
			}*/
			
			request.setAttribute("SearchList", list);
			list.size();
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("SearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentSearchByList Ending");

		return null;
	}
	
	
	public ActionForward PrintPreviewStaffMultipleIDCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");


		String teacherID[]=request.getParameter("staffid").split(",");
		String locationId[]=request.getParameter("locationId").split(",");
		String desigantionId[]=request.getParameter("designationId").split(",");
		String academicyear[]=request.getParameter("academicyear").split(",");
	
		List<PageFilterVo> filterVo = new StudentIDDaoImpl().getstaffIDPDFReport(locationId,teacherID);
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();

		int AccYearVal=Integer.parseInt(HelperClass.getAcademicYearFace(academicyear[0]).split("-")[0]);
		int validuptoyear=AccYearVal+3;
		String validUpto="31-03-"+validuptoyear;
		for (int i = 0; i < filterVo.size(); i++) {

			PageFilterVo vo = new PageFilterVo();
			
			String fileName = filterVo.get(i).getImage();
			String filePath = request.getRealPath("/") + fileName.trim();
			vo.setImages("./" + fileName.trim());
			vo.setTeacherName(filterVo.get(i).getTeacherName());
			vo.setTeacherID(filterVo.get(i).getTeacherID());
			vo.setAbbrivateId(filterVo.get(i).getAbbrivateId());
			vo.setDepartmentName(filterVo.get(i).getDepartmentName());
			vo.setDesignationName(filterVo.get(i).getDesignationName());
			vo.setAddress(filterVo.get(i).getAddress());
			vo.setMobileNo(filterVo.get(i).getMobileNo());
			vo.setLocation_address(filterVo.get(i).getLocation_address());
			vo.setLocation_phone(filterVo.get(i).getLocation_phone());
			vo.setLocationName(filterVo.get(i).getLocationName());
			
			list.add(vo);
		}
		
		request.setAttribute("template", academicyear[0]+locationId[0]+desigantionId[0]);
		request.setAttribute("validUpto", validUpto);
		
		request.setAttribute("staffList", list);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDCreationAction : studentIDPDFReport Ending");

		return mapping.findForward(MessageConstants.PrintPreviewStaffMultipleIDCard);
	}
	
	public ActionForward getStaffSearchByList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentSearchByList Starting");
		
		//List<StudentRegistrationVo> list = null;
		List<PageFilterVo> list=new ArrayList<PageFilterVo>();
		
		try {
			System.out.println("**************************Searachhhhhhhhhhhhhhhhhhh");
			String academicYear = request.getParameter("academicYear");
			String locationId = request.getParameter("locationId");
			String designation = request.getParameter("designation");
			String department = request.getParameter("department");
			
			
			String searchTerm = request.getParameter("searchname".trim());
			if(academicYear.equalsIgnoreCase("all")){
				academicYear="%%";
			}
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			if(designation.equalsIgnoreCase("all")){
				designation="%%";
			}
			if(department.equalsIgnoreCase("all")){
				department="%%";
			}
				
				list=new  IDGenerator().getstaffSearchByAll(searchTerm,locationId,academicYear,department,designation);
				
		
			
			request.setAttribute("SearchList", list);
			list.size();
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("SearchList", list);
			
			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentSearchByList Ending");

		return null;
	}

}
