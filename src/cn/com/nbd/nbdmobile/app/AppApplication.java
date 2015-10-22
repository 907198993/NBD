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
import cn.com.nbd.nbdmobile.bean.ArticleDetail;
import cn.com.nbd.nbdmobile.bean.ArticleDetailForQuick;
import cn.com.nbd.nbdmobile.config.AppPresences;
import cn.com.nbd.nbdmobile.config.BaseConfig;
import cn.com.nbd.nbdmobile.db.SQLHelper;
import cn.com.nbd.nbdmobile.exception.ExceptionHandler;

public class AppApplication extends Application {
	private static AppApplication mAppApplication;
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
		publicList.add(ArticleDetail.class);
		publicList.add(ArticleDetailForQuick.class);
		DataBaseHelper.addSystemTable(publicList);
		DataBaseHelper.addPrivateTable(privateList);
		initCacheDirPath();
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
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
