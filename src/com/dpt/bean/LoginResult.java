package com.dpt.bean;

import java.io.Serializable;

/**
 * µÇÂ½½á¹û
 * @author hjh
 * 2015-1-22ÏÂÎç2:48:35
 *
 */
public final class LoginResult implements Serializable{

	private static final long serialVersionUID = -5496160298710369813L;

	private User member_info;
	private String userkey;
	
	
	
	public User getMember_info() {
		return member_info;
	}
	
	public void setMember_info(User member_info) {
		this.member_info = member_info;
	}
	
	public String getUserkey() {
		return userkey;
	}
	
	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}
	
	
	
}
