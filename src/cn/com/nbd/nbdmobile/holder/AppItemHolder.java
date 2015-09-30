package cn.com.nbd.nbdmobile.holder;

import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.com.nbd.nbdmobile.R;


@InjectLayout(layout = R.layout.app_item_layout_one)
public class AppItemHolder {

	@InjectView(id = R.id.oneImageView)
	public ImageView oneImageView;
	
	@InjectView(id = R.id.oneView)
	public TextView oneView;
	
	@InjectView(id = R.id.twoView)
	public TextView twoView;
	
	@InjectView(id = R.id.threeView)
	public TextView threeView;
	
	@InjectView(id = R.id.fourImageView)
	public ImageView fourImageView;
	
	@InjectView(id = R.id.layout)
	public LinearLayout layout;
	
}
