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
import java.util.ArrayList;
import java.util.List;

/**
 * 活动
 */
@Table(TableName = "ActivityArticle")
public final class ActivityArticle implements Serializable, Parcelable {


	@ColumnPrimaryKey(TYPE = PrimaryKeyType.DEFINE)
	@ColumnInt
//	private int ids;
//	@ColumnInt
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

	@Expose
	private List<ActivityLabelForTitle> app_feature_labels;

//	public int getIds() {
//		return ids;
//	}
//
//	public void setIds(int ids) {
//		this.ids = ids;
//	}

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

	public List<ActivityLabelForTitle> getApp_feature_labels() {
		return app_feature_labels;
	}

	public void setApp_feature_labels(List<ActivityLabelForTitle> app_feature_labels) {
		this.app_feature_labels = app_feature_labels;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	//	dest.writeInt(this.ids);
		dest.writeInt(this.id);
		dest.writeString(this.title);
		dest.writeString(this.lead);
		dest.writeString(this.click_count);
		dest.writeString(this.type);
		dest.writeString(this.created_at);
		dest.writeString(this.updated_at);
		dest.writeString(this.avatar);
		dest.writeList(this.app_feature_labels);
	}

	public ActivityArticle() {
	}

	protected ActivityArticle(Parcel in) {
	//	this.ids = in.readInt();
		this.id = in.readInt();
		this.title = in.readString();
		this.lead = in.readString();
		this.click_count = in.readString();
		this.type = in.readString();
		this.created_at = in.readString();
		this.updated_at = in.readString();
		this.avatar = in.readString();
		this.app_feature_labels = new ArrayList<ActivityLabelForTitle>();
		in.readList(this.app_feature_labels, List.class.getClassLoader());
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
