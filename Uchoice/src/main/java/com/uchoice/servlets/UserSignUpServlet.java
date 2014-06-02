/**
 * 
 */
package com.uchoice.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mongodb.DBObject;
import com.uchoice.service.UUserService;

/**
 * @author madhava
 * 
 */
public class UserSignUpServlet extends HttpServlet {

	private ApplicationContext ctxt;
	private UUserService userService;
	private AuthenticationManager authenticationManager;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public void init() throws ServletException {
		ctxt = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		userService = (UUserService) ctxt.getBean("userService");
		authenticationManager = (AuthenticationManager) ctxt
				.getBean("authenticationManager");
		
		log.info("UserSignUPServlet init method loaded");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("Signup request received");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String emailId = req.getParameter("emailId");
		String password = req.getParameter("password");
		DBObject user = userService.createUser(firstName, lastName, emailId,
				password);
		// auto login into spring security
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				emailId, password);
		token.setDetails(new WebAuthenticationDetails(req));
		Authentication authenticatedUser = authenticationManager
				.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		log.debug("signup request completed forwarding to welcome");
		resp.sendRedirect(req.getContextPath() + "/welcome.jsp");
	}

}
