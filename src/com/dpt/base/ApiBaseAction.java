package com.dpt.base;

public enum  ApiBaseAction {

	/**
	 * µÇÂ½
	 */
	LOGIN("login"),
	
	/**
	 * ÍË³ö
	 */
	LOGOUT("logout"),
	
	/**
	 * ¹«¹²action
	 */
	PUBLIC("Public");
	
	private String name;
	
	private ApiBaseAction(String name){
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
