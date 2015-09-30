package com.dpt.base;

import com.dpt.config.BaseConfig;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * @author hjh
 * 2015-1-9上午9:37:45
 * SQLite
 */
public final class DBManager {

	private String databaseName;

	private Context mContext = null;
	private static DBManager dBManager = null;

	private DBManager(Context mContext) {
		super();
		this.mContext = mContext;
	}
	
	private synchronized static void init(Context context){
		if(null == dBManager){
			dBManager = new DBManager(context);
		}
	}

	public static DBManager getInstance(Context mContext, String databaseName) {
		if (null == dBManager) {
			init(mContext);
		}
		
		dBManager.databaseName = databaseName;
		return dBManager;
	}
	
	/**
	 * 关闭数据库
	 */
	public void closeDatabase(SQLiteDatabase dataBase, Cursor cursor) {
		if (null != dataBase) {
			dataBase.close();
		}
		if (null != cursor) {
			cursor.close();
		}
	}

	/**
	 * 打开私人数据库
	 */
	public SQLiteDatabase openPrivateDatabase() {
		return getDatabaseHelper().getWritableDatabase();
	}
	
	/**
	 * 打开系统公用数据库
	 * @return
	 */
	public SQLiteDatabase openSystemDatabase(){
		return new DataBaseHelper(mContext,"system", null,
				BaseConfig.SYSTEM_DB_VERSION,false).getWritableDatabase();
	}

	private DataBaseHelper getDatabaseHelper() {
		return new DataBaseHelper(mContext,dBManager.databaseName, null,
				BaseConfig.PRIVATE_DB_VERSION,true);
	}
	
	
}
