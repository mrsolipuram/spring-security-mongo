package com.uchoice.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mongodb.DBObject;
import com.uchoice.enums.UChoiceKeys;
import com.uchoice.service.UUserService;

public class MongoUserDetailService implements UserDetailsService {

	private UUserService userService;
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */

	public void setUserService(UUserService userService) {
		this.userService = userService;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		log.debug(username+":trying to get data for spring security");
		UserDetails udetail = null;
		try {
			DBObject user = userService.getUserByEmailId(username);
			DBObject userRole = userService.getUserRoleByUserId((String) user
					.get(UChoiceKeys.ID.getKey()));
			List<String> roles = (List<String>) userRole.get(UChoiceKeys.ROLES
					.getKey());
			if (user == null) {
				return null;
			}
			List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(roles.get(0)));

			udetail = new User(
					(String) user.get(UChoiceKeys.EMAIL_ID.getKey()),
					(String) user.get(UChoiceKeys.PASSWORD.getKey()),
					(Boolean) user.get(UChoiceKeys.IS_ENABLED.getKey()), true,
					true, true, authorities);
		} catch (Exception e) {
			log.debug(username+":User is not available");
			log.error(username+":error in spring security", e);
		}
		return udetail;
	}

}
