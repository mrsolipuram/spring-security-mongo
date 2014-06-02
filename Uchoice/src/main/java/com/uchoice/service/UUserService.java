package com.uchoice.service;

import java.net.UnknownHostException;

import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * 
 * @author madhava
 * 
 */
public interface UUserService {

	public void addUser(DBObject obj) throws UnknownHostException,
			MongoException;

	public DBObject getUserByEmailId(String emailId)
			throws UnknownHostException, MongoException;

	public void addUserRole(DBObject obj) throws UnknownHostException,
			MongoException;

	public DBObject getUserRoleByUserId(String userId)
			throws UnknownHostException, MongoException;

	public DBObject createUser(String firstName, String lastName, String emailId,
			String password) throws UnknownHostException, MongoException;
}
