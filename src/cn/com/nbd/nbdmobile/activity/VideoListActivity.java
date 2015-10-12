package cn.com.nbd.nbdmobile.activity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import org.hjh.inject.InjectCore;
import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;
import org.hjh.refresh.PullToRefreshBase;
import org.hjh.refresh.PullToRefreshBase.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.base.AppPublicAdapter;
import cn.com.nbd.nbdmobile.holder.VideoHolder;
import cn.com.nbd.nbdmobile.tool.BaseTools;
import cn.com.nbd.nbdmobile.view.PullToRefreshListView;



@InjectLayout(layout = R.layout.tab_client_layout)
public class VideoListActivity extends BaseActivity implements
		OnItemClickListener, AppPublicAdapter.IFillValue {

	@InjectView(id = R.id.pullview)
	private PullToRefreshListView refreshView;

	private ListView mListView;

	private List<VideoInfo> videoList = new ArrayList<VideoInfo>();

	private VideoInfo video;

	private AppPublicAdapter adapter;

	private int currentIndex = -1;

	private String url1 = "http://ht-mobile.cdn.turner.com/nba/big/teams/kings/2014/12/12/HollinsGlassmov-3462827_8382664.mp4";
	private String url2 = "http://www.iqiyi.com/dianshiju/20110418/48af82e3012faac7.html";

	private VideoView mVideoView;

	MediaController mMediaCtrl;

	private int playPosition = -1;
	private boolean isPaused = false;
	private boolean isPlaying = false;
	private Class clazz;
	private int currentPage = 1;
	private boolean loadMore = false;// 当前是否为加载更多

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		InjectCore.injectUI(this);
		init();

		mListView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if ((currentIndex < firstVisibleItem || currentIndex > mListView
						.getLastVisiblePosition()) && isPlaying) {
					System.out.println("滑动的：" + mVideoView.toString());
					playPosition = mVideoView.getCurrentPosition();
					mVideoView.pause();
					mVideoView.setMediaController(null);
					isPaused = true;
					isPlaying = false;
					System.out.println("视频已经暂停：" + playPosition);
				}
			}
		});
		/*
		 * mListView.setOnItemClickListener(new OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) { currentIndex=position;
		 * adapter.notifyDataSetChanged(); } });
		 */
	}

	private void init() {
		// 构造视频数据
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				video = new VideoInfo(url1, "猛龙过江" + i, R.drawable.video2);
			} else {
				video = new VideoInfo(url2, "猛龙过江" + i, R.drawable.video1);
			}
			videoList.add(video);
		}
		initPullView();

	}

	private void initPullView() {
		LinearLayout.LayoutParams params = (LayoutParams) refreshView.getLayoutParams();
		params.height = BaseTools.getWindowHight(VideoListActivity.this)
				- getStatusBarHeight();
		refreshView.setPullLoadEnabled(false);
		refreshView.setScrollLoadEnabled(true);
		mListView = refreshView.getRefreshableView();
		adapter = new AppPublicAdapter(mContext, videoList, VideoHolder.class,
				this);
		mListView.setAdapter(adapter);
		mListView.setDivider(null);
		mListView.setCacheColorHint(Color.TRANSPARENT);
		refreshView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				adapter.clear();//
				currentPage = 1;
				loadMore = false;
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				mHandler.showDialog(false);// 上拉加载不显示连接框
				loadMore = true;
			}
		});

		setLastUpdateTime(refreshView);
		mListView.setOnItemClickListener(this);
	}

	static class VideoInfo {
		private String url;
		private String videoName;
		private int videoImage;

		public VideoInfo(String url, String name, int path) {
			this.videoName = name;
			this.videoImage = path;
			this.url = url;
		}

		public String getVideoName() {
			return videoName;
		}

		public void setVideoName(String videoName) {
			this.videoName = videoName;
		}

		public int getVideoImage() {
			return videoImage;
		}

		public void setVideoImage(int videoImage) {
			this.videoImage = videoImage;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}

	@Override
	public void fillData(final int position, Object object) {
		final VideoHolder holder = (VideoHolder) object;
		holder.imageView.setImageDrawable(getResources().getDrawable(
				videoList.get(position).getVideoImage()));
		holder.videoplaybtn.setVisibility(View.VISIBLE);
		holder.imageView.setVisibility(View.VISIBLE);
		mMediaCtrl = new MediaController(VideoListActivity.this, false);

		if (currentIndex == position) {
			holder.videoplaybtn.setVisibility(View.INVISIBLE);
			holder.imageView.setVisibility(View.INVISIBLE);

			if (isPlaying || playPosition == -1) {
				if (mVideoView != null) {
					mVideoView.setVisibility(View.GONE);
					mVideoView.stopPlayback();
					holder.progressbar.setVisibility(View.GONE);
				}
			}
			mVideoView = holder.videoView;
			mVideoView.setVisibility(View.VISIBLE);
			mMediaCtrl.setAnchorView(mVideoView);
			mMediaCtrl.setMediaPlayer(mVideoView);
			mVideoView.setMediaController(mMediaCtrl);
			mVideoView.requestFocus();
			holder.progressbar.setVisibility(View.VISIBLE);
			if (playPosition > 0 && isPaused) {
				mVideoView.start();
				isPaused = false;
				isPlaying = true;
				holder.progressbar.setVisibility(View.GONE);
			} else {
				mVideoView.setVideoPath(videoList.get(position).getUrl());
				isPaused = false;
				isPlaying = true;
				System.out.println("播放新的视频");
			}

			mVideoView.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					if (mVideoView != null) {
						mVideoView.seekTo(0);
						mVideoView.stopPlayback();
						currentIndex = -1;
						isPaused = false;
						isPlaying = false;
						holder.progressbar.setVisibility(View.GONE);
						adapter.notifyDataSetChanged();
					}
				}
			});

			mVideoView.setOnPreparedListener(new OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mp) {
					holder.progressbar.setVisibility(View.GONE);
					mVideoView.start();
				}
			});

		} else {
			holder.videoplaybtn.setVisibility(View.VISIBLE);
			holder.imageView.setVisibility(View.VISIBLE);
			holder.progressbar.setVisibility(View.GONE);
		}

		holder.videoplaybtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentIndex = position;
				playPosition = -1;
				adapter.notifyDataSetChanged();
			}
		});

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}
}
