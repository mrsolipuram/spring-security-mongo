/**
 * 
 */
package com.uchoice.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.uchoice.enums.UChoiceKeys;

/**
 * @author madhava
 * 
 */
public class UChoiceUtil {

	public static String getPrimaryKey() {
		UUID id = UUID.randomUUID();
		return id.toString();
	}
	
	public static DBObject buildUserObject(String id,String firstName, String lastName, String emailId,
			String password){
		DBObject obj = new BasicDBObject();		
		obj.put(UChoiceKeys.ID.getKey(), id);
		obj.put(UChoiceKeys.USER_ID.getKey(), id);
		obj.put(UChoiceKeys.FIRST_NAME.getKey(), firstName);
		obj.put(UChoiceKeys.LAST_NAME.getKey(), lastName);
		obj.put(UChoiceKeys.EMAIL_ID.getKey(), emailId);
		obj.put(UChoiceKeys.PASSWORD.getKey(), password);
		obj.put(UChoiceKeys.IS_ENABLED.getKey(), true);
		return obj;
	}
	
	public static DBObject buildUserRole(String userId){
		DBObject obj = new BasicDBObject();	
		List<String> roles = new ArrayList<String>();
		roles.add(UChoiceKeys.ROLE_USER.getKey());
		obj.put(UChoiceKeys.ID.getKey(), getPrimaryKey());
		obj.put(UChoiceKeys.USER_ID.getKey(), userId);
		obj.put(UChoiceKeys.ROLES.getKey(), roles);
		
		return obj;
	}

}
