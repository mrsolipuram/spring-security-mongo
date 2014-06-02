/**
 * 
 */
package com.uchoice.service;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.uchoice.persistance.UUserPersistence;
import com.uchoice.utils.UChoiceUtil;

/**
 * @author madhava
 * 
 */
public class UUserServiceImpl implements UUserService {

	private UUserPersistence userPersistence;
	private PasswordEncoder passwordEncoder;
	private Logger log = Logger.getLogger(this.getClass());
	

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void setUserPersistence(UUserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void addUser(DBObject obj) throws UnknownHostException,
			MongoException {
		userPersistence.insertUser(obj);

	}

	public DBObject createUser(String firstName, String lastName, String emailId,
			String password) throws UnknownHostException, MongoException {
		log.debug(emailId+":is creating");
		String id = UChoiceUtil.getPrimaryKey();
		password = passwordEncoder.encode(password);
		System.out.println("Encoded password:"+password);
		DBObject user = UChoiceUtil.buildUserObject(id,firstName, lastName, emailId, password);
		DBObject userRole = UChoiceUtil.buildUserRole(id);
		addUser(user);
		addUserRole(userRole);
		log.debug(emailId+"is created");
		return user;
	}

	public DBObject getUserByEmailId(String emailId)
			throws UnknownHostException, MongoException {
		return userPersistence.findUserByEmailId(emailId);
	}

	public void addUserRole(DBObject obj) throws UnknownHostException,
			MongoException {
		userPersistence.insertUserRole(obj);
	}

	public DBObject getUserRoleByUserId(String userId)
			throws UnknownHostException, MongoException {
		return userPersistence.findUserRoleByUserId(userId);
	}

}
