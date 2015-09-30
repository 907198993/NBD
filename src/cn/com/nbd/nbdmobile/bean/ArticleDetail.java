package cn.com.nbd.nbdmobile.bean;

import java.io.Serializable;
import java.util.List;

/**
 *  ндубоЙгИ
 * @author Dell
 *
 */

public class ArticleDetail  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3783252497153754852L;
	private String msg;
    private List<ArticleListDetail>  articles;
    
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<ArticleListDetail> getArticles() {
		return articles;
	}
	public void setArticles(List<ArticleListDetail> articles) {
		this.articles = articles;
	}
    
	
}
