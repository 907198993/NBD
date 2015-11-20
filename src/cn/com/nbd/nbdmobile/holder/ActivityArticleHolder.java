package cn.com.nbd.nbdmobile.holder;

import android.widget.ImageView;
import android.widget.TextView;

import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;

import cn.com.nbd.nbdmobile.R;


@InjectLayout(layout = R.layout.activity_article_list_item)
public class ActivityArticleHolder {
	
	
	@InjectView(id = R.id.image)
	public ImageView image;
	
	@InjectView(id = R.id.description)
	public TextView description;
	


}
