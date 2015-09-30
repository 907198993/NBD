package cn.com.nbd.nbdmobile.api;

public enum ApiInterface {

	/**
	 * 点击条目数
	 */
	COLUMNS_MOBILE_CILCK("columns/mobile_click_count?"),
	
	ARTICLE("articles"),
	
	last("");
	
	private String value;
	
	private ApiInterface(String value){
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
