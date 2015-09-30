package cn.com.nbd.nbdmobile.bean;

import java.io.Serializable;


public final class ResultObject implements Serializable{

	private static final long serialVersionUID = 7169396560390695615L;

	
	private int 	code ;
	private String 	content ;
	private String 	error ;
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void clear(){
		setCode(-1) ;
		setContent("");
		setError("");
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
