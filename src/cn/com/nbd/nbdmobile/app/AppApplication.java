package cn.com.nbd.nbdmobile.app;


import android.app.Application;
import android.os.Environment;



import org.hjh.tools.FileTools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.com.nbd.nbdmobile.api.BaseApi;
import cn.com.nbd.nbdmobile.api.BaseComponent;
import cn.com.nbd.nbdmobile.base.DataBaseHelper;
import cn.com.nbd.nbdmobile.bean.ActivityArticle;
import cn.com.nbd.nbdmobile.bean.ArticleDetail;
import cn.com.nbd.nbdmobile.bean.ArticleDetailForRoll;
import cn.com.nbd.nbdmobile.config.AppPresences;
import cn.com.nbd.nbdmobile.config.BaseConfig;
import cn.com.nbd.nbdmobile.dao.ADArticleDao;
import cn.com.nbd.nbdmobile.db.SQLHelper;

public class AppApplication extends Application {
	private static AppApplication mAppApplication;
//	public static YoukuPlayerBaseConfiguration configuration;
	private SQLHelper sqlHelper;
	/** 缓存路径 */
	private static String cacheDir;
	private List<Class> publicList = new ArrayList<Class>();
	private List<Class> privateList = new ArrayList<Class>();
	@Override
	public void onCreate() {
		super.onCreate();

		mAppApplication = this;
		AppPresences.init(this);
		BaseApi.init(this);
		BaseComponent.init(this);
		FileTools.init(BaseConfig.PATH_IMAGE, BaseConfig.PATH_TEMP);
		publicList.add(ADArticleDao.class);
		publicList.add(ArticleDetail.class);
		publicList.add(ArticleDetailForRoll.class);
		publicList.add(ActivityArticle.class);//活动
		DataBaseHelper.addSystemTable(publicList);
		DataBaseHelper.addPrivateTable(privateList);
		initCacheDirPath();
//		configuration = new YoukuPlayerBaseConfiguration(this) {
//
//
//			/**
//			 * 通过覆写该方法，返回“正在缓存视频信息的界面”，
//			 * 则在状态栏点击下载信息时可以自动跳转到所设定的界面.
//			 * 用户需要定义自己的缓存界面
//			 */
//			@Override
//			public Class<? extends Activity> getCachingActivityClass() {
//				// TODO Auto-generated method stub
//				return CachingActivity.class;
//			}
//
//			/**
//			 * 通过覆写该方法，返回“已经缓存视频信息的界面”，
//			 * 则在状态栏点击下载信息时可以自动跳转到所设定的界面.
//			 * 用户需要定义自己的已缓存界面
//			 */
//
//			@Override
//			public Class<? extends Activity> getCachedActivityClass() {
//				// TODO Auto-generated method stub
//				return CachedActivity.class;
//			}
//
//			/**
//			 * 配置视频的缓存路径，格式举例： /appname/videocache/
//			 * 如果返回空，则视频默认缓存路径为： /应用程序包名/videocache/
//			 *
//			 */
//			@Override
//			public String configDownloadPath() {
//				// TODO Auto-generated method stub
//
//				//return "/myapp/videocache/";			//举例
//				return null;
//			}
//		};
	//	Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));

	}

	public static String getCacheDirPath() {
		return cacheDir;
	}

	private void initCacheDirPath() {
		File f;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			f = new File(Environment.getExternalStorageDirectory() + "/testnbd1/");
			if (!f.exists()) {
				f.mkdirs();
			}
		} else {
			f = getApplicationContext().getCacheDir();
		}
		cacheDir = f.getAbsolutePath();
	}
	/** ��ȡApplication */
	public static AppApplication getApp() {
		return mAppApplication;
	}

	/** ��ȡ��ݿ�Helper */
//	public SQLHelper getSQLHelper() {
//		if (sqlHelper == null)
//			sqlHelper = new SQLHelper(mAppApplication);
//		return sqlHelper;
//	}

//	@Override
//	public void onTerminate() {
//		// TODO Auto-generated method stub
//		if (sqlHelper != null)
//			sqlHelper.close();
//		super.onTerminate();
//	}

}
