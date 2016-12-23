//package com.dante.ws.demo;
//
//import java.io.IOException;
//import java.util.Calendar;
//import java.util.Date;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
//import org.springframework.security.authentication.CredentialsExpiredException;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.dante.service.IConfigurationManager;
//
//
//
//public class RestEasyWSServlet extends HttpServletDispatcher {
//	private static final long serialVersionUID = 1L;
//
//	protected final Log log = LogFactory.getLog(getClass());
//
//	public static final String ERROR_CODE_ATTR = "ERROR_CODE_ATTR";
//
//	private IConfigurationManager configurationManager;
//	
//	private WebApplicationContext rootContext;
//	
////	private DemErrorRepository demErrorRepository;
//
//	protected IConfigurationManager getConfigurationManager() {
//		if (this.configurationManager == null) {
//			this.configurationManager = (IConfigurationManager) AppServletContextListener.getBean("configurationManager");
//		}
//		return this.configurationManager;
//	}
//
//	public RestEasyWSServlet(WebApplicationContext rootContext) {
//		this.rootContext = rootContext;
//	}
//	
//	protected DemErrorRepository getDemErrorRepository() {
//		if (this.demErrorRepository == null) {
//			this.demErrorRepository = (DemErrorRepository) AppServletContextListener.getBean("demErrorRepository");
//		}
//		return this.demErrorRepository;
//	}
//
//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		try {
//			long start = System.currentTimeMillis();
//			authenticate(request, response);
//			super.service(request, response);
//			log.info("Finished request '" + request.getRequestURL() + "'. " + Lib.getTimeSpentText(start));
//		} catch (Exception e) {
//			log.error("Unexpected error when processing service", e);
//			Integer errorCode = (Integer) request.getAttribute(ERROR_CODE_ATTR);
//			String error = errorCode == null ? WSHelper.buildWSErrorCall(e)
//					: WSHelper.buildWSErrorCall(e.getCause().getMessage(), errorCode);
//			WSHelper.sendData(response, error);
//		} finally {
//			cleanUp(request);
//		}
//	}
//
//	protected void cleanUp(HttpServletRequest request) {
//		HttpSession session = request.getSession(false);
//		if (session != null) {
//			String userEmail = WSHelper.getParameter(request, WebConstants.Webservices.USER_NAME_PARAM, false);
//			/**
//			 * logout this user
//			 */
//			try {
//				session.invalidate();
//			} catch (Exception e) {
//				log.error("Unexpected error when loging out of user '" + userEmail + "'", e);
//			}
//		}
//	}
//
//	/**
//	 * This method tries to authenticate and validate user. If everything is ok, it return true. Otherwise, an exception
//	 * will be thrown
//	 * 
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws IOException
//	 */
//	private boolean authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		/**
//		 * 0. - Parse request if it is multipart/form-data. Because it can parse once time only, we will store it into
//		 * the session for the services (LoanWS, DocumentWS...). These services can get it from session using
//		 * WSConstants.Parameters.MULTIPLE_FORM_DATA_BEAN parameter. - Get userEmail, password from normal request
//		 * first. If they are both null, try to get them from multi-part request. - Validate them and build LoginBean
//		 */
//		FileUploadHelperBean bean = parseMultipartRequest(request);
//		LoginBean loginBean = validateParamsAndGetLoginBean(request, bean);
//		IUserService userService = (IUserService) AppServletContextListener.getBean(UserService.class);
//		User user = userService.validateUser(loginBean);
//		
//		String md5CheckSumParam = WSHelper.getParameter(request, WebConstants.Webservices.MD5_CHECKSUM_PARAM, false);
//		String clientDocIdParam = WSHelper.getParameter(request, WebConstants.Webservices.CLIENT_DOC_ID_PARAM, false);
//		
//		if (user == null) {
//			this.getDemErrorRepository().saveWithDemErrorType(WebConstants.DemErrorType.USER_BAD_CREDENTIALS,
//					md5CheckSumParam, clientDocIdParam,
//					loginBean.getUsername(), DateUtil.currentTime());
//			throw new RuntimeException("Invalid credentials [user=" + loginBean.getUsername() + ", password=*****]");
//		}
//
//		/**
//		 * 1. check if user is locked
//		 */
//		if (user.getAccountLocked() != null && user.getAccountLocked()) {
//			this.getDemErrorRepository().saveWithDemErrorType(WebConstants.DemErrorType.USER_IS_LOCKED,
//					md5CheckSumParam, clientDocIdParam,
//					loginBean.getUsername(), DateUtil.currentTime());
//			throw new RuntimeException("User '" + loginBean.getUsername() + "' has been locked");
//		}
//
//		/**
//		 * 2. Check if user is deactivated
//		 */
//		if (user.getUserActive() != null && !user.getUserActive()) {
//			this.getDemErrorRepository().saveWithDemErrorType(WebConstants.DemErrorType.USER_IS_DEACTIVATED,
//					md5CheckSumParam, clientDocIdParam,
//					loginBean.getUsername(), DateUtil.currentTime());
//			throw new RuntimeException("User '" + loginBean.getUsername() + "' has been deactivated");
//		}
//
//		/**
//		 * 3. Check if password expired
//		 */
//		if (user.getApiLogin()) {
//			this.getDemErrorRepository().saveWithDemErrorType(WebConstants.DemErrorType.USER_PASSWORD_IS_EXPIRED,
//					md5CheckSumParam, clientDocIdParam,
//					loginBean.getUsername(), DateUtil.currentTime());
//			handlePasswordExpiredOfWSLogin(user);
//		}
//
//		/**
//		 * 4. put this user into the session for later use
//		 */
//		WSHelper.saveLoginedUserToSession(request, user);
//		return true;
//	}
//
//	private FileUploadHelperBean parseMultipartRequest(HttpServletRequest request) {
//		FileUploadHelperBean bean = null;
//		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//		if (isMultipart) {
//			try {
//				bean = FileUploadUtil.parseMultipartRequest(request);
//			} catch (Exception e) {
//				throw new RuntimeException("Failed to parse multipart/form-data.", e);
//			}
//
//			HttpSession session = request.getSession(true);
//			session.setAttribute(WebConstants.Webservices.MULTIPLE_FORM_DATA_BEAN, bean);
//		}
//
//		return bean;
//	}
//
//	private LoginBean validateParamsAndGetLoginBean(HttpServletRequest request, FileUploadHelperBean bean) {
//		String userEmail = request.getParameter(WebConstants.Webservices.USER_NAME_PARAM);
//		String password = request.getParameter(WebConstants.Webservices.PASSWORD_PARAM);
//		if (userEmail == null && password == null && bean != null) {
//			userEmail = (String) bean.getFormField(WebConstants.Webservices.USER_NAME_PARAM);
//			password = (String) bean.getFormField(WebConstants.Webservices.PASSWORD_PARAM);
//		}
//
//		if (userEmail == null) {
//			throw new RuntimeException("Missing required parameter '" + WebConstants.Webservices.USER_NAME_PARAM + "'");
//		}
//
//		if (password == null) {
//			throw new RuntimeException("Missing required parameter '" + WebConstants.Webservices.PASSWORD_PARAM + "'");
//		}
//
//		LoginBean loginBean = new LoginBean();
//		loginBean.setUsername(userEmail);
//		loginBean.setPassword(password);
//		return loginBean;
//	}
//
//	/**
//	 * Implemented https://jira.aklero.com/browse/IDEA-18:
//	 *   + If password is expired (i.e. password change is required)
//	 *   + Send back an error message - Password Expired, Password Change Required
//	 * @param request
//	 * @param authentication
//	 */
//	private void handlePasswordExpiredOfWSLogin(User user) {
//		boolean isExpired = user.getPasswordExpired();
//		if(isExpired == false) {
//			Date lastDate = user.getPasswordLastChangedDate();
//			if(lastDate != null) {
//				Calendar calendar = Calendar.getInstance();
//				int maxAge = getConfigurationManager().getIntProperty(ConfigConstants.MAX_PASSWORD_AGE);
//				calendar.add(Calendar.DAY_OF_MONTH, -maxAge);
//				isExpired = lastDate.before(calendar.getTime()); 
//			}
//			if(isExpired) {
//				user.setPasswordExpired(true);
//				UserRepository userRepository = (UserRepository) AppServletContextListener.getBean(UserRepository.class);
//				userRepository.save(user);
//			}
//		}
//		if(isExpired) {
//			throw new CredentialsExpiredException("Password Expired, Password Change Required");
//		}
//	}
//}
//
