package cn.com.nbd.nbdmobile.view;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import cn.com.nbd.nbdmobile.R;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


/**
 * ViewPager实锟街碉拷锟街诧拷图锟斤拷锟斤拷远锟斤拷锟斤拷锟酵硷拷锟斤拷缇╋拷锟斤拷锟揭筹拷墓锟斤拷锟街诧拷图效锟斤拷
 * 锟斤拷支锟斤拷锟皆讹拷锟街诧拷页锟斤拷也支锟斤拷锟斤拷锟狡伙拷锟斤拷锟叫伙拷页锟斤拷
 * 
 *
 */

public class SlideShowView extends FrameLayout {
	
	// 使锟斤拷universal-image-loader锟斤拷锟斤拷锟饺★拷锟斤拷锟酵计拷锟斤拷锟揭拷锟斤拷痰锟斤拷锟絬niversal-image-loader-1.8.6-with-sources.jar
	private ImageLoader imageLoader = ImageLoader.getInstance();

    //锟街诧拷图图片锟斤拷锟斤拷
    private final static int IMAGE_COUNT = 5;
    //锟皆讹拷锟街诧拷锟斤拷时锟斤拷锟斤拷
    private final static int TIME_INTERVAL = 5;
    //锟皆讹拷锟街诧拷锟斤拷锟矫匡拷锟斤拷
    private final static boolean isAutoPlay = true; 
    
    //锟皆讹拷锟斤拷锟街诧拷图锟斤拷锟斤拷源
    private String[] imageUrls;
    //锟斤拷锟街诧拷图片锟斤拷ImageView 锟斤拷list
    private List<ImageView> imageViewsList;
    //锟斤拷圆锟斤拷锟絍iew锟斤拷list
    private List<View> dotViewsList;
    
    private ViewPager viewPager;
    //锟斤拷前锟街诧拷页
    private int currentItem  = 0;
    //锟斤拷时锟斤拷锟斤拷
    private ScheduledExecutorService scheduledExecutorService;
    
    private Context context;
    
