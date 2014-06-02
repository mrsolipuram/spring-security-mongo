package com.uchoice.enums;

/**
 * 
 * @author madhava
 *
 */
public enum MongoCollections {

	UUSERS("UUsers"),
	UUSER_ROLES("UUserRoles");
	
	private String key;
	private MongoCollections(String key) {
		this.key = key;
	}
	public String getKey(){
		return key;
	}
}
