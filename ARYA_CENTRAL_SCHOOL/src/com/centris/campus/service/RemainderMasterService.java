package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.vo.RemainderMasterVO;

public interface RemainderMasterService {

	boolean getnamecount(RemainderMasterVO vo);

	String addremainderdata(RemainderMasterVO vo);

	ArrayList<RemainderMasterVO> remainderdetails(RemainderMasterVO vo);

	RemainderMasterVO editremainderDetails(RemainderMasterVO vo);

	String deleteRemainderDetails(RemainderMasterVO vo);

}