    //Handler
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }
        
    };
    
    public SlideShowView(Context context) {
        this(context,null);
        // TODO Auto-generated constructor stub
    }
    public SlideShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }
    public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

		initImageLoader(context);
		
        initData();
        if(isAutoPlay){
            startPlay();
        }
        
    }
    /**
     * 锟斤拷始锟街诧拷图锟叫伙拷
     */
    private void startPlay(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
    }
    /**
     * 停止锟街诧拷图锟叫伙拷
     */
    private void stopPlay(){
        scheduledExecutorService.shutdown();
    }
    /**
     * 锟斤拷始锟斤拷锟斤拷锟紻ata
     */
    private void initData(){
        imageViewsList = new ArrayList<ImageView>();
        dotViewsList = new ArrayList<View>();

        // 一锟斤拷锟斤拷锟斤拷锟饺⊥计�
        new GetListTask().execute("");
    }
    /**
     * 锟斤拷始锟斤拷Views锟斤拷UI
     */
    private void initUI(Context context){
    	if(imageUrls == null || imageUrls.length == 0)
    		return;
    	
        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);
        
        LinearLayout dotLayout = (LinearLayout)findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();
        
        // 锟饺碉拷锟斤拷锟斤拷锟酵计拷锟斤拷锟斤拷锟斤拷
        for (int i = 0; i < imageUrls.length; i++) {
        	ImageView view =  new ImageView(context);
        	view.setTag(imageUrls[i]);
        	if(i==0)//锟斤拷一锟斤拷默锟斤拷图
        		view.setBackgroundResource(R.drawable.appmain_subject_1);
        	view.setScaleType(ScaleType.FIT_XY);
        	imageViewsList.add(view);
        	
        	ImageView dotView =  new ImageView(context);
        	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        	params.leftMargin = 4;
			params.rightMargin = 4;
			dotLayout.addView(dotView, params);
        	dotViewsList.add(dotView);
		}
        
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setFocusable(true);
        
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }
    
    /**
     * 锟斤拷锟絍iewPager锟斤拷页锟斤拷锟斤拷锟斤拷锟斤拷
     * 
     */
    private class MyPagerAdapter  extends PagerAdapter{

        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            //((ViewPag.er)container).removeView((View)object);
            ((ViewPager)container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
        	ImageView imageView = imageViewsList.get(position);

			imageLoader.displayImage(imageView.getTag() + "", imageView);
        	
            ((ViewPager)container).addView(imageViewsList.get(position));
            return imageViewsList.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub
            
        }
        
    }
    /**
     * ViewPager锟侥硷拷锟斤拷锟斤拷
     * 锟斤拷ViewPager锟斤拷页锟斤拷锟阶刺拷锟斤拷锟侥憋拷时锟斤拷锟斤拷
     * 
     */
    private class MyPageChangeListener implements OnPageChangeListener{

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
            case 1:// 锟斤拷锟狡伙拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
                isAutoPlay = false;
                break;
            case 2:// 锟斤拷锟斤拷锟叫伙拷锟斤拷
                isAutoPlay = true;
                break;
            case 0:// 锟斤拷锟斤拷锟斤拷锟斤拷锟叫伙拷锟斤拷匣锟斤拷呒锟斤拷锟斤拷锟斤拷
                // 锟斤拷前为锟斤拷锟揭伙拷牛锟斤拷锟绞憋拷锟斤拷锟斤拷锟斤拷蠡锟斤拷锟斤拷谢锟斤拷锟斤拷锟揭伙拷锟�
                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                    viewPager.setCurrentItem(0);
                }
                // 锟斤拷前为锟斤拷一锟脚ｏ拷锟斤拷时锟斤拷锟斤拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟叫伙拷锟斤拷锟斤拷锟揭伙拷锟�
                else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                    viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                }
                break;
        }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            
            currentItem = pos;
            for(int i=0;i < dotViewsList.size();i++){
                if(i == pos){
                    ((View)dotViewsList.get(pos)).setBackgroundResource(R.drawable.feature_point);
                }else {
                    ((View)dotViewsList.get(i)).setBackgroundResource(R.drawable.feature_point_cur);
                }
            }
        }
        
    }
    
    /**
     *执锟斤拷锟街诧拷图锟叫伙拷锟斤拷锟斤拷
     *
     */
    private class SlideShowTask implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (viewPager) {
                currentItem = (currentItem+1)%imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
        
    }
    
    /**
     * 锟斤拷锟絀mageView锟斤拷源锟斤拷锟斤拷锟斤拷锟节达拷
     * 
     */
    private void destoryBitmaps() {

        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //锟斤拷锟絛rawable锟斤拷view锟斤拷锟斤拷锟斤拷
                drawable.setCallback(null);
            }
        }
    }
 

	/**
	 * 锟届步锟斤拷锟斤拷,锟斤拷取锟斤拷锟�
	 * 
	 */
	class GetListTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				// 锟斤拷锟斤拷一锟斤拷锟斤拷梅锟斤拷锟剿接口伙拷取一锟斤拷锟街诧拷图片锟斤拷锟斤拷锟斤拷锟角从百讹拷锟揭的硷拷锟斤拷图片
				
				imageUrls = new String[]{
						"http://image.zcool.com.cn/56/35/1303967876491.jpg",
						"http://image.zcool.com.cn/59/54/m_1303967870670.jpg",
						"http://image.zcool.com.cn/47/19/1280115949992.jpg",
						"http://image.zcool.com.cn/59/11/m_1303967844788.jpg"
				};
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (result) {
		        initUI(context);
			}
		}
	}
	
	/**
	 * ImageLoader 图片锟斤拷锟斤拷锟绞硷拷锟�
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove
																																																																								// for
																																																																								// release
																																																																								// app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}