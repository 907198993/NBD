package com.dpt.bean;

import java.io.Serializable;

/**
 * ��½���ص��û���Ϣ
 * @author hjh
 * 2015-1-22����2:49:04
 *
 */
public final class User implements Serializable{

	private static final long serialVersionUID = -8466776852719268421L;
	

	private long member_id;
	private String member_name;
	private String member_truename;
	private String member_avatar;
	private int member_sex;
	private String member_birthday;
	private String member_passwd;
	private String member_email;
	private String member_qq;
	private String member_ww;
	private int member_login_num;
	private long member_time;
	private long member_login_time;
	private long member_old_login_time;
	private String member_login_ip;
	private String member_old_login_ip;
	private String member_qqopenid;
	private String member_qqinfo;
	private String member_sinaopenid;
	private String member_sinainfo;
	private int member_points;//���
	private float available_predeposit;//Ԥ���
	private float freeze_predeposit;
	private int inform_allow;
	private int is_buy;
	private int is_allowtalk;
	private int member_state;
	private int member_credit;
	private int member_snsvisitnum;
	private String member_areaid;
	private String member_cityid;
	private String member_provinceid;
	private String member_areainfo;
	private String member_privacy;
	private String photo;
	
	public long getMember_id() {
		return member_id;
	}
	
	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}
	
	public String getMember_name() {
		return member_name;
	}
	
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	
	public String getMember_truename() {
		return member_truename;
	}
	
	public void setMember_truename(String member_truename) {
		this.member_truename = member_truename;
	}
	
	public String getMember_avatar() {
		return member_avatar;
	}
	
	public void setMember_avatar(String member_avatar) {
		this.member_avatar = member_avatar;
	}
	
	public int getMember_sex() {
		return member_sex;
	}
	
	public void setMember_sex(int member_sex) {
		this.member_sex = member_sex;
	}
	
	public String getMember_birthday() {
		return member_birthday;
	}
	
	public void setMember_birthday(String member_birthday) {
		this.member_birthday = member_birthday;
	}
	
	public String getMember_passwd() {
		return member_passwd;
	}
	
	public void setMember_passwd(String member_passwd) {
		this.member_passwd = member_passwd;
	}
	
	public String getMember_email() {
		return member_email;
	}
	
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	
	public String getMember_qq() {
		return member_qq;
	}
	
	public void setMember_qq(String member_qq) {
		this.member_qq = member_qq;
	}
	
	public String getMember_ww() {
		return member_ww;
	}
	
	public void setMember_ww(String member_ww) {
		this.member_ww = member_ww;
	}
	
	public int getMember_login_num() {
		return member_login_num;
	}
	
	public void setMember_login_num(int member_login_num) {
		this.member_login_num = member_login_num;
	}
	
	public long getMember_time() {
		return member_time;
	}
	
	public void setMember_time(long member_time) {
		this.member_time = member_time;
	}
	
	public long getMember_login_time() {
		return member_login_time;
	}
	
	public void setMember_login_time(long member_login_time) {
		this.member_login_time = member_login_time;
	}
	
	public long getMember_old_login_time() {
		return member_old_login_time;
	}
	
	public void setMember_old_login_time(long member_old_login_time) {
		this.member_old_login_time = member_old_login_time;
	}
	
	public String getMember_login_ip() {
		return member_login_ip;
	}
	
	public void setMember_login_ip(String member_login_ip) {
		this.member_login_ip = member_login_ip;
	}
	
	public String getMember_old_login_ip() {
		return member_old_login_ip;
	}
	
	public void setMember_old_login_ip(String member_old_login_ip) {
		this.member_old_login_ip = member_old_login_ip;
	}
	
	public String getMember_qqopenid() {
		return member_qqopenid;
	}
	
	public void setMember_qqopenid(String member_qqopenid) {
		this.member_qqopenid = member_qqopenid;
	}
	
	public String getMember_qqinfo() {
		return member_qqinfo;
	}
	
	public void setMember_qqinfo(String member_qqinfo) {
		this.member_qqinfo = member_qqinfo;
	}
	
	public String getMember_sinaopenid() {
		return member_sinaopenid;
	}
	
	public void setMember_sinaopenid(String member_sinaopenid) {
		this.member_sinaopenid = member_sinaopenid;
	}
	
	public String getMember_sinainfo() {
		return member_sinainfo;
	}
	
	public void setMember_sinainfo(String member_sinainfo) {
		this.member_sinainfo = member_sinainfo;
	}
	
	public int getMember_points() {
		return member_points;
	}
	
	public void setMember_points(int member_points) {
		this.member_points = member_points;
	}
	
	public float getAvailable_predeposit() {
		return available_predeposit;
	}
	
	public void setAvailable_predeposit(float available_predeposit) {
		this.available_predeposit = available_predeposit;
	}
	
	public float getFreeze_predeposit() {
		return freeze_predeposit;
	}
	
	public void setFreeze_predeposit(float freeze_predeposit) {
		this.freeze_predeposit = freeze_predeposit;
	}
	
	public int getInform_allow() {
		return inform_allow;
	}
	
	public void setInform_allow(int inform_allow) {
		this.inform_allow = inform_allow;
	}
	
	public int getIs_buy() {
		return is_buy;
	}
	
	public void setIs_buy(int is_buy) {
		this.is_buy = is_buy;
	}
	
	public int getIs_allowtalk() {
		return is_allowtalk;
	}
	
	public void setIs_allowtalk(int is_allowtalk) {
		this.is_allowtalk = is_allowtalk;
	}
	
	public int getMember_state() {
		return member_state;
	}
	
	public void setMember_state(int member_state) {
		this.member_state = member_state;
	}
	
	public int getMember_credit() {
		return member_credit;
	}
	
	public void setMember_credit(int member_credit) {
		this.member_credit = member_credit;
	}
	
	public int getMember_snsvisitnum() {
		return member_snsvisitnum;
	}
	
	public void setMember_snsvisitnum(int member_snsvisitnum) {
		this.member_snsvisitnum = member_snsvisitnum;
	}
	
	public String getMember_areaid() {
		return member_areaid;
	}
	
	public void setMember_areaid(String member_areaid) {
		this.member_areaid = member_areaid;
	}
	
	public String getMember_cityid() {
		return member_cityid;
	}
	
	public void setMember_cityid(String member_cityid) {
		this.member_cityid = member_cityid;
	}
	
	public String getMember_provinceid() {
		return member_provinceid;
	}
	
	public void setMember_provinceid(String member_provinceid) {
		this.member_provinceid = member_provinceid;
	}
	
	public String getMember_areainfo() {
		return member_areainfo;
	}
	
	public void setMember_areainfo(String member_areainfo) {
		this.member_areainfo = member_areainfo;
	}
	
	public String getMember_privacy() {
		return member_privacy;
	}
	
	public void setMember_privacy(String member_privacy) {
		this.member_privacy = member_privacy;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
	
}
