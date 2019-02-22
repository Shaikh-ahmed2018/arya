package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.pojo.ElectionPojo;
import com.centris.campus.vo.ElectionVo;

public interface ElectionDao {

	String saveGroupdetails(String accyear, String locid, String groupname);

	List<ElectionVo> getGroupdetails();



	List<ElectionVo> getAccGrpName(ElectionPojo detailspojo);

	String updateGroupdetails(ElectionPojo pojo, String[] schoolHiddenval);



/*	List<ElectionVo> getGroupdetails(detailspojo);*/

}
