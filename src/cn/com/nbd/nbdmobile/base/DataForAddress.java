package cn.com.nbd.nbdmobile.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库创建类
 * 
 * @author 
 * @time 2014年6月4日
 */
public class DataForAddress extends SQLiteOpenHelper {

	// 数据库的名称
	private static final String DATEBASE_NAME = "address.db";
	// 数据库的版本
	private static final int DATEBASE_VERSION = 1;

	// 必须定义一个构造函数，并且调用父类的构造函数
	public DataForAddress(Context context) {
		super(context, DATEBASE_NAME, null, DATEBASE_VERSION);
	}

	// 当数据库被第一次使用的时候，系统会判断是否已经创建数据库。若还未创建数据库，则运行下面的SQL语句完成数据库的创建
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE address(id integer primary key autoincrement,addressname vchar(20))");
	}

	// 当数据库的版本发生变化的时候，系统会自动调用这个方法，我们可以在这里完成数据库的更新等操作
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
}
