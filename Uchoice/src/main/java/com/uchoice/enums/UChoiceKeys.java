package com.uchoice.enums;

/**
 * 
 * @author madhava
 *
 */
public enum UChoiceKeys {

	ID("_id"),
	EMAIL_ID("emailId"),
	FIRST_NAME("firstName"),
	LAST_NAME("lastName"),
	USER_ID("userId"),
	PASSWORD("password"),
	ROLES("roles"),
	IS_ENABLED("isEnabled"),
	ROLE_USER("ROLE_USER");
	
	private String key;
	private UChoiceKeys(String key) {
		this.key = key;
	}
	
	public String getKey(){
		return key;
	}
}
