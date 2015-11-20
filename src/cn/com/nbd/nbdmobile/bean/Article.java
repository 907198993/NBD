package cn.com.nbd.nbdmobile.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;



public class Article  implements Serializable{


//	@Expose
//	private String msg;

	@Expose
    private List<ArticleDetail>  articles;
	private List<ActivityArticle>  ActivityArticle;


//	public String getMsg() {
//		return msg;
//	}
//
//	public void setMsg(String msg) {
//		this.msg = msg;
//	}



	public List<ArticleDetail> getArticles() {
		return articles;
	}
	public void setArticles(List<ArticleDetail> articles) {
		this.articles = articles;
	}

	public List<ActivityArticle> getActivityArticle() {
		return ActivityArticle;
	}

	public void setActivityArticle(List<ActivityArticle> activityArticle) {
		ActivityArticle = activityArticle;
	}
}
