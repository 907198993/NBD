package cn.com.nbd.nbdmobile.holder;

import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;
import cn.com.nbd.nbdmobile.R;



@InjectLayout(layout = R.layout.video_item_layout)
public class VideoHolder {
	
	@InjectView(id = R.id.video_view)
	public VideoView videoView;
	
	@InjectView(id = R.id.video_image)
	public ImageView imageView;
	
	@InjectView(id = R.id.progressbar)
	public ProgressBar progressbar;
	
	@InjectView(id = R.id.video_play_btn)
	public ImageButton  videoplaybtn;
	
	
	
}
