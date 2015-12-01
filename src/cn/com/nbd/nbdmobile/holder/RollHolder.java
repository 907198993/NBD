package cn.com.nbd.nbdmobile.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;

import cn.com.nbd.nbdmobile.R;


@InjectLayout(layout = R.layout.roll_article_item)
public class RollHolder {
	
	

	@InjectView(id = R.id.description)
	public TextView description;

	@InjectView(id = R.id.view_1)
	public View view_1;

	@InjectView(id = R.id.view_2)
	public View view_2;

	//阅读量
	@InjectView(id = R.id.read_text)
	public TextView readText;

	@InjectView(id = R.id.header)
	public TextView header;

	@InjectView(id = R.id.linear)
	public LinearLayout linear;

}
