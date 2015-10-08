package cn.com.nbd.nbdmobile.bean;

import java.io.Serializable;
import java.util.List;

/**
 *  ��������
 * @author Dell
 *
 */

public class Article  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3783252497153754852L;
	private String msg;
    private List<ArticleDetail>  articles;
    
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<ArticleDetail> getArticles() {
		return articles;
	}
	public void setArticles(List<ArticleDetail> articles) {
		this.articles = articles;
	}
    
	
}
