package cn.com.nbd.nbdmobile.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 2015/10/15.
 */
public class NewsPaper implements Serializable {

	public Self newspaper;

	public class Self {
		public long id;
		public String n_index;
		public long created_at;
		public List<NewsPaperArticle> articles;
		public String msg;
		public int code;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getN_index() {
			return n_index;
		}

		public void setN_index(String n_index) {
			this.n_index = n_index;
		}

		public long getCreated_at() {
			return created_at;
		}

		public void setCreated_at(long created_at) {
			this.created_at = created_at;
		}

		public List<NewsPaperArticle> getArticles() {
			return articles;
		}

		public void setArticles(List<NewsPaperArticle> articles) {
			this.articles = articles;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}
	}

	public Self getNewspaper() {
		return newspaper;
	}

	public void setNewspaper(Self newspaper) {
		this.newspaper = newspaper;
	}


}
