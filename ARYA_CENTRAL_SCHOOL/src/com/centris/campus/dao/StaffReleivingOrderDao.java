package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.pojo.RelievingOrderPojo;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReleivingOrderVo;
import com.centris.campus.vo.UserDetailVO;

public interface StaffReleivingOrderDao {

	List<AllTeacherDetailsVo> getTeachingListDaoImpl(AllTeacherDetailsVo vo);

	List<AllTeacherDetailsVo> getNonTeachingListDaoImpl(AllTeacherDetailsVo vo);

	List<UserDetailVO> getUsersListDaoImpl();

	List<ReleivingOrderVo> getReleivingDetailsDaoImpl(RelievingOrderPojo pojo);

}
