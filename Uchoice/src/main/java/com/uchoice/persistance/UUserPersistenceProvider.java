package com.uchoice.persistance;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.uchoice.enums.MongoCollections;
import com.uchoice.enums.UChoiceKeys;
import com.uchoice.utils.MongoDB;


/**
 * 
 * @author madhava
 *
 */
public class UUserPersistenceProvider implements UUserPersistence{
	
	public void insertUser(DBObject obj) throws UnknownHostException, MongoException{
		DBCollection collection = MongoDB.getCollection(MongoCollections.UUSERS.getKey());
		collection.save(obj);
	}
	
	public DBObject findUserByEmailId(String emailId) throws UnknownHostException, MongoException{
		DBCollection collection = MongoDB.getCollection(MongoCollections.UUSERS.getKey());
		DBObject query = new BasicDBObject();
		query.put(UChoiceKeys.EMAIL_ID.getKey(), emailId);
		return collection.findOne(query);
	}
	
	public void insertUserRole(DBObject obj) throws UnknownHostException, MongoException{
		DBCollection collection = MongoDB.getCollection(MongoCollections.UUSER_ROLES.getKey());
		collection.save(obj);		
	}
	
	public DBObject findUserRoleByUserId(String userId) throws UnknownHostException, MongoException{
		DBCollection collection = MongoDB.getCollection(MongoCollections.UUSER_ROLES.getKey());
		DBObject query = new BasicDBObject();
		query.put(UChoiceKeys.USER_ID.getKey(), userId);
		return collection.findOne(query);		
	}

}
