package cn.com.nbd.nbdmobile.base;

public enum  ApiBaseAction {

	/**
	 * ��½
	 */
	LOGIN("login"),
	
	/**
	 * �˳�
	 */
	LOGOUT("logout"),
	
	/**
	 * ����action
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
