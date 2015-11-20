package cn.com.nbd.nbdmobile.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;


public class ArticleForAd implements Serializable {


//	@Expose
//	private String msg;

	@Expose
    private List<ADArticle>  articles;


	public List<ADArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<ADArticle> articles) {
		this.articles = articles;
	}
}
