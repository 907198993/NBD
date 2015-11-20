package cn.com.nbd.nbdmobile.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import org.hjh.annomation.ColumnInt;
import org.hjh.annomation.ColumnPrimaryKey;
import org.hjh.annomation.ColumnString;
import org.hjh.annomation.PrimaryKeyType;
import org.hjh.annomation.Table;

import java.io.Serializable;

@Table(TableName = "ActivityArticle")
public final class ActivityArticle implements Serializable,Parcelable {


	@ColumnPrimaryKey(TYPE= PrimaryKeyType.DEFINE)
	@ColumnInt
    private  int ids;

    @ColumnString(length = 64)
    @Expose
	private  String id;

	@ColumnInt
	private int is_rolling_news;

	@ColumnString(length = 64)
	@Expose
	private String pos;

	@ColumnInt
	private int special;

	@ColumnString(length = 64)
	@Expose
	private String type;

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
	private String column_id;

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

	public int getIds() {
		return ids;
	}

	public void setIds(int ids) {
		this.ids = ids;
	}

	public String getMobile_click_count() {
		return mobile_click_count;
	}

	public void setMobile_click_count(String mobile_click_count) {
		this.mobile_click_count = mobile_click_count;
	}

	public int getSpecial() {
		return special;
	}

	public void setSpecial(int special) {
		this.special = special;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getColumn_id() {
		return column_id;
	}

	public void setColumn_id(String column_id) {
		this.column_id = column_id;
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


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.ids);
		dest.writeString(this.id);
		dest.writeInt(this.is_rolling_news);
		dest.writeString(this.pos);
		dest.writeInt(this.special);
		dest.writeString(this.type);
		dest.writeString(this.title);
		dest.writeString(this.digest);
		dest.writeString(this.url);
		dest.writeString(this.created_at);
		dest.writeString(this.column_id);
		dest.writeString(this.columnist_id);
		dest.writeString(this.content);
		dest.writeString(this.image);
		dest.writeString(this.mobile_click_count);
	}

	public ActivityArticle() {
	}

	protected ActivityArticle(Parcel in) {
		this.ids = in.readInt();
		this.id = in.readString();
		this.is_rolling_news = in.readInt();
		this.pos = in.readString();
		this.special = in.readInt();
		this.type = in.readString();
		this.title = in.readString();
		this.digest = in.readString();
		this.url = in.readString();
		this.created_at = in.readString();
		this.column_id = in.readString();
		this.columnist_id = in.readString();
		this.content = in.readString();
		this.image = in.readString();
		this.mobile_click_count = in.readString();
	}

	public static final Creator<ActivityArticle> CREATOR = new Creator<ActivityArticle>() {
		public ActivityArticle createFromParcel(Parcel source) {
			return new ActivityArticle(source);
		}

		public ActivityArticle[] newArray(int size) {
			return new ActivityArticle[size];
		}
	};
}
