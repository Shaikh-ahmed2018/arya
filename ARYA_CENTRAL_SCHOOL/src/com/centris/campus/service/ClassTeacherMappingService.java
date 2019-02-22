package com.centris.campus.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.centris.campus.vo.ClassTeacherMappingVO;
import com.centris.campus.vo.RemainderMasterVO;

public interface ClassTeacherMappingService {

	ArrayList<ClassTeacherMappingVO> getclass(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getsection(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getteacher(ClassTeacherMappingVO vo);

	String addmappingdetails(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> teachermapping(ClassTeacherMappingVO vo);

	LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>> getclassdetails(
			ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> editclassdetails(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getclassupdate(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getupclasslist(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getsectionupdate(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getupdateteacherlist(
			ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getupteacherlist(ClassTeacherMappingVO vo);

	String deletemappingDetails(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getDownloadDetails(ClassTeacherMappingVO vo);

}
