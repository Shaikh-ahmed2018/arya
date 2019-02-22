package com.centris.campus.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.centris.campus.delegate.CareersViewdelegate;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.CareersViewVo;
import org.json.JSONObject;

/**
 * @author Ratna
 * @return ret
 * 
 * 
 */
public class PublicMenuAction extends DispatchAction {
	private static final Logger logger = Logger
			.getLogger(PublicMenuAction.class);

	public PublicMenuAction() {

	}

	/** Home */
	/*public ActionForward Home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : Home Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : Home Ending");
		return mapping.findForward(MessageConstants.HOME);
	}

	public ActionForward aboutUs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : aboutUs Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction: aboutUs Ending");
		return mapping.findForward(MessageConstants.ABOUTUS);
	}

	public ActionForward admission(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : admission Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : admission  Ending");
		return mapping.findForward(MessageConstants.ADMISSIONS);
	}

	public ActionForward gallery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : gallery Starting");
		try {
			Map<String, List<GalleryVO>> imageVideoMap = new GalleryBD()
					.getImageVideos();
			request.setAttribute("imageList", imageVideoMap.get("imageList"));
			request.setAttribute("videoList", imageVideoMap.get("videoList"));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : gallery Ending");
		return mapping.findForward(MessageConstants.GALLERY);
	}

	public ActionForward facilities(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : facilities  Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : facilities Ending");
		return mapping.findForward(MessageConstants.FACILITIES);
	}
*/
	public ActionForward careers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : careers  Starting");

		CareersViewdelegate careerview;
		try {
			List<CareersViewVo> career = new ArrayList<CareersViewVo>();
			careerview = new CareersViewdelegate();
			career = careerview.getActivecareerdetails();

			request.setAttribute("career", career);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : careersEnding");
		return mapping.findForward(MessageConstants.CAREERS);
	}

	/*public ActionForward contactUs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : contactUs  Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : contactUs  Ending");
		return mapping.findForward(MessageConstants.CONTACTUS);
	}

	public ActionForward rules(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : rules  Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : rules Ending");
		return mapping.findForward(MessageConstants.RULES);
	}

	public ActionForward signUpParent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : signUpParent  Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : signUpParent  Ending");
		return mapping.findForward("signUpParent");
	}

	public ActionForward signUpTeacher(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : signUpTeacher  Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : signUpTeacher  Ending");
		return mapping.findForward("signUpTeacher");
	}

	public ActionForward applyJob(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : applyJob  Starting");
		try {
			String job = (String) request.getParameter("jobid");
			CareersViewdelegate careerview = new CareersViewdelegate();
			List<CareersViewVo> career = careerview.getActivecareerdetails();

			request.setAttribute("career", career);
			request.setAttribute("jobid", job);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : applyJob Ending");
		return mapping.findForward("applyJob");

	}

	public ActionForward futureEvents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : futureEvents Starting");
		Date todaydate = new Date();
		try {
			java.sql.Date EventDate = new java.sql.Date(todaydate.getTime());

			List<FutureEventVO> futureEvents = new FutureEventBD()
					.upcomingFutureEvents(EventDate);

			request.setAttribute("futureEvents", futureEvents);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : futureEvents Ending");
		return mapping.findForward("futureEvents");
	}

	public ActionForward loadEventsFirstRecords(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : loadEventsFirstRecords  Starting");
		try {
			Date todaydate = new Date();
			java.sql.Date EventDate = new java.sql.Date(todaydate.getTime());

			List<FutureEventVO> futureEvents = new FutureEventBD()
					.upcomingFutureEvents(EventDate);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("firstEvent", futureEvents);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : loadEventsFirstRecords Ending");
		return null;
	}

	public ActionForward noticeBoard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : noticeBoard  Starting");
		try {
			NoticeBoardViewAction noticeboardAction = new NoticeBoardViewAction();
			if (request != null) {
				noticeboardAction.execute(mapping, form, request, response);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : noticeBoard Ending");

		return mapping.findForward(MessageConstants.NOTICE_BOARD);
	}

	public ActionForward loadNoticeBoardFirstRecords(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : loadNoticeBoardFirstRecords  Starting");
		NoticeBoardViewBD noticeBD = new NoticeBoardViewBD();
		try {
			List<NoticeBoardVO1> noticeboardlist = new ArrayList<NoticeBoardVO1>();
			noticeboardlist = new NoticeBoardViewBD().getNoticeBoard();
			NoticeBoardVO1 nb = noticeboardlist.get(0);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("first", noticeboardlist);
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : loadNoticeBoardFirstRecords  Ending");

		return null;
	}

	public ActionForward schoolTimings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : schoolTimings  Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : schoolTimings  Ending");
		return mapping.findForward(MessageConstants.SCHOOLTIMINGS);
	}

	public ActionForward rulesandRegulations(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : rulesandRegulations  Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : rulesandRegulations  Ending");
		return mapping.findForward(MessageConstants.RULESANDREGULATIONS);
	}

	public ActionForward wordtoParents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : wordtoParents  Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : wordtoParents  Ending");
		return mapping.findForward(MessageConstants.WORDTOPARENTS);
	}

	public ActionForward necessory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : necessory  Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : necessory  Ending");
		return mapping.findForward(MessageConstants.NECESSORY);
	}

	public ActionForward calendar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : calendar  Starting");
		try {

			Date curDate = HelperClass.getCurrentSqlDate();

			String date = curDate.toString();

			CalendarBD listBD = new CalendarBD();
			List<HolidayListVo> allHolidayList = new ArrayList<HolidayListVo>();
			allHolidayList = listBD.getAllHolidayBD(date);
			request.setAttribute("allHolidayList", allHolidayList);

			HolidayListForm listForm = new HolidayListForm();
			listForm.setDate(date);
			HolidayListBD list = new HolidayListBD();
			Map<String, List<HolidayListVo>> allHolidayMap = new LinkedHashMap<String, List<HolidayListVo>>();
			allHolidayMap = list.getAllHolidayBD(listForm);
			request.setAttribute("allHolidayMap", allHolidayMap);

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : calendar Ending");
		return mapping.findForward(MessageConstants.CALENDAR);
	}

	public ActionForward customerFeedback(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : customerFeedback  Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PublicMenuAction : customerFeedback  Ending");
		return mapping.findForward(MessageConstants.CUSTOMER_FEEDBACK);
	}*/
}