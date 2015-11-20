package cn.com.nbd.nbdmobile.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import cn.com.nbd.nbdmobile.bean.ADArticle;
import cn.com.nbd.nbdmobile.bean.ArticleDetail;


public final class HomeGalleryView extends ImageView {

	private ArticleDetail ArticleDetail;

	private ADArticle ADArticle;
	private String imagePath;
	private boolean load = false;
	
	public HomeGalleryView(Context context){
		this(context, null);
	}
	
	public HomeGalleryView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ArticleDetail getArticleDetail() {
		return ArticleDetail;
	}

	public void setArticleDetail(cn.com.nbd.nbdmobile.bean.ArticleDetail articleDetail) {
		ArticleDetail = articleDetail;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public cn.com.nbd.nbdmobile.bean.ADArticle getADArticle() {
		return ADArticle;
	}

	public void setADArticle(cn.com.nbd.nbdmobile.bean.ADArticle ADArticle) {
		this.ADArticle = ADArticle;
	}
}
