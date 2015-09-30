package cn.com.nbd.nbdmobile.bean;

import java.io.Serializable;
import java.util.Map;

import com.google.gson.annotations.Expose;

public final class Response implements Serializable{

	private static final long serialVersionUID = -2060511106914419928L;

	@Expose
	private int status;//״̬
	
	@Expose
	private String info;//
	
	@Expose
	private  Map<String, Object> data;
	
	public Response(){
		setStatus(-1);
		setInfo("");
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
