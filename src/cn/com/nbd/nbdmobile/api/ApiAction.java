package cn.com.nbd.nbdmobile.api;


public enum  ApiAction{
	
	/**
	 *  增加阅读数
	 */
	ADD_READ_NUM("/1");
	
	
	
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
