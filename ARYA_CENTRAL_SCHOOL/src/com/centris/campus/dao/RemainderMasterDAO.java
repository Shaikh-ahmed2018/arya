package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.TermMasterVo;



public interface RemainderMasterDAO {

	boolean getnamecount(RemainderMasterVO vo);

	String addremainderdata(RemainderMasterVO vo);

	ArrayList<RemainderMasterVO> remainderdetails(RemainderMasterVO vo);

	RemainderMasterVO editremainderDetails(RemainderMasterVO vo);

	boolean geteditcount(RemainderMasterVO vo);

	String updateremainderdata(RemainderMasterVO vo);

	String deleteRemainderDetails(RemainderMasterVO vo);

	ArrayList<RemainderMasterVO> searchdetails(RemainderMasterVO vo);

	

	
}
