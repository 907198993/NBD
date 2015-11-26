package cn.com.nbd.nbdmobile.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;


public class ActivityArticleRollList implements Serializable{


//	@Expose
//	private String msg;

	@Expose
	private List<ArticleDetailForRoll>  articles;


//	public String getMsg() {
//		return msg;
//	}
//
//	public void setMsg(String msg) {
//		this.msg = msg;
//	}


	public List<ArticleDetailForRoll> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDetailForRoll> articles) {
		this.articles = articles;
	}
}
