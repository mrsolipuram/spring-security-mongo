/**
 * 
 */
package com.uchoice.persistance;

import java.net.UnknownHostException;

import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * @author madhava
 *
 */
public interface UUserPersistence {

	public void insertUser(DBObject obj) throws UnknownHostException, MongoException;
	
	public DBObject findUserByEmailId(String emailId) throws UnknownHostException, MongoException;
	
	public void insertUserRole(DBObject obj) throws UnknownHostException, MongoException;
	
	public DBObject findUserRoleByUserId(String userId) throws UnknownHostException, MongoException;
}
