package cn.com.nbd.nbdmobile.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 *  活动列表
 */
public class ActivityArticleList implements Serializable, Parcelable {


	@Expose
	private List<ActivityMainArticle>  data;

	public List<ActivityMainArticle> getData() {
		return data;
	}

	public void setData(List<ActivityMainArticle> data) {
		this.data = data;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(data);
	}

	public ActivityArticleList() {
	}

	protected ActivityArticleList(Parcel in) {
		this.data = in.createTypedArrayList(ActivityMainArticle.CREATOR);
	}

	public static final Parcelable.Creator<ActivityArticleList> CREATOR = new Parcelable.Creator<ActivityArticleList>() {
		public ActivityArticleList createFromParcel(Parcel source) {
			return new ActivityArticleList(source);
		}

		public ActivityArticleList[] newArray(int size) {
			return new ActivityArticleList[size];
		}
	};
}
