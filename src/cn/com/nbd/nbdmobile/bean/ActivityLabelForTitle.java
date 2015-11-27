package cn.com.nbd.nbdmobile.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 2015/11/27.
 */
public class ActivityLabelForTitle implements Serializable, Parcelable {

	private String name;

	private List<ActivityLabelArticle> articles;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ActivityLabelArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<ActivityLabelArticle> articles) {
		this.articles = articles;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeTypedList(articles);
	}

	public ActivityLabelForTitle() {
	}

	protected ActivityLabelForTitle(Parcel in) {
		this.name = in.readString();
		this.articles = in.createTypedArrayList(ActivityLabelArticle.CREATOR);
	}

	public static final Parcelable.Creator<ActivityLabelForTitle> CREATOR = new Parcelable.Creator<ActivityLabelForTitle>() {
		public ActivityLabelForTitle createFromParcel(Parcel source) {
			return new ActivityLabelForTitle(source);
		}

		public ActivityLabelForTitle[] newArray(int size) {
			return new ActivityLabelForTitle[size];
		}
	};
}
