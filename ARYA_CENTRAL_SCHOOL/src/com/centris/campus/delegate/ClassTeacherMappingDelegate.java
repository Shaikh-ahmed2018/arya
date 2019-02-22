package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.centris.campus.service.ClassTeacherMappingService;
import com.centris.campus.serviceImpl.ClassTeacherMappingServiceIMPL;
import com.centris.campus.vo.ClassTeacherMappingVO;
import com.centris.campus.vo.RemainderMasterVO;

public class ClassTeacherMappingDelegate {
	
	ClassTeacherMappingService obj_map = new ClassTeacherMappingServiceIMPL();

	public ArrayList<ClassTeacherMappingVO> getclass(ClassTeacherMappingVO vo) {
		
		return obj_map.getclass(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getsection(ClassTeacherMappingVO vo) {
		
		return obj_map.getsection(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getteacher(ClassTeacherMappingVO vo) {
		
		return obj_map.getteacher(vo);
	}

	public String addmappingdetails(ClassTeacherMappingVO vo) {
		
		return obj_map.addmappingdetails(vo);
	}

	public ArrayList<ClassTeacherMappingVO> teachermapping(ClassTeacherMappingVO vo) {
		
		return obj_map.teachermapping(vo);
	}

	public LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>> getclassdetails(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getclassdetails(vo);
	}

	public ArrayList<ClassTeacherMappingVO> editclassdetails(
			ClassTeacherMappingVO vo) {
		
		return obj_map.editclassdetails(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getclassupdate(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getclassupdate(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getupclasslist(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getupclasslist(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getsectionupdate(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getsectionupdate(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getupdateteacherlist(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getupdateteacherlist(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getupteacherlist(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getupteacherlist(vo);
	}

	public String deletemappingDetails(ClassTeacherMappingVO vo) {
		
		return obj_map.deletemappingDetails(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getDownloadDetails(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getDownloadDetails(vo);
	}

}
