package com.centris.campus.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONException;
import org.json.JSONObject;

import com.centris.campus.delegate.TeacherAttendanceBD;
import com.centris.campus.forms.TeacherAttendanceForm;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PostAttendanceVO;
import com.centris.campus.vo.TeacherAttendanceStatusVo;
import com.centris.campus.vo.TeacherAttendanceVo;

public class TeacherAttendanceAction extends DispatchAction {
	private static final Logger logger = Logger
			.getLogger(TeacherAttendanceAction.class);

	public ActionForward getTeacherAttendanceAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : getTeacherAttendanceAction Starting");

		TeacherAttendanceForm attendanceForm = (TeacherAttendanceForm) form;
		List<TeacherAttendanceVo> teacherAttendanceList = new TeacherAttendanceBD()
				.getTeacherAttendanceBD(attendanceForm);

		JSONObject jsonRespObject = new JSONObject();
		try {
			jsonRespObject.accumulate("teacherAttendanceList",
					teacherAttendanceList);

			response.getWriter().print(jsonRespObject);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (JSONException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : getTeacherAttendanceAction  Ending");
		return null;
	}

	public ActionForward insertTeacherAttendanceAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : insertTeacherAttendanceAction Starting");
		try {
			TeacherAttendanceForm attendanceForm = (TeacherAttendanceForm) form;
			String user = HelperClass.getCurrentUserID(request);
			String date = attendanceForm.getDate();
			String message = "";
			Date sd = null;
			try {
				sd = new SimpleDateFormat("dd-MM-yyy").parse(date);
			} catch (ParseException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			String date3 = new SimpleDateFormat("yyyy-MM-dd").format(sd);
			attendanceForm.setDate(date3);
			attendanceForm.setCreatedBy(user);
			boolean msg = new TeacherAttendanceBD()
					.insertTeacherAttendanceBD(attendanceForm);

			if (msg) {
				message = "Attendance uploaded Successfully";
				request.setAttribute("message", message);
			} else {
				message = "Attendance not uploaded ";
				request.setAttribute("error", message);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : insertTeacherAttendanceAction  Ending");
		return mapping.findForward("success");
	}

	public ActionForward removeMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : removeMessage Starting");
		try {
			request.getSession().setAttribute("message", null);
			response.getWriter().print("Message Removed");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : removeMessage  Ending");
		return null;
	}

	public ActionForward PostAttendance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : PostAttendance Starting");

		try {
			TeacherAttendanceForm attendanceForm = (TeacherAttendanceForm) form;
			String date = attendanceForm.getDate();
			String createdBy = HelperClass.getCurrentUserID(request);

			PostAttendanceVO postattendancevo = new PostAttendanceVO();
			postattendancevo.setCreatedBy(createdBy);
			postattendancevo.setDate(date);
			String status = new TeacherAttendanceBD()
					.PostAttendance(postattendancevo);

			if (status.equals("success")) {
				request.setAttribute("message", "Attendance Posted Successfuly");
			} else if (status.equals("failure")) {
				request.setAttribute("error1", "Attendance Not Posted");
			} else
				request.setAttribute("error1", "Attendance Already Posted");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : PostAttendance  Ending");
		return mapping.findForward("postattendance");

	}

	public ActionForward getTeacherAttendanceByDate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : getTeacherAttendanceByDate Starting");
		try {
			String attendanceDate = request.getParameter("attendanceDate");

			ArrayList<TeacherAttendanceStatusVo> teacherAttendenceByDate = new TeacherAttendanceBD()
					.getTeacherAttendanceByDate(attendanceDate);
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("teacherAttendenceByDate",
					teacherAttendenceByDate);

			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : getTeacherAttendanceByDate  Ending");
		return null;
	}

	public ActionForward approvedByPrincipal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : approvedByPrincipal Starting");
		try {
			TeacherAttendanceStatusVo attendanceStatusVo = new TeacherAttendanceStatusVo();
			ArrayList<TeacherAttendanceStatusVo> attendanceStatusVos = new ArrayList<TeacherAttendanceStatusVo>();
			String teacherId = (String) request.getParameter("teacherIdVal");
			String attendanceDate = (String) request
					.getParameter("attendanceDateval");
			String attendanceStatus = (String) request
					.getParameter("attendanceStatusVal");
			int result = 0;
			TeacherAttendanceBD attendanceBD = new TeacherAttendanceBD();
			String teacherArray[] = teacherId.split(",");
			String attendanceDateArray[] = attendanceDate.split(",");
			String attendanceStatusArray[] = attendanceStatus.split(",");

			for (int i = 0; i < attendanceDateArray.length; i++) {
				attendanceStatusVo.setTeacherId(teacherArray[i]);
				attendanceStatusVo.setAttendanceDate(attendanceDateArray[i]);
				attendanceStatusVo
						.setAttendanceStatus(attendanceStatusArray[i]);
				attendanceStatusVos.add(attendanceStatusVo);
				result = attendanceBD.approvedByPrincipal(attendanceStatusVos);
			}

			JSONObject jsonObject = new JSONObject();

			if (result == 0) {

				jsonObject.accumulate("status", false);
				request.setAttribute("error", "Attendance not Approved");
			} else {

				request.setAttribute("success",
						"Attendance Approved Successfully");
				jsonObject.accumulate("status", true);
			}

			response.getWriter().print(jsonObject);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : approvedByPrincipal  Ending");
		return null;
	}

	public ActionForward updateTeacherAttendanceDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : updateTeacherAttendanceDetails Starting");
		try {
			String var_TeacherId = request.getParameter("var_TeacherId");
			String var_TeacherStatus = request
					.getParameter("var_TeacherStatus");

			String date = request.getParameter("date");

			String teacherid[] = var_TeacherId.split(",");
			String TeacherStatus[] = var_TeacherStatus.split(",");

			String updateby = HelperClass.getCurrentUserID(request);

			String status = new TeacherAttendanceBD()
					.updateTeacherAttendanceDetails(teacherid, TeacherStatus,
							date, updateby);

			JSONObject ob = new JSONObject();
			ob.put("status", status);
			response.getWriter().print(ob);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherAttendanceAction : updateTeacherAttendanceDetails  Ending");
		return null;
	}
}