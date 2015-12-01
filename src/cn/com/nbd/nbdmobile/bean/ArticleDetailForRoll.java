package cn.com.nbd.nbdmobile.bean;

import com.google.gson.annotations.Expose;

import org.hjh.annomation.ColumnInt;
import org.hjh.annomation.ColumnPrimaryKey;
import org.hjh.annomation.ColumnString;
import org.hjh.annomation.PrimaryKeyType;
import org.hjh.annomation.Table;

import java.io.Serializable;
import java.util.List;

/**
 *滚动
 */
@Table(TableName = "ArticleDetailForRoll")
public final class ArticleDetailForRoll implements Serializable{



	@ColumnPrimaryKey(TYPE= PrimaryKeyType.DEFINE)
	@ColumnInt
    private  int site_id;

    @ColumnInt
    @Expose
	private  int id;

	@ColumnInt
	private int is_rolling_news;

	@ColumnString(length = 64)
	@Expose
	private String pos;

	@ColumnInt
	private int special;

	@ColumnString(length = 64)
	@Expose
	private String title;

	@ColumnString(length = 64)
	@Expose
	private String digest;

	@ColumnString(length = 64)
	@Expose
	private String url;

	@ColumnString(length = 64)
	@Expose
	private String created_at;

	@ColumnString(length = 64)
	@Expose
	private String columnist_id;

	@ColumnString(length = 256)
	@Expose
	private String content;

	@ColumnString(length = 64)
	@Expose
	private String image;

	@ColumnString(length = 64)
	@Expose
	private String mobile_click_count;

	private List<RollLabel> children_articles;

	private boolean check;

	public List<RollLabel> getChildren_articles() {
		return children_articles;
	}

	public void setChildren_articles(List<RollLabel> children_articles) {
		this.children_articles = children_articles;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

    public int getIs_rolling_news() {
		return is_rolling_news;
	}
	public void setIs_rolling_news(int is_rolling_news) {
		this.is_rolling_news = is_rolling_news;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public int getSpecial() {
		return special;
	}
	public void setSpecial(int special) {
		this.special = special;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public int getSite_id() {
		return site_id;
	}

	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getColumnist_id() {
		return columnist_id;
	}
	public void setColumnist_id(String columnist_id) {
		this.columnist_id = columnist_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMobile_click_count() {
		return mobile_click_count;
	}
	public void setMobile_click_count(String mobile_click_count) {
		this.mobile_click_count = mobile_click_count;
	}


}
