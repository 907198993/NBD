package cn.com.nbd.nbdmobile.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 *  活动列表
 */
public class ActivityArticleList implements Serializable{


	@Expose
	private List<ActivityArticle>  data;


	public List<ActivityArticle> getData() {
		return data;
	}

	public void setData(List<ActivityArticle> data) {
		this.data = data;
	}
}
