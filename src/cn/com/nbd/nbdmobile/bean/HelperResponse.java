package cn.com.nbd.nbdmobile.bean;

import java.io.Serializable;


public class HelperResponse implements Serializable{

	
	private static final long serialVersionUID = 102353617118971117L;

	private String result;
	private String message;
	
	public HelperResponse(){
		
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
