package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.forms.LeaveBankForm;
import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LeaveRequestVo;

public interface LeaveBankDAO {
	public ArrayList<LeaveBankVO> leavebanklist(LeaveBankVO vo);
	
	public String insertLeaveBanksDAO(LeaveBankVO vo);
	
	
	public LeaveBankForm editleavebank(LeaveBankForm aform);

	public ArrayList<LeaveBankVO> getSearchDetails(String searchTextVal) ;
	public Boolean deleteLeave(String[] deletelist);
	public ArrayList<LeaveRequestVo> getLeaveAprrovedDetails(
			LeaveRequestVo leavevo);



}
