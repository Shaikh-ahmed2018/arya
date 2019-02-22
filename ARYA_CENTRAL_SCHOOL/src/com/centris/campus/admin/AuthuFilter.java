package com.centris.campus.admin;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Ratna
 */
@WebFilter(filterName = "AuthuFilter", urlPatterns = { "*.html" })
public class AuthuFilter implements Filter {

	private static final boolean debug = false;
	private FilterConfig filterConfig = null;

	public AuthuFilter() {
	}

	private void doBeforeProcessing(RequestWrapper request,
			ResponseWrapper response) throws IOException, ServletException {
		if (debug) {
			log("AuthuFilter:DoBeforeProcessing");
		}
	}

	private void doAfterProcessing(RequestWrapper request,
			ResponseWrapper response) throws IOException, ServletException {
		if (debug) {
			log("AuthuFilter:DoAfterProcessing");
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (debug) {
			log("AuthuFilter:doFilter()");
		}

		RequestWrapper wrappedRequest = new RequestWrapper(
				(HttpServletRequest) request);
		ResponseWrapper wrappedResponse = new ResponseWrapper(
				(HttpServletResponse) response);
		Throwable problem = null;

		HttpSession ses = wrappedRequest.getSession();

		ses.setAttribute("NOTICE", "JAFFA ITS WORKING RA");
		System.out
				.println("----------------------------------------------------------------------");
		System.out.println(" This is a Filter in Administration Module");
		System.out.println("1----------- " + wrappedRequest.getRequestURI());
		System.out.println("2----------- " + wrappedRequest.getRequestURL());
		System.out.println(" Online users -------------"
				+ request.getServletContext().getContextPath());
		System.out.println(" Ip address -------------"
				+ request.getRemoteAddr());
		System.out.println(" Online users -------------"
				+ request.getLocalName());
		System.out.println(" Online users -------------" + request.getScheme());

		System.out
				.println("----------------------------------------------------------------------");
		if (wrappedRequest.getRequestURI().contains("/ARYA_CENTRAL_SCHOOL/login.html")
				|| wrappedRequest.getRequestURI().contains(
						"/ARYA_CENTRAL_SCHOOL/forgotPassword.html")) {
			System.out.println("Hi thissssssssssss is login page in filter");
			try {
				chain.doFilter(wrappedRequest, wrappedResponse);
			} catch (Throwable t) {
				problem = t;
				t.printStackTrace();
			}
		} else if (wrappedRequest.getRequestURI().contains(
				"/ARYA_CENTRAL_SCHOOL/displayNotice.html")) {
			System.out.println("Hi this is login page in filter displayNotice.html");
			try {
				chain.doFilter(wrappedRequest, wrappedResponse);
			} catch (Throwable t) {
				problem = t;
				t.printStackTrace();
			}
		} else if (wrappedRequest.getRequestURI().contains(
				"/ARYA_CENTRAL_SCHOOL/	.html")) {

			System.out.println("Hi thissssss is welcome(Welcome) page  in filter");
			try {
				chain.doFilter(wrappedRequest, wrappedResponse);
			} catch (Throwable t) {
				problem = t;
				t.printStackTrace();
			}
		} else if (wrappedRequest.getRequestURI().contains(
				"/ARYA_CENTRAL_SCHOOL/applyJob.html")) {
			System.out.println("Hi this is welcome(Welcome) applyJob page  in filter");
			try {
				chain.doFilter(wrappedRequest, wrappedResponse);
			} catch (Throwable t) {
				problem = t;
				t.printStackTrace();
			}
		}

		else if (wrappedRequest.getRequestURI().contains(
				"/ARYA_CENTRAL_SCHOOL/feedback.html")) {
			System.out.println("Hi this is welcome(Welcome) feedback page   in filter");
			try {
				chain.doFilter(wrappedRequest, wrappedResponse);
			} catch (Throwable t) {
				problem = t;
				t.printStackTrace();
			}
		}
		//parent requires appointment
		else if (wrappedRequest.getRequestURI().contains(
				"/ARYA_CENTRAL_SCHOOL/parentrequiresappointment.html")) {
			System.out.println("Hi this is welcome(Welcome) parentrequiresappointment page  in filter");
			try {
				chain.doFilter(wrappedRequest, wrappedResponse);
			} catch (Throwable t) {
				problem = t;
				t.printStackTrace();
			}
		} 
		//New Registration Of Users
		
		else if (wrappedRequest.getRequestURI().contains(
				"/ARYA_CENTRAL_SCHOOL/registration.html")) {
			System.out.println("Hi this is welcome(Welcome  registration) registration page  in filter");
			try {
				chain.doFilter(wrappedRequest, wrappedResponse);
			} catch (Throwable t) {
				problem = t;
				t.printStackTrace();
			}
		} 
		
		else if (wrappedRequest.getRequestURI().contains(
				"/ARYA_CENTRAL_SCHOOL/studentRegistration.html")) {
			System.out.println("Hi this is welcome(Welcome)  studentRegistration page  in filter");
			try {
				
				String ss = (String) wrappedRequest.getSession().getAttribute("user");
				if(ss==null ||ss.equalsIgnoreCase("null") || ss.equalsIgnoreCase("")){
					request.getRequestDispatcher("/LAYOUT/index.jsp").forward(request, response);
					
				}
				System.out.println("studentRegistration ss::::"+ss);
				chain.doFilter(wrappedRequest, wrappedResponse);
			} catch (Throwable t) {
				problem = t;
				t.printStackTrace();
			} 
		} 
		else if (wrappedRequest.getRequestURI().contains(
				"/ARYA_CENTRAL_SCHOOL/newregistration.html")) {
			System.out.println("Hi this is welcome(PZPPPP) applyJob page  in filter");
			try {
				chain.doFilter(wrappedRequest, wrappedResponse);
			} catch (Throwable t) {
				problem = t;
				t.printStackTrace();
			}
		}
		
		else if (wrappedRequest.getRequestURI().contains(
				"/ARYA_CENTRAL_SCHOOL/parentrequiresappointment.html")) {
			System.out.println("Hi this is welcome(Welcome) registration page  in filter");
			try {
				chain.doFilter(wrappedRequest, wrappedResponse);
			} catch (Throwable t) {
				problem = t;
				t.printStackTrace();
			}
		}
		
		else {
			try {
				ServletContext con = request.getServletContext();
				String ss = wrappedRequest.getSession().getAttribute("user").toString();
			
				chain.doFilter(wrappedRequest, wrappedResponse);
				wrappedResponse.setHeader("Cache-Control",
						"no-cache,no-store,must-revalidate");
				wrappedResponse.setHeader("Pragma", "no-cache");
				wrappedResponse.setDateHeader("Expires", 0);
				
			} catch (Exception e) {
				System.out.println(" Problem in Session gathering Logic");
				System.out.println("" + e.getMessage());
				System.out.println("" + e);
				e.printStackTrace();
				HttpServletRequest hsr = (HttpServletRequest) request;
				// HttpSession ses = hsr.getSession(false);
				ses.invalidate();
				PrintWriter pw = response.getWriter();
				pw.println("<html><head><script language='javascript'>"
						+ "window.history.forward();"
						+ "function noBack() { window.history.forward(); }"
						+ "</script></head><body style='background-color: #85C2FF' onload='noBack()' onpageshow='if (event.persisted) noBack()' onunload=''>"
						+ "<center> <br><br><br> Sorry !!  You are not Authorized. Please Login again..."
						+ "<form action='/ARYA_CENTRAL_SCHOOL/' method='post' accept-charset='utf-8'><input type='submit' value='OK'/></form>"
						+ "</center></body></html>");
			}
		}
		if (problem != null) {
			if (problem instanceof ServletException) {
				throw (ServletException) problem;
			}
			if (problem instanceof IOException) {
				throw (IOException) problem;
			}
			sendProcessingError(problem, response);
		}
	}

	/**
	 * Return the filter configuration object for this filter.
	 */
	public FilterConfig getFilterConfig() {
		return (this.filterConfig);
	}

	/**
	 * Set the filter configuration object for this filter.
	 * 
	 * @param filterConfig
	 *            The filter configuration object
	 */
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * Destroy method for this filter
	 */
	public void destroy() {
	}

	/**
	 * Init method for this filter
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		if (filterConfig != null) {
			if (debug) {
				log("AuthuFilter: Initializing filter");
			}
		}
	}

	@Override
	public String toString() {
		if (filterConfig == null) {
			return ("AuthuFilter()");
		}
		StringBuffer sb = new StringBuffer("AuthuFilter(");
		sb.append(filterConfig);
		sb.append(")");
		return (sb.toString());

	}

	private void sendProcessingError(Throwable t, ServletResponse response) {
		String stackTrace = getStackTrace(t);

		if (stackTrace != null && !stackTrace.equals("")) {
			try {
				response.setContentType("text/html");
				PrintStream ps = new PrintStream(response.getOutputStream());
				PrintWriter pw = new PrintWriter(ps);
				pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); // NOI18N

				// PENDING! Localize this for next official release
				pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
				pw.print(stackTrace);
				pw.print("</pre></body>\n</html>"); // NOI18N
				pw.close();
				ps.close();
				response.getOutputStream().close();
			} catch (Exception ex) {
			}
		} else {
			try {
				PrintStream ps = new PrintStream(response.getOutputStream());
				t.printStackTrace(ps);
				ps.close();
				response.getOutputStream().close();
			} catch (Exception ex) {
			}
		}
	}

	public static String getStackTrace(Throwable t) {
		String stackTrace = null;
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			pw.close();
			sw.close();
			stackTrace = sw.getBuffer().toString();
		} catch (Exception ex) {
		}
		return stackTrace;
	}

	public void log(String msg) {
		filterConfig.getServletContext().log(msg);
	}

	class RequestWrapper extends HttpServletRequestWrapper {

		public RequestWrapper(HttpServletRequest request) {
			super(request);
		}

		protected Hashtable localParams = null;

		public void setParameter(String name, String[] values) {
			if (debug) {
				System.out.println("AuthuFilter::setParameter(" + name + "="
						+ values + ")" + " localParams = " + localParams);
			}

			if (localParams == null) {
				localParams = new Hashtable();
				// Copy the parameters from the underlying request.
				Map wrappedParams = getRequest().getParameterMap();
				Set keySet = wrappedParams.keySet();
				for (Iterator it = keySet.iterator(); it.hasNext();) {
					Object key = it.next();
					Object value = wrappedParams.get(key);
					localParams.put(key, value);
				}
			}
			localParams.put(name, values);
		}

		@Override
		public String getParameter(String name) {
			if (debug) {
				System.out.println("AuthuFilter::getParameter(" + name
						+ ") localParams = " + localParams);
			}
			if (localParams == null) {
				return getRequest().getParameter(name);
			}
			Object val = localParams.get(name);
			if (val instanceof String) {
				return (String) val;
			}
			if (val instanceof String[]) {
				String[] values = (String[]) val;
				return values[0];
			}
			return (val == null ? null : val.toString());
		}

		@Override
		public String[] getParameterValues(String name) {
			if (debug) {
				System.out.println("AuthuFilter::getParameterValues(" + name
						+ ") localParams = " + localParams);
			}
			if (localParams == null) {
				return getRequest().getParameterValues(name);
			}
			return (String[]) localParams.get(name);
		}

		@Override
		public Enumeration getParameterNames() {
			if (debug) {
				System.out
						.println("AuthuFilter::getParameterNames() localParams = "
								+ localParams);
			}
			if (localParams == null) {
				return getRequest().getParameterNames();
			}
			return localParams.keys();
		}

		@Override
		public Map getParameterMap() {
			if (debug) {
				System.out
						.println("AuthuFilter::getParameterMap() localParams = "
								+ localParams);
			}
			if (localParams == null) {
				return getRequest().getParameterMap();
			}
			return localParams;
		}
	}

	class ResponseWrapper extends HttpServletResponseWrapper {

		public ResponseWrapper(HttpServletResponse response) {
			super(response);
		}
	}
}