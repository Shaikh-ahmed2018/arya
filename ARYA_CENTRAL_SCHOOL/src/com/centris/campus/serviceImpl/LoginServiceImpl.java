package com.centris.campus.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.daoImpl.LoginDaoImpl;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DairyDetailsVo;
import com.centris.campus.vo.LoginVo;
import com.centris.campus.vo.UserDetailVO;

public class LoginServiceImpl {
	private static final Logger logger = Logger
			.getLogger(LoginServiceImpl.class);

	public LoginVo validateUserServiceImpl(String UserName,
			String Password) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl:validateUserServiceImpl Starting");
		
		LoginVo result=null;
		
		try {
			
			 result = new LoginDaoImpl().validateUserDaoImpl(UserName,Password);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return result;
	}

	public UserDetailVO loadUserServiceImpl(LoginVo loginvo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl: loadUserServiceImpl Starting");
		UserDetailVO userdetails = null;
		
		try {
			
			userdetails = new LoginDaoImpl().loadUserDaoImpl(loginvo);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl: loadUserServiceImpl  Ending");
		return userdetails;

	}


	public int checkCurrentPassword(String user, String currentPassword,
			String currentuserId) {

		return new LoginDaoImpl().checkCurrentPassword(user, currentPassword,
				currentuserId);
	}

	public int changePassword(String user, String newpassword,
			String currentuserId) {

		return new LoginDaoImpl().changePassword(user, newpassword,
				currentuserId);
	}

	public String userValidCase(String uname, String password) {
		return new LoginDaoImpl().userValidCase(uname, password);
	}

	public String saveDairy(String content, String rowid, String date,
			String userId, String userType) {
		return new LoginDaoImpl().saveDairy(content, rowid,date,userId,userType);
	}

	public List<DairyDetailsVo> getDairy(String userId) {
		// TODO Auto-generated method stub
		return new LoginDaoImpl().getDairy(userId);
	}

	
}