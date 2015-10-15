package cn.com.nbd.nbdmobile.api;


public enum  ApiAction{
	
	/**
	 *  增加阅读数
	 */
	ADD_READ_NUM("1"),

	/**
	 *
	 */
	NEWS_PAPER("1/newspapers");
	
	
	
	private String value;
	
	private ApiAction(String value){
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
		
}
