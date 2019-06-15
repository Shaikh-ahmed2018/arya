package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.json.JSONArray;
import org.json.JSONObject;

import com.centris.campus.daoImpl.ElectionDaoImpl;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.delegate.FeeCollectionBD;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.delegate.TransportBD;
import com.centris.campus.forms.StudentRegistrationForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.HelperClassVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportTypeVO;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.registrationvo;
import com.centris.campus.vo.secadmissionformformatVO;
import com.sun.mail.iap.Response;

public class StudentRegistrationAction extends DispatchAction {
	/**
	 * Logger instance.
	 */

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	private static final Logger logger = Logger
			.getLogger(StudentRegistrationAction.class);

	/**
	 * <p>
	 * This action method is responsible for getting AcademicYears.
	 * </p>
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return List of StudentRegistrationVo Object.
	 * @throws Exception
	 */

	public ActionForward getAcademicYear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getAcademicYear Starting");

		List<StudentRegistrationVo> studentRegistrationVos = new StudentRegistrationDelegate().getAcademicYear();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("jsonResponse",studentRegistrationVos);
		response.getWriter().print(jsonObject);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getAcademicYear Ending");
		return null;
	}

	public ActionForward getStudentquota(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getStudentquota Starting");

		List<StudentRegistrationVo> studentRegistrationQuotaList = new StudentRegistrationDelegate()
				.getStudentquota();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(MessageConstants.JSON_RESPONSE,
				studentRegistrationQuotaList);
		response.getWriter().print(jsonObject);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getStudentquota Ending");
		return null;
	}

	/**
	 * <p>
	 * This action method is responsible for Saving the StudentRegistrationForm.
	 * </p>
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */
	public ActionForward saveStudentRegistration(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info("Control in StudentRegistrationAction: saveStudentRegistration Starting");

		System.out.println("saveStudentRegistration method in Action class: ");

		String keyValues = null;
		FormFile imagePath = null;
		String realPath = "";
		FileOutputStream outputStream = null;
		String imagepath;
		String fileName = "";
		int i = 0;
		boolean flag = false;
		String academicYearFace=null;
		StudentRegistrationDelegate registrationDelegate = new StudentRegistrationDelegate();
		StudentRegistrationForm studentRegistrationForm = (StudentRegistrationForm) form;
		FormFile formFile = null;
		String path = null;
		String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		studentRegistrationForm.setLocationId(schoolLocation);
		formFile = studentRegistrationForm.getBirthFile();

		String StudentIDGenerator = null;
		
		try {
			academicYearFace=HelperClass.getAcademicYearFace(studentRegistrationForm.getAcademicYear());
			int count = registrationDelegate.validateDuplicateData(studentRegistrationForm, "Add");
			if (count == 0) {

				if (i == 0) {

					StudentIDGenerator = IDGenerator.getPrimaryKeyID("campus_student",JDBCConnection.getSeparateGodaddyConnection());

					String createdUser = HelperClass.getCurrentUserID(request);

					System.out.println("create User: "+ createdUser);

					String aadharno = studentRegistrationForm.getAadharno();


					if (studentRegistrationForm.getDateofBirth() != null) {

						//To get image path
						if(studentRegistrationForm.getPreviousImage() !=null && studentRegistrationForm.getPreviousImage().length()>1) {
							int dot=studentRegistrationForm.getPreviousImage().lastIndexOf('.');
							String extension="";
							if (dot >= 0) {
								extension = studentRegistrationForm.getPreviousImage().substring(dot+1);
							}
							File file = new File(request.getRealPath("/")+ "FIles/STUDENTIMAGES/"+academicYearFace);
							if (!file.exists()) {
								file.mkdirs();
							}
							FileInputStream is = null;
							FileOutputStream os = null;
							try {
								File sourceFile=new File(request.getRealPath("/")+studentRegistrationForm.getPreviousImage());
								File desFile=new File(request.getRealPath("/")+ "FIles/STUDENTIMAGES"+ "/"+academicYearFace+"/"+studentRegistrationForm.getStudentrollno()+ "." + extension);
								is = new FileInputStream(sourceFile);
								os = new FileOutputStream(desFile);
								byte[] buffer = new byte[1024];
								int length;
								while ((length = is.read(buffer)) > 0) {
									os.write(buffer, 0, length);
								}


							}catch(Exception ex) {
								System.out.println("Unable to copy file:"+ex.getMessage());
							}   
							finally {
								try {
									is.close();
									os.close();
								}catch(Exception ex) {}
							}
							studentRegistrationForm.setStudentPhoto("FIles/STUDENTIMAGES"+ "/"+academicYearFace+"/"+studentRegistrationForm.getStudentrollno()+ "." + extension);

						}	else{

							try {

								File file = new File(request.getRealPath("/")+ "FIles/STUDENTIMAGES/"+academicYearFace);
								if (!file.exists()) {
									file.mkdirs();
								}

								String extension="";
								imagePath = studentRegistrationForm.getStudentImage();

								if (imagePath != null) {

									fileName = imagePath.getFileName();
								}

								int dot = imagePath.getFileName().lastIndexOf('.');
								if (dot >= 0) {
									extension = imagePath.getFileName().substring(dot+1);
								}

								if (fileName.length() < 2 && fileName != null) {

									studentRegistrationForm.setStudentPhoto(request.getRealPath("/")+ "FIles/STUDENTIMAGES/"+"noImage.png");

								} else if(extension.equalsIgnoreCase("jpg")){
									imagepath = request.getRealPath("/")+ "FIles/STUDENTIMAGES"+ "/"+academicYearFace+"/"+studentRegistrationForm.getStudentrollno()+ "." + extension;
									realPath = imagepath.substring(imagepath.indexOf("FIles/"));
									outputStream = new FileOutputStream(new File(imagepath));
									outputStream.write(imagePath.getFileData());
								}else if(extension.equalsIgnoreCase("jpeg")){
									imagepath = request.getRealPath("/")+ "FIles/STUDENTIMAGES"+ "/"+academicYearFace+"/"+studentRegistrationForm.getStudentrollno()+ "." + extension;
									realPath = imagepath.substring(imagepath.indexOf("FIles/"));
									outputStream = new FileOutputStream(new File(imagepath));
									outputStream.write(imagePath.getFileData());
								}else if(extension.equalsIgnoreCase("png")){
									imagepath = request.getRealPath("/")+ "FIles/STUDENTIMAGES"+ "/"+academicYearFace+"/"+studentRegistrationForm.getStudentrollno()+ "." + extension;
									realPath = imagepath.substring(imagepath.indexOf("FIles/"));
									outputStream = new FileOutputStream(new File(imagepath));
									outputStream.write(imagePath.getFileData());
								}
								studentRegistrationForm.setStudentPhoto(realPath);
							} catch (Exception e) {
								logger.error(e.getMessage(), e);
								e.printStackTrace();
							} finally {

								if (outputStream != null) {

									outputStream.close();
								}
							}
						}
						//To get Father Image Path


						try{

							String extension="jpg";

							imagePath = studentRegistrationForm.getFatherPhoto();

							if (imagePath != null) {

								fileName = imagePath.getFileName();
							}

							int dot = imagePath.getFileName().lastIndexOf('.');
							if (dot >= 0) {
								extension = imagePath.getFileName().substring(dot+1);
							}


							if (fileName.length() < 2 && fileName != null) {

								studentRegistrationForm.setFatherPhotoUrl(request.getRealPath("/")+ "FIles/STUDENTIMAGES"+ "/"+academicYearFace+"/"+"noImage.png");

							} else if(extension.equalsIgnoreCase("jpg")){

								imagepath = request.getRealPath("/")+ "FIles/STUDENTIMAGES"+ "/"+studentRegistrationForm.getStudentFirstName()+"_"+"father"+StudentIDGenerator + "." +extension ;

								realPath = imagepath.substring(imagepath.indexOf("FIles/"));

								outputStream = new FileOutputStream(new File(imagepath));
								outputStream.write(imagePath.getFileData());

								studentRegistrationForm.setFatherPhotoUrl(realPath);
							}else if(extension.equalsIgnoreCase("jpeg")){

								imagepath = request.getRealPath("/")+ "FIles/STUDENTIMAGES"+  "/"+studentRegistrationForm.getStudentFirstName()+"_"+"father"+StudentIDGenerator + "." +extension ;

								realPath = imagepath.substring(imagepath.indexOf("FIles/"));

								outputStream = new FileOutputStream(new File(imagepath));
								outputStream.write(imagePath.getFileData());

								studentRegistrationForm.setFatherPhotoUrl(realPath);
							}else if(extension.equalsIgnoreCase("png")){

								imagepath = request.getRealPath("/")+ "FIles/STUDENTIMAGES"+  "/"+studentRegistrationForm.getStudentFirstName()+"_"+"father"+StudentIDGenerator + "." +extension ;

								realPath = imagepath.substring(imagepath.indexOf("FIles/"));

								outputStream = new FileOutputStream(new File(imagepath));
								outputStream.write(imagePath.getFileData());

								studentRegistrationForm.setFatherPhotoUrl(realPath);
							}




						}catch(Exception  e){

							logger.error(e.getMessage(), e);
							e.printStackTrace();

						}finally{
							if (outputStream != null) {

								outputStream.close();
							}
						}


						//to get mother iamge path

						try{

							String extension="jpg";

							imagePath = studentRegistrationForm.getMotherPhoto();

							if (imagePath != null) {

								fileName = imagePath.getFileName();
							}

							int dot = imagePath.getFileName().lastIndexOf('.');
							if (dot >= 0) {
								extension = imagePath.getFileName().substring(dot+1);
							}


							if (fileName.length() < 2 && fileName != null) {

								studentRegistrationForm.setMotherPhotoUrl(request.getRealPath("/")+ "FIles/STUDENTIMAGES/" +"noImage.png");

							} else if(extension.equalsIgnoreCase("jpg")) {

								imagepath = request.getRealPath("/")+ "FIles/STUDENTIMAGES"+ "/"+studentRegistrationForm.getStudentFirstName()+"_"+"mother"+StudentIDGenerator + "." + extension;

								realPath = imagepath.substring(imagepath.indexOf("FIles/"));

								outputStream = new FileOutputStream(new File(imagepath));
								outputStream.write(imagePath.getFileData());

								studentRegistrationForm.setMotherPhotoUrl(realPath);
							}else if(extension.equalsIgnoreCase("jpeg")) {

								imagepath = request.getRealPath("/")+ "FIles/STUDENTIMAGES"+ "/"+studentRegistrationForm.getStudentFirstName()+"_"+"mother"+StudentIDGenerator + "." + extension;

								realPath = imagepath.substring(imagepath.indexOf("FIles/"));

								outputStream = new FileOutputStream(new File(imagepath));
								outputStream.write(imagePath.getFileData());

								studentRegistrationForm.setMotherPhotoUrl(realPath);
							}else if(extension.equalsIgnoreCase("png")) {

								imagepath = request.getRealPath("/")+ "FIles/STUDENTIMAGES"+  "/"+studentRegistrationForm.getStudentFirstName()+"_"+"mother"+StudentIDGenerator + "." + extension;

								realPath = imagepath.substring(imagepath.indexOf("FIles/"));

								outputStream = new FileOutputStream(new File(imagepath));
								outputStream.write(imagePath.getFileData());

								studentRegistrationForm.setMotherPhotoUrl(realPath);
							}

						}catch(Exception  e){

							logger.error(e.getMessage(), e);
							e.printStackTrace();

						}finally{
							if (outputStream != null) {

								outputStream.close();
							}
						}

						//to guardian image path


						try{

							String extension="jpg";

							imagePath = studentRegistrationForm.getGuardianPhoto();

							if (imagePath != null) {

								fileName = imagePath.getFileName();
							}

							int dot = imagePath.getFileName().lastIndexOf('.');
							if (dot >= 0) {
								extension = imagePath.getFileName().substring(dot+1);
							}

							if (fileName.length() < 2 && fileName != null) {

								studentRegistrationForm.setGuardianPhotoUrl(request.getRealPath("/")+ "FIles/STUDENTIMAGES/" +"noImage.png");

							} else if(extension.equalsIgnoreCase("jpg")) {

								imagepath = request.getRealPath("/")+ "FIles/STUDENTIMAGES"+  "/"+studentRegistrationForm.getStudentFirstName()+"_"+"guardian"+StudentIDGenerator + "." + extension;

								realPath = imagepath.substring(imagepath.indexOf("FIles/"));

								outputStream = new FileOutputStream(new File(imagepath));
								outputStream.write(imagePath.getFileData());

								studentRegistrationForm.setGuardianPhotoUrl(realPath);
							}else if(extension.equalsIgnoreCase("jpeg")) {

								imagepath = request.getRealPath("/")+ "FIles/STUDENTIMAGES"+  "/"+studentRegistrationForm.getStudentFirstName()+"_"+"guardian"+StudentIDGenerator + "." + extension;

								realPath = imagepath.substring(imagepath.indexOf("FIles/"));

								outputStream = new FileOutputStream(new File(imagepath));
								outputStream.write(imagePath.getFileData());

								studentRegistrationForm.setGuardianPhotoUrl(realPath);
							}else if(extension.equalsIgnoreCase("png")) {

								imagepath = request.getRealPath("/")+ "FIles/STUDENTIMAGES"+  "/"+studentRegistrationForm.getStudentFirstName()+"_"+"guardian"+StudentIDGenerator + "." + extension;

								realPath = imagepath.substring(imagepath.indexOf("FIles/"));

								outputStream = new FileOutputStream(new File(imagepath));
								outputStream.write(imagePath.getFileData());

								studentRegistrationForm.setGuardianPhotoUrl(realPath);
							}

						}catch(Exception  e){

							logger.error(e.getMessage(), e);
							e.printStackTrace();

						}finally{
							if (outputStream != null) {

								outputStream.close();
							}
						}


						//	To get BirthCertificate path 

						if(studentRegistrationForm.getPreviousBirthCer() !=null && studentRegistrationForm.getPreviousBirthCer().length() > 1){


							int dot=studentRegistrationForm.getPreviousBirthCer().lastIndexOf('.');
							String extension="";
							if (dot >= 0) {
								extension = studentRegistrationForm.getPreviousBirthCer().substring(dot+1);
							}
							FileInputStream is = null;
							FileOutputStream os = null;
							try {
								File sourceFile=new File(request.getRealPath("/")+studentRegistrationForm.getPreviousBirthCer());
								File desFile=new File(request.getRealPath("/")+ "FIles/STUDENTBIRTHCER/BirthCer_" + StudentIDGenerator+ "." + extension);
								is = new FileInputStream(sourceFile);
								os = new FileOutputStream(desFile);
								byte[] buffer = new byte[1024];
								int length;
								while ((length = is.read(buffer)) > 0) {
									os.write(buffer, 0, length);
								}


								studentRegistrationForm.setBirthfile("FIles/STUDENTBIRTHCER/BirthCer_" + StudentIDGenerator+ "." + extension);
							}catch(Exception ex) {
								System.out.println("Unable to copy file:"+ex.getMessage());
							}   
							finally {
								try {
									is.close();
									os.close();
								}catch(Exception ex) {}
							}



						}
						else{

							try {

								if (studentRegistrationForm.getBirthFile() != null) {

									String extension="jpg";

									int j = studentRegistrationForm.getBirthFile().getFileName().lastIndexOf('.');
									if (j >= 0) {
										extension = studentRegistrationForm.getBirthFile().getFileName().substring(j+1);
									}

									String birthcertificate_path = "FIles/STUDENTBIRTHCER/BirthCer_" + StudentIDGenerator+ "." + extension;

									System.out.println("birthcertificate_path::"+birthcertificate_path);

									String filePath = request.getRealPath("/") + "FIles/STUDENTBIRTHCER/BirthCer_" + StudentIDGenerator
											+ "." +extension;
									if (studentRegistrationForm.getBirthFile().getFileSize() > 0) {

										byte[] btDataFile = studentRegistrationForm.getBirthFile().getFileData();
										File of = new File(filePath);
										FileOutputStream osf = new FileOutputStream(of);
										osf.write(btDataFile);
										osf.flush();

									} else {

										birthcertificate_path = "";
									}


									studentRegistrationForm.setBirthfile(birthcertificate_path);

								}

							} catch (Exception e) {
								logger.error(e.getMessage(), e);
								e.printStackTrace();
							} finally {
								if (outputStream != null) {
									outputStream.close();
								}
							}

						}
						//	To get TransferCertificate path

						try {

							if (studentRegistrationForm.getTransferFile() != null) {

								String extension=null;

								int k = studentRegistrationForm.getTransferFile().getFileName().lastIndexOf('.');

								if (k >= 0) {

									extension = studentRegistrationForm.getTransferFile().getFileName().substring(k+1);
								}

								String transfercertificate_path = "FIles/STUDENTTRANSFERCER/TransferCer_" + StudentIDGenerator+ "." + extension;

								System.out.println("transfercertificate_path::"+transfercertificate_path);

								String filePath = request.getRealPath("/") + "FIles/STUDENTTRANSFERCER/TransferCer_" + StudentIDGenerator
										+ "." +extension;

								if (studentRegistrationForm.getTransferFile().getFileSize() > 0) {

									byte[] btDataFile = studentRegistrationForm.getTransferFile().getFileData();
									File of = new File(filePath);
									FileOutputStream osf = new FileOutputStream(of);
									osf.write(btDataFile);
									osf.flush();

								} else {
									transfercertificate_path = "";
								}


								studentRegistrationForm.setTransferfile(transfercertificate_path);

							}

						} catch (Exception e) {
							logger.error(e.getMessage(), e);
							e.printStackTrace();
						} finally {
							if (outputStream != null) {
								outputStream.close();
							}
						}

						//to get certificate1 path

						try {

							if (studentRegistrationForm.getCertificate1() != null) {

								String extension=null;

								int k = studentRegistrationForm.getCertificate1().getFileName().lastIndexOf('.');

								if (k >= 0) {

									extension = studentRegistrationForm.getCertificate1().getFileName().substring(k+1);
								}

								String certificate1_path = "FIles/STUDENTTRANSFERCER/Certificate1_" + StudentIDGenerator+ "." + extension;

								System.out.println("certificate1_path::"+certificate1_path);

								String filePath = request.getRealPath("/") + "FIles/STUDENTTRANSFERCER/Certificate1_" + StudentIDGenerator
										+ "." +extension;

								if (studentRegistrationForm.getCertificate1().getFileSize() > 0) {

									byte[] btDataFile = studentRegistrationForm.getCertificate1().getFileData();
									File of = new File(filePath);
									FileOutputStream osf = new FileOutputStream(of);
									osf.write(btDataFile);
									osf.flush();

								} else {
									certificate1_path = "";
								}


								studentRegistrationForm.setCertificate1Url(certificate1_path);

							}

						} catch (Exception e) {
							logger.error(e.getMessage(), e);
							e.printStackTrace();
						} finally {
							if (outputStream != null) {
								outputStream.close();
							}
						}

						//to get certifiate2 path
						try {

							if (studentRegistrationForm.getCertificate2() != null) {

								String extension=null;

								int k = studentRegistrationForm.getCertificate2().getFileName().lastIndexOf('.');

								if (k >= 0) {

									extension = studentRegistrationForm.getCertificate2().getFileName().substring(k+1);
								}

								String certificate2_path = "FIles/STUDENTTRANSFERCER/Certificate2_" + StudentIDGenerator+ "." + extension;

								System.out.println("certificate2_path::"+certificate2_path);

								String filePath = request.getRealPath("/") + "FIles/STUDENTTRANSFERCER/Certificate2_" + StudentIDGenerator
										+ "." +extension;

								if (studentRegistrationForm.getCertificate2().getFileSize() > 0) {

									byte[] btDataFile = studentRegistrationForm.getCertificate2().getFileData();
									File of = new File(filePath);
									FileOutputStream osf = new FileOutputStream(of);
									osf.write(btDataFile);
									osf.flush();

								} else {
									certificate2_path = "";
								}


								studentRegistrationForm.setCertificate2Url(certificate2_path);

							}

						} catch (Exception e) {
							logger.error(e.getMessage(), e);
							e.printStackTrace();
						} finally {
							if (outputStream != null) {
								outputStream.close();
							}
						}

						//to get certificate3 path

						try {

							if (studentRegistrationForm.getCertificate3() != null) {

								String extension=null;

								int k = studentRegistrationForm.getCertificate3().getFileName().lastIndexOf('.');

								if (k >= 0) {

									extension = studentRegistrationForm.getCertificate3().getFileName().substring(k+1);
								}

								String certificate3_path = "FIles/STUDENTTRANSFERCER/Certificate3_" + StudentIDGenerator+ "." + extension;

								String filePath = request.getRealPath("/") + "FIles/STUDENTTRANSFERCER/Certificate3_" + StudentIDGenerator + "." +extension;

								if (studentRegistrationForm.getCertificate3().getFileSize() > 0) {

									byte[] btDataFile = studentRegistrationForm.getCertificate3().getFileData();
									File of = new File(filePath);
									FileOutputStream osf = new FileOutputStream(of);
									osf.write(btDataFile);
									osf.flush();

								} else {
									certificate3_path = "";
								}


								studentRegistrationForm.setCertificate3Url(certificate3_path);

							}

						} catch (Exception e) {
							logger.error(e.getMessage(), e);
							e.printStackTrace();
						} finally {
							if (outputStream != null) {
								outputStream.close();
							}
						}
						//to get certificate4 path


						try {

							if (studentRegistrationForm.getCertificate4() != null) {

								String extension=null;

								int k = studentRegistrationForm.getCertificate4().getFileName().lastIndexOf('.');

								if (k >= 0) {

									extension = studentRegistrationForm.getCertificate4().getFileName().substring(k+1);
								}

								String certificate4_path = "FIles/STUDENTTRANSFERCER/Certificate4_" + StudentIDGenerator+ "." + extension;

								String filePath = request.getRealPath("/") + "FIles/STUDENTTRANSFERCER/Certificate4_" + StudentIDGenerator	+ "." +extension;

								if (studentRegistrationForm.getCertificate4().getFileSize() > 0) {

									byte[] btDataFile = studentRegistrationForm.getCertificate4().getFileData();
									File of = new File(filePath);
									FileOutputStream osf = new FileOutputStream(of);
									osf.write(btDataFile);
									osf.flush();

								} else {
									certificate4_path = "";
								}


								studentRegistrationForm.setCertificate4Url(certificate4_path);

							}

						} catch (Exception e) {
							logger.error(e.getMessage(), e);
							e.printStackTrace();
						} finally {
							if (outputStream != null) {
								outputStream.close();
							}
						}
						//to get cetificate5 path

						try {

							if (studentRegistrationForm.getCertificate5() != null) {

								String extension=null;

								int k = studentRegistrationForm.getCertificate5().getFileName().lastIndexOf('.');

								if (k >= 0) {

									extension = studentRegistrationForm.getCertificate5().getFileName().substring(k+1);
								}

								String certificate5_path = "FIles/STUDENTTRANSFERCER/Certificate5_" + StudentIDGenerator+ "." + extension;

								String filePath = request.getRealPath("/") + "FIles/STUDENTTRANSFERCER/Certificate5_" + StudentIDGenerator + "." +extension;

								if (studentRegistrationForm.getCertificate5().getFileSize() > 0) {

									byte[] btDataFile = studentRegistrationForm.getCertificate5().getFileData();
									File of = new File(filePath);
									FileOutputStream osf = new FileOutputStream(of);
									osf.write(btDataFile);
									osf.flush();

								} else {
									certificate5_path = "";
								}

								studentRegistrationForm.setCertificate5Url(certificate5_path);
							}

						} catch (Exception e) {
							logger.error(e.getMessage(), e);
							e.printStackTrace();
						} finally {
							if (outputStream != null) {
								outputStream.close();
							}
						}

						studentRegistrationForm.setCreateUser(createdUser);
						studentRegistrationForm.setStudentIDgenerator(StudentIDGenerator);
						studentRegistrationForm.getRemarks();
						studentRegistrationForm.setAadharno(aadharno);


						i = i + 1;

						Map<String, String> studentRegistrationMap = registrationDelegate.saveStudentRegistration(studentRegistrationForm);

						Set set = studentRegistrationMap.entrySet();
						Iterator iterator = set.iterator();

						while (iterator.hasNext()) {

							Map.Entry<String, String> m = (Map.Entry) iterator.next();
							keyValues = (String) m.getKey();

							if (keyValues.equalsIgnoreCase("successMessage")) {

								request.setAttribute("StudentCount",StudentIDGenerator);
								request.setAttribute("successMessage","Adding Record Progressing...");
								request.setAttribute("classId",studentRegistrationForm.getStudClassId());
								request.setAttribute("accyearId",studentRegistrationForm.getAcademicYear());
								request.setAttribute("studentId",StudentIDGenerator);
								request.setAttribute("isConcessionAvailable",studentRegistrationForm.getCencession());
								request.setAttribute("isTransport",studentRegistrationForm.getTransport());
								request.setAttribute("concessionPercentage",studentRegistrationForm.getScholarShip());
								request.setAttribute("studentQuataName",studentRegistrationForm.getStudentquotaname());


							} else if (keyValues.equalsIgnoreCase("errorMessage")) {

								request.setAttribute("errorMessage","Student Not Registred");

							} else if (keyValues.equalsIgnoreCase("duplicateMessage")) {

								request.setAttribute("duplicateMessage","Student Already Registered  with this Details");

							} else {

								request.setAttribute("refresh ", " ");
							}
						}

					}
				}
			} else {

				request.setAttribute("duplicateMessage","Student Already Registered  with This Details");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction: saveStudentRegistration Ending");

		return mapping.findForward(MessageConstants.ADD_STUDENT);

	}
	public ActionForward studentSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info("Control in StudentRegistrationAction: studentSearch Starting");

		List<StudentRegistrationVo> searchStudentList = new ArrayList<StudentRegistrationVo>();
		try {
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			String searchterm = request.getParameter("searchTerm").trim();
			String accYearVal = request.getParameter("accYearVal");
			String classId = request.getParameter("classname");
			String sectionId = request.getParameter("section");

			registrationVo.setSearchTerm(searchterm);
			registrationVo.setAcademicYearId(accYearVal);
			registrationVo.setClassDetailId(classId);
			registrationVo.setClassSectionId(sectionId);

			StudentRegistrationDelegate registrationDelegate = new StudentRegistrationDelegate();
			searchStudentList = registrationDelegate
					.studentSearch(registrationVo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction: studentSearch Ending");
		return null;

	}

	public ActionForward searchStudentResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info("Control in StudentRegistrationAction: searchStudentResult Starting");

		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		StudentRegistrationDelegate delegate = new StudentRegistrationDelegate();
		try {

			String searchTerm = request.getParameter("searchTerm").trim();

			registrationVo1.setSearchTerm(searchTerm);

			request.setAttribute("SerchTerm", searchTerm);

			request.setAttribute("studentSearchList", delegate.searchStudentResult(registrationVo1));


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : searchStudentResult Ending");

		return mapping.findForward(MessageConstants.ADDSTUDENTREGISTRATION);
	}

	public ActionForward modifyStudentDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info("Control in StudentRegistrationAction : ModifyStudentDetails Starting");
		String success = "";
		FileOutputStream outputStream = null;
		String realPath = "";
		String academicYearFace=null;
		String currentAccYear=HelperClass.getCurrentYearID();
		System.out.println("currentAccYear"+currentAccYear);
		String current_academicyear=(String) request.getSession(false).getAttribute("current_academicYear");
		StudentRegistrationForm studentRegistrationForm = (StudentRegistrationForm) form;

		academicYearFace=HelperClass.getAcademicYearFace(studentRegistrationForm.getAcademicYear());

		try {


			String createdUser = HelperClass.getCurrentUserID(request);
			System.out.println("createdUser is "+createdUser);

			String studentid=studentRegistrationForm.getStudentId();



			try {
				File file = new File(request.getRealPath("/")+ "FIles/STUDENTIMAGES/"+academicYearFace);
				if (!file.exists()) {
					file.mkdirs();
				}

				if (studentRegistrationForm.getStudentImage() != null) {

					String extension=null;

					int i = studentRegistrationForm.getStudentImage().getFileName().lastIndexOf('.');
					if (i >= 0) {

						extension = studentRegistrationForm.getStudentImage().getFileName().substring(i+1);
					}

					String image_path = "FIles/STUDENTIMAGES"+ "/"+academicYearFace+"/"+studentRegistrationForm.getStudentrollno()+ "." + extension;

					String filePath = request.getRealPath("/") + "FIles/STUDENTIMAGES"+ "/"+academicYearFace+"/"+studentRegistrationForm.getStudentrollno()+ "." + extension;

					if (studentRegistrationForm.getStudentImage().getFileSize() > 0) {
						byte[] btDataFile = studentRegistrationForm.getStudentImage().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {

						image_path = studentRegistrationForm.getPreviousImage();
					}

					studentRegistrationForm.setImageFileName(image_path);


				}else{

					studentRegistrationForm.setImageFileName(studentRegistrationForm.getPreviousImage());
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
			}

			try {


				if (studentRegistrationForm.getBirthFile() != null) {

					String extension=null;

					int i = studentRegistrationForm.getBirthFile().getFileName().lastIndexOf('.');
					if (i >= 0) {
						extension = studentRegistrationForm.getBirthFile().getFileName().substring(i+1);
					}

					String idProof_path = "FIles/STUDENTBIRTHCER/BirthCer_" + studentid.trim()+ "." + extension;


					String filePath = request.getRealPath("/") + "FIles/STUDENTBIRTHCER/BirthCer_" + studentid.trim()
					+ "." +extension;
					if (studentRegistrationForm.getBirthFile().getFileSize() > 0) {

						byte[] btDataFile = studentRegistrationForm.getBirthFile().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {

						idProof_path = studentRegistrationForm.getPreviousBirthCer();
					}

					studentRegistrationForm.setBirthfile(idProof_path);

				}else{
					studentRegistrationForm.setBirthfile(studentRegistrationForm.getPreviousBirthCer());
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
			}

			//To get Father Image Path


			try{

				if (studentRegistrationForm.getFatherPhoto() != null) {

					String extension=null;

					int i = studentRegistrationForm.getFatherPhoto().getFileName().lastIndexOf('.');
					if (i >= 0) {

						extension = studentRegistrationForm.getFatherPhoto().getFileName().substring(i+1);
					}

					String image_path = "FIles/STUDENTIMAGES/"+studentRegistrationForm.getFatherPhoto()+"_" + studentid.trim()+ "." + extension;

					String filePath = request.getRealPath("/") + "FIles/STUDENTIMAGES/"+studentRegistrationForm.getFatherPhoto()+"_" + studentid.trim()+ "." +extension;

					if (studentRegistrationForm.getFatherPhoto().getFileSize() > 0) {
						byte[] btDataFile = studentRegistrationForm.getFatherPhoto().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {

						image_path = studentRegistrationForm.getPreviousFatherImage();
					}

					studentRegistrationForm.setFatherPhotoUrl(image_path);


				}else{

					studentRegistrationForm.setFatherPhotoUrl(studentRegistrationForm.getPreviousFatherImage());
				}



			}catch(Exception  e){

				logger.error(e.getMessage(), e);
				e.printStackTrace();

			}finally{
				if (outputStream != null) {

					outputStream.close();
				}
			}


			//to get mother iamge path

			try{

				if (studentRegistrationForm.getMotherPhoto() != null) {

					String extension=null;

					int i = studentRegistrationForm.getMotherPhoto().getFileName().lastIndexOf('.');
					if (i >= 0) {

						extension = studentRegistrationForm.getMotherPhoto().getFileName().substring(i+1);
					}

					String image_path = "FIles/STUDENTIMAGES/"+studentRegistrationForm.getMotherPhoto()+"_" + studentid.trim()+ "." + extension;

					String filePath = request.getRealPath("/") + "FIles/STUDENTIMAGES/"+studentRegistrationForm.getMotherPhoto()+"_" + studentid.trim()+ "." +extension;

					if (studentRegistrationForm.getMotherPhoto().getFileSize() > 0) {
						byte[] btDataFile = studentRegistrationForm.getMotherPhoto().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {

						image_path = studentRegistrationForm.getPreviousMotherImage();
					}

					studentRegistrationForm.setMotherPhotoUrl(image_path);


				}else{

					studentRegistrationForm.setMotherPhotoUrl(studentRegistrationForm.getPreviousMotherImage());
				}

			}catch(Exception  e){

				logger.error(e.getMessage(), e);
				e.printStackTrace();

			}finally{
				if (outputStream != null) {

					outputStream.close();
				}
			}

			//to guardian image path


			try{

				if (studentRegistrationForm.getGuardianPhoto() != null) {

					String extension=null;

					int i = studentRegistrationForm.getGuardianPhoto().getFileName().lastIndexOf('.');
					if (i >= 0) {

						extension = studentRegistrationForm.getGuardianPhoto().getFileName().substring(i+1);
					}

					String image_path = "FIles/STUDENTIMAGES/"+studentRegistrationForm.getGuardianPhoto()+"_" + studentid.trim()+ "." + extension;

					String filePath = request.getRealPath("/") + "FIles/STUDENTIMAGES/"+studentRegistrationForm.getGuardianPhoto()+"_" + studentid.trim()+ "." +extension;

					if (studentRegistrationForm.getGuardianPhoto().getFileSize() > 0) {
						byte[] btDataFile = studentRegistrationForm.getGuardianPhoto().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {

						image_path = studentRegistrationForm.getPreviousGuardImage();
					}

					studentRegistrationForm.setGuardianPhotoUrl(image_path);


				}else{

					studentRegistrationForm.setGuardianPhotoUrl(studentRegistrationForm.getPreviousGuardImage());
				}

			}catch(Exception  e){

				logger.error(e.getMessage(), e);
				e.printStackTrace();

			}finally{
				if (outputStream != null) {

					outputStream.close();
				}
			}



			try {


				if (studentRegistrationForm.getTransferFile() != null) {

					String extension=null;

					int i = studentRegistrationForm.getTransferFile().getFileName().lastIndexOf('.');
					if (i >= 0) {
						extension = studentRegistrationForm.getTransferFile().getFileName().substring(i+1);
					}

					String idProof_path = "FIles/STUDENTTRANSFERCER/TransferCer_" + studentid.trim()+ "." + extension;


					String filePath = request.getRealPath("/") + "FIles/STUDENTTRANSFERCER/TransferCer_" + studentid.trim()
					+ "." +extension;
					if (studentRegistrationForm.getTransferFile().getFileSize() > 0) {

						byte[] btDataFile = studentRegistrationForm.getTransferFile().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();
					} else {

						idProof_path = studentRegistrationForm.getPreviousTransferCer();
					}

					studentRegistrationForm.setTransferfile(idProof_path);

				}else{
					studentRegistrationForm.setTransferfile(studentRegistrationForm.getPreviousTransferCer());
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
			}

			//to get certificate1 path

			try {

				if (studentRegistrationForm.getCertificate1() != null) {

					String extension=null;

					int k = studentRegistrationForm.getCertificate1().getFileName().lastIndexOf('.');

					if (k >= 0) {

						extension = studentRegistrationForm.getCertificate1().getFileName().substring(k+1);
					}

					String certificate1_path = "FIles/STUDENTTRANSFERCER/Certificate1_" + studentid.trim()+ "." + extension;

					System.out.println("certificate1_path::"+certificate1_path);

					String filePath = request.getRealPath("/") + "FIles/STUDENTTRANSFERCER/Certificate1_" + studentid.trim()
					+ "." +extension;

					if (studentRegistrationForm.getCertificate1().getFileSize() > 0) {

						byte[] btDataFile = studentRegistrationForm.getCertificate1().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();

					} else {
						certificate1_path = "";
					}


					studentRegistrationForm.setCertificate1Url(certificate1_path);

				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
			}

			//to get certifiate2 path
			try {

				if (studentRegistrationForm.getCertificate2() != null) {

					String extension=null;

					int k = studentRegistrationForm.getCertificate2().getFileName().lastIndexOf('.');

					if (k >= 0) {

						extension = studentRegistrationForm.getCertificate2().getFileName().substring(k+1);
					}

					String certificate2_path = "FIles/STUDENTTRANSFERCER/Certificate2_" + studentid.trim()+ "." + extension;

					System.out.println("certificate2_path::"+certificate2_path);

					String filePath = request.getRealPath("/") + "FIles/STUDENTTRANSFERCER/Certificate2_" + studentid.trim()
					+ "." +extension;

					if (studentRegistrationForm.getCertificate2().getFileSize() > 0) {

						byte[] btDataFile = studentRegistrationForm.getCertificate2().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();

					} else {
						certificate2_path = "";
					}


					studentRegistrationForm.setCertificate2Url(certificate2_path);

				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
			}

			//to get certificate3 path

			try {

				if (studentRegistrationForm.getCertificate3() != null) {

					String extension=null;

					int k = studentRegistrationForm.getCertificate3().getFileName().lastIndexOf('.');

					if (k >= 0) {

						extension = studentRegistrationForm.getCertificate3().getFileName().substring(k+1);
					}

					String certificate3_path = "FIles/STUDENTTRANSFERCER/Certificate3_" + studentid.trim()+ "." + extension;

					System.out.println("certificate3_path::"+certificate3_path);

					String filePath = request.getRealPath("/") + "FIles/STUDENTTRANSFERCER/Certificate3_" + studentid.trim()
					+ "." +extension;

					if (studentRegistrationForm.getCertificate3().getFileSize() > 0) {

						byte[] btDataFile = studentRegistrationForm.getCertificate3().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();

					} else {
						certificate3_path = "";
					}


					studentRegistrationForm.setCertificate3Url(certificate3_path);

				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
			}
			//to get certificate4 path


			try {

				if (studentRegistrationForm.getCertificate4() != null) {

					String extension=null;

					int k = studentRegistrationForm.getCertificate4().getFileName().lastIndexOf('.');

					if (k >= 0) {

						extension = studentRegistrationForm.getCertificate4().getFileName().substring(k+1);
					}

					String certificate4_path = "FIles/STUDENTTRANSFERCER/Certificate4_" + studentid.trim()+ "." + extension;

					System.out.println("certificate4_path::"+certificate4_path);

					String filePath = request.getRealPath("/") + "FIles/STUDENTTRANSFERCER/Certificate4_" + studentid.trim()
					+ "." +extension;

					if (studentRegistrationForm.getCertificate4().getFileSize() > 0) {

						byte[] btDataFile = studentRegistrationForm.getCertificate4().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();

					} else {
						certificate4_path = "";
					}


					studentRegistrationForm.setCertificate4Url(certificate4_path);

				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
			}
			//to get cetificate5 path

			try {

				if (studentRegistrationForm.getCertificate5() != null) {

					String extension=null;

					int k = studentRegistrationForm.getCertificate5().getFileName().lastIndexOf('.');

					if (k >= 0) {

						extension = studentRegistrationForm.getCertificate5().getFileName().substring(k+1);
					}

					String certificate5_path = "FIles/STUDENTTRANSFERCER/Certificate5_" + studentid.trim()+ "." + extension;

					System.out.println("certificate5_path::"+certificate5_path);

					String filePath = request.getRealPath("/") + "FIles/STUDENTTRANSFERCER/Certificate5_" + studentid.trim()
					+ "." +extension;

					if (studentRegistrationForm.getCertificate5().getFileSize() > 0) {

						byte[] btDataFile = studentRegistrationForm.getCertificate5().getFileData();
						File of = new File(filePath);
						FileOutputStream osf = new FileOutputStream(of);
						osf.write(btDataFile);
						osf.flush();

					} else {
						certificate5_path = "";
					}

					studentRegistrationForm.setCertificate5Url(certificate5_path);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
			}

			studentRegistrationForm.setStudentPhoto(realPath);
			studentRegistrationForm.setModifyUser(createdUser);
			studentRegistrationForm.setStudentId(studentid);


			success = new StudentRegistrationDelegate().modifyStudentDetails(studentRegistrationForm);


			System.out.println("Success::::"+success);

			if (success.equalsIgnoreCase("successMessage")) {
				System.out.println("Successffffsfsf::::"+success);
				request.setAttribute("successMessage","Updating Record Progressing...");

			} else if (success.equalsIgnoreCase("errorMessage")) {

				request.setAttribute("errorMessage","Student Record Not Updated");

			} else {
				request.setAttribute("errorMessage","Student registerd already with this Details");
			}

			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userType=userDetailVO.getUserNametype();
			String userCode=userDetailVO.getUserId();
			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			if(academic_year==null || academic_year==""||academic_year.equalsIgnoreCase("")){
				System.out.println("HelperClass.getCurrentYearID()"+HelperClass.getCurrentYearID());
				academic_year=HelperClass.getCurrentYearID();
			}

			String schoolLocation=(String) request.getSession(false).getAttribute("current_schoolLocation");
			List<StudentRegistrationVo> list =null;
			list = new StudentRegistrationDelegate().getStudentDetails(userType,userCode,academic_year+","+schoolLocation);
			request.setAttribute(MessageConstants.STUDENTDETAILSLIST, list);

			/*StudentRegistrationDelegate obj = new StudentRegistrationDelegate();
					List<StudentRegistrationVo> List = new ArrayList<StudentRegistrationVo>();

					String SearchName = request.getParameter("searchname");

					if(SearchName != null){

						List=obj.getStudentDetails(SearchName);
						request.setAttribute("searchname", SearchName);
						request.setAttribute("searchnamelist", SearchName);

					}
					else{
						UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
						String userType=userDetailVO.getUserNametype();
						String userCode=userDetailVO.getUserId();
						String academicyear = userDetailVO.getAcademicyear();

						List =obj.getStudentDetails(userType,userCode,academicyear);
					}*/

			/*List<StudentRegistrationVo> List = new StudentRegistrationDelegate().getStudentDetails();*/

			//request.setAttribute(MessageConstants.STUDENTDETAILSLIST, List);	

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : ModifyStudentDetails Ending");
		//return mapping.findForward("studentRegistration");

		return mapping.findForward(MessageConstants.ADDSTUDENTREGISTRATION);

	}

	public ActionForward deleteStudentSession(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : deleteStudentSession Starting");
		try {
			request.getSession(false).setAttribute("success", null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : deleteStudentSession Ending");
		return null;
	}

	public ActionForward studentSearchByParent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info("Control in StudentRegistrationAction : studentSearchByParent Starting");
		List<StudentRegistrationVo> studentSearchList = new ArrayList<StudentRegistrationVo>();
		StudentRegistrationVo registrationVo = new StudentRegistrationVo();
		StudentRegistrationDelegate delegate = new StudentRegistrationDelegate();
		try {
			String searchTerm = request.getParameter("searchTerm");

			registrationVo.setSearchTerm(searchTerm);
			System.out.println("sibling searchTerm "+searchTerm);
			studentSearchList = delegate.studentSearchByParent(registrationVo);

			JSONArray array = new JSONArray();
			array.put(studentSearchList);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, studentSearchList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : studentSearchByParent Ending");
		return null;
	}

	public ActionForward studentSearchbySibling(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info("Control in StudentRegistrationAction : studentSearchbySibling Starting");

		List<StudentRegistrationVo> searchStudentList = new ArrayList<StudentRegistrationVo>();
		try {
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			String searchterm = request.getParameter("searchTerm");

			registrationVo.setSearchTerm(searchterm);
			StudentRegistrationDaoImpl daoImpl = new StudentRegistrationDaoImpl();
			searchStudentList = daoImpl.studentSearchbySibling(registrationVo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : studentSearchbySibling Ending");
		return null;

	}

	public ActionForward validatePhoneNo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : validatePhoneNo Starting");
		try {
			StudentRegistrationDaoImpl registrationDaoImpl = new StudentRegistrationDaoImpl();
			String phoneNO = request.getParameter("phoneId").trim();

			String message = registrationDaoImpl.validatePhoneNo(phoneNO);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("message", message);

			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationAction : validatePhoneNo Ending");
		return null;

	}

	public ActionForward validateEmail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : validateEmail Starting");
		try {
			StudentRegistrationDaoImpl registrationDaoImpl = new StudentRegistrationDaoImpl();
			String email = request.getParameter("emailid").trim();

			String message = registrationDaoImpl.validateEmail(email);
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("message", message);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationAction : validateEmail Ending");
		return null;

	}

	public ActionForward getStudentCaste(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getStudentCaste Starting");
		try {
			List<StudentRegistrationVo> studentCastelist = new ArrayList<StudentRegistrationVo>();
			StudentRegistrationDelegate delegate = new StudentRegistrationDelegate();
			studentCastelist = delegate.getStudentCaste();

			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, studentCastelist);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getStudentCaste Ending");
		return null;
	}

	public ActionForward validateroleNumber(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : validateroleNumber Starting");
		try {
			StudentRegistrationDaoImpl registrationDaoImpl = new StudentRegistrationDaoImpl();
			String rollNumber = request.getParameter("rollNumber").trim();
			String locationId=request.getParameter("locationId");
			String message = registrationDaoImpl.validateRollNumber(rollNumber+","+locationId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("message", message);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationAction : validateroleNumber Ending");
		return null;

	}

	public ActionForward checkapplicationNo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : checkApplicationNo Starting");
		try {
			StudentRegistrationDaoImpl registrationDaoImpl = new StudentRegistrationDaoImpl();
			String applicationNo = request.getParameter("applicationNo").trim();

			String message = registrationDaoImpl
					.checkApplicationNo(applicationNo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("message", message);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationAction:checkApplicationNo Ending");
		return null;

	}

	public ActionForward getAllStudentsByClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getAllStudentsByClass Starting");
		try {

			String classname = request.getParameter("classid").trim();
			String accyear = request.getParameter("accyearId").trim();

			ArrayList<StudentInfoVO> studentList = new StudentRegistrationDelegate()
					.getAllStudentsDetails(classname, accyear);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("studentList", studentList);

			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationAction: getAllStudentsByClass Ending");
		return null;

	}

	public ActionForward getTransportCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getTransportCategory Starting");
		try {

			List<TransportTypeVO> tar_type_list = new StudentRegistrationDelegate()
					.transportypedetails();

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("transportCategory", tar_type_list);

			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationAction: getTransportCategory Ending");
		return null;

	}

	public ActionForward getTransportStages(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getTransportStages Starting");
		try {
			String accyear=request.getParameter("accyear");
			List<StageMasterVo> masterVos = new StudentRegistrationDelegate().getStageDetails(accyear);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("transportstages", masterVos);

			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationAction: getTransportStages Ending");
		return null;

	}

	public ActionForward getTransportCategoryType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getTransportCategoryType Starting");
		try {

			String transportTypeId = request.getParameter("typeId");

			String transportTypeStatus = new StudentRegistrationDelegate()
					.getTransportCategoryType(transportTypeId);


			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("transportTypeStatus", transportTypeStatus);

			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  StudentRegistrationAction: getTransportCategoryType Ending");
		return null;

	}

	public ActionForward studentSearchbyClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)

					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info("Control in StudentRegistrationAction : studentSearchbyClass Starting");

		List<StudentRegistrationVo> searchStudentList = new ArrayList<StudentRegistrationVo>();
		try {
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			String searchterm = request.getParameter("searchTerm");
			String accYearVal = request.getParameter("accYearVal");
			String classId = request.getParameter("classname");

			registrationVo.setSearchTerm(searchterm);
			registrationVo.setAcademicYearId(accYearVal);
			registrationVo.setClassDetailId(classId);

			StudentRegistrationDelegate registrationDelegate = new StudentRegistrationDelegate();
			searchStudentList = registrationDelegate
					.studentSearchbyClass(registrationVo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : studentSearchbyClass Ending");
		return null;

	}

	public ActionForward getCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction :getCategory Starting");
		String schoolLocation=null;
		schoolLocation=request.getParameter("locationId");
		if(schoolLocation==null || schoolLocation.equalsIgnoreCase("")){
			schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		}
		try {
			List<StudentRegistrationVo> CategoryList = new StudentRegistrationDelegate().getChildCategory(schoolLocation);
			JSONObject jsonObject = new JSONObject(CategoryList);
			jsonObject.accumulate("CategoryList", CategoryList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction :getCategory Ending");
		return null;
	}

	public ActionForward getClassDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassDetail Starting");
		try {

			String categoryVal = request.getParameter("categoryVal");
			System.out.println("categoryVal is in Action class:  "+categoryVal);


			List<StudentRegistrationVo> ClassList = new StudentRegistrationDelegate().getClassDetails(categoryVal);

			JSONObject jsonObject = new JSONObject(ClassList);
			jsonObject.accumulate("ClassList", ClassList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassDetail Ending");
		return null;
	}

	public ActionForward getClassSection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassSection Starting");

		String schoolLocation=null;
		schoolLocation=request.getParameter("locationId");
		if(schoolLocation==null || schoolLocation.equalsIgnoreCase("")){
			schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		}

		try {
			String classidVal = request.getParameter("classidVal");
			System.out.println("class id val is"+classidVal);
			List<StudentRegistrationVo> List = new StudentRegistrationDelegate().getSection(classidVal+","+schoolLocation);
			JSONObject jsonObject = new JSONObject(List);
			jsonObject.accumulate("sectionList", List);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction :getClassSection Ending");
		return null;
	}

	public ActionForward getConcessionDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getConcessionDetails Starting");

		try {
			List<StudentRegistrationVo> List = new StudentRegistrationDelegate()
					.getConcessionDetails();
			JSONObject object = new JSONObject();
			object.put("ConcessionDetails", List);
			response.getWriter().print(object);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getConcessionDetails Ending");
		return null;
	}

	public ActionForward deactivateStudent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info("Control in StudentRegistrationAction: deactivateStudent Starting");

		StudentRegistrationVo registrationVo = new StudentRegistrationVo();
		StudentRegistrationDelegate delegate = new StudentRegistrationDelegate();
		try {
			String deletestudentid = request.getParameter("studentid");

			registrationVo.setStudentId(deletestudentid);

			boolean status = delegate.deactivateStudent(registrationVo);

			JSONObject obj = new JSONObject();
			obj.put("status", status);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : deactivateStudent Ending");
		return null;
	}

	public ActionForward editStudent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info("Control in StudentRegistrationAction: editStudent Starting");

		StudentRegistrationVo registrationVo1 = new StudentRegistrationVo();
		StudentRegistrationDelegate delegate = new StudentRegistrationDelegate();

		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_REGISTRATION);
			System.out.println("Inside Edit option");
			String searchTermVal = request.getParameter("searchTerm");
			String searchTerm=searchTermVal.split(",")[0];
			String currentYear=searchTermVal.split(",")[1];
			System.out.println("searchTerm is "+searchTerm);

			registrationVo1.setSearchTerm(searchTerm);
			registrationVo1.setAcademicYear(currentYear);
			registrationVo1=delegate.editStudent(registrationVo1);


			request.setAttribute("studentSearchList", registrationVo1);


			request.setAttribute("successMessage", "");
			request.setAttribute("errorMessage", "");


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : editStudent Ending");

		return mapping.findForward(MessageConstants.ADDSTUDENTREGISTRATION);
	}


	public synchronized ActionForward downloadDocument(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: downloadDocument Starting");

		try {
			String docPath = request.getParameter("Path");
			response.setContentType("application/octet-stream");
			String fileName = "FileName";
			fileName=docPath;

			response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
			File docFile = new File(request.getRealPath("/") + docPath);
			response.setContentLength((int) docFile.length());

			FileInputStream input = new FileInputStream(docFile);
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;
			ServletOutputStream stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: downloadDocument Ending");

		return null;
	}


	public ActionForward downloadStudentDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : downloadStudentDetailsXLS  Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/studentfulldetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);


			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");
			if(academic_year==null || academic_year==""||academic_year.equalsIgnoreCase("")){
				System.out.println("HelperClass.getCurrentYearID()"+HelperClass.getCurrentYearID());
				academic_year=HelperClass.getCurrentYearID();
			}
			else
			{
				academic_year=academic_year;
			}

			List<registrationvo> streamList = new  StudentRegistrationDelegate()
					.StudentDetails(academic_year);


			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					streamList);
			Map parameters = new HashMap();


			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/studentfulldetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "studentfulldetailsxls Excel Report" };
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
					sheetNames);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					Boolean.FALSE);

			exporter.exportReport();

			pdfxls = new File(
					request.getRealPath("Reports/studentfulldetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=studentfulldetailsxls.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : downloadStudentDetailsXLS   Ending");
		return null;


	}

	public ActionForward downloadStudentDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : downloadStudentDetailsPDF  Starting");

		try {

			String academic_year = (String)request.getSession(false).getAttribute("current_academicYear");

			if(academic_year==null || academic_year==""||academic_year.equalsIgnoreCase("")){
				System.out.println("HelperClass.getCurrentYearID()"+HelperClass.getCurrentYearID());
				academic_year=HelperClass.getCurrentYearID();
			}
			else
			{
				academic_year = academic_year;
			}
			List<registrationvo> Details = new  StudentRegistrationDelegate()
					.StudentDetails(academic_year);

			String sourceFileName = request
					.getRealPath("Reports/studentfulldetailsPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					Details);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();

			parameters.put("Image", PropfilePath);


			/*parameters.put("Image", clientImage);

				parameters.put("ClientName", ClientName);

				parameters.put("ClientAddress", ClientAddress_l1);*/

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "studentfulldetailsPDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();

		}

		catch (Exception e)

		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : downloadStudentDetailsPDF  Ending");
		return null;

	}


	public ActionForward getSchoolLocation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassSection Starting");
		try {
			LocationBD obj = new LocationBD();
			List<LocationVO> list = new ArrayList<LocationVO>();
			list = obj.getLocationDetails();

			JSONObject jsonObject = new JSONObject(list);
			jsonObject.accumulate("locationList", list);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction :getClassSection Ending");
		return null;
	}

	public ActionForward getClassDetailWithOutStream(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassDetail Starting");

		String location = request.getParameter("location");
		List<StudentRegistrationVo> ClassList = null;

		try {
			if(location.equalsIgnoreCase("all")){
				location="%%";
			}
			ClassList = new StudentRegistrationDelegate().getClassDetailWithOutStream(location);
			JSONObject jsonObject = new JSONObject(ClassList);
			jsonObject.accumulate("ClassList", ClassList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:getClassDetail Ending");
		return null;
	}

	public ActionForward studentTempRegistrationList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:studentTempRegistrationList Starting");
		try {
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			String tempid=request.getParameter("tempid");
			registrationVo.setTempregid(tempid);
			List<StudentRegistrationVo> tempList = new StudentRegistrationDelegate().getTempRegistrationDetails(registrationVo);

			JSONArray array = new JSONArray();
			array.put(tempList);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, tempList);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:studentTempRegistrationList Ending");
		return null;
	}

	public ActionForward getClassByLocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: getClassByLocation Starting");
		try {

			String locationid = request.getParameter("locationid");



			List<StudentRegistrationVo> ClassList = new StudentRegistrationDelegate().getClassByLocation(locationid);

			JSONObject jsonObject = new JSONObject(ClassList);
			jsonObject.accumulate("ClassList", ClassList);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction: getClassByLocation Ending");
		return null;
	}

	public ActionForward singleStudentDetailsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : singleStudentDetailsList Starting");


		try {
			String studentId = request.getParameter("studentId");
			String accyear = request.getParameter("accyear");
			String locationid = request.getParameter("locationid");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().singleStudentConfDetails(studentId,accyear,locationid);

			request.setAttribute("studentSearchList", list);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : singleStudentDetailsList Ending");

		return mapping.findForward(MessageConstants.ADDSTUDENTCONFIDENTIAL);
	}


	public ActionForward singleStudentWithHeldDetailsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : singleStudentWithHeldDetailsList Starting");

		try {

			List<StudentRegistrationVo> list  = null;
			String studentId = request.getParameter("studentId");
			String accyear = request.getParameter("accyear");
			String locationid = request.getParameter("locationId");
			String flag = request.getParameter("flag");
			String status = request.getParameter("editsectionid");

			list = new StudentRegistrationDelegate().singleStudentWithHeldDetailsList(studentId,accyear,locationid,flag,status);
			/*singleStudentDetailsList*/
			request.setAttribute("studentSearchList",list);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentSearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : singleStudentWithHeldDetailsList Ending");

		return null;

	}

	public ActionForward individualStudentSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction    : individualStudentSearch Starting");

		try {
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			/*List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);*/
			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().individualStudentSearch(studentId);
			request.setAttribute("studentSearchList", list);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentSearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : individualStudentSearch Ending");

		return null;
	}
	public ActionForward showContactDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction    : individualStudentSearch Starting");

		try {
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);
			request.setAttribute("studentSearchList", list);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentSearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : individualStudentSearch Ending");

		return null;
	}

	public ActionForward searchAllAcademicYearDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction    : searchAllAcademicYearDetails Starting");

		String accYear = request.getParameter("accyear");
		String locationId = request.getParameter("locationId");


		List<StudentRegistrationVo> list= null;

		try {
			if(locationId.equalsIgnoreCase("all") && !(accYear.equalsIgnoreCase("all"))){

				locationId="%%";
				list = new StudentRegistrationDelegate().searchAllAcademicYearDetails(locationId,accYear);
			}
			else if(accYear.equalsIgnoreCase("all") && !(locationId.equalsIgnoreCase("all"))){

				accYear="%%";
				list = new StudentRegistrationDelegate().searchAllAcademicYearDetails(locationId,accYear);
			}
			else if(accYear.equalsIgnoreCase("all") && locationId.equalsIgnoreCase("all")){

				locationId="%%";
				accYear="%%";
				list = new StudentRegistrationDelegate().searchAllAcademicYearDetails(locationId,accYear);
			}else{
				list = new StudentRegistrationDelegate().searchAllAcademicYearDetails(locationId,accYear);
			}

			request.setAttribute("SearchList", list);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("SearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : searchAllAcademicYearDetails Ending");

		return null;
	}

	public ActionForward getStudentSearchByList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentSearchByList Starting");

		List<StudentRegistrationVo> list = null;

		try {

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");

			String searchTerm = request.getParameter("searchname");

			if(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")){
				list = new StudentRegistrationDelegate().getStudentSearchByList(searchTerm);
			}
			else if(locationid.equalsIgnoreCase("all") && !(accyear.equalsIgnoreCase("all"))){
				list = new StudentRegistrationDelegate().getStudentSearchListByAccYear(searchTerm,accyear);
			}
			else if(accyear.equalsIgnoreCase("all") && !(locationid.equalsIgnoreCase("all"))){
				list = new StudentRegistrationDelegate().getStudentSearchListByLocation(searchTerm,locationid);
			}
			else if(!(locationid.equalsIgnoreCase("all") && !accyear.equalsIgnoreCase("all")) && classname.equalsIgnoreCase("all")){
				list = new StudentRegistrationDelegate().getStudentSearchByFilter(searchTerm,locationid,accyear,classname);
			}
			else if(!(locationid.equalsIgnoreCase("all") && !accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all")) && sectionid.equalsIgnoreCase("all")){
				list = new StudentRegistrationDelegate().getStudentSearchByClass(searchTerm,locationid,accyear,classname);
			}
			else if(!(locationid.equalsIgnoreCase("all") && !accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all") && sectionid.equalsIgnoreCase("all"))){
				list = new StudentRegistrationDelegate().getStudentSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
			}

			request.setAttribute("SearchList", list);

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


	public ActionForward getfeedetailsofthestudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {


		String studentId = request.getParameter("studentid");
		String accYear = request.getParameter("accyear");
		String locationId = request.getParameter("locationId");


		String FeeCodeDetails = request.getParameter("studentid")+","+request.getParameter("accyear")+","+request.getParameter("locationId");

		FeeCollectionVo collectionVo = new FeeCollectionBD().getFeeCollectionAmount(FeeCodeDetails);
		List<FeeNameVo> list = collectionVo.getFeeNamelist();


		JSONObject jsonobj = new JSONObject();
		jsonobj.put("studentFeeList", list);
		response.getWriter().print(jsonobj);

		return null;
	}

	public ActionForward singleStudentDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : singleStudentDetail Starting");

		try {

			List<StudentRegistrationVo> list  = null;
			String studentId = request.getParameter("studentId");
			String accyear = request.getParameter("accyear");
			String locationid = request.getParameter("locationId");
			String flag = request.getParameter("flag");

			list = new StudentRegistrationDelegate().singleStudentDetailsList(studentId,accyear,locationid,flag);

			request.setAttribute("studentSearchList", list);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentSearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : singleStudentDetail Ending");

		return null;
	}

	public ActionForward AddConfidentialDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : AddConfidentialDetails Starting");

		String userName = null;
		String status = null;

		try {
			String entryDate = request.getParameter("entryDate");
			String comments = request.getParameter("comments");
			String studentId = request.getParameter("student_id");
			String accyear = request.getParameter("academicyear_id");
			String locationid = request.getParameter("location_id");

			userName = HelperClass.getCurrentUserID(request);

			status = new StudentRegistrationDelegate().AddConfidentialDetails(entryDate,comments,studentId,accyear,locationid,userName);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("confidentialstatus", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : AddConfidentialDetails Ending");

		return null;
	}


	public ActionForward AddWithHeldDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : AddWithHeldDetails Starting");

		String createdUser=null;
		String status = null;

		try {
			StudentRegistrationVo studentvo=new StudentRegistrationVo();

			String entryDate = request.getParameter("entryDate");
			String comments = request.getParameter("comments");
			String Fromdate = request.getParameter("Fromdate");
			String cancelcomment = request.getParameter("cancelcomment");
			String studentId = request.getParameter("student_id");
			String accyear = request.getParameter("academicyear_id");
			String locationid = request.getParameter("location_id");
			String sectionid = request.getParameter("sectionid");
			String withheldId = request.getParameter("withheldId");

			createdUser = HelperClass.getCurrentUserID(request);

			studentvo.setEntryDate(entryDate);
			studentvo.setComments(comments);
			studentvo.setCancelcomment(cancelcomment);
			studentvo.setCancelDate(Fromdate);
			studentvo.setStudentId(studentId);
			studentvo.setAccyear(accyear);
			studentvo.setLocationId(locationid);
			studentvo.setCreateUser(createdUser);
			studentvo.setStatus(sectionid);
			studentvo.setWithheldId(withheldId);

			status= new StudentRegistrationDelegate().AddWithHeldDetails(studentvo);
			/*AddConfidentialDetails*/
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentSearchList",status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : AddWithHeldDetails Ending");

		return null;
	}

	public ActionForward searchAllAccYearDetailsConfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction  : searchAllAccYearDetailsConfReport Starting");

		String accYear = request.getParameter("accyear");
		String locationId = request.getParameter("locationId");


		List<StudentRegistrationVo> list= null;

		try {
			if(locationId.equalsIgnoreCase("all") && !(accYear.equalsIgnoreCase("all"))){

				locationId="%%";
				list = new StudentRegistrationDelegate().searchAllAccYearDetailsConfReport(locationId,accYear);
			}
			else if(accYear.equalsIgnoreCase("all") && !(locationId.equalsIgnoreCase("all"))){

				accYear="%%";
				list = new StudentRegistrationDelegate().searchAllAccYearDetailsConfReport(locationId,accYear);
			}
			else if(accYear.equalsIgnoreCase("all") && locationId.equalsIgnoreCase("all")){

				locationId="%%";
				accYear="%%";
				list = new StudentRegistrationDelegate().searchAllAccYearDetailsConfReport(locationId,accYear);
			}else{
				list = new StudentRegistrationDelegate().searchAllAccYearDetailsConfReport(locationId,accYear);
			}

			request.setAttribute("SearchList", list);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("SearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : searchAllAccYearDetailsConfReport Ending");

		return null;
	}

	public ActionForward getStudentSearchByConfReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentSearchByConfReport Starting");

		List<StudentRegistrationVo> list = null;

		try {

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");

			String searchTerm = request.getParameter("searchname".trim());

			if(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")){

				list = new StudentRegistrationDelegate().getStudentSearchByConfReport(searchTerm);
			}
			else if(locationid.equalsIgnoreCase("all") && !(accyear.equalsIgnoreCase("all"))){

				list = new StudentRegistrationDelegate().getConfSearchListByAccYear(searchTerm,accyear);
			}
			else if(accyear.equalsIgnoreCase("all") && !(locationid.equalsIgnoreCase("all"))){

				list = new StudentRegistrationDelegate().getConfSearchListByLocation(searchTerm,locationid);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")) && classname.equalsIgnoreCase("all")){

				list = new StudentRegistrationDelegate().getConfSearchByFilter(searchTerm,locationid,accyear);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all")) && sectionid.equalsIgnoreCase("all")){

				list = new StudentRegistrationDelegate().getConfSearchByClass(searchTerm,locationid,accyear,classname);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all") && sectionid.equalsIgnoreCase("all"))){

				list = new StudentRegistrationDelegate().getConfSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
			}

			request.setAttribute("SearchList", list);

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
				+ " Control in AdminMenuAction : getStudentSearchByConfReport Ending");

		return null;
	}


	public ActionForward saveStudentPromotion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : saveStudentPromotion : Starting");
		//StudentRegistrationForm formObj= (StudentRegistrationForm) form;

		String result = null;
		try {
			System.out.println("Inside the saveStudentPromotion");
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");

			String student = request.getParameter("studentIdArray");
			String[] studentId=student.split(",");
			String accyearf = request.getParameter("academicyear_fromArray");
			String[] accyearfrom=accyearf.split(",");
			String admission = request.getParameter("admissionNoArray");
			String[] admissionno=admission.split(",");
			String roll = request.getParameter("rollNoArray");
			String[] rollno=roll.split(",");
			String classf = request.getParameter("class_fromArray");
			String[] classfrom=classf.split(",");
			String sectionf = request.getParameter("section_fromArray");
			String[] sectionfrom=sectionf.split(",");
			String specf = request.getParameter("specilization_fromArray");
			String[] specfrom=specf.split(",");
			String classt = request.getParameter("class_toArray");
			String[] classto=classt.split(",");
			String statussingle = request.getParameter("statusArray");
			String[] status=statussingle.split(",");
			String sectiont = request.getParameter("section_toArray");
			String[] sectionto=sectiont.split(",");
			String sepct = request.getParameter("specilization_toArray");
			String[] sepcto=sepct.split(",");
			String accyeart = request.getParameter("academicyear_toArray");
			String[] accyearto=accyeart.split(",");
			String locationId = request.getParameter("locationIdArray");
			String[] location = locationId.split(",");
			System.out.println("location is "+location);

			String userCode = userDetailVO.getUserId();
			registrationVo.setCreateUser(userCode);
			registrationVo.setLocationIdArray(location);
			registrationVo.setStudentIdArray(studentId);
			registrationVo.setAcademicyear_fromArray(accyearfrom);
			registrationVo.setAdmissionNoArray(admissionno);
			registrationVo.setRollNoArray(rollno);
			registrationVo.setClass_fromArray(classfrom);
			registrationVo.setSection_fromArray(sectionfrom);
			registrationVo.setSpecilization_fromArray(specfrom);
			registrationVo.setClass_toArray(classto);
			registrationVo.setStatusArray(status);
			registrationVo.setSection_toArray(sectionto);
			registrationVo.setSpecilization_toArray(sepcto);
			registrationVo.setAcademicyear_toArray(accyearto);


			result = delegateObj.saveStudentPromotion(registrationVo);
			System.out.println(result);
			JSONObject object = new JSONObject();
			object.put("expense_Result", result);
			response.getWriter().print(object);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeExpenseDetailsAction : saveEmployeeExpense ");
		return null;
	}

	public ActionForward getNextAcademicYearId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : setUpMenu : Starting");

		String result = null;
		try {

			String academicyearid=request.getParameter("academicyearid");
			result = new StudentRegistrationDelegate().getNextAcademicYearId(academicyearid);
			System.out.println("result is "+result);
			JSONObject object = new JSONObject();
			object.put("academicyearid", result);
			response.getWriter().print(object);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeExpenseDetailsAction : saveEmployeeExpense ");
		return null;
	}
	public ActionForward getexaminationdetailsofthestudent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String studentid = request.getParameter("studentid");
		String loc_id = request.getParameter("locid");
		String acyyear = request.getParameter("acyyear");

		StudentRegistrationVo svo = new StudentRegistrationVo();
		svo.setClassDetailId(classid);
		svo.setClassSectionId(sectionid);
		svo.setStudentId(studentid);
		svo.setLocationId(loc_id);
		svo.setAcademicYearId(acyyear);

		List<ExaminationDetailsVo> list = new StudentRegistrationDelegate().getExaminationDetails(svo);
		request.setAttribute("examDetailsList", list);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("examDetailsList", list);
		response.getWriter().print(jsonobj);
		return null;
	}

	public ActionForward getStudentClassDivisionWisePromotionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentClassDivisionWisePromotionList Starting");

		try {


			String academic_year=request.getParameter("accyear");
			String location=request.getParameter("locationId");
			String classId=request.getParameter("classId");
			String sectionid=request.getParameter("sectionid");

			List<StudentRegistrationVo> List = null;

			if(sectionid.equalsIgnoreCase("all")){

				sectionid="%%";
				List = new StudentRegistrationDelegate().getAllStudentPromotionClassSectionDetails(academic_year,location,classId,sectionid);
			}
			else{

				List = new StudentRegistrationDelegate().getAllStudentPromotionClassSectionDetails(academic_year,location,classId,sectionid);
			}



			JSONObject object = new JSONObject();
			object.put("studentdetailslist", List);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentClassDivisionWisePromotionList Ending");

		return null;
	}

	public ActionForward toCheckNextClassAvailable(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : AddConfidentialDetails Starting");

		String userName = null;
		String status = null;

		try {
			String locationId = request.getParameter("locationId");
			String toclass = request.getParameter("toClass");

			userName = HelperClass.getCurrentUserID(request);

			status = new StudentRegistrationDelegate().toCheckNextClassAvailable(toclass,locationId);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("checkClass", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : AddConfidentialDetails Ending");

		return null;
	}

	public ActionForward getAllAcademicYearPromotedDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getAllAcademicYearPromotedDetails Starting");

		String accYear = request.getParameter("accyear");
		String locationId = request.getParameter("locationId");
		System.out.println("accYear "+accYear);
		System.out.println("locationId "+locationId);

		List<StudentRegistrationVo> list= null;

		try {
			if(locationId.equalsIgnoreCase("all") && !(accYear.equalsIgnoreCase("all"))){

				locationId="%%";
				list = new StudentRegistrationDelegate().getAllAcademicYearPromotedDetails(locationId,accYear);
			}
			else if(accYear.equalsIgnoreCase("all") && !(locationId.equalsIgnoreCase("all"))){

				accYear="%%";
				list = new StudentRegistrationDelegate().getAllAcademicYearPromotedDetails(locationId,accYear);
			}
			else if(accYear.equalsIgnoreCase("all") && locationId.equalsIgnoreCase("all")){

				locationId="%%";
				accYear="%%";
				list = new StudentRegistrationDelegate().getAllAcademicYearPromotedDetails(locationId,accYear);
			}else{
				list = new StudentRegistrationDelegate().getAllAcademicYearPromotedDetails(locationId,accYear);
			}

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("StudentPromotedList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getAllAcademicYearPromotedDetails Ending");

		return null;
	}

	public ActionForward getStudentPromotedSearchList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentPromotedSearchList Starting");

		List<StudentRegistrationVo> list = null;

		try {

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");

			String searchTerm = request.getParameter("searchname".trim());

			if(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")){

				list = new StudentRegistrationDelegate().getStudentPromotedSearchByList(searchTerm);
			}
			else if(locationid.equalsIgnoreCase("all") && !(accyear.equalsIgnoreCase("all"))){

				list = new StudentRegistrationDelegate().getStudentPromotedSearchListByAccYear(searchTerm,accyear);
			}
			else if(accyear.equalsIgnoreCase("all") && !(locationid.equalsIgnoreCase("all"))){

				list = new StudentRegistrationDelegate().getStudentPromotedSearchListByLocation(searchTerm,locationid);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")) && classname.equalsIgnoreCase("all")){

				list = new StudentRegistrationDelegate().getStudentPromotedSearchByFilter(searchTerm,locationid,accyear);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all")) && sectionid.equalsIgnoreCase("all")){

				list = new StudentRegistrationDelegate().getStudentPromotedSearchByClass(searchTerm,locationid,accyear,classname);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all") && sectionid.equalsIgnoreCase("all"))){

				list = new StudentRegistrationDelegate().getStudentPromotedSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
			}

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("StudentPromotedSearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentPromotedSearchList Ending");

		return null;
	}

	public ActionForward getStudentPromotingSearchList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentSearchByList Starting");

		List<StudentRegistrationVo> list = null;

		try {

			String locationid = request.getParameter("locationId");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");

			if(classname==null || classname.equalsIgnoreCase("") || classname.equalsIgnoreCase("all")) {
				classname="%%";
			}
			if(sectionid==null || sectionid.equalsIgnoreCase("") || sectionid.equalsIgnoreCase("all")) {
				sectionid="%%";
			}
			String searchTerm = request.getParameter("searchname".trim());

			list = new StudentRegistrationDaoImpl().getAllStudentPromotionClassSearchDetails(accyear, locationid, classname, sectionid, searchTerm);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("StudentPromotingSearchList", list);
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

	//	public ActionForward getStudentDemotedSearchList(ActionMapping mapping, ActionForm form,
	//			HttpServletRequest request, HttpServletResponse response)
	//					throws Exception {
	//
	//		logger.setLevel(Level.DEBUG);
	//		JLogger.log(0, JDate.getTimeString(new Date())
	//				+ MessageConstants.START_POINT);
	//		logger.info(JDate.getTimeString(new Date())
	//				+ " Control in AdminMenuAction : getStudentSearchByList Starting");
	//		
	//		List<StudentRegistrationVo> list = null;
	//		
	//		try {
	//			
	//			String locationid = request.getParameter("location");
	//			String accyear = request.getParameter("accyear");
	//			String classname = request.getParameter("classId");
	//			String sectionid = request.getParameter("sectionid");
	//			
	//			String searchTerm = request.getParameter("searchname".trim());
	//
	//			if(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")){
	//				
	//				list = new StudentRegistrationDelegate().getStudentSearchByList(searchTerm);
	//				list = new StudentRegistrationDelegate().getStudentDemotedSearchByList(searchTerm);
	//			}
	//			else if(locationid.equalsIgnoreCase("all") && !(accyear.equalsIgnoreCase("all"))){
	//				
	//				list = new StudentRegistrationDelegate().getConfSearchListByAccYear(searchTerm,accyear);
	//				list = new StudentRegistrationDelegate().getStudentDemotedSearchListByAccYear(searchTerm,accyear);
	//			}
	//			else if(accyear.equalsIgnoreCase("all") && !(locationid.equalsIgnoreCase("all"))){
	//				
	//				list = new StudentRegistrationDelegate().getConfSearchListByLocation(searchTerm,locationid);
	//				list = new StudentRegistrationDelegate().getStudentDemotedSearchListByLocation(searchTerm,locationid);
	//			}
	//			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")) && classname.equalsIgnoreCase("all")){
	//				
	//				list = new StudentRegistrationDelegate().getConfSearchByFilter(searchTerm,locationid,accyear);
	//				list = new StudentRegistrationDelegate().getStudentDemotedSearchByFilter(searchTerm,locationid,accyear);
	//			}
	//			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all")) && sectionid.equalsIgnoreCase("all")){
	//				
	//				list = new StudentRegistrationDelegate().getConfSearchByClass(searchTerm,locationid,accyear,classname);
	//				list = new StudentRegistrationDelegate().getStudentDemotedSearchByClass(searchTerm,locationid,accyear,classname);
	//			}
	//			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all") && sectionid.equalsIgnoreCase("all"))){
	//				
	//				list = new StudentRegistrationDelegate().getConfSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
	//				list = new StudentRegistrationDelegate().getStudentDemotedSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
	//			}
	//			
	//			JSONObject jsonobj = new JSONObject();
	//			jsonobj.put("StudentDemotedSearchList", list);
	//			response.getWriter().print(jsonobj);
	//
	//		} catch (Exception e) {
	//			logger.error(e.getMessage(), e);
	//			e.printStackTrace();
	//		}
	//
	//		JLogger.log(0, JDate.getTimeString(new Date())
	//				+ MessageConstants.END_POINT);
	//		logger.info(JDate.getTimeString(new Date())
	//				+ " Control in AdminMenuAction : getStudentSearchByList Ending");
	//
	//		return null;
	//	}

	public ActionForward updateStudentPromotion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction    : saveStudentPromotion : Starting");
		//StudentRegistrationForm formObj= (StudentRegistrationForm) form;

		String result = null;
		try {
			System.out.println("Inside the saveStudentPromotion");
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			StudentRegistrationDelegate delegateObj = new StudentRegistrationDelegate();
			String studentId=request.getParameter("studentId");
			String accyear=request.getParameter("accyear");
			String locationId=request.getParameter("locationId");
			String status=request.getParameter("status");
			String newsection=request.getParameter("newsection");
			String newspec=request.getParameter("newspec");
			String promotedId=request.getParameter("promotedId");


			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");


			String userCode = userDetailVO.getUserId();
			registrationVo.setCreateUser(userCode);
			registrationVo.setStudentId(studentId);
			registrationVo.setAcademicYearId(accyear);
			registrationVo.setLocationId(locationId);
			registrationVo.setStudentStatus(status);
			registrationVo.setNewsection(newsection);
			registrationVo.setNewspecilaization(newspec);
			registrationVo.setPromotedId(promotedId);

			result = delegateObj.saveStudentPromotion(registrationVo);
			System.out.println(result);
			JSONObject object = new JSONObject();
			object.put("expense_Result", result);
			response.getWriter().print(object);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in EmployeeExpenseDetailsAction : saveEmployeeExpense ");
		return null;
	}

	public ActionForward getexaminationcodeslist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Whether it is calling the method or not");
		String loc_id=request.getParameter("loc_id");
		String Acyear_id=request.getParameter("acyear");
		List<ExaminationDetailsVo> list = new StudentRegistrationDelegate().getExaminationCodes(loc_id,Acyear_id);

		JSONObject jsonobj = new JSONObject();
		jsonobj.put("examCodeslist", list);
		response.getWriter().print(jsonobj);
		return null;
	}
	public ActionForward getExamname(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String examcode =request.getParameter("examcode");
		String examname= new StudentRegistrationDelegate().getExamName(examcode);
		System.out.println("What it is returnin from daoimpl:" +examname);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("examnamelist", examname);
		response.getWriter().print(jsonobj);
		return null;
	}
	public ActionForward getstudentattendance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String stud_id= request.getParameter("studentid");


		List<StudentAttendanceVo> list = new StudentRegistrationDelegate().getStudentAttendance(stud_id);
		request.setAttribute("studentattendance", list);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("studentattendance", list);
		response.getWriter().print(jsonobj);
		return null;


	}
	public ActionForward getStudentAppraisal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String stud_id= request.getParameter("stu_id");
		String loc_id=request.getParameter("loc_id");
		String acyear_id=request.getParameter("acyearid");

		StudentRegistrationVo spvo = new StudentRegistrationVo();
		spvo.setLocationId(loc_id);
		spvo.setAcademicYearId(acyear_id);
		spvo.setStudentId(stud_id);
		List<StudentAttendanceVo> list = new StudentRegistrationDelegate().getStudentAppraisal(spvo);
		request.setAttribute("studentappraisal", list);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("studentappraisal", list);
		response.getWriter().print(jsonobj);
		return null;
	}
	public ActionForward getexaminationdetailsofthestudentbasedexams(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("it is coming inside the antoher action or not");

		String classid = request.getParameter("classid");
		String sectionid = request.getParameter("sectionid");
		String studentid = request.getParameter("studentid");
		String loc_id = request.getParameter("locid");
		String acyyear = request.getParameter("acyyear");
		String examcode = request.getParameter("examcode");
		String examname = request.getParameter("examname");
		System.out.println("ExamCode  "+examcode);

		StudentRegistrationVo svo = new StudentRegistrationVo();
		svo.setClassDetailId(classid);
		svo.setClassSectionId(sectionid);
		svo.setStudentId(studentid);
		svo.setLocationId(loc_id);
		svo.setAcademicYearId(acyyear);
		svo.setExam_code(examcode);
		svo.setExam_name(examname);

		List<ExaminationDetailsVo> list = new StudentRegistrationDelegate().getExaminationDetailsBasedonExams(svo);
		request.setAttribute("examDetailsList", list);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("examDetailsList", list);
		response.getWriter().print(jsonobj);


		return null;
	}


	public ActionForward deleteConfidentialDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : deleteConfidentialDetails Starting");

		String status = null;

		try {
			String deleteId = request.getParameter("deleteid");

			System.out.println("deleteId :"+deleteId);


			status = new StudentRegistrationDelegate().deleteConfidentialDetails(deleteId);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("confidentialstatus", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : deleteConfidentialDetails Ending");

		return null;
	}

	public ActionForward deleteWithHeldDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : deleteWithHeldDetails Starting");

		String status = null;

		try {
			String deleteId = request.getParameter("deleteid");

			System.out.println("deleteId :"+deleteId);


			status = new StudentRegistrationDelegate().deleteWithHeldDetails(deleteId); 
			/*deleteConfidentialDetails*/
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentSearchList",status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : deleteWithHeldDetails Ending");

		return null;
	}

	public ActionForward EditConfidentialDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : EditConfidentialDetails Starting");

		String status = null;

		try {
			String entryDate = request.getParameter("entrydate");
			String comments = request.getParameter("comment");
			String editId = request.getParameter("editid");

			System.out.println("entryDate :"+entryDate);
			System.out.println("comments :"+comments);
			System.out.println("editId:"+editId);

			status = new StudentRegistrationDelegate().editConfidentialDetails(entryDate,comments,editId);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("confidentialstatus", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : EditConfidentialDetails Ending");

		return null;
	}


	public ActionForward EditWithHeldDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : EditWithHeldDetails Starting");
		String createdUser=null;
		String status = null;

		try {
			StudentRegistrationVo studentvo=new StudentRegistrationVo();

			String entryDate = request.getParameter("entrydate");
			String comments = request.getParameter("comment");
			String Fromdate = request.getParameter("Fromdate");
			String cancelcomment = request.getParameter("cancelcomment");
			String studentId = request.getParameter("student_id");
			String accyear = request.getParameter("academicyear_id");
			String locationid = request.getParameter("location_id");
			String sectionid = request.getParameter("sectionid");
			String withheldId = request.getParameter("editid");
			createdUser = HelperClass.getCurrentUserID(request);
			System.out.println(entryDate);

			studentvo.setEntryDate(entryDate);
			studentvo.setComments(comments);
			studentvo.setCancelcomment(cancelcomment);
			studentvo.setCancelDate(Fromdate);
			studentvo.setStudentId(studentId);
			studentvo.setAccyear(accyear);
			studentvo.setLocationId(locationid);
			studentvo.setStatus(sectionid);
			studentvo.setWithheldId(withheldId);
			studentvo.setCreateUser(createdUser);

			status = new StudentRegistrationDelegate().EditWithHeldDetails(studentvo);
			/*editConfidentialDetails*/
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentSearchList", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : EditWithHeldDetails Ending");

		return null;
	}

	public ActionForward getStudentSearchByFeeCollection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : getStudentSearchByList Starting");

		List<StudentRegistrationVo> list = null;

		try {

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			String sectionid = request.getParameter("sectionid");

			System.out.println("Academic year from action:" +accyear);
			String searchTerm = request.getParameter("searchname".trim());

			if(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")){
				list = new StudentRegistrationDelegate().getStudentFeeSearchByList(searchTerm, accyear);
			}


			else if(locationid.equalsIgnoreCase("all") && !(accyear.equalsIgnoreCase("all"))){

				list = new StudentRegistrationDelegate().getStudentFeeSearchListByAccYear(searchTerm,accyear);
			}
			else if(accyear.equalsIgnoreCase("all") && !(locationid.equalsIgnoreCase("all"))){

				list = new StudentRegistrationDelegate().getStudentSearchListByLocation(searchTerm,locationid);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all")) && classname.equalsIgnoreCase("all")){

				list = new StudentRegistrationDelegate().getStudentSearchByFeeFilter(searchTerm,locationid,accyear);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all")) && sectionid.equalsIgnoreCase("all")){

				list = new StudentRegistrationDelegate().getStudentSearchByFeeClass(searchTerm,locationid,accyear,classname);
			}
			else if(!(locationid.equalsIgnoreCase("all") && accyear.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all") && sectionid.equalsIgnoreCase("all"))){

				list = new StudentRegistrationDelegate().getStudentFeeSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid);
			}

			request.setAttribute("SearchList", list);

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




	public ActionForward searchAllAcademicYearFeeDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction    : searchAllAcademicYearDetails Starting");

		String accYear = request.getParameter("accyear");
		String locationId = request.getParameter("locationId");


		List<StudentRegistrationVo> list= null;

		try {
			if(locationId.equalsIgnoreCase("all") && !(accYear.equalsIgnoreCase("all"))){

				locationId="%%";
				list = new StudentRegistrationDelegate().searchAllAcademicYearDetails(locationId,accYear);
			}
			else if(accYear.equalsIgnoreCase("all") && !(locationId.equalsIgnoreCase("all"))){

				accYear="%%";
				list = new StudentRegistrationDelegate().searchAllAcademicYearDetails(locationId,accYear);
			}
			else if(accYear.equalsIgnoreCase("all") && locationId.equalsIgnoreCase("all")){

				locationId="%%";
				accYear="%%";
				list = new StudentRegistrationDelegate().searchAllAcademicYearDetails(locationId,accYear);
			}else{
				list = new StudentRegistrationDelegate().searchAllAcademicYearDetails(locationId,accYear);
			}

			request.setAttribute("SearchList", list);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("SearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : searchAllAcademicYearDetails Ending");

		return null;

	}
	public ActionForward saveRollNo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("it is coming inside the antoher action or not");
		String studentId[] = request.getParameter("studentId").split(",");
		String accYear[] = request.getParameter("accYear").split(",");;
		String schoolCode[] = request.getParameter("schoolCode").split(",");;
		String rollNo[] = request.getParameter("rollNo").split(",");;
		StudentRegistrationVo svo = new StudentRegistrationVo();

		svo.setStudentIdArray(studentId);
		svo.setAcademicyear_toArray(accYear);
		svo.setLocationIdArray(schoolCode);
		svo.setRollNoArray(rollNo);

		String status=new StudentRegistrationDaoImpl().saveRollNo(svo);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("status", status);
		response.getWriter().print(jsonobj);

		return null;
	}

	public ActionForward getAllAcademicYearDemotedDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction    : getAllAcademicYearDemotedDetails Starting");

		String accYear = request.getParameter("accyear");
		String locationId = request.getParameter("locationId");
		System.out.println("accYear "+accYear);
		System.out.println("locationId "+locationId);

		List<StudentRegistrationVo> list= null;

		try {
			if(locationId.equalsIgnoreCase("all") && !(accYear.equalsIgnoreCase("all"))){

				locationId="%%";
				list = new StudentRegistrationDelegate().getAllAcademicYearDemotedDetails(locationId,accYear);
			}
			else if(accYear.equalsIgnoreCase("all") && !(locationId.equalsIgnoreCase("all"))){

				accYear="%%";
				list = new StudentRegistrationDelegate().getAllAcademicYearDemotedDetails(locationId,accYear);
			}
			else if(accYear.equalsIgnoreCase("all") && locationId.equalsIgnoreCase("all")){

				locationId="%%";
				accYear="%%";
				list = new StudentRegistrationDelegate().getAllAcademicYearDemotedDetails(locationId,accYear);
			}else{
				list = new StudentRegistrationDelegate().getAllAcademicYearDemotedDetails(locationId,accYear);
			}

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("StudentDemotedList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getAllAcademicYearDemotedDetails Ending");

		return null;
	}

	public ActionForward toCheckNextYearAvailable(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : toCheckNextYearAvailable Starting");

		String status = null;

		try {
			String accyearid = request.getParameter("AccYear");
			System.out.println("accyearid "+accyearid);
			status = new StudentRegistrationDelegate().toCheckNextYearAvailable(accyearid);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("checkNextYear", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : toCheckNextYearAvailable Ending");

		return null;
	}
	
	
	public ActionForward getStudentDetailsByJsInRegistration(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Starting");

		List<StudentRegistrationVo> List = null;
		try {
			StudentRegistrationVo data= new StudentRegistrationVo();
			String locationId=request.getParameter("locationId");
			String academicYear=request.getParameter("academicYear");
			String classId=request.getParameter("classId");
			String divisionId=request.getParameter("divisionId");
			String searchTerm=request.getParameter("searchTerm");
			String show_per_page=request.getParameter("show_per_page");
			String startPoint=request.getParameter("startPoint");
			
			
			data.setShow_per_page(Integer.parseInt(show_per_page));
			data.setStartPoint(Integer.parseInt(startPoint));
			data.setLocationId(locationId);
			data.setAcademicYear(academicYear);
			data.setClasscode(classId);
			data.setSection_id(divisionId);
			data.setSearchTerm(searchTerm);

			List = new StudentRegistrationDaoImpl().getStudentDetailsByJsInRegistration(data);
			System.out.println("after setting all data");
            System.out.println("after setting all data");

			JSONObject obj=new JSONObject();
			obj.put(MessageConstants.STUDENTDETAILSLIST, List);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Ending");

		return null;
	}
	public ActionForward studentListByJs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Starting");

		List<StudentRegistrationVo> List = null;
		try {
			StudentRegistrationVo data= new StudentRegistrationVo();
			String locationId=request.getParameter("locationId");
			String academicYear=request.getParameter("academicYear");
			String classId=request.getParameter("classId");
			String divisionId=request.getParameter("divisionId");
			String searchTerm=request.getParameter("searchTerm");

			data.setLocationId(locationId);
			data.setAcademicYear(academicYear);
			data.setClasscode(classId);
			data.setSection_id(divisionId);
			data.setSearchTerm(searchTerm);

			List = new StudentRegistrationDelegate().getStudentDetailsByJs(data);

			JSONObject obj=new JSONObject();
			obj.put(MessageConstants.STUDENTDETAILSLIST, List);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : studentList Ending");

		return null;
	}
//edited by anu
	public ActionForward getStudentcontact(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getStudentSearch Starting");
		
		try {
			//String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accYear");
			String locationId = request.getParameter("locationId");
			String classId=request.getParameter("classId");
			String divisionId=request.getParameter("divisionId");
			List<StudentRegistrationVo> list = new StudentRegistrationDaoImpl().getStudentcontact(locationId,accYear,classId,divisionId);
			request.setAttribute("studentSearchList", list);
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentSearchList", list);
			response.getWriter().print(jsonobj);
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getStudentSearch Ending");

				return null;
		
	}
	
//
	//display contact details of all students
	public ActionForward getStudentcontactDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getStudentcontactDetails Starting");
		try
		{
			String accaYear = request.getParameter("accYear");
			String locId = request.getParameter("locationId");
			String classid=request.getParameter("classId");
			String secId=request.getParameter("divisionId");
			StudentRegistrationVo StudentRegistrationVo = new StudentRegistrationVo();
			ArrayList<StudentRegistrationVo> list = new StudentRegistrationDelegate().getStudentcontactDetails(accaYear,locId,classid,secId);
			JSONObject obj = new JSONObject();
			obj.put("data", list);
			response.getWriter().print(obj);
			//List<StudentRegistrationVo> list = new StudentRegistrationDaoImpl().getStudentcontactDetails(locId,accaYear,classid,secId);
			//request.setAttribute("studentContactList", list);
			//JSONObject jsonobj = new JSONObject();
			//jsonobj.put("studentContactList", list);
			//response.getWriter().print(jsonobj);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}
				return null;
		
	}
		
	//
	public ActionForward individualStudentcontact(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{ 

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction    : individualStudentSearch Starting");

		try {
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			/*List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId);*/
			List<StudentRegistrationVo> list = new StudentRegistrationDaoImpl().individualStudentcontact(studentId);
			request.setAttribute("studentSearchList", list);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentSearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : individualStudentSearch Ending");

		return null;
	}


	public ActionForward AddApparisalDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : AddApparisalDetails Starting");

		String userName = null;
		String status = null;



		StudentRegistrationVo stulist =new StudentRegistrationVo();
		try {
			String actiontaken=request.getParameter("actiontaken");
			String schedualdate=request.getParameter("schedualdate");
			String meetingdate=request.getParameter("meetingdate");
			String remark=request.getParameter("remark");
			String recomendeby=request.getParameter("recomendeby");
			String meetingwith=request.getParameter("meetingwith");
			String meetingon=request.getParameter("meetingon");
			String studentId = request.getParameter("student_id");
			String accyear = request.getParameter("academicyear_id");
			String locationid = request.getParameter("location_id");
			String hiddenid=request.getParameter("hiddenid");

			userName = HelperClass.getCurrentUserID(request);

			stulist.setStudentId(studentId);
			stulist.setAcademicYearId(accyear);
			stulist.setLocationId(locationid);
			stulist.setCreateUser(userName);
			stulist.setActiontaken(actiontaken);
			stulist.setSchedualdate(schedualdate);
			stulist.setMeetingdate(meetingdate);
			stulist.setRemarks(remark);
			stulist.setRecomendedby(recomendeby);
			stulist.setMeetingwith(meetingwith);
			stulist.setMeetingon(meetingon);
			stulist.setHiddenid(hiddenid);

			System.out.println(meetingon);


			status = new StudentRegistrationDelegate().AddApparisalDetails(stulist);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("appraisalstatus", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : AddApparisalDetails Ending");

		return null;
	}
	public ActionForward singleStudentDetailReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : singleStudentDetail Starting");

		try {

			List<StudentRegistrationVo> list  = null;
			String studentId = request.getParameter("studentId");
			String accyear = request.getParameter("accyear");
			String locationid = request.getParameter("locationId");
			/*String flag = request.getParameter("flag");*/

			list = new StudentRegistrationDelegate().singleStudentDetailReport(studentId,accyear,locationid);

			/*	request.setAttribute("studentReport", list);*/

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentReport", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : singleStudentDetail Ending");

		return null;
	}
	public ActionForward EditAppraisalDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : EditAppraisalDetails Starting");

		String status = null;



		try {

			StudentRegistrationVo vo =new StudentRegistrationVo();


			JSONObject jsonobj = new JSONObject();
			jsonobj.put("editapplist", vo);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : EditAppraisalDetails Ending");

		return null;
	}
	public ActionForward deleteApparaisalDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : deleteApparaisalDetails Starting");

		String status = null;

		try {
			String deleteId = request.getParameter("deleteid");

			System.out.println("deleteId :"+deleteId);


			status = new StudentRegistrationDelegate().deleteApparaisalDetails(deleteId);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("confidentialstatus", status);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : deleteApparaisalDetails Ending");

		return null;
	}

	public ActionForward searchStudentByTempAdmission(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);

		logger.info("Control in StudentRegistrationAction : searchStudentByTempAdmission Starting");

		List<secadmissionformformatVO> searchStudentList = new ArrayList<secadmissionformformatVO>();
		try {
			secadmissionformformatVO registrationVo = new secadmissionformformatVO();

			String studentName = request.getParameter("studentName");
			String parentName = request.getParameter("parentName");
			String mobileNo = request.getParameter("mobileNo");


			if(studentName.equals(null) || studentName.equals(""))
			{
				studentName = "%%";
			}

			if(parentName.equals(null) ||  parentName.equals(""))
			{
				parentName = "%%";
			}

			if(mobileNo.equals(null) ||  mobileNo.equals(""))
			{
				mobileNo = "%%";
			}


			registrationVo.setStudentfirstName(studentName);
			registrationVo.setStudentLastName(studentName);
			registrationVo.setParentId(parentName);
			registrationVo.setMobile_number(mobileNo);



			StudentRegistrationDaoImpl daoImpl = new StudentRegistrationDaoImpl();
			searchStudentList = daoImpl.searchStudentByTempAdmission(studentName,parentName,mobileNo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : searchStudentByTempAdmission Ending");

		return null;

	}

	public ActionForward searchStudentByStudentName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);

		logger.info("Control in StudentRegistrationAction : searchStudentByStudentName Starting");

		List<StudentRegistrationVo> searchStudentList = new ArrayList<StudentRegistrationVo>();
		try {
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			String searchterm = request.getParameter("searchTerm");

			registrationVo.setSearchTerm(searchterm);
			StudentRegistrationDaoImpl daoImpl = new StudentRegistrationDaoImpl();
			searchStudentList = daoImpl.searchStudentByStudentName(registrationVo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : searchStudentByStudentName Ending");

		return null;

	}

	public ActionForward searchStudentByParentName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);

		logger.info("Control in StudentRegistrationAction : searchStudentByParentName Starting");

		List<StudentRegistrationVo> searchStudentList = new ArrayList<StudentRegistrationVo>();
		try {
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			String searchterm = request.getParameter("searchTerm");

			registrationVo.setSearchTerm(searchterm);
			StudentRegistrationDaoImpl daoImpl = new StudentRegistrationDaoImpl();
			searchStudentList = daoImpl.searchStudentByParentName(registrationVo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : searchStudentByStudentName Ending");

		return null;

	}
//getstudentmobileno
	public ActionForward getStudentMobileNo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);

		logger.info("Control in StudentRegistrationAction : searchStudentByMobileNo Starting");
		List<StudentRegistrationVo> StudentPhoneList = new ArrayList<StudentRegistrationVo>();
		try {
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			
		}
		
		 catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
				return null;
		
	}
	
	public ActionForward searchStudentByMobileNo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);

		logger.info("Control in StudentRegistrationAction : searchStudentByMobileNo Starting");

		List<StudentRegistrationVo> searchStudentList = new ArrayList<StudentRegistrationVo>();
		try {
			StudentRegistrationVo registrationVo = new StudentRegistrationVo();
			String searchterm = request.getParameter("searchTerm");

			registrationVo.setSearchTerm(searchterm);
			StudentRegistrationDaoImpl daoImpl = new StudentRegistrationDaoImpl();
			searchStudentList = daoImpl.searchStudentByMobileNo(registrationVo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstants.JSON_RESPONSE, searchStudentList);

			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : searchStudentByMobileNo Ending");

		return null;

	}
	public ActionForward getHouse(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);

		logger.info("Control in StudentRegistrationAction : getHouse Starting");


		try {
			List<HelperClassVo> houseList = new ArrayList<HelperClassVo>();
			String locationId = request.getParameter("locationId");
			String accyearid=request.getParameter("accyearid");
			
			houseList=HelperClass.getHouse(locationId,accyearid);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("houseList", houseList);

			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.info(JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info("Control in StudentRegistrationAction : getHouse Ending");

		return null;

	}



	public ActionForward getAdmissionNo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:studentTempRegistrationList Starting");
		try {
			String locationId = request.getParameter("locationId");

			String admissionNo = new StudentRegistrationDelegate().getAdmissionNo(locationId);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("admissionNo", admissionNo);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction:studentTempRegistrationList Ending");
		return null;
	}


	public ActionForward ShowStudentAddress(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction    : ShowStudentAddress Starting");

		try {
			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().ShowStudentAddress(studentId,accYear,locationId);
			request.setAttribute("studentSearchList", list);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentSearchList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : ShowStudentAddress Ending");

		return null;
	}



	public ActionForward studentListByJsForScholorship(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : studentListByJsForScholorship Starting");

		List<StudentRegistrationVo> List = null;
		try {
			StudentRegistrationVo data= new StudentRegistrationVo();
			String locationId=request.getParameter("locationId");
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			String academicYear=request.getParameter("academicYear");
			if(academicYear.equalsIgnoreCase("all")){
				academicYear="%%";
			}
			String classId=request.getParameter("classId");
			if(classId.equalsIgnoreCase("all")){
				classId="%%";
			}
			String divisionId=request.getParameter("divisionId");
			if(divisionId.equalsIgnoreCase("all")){
				divisionId="%%";
			}
			String searchTerm=request.getParameter("searchTerm");

			data.setLocationId(locationId);
			data.setAcademicYear(academicYear);
			data.setClasscode(classId);
			data.setSection_id(divisionId);
			data.setSearchTerm(searchTerm);

			List = new StudentRegistrationDaoImpl().getStudentListByJsForScholorship(data);

			JSONObject obj=new JSONObject();
			obj.put(MessageConstants.STUDENTDETAILSLIST, List);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : studentListByJsForScholorship Ending");

		return null;
	}
	public ActionForward getAdmissionDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getAdmissionDetails Starting");

	
		try {
			StudentRegistrationVo data= new StudentRegistrationVo();
			String tempadmissionid = request.getParameter("temadmissionid");
			
			List<StudentRegistrationVo> list = new StudentRegistrationDaoImpl().getAdmissionDetails(tempadmissionid);

			JSONObject obj=new JSONObject();
			obj.put("stuList",list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getAdmissionDetails Ending");

		return null;
	}
	
	
	public ActionForward studentListbyAdmissionNo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getAdmissionDetails Starting");

	
		try {
			StudentConcessionVo data= new StudentConcessionVo();
			String admissionNo = request.getParameter("admissionNo");
			String accyear=request.getParameter("accyear");
			
			List<StudentConcessionVo> list = new StudentRegistrationDaoImpl().getStudentListbyAdmissionNo(admissionNo,accyear);

			JSONObject obj=new JSONObject();
			obj.put("stuList",list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getAdmissionDetails Ending");

		return null;
	}
	
	
	public ActionForward TermdeatilsForConcession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getAdmissionDetails Starting");

	
		try {
			StudentConcessionVo data= new StudentConcessionVo();
			String classId = request.getParameter("classId");
			String accyear=request.getParameter("accyear");
			String specialization=request.getParameter("specialization");
			
			List<StudentConcessionVo> list = new StudentRegistrationDaoImpl().getTermdeatilsForConcession(classId,accyear,specialization);
System.out.println("list size="+list.size());
			JSONObject obj=new JSONObject();
			obj.put("stuList",list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : getAdmissionDetails Ending");

		return null;
	}
	public ActionForward saveStudentPhotoUpload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : saveStudentPhotoUpload Starting");

		try {
			StudentRegistrationForm studentRegistrationForm = (StudentRegistrationForm) form;
			List<FormFile> formFile = studentRegistrationForm.getPhotoUpload();
			String academicYearFace=HelperClass.getCurrentAcadamicYear();
			
			File file = new File(request.getRealPath("/")+ "FIles/STUDENTIMAGES/"+academicYearFace);
			if (!file.exists()) {
				file.mkdirs();
			}
			ArrayList<String> photoPath=new ArrayList<String>();
			ArrayList<String> admissionNo=new ArrayList<String>();
			for(int k=0;k<formFile.size();k++){
				FormFile photofile=formFile.get(k);
				String extension = "";
				String base="";
				int j = (photofile).getFileName().lastIndexOf('.');
				if (j >= 0) {
					base = (String) ((j == -1) ? file : (photofile).getFileName().substring(0, j));
					extension = (photofile).getFileName().substring(j + 1);
					
				}
				
				
				FileInputStream is = null;
				FileOutputStream os = null;
				try {
					
				String filepath=request.getRealPath("/")+ "FIles/STUDENTIMAGES"+ "/"+academicYearFace+"/"+base+ "." + extension;	
				
					byte[] btDataFile = (photofile).getFileData();
					File of = new File(filepath);
					FileOutputStream osf = new FileOutputStream(of);
					osf.write(btDataFile);
					osf.close();
					photoPath.add("FIles/STUDENTIMAGES"+ "/"+academicYearFace+"/"+base+ "." + extension);
					admissionNo.add(base);
				}catch(Exception ex) {
					System.out.println("Unable to copy file:"+ex.getMessage());
				}   
				finally {
					try {
						is.close();
						os.close();
					}catch(Exception ex) {}
				}
			}
			
			studentRegistrationForm.setPhotoRealPath(photoPath);
			studentRegistrationForm.setPhotoAdmissionNoArray(admissionNo);
		int status=new StudentRegistrationDaoImpl().savePhotoUploadLink(studentRegistrationForm);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : saveStudentPhotoUpload Ending");

		return mapping.findForward(MessageConstants.STUDENT_PHOTO_UPLOAD);
	}
	
	
	
	public ActionForward WithHeldActivationDeactivation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : WithHeldActivationDeactivation Starting");

	
		try {
			StudentConcessionVo data= new StudentConcessionVo();
			String studentId = request.getParameter("studentId");
			String accyear=request.getParameter("accyear");
			String status=request.getParameter("status");
			
			String resultStatus = new StudentRegistrationDaoImpl().WithHeldActivationDeactivation(studentId,accyear,status);

			JSONObject obj=new JSONObject();
			obj.put("resultStatus",resultStatus);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : WithHeldActivationDeactivation Ending");

		return null;
	}
	public ActionForward deleteTemRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : deleteTemRecord Starting");

	
		try {
			StudentConcessionVo data= new StudentConcessionVo();
			String id = request.getParameter("id");
			
			String resultStatus = new StudentRegistrationDaoImpl().deleteTemRecord(id);

			JSONObject obj=new JSONObject();
			obj.put("resultStatus",resultStatus);  
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationAction : deleteTemRecord Ending");

		return null;
	}
	
}


