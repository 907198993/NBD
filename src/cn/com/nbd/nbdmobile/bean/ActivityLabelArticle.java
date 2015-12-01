package cn.com.nbd.nbdmobile.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import org.hjh.annomation.ColumnInt;
import org.hjh.annomation.ColumnString;
import org.hjh.annomation.Table;

import java.io.Serializable;

/**
 * Created by Dell on 2015/11/27.
 * 专题标签
 */
@Table(TableName = "ActivityLabelArticle")
public class ActivityLabelArticle  implements Serializable, Parcelable {

	@ColumnInt
	private int id;

	@ColumnString(length = 64)
	@Expose
	private String title;

	@ColumnString(length = 64)
	@Expose
	private String list_title;

	@ColumnString(length = 64)
	@Expose
	private String sub_title;

	@ColumnString(length = 64)
	@Expose
	private String digest;

	@ColumnString(length = 64)
	@Expose
	private String ori_author;

	@ColumnString(length = 64)
	@Expose
	private String third_author;

	@ColumnString(length = 64)
	@Expose
	private String copyright;

	@ColumnString(length = 64)
	@Expose
	private String author;

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
	private String image;

	@ColumnString(length = 64)
	@Expose
	private String content;

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

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getList_title() {
		return list_title;
	}

	public void setList_title(String list_title) {
		this.list_title = list_title;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getOri_author() {
		return ori_author;
	}

	public void setOri_author(String ori_author) {
		this.ori_author = ori_author;
	}

	public String getThird_author() {
		return third_author;
	}

	public void setThird_author(String third_author) {
		this.third_author = third_author;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.title);
		dest.writeString(this.list_title);
		dest.writeString(this.sub_title);
		dest.writeString(this.digest);
		dest.writeString(this.ori_author);
		dest.writeString(this.third_author);
		dest.writeString(this.copyright);
		dest.writeString(this.author);
		dest.writeString(this.type);
		dest.writeString(this.created_at);
		dest.writeString(this.updated_at);
		dest.writeString(this.image);
		dest.writeString(this.content);
	}

	public ActivityLabelArticle() {
	}

	protected ActivityLabelArticle(Parcel in) {
		this.id = in.readInt();
		this.title = in.readString();
		this.list_title = in.readString();
		this.sub_title = in.readString();
		this.digest = in.readString();
		this.ori_author = in.readString();
		this.third_author = in.readString();
		this.copyright = in.readString();
		this.author = in.readString();
		this.type = in.readString();
		this.created_at = in.readString();
		this.updated_at = in.readString();
		this.image = in.readString();
		this.content = in.readString();
	}

	public static final Parcelable.Creator<ActivityLabelArticle> CREATOR = new Parcelable.Creator<ActivityLabelArticle>() {
		public ActivityLabelArticle createFromParcel(Parcel source) {
			return new ActivityLabelArticle(source);
		}

		public ActivityLabelArticle[] newArray(int size) {
			return new ActivityLabelArticle[size];
		}
	};
}
