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

/**
 * 活动
 */
@Table(TableName = "ActivityMainArticle")
public final class ActivityMainArticle implements Serializable, Parcelable {


	@ColumnPrimaryKey(TYPE = PrimaryKeyType.DEFINE)
	@ColumnInt
	private int siteId;

	@ColumnInt
	private int id;
	@ColumnString(length = 64)
	@Expose
	private String title;


	@ColumnString(length = 64)
	@Expose
	private String lead;

	@ColumnString(length = 64)
	@Expose
	private String click_count;

	@ColumnString(length = 64)
	@Expose
	private String type;
	@ColumnString(length = 64)
	@Expose
	private String created_at;

	@ColumnString(length = 64)
	@Expose
	private String updated_at;

	@ColumnString(length = 64)
	@Expose
	private String avatar;

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLead() {
		return lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
	}

	public String getClick_count() {
		return click_count;
	}

	public void setClick_count(String click_count) {
		this.click_count = click_count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.siteId);
		dest.writeInt(this.id);
		dest.writeString(this.title);
		dest.writeString(this.lead);
		dest.writeString(this.click_count);
		dest.writeString(this.type);
		dest.writeString(this.created_at);
		dest.writeString(this.updated_at);
		dest.writeString(this.avatar);
	}

	public ActivityMainArticle() {
	}

	protected ActivityMainArticle(Parcel in) {
		this.siteId = in.readInt();
		this.id = in.readInt();
		this.title = in.readString();
		this.lead = in.readString();
		this.click_count = in.readString();
		this.type = in.readString();
		this.created_at = in.readString();
		this.updated_at = in.readString();
		this.avatar = in.readString();
	}

	public static final Creator<ActivityMainArticle> CREATOR = new Creator<ActivityMainArticle>() {
		public ActivityMainArticle createFromParcel(Parcel source) {
			return new ActivityMainArticle(source);
		}

		public ActivityMainArticle[] newArray(int size) {
			return new ActivityMainArticle[size];
		}
	};
}
