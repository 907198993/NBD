package cn.com.nbd.nbdmobile.holder;

import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;

import android.widget.ImageView;
import android.widget.TextView;

import cn.com.nbd.nbdmobile.R;



@InjectLayout(layout = R.layout.article_list_item)
public class ArticleHolder {
	
	
	@InjectView(id = R.id.image)
	public ImageView image;
	
	@InjectView(id = R.id.description)
	public TextView description;
	
	@InjectView(id = R.id.readnum)
	public TextView readnum;
	
	@InjectView(id = R.id.time)
	public TextView time;

}
