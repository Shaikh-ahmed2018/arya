package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StreamDetailsDao;
import com.centris.campus.daoImpl.ReligionCasteCasteCategoryDaoImpl;
import com.centris.campus.daoImpl.StreamDetailsDaoImpl;
import com.centris.campus.forms.ReligionCasteCasteCategoryForm;
import com.centris.campus.forms.StreamDetailsForm;
import com.centris.campus.pojo.ReligionCasteCasteCategoryPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.service.ReligionCasteCasteCategoryService;
import com.centris.campus.service.StreamDetailsService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;
import com.centris.campus.vo.StreamDetailsVO;

public class ReligionCasteCasteCategoryServiceImpl implements ReligionCasteCasteCategoryService {
	private static final Logger logger = Logger
			.getLogger(ReligionCasteCasteCategoryServiceImpl.class);
	
	ReligionCasteCasteCategoryDaoImpl daoImpl = new ReligionCasteCasteCategoryDaoImpl();

	public String insertReligion(ReligionCasteCasteCategoryForm detailsForm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertReligion Starting");

		ReligionCasteCasteCategoryPojo detailsPojo=new ReligionCasteCasteCategoryPojo();
		
		String result = null;
		
		
		
		if(detailsForm.getReligionId()==null || detailsForm.getReligionId().equalsIgnoreCase("")){
		
			String religion=detailsForm.getReligion();
			System.out.println("religion name is "+religion);
			detailsPojo.setReligion(religion);
			if(religion !=null){

				int nameFound = daoImpl.validateReligion(detailsPojo);
				if(nameFound==1){
					result="Religion Already Exist";
				}else{
					try {

						detailsPojo.setReligion(detailsForm.getReligion());

						int res = daoImpl.insertReligion(detailsPojo);

						if(res==1){

							result = "Religion not Added Successfully";
						}
						else {
							result = "Religion Added Successfully";
						}

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
			
			}else{
				result="Enter Religion";
			}
		}else{
			String religion=detailsForm.getReligion();
			String hiddenreligion=detailsForm.getHiddenreligion().trim();
			System.out.println("religion name is "+hiddenreligion);
			detailsPojo.setReligion(religion);
			System.out.println(religion != hiddenreligion);
			if(!religion.equalsIgnoreCase(hiddenreligion)){
				int nameFound = daoImpl.validateReligion(detailsPojo);
				if(nameFound==1 ){
					result="Religion Already Exist";
				}else{
					try {
						detailsPojo.setReligionId(detailsForm.getReligionId());
						detailsPojo.setReligion(detailsForm.getReligion());
						detailsPojo.setCreateUser(detailsForm.getCreateUser());

						int res = daoImpl.updateReligion(detailsPojo);

						if(res==1){

							result = "Religion Updated Successfully";
						}
						else {
							result = "Religion not Updated Successfully";
						}
					}catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
			}else{
				try {
					detailsPojo.setReligionId(detailsForm.getReligionId());
					detailsPojo.setReligion(detailsForm.getReligion());
					detailsPojo.setCreateUser(detailsForm.getCreateUser());

					int res = daoImpl.updateReligion(detailsPojo);

					if(res==1){

						result = "Religion Updated Successfully";
					}
					else {
						result = "Religion not Updated Successfully";
					}
				}catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);

		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertReligion Ending");

		return result;
	}


	public List<ReligionCasteCasteCategoryVo> searchReligion(String searchName) {
		return daoImpl.searchReligion(searchName);
	}

	public List<ReligionCasteCasteCategoryVo> getReligionDetails() {
		return daoImpl.getReligionDetails();
	}


	
	public ReligionCasteCasteCategoryVo getSingleReligion(ReligionCasteCasteCategoryPojo detailsPojo) {
		return daoImpl.getSingleReligion(detailsPojo);
	}



	public String deleteReligion(ReligionCasteCasteCategoryPojo detailsPojo) {
		return daoImpl.deleteReligion(detailsPojo);
	}



	public List<ReligionCasteCasteCategoryVo> searchCaste(String searchName) {
		return daoImpl.searchCaste(searchName);
	}


	public List<ReligionCasteCasteCategoryVo> getCasteDetails(String religionId) {
		return daoImpl.getCasteDetails(religionId);
	}


	
	public String deleteCaste(ReligionCasteCasteCategoryPojo detailsPojo) {
		return daoImpl.deleteCaste(detailsPojo);
	}


	
	public String insertCaste(ReligionCasteCasteCategoryForm detailsForm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertCaste Starting");

		ReligionCasteCasteCategoryPojo detailsPojo=new ReligionCasteCasteCategoryPojo();

		String result = null;


		System.out.println("Service class: getCasteId: "+detailsForm.getCasteId());
		System.out.println("Service class: getCasteId: "+detailsForm.getCaste());

		if(detailsForm.getCasteId()==null || detailsForm.getCasteId().equalsIgnoreCase("")){

			String caste=detailsForm.getCaste();
			detailsPojo.setCaste(caste);
			detailsPojo.setMain_religion(detailsForm.getMain_religion());
			if(caste !=null){

				int nameFound = daoImpl.validateCaste(detailsPojo);
				if(nameFound==1){
					result="Caste Already Exist";
				}else{
					try {

						detailsPojo.setCaste(detailsForm.getCaste());

						int res = daoImpl.insertCaste(detailsPojo);

						if(res==1){

							result = "Caste not Added Successfully";
						}
						else {
							result = "Caste Added Successfully";
						}

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}

			}else{
				result="Enter Caste";
			}
		}else{
			String caste=detailsForm.getCaste();
			String hiddencaste=detailsForm.getHiddencaste().trim();
			detailsPojo.setCaste(caste);
			detailsPojo.setMain_religion(detailsForm.getMain_religion());
			if(!caste.equalsIgnoreCase(hiddencaste)){

				int nameFound = daoImpl.validateCaste(detailsPojo);
				if(nameFound==1){
					result="Caste Already Exist";
				}else{
					try{
						detailsPojo.setCasteId(detailsForm.getCasteId());
						detailsPojo.setCaste(detailsForm.getCaste());
						detailsPojo.setCreateUser(detailsForm.getCreateUser());
						detailsPojo.setMain_religion(detailsForm.getMain_religion());

						int res = daoImpl.updateCaste(detailsPojo);

						if(res==1){

							result = "Caste Updated Successfully";
						}
						else {
							result = "Caste not Updated Successfully";
						}
					}catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
			}else{
				try{
					detailsPojo.setCasteId(detailsForm.getCasteId());
					detailsPojo.setCaste(detailsForm.getCaste());
					detailsPojo.setCreateUser(detailsForm.getCreateUser());
					detailsPojo.setMain_religion(detailsForm.getMain_religion());

					int res = daoImpl.updateCaste(detailsPojo);

					if(res==1){

						result = "Caste Updated Successfully";
					}
					else {
						result = "Caste not Updated Successfully";
					}
				}catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);

		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertCaste Ending");

		return result;
	}


	
	public ReligionCasteCasteCategoryVo getSingleCaste(
			ReligionCasteCasteCategoryPojo detailsPojo) {
		return daoImpl.getSingleCaste(detailsPojo);
	}



	public List<ReligionCasteCasteCategoryVo> searchCasteCategory(
			String searchName) {
		return daoImpl.searchCasteCategory(searchName);
	}



	public List<ReligionCasteCasteCategoryVo> getCasteCategoryDetails() {
		return daoImpl.getCasteCategoryDetails();
	}


	
	public String insertCasteCategory(ReligionCasteCasteCategoryForm detailsForm) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertCaste Starting");

		ReligionCasteCasteCategoryPojo detailsPojo=new ReligionCasteCasteCategoryPojo();

		String result = null;


		System.out.println("Service class: getCasteId: "+detailsForm.getCasteCatId());
		System.out.println("Service class: getCasteId: "+detailsForm.getCasteCategory());

		if(detailsForm.getCasteCatId()==null || detailsForm.getCasteCatId().equalsIgnoreCase("")){

			String caste=detailsForm.getCasteCategory();
			String religionId=detailsForm.getReligionId();
			String casteId=detailsForm.getCasteId();

			detailsPojo.setCasteCategory(caste);
			detailsPojo.setCasteId(casteId);
			detailsPojo.setReligionId(religionId);
			if(caste !=null){

				int nameFound = daoImpl.validateCasteCategory(detailsPojo);
				if(nameFound==1){
					result="Caste Category Already Exist";
				}else{
					try {

						detailsPojo.setCaste(detailsForm.getCaste());

						int res = daoImpl.insertCasteCategory(detailsPojo);

						if(res==1){

							result = "Caste Category not Added Successfully";
						}
						else {
							result = "Caste Category Added Successfully";
						}

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}

			}else{
				result="Enter Caste Category";
			}

		}else{
			String caste=detailsForm.getCasteCategory();
			String religionId=detailsForm.getReligionId();
			String casteId=detailsForm.getCasteId();
			String hiddencastecategory=detailsForm.getHiddencastecategory().trim();
			System.out.println("hiddencastecategory "+hiddencastecategory);
			
			detailsPojo.setCasteCategory(caste);
			detailsPojo.setCasteId(casteId);
			detailsPojo.setReligionId(religionId);
			if(!caste.equalsIgnoreCase(hiddencastecategory)){
				int nameFound = daoImpl.validateCasteCategory(detailsPojo);
				if(nameFound==1){
					result="Caste Category Already Exist";
				}else{
					try{
						detailsPojo.setCasteCategoryId(detailsForm.getCasteCatId());
						detailsPojo.setCasteCategory(detailsForm.getCasteCategory());
						detailsPojo.setCreateUser(detailsForm.getCreateUser());
						detailsPojo.setCasteId(detailsForm.getCasteId());
						detailsPojo.setReligionId(detailsForm.getReligionId());
						int res = daoImpl.updateCasteCategory(detailsPojo);

						if(res==1){

							result = "Caste Category Updated Successfully";
						}
						else {
							result = "Caste Category not Updated Successfully";
						}
					}catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
			}else{
				try{
					detailsPojo.setCasteCategoryId(detailsForm.getCasteCatId());
					detailsPojo.setCasteCategory(detailsForm.getCasteCategory());
					detailsPojo.setCreateUser(detailsForm.getCreateUser());
					detailsPojo.setCasteId(detailsForm.getCasteId());
					detailsPojo.setReligionId(detailsForm.getReligionId());
					int res = daoImpl.updateCasteCategory(detailsPojo);

					if(res==1){

						result = "Caste Category Updated Successfully";
					}
					else {
						result = "Caste Category not Updated Successfully";
					}
				}catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);

		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertCasteCategory Ending");

		return result;
	}


	
	public String deleteCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo) {
		return daoImpl.delteCasteCategory(detailsPojo);
	}



	public ReligionCasteCasteCategoryVo getSingleCasteCategory(
			ReligionCasteCasteCategoryPojo detailsPojo) {
	    return daoImpl.getSingleCasteCategory(detailsPojo);
	}


	
	public String insertOccupation(ReligionCasteCasteCategoryForm detailsForm) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertOccupation Starting");

		ReligionCasteCasteCategoryPojo detailsPojo=new ReligionCasteCasteCategoryPojo();
		
		String result = null;
		
		if(detailsForm.getOccupationId()==null || detailsForm.getOccupationId().equalsIgnoreCase("")){
			
			String occupation=detailsForm.getOccupation();
			detailsPojo.setOccupation(occupation);
			if(occupation !=null){
					
				int nameFound = daoImpl.validateOccupation(detailsPojo);
				if(nameFound==1){
					result="Occupation Already Exist";
			}else{
					try {
						
						detailsPojo.setOccupation(detailsForm.getOccupation());
				
						int res = daoImpl.insertOccupation(detailsPojo);
						
						if(res==1){
							
							result = "Occupation Not Added Successfully";
						}
						else {
							result = "Occupation Added Successfully";
						}
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
			
			}else{
				result="Enter Occupation";
			}
			
			
			
		}else{
			String occupation=detailsForm.getOccupation();
			String hiddenoccupation=detailsForm.getHiddenoccupation().trim();
			detailsPojo.setOccupation(occupation);
			
			if(!occupation.equalsIgnoreCase(hiddenoccupation)){
				
				int nameFound = daoImpl.validateOccupation(detailsPojo);
				if(nameFound==1){
					result="Occupation Already Exist";
			}else{
					try {
						
						detailsPojo.setOccupationId(detailsForm.getOccupationId());
						detailsPojo.setOccupation(detailsForm.getOccupation());
						detailsPojo.setCreateUser(detailsForm.getCreateUser());
						
						int res = daoImpl.updateOccupation(detailsPojo);
						
						 if(res==1){
								
								result = "Occupation Updated Successfully";
							}
							else {
								result = "Occupation Not Updated Successfully";
							}
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
			
			}else{
				
				try {
					
					detailsPojo.setOccupationId(detailsForm.getOccupationId());
					detailsPojo.setOccupation(detailsForm.getOccupation());
					detailsPojo.setCreateUser(detailsForm.getCreateUser());
					
					int res = daoImpl.updateOccupation(detailsPojo);
					
					 if(res==1){
							
							result = "Occupation Updated Successfully";
						}
						else {
							result = "Occupation Not Updated Successfully";
						}
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
				
			}
			
			
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);

		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryServiceImpl : insertOccupation Ending");

		return result;
	
	}


	public List<ReligionCasteCasteCategoryVo> getOccupationDetails() {
		return daoImpl.getOccupationDetails();
	}

	public ReligionCasteCasteCategoryVo getSingleOccupation(
			ReligionCasteCasteCategoryPojo detailsPojo) {
		return daoImpl.getSingleOccupation(detailsPojo);
	}


	public String deleteOccupation(ReligionCasteCasteCategoryPojo detailsPojo) {
		return daoImpl.deleteOccupation(detailsPojo);
	}


	@Override
	public List<ReligionCasteCasteCategoryVo> getCasteDetailsList(String religionId) {
		return daoImpl.getCasteDetailsList(religionId);
	}
	
	public List<ReligionCasteCasteCategoryVo> getListOfCaste() {
		return daoImpl.getListOfCaste();
	}
	
	public List<ReligionCasteCasteCategoryVo> getCasteCategoryListDetails(String casteId) {
		return daoImpl.getCasteCategoryListDetails(casteId);
	}


	@Override
	public List<ReligionCasteCasteCategoryVo> getOccupationDetailsSearch(
			String searchName) {
		return daoImpl.getOccupationDetailsSearch(searchName);
	}
		
}
