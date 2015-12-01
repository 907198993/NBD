package cn.com.nbd.nbdmobile.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;


public class ActivityArticleRollList implements Serializable{


//	@Expose
//	private String msg;

	@Expose
	private List<ArticleDetailForRoll>  data;


	public List<ArticleDetailForRoll> getData() {
		return data;
	}

	public void setData(List<ArticleDetailForRoll> data) {
		this.data = data;
	}
}
