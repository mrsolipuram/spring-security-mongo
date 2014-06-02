package com.uchoice.utils;

import java.net.UnknownHostException;
import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.uchoice.enums.MongoCollections;

/**
 * 
 * @author madhava
 *
 */
public class MongoDB {
	//
	private static MongoClient mongo = null;
	//
	private static DB db = null;
	//
	private static String mongoDB;

	private static Properties properties;

	/**
* 
*/
	private MongoDB() {

	}

	static {
		try {
			properties = new Properties();
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("mongodb.properties"));
			mongoDB = properties.getProperty("mongo.db.database");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 * @throws UnknownHostException
	 * @throws MongoException
	 */
	public static DB getMongoDB() throws UnknownHostException, MongoException {
		if (mongo == null) {

			mongo = new MongoClient(properties.getProperty("mongo.db.host"),
					Integer.parseInt(properties.getProperty("mongo.db.port")));

			db = mongo.getDB(mongoDB);
			boolean auth = db.authenticate(properties
					.getProperty("mongo.db.user"),
					properties.getProperty("mongo.db.password").toCharArray());
		}
		return db;
	}

	/**
	 * 
	 * @param collectionName
	 * @return
	 * @throws UnknownHostException
	 * @throws MongoException
	 */
	public static DBCollection getCollection(String collectionName)
			throws UnknownHostException, MongoException {
		if (db == null)
			getMongoDB();
		return db.getCollection(collectionName);
	}
	
	public static void main(String[] args) throws UnknownHostException, MongoException {
		getCollection(MongoCollections.UUSERS.getKey());
	}
}
