package cn.com.nbd.nbdmobile.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;


public class ActivityArticleList implements Serializable{


//	@Expose
//	private String msg;

	@Expose
	private List<ActivityArticle>  articles;


//	public String getMsg() {
//		return msg;
//	}
//
//	public void setMsg(String msg) {
//		this.msg = msg;
//	}


	public List<ActivityArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<ActivityArticle> articles) {
		this.articles = articles;
	}
}
