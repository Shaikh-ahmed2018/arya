package com.centris.campus.service;

import java.util.List;

import com.centris.campus.vo.ElectionVo;

public interface ElectionService {

	String saveGroupdetails(String accyear, String locid, String groupname);

	List<ElectionVo> getGroupdetails();





}
