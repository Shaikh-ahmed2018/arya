package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.RelievingOrderPojo;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReleivingOrderVo;
import com.centris.campus.vo.UserDetailVO;

public interface StaffReleivingOrderService {

	List<AllTeacherDetailsVo> getTeachingListService(AllTeacherDetailsVo vo);

	List<AllTeacherDetailsVo> getNonTeachingListService(AllTeacherDetailsVo vo);

	List<UserDetailVO> getUsersListService();

	List<ReleivingOrderVo> getReleivingDetailsService(RelievingOrderPojo pojo);

}
