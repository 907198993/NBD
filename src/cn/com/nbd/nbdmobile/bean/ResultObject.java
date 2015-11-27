package cn.com.nbd.nbdmobile.bean;

import java.io.Serializable;


public final class ResultObject implements Serializable{

	private static final long serialVersionUID = 7169396560390695615L;

	
	private int 	status_code ;
	private String 	data ;
	private String 	msg ;
	private int     totalPage;
	private int		currentPage;
	
	public ResultObject(){
		clear();
	}

	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void clear(){
		setStatus_code(-1); ;
		setData("");
		setMsg("");
	}

	@Override
	public ResultObject clone(){
		
		try{			
			ResultObject obj = (ResultObject)super.clone() ;	
			obj.clear() ;
			
			return obj ;
		} catch(CloneNotSupportedException e){
			return new ResultObject();
		}
	}
}
