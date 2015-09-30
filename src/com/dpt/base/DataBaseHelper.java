package com.dpt.base;

import java.util.ArrayList;
import java.util.List;

import org.hjh.db.DBTools;
import org.hjh.db.SDCardSQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * SQLite
 * @author hjh
 * 2015-1-9上午9:36:59
 */
public final class DataBaseHelper extends SDCardSQLiteOpenHelper{

	private boolean privateDb = true;//�Ƿ�˽����ݿ�
	private static List<Class> privateList = new ArrayList<Class>();
	private static List<Class> systemList = new ArrayList<Class>();
	
	public DataBaseHelper(Context context, String name, CursorFactory factory,int version,boolean privateDb) {
		super(context, name, factory, version,false);
		this.privateDb = privateDb;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//是否是个人数据库
		if(privateDb){
			for(Class clazz : privateList){
				db.execSQL(DBTools.getCreateSql(clazz));
			}
		}
		
		for(Class clazz : systemList){
			db.execSQL(DBTools.getCreateSql(clazz));
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(privateDb){
			for(Class clazz : privateList){
				db.execSQL(DBTools.getDeleteSql(clazz));
			}
		}
		
		for(Class clazz : systemList){
			db.execSQL(DBTools.getDeleteSql(clazz));
		}
		
		onCreate(db);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
	
	public  static void addPrivateTable(List<Class> list){
		privateList.addAll(list);
	}
	
	public static void addSystemTable(List<Class> list){
		systemList.addAll(list);
	}
	
	
}
